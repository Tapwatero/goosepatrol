package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private static PineappleHandler instance;
    public static PineappleHandler getInstance() {
        if(instance==null){
            instance = new PineappleHandler();
        }
        return instance;
    }

    PineappleHandler(){
        x = 0;
        y = 0;
        areaOfEffect = new Circle();
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
    public Circle getAOE(){
        return areaOfEffect;
    }

    public void setX(float newX){
        x = newX;
    }
    public void setY(float newY){
        y = newY;
    }
    public void setAOE(float x, float y, float radius){
        areaOfEffect = new Circle(x, y, radius);
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
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    activeFalse();
                }
            }, 1f);
        }
    }
}
