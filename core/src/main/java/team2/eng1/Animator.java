package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.HashMap;




public class Animator implements ApplicationListener {


    ArrayList<AnimationSheet> animationSheets;
    //a
    HashMap<AnimationId, Animation<TextureRegion>> frameMap = new HashMap<>();


    PlayerActorState playerState = PlayerActorState.getInstance();


	// A variable for tracking elapsed time for the animation

    private TextureRegion[] sheetToFrames(Texture sheet, AnimationSheet animationSheet) {

        int cols = animationSheet.getSheetCols();
        int rows = animationSheet.getSheetRows();
        int totalFrames = animationSheet.getTotalFrames()-1;

        TextureRegion[][] tmp = TextureRegion.split(sheet,
            sheet.getWidth() / cols,
            sheet.getHeight() / rows);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] frames = new TextureRegion[totalFrames+1];
        int index = 0;

        int frameCounter = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];

                if (frameCounter > totalFrames) {
                    break;
                }

                frameCounter++;
            }
        }

        System.out.println(frames.length);
        return frames;
    }


    public Animator(ArrayList<AnimationSheet> animationSheets) {
        this.animationSheets = animationSheets;
    }



    public void initializeFrames(ArrayList<AnimationSheet> animationSheets) {


        for (AnimationSheet animationSheet : animationSheets) {

            Texture texture = new Texture(Gdx.files.internal(String.format("%s.png", animationSheet.getAnimationId())));
            Animation<TextureRegion> animation = new Animation<TextureRegion>(animationSheet.getFrameDuration(), sheetToFrames(texture, animationSheet));

            frameMap.put(animationSheet.getAnimationId(), animation);
        }

    }

	@Override
	public void create() {
        initializeFrames(animationSheets);
		// Instantiate a SpriteBatch for drawing and reset the elapsed animation
		// time to 0
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
	public void dispose() { // SpriteBatches and Textures must always be disposed

	}
}
