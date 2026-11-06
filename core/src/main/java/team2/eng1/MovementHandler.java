package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MovementHandler implements ApplicationListener {

	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS = 4, FRAME_ROWS = 1;

    GooseState gooseState = GooseState.getInstance();

	// A variable for tracking elapsed time for the animation
	float stateTime;

	@Override
	public void create() {

	}

    @Override
    public void resize(int width, int height) {

    }




    @Override
	public void render() {


        boolean aPressed = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.D);


        boolean anythingPressed = aPressed || wPressed || sPressed || dPressed;

        if (anythingPressed) {
            gooseState.setAnimationState(AnimationState.WALKING);
        } else {
            gooseState.setAnimationState(AnimationState.IDLE);
        }

        boolean justaPressed = Gdx.input.isKeyJustPressed(Input.Keys.A);
        boolean justwPressed = Gdx.input.isKeyJustPressed(Input.Keys.W);
        boolean justsPressed = Gdx.input.isKeyJustPressed(Input.Keys.S);
        boolean justdPressed = Gdx.input.isKeyJustPressed(Input.Keys.D);


        boolean justanythingPressed = justaPressed || justwPressed || justsPressed || justdPressed;

        if (justanythingPressed) {
            SoundSystem.footstep(true);
        } else if (!anythingPressed) {
            SoundSystem.footstep(false);
        }



        Vector2 movementVector = new Vector2(0, 0);

        if (aPressed && wPressed) {
            gooseState.direction = Direction.LEFT;

            movementVector = new Vector2(-1, 1);
        } else  if (aPressed && sPressed) {
            gooseState.direction = Direction.LEFT;
            movementVector = new Vector2(-1, -1);
        }
        else  if (dPressed && wPressed) {
            gooseState.direction = Direction.RIGHT;
            movementVector = new Vector2(1, 1);
        }  else  if (dPressed && sPressed) {
            gooseState.direction = Direction.RIGHT;
            movementVector = new Vector2(1, -1);
        }  else if (aPressed) {
            gooseState.direction = Direction.LEFT;
            movementVector = new Vector2(-1, 0);
        } else if  (dPressed) {
            gooseState.direction = Direction.RIGHT;
            movementVector = new Vector2(1, 0);
		} else if (wPressed) {
            movementVector = new Vector2(0, 1);
        }else if  (sPressed) {
            movementVector = new Vector2(0, -1);
        }

        movementVector.nor();
        movementVector.x=movementVector.x*0.1f;
        movementVector.y=movementVector.y*0.1f;
        gooseState.addPosition(movementVector.scl(1));


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
