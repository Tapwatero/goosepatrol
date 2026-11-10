package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeeseHoard {

    public ArrayList<GeeseActorState> hoardMembers = new ArrayList<GeeseActorState>();

    Vector2 position;
    PlayerActorState playerState = PlayerActorState.getInstance();


    private static final GeeseHoard instance  = new GeeseHoard();


    GeeseHoard() {
        for (int i = 0; i < 4; i++) {
            addGeeseToHoard();
        }
    }


    public static GeeseHoard getInstance() {
        return instance;
    }

    private void addGeeseToHoard() {

        int randomNumX = ThreadLocalRandom.current().nextInt(0, 300 + 1);
        int randomNumY = ThreadLocalRandom.current().nextInt(0, 300 + 1);

        this.hoardMembers.add(new GeeseActorState(new Vector2(randomNumX, randomNumY)));
    }




	public void update() {
        moveToPlayer();
	}


    public void moveToPlayer() {

        Vector2 destination = playerState.getPosition();

        List<GeeseActorState> toRemove = new ArrayList<GeeseActorState>();

        this.hoardMembers.forEach(hoardMember -> {
            Vector2 npcPosition = hoardMember.getPosition();


            Vector2 movementVector = new Vector2(destination.x-npcPosition.x,destination.y-(npcPosition.y+5)  );


            // Collision Logic
            if (Math.hypot(movementVector.x, movementVector.y) < 5) {
                playerState.setHealth((float) (playerState.getHealth()-0.25));
                toRemove.add(hoardMember);
            }


            movementVector.nor();
            movementVector.scl(0.2f);

            hoardMember.setDirection(movementVector.x < 0 ? Direction.LEFT : Direction.RIGHT);


            hoardMember.addPosition(movementVector);
        });

        toRemove.forEach(geeseActorState -> {
            hoardMembers.remove(geeseActorState);
        });


    }



}
