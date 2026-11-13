package team2.eng1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState  {
    public  SpriteBatch batch;
    public  int itemsUsed;
    public  int eventsActivated;
    private static final GameState instance  = new GameState();

    GameState() {
        this.batch = new SpriteBatch();
        itemsUsed=0;
        eventsActivated=0;
    }

    public static GameState getInstance() {
        return instance;
    }
}
