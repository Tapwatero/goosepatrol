package team2.eng1;


import com.badlogic.gdx.math.Vector2;

enum Direction {
    LEFT,
    RIGHT
}

enum AnimationState {
    IDLE,
    WALKING,
}


public class GooseState {

    public Direction direction;
    public Vector2 position;
    public AnimationState animationState;


    private static final GooseState instance  = new GooseState();

    private GooseState() {

        position = new Vector2(-50, 0);
        direction = Direction.RIGHT;
        animationState = AnimationState.IDLE;
    }

    public static GooseState getInstance() {
        return instance;
    }

    public Direction getDirection() {
        return direction;
    }

    public Vector2 getPosition() {
        return position;
    }


    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPositionY(float positionY) {
        this.position.y = positionY;
    }

    public void setPositionX(float positionX) {
        this.position.x = positionX;
    }

    public void setAnimationState(AnimationState animationState) {
        this.animationState = animationState;
    }
}
