package team2.eng1;


import com.badlogic.gdx.math.Vector2;


// Both Individual Geese and the Player are actors, the GeeseHorde is essentially a wrapper on an array of geese actors

public class PlayerActorState extends AbstractActorState {


    private static PlayerActorState instance  = new PlayerActorState();

    private PlayerActorState() {
        setPosition(new Vector2(50, 50));
        direction = Direction.RIGHT;
        animationState = AnimationState.IDLE;
        health = 10;
    }

    public static PlayerActorState getInstance() {
        return instance;
    }

    public void dispose() {
        instance = new PlayerActorState();
    }
}
