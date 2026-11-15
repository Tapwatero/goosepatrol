package team2.eng1;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {

    final GoosePatrol game;

    public static final int V_WIDTH = 295;
    public static final int V_HEIGHT = 185;
    public static final float PPM = 2; // Scale objects

    MovementHandler movementHandler;
    Peashooter peashooter;
    ItemBar itemBar;
    HealthBar healthBar;
    GameTimer gameTimer = GameTimer.getInstance();

    GeeseAnimator geeseAnimator;
    PlayerAnimator playerAnimator;
//    NPCanimator npcAnimator;
    BusRenderer busRenderer;
    
    // Tiled Maps Variables
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer mapRenderer;
    public static OrthographicCamera gameCam;
    private Viewport gamePort;
    ShapeRenderer shapeRenderer;
    ShapeRenderer SlotRenderer;
    private TiledMapTileLayer logic;

    public static int mapWidth;
    public static int mapHeight;

    public static Rectangle playerHitbox;
    public static ArrayList<Rectangle> topWalls = new ArrayList<Rectangle> ();
    public static ArrayList<Rectangle> leftWalls = new ArrayList<Rectangle> ();
    public static ArrayList<Rectangle> bottomWalls = new ArrayList<Rectangle> ();
    public static ArrayList<Rectangle> rightWalls = new ArrayList<Rectangle> ();
    public static Rectangle longBoiEventTrigger;
    public static Rectangle u2BusEventTrigger;
    public static Rectangle winTrigger;

    public static boolean upEnabled;
    public static boolean leftEnabled;
    public static boolean downEnabled;
    public static boolean rightEnabled;

    public boolean paused = false;
    private int random = 0;

    // Singletons
    GameState gameState = GameState.getInstance();

    GeeseHorde geeseHorde = GeeseHorde.getInstance();

    U2BusEvent u2BusEvent = U2BusEvent.getInstance();

    EventHandler eventHandler = EventHandler.getInstance();

    PlayerActorState playerState = PlayerActorState.getInstance();

    PineappleHandler pineappleState = PineappleHandler.getInstance();

    Peashooter peashooter = Peashooter.getInstance();

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
        busRenderer = new BusRenderer();

        movementHandler = new MovementHandler();
        peashooter = new Peashooter();

        geeseAnimator.create();
        playerAnimator.create();
        busRenderer.create();

        shapeRenderer = new ShapeRenderer();
        SlotRenderer = new ShapeRenderer();
        gameCam = new OrthographicCamera();

        gamePort = new FitViewport(V_WIDTH / PPM, V_HEIGHT / PPM, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/campus_east.tmx");
        mapRenderer = new OrthoCachedTiledMapRenderer(map, 1/PPM);
        itemBar = new ItemBar();
        healthBar = new HealthBar();
        logic = (TiledMapTileLayer) map.getLayers().get(2);
        
        // Cycles through every cell inside the logic layer of the map and if they have properties, either handles their logic or prints their location
        for (int x = 0; x < logic.getWidth(); x++) {
            for (int y = 0; y < logic.getHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell == null) {
                    continue;
                }
                TiledMapTile tile = cell.getTile();
                switch (tile.getProperties().get("type").toString()) {
                    case "obstruction":
                        //every single block marked as obstruction has four walls created to block moving inside it. This includes adjacent blocks, which is inefficient.
                        //logic needs to be inverted for obstruction walls as opposed to world boundaries. (ie. the top wall of the map acts as the bottom wall of a block). 
                        //It is named in relation to the world boundaries because those were implemented first.
                        //hitboxes are slightly smaller than wall because otherwise the hitbox clips through multiple at once and movement is disabled.
                        bottomWalls.add(new Rectangle((float) x*16+2, (float) y*16+16, (float) 12, (float) 1));;
                        rightWalls.add(new Rectangle((float) x*16, (float) y*16+2, (float) 1, (float) 12));;
                        topWalls.add(new Rectangle((float) x*16+2 , (float) y*16, (float) 12, (float) 1));;
                        leftWalls.add(new Rectangle((float) x*16+16, (float) y*16+2, (float) 1, (float) 12));;
                        break;
                    case "spawn":
                        //Currently not handled
                        System.out.println("Tile at (" + x + ", " + y + ") is a goose spawn point");
                        break;
                    case "exit":
                        winTrigger = new Rectangle((float) x*16, (float) y*16, 16, 16);
                        break;
                    case "event":
                        //Handled manually later as they are both marked the same in the map file.
                        System.out.println("Tile at (" + x + ", " + y + ") is an event trigger.");
                        break;
                }
            }
        }

        gameCam.position.set((gamePort.getWorldWidth() /2) + 50, (gamePort.getWorldHeight() / 2) + 50, 0);
        mapWidth = (int) (logic.getWidth() * logic.getTileWidth() / PPM);
        mapHeight = (int) (logic.getHeight() * logic.getTileHeight() / PPM);

        playerHitbox = new Rectangle();

        //These are the world boundaries, mentioned earlier
        topWalls.add(new Rectangle(0, (float) mapHeight, (float) mapWidth, (float) 0.1));
        leftWalls.add(new Rectangle(0, 0, (float) 0.1, (float) mapHeight));
        bottomWalls.add(new Rectangle(0, 0, (float) mapWidth, (float) 0.1));
        rightWalls.add(new Rectangle((float) mapWidth, 0, (float) 0.1, (float) mapHeight));

        //This is where the event tiles are handled manually, as mentioned earlier.
        longBoiEventTrigger = new Rectangle((float) 16*16, (float) 10*16, 16, 16);
        u2BusEventTrigger = new Rectangle((float) 39*16, (float) 26*16, 16, 16);
        //Fixes an issue where the pineapple grenade would not affect geese on reset.
        //Previously, the initialise function was actually called when the geeseHorde was initialised.
        geeseHorde.initialise();
    }

    @Override
    public void resize(int width, int height) {
        // use true here to center the camera
        gamePort.update(width,height,true);
    }

    @Override
    public void render(float delta) {

        //Ensures all directions are enabled before collision is checked so only the ones that need to be disabled are.
        upEnabled = true;
        leftEnabled = true;
        downEnabled = true;
        rightEnabled = true;
        
//      Generates a random number between 1 and 120. Currently used for randomly playing goose honks.
        random = (int) (Math.random() * 120) + 1;

        geeseHorde.update();
        peashooter.update(delta, gamePort, playerState);

        u2BusEvent.update();
        gameCam.position.set((gamePort.getWorldWidth() /2) + playerState.getPosition().x - 75, (gamePort.getWorldHeight() / 2) + playerState.getPosition().y - 25, 0);

        gameCam.update();
        peashooter.update(delta, gamePort, playerState);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        playerHitbox.set(playerState.getX()+6.5f, playerState.getY()+3, 7f, 5f);

        //Four for loops per frame for detecting collision as we didn't have time to learn a physics engine. Inefficient but works for our purposes.
        //Disables directions if you walk
        for (Rectangle topWall : topWalls) {
            if (playerHitbox.overlaps(topWall)) {
                upEnabled = false;
            }
        }

        for (Rectangle leftWall : leftWalls) {
            if (playerHitbox.overlaps(leftWall)) {
                leftEnabled = false;
            }
        }

        for (Rectangle bottomWall : bottomWalls) {
            if (playerHitbox.overlaps(bottomWall)) {
                downEnabled = false;
            }
        }

        for (Rectangle rightWall : rightWalls) {
            if (playerHitbox.overlaps(rightWall)) {
                rightEnabled = false;
            }
        }

        if (playerHitbox.overlaps(longBoiEventTrigger)) {
            eventHandler.longBoi();
        }

        if (playerHitbox.overlaps(u2BusEventTrigger)) {
            eventHandler.u2Bus();
        }

        //SoundSystem.geeseHorde needs to be turned off (and currentlyPlaying set to 0) on any transition to a new game screen to prevent it playing on the new screen
        if (playerHitbox.overlaps(winTrigger)) {
            SoundSystem.geeseHorde(false);
            SoundSystem.currentlyPlaying = 0;
            game.setScreen(new EndScreen(game,this));
            dispose();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)||Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = true;
            game.setScreen(new PauseScreen(game, this));
		}

        if (geeseHorde.hordeMembers.size() > 5 & paused == false) {
            SoundSystem.geeseHorde(true);
        } else {
            SoundSystem.geeseHorde(false);
        }

        //1 in 120 chance per frame for a geese honk sound to play. This means the higher your framerate, the worse experience auditorially you will have.
        if (geeseHorde.hordeMembers.size() > 0) {
            if (random == 120) {
                SoundSystem.playSoundRandom(SoundSystem.gooseHonk, 0.25f, 1);
            }
        }

        if (playerState.getHealth() <= 0) {
            SoundSystem.geeseHorde(false);
            SoundSystem.currentlyPlaying = 0;
            game.setScreen(new EndScreen(game,this));
            dispose();
        }

        if (gameTimer.getTime()<=0){
            SoundSystem.geeseHorde(false);
            SoundSystem.currentlyPlaying = 0;
            game.setScreen(new EndScreen(game,this));
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
        if (u2BusEvent.active == true) {
            busRenderer.render();  
        }

        if (pineappleState.active == true){
            pineappleState.render();
        }

        itemBar.render(gameCam.position.x,gameCam.position.y);
        itemBar.drawItems(spriteBatch);
        gameTimer.render(gameCam.position.x,gameCam.position.y,spriteBatch,delta);

        spriteBatch.end();
        shapeRenderer.setProjectionMatrix(gameCam.combined);
        peashooter.render(shapeRenderer);
        healthBar.draw(shapeRenderer);
        itemBar.highlightSlot(SlotRenderer);
        shapeRenderer.setProjectionMatrix(gameCam.combined);
        peashooter.render(shapeRenderer);
    }

    @Override
    public void dispose() {
        // These need to be disposed of in this specific order or things break.
        // This is because some of these instances while ininitialising try to create instances of ones later down the list.
        // This also doesn't ACTUALLY dispose of anything like it should in best practice.
        // I am fairly certain this causes a memory leak, but this game is not large enough for this to be a worry at this stage
        gameState.dispose();
        playerState.dispose();
        u2BusEvent.dispose();
        pineappleState.dispose();
        geeseHorde.dispose();
        eventHandler.dispose();
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
