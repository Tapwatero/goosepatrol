package team2.eng1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

    final GoosePatrol game;

    public static final int V_WIDTH = 295;
    public static final int V_HEIGHT = 185;
    public static final float PPM = 2; // Scale objects

    Animator animator;
    MovementHandler movementHandler;
    SoundSystem soundSystem;
    ItemBar itemBar;
    HealthBar healthBar;

//    NPCanimator npcAnimator;

    // Tiled Maps Variables
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer mapRenderer;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    ShapeRenderer shapeRenderer;

    GeeseHoard geeseHoard = GeeseHoard.getInstance();


    PlayerActorState playerState = PlayerActorState.getInstance();


    public GameScreen(final GoosePatrol game) {
        this.game = game;


        // Feel free to delete, this is just for testing the geese horde. It might be useful to have the goose horde sound playing at all times however
        SoundSystem.geeseHorde(true);

        shapeRenderer = new ShapeRenderer();
        gameCam = new OrthographicCamera();


        gamePort = new FitViewport(V_WIDTH / PPM, V_HEIGHT / PPM, gameCam);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/campus_east.tmx");
        mapRenderer = new OrthoCachedTiledMapRenderer(map, 1/PPM);
        itemBar = new ItemBar();
        healthBar = new HealthBar();


        gameCam.position.set((gamePort.getWorldWidth() /2) + 50, (gamePort.getWorldHeight() / 2) + 50, 0);
    }

    @Override
    public void resize(int width, int height) {
        // use true here to center the camera
        gamePort.update(width,height,true);
    }


    @Override
    public void render(float delta) {

//        PerspectiveCamera camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        camera.position.set(0f, 0f, 10f);
//        camera.lookAt(0f, 0f, 0f);
//        camera.update();

        geeseHoard.update();
        gameCam.position.set((gamePort.getWorldWidth() /2) + playerState.getPosition().x - 75, (gamePort.getWorldHeight() / 2) + playerState.getPosition().y - 25, 0);





        gameCam.update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            SoundSystem.geeseHorde(false);
            game.setScreen(new PauseScreen(game));
            dispose();
		}


        spriteBatch.setProjectionMatrix(gameCam.combined);

//        batch.draw(loadingScreen, 140, 210);

        mapRenderer.setView(gameCam);
        mapRenderer.render();

        spriteBatch.begin();

        game.movementHandler.render();
        game.geeseAnimator.render();
//        game.npcAnimator.render();
        game.playerAnimator.render();

        itemBar.render(gameCam.position.x,gameCam.position.y);

        spriteBatch.end();
        healthBar.draw(shapeRenderer);

    }

    @Override
    public void dispose() {
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
