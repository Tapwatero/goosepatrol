package team2.eng1;


import com.badlogic.gdx.math.Vector2;


// Both Individual Geese and the Player are actors, the GeeseHoard is essentially a wrapper on an array of geese actors

public class GeeseActorState extends AbstractActorState {


    private static final GeeseActorState instance  = new GeeseActorState();

    public GeeseActorState() {
        setPosition(new Vector2(0, 0));
        direction = Direction.RIGHT;
        animationState = AnimationState.IDLE;
    }

    public static GeeseActorState getInstance() {
        return instance;
    }
}
