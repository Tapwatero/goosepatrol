package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseScreen implements Screen {
    final GoosePatrol game;

	public PauseScreen(final GoosePatrol game) {
		this.game = game;
	}

    @Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0.01f);

		game.viewport.apply();
		game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

		game.batch.begin();
		//draw text. Remember that x and y are in meters
		game.font.draw(game.batch, "Welcome to Pause Menu!!! ", 1, 1.5f);
		game.font.draw(game.batch, "Press P to unpause and M for menu.", 1, 1);
		game.batch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
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
