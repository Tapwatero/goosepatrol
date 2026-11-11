package team2.eng1;


import com.badlogic.gdx.math.Vector2;


// Both Individual Geese and the Player are actors, the GeeseHorde is essentially a wrapper on an array of geese actors

public class PlayerActorState extends AbstractActorState {


    private static PlayerActorState instance  = new PlayerActorState();
    private float health;

    private PlayerActorState() {
        setPosition(new Vector2(50  , 50));
        health = 1;
        direction = Direction.RIGHT;
        animationState = AnimationState.IDLE;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float newHealth) {
        this.health = newHealth;
    }

    public static PlayerActorState getInstance() {
        return instance;
    }

    public void dispose() {
        instance = new PlayerActorState();
    }
}
