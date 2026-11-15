package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseScreen implements Screen {
    final GoosePatrol game;
    private GameScreen gameScreen;
    SpriteBatch spriteBatch = GameState.getInstance().batch;

	public PauseScreen(final GoosePatrol game, GameScreen gameScreen) {
		this.game = game;
        this.gameScreen = gameScreen;
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
            gameScreen.paused = false;
            game.setScreen(new GameScreen(game));
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            game.setScreen(new MainMenuScreen(game));
            //This is called, otherwise the geese horde noise will resume instead of restarting as intended
            SoundSystem.currentlyPlaying = 0;
            gameScreen.dispose();
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
