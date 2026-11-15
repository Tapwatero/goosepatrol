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

    public abstract int getItemCount();//The number of the item in inventory

    

    public static class PineappleGrenade extends Item{
        PineappleHandler pineappleState = PineappleHandler.getInstance();
        PlayerActorState playerState = PlayerActorState.getInstance();
        
        PineappleGrenade(int count) {
            super("PineappleGrenade");
            pineappleState.addCount(count);
        }

        @Override
        public Texture getTexture() {
            return new Texture("PineappleGrenade.png");
        }
        @Override
        public void use() {
            if (pineappleState.active == false) {
                float playerX = playerState.getX();
                float playerY = playerState.getY();
                pineappleState.activeTrue();
                if (pineappleState.active == true) {
                    SoundSystem.playSound(SoundSystem.pineappleArmed, 1f, 1f);
                }
                pineappleState.setX(playerState.getX());
                pineappleState.setY(playerState.getY());
                pineappleState.setAOE(playerX, playerY, 25);
            }
        }
        @Override
        public int getItemCount(){
            return pineappleState.getCount();
        }
    }
}
