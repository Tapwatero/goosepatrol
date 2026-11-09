package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;


public class PlayerAnimator extends Animator {

    float stateTime;
    SpriteBatch spriteBatch = GameState.getInstance().batch;

    public PlayerAnimator(ArrayList<AnimationSheet> animationSheets) {
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
        TextureRegion currentFrame;

        if (playerState.animationState == AnimationState.WALKING) {
             currentFrame = frameMap.get(AnimationId.Walk).getKeyFrame(stateTime, true);
        } else {
            currentFrame = frameMap.get(AnimationId.Idle).getKeyFrame(stateTime, true);
        }



        Sprite sprite = new Sprite(currentFrame);
        sprite.setScale(3f);

        sprite.setPosition(275,175);


        if (playerState.direction == Direction.LEFT) {
            sprite.flip(true, false);
        }

        spriteBatch.draw(sprite, playerState.getPosition().x, playerState.getPosition().y, 1f, 1f);
	}


}
