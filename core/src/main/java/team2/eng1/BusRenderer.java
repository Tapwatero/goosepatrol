package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BusRenderer implements ApplicationListener {
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    Texture u2Bus;
    Sprite u2Sprite;
    U2BusEvent u2BusEvent = U2BusEvent.getInstance();

	@Override
	public void create() {
        u2Bus = new Texture(Gdx.files.internal("U2Bus.png"));
        u2Sprite = new Sprite(u2Bus);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

        if (u2BusEvent.active == true) {
            // Stops the bus from flickering between left and right states. Poor practice but I forgot flip was a toggle.
            if (u2Sprite.isFlipX()) {
                u2Sprite.flip(true, false);
            }

            if (u2BusEvent.u2BusState.direction == Direction.LEFT) {
                u2Sprite.flip(true, false);
            } else if (u2BusEvent.u2BusState.direction == Direction.UP) {
                u2Sprite.flip(true, false);
            }

            spriteBatch.draw(u2Sprite, u2BusEvent.u2BusState.getX(), u2BusEvent.u2BusState.getY(), 40f, 20);
        }
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
