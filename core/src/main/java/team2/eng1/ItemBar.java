package team2.eng1;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class ItemBar {
    PlayerActorState playerState = PlayerActorState.getInstance();
    List<Item> hotbar= new ArrayList<>();
    int currentItem=0; //no item equipped
    SpriteBatch spriteBatch = GameState.getInstance().batch;

    public void render(float x, float y){
        Sprite sprite = new Sprite();
        Texture itemBarTexture = new Texture("GooseItemBar.png");
        TextureRegion itemBarRegion = new TextureRegion(itemBarTexture);
        itemBarRegion.setRegion(1,16 ,64 , 48);
        sprite.setRegion(itemBarRegion);
        sprite.setScale(20f);

       // sprite.setPosition(0,0);

        spriteBatch.draw(sprite,x-30, y-90, 60f, 60f);

        boolean spacePressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);
        boolean ZeroPressed = Gdx.input.isKeyPressed(Input.Keys.NUM_0);
        boolean OnePressed = Gdx.input.isKeyPressed(Input.Keys.NUM_1);
        boolean TwoPressed = Gdx.input.isKeyPressed(Input.Keys.NUM_2);

        if (spacePressed && currentItem!=0){
            hotbar.get(currentItem).use();
        }
        if (ZeroPressed){
            currentItem=0; //acts as no item equipped
        }
        if (OnePressed){
            currentItem=1;
        }
        if (TwoPressed){
            currentItem=2;
        }

    }
}
