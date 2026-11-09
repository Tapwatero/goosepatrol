package team2.eng1;


import com.badlogic.gdx.math.Vector2;


// Both Individual Geese and the Player are actors, the GeeseHoard is essentially a wrapper on an array of geese actors

public class PlayerActorState extends AbstractActorState {


    private static final PlayerActorState instance  = new PlayerActorState();

    public PlayerActorState() {
        setPosition(new Vector2(3  , 2));
        direction = Direction.RIGHT;
        animationState = AnimationState.IDLE;
    }

    public static PlayerActorState getInstance() {
        return instance;
    }
}
