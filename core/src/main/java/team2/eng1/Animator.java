package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.HashMap;




public class Animator implements ApplicationListener {


    ArrayList<team2.eng1.AnimationSheet> animationSheets;
    HashMap<AnimationId, Animation<TextureRegion>> frameMap = new HashMap<>();


    PlayerActorState playerState = PlayerActorState.getInstance();


	// A variable for tracking elapsed time for the animation

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


    public Animator(ArrayList<AnimationSheet> animationSheets) {
        this.animationSheets = animationSheets;
    }



    public void initializeFrames(ArrayList<AnimationSheet> animationSheets) {


        for (AnimationSheet animationSheet : animationSheets) {

            Texture texture = new Texture(Gdx.files.internal(String.format("%s.png", animationSheet.getAnimationId())));
            Animation<TextureRegion> animation = new Animation<TextureRegion>(animationSheet.getFrameDuration(), sheetToFrames(texture, animationSheet.getSheetCols(), animationSheet.getSheetRows()));

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
