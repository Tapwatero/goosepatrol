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
import org.w3c.dom.Text;

public class Animator implements ApplicationListener {

	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS = 4, FRAME_ROWS = 1;


    GooseState gooseState = GooseState.getInstance();


	// Objects used
	Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    Animation<TextureRegion> idleAnimation; // Must declare frame type (TextureRegion)


    Texture walkSheet;
    Texture idleSheet;

    SpriteBatch spriteBatch;

	// A variable for tracking elapsed time for the animation
	float stateTime;



    private TextureRegion[] sheetToFrames(Texture sheet, int cols, int rows) {
        TextureRegion[][] tmp = TextureRegion.split(sheet,
            sheet.getWidth() / cols,
            sheet.getHeight() / rows);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }

        return frames;
    }

	@Override
	public void create() {

		// Load the sprite sheet as a Texture
		walkSheet = new Texture(Gdx.files.internal("Walk.png"));
        idleSheet = new Texture(Gdx.files.internal("Idle.png"));


        // Use the split utility method to create a 2D array of TextureRegions. This is
		// possible because this sprite sheet contains frames of equal size and they are
		// all aligned.


		// Initialize the Animation with the frame interval and array of frames, but also include the information about the sheet such as cols/row data
		walkAnimation = new Animation<TextureRegion>(0.25f, sheetToFrames(walkSheet, 4, 1));
        idleAnimation = new Animation<TextureRegion>(0.5f, sheetToFrames(idleSheet, 2, 1));

		// Instantiate a SpriteBatch for drawing and reset the elapsed animation
		// time to 0
		spriteBatch = new SpriteBatch();
		stateTime = 0f;
	}

    @Override
    public void resize(int width, int height) {

    }

    @Override
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time


		// Get current frame of animation for the current stateTime

        TextureRegion currentFrame;

        if (gooseState.animationState == AnimationState.WALKING) {
             currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }



        Sprite sprite = new Sprite(currentFrame);
        sprite.setScale(3f);

        sprite.setPosition(275,175);


        if (gooseState.direction == Direction.LEFT) {
            sprite.flip(true, false);
        }


        spriteBatch.begin();
        sprite.draw(spriteBatch);
		spriteBatch.end();
	}

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
	public void dispose() { // SpriteBatches and Textures must always be disposed
		spriteBatch.dispose();
		walkSheet.dispose();
        idleSheet.dispose();
	}
}
