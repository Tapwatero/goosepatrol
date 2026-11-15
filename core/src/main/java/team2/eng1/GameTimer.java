package team2.eng1;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This is the class used for the in game 5 minute timer.
 * worldTimer is exposed so that other classes can call functions pased off of the time.
 */

public class GameTimer{
    int startTime=300;
    int worldTimer=startTime;
    float timeSeconds=0;
    BitmapFont timerFont= new BitmapFont();

    private static GameTimer instance  = new GameTimer();

    public static GameTimer getInstance() {
        return instance;
    }

    public void render(float x, float y,SpriteBatch spriteBatch,float delta){
        //decrements the timer each frame.
        timeSeconds+=delta;
        if (worldTimer<=0){
           //prevents decrementing below 0, as the game should end when it reaches 0.
        }
        else if (timeSeconds >1f){
            timeSeconds=-1f;
            worldTimer-=1;
        }
        timerFont.setUseIntegerPositions(false);
        timerFont.draw(spriteBatch, String.format("%d", worldTimer), x+30, y-30);
    }
    public int getTime(){
        //for comparing the time to 0 to end the game.
        return worldTimer;
    }
    public void resetTime(){
        //resets the time for a new game.
        //needed as the time must save between pauses, but reset on a new game.
        worldTimer=startTime;
    }
}
