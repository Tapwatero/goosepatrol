package team2.eng1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PineappleState{
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    Texture pineappleTexture;
    Sprite pineappleSprite;
    boolean active;
    float x;
    float y;
    private static PineappleState instance;
    public static PineappleState getInstance() {
        if(instance==null){
            instance = new PineappleState();
        }
        return instance;
    }

    PineappleState(){
        x = 0;
        y = 0;
        active = false;
    }

    public boolean getActive(){
        return active;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public void setX(float newX){
        x = newX;
    }
    public void setY(float newY){
        y = newY;
    }
    public void activeTrue(){
        active = true;
    }
    public void activeFalse(){
        active = false;
    }

    public void render(){
        if (active) {
            pineappleTexture = new Texture(Gdx.files.internal("PineappleGrenade.png"));
            pineappleSprite = new Sprite(pineappleTexture);
            spriteBatch.draw(pineappleSprite, x+8, y, 8, 8);
        }
    }
}
