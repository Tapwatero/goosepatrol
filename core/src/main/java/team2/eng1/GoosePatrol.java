package team2.eng1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;


public class GoosePatrol extends Game {

    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100; // Scale objects

    public BitmapFont font;
    public FitViewport viewport;
    private Texture image;
    private Texture loadingScreen;

    GeeseAnimator geeseAnimator;
    PlayerAnimator playerAnimator;
    MovementHandler movementHandler;
    SoundSystem soundSystem;

	public void create() {
        image = new Texture("libgdx.png");
        loadingScreen = new Texture("loadingscreen.jpg");

        soundSystem = new SoundSystem();
        
        soundSystem.create();

		// use libGDX's default font
		font = new BitmapFont();
		viewport = new FitViewport(8, 5);

		//font has 15pt, but we need to scale it to our viewport by ratio of viewport height to screen height
		font.setUseIntegerPositions(false);
		font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		font.dispose();
        image.dispose();
	}

}
