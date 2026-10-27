package team2.eng1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements ApplicationListener {


    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100; // Scale objects

    private SpriteBatch batch;
    private Texture image;
    private Texture loadingScreen;
    Animator animator;
    MovementHandler movementHandler;

    // Tiled Maps Variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer mapRenderer;
    private OrthographicCamera gameCam;
    private Viewport gamePort;


    GooseState gooseState = GooseState.getInstance();

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        loadingScreen = new Texture("loadingscreen.jpg");
        animator = new Animator();
        movementHandler = new MovementHandler();
        animator.create();

        gameCam = new OrthographicCamera();


        gamePort = new FitViewport(V_WIDTH / PPM, V_HEIGHT / PPM, gameCam);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/level1.tmx");
        mapRenderer = new OrthoCachedTiledMapRenderer(map, 1/PPM);

        gameCam.position.set((gamePort.getWorldWidth() /2) + 50, (gamePort.getWorldHeight() / 2) + 50, 0);
    }

    @Override
    public void resize(int width, int height) {
        // use true here to center the camera
        gamePort.update(width,height,false);
    }


    @Override
    public void render() {

//        PerspectiveCamera camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        camera.position.set(0f, 0f, 10f);
//        camera.lookAt(0f, 0f, 0f);
//        camera.update();

        gameCam.position.set(gooseState.getPosition().x, gooseState.getPosition().y, 0);

        gameCam.update();

        mapRenderer.setView(gameCam);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


//

//        batch.draw(loadingScreen, 140, 210);

        mapRenderer.render();

        batch.begin();

        movementHandler.render();
        animator.render();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
