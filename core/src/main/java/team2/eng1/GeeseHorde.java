package team2.eng1;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Intersector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeeseHorde {

    public ArrayList<GeeseActorState> hordeMembers = new ArrayList<GeeseActorState>();

    Vector2 position;
    PlayerActorState playerState = PlayerActorState.getInstance();
    U2BusEvent u2BusEvent = U2BusEvent.getInstance();
    PineappleHandler pineappleHandler = PineappleHandler.getInstance();

    private static GeeseHorde instance  = new GeeseHorde();
    private float modifier;


    GeeseHorde() {
        for (int i = 0; i < 256; i++) {
            addGeeseToHorde();
        }
        modifier = 1;
    }

    public static GeeseHorde getInstance() {
        return instance;
    }

    private void addGeeseToHorde() {

        int randomNumX = ThreadLocalRandom.current().nextInt(0, 960);
        int randomNumY = ThreadLocalRandom.current().nextInt(0, 640);

        this.hordeMembers.add(new GeeseActorState(new Vector2(randomNumX, randomNumY), false));
    }

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

            if (u2BusEvent.active == true) {
                if (hordeMember.gooseHitbox.overlaps(u2BusEvent.u2BusState.busHitbox)) {
                    SoundSystem.playSoundRandom(SoundSystem.gooseHonk, 1f, (float) 1/hordeMember.size);
                    toRemove.add(hordeMember);
                    u2BusEvent.u2BusState.geeseClaimed += 1;
                }
            }

            if(pineappleHandler.getActive()) {
                if(Intersector.overlaps(pineappleHandler.getAOE(), hordeMember.gooseHitbox)){
                    toRemove.add(hordeMember);
                }
            }

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
            if (geeseActorState.size == 2) {
                modifier = 1;
            }
            hordeMembers.remove(geeseActorState);
            SoundSystem.playSoundRandom(SoundSystem.gooseWing, 0.5f, (float) 1/geeseActorState.size);
        });
    }

    public void dispose() {
        instance  = new GeeseHorde();
    }
}
