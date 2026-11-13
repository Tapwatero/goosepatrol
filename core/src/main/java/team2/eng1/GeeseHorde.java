package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeeseHorde {

    public ArrayList<GeeseActorState> hordeMembers = new ArrayList<GeeseActorState>();

    Vector2 position;
    PlayerActorState playerState = PlayerActorState.getInstance();

    private static GeeseHorde instance  = new GeeseHorde();
    private float modifier;

    GeeseHorde() {
        for (int i = 0; i < 128; i++) {
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
        hordeMembers.add(new GeeseActorState(new Vector2(246, 0), true));
        modifier = 1.5f;
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
            hordeMember.gooseHitbox.set(npcx+(hordeMember.size*5), npcy+(hordeMember.size*8), hordeMember.size*8.75f, hordeMember.size*8.75f);

            Vector2 movementVector = new Vector2(x-npcx-(hordeMember.size*6),y-npcy-(hordeMember.size*6));

            // Collision Logic
            if (hordeMember.gooseHitbox.overlaps(GameScreen.playerHitbox)) {
                playerState.decrementHealth();
                SoundSystem.playSoundRandom(SoundSystem.gooseHiss, 1f, (float) 1/hordeMember.size);
                toRemove.add(hordeMember);
            }

            for (Rectangle topWall : GameScreen.topWalls) {
                if(hordeMember.gooseHitbox.overlaps(topWall)) {
                    movementVector = new Vector2(x-npcx-(hordeMember.size*6), MathUtils.clamp(y-npcy-(hordeMember.size*6), (float) Double.NEGATIVE_INFINITY, 0f));
                }
            }

            for (Rectangle leftWall : GameScreen.leftWalls) {
                if(hordeMember.gooseHitbox.overlaps(leftWall)) {
                    movementVector = new Vector2(MathUtils.clamp(x-npcx-(hordeMember.size*6), 0f, (float) Double.POSITIVE_INFINITY), y-npcy-(hordeMember.size*6));
                }
            }

            for (Rectangle bottomWall : GameScreen.bottomWalls) {
                if(hordeMember.gooseHitbox.overlaps(bottomWall)) {
                    movementVector = new Vector2(x-npcx-(hordeMember.size*6), MathUtils.clamp(y-npcy-(hordeMember.size*6), 0f, (float) Double.POSITIVE_INFINITY));
                }
            }

            for (Rectangle rightWall : GameScreen.rightWalls) {
                if(hordeMember.gooseHitbox.overlaps(rightWall)) {
                    movementVector = new Vector2(MathUtils.clamp(x-npcx-(hordeMember.size*6), (float) Double.NEGATIVE_INFINITY, 0f), y-npcy-(hordeMember.size*6));
                }
            }

            movementVector.nor();
            movementVector.scl((hordeMember.size*0.2f)*modifier);

            hordeMember.setDirection(movementVector.x < 0 ? Direction.LEFT : Direction.RIGHT);

            hordeMember.addPosition(movementVector);
        });

        toRemove.forEach(geeseActorState -> {
            if (geeseActorState.size == 2) {
                modifier = 1;
            }
            hordeMembers.remove(geeseActorState);
        });
    }

    public void dispose() {
        instance  = new GeeseHorde();
    }
}
