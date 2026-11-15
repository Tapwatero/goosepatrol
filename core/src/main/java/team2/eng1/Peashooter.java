package team2.eng1;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Peashooter {

    private static class Pea {
        Vector2 position;
        Vector2 velocity;
        Rectangle hitbox;
        float lifeTimer;

        Pea(float x, float y, Vector2 direction, float speed) {
            this.position = new Vector2(x, y);
            this.velocity = new Vector2(direction).scl(speed);
            this.hitbox = new Rectangle(x, y, 3f, 3f);
            this.lifeTimer = 1.5f;
        }
    }

    private final ArrayList<Pea> activePeas = new ArrayList<>();
    private final Vector2 aimTarget = new Vector2();

    private float cooldownTimer = 0f;
    private final float fireCooldown = 0.25f; 
    private final float peaSpeed = 140f;      
    private boolean equipped = true;          

    public void update(float delta, Viewport gamePort, PlayerActorState playerState) {
        if (cooldownTimer > 0f) {
            cooldownTimer -= delta;
        }

        if (equipped && cooldownTimer <= 0f && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            aimTarget.set(Gdx.input.getX(), Gdx.input.getY());
            gamePort.unproject(aimTarget);

            float originX = playerState.getX() + 9f;
            float originY = playerState.getY() + 5f;

            Vector2 direction = new Vector2(aimTarget.x - originX, aimTarget.y - originY);
            if (direction.len2() > 0f) {
                direction.nor();
                activePeas.add(new Pea(originX, originY, direction, peaSpeed));
                cooldownTimer = fireCooldown;
            }
        }

        ArrayList<GeeseActorState> hordeMembers = GeeseHorde.getInstance().hordeMembers;

        Iterator<Pea> peaIterator = activePeas.iterator();
        while (peaIterator.hasNext()) {
            Pea pea = peaIterator.next();
            pea.lifeTimer -= delta;
            if (pea.lifeTimer <= 0f) {
                peaIterator.remove();
                continue;
            }

            pea.position.mulAdd(pea.velocity, delta);
            pea.hitbox.setPosition(pea.position.x, pea.position.y);

            if (hitsWall(pea.hitbox)) {
                peaIterator.remove();
                continue;
            }

            Iterator<GeeseActorState> gooseIterator = hordeMembers.iterator();
            while (gooseIterator.hasNext()) {
                GeeseActorState goose = gooseIterator.next();
                if (pea.hitbox.overlaps(goose.gooseHitbox)) {
                    gooseIterator.remove();
                    peaIterator.remove();
                    break;
                }
            }
        }
    }

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

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public boolean isEquipped() {
        return equipped;
    }
}