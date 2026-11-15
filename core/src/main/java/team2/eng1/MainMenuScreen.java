package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;

public class MainMenuScreen implements Screen {
    final GoosePatrol game;
    SpriteBatch spriteBatch = GameState.getInstance().batch;

	public MainMenuScreen(final GoosePatrol game) {
		this.game = game;
	}

    @Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLACK);

		game.viewport.apply();
        spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

        spriteBatch.begin();
		//draw text. Remember that x and y are in meters
		game.font.draw(spriteBatch, "Welcome to Goose Patrol!!! ", 1, 2f);
        game.font.draw(spriteBatch,"Campus is chock full of geese, and soon they will reach critical mass!",1,1.75f);
        game.font.draw(spriteBatch,"Avoid them and find the exit before time runs out!",1,1.5f);
        game.font.draw(spriteBatch,"Press WASD to move, the number keys to switch item and Left Click to use!",1,1.25f);
        game.font.draw(spriteBatch,"Press P or Esc to pause at any time!",1,1f);
		game.font.draw(spriteBatch, "Press the spacebar to begin!", 1, 0.75f);
        spriteBatch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            GameTimer.getInstance().resetTime();
			game.setScreen(new GameScreen(game));
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
