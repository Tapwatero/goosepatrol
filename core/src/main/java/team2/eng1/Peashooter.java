package team2.eng1;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
feature/peashooter-item
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
main
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

feature/peashooter-item
public class Peashooter {

    private static final Peashooter instance = new Peashooter();

    public static Peashooter getInstance() {
        return instance;
    }

public class Peashooter extends Item{   
    public static boolean equipped;      

@   Override
    public Texture getTexture() {
        return new Texture("PineappleGrenade.png");
    }
    @Override
    public void use() {
    }
    @Override
    public int getItemCount(){
        //It's actually infinite, but 99 gets the idea across while not needing to implement an infinity symbol.
        return 99;
    }
    Peashooter() {
            super("Peashooter");
        }
 main

    private static class Pea {
        Vector2 position;
        Vector2 velocity;
        Rectangle hitbox;
 feature/peashooter-item

        float lifeTimer;
 main

        Pea(float x, float y, Vector2 direction, float speed) {
            this.position = new Vector2(x, y);
            this.velocity = new Vector2(direction).scl(speed);
            this.hitbox = new Rectangle(x, y, 3f, 3f);
 feature/peashooter-item

            this.lifeTimer = 1.5f;
            main
        }
    }

    private final ArrayList<Pea> activePeas = new ArrayList<>();
    private final Vector2 aimTarget = new Vector2();

    private float cooldownTimer = 0f;
 feature/peashooter-item
    private final float fireCooldown = 0.25f;
    private final float peaSpeed = 140f;
    private static boolean equipped = false;
    private static boolean fireQueued = false;

    public void triggerFire() {
        fireQueued = true;
    }

    private final float fireCooldown = 0.25f; 
    private final float peaSpeed = 140f;    
 main

    public void update(float delta, Viewport gamePort, PlayerActorState playerState) {
        if (cooldownTimer > 0f) {
            cooldownTimer -= delta;
        }

feature/peashooter-item
        boolean shouldFire = Gdx.input.isButtonPressed(Input.Buttons.LEFT) || fireQueued;
        fireQueued = false;

        if (equipped && cooldownTimer <= 0f && shouldFire && gamePort != null && playerState != null) {

        if (equipped && cooldownTimer <= 0f && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
 main
            aimTarget.set(Gdx.input.getX(), Gdx.input.getY());
            gamePort.unproject(aimTarget);

            float originX = playerState.getX() + 9f;
            float originY = playerState.getY() + 5f;

            Vector2 direction = new Vector2(aimTarget.x - originX, aimTarget.y - originY);
            if (direction.len2() > 0f) {
                direction.nor();
                activePeas.add(new Pea(originX, originY, direction, peaSpeed));
 feature/peashooter-item

                SoundSystem.playSoundRandom(SoundSystem.peashooterShoot, 1f, 1f);
                GameState.getInstance().itemsUsed+=1;
 main
                cooldownTimer = fireCooldown;
            }
        }

        ArrayList<GeeseActorState> hordeMembers = GeeseHorde.getInstance().hordeMembers;

        Iterator<Pea> peaIterator = activePeas.iterator();
        while (peaIterator.hasNext()) {
            Pea pea = peaIterator.next();
 feature/peashooter-item

            pea.lifeTimer -= delta;
            if (pea.lifeTimer <= 0f) {
                peaIterator.remove();
                continue;
            }
 main

            pea.position.mulAdd(pea.velocity, delta);
            pea.hitbox.setPosition(pea.position.x, pea.position.y);

 feature/peashooter-item
            if (isOffScreen(pea.hitbox, gamePort) || hitsWall(pea.hitbox)) {
                peaIterator.remove();

            if (hitsWall(pea.hitbox)) {
                peaIterator.remove();
                SoundSystem.playSound(SoundSystem.peashooterSplat, 1f, 1f);
main
                continue;
            }

            Iterator<GeeseActorState> gooseIterator = hordeMembers.iterator();
            while (gooseIterator.hasNext()) {
                GeeseActorState goose = gooseIterator.next();
                if (pea.hitbox.overlaps(goose.gooseHitbox)) {
 feature/peashooter-item
                    gooseIterator.remove();

                    goose.health -= 1;
                    SoundSystem.playSound(SoundSystem.peashooterSplat, 1f, 1f);
main
                    peaIterator.remove();
                    break;
                }
            }
        }
    }

 feature/peashooter-item
    private boolean isOffScreen(Rectangle hitbox, Viewport gamePort) {
        if (gamePort == null) return false;
        Camera cam = gamePort.getCamera();
        float halfWidth = gamePort.getWorldWidth() / 2f;
        float halfHeight = gamePort.getWorldHeight() / 2f;

        float screenLeft = cam.position.x - halfWidth;
        float screenRight = cam.position.x + halfWidth;
        float screenBottom = cam.position.y - halfHeight;
        float screenTop = cam.position.y + halfHeight;

        return hitbox.x + hitbox.width < screenLeft
                || hitbox.x > screenRight
                || hitbox.y + hitbox.height < screenBottom
                || hitbox.y > screenTop;
    }


 main
    private boolean hitsWall(Rectangle hitbox) {
        for (Rectangle wall : GameScreen.topWalls) {
            if (hitbox.overlaps(wall)) return true;
        }
        for (Rectangle wall : GameScreen.bottomWalls) {
            if (hitbox.overlaps(wall)) return true;
        }
        for (Rectangle wall : GameScreen.leftWalls) {
            if (hitbox.overlaps(wall)) return true;
        }
        for (Rectangle wall : GameScreen.rightWalls) {
            if (hitbox.overlaps(wall)) return true;
        }
        return false;
    }

    public void render(ShapeRenderer shapeRenderer) {
        if (activePeas.isEmpty()) return;

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.LIME);
        for (Pea pea : activePeas) {
            shapeRenderer.rect(pea.position.x, pea.position.y, pea.hitbox.width, pea.hitbox.height);
        }
        shapeRenderer.end();
    }

feature/peashooter-item
    public void setEquipped(boolean isEquipped) {
        equipped = isEquipped;

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
 main
    }

    public boolean isEquipped() {
        return equipped;
    }
 feature/peashooter-item
}

}
main
