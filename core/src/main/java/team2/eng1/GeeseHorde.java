package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

        Vector2 destination = playerState.getPosition();

        List<GeeseActorState> toRemove = new ArrayList<GeeseActorState>();

        this.hordeMembers.forEach(hordeMember -> {
            Vector2 npcPosition = hordeMember.getPosition();


            Vector2 movementVector = new Vector2(destination.x-npcPosition.x,destination.y-(npcPosition.y+5)  );


            // Collision Logic
            if (Math.hypot(movementVector.x, movementVector.y) < 5) {
                playerState.setHealth((float) (playerState.getHealth()-0.1));
                SoundSystem.playSoundRandom(SoundSystem.gooseHiss, 1f);
                toRemove.add(hordeMember);
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
