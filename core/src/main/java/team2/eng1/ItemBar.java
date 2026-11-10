package team2.eng1;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ItemBar {
    PlayerActorState playerState = PlayerActorState.getInstance();
    List<String> hotbar= new ArrayList<>();
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
    }
}
