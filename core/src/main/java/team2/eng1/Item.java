package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;


public abstract class Item {
    String itemName;
    Item(String itemName){
        this.itemName=itemName;
    }

    public abstract Texture getTexture();//Get the item's image

    public abstract void use();//The item's effect to be triggered by input

    public static class PineappleGrenade extends Item{
        PineappleHandler pineappleState = PineappleHandler.getInstance();
        PlayerActorState playerState = PlayerActorState.getInstance();
        
        PineappleGrenade() {
            super("PineappleGrenade");
        }

        @Override
        public Texture getTexture() {
            return new Texture("PineappleGrenade.png");
        }
        @Override
        public void use() {
            float playerX = playerState.getX();
            float playerY = playerState.getY();
            pineappleState.activeTrue();
            pineappleState.setX(playerState.getX());
            pineappleState.setY(playerState.getY());
            pineappleState.setAOE(playerX, playerY, 25);
        }
    }
}
