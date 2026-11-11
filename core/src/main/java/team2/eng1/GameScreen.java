package team2.eng1;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    final GoosePatrol game;

    public static final int V_WIDTH = 295;
    public static final int V_HEIGHT = 185;
    public static final float PPM = 2; // Scale objects

    MovementHandler movementHandler;
    ItemBar itemBar;
    HealthBar healthBar;
    GameTimer gameTimer;

    GeeseAnimator geeseAnimator;
    PlayerAnimator playerAnimator;
//    NPCanimator npcAnimator;

    // Tiled Maps Variables
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer mapRenderer;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    ShapeRenderer shapeRenderer;
    private TiledMapTileLayer logic;

    public static int mapWidth;
    public static int mapHeight;

    public boolean paused = false;
    private int random = 0;

    GeeseHorde geeseHorde = GeeseHorde.getInstance();

    PlayerActorState playerState = PlayerActorState.getInstance();

    public GameScreen(final GoosePatrol game) {
        this.game = game;

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

        geeseAnimator.create();
        playerAnimator.create();

        shapeRenderer = new ShapeRenderer();
        gameCam = new OrthographicCamera();

        gamePort = new FitViewport(V_WIDTH / PPM, V_HEIGHT / PPM, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/campus_east.tmx");
        mapRenderer = new OrthoCachedTiledMapRenderer(map, 1/PPM);
        itemBar = new ItemBar();
        healthBar = new HealthBar();
        gameTimer = new GameTimer();
        logic = (TiledMapTileLayer) map.getLayers().get(2);

        for (int x = 0; x < logic.getWidth(); x++) {
            for (int y = 0; y < logic.getHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell == null) {
                    continue;
                }
                TiledMapTile tile = cell.getTile();
                switch (tile.getProperties().get("type").toString()) {
                    case "obstruction":
                        System.out.println("Tile at (" + x + ", " + y + ") is an obstruction.");
                        break;
                    case "exit":
                        System.out.println("Tile at (" + x + ", " + y + ") is the exit.");
                        break;
                    case "event":
                        System.out.println("Tile at (" + x + ", " + y + ") is an event trigger.");
                        break;
                }
            }
        }

        gameCam.position.set((gamePort.getWorldWidth() /2) + 50, (gamePort.getWorldHeight() / 2) + 50, 0);
        mapWidth = (int) (logic.getWidth() * logic.getTileWidth() / PPM);
        mapHeight = (int) (logic.getHeight() * logic.getTileHeight() / PPM);
    }

    @Override
    public void resize(int width, int height) {
        // use true here to center the camera
        gamePort.update(width,height,true);
    }

    @Override
    public void render(float delta) {
        
//      Generates a random number between 1 and 120. Currently used for randomly playing goose honks.
        random = (int) (Math.random() * 120) + 1;

//        PerspectiveCamera camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        camera.position.set(0f, 0f, 10f);
//        camera.lookAt(0f, 0f, 0f);
//        camera.update();

        geeseHorde.update();
        gameCam.position.set((gamePort.getWorldWidth() /2) + playerState.getPosition().x - 75, (gamePort.getWorldHeight() / 2) + playerState.getPosition().y - 25, 0);

        gameCam.update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)||Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = true;
            game.setScreen(new PauseScreen(game, this));
		}

        if (geeseHorde.hordeMembers.size() > 5 & paused == false) {
            SoundSystem.geeseHorde(true);
        } else {
            SoundSystem.geeseHorde(false);
        }

        if (geeseHorde.hordeMembers.size() > 0) {
            if (random == 120) {
                SoundSystem.playSoundRandom(SoundSystem.gooseHonk, 0.25f);
            }
        }

        if (playerState.getHealth() <= 0) {
            SoundSystem.geeseHorde(false);
            SoundSystem.currentlyPlaying = 0;
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

        spriteBatch.setProjectionMatrix(gameCam.combined);

//        batch.draw(loadingScreen, 140, 210);

        mapRenderer.setView(gameCam);
        mapRenderer.render();

        spriteBatch.begin();

        movementHandler.render();
        geeseAnimator.render();
//        game.npcAnimator.render();
        playerAnimator.render();

        itemBar.render(gameCam.position.x,gameCam.position.y);
        gameTimer.render(gameCam.position.x,gameCam.position.y,spriteBatch,delta);

        spriteBatch.end();
        healthBar.draw(shapeRenderer);
    }

    @Override
    public void dispose() {
        playerState.dispose();
        geeseHorde.dispose();
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
