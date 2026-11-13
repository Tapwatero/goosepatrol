package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MovementHandler implements ApplicationListener {

	// Constant rows and columns of the sprite sheet

    PlayerActorState playerState = PlayerActorState.getInstance();

	// A variable for tracking elapsed time for the animation
	float stateTime;

    // A variable for tracking elapsed time so that footsteps don't play over themselves. May be redundant with stateTime
    float footstepTime;

	@Override
	public void create() {

	}

    @Override
    public void resize(int width, int height) {

    }

    @Override
	public void render() {

        footstepTime += Gdx.graphics.getDeltaTime();
        boolean aPressed = (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) & GameScreen.leftEnabled;
        boolean wPressed = (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) & GameScreen.upEnabled;
        boolean sPressed = (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) & GameScreen.downEnabled;
        boolean dPressed = (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) & GameScreen.rightEnabled;


        boolean anythingPressed = aPressed || wPressed || sPressed || dPressed;

        if (anythingPressed) {
            playerState.setAnimationState(AnimationState.WALKING);
            if (footstepTime > 0.5f) {
                SoundSystem.playSoundRandom(SoundSystem.playerFootstep, 1f, 1f);
                footstepTime = 0;
            }
        } else {
            playerState.setAnimationState(AnimationState.IDLE);
        }

        Vector2 movementVector = new Vector2(0, 0);

        if (aPressed && wPressed) {
            playerState.direction = Direction.LEFT;

            movementVector = new Vector2(-1, 1);
        } else  if (aPressed && sPressed) {
            playerState.direction = Direction.LEFT;
            movementVector = new Vector2(-1, -1);
        }
        else  if (dPressed && wPressed) {
            playerState.direction = Direction.RIGHT;
            movementVector = new Vector2(1, 1);
        }  else  if (dPressed && sPressed) {
            playerState.direction = Direction.RIGHT;
            movementVector = new Vector2(1, -1);
        }  else if (aPressed) {
            playerState.direction = Direction.LEFT;
            movementVector = new Vector2(-1, 0);
        } else if  (dPressed) {
            playerState.direction = Direction.RIGHT;
            movementVector = new Vector2(1, 0);
		} else if (wPressed) {
            playerState.direction = Direction.UP;
            movementVector = new Vector2(0, 1);
        }else if  (sPressed) {
            playerState.direction = Direction.DOWN;
            movementVector = new Vector2(0, -1);
        }

        movementVector.nor();
        movementVector.x=movementVector.x;
        movementVector.y=movementVector.y;
        playerState.addPosition(movementVector.scl(1));
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
