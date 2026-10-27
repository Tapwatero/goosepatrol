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


        if (aPressed && wPressed) {
            gooseState.direction = Direction.LEFT;
            gooseState.setPositionX(gooseState.getPosition().x - 3);
            gooseState.setPositionY(gooseState.getPosition().y + 3);



        } else  if (aPressed && sPressed) {
            gooseState.direction = Direction.LEFT;
            gooseState.setPositionX(gooseState.getPosition().x - 3);
            gooseState.setPositionY(gooseState.getPosition().y - 3);
        }
        else  if (dPressed && wPressed) {
            gooseState.direction = Direction.RIGHT;
            gooseState.setPositionX(gooseState.getPosition().x + 3);
            gooseState.setPositionY(gooseState.getPosition().y + 3);
        }  else  if (dPressed && sPressed) {
            gooseState.direction = Direction.RIGHT;
            gooseState.setPositionX(gooseState.getPosition().x + 3);
            gooseState.setPositionY(gooseState.getPosition().y - 3);
        }  else if (aPressed) {
            gooseState.direction = Direction.LEFT;
            gooseState.setPositionX(gooseState.getPosition().x - 3);
        } else if  (dPressed) {
            gooseState.direction = Direction.RIGHT;
            gooseState.setPositionX(gooseState.getPosition().x + 3);
		} else if (wPressed) {
            gooseState.setPositionY(gooseState.getPosition().y + 3);
        }else if  (sPressed) {
                gooseState.setPositionY(gooseState.getPosition().y - 3);
            }

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
