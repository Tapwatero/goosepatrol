package team2.eng1;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


// Both Individual Geese and the Player are actors, the GeeseHoard is essentially a wrapper on an array of geese actors

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
