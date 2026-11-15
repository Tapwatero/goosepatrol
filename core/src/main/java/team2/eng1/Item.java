package team2.eng1;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item {
    String itemName;
    Item(String itemName){
        this.itemName=itemName;
    }

    public abstract Texture getTexture();//Get the item's image

    public abstract void use();//The item's effect to be triggered by input

    public static class PineappleGrenade extends Item{
        PineappleState pineappleState = PineappleState.getInstance();
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
            pineappleState.activeTrue();
            pineappleState.setX(playerState.getX());
            pineappleState.setY(playerState.getY());
        }
    }
}
