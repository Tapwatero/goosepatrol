package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * This is the screen the game goes to upon reaching an end state.
 * Displays player score and allows them to exit.
 */

public class EndScreen implements Screen{
    final GoosePatrol game;
    private GameScreen gameScreen;
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    int itemsUsed =GameState.getInstance().itemsUsed;
    int eventsActivated =GameState.getInstance().eventsActivated;

	public EndScreen(final GoosePatrol game, GameScreen gameScreen) {
		this.game = game;
        this.gameScreen = gameScreen;
	}

    @Override
	public void render(float delta) {
        //Clears the last screen and renders the end screen text
		ScreenUtils.clear(0, 0, 0, 0.01f);

		game.viewport.apply();
        spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

        spriteBatch.begin();
		//draw text. Remember that x and y are in meters
		game.font.draw(spriteBatch, "Game end! ", 1, 1.5f);
        game.font.draw(spriteBatch, "Items used ="+String.format("%d", itemsUsed), 1, 1.25f);
        game.font.draw(spriteBatch, "Events activated ="+String.format("%d", eventsActivated), 1, 1);
		game.font.draw(spriteBatch, "Press ESC to exit to the menu.", 1, 0.75f);
        spriteBatch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            GameTimer.getInstance().resetTime();
            game.setScreen(new MainMenuScreen(game));
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
