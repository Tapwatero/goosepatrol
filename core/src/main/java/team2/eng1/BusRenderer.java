package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BusRenderer implements ApplicationListener {
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    Texture u1Bus;
    Texture u2Bus;
    Sprite u1Sprite;
    Sprite u2Sprite;
    U2BusEvent u2BusEvent = U2BusEvent.getInstance();

	@Override
	public void create() {
        u1Bus = new Texture(Gdx.files.internal("U1Bus.png"));
        u2Bus = new Texture(Gdx.files.internal("U2Bus.png"));
        u1Sprite = new Sprite(u1Bus);
        u2Sprite = new Sprite(u2Bus);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

        /* if (u1Sprite.isFlipX()) {
            u1Sprite.flip(true, false);
        }
        u1Sprite.setRotation(0); */

        /* if (u1BusActorState.direction == Direction.UP) {
            u1Sprite.setRotation(90);
        } else if (u1BusActorState.direction == Direction.LEFT) {
            u1Sprite.flip(true, false);
        } else if (u1BusActorState.direction == Direction.DOWN) {
            u1Sprite.setRotation(90);
            u1Sprite.flip(true, false);
        } */

        /* if (u1BusActive) {
            spriteBatch.draw(u1Sprite, u1BusActorState.getX(), u1BusActorState.getY(), 40f, 20);
        } */

        if (u2BusEvent.active == true) {
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
