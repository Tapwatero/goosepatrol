package team2.eng1;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;


public class HealthBar  {


    PlayerActorState playerActorState = PlayerActorState.getInstance();
    final int width = 190;

    public HealthBar() {
    }

    public void draw(ShapeRenderer shapeRenderer) {
        float progress = playerActorState.getHealth();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(0, 0, width, 20);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(0, 0, width*progress, 20);
        shapeRenderer.end();

//        loadingBarBackground.draw(batch, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
//        ba.draw(batch, getX(), getY(), progress * getWidth() * getScaleX(), getHeight() * getScaleY());
    }
}
