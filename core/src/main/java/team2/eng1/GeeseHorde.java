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


    GeeseHorde() {
        for (int i = 0; i < 256; i++) {
            addGeeseToHorde();
        }
    }


    public static GeeseHorde getInstance() {
        return instance;
    }

    private void addGeeseToHorde() {

        int randomNumX = ThreadLocalRandom.current().nextInt(0, 960);
        int randomNumY = ThreadLocalRandom.current().nextInt(0, 640);

        this.hordeMembers.add(new GeeseActorState(new Vector2(randomNumX, randomNumY)));
    }




	public void update() {
        moveToPlayer();
	}


    public void moveToPlayer() {

        float x = GameScreen.playerHitbox.getX();
        float y = GameScreen.playerHitbox.getY();

        List<GeeseActorState> toRemove = new ArrayList<GeeseActorState>();

        this.hordeMembers.forEach(hordeMember -> {
            Vector2 npcPosition = hordeMember.getPosition();
            float npcx = npcPosition.x;
            float npcy = npcPosition.y;
            hordeMember.gooseHitbox.set(npcx+5, npcy+8, 8.75f, 8.75f);


            Vector2 movementVector = new Vector2(x-npcx-5,y-npcy-5);


            // Collision Logic
            if (hordeMember.gooseHitbox.overlaps(GameScreen.playerHitbox)) {
                playerState.setHealth((float) (playerState.getHealth()-0.1));
                SoundSystem.playSoundRandom(SoundSystem.gooseHiss, 1f);
                toRemove.add(hordeMember);
            }

            for (Rectangle topWall : GameScreen.topWalls) {
                if(hordeMember.gooseHitbox.overlaps(topWall)) {
                    movementVector = new Vector2(x-npcx-5, MathUtils.clamp(y-npcy-5, (float) Double.NEGATIVE_INFINITY, 0f));
                }
            }

            for (Rectangle leftWall : GameScreen.leftWalls) {
                if(hordeMember.gooseHitbox.overlaps(leftWall)) {
                    movementVector = new Vector2(MathUtils.clamp(x-npcx-5, 0f, (float) Double.POSITIVE_INFINITY), y-npcy-5);
                }
            }

            for (Rectangle bottomWall : GameScreen.bottomWalls) {
                if(hordeMember.gooseHitbox.overlaps(bottomWall)) {
                    movementVector = new Vector2(x-npcx-5, MathUtils.clamp(y-npcy-5, 0f, (float) Double.POSITIVE_INFINITY));
                }
            }

            for (Rectangle rightWall : GameScreen.rightWalls) {
                if(hordeMember.gooseHitbox.overlaps(rightWall)) {
                    movementVector = new Vector2(MathUtils.clamp(x-npcx-5, (float) Double.NEGATIVE_INFINITY, 0f), y-npcy-5);
                }
            }

            movementVector.nor();
            movementVector.scl(0.2f);

            hordeMember.setDirection(movementVector.x < 0 ? Direction.LEFT : Direction.RIGHT);


            hordeMember.addPosition(movementVector);
        });

        toRemove.forEach(geeseActorState -> {
            hordeMembers.remove(geeseActorState);
        });
    }

    public void dispose() {
        hordeMembers.clear();
        instance  = new GeeseHorde();
    }

}
