package team2.eng1;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

public class GameTimer{
    int worldTimer=300;
    float timeSeconds=0;
    BitmapFont timerFont= new BitmapFont();
    

    public void render(float x, float y,SpriteBatch spriteBatch,float delta){
        timeSeconds+=delta;
        if (worldTimer==0){
            //call end screen
        }
        else if (timeSeconds >1f){
            timeSeconds=-1f;
            worldTimer-=1;
        }
        timerFont.draw(spriteBatch, String.format("%d", worldTimer), x+30, y-30);
    }
}
