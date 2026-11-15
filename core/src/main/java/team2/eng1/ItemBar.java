package team2.eng1;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class ItemBar {
    PlayerActorState playerState = PlayerActorState.getInstance();
    List<Item> hotbar= new ArrayList<>();
    int currentItem=0; //no item equipped
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    BitmapFont itemFont = new BitmapFont();

    public void render(float x, float y){
        if(hotbar.size()==0) {
            hotbar.add(new Peashooter());
            hotbar.add(new Item.PineappleGrenade(3));
        }
        Sprite sprite = new Sprite();
        Texture itemBarTexture = new Texture("GooseItemBar.png");
        TextureRegion itemBarRegion = new TextureRegion(itemBarTexture);
        itemBarRegion.setRegion(1,16 ,64 , 48);
        sprite.setRegion(itemBarRegion);
        sprite.setScale(20f);

        // sprite.setPosition(0,0);

        spriteBatch.draw(sprite,x-30, y-90, 60f, 60f);
        if(Gdx.input.isKeyJustPressed(Input.Keys.O)&&(hotbar.size()<5||hotbar.contains(null))){ //Temp: Add an item to the hotbar
            if(hotbar.contains(null)) {
                hotbar.set(hotbar.indexOf(null), new Item.PineappleGrenade(1));
            }else{
                hotbar.add(new Item.PineappleGrenade(1));
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.I)){ //Temp: Remove current item in the hotbar
            if(currentItem>=0) {
                hotbar.set(currentItem,null);
                currentItem=-1;
            }
        }
        for(int slot=0;slot<hotbar.size();slot++){ //Goes through hotbar to draw all items
            Item item=hotbar.get(slot);
            if(item!=null){
                spriteBatch.draw(item.getTexture(),x-27+(slot*11),y-42,8,9);
                itemFont.getData().setScale(0.5f);
                itemFont.draw(spriteBatch, String.format("%d", item.getItemCount()), x-23+(slot*11), y-38);
            }
        }
        boolean spacePressed = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        boolean ZeroPressed = Gdx.input.isKeyJustPressed(Input.Keys.NUM_0);
        boolean OnePressed = Gdx.input.isKeyJustPressed(Input.Keys.NUM_1);
        boolean TwoPressed = Gdx.input.isKeyJustPressed(Input.Keys.NUM_2);
        boolean ThreePressed = Gdx.input.isKeyJustPressed(Input.Keys.NUM_3);
        boolean FourPressed = Gdx.input.isKeyJustPressed(Input.Keys.NUM_4);
        boolean FivePressed = Gdx.input.isKeyJustPressed(Input.Keys.NUM_5);

        if (spacePressed && currentItem!=-1 && currentItem < hotbar.size()){
            hotbar.get(currentItem).use();
        }
        if (currentItem !=-1 && currentItem < hotbar.size()) {
            if (currentItem!=-1 && hotbar.get(currentItem) instanceof Peashooter) {
                Peashooter.equipped = true;
            } else {
                Peashooter.equipped = false;
            }
        } else {
            Peashooter.equipped = false;
        }
        if (ZeroPressed){
            currentItem=-1; //acts as no item equipped
        }
        if (OnePressed){
            currentItem=0;
        }
        if (TwoPressed){
            currentItem=1;
        }
        if (ThreePressed){
            currentItem=2;
        }
        if (FourPressed){
            currentItem=3;
        }
        if (FivePressed){
            currentItem=4;
        }

    }
    public int getCurrentItemSlot(){
        return currentItem;
    }
    public void addItemToBar(Item itemID,int ItemBarSlot){
        hotbar.set(ItemBarSlot, itemID);
    }
    public void highlightSlot(ShapeRenderer SlotRenderer){
        if(currentItem>=0){
        SlotRenderer.begin(ShapeRenderer.ShapeType.Line);
        SlotRenderer.setColor(Color.LIGHT_GRAY);
        SlotRenderer.rect(200+(currentItem*45),15,45,60);
        SlotRenderer.end();
        }
    }
    public void drawItems(SpriteBatch spriteBatch){
        for(Item item :hotbar){
            return;
        }
    }
}
