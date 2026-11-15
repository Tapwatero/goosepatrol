package team2.eng1;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
        timeSeconds+=delta;
        if (worldTimer<=0){
           
        }
        else if (timeSeconds >1f){
            timeSeconds=-1f;
            worldTimer-=1;
        }
        timerFont.draw(spriteBatch, String.format("%d", worldTimer), x+30, y-30);
    }
    public int getTime(){
        return worldTimer;
    }
    public void resetTime(){
        worldTimer=startTime;
    }
}
