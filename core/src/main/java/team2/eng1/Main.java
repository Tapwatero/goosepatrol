package team2.eng1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private Texture image;
    private Texture loadingScreen;
    Animator animator;
    MovementHandler movementHandler;



    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        loadingScreen = new Texture("loadingscreen.jpg");
        animator = new Animator();
        movementHandler = new MovementHandler();
        animator.create();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        PerspectiveCamera camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 0f, 10f);
        camera.lookAt(0f, 0f, 0f);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

//        batch.draw(loadingScreen, 140, 210);

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
