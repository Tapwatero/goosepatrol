package team2.eng1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState  {
    public  SpriteBatch batch;
    private static final GameState instance  = new GameState();

    GameState() {
        this.batch = new SpriteBatch();
    }

    public static GameState getInstance() {
        return instance;
    }
}
