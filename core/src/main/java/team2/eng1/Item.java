package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public abstract class Item {
    public abstract void use(); // The item's effect to be triggered by input
    public abstract Texture getTexture(); // Get the item's hotbar icon
    public abstract int getItemCount(); // Get the item count in inventory

    public static class Peashooter extends Item {
        private Texture texture;

        public Peashooter() {
            super();
        }

        @Override
        public Texture getTexture() {
            if (texture == null) {
                if (Gdx.files.internal("Peashooter.png").exists()) {
                    texture = new Texture("Peashooter.png");
                } else {
                    Pixmap pixmap = new Pixmap(8, 8, Pixmap.Format.RGBA8888);
                    pixmap.setColor(Color.LIME);
                    pixmap.fill();
                    texture = new Texture(pixmap);
                    pixmap.dispose();
                }
            }
            return texture;
        }

        @Override
        public void use() {
            team2.eng1.Peashooter.getInstance().triggerFire();
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }

    public static class PineappleGrenade extends Item {
        PineappleHandler pineappleState = PineappleHandler.getInstance();
        PlayerActorState playerState = PlayerActorState.getInstance();
        private Texture texture;

        public PineappleGrenade(int count) {
            super();
            pineappleState.addCount(count);
        }

        @Override
        public Texture getTexture() {
            if (texture == null) {
                if (Gdx.files.internal("PineappleGrenade.png").exists()) {
                    texture = new Texture("PineappleGrenade.png");
                } else {
                    Pixmap pixmap = new Pixmap(8, 8, Pixmap.Format.RGBA8888);
                    pixmap.setColor(Color.YELLOW);
                    pixmap.fill();
                    texture = new Texture(pixmap);
                    pixmap.dispose();
                }
            }
            return texture;
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
        public int getItemCount() {
            return pineappleState.getCount();
        }
    }
}