package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.math.Vector2;

public class NPChandler implements ApplicationListener {

	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS = 4, FRAME_ROWS = 1;

    Vector2 position;
    GeeseActorState actorState = GeeseActorState.getInstance();
    PlayerActorState playerState = PlayerActorState.getInstance();


    // A variable for tracking elapsed time for the animation
	float stateTime;

    boolean inAggro=false;

    float speed=1.0f;

	@Override
	public void create() {
	}

    @Override
    public void resize(int width, int height) {

    }


    @Override
	public void render() {

        // if (inAggro) {
        //     npcState.setAnimationState(AnimationState.WALKING);
        // } else {
        //     npcState.setAnimationState(AnimationState.IDLE);
        // }

        moveToPlayer();

	}


    public void moveToPlayer() {
        Vector2 destination = playerState.getPosition();
        Vector2 npcPosition = actorState.getPosition();
        System.out.println("npc: " + npcPosition);
        System.out.println("player: " + destination);

        Vector2 movementVector = new Vector2(destination.x-npcPosition.x,destination.y-npcPosition.y);

        movementVector.nor();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }


}
