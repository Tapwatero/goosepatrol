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

        PineappleGrenade() {
            super("PineappleGrenade");
        }

        @Override
        public Texture getTexture() {
            return new Texture("libgdx.png");
        }
        @Override
        public void use() {

        }
    }
}
