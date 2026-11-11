package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseScreen implements Screen {
    final GoosePatrol game;
    SpriteBatch spriteBatch = GameState.getInstance().batch;

	public PauseScreen(final GoosePatrol game) {
		this.game = game;
	}

    @Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0.01f);

		game.viewport.apply();
        spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

        spriteBatch.begin();
		//draw text. Remember that x and y are in meters
		game.font.draw(spriteBatch, "Welcome to Pause Menu!!! ", 1, 1.5f);
		game.font.draw(spriteBatch, "Press ESCAPE or P to unpause, and M for menu.", 1, 1);
        spriteBatch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.P)||Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new GameScreen(game));
			dispose();
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
	}

    @Override
    public void resize(int width, int height) {
	    game.viewport.update(width, height, true);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
