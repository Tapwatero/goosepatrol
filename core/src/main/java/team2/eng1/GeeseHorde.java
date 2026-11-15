package team2.eng1;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Intersector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeeseHorde {

    public ArrayList<GeeseActorState> hordeMembers = new ArrayList<GeeseActorState>();

    Vector2 position;
    //Used for detecting hitboxes
    PlayerActorState playerState = PlayerActorState.getInstance();
    U2BusEvent u2BusEvent = U2BusEvent.getInstance();
    PineappleHandler pineappleHandler = PineappleHandler.getInstance();

    private static GeeseHorde instance  = new GeeseHorde();
    private float modifier;


    public static GeeseHorde getInstance() {
        return instance;
    }

    public void initialise() {
        for (int i = 0; i < 256; i++) {
            addGeeseToHorde();
        }
        modifier = 1;
    }

    private void addGeeseToHorde() {

        int randomNumX = ThreadLocalRandom.current().nextInt(0, 960);
        int randomNumY = ThreadLocalRandom.current().nextInt(0, 640);
        GeeseActorState goose = new GeeseActorState(new Vector2(randomNumX, randomNumY), false);

        // This code means that if a goose tries to spawn in a wall, it will retry and dispose of the failed spawn, without affecting the counter.
        // This code doesn't exist for Long Boi but this is because he is spawned in a specific location as of now, not randomly.
        for (Rectangle topWall : GameScreen.topWalls) {
            if(goose.gooseHitbox.overlaps(topWall)) {
                addGeeseToHorde();
                return;
            }
        }

        for (Rectangle leftWall : GameScreen.leftWalls) {
            if(goose.gooseHitbox.overlaps(leftWall)) {
                addGeeseToHorde();
                return;
            }
        }

        for (Rectangle bottomWall : GameScreen.bottomWalls) {
            if(goose.gooseHitbox.overlaps(bottomWall)) {
                addGeeseToHorde();
                return;
            }
        }

        for (Rectangle rightWall : GameScreen.rightWalls) {
            if(goose.gooseHitbox.overlaps(rightWall)) {
                addGeeseToHorde();
                return;
            }
        }

        this.hordeMembers.add(goose);
    }

    //While LongBoi is on screen, all geese move at 2x speed. This is what the modifier variable controls. 
    //LongBoi's sound effects play at 0.5 pitch (because that was easier to implement), but here it is 0.25 for dramatic effect.
    public void spawnLongBoi() {
        hordeMembers.add(new GeeseActorState(new Vector2(150, 80), true));
        modifier = 2f;
        SoundSystem.playSoundRandom(SoundSystem.gooseHiss, 1f, 0.25f);
    }

	public void update() {
        moveToPlayer();
	}

    public void moveToPlayer() {
        float x = GameScreen.playerHitbox.getX();
        float y = GameScreen.playerHitbox.getY();

        List<GeeseActorState> toRemove = new ArrayList<GeeseActorState>();

        this.hordeMembers.forEach(hordeMember -> {
            float npcx = hordeMember.getX();
            float npcy = hordeMember.getY();
            float maxX = Float.POSITIVE_INFINITY;
            float minX = Float.NEGATIVE_INFINITY;
            float maxY = Float.POSITIVE_INFINITY;
            float minY = Float.NEGATIVE_INFINITY;
            hordeMember.gooseHitbox.set(npcx+(hordeMember.size*5), npcy+(hordeMember.size*8), hordeMember.size*8.75f, hordeMember.size*8.75f);

            // Collision Logic
            if (hordeMember.gooseHitbox.overlaps(GameScreen.playerHitbox)) {
                playerState.decrementHealth();
                SoundSystem.playSoundRandom(SoundSystem.gooseHiss, 1f, (float) 1/hordeMember.size);
                toRemove.add(hordeMember);
            }

            //The geese honk here instead of hissing because they are not being hit by the bus, they are being picked up by it and are therefore happy.
            //This is supposed to be explained/shown in game but currently isn't due to its prototype nature.
            //I simply cannot stress enough that they are NOT being hit by the bus, as per user requirements of nonviolence.
            //It is meant to be a joke on how the U1 bus picks up the players at the end.
            //Unfortunately, this also isn't implemented in this prototype, so the joke falls flat.
            if (u2BusEvent.active == true) {
                if (hordeMember.gooseHitbox.overlaps(u2BusEvent.u2BusState.busHitbox)) {
                    SoundSystem.playSoundRandom(SoundSystem.gooseHonk, 1f, (float) 1/hordeMember.size);
                    toRemove.add(hordeMember);
                    u2BusEvent.u2BusState.geeseClaimed += 1;
                }
            }

            //In a similar vein to the last one, they are eating the pineapple from the pineapple grenade. The "grenade" part of the name is a pun and nothing more.
            //The geese are safe and nonviolence is achieved.
            if(pineappleHandler.active == true) {
                if(Intersector.overlaps(pineappleHandler.getAOE(), hordeMember.gooseHitbox)){
                    toRemove.add(hordeMember);
                    SoundSystem.playSound(SoundSystem.gooseEat, 1f, (float) 1/hordeMember.size);
                }
            }

            //Another four for loops called per frame. Should be changed to be inline with player collision in GameScreen.
            //Instead of blocking movement in a direciton directly, they use the clamp function to prevent the X/Y from increasing/decreasing as necessary.
            //The movement vector should always be clamped to these values.
            for (Rectangle topWall : GameScreen.topWalls) {
                if(hordeMember.gooseHitbox.overlaps(topWall)) {
                    maxY = 0;
                }
            }

            for (Rectangle leftWall : GameScreen.leftWalls) {
                if(hordeMember.gooseHitbox.overlaps(leftWall)) {
                    minX = 0;
                }
            }

            for (Rectangle bottomWall : GameScreen.bottomWalls) {
                if(hordeMember.gooseHitbox.overlaps(bottomWall)) {
                    minY = 0;
                }
            }

            for (Rectangle rightWall : GameScreen.rightWalls) {
                if(hordeMember.gooseHitbox.overlaps(rightWall)) {
                    maxX = 0;
                }
            }

            Vector2 movementVector = new Vector2(MathUtils.clamp(x-npcx-(hordeMember.size*6), minX, maxX),MathUtils.clamp(y-npcy-(hordeMember.size*6), minY, maxY));
            movementVector.nor();
            movementVector.scl(0.2f*modifier);

            hordeMember.setDirection(movementVector.x < 0 ? Direction.LEFT : Direction.RIGHT);

            hordeMember.addPosition(movementVector);
        });

        toRemove.forEach(geeseActorState -> {
            //This turns off the speed increase if LongBoi is pacified.
            if (geeseActorState.size == 2) {
                modifier = 1;
            }
            hordeMembers.remove(geeseActorState);
            //We don't have an animation for them flying away. The sound effect is meant to convey this fact.
            SoundSystem.playSoundRandom(SoundSystem.gooseWing, 0.5f, (float) 1/geeseActorState.size);
        });
    }

    public void dispose() {
        instance  = new GeeseHorde();
    }
}
