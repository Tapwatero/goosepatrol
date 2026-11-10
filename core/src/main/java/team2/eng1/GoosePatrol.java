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

        ArrayList<AnimationSheet> animationSheetsGeese = new ArrayList<>();
        animationSheetsGeese.add(new AnimationSheet(AnimationId.Idle, 0.25f, 2,1, 2));
        animationSheetsGeese.add(new AnimationSheet(AnimationId.Walk, 0.25f, 4,1, 4));

        ArrayList<AnimationSheet> animationSheetsPlayer = new ArrayList<>();
        animationSheetsPlayer.add(new AnimationSheet(AnimationId.SideIdlePlayer, 0.25f, 4,2, 5));
        animationSheetsPlayer.add(new AnimationSheet(AnimationId.SideWalkPlayer, 0.25f, 4,2, 6));
        animationSheetsPlayer.add(new AnimationSheet(AnimationId.UpIdlePlayer, 0.25f, 4,2, 5));
        animationSheetsPlayer.add(new AnimationSheet(AnimationId.DownIdlePlayer, 0.25f, 4,2, 5));
        animationSheetsPlayer.add(new AnimationSheet(AnimationId.UpWalkPlayer, 0.25f, 4,2, 6));
        animationSheetsPlayer.add(new AnimationSheet(AnimationId.DownWalkPlayer, 0.25f, 4,2, 6));



        playerAnimator = new PlayerAnimator(animationSheetsPlayer);
        geeseAnimator = new GeeseAnimator(animationSheetsGeese);



        movementHandler = new MovementHandler();
        soundSystem = new SoundSystem();

        geeseAnimator.create();
        playerAnimator.create();
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
