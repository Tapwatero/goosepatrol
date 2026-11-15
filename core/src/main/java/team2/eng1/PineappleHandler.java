package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Timer;

public class PineappleHandler{
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    GameTimer gameTimer = GameTimer.getInstance();
    Texture pineappleTexture;
    Sprite pineappleSprite;
    Circle areaOfEffect;
    boolean active;
    float x;
    float y;
    int count;

    //allows variables like "active" and "count" to be universal and
    //accessed across all classes
    private static PineappleHandler instance = new PineappleHandler();
    public static PineappleHandler getInstance() {
        return instance;
    }

    PineappleHandler(){
        x = 0;
        y = 0;
        count = 0;
        areaOfEffect = new Circle();
        active = false;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public int getCount(){
        return count;
    }
    public Circle getAOE(){
        return areaOfEffect;
    }

    public void setX(float newX){
        x = newX;
    }
    public void setY(float newY){
        y = newY;
    }
    public void set(int newCount){
        count = newCount;
    }
    public void addCount(int moreCount){
        count += moreCount;
    }
    public void setAOE(float x, float y, float radius){
        areaOfEffect = new Circle(x, y, radius);
    }
    public void activeTrue(){
        //only allows the pineapple grenade to activate if there is
        //more than one in inventory, and it is not already activated
        if(!active && count>0) {
            active = true;
            GameState.getInstance().itemsUsed += 1;
            count--;
        }
    }
    public void activeFalse(){
        if (active == true) {
            SoundSystem.playSound(SoundSystem.pineappleExplode, 1f, 1f);
        }
        active = false;
    }

    public void render(){
        if (active = true) {
            pineappleTexture = new Texture(Gdx.files.internal("PineappleGrenade.png"));
            pineappleSprite = new Sprite(pineappleTexture);
            spriteBatch.draw(pineappleSprite, x+8, y, 8, 8);
            //delays the pineapple sprite disappearing for the specified seconds
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    activeFalse();
                }
            }, 1f);
        }
    }

    public void dispose() {
        instance = new PineappleHandler();
    }
}
