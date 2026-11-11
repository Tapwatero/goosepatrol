package team2.eng1;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

// Both Individual Geese and the Player are actors, the GeeseHorde is essentially a wrapper on an array of geese actors

public class GeeseActorState extends AbstractActorState {

    public Rectangle gooseHitbox;

    public GeeseActorState(Vector2 startingPosition) {
        setPosition(startingPosition);
        gooseHitbox = new Rectangle();
        direction = Direction.RIGHT;
        animationState = AnimationState.WALKING;
    }



}
