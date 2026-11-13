package team2.eng1;


import com.badlogic.gdx.math.Vector2;

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

enum AnimationState {
    IDLE,
    WALKING,
}


// Both Individual Geese and the Player are actors, the GeeseHorde is essentially a wrapper on an array of geese actors

public abstract class AbstractActorState {

    public Direction direction;
    public Vector2 position;
    public AnimationState animationState;
    public float health;

//    private static final ActorState instance  = new ActorState();
//
//    public ActorState() {
//        position = new Vector2(3, 3);
//        direction = Direction.RIGHT;
//        animationState = AnimationState.IDLE;
//    }

//    public static ActorState getInstance() {
//        return instance;
//    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float newHealth) {
        this.health = newHealth;
    }

    public void incrementHealth() {
        this.health = health+1;
    }

    public void decrementHealth() {
        this.health = health-1;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void addPosition(Vector2 movementVector) {
        this.position.add(movementVector);
    }

    public void setAnimationState(AnimationState animationState) {
        this.animationState = animationState;
    }
}
