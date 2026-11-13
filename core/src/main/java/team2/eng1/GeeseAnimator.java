package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;


public class GeeseAnimator extends Animator {

    float stateTime;
    SpriteBatch spriteBatch = GameState.getInstance().batch;
    GeeseHorde geeseHorde = GeeseHorde.getInstance();

    public GeeseAnimator(ArrayList<AnimationSheet> animationSheets) {
        super(animationSheets);
    }

    @Override
    public void create() {
        super.create();
        stateTime = 0f;
    }

    @Override
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time


        // Get current frame of animation for the current stateTime


        geeseHorde.hordeMembers.forEach(geeseActorState -> {

            TextureRegion currentFrame;

            if (geeseActorState.animationState == AnimationState.WALKING) {
                currentFrame = frameMap.get(AnimationId.Walk).getKeyFrame(stateTime, true);
            } else {
                currentFrame = frameMap.get(AnimationId.Idle).getKeyFrame(stateTime, true);
            }

            Sprite sprite = new Sprite(currentFrame);

            if (geeseActorState.direction == Direction.LEFT) {
                sprite.flip(true, false);
            }

            spriteBatch.draw(sprite, geeseActorState.getX(), geeseActorState.getY(), geeseActorState.size*17.5f, geeseActorState.size*17.5f);
        });

	}


}
