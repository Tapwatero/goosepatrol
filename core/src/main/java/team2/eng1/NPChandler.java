package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.math.Vector2;

public class NPChandler implements ApplicationListener {

	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS = 4, FRAME_ROWS = 1;

    Vector2 position;

	// A variable for tracking elapsed time for the animation
	float stateTime;

    boolean inAggro=false;

    float speed=1.0f;

	@Override
	public void create() {
        Vector2 spawnpoint= new Vector2(2,2);
        setPosition(spawnpoint);
	}

    @Override
    public void resize(int width, int height) {

    }

    public Vector2 getPosition() {
        return position;
    }


    public void setPosition(Vector2 position) {
        this.position = position;
    }


    public void addPosition(Vector2 movementVector) {
        this.position.add(movementVector);
    }


    @Override
	public void render() {

        // if (inAggro) {
        //     npcState.setAnimationState(AnimationState.WALKING);
        // } else {
        //     npcState.setAnimationState(AnimationState.IDLE);
        // }
        Vector2 destination = new Vector2(275,175);
        Vector2 movementVector = new Vector2(destination.x-getPosition().x,destination.y-getPosition().y);

        movementVector.nor();
        movementVector.x=movementVector.x*speed;
        movementVector.y=movementVector.y*speed;
        addPosition(movementVector.scl(1));

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
