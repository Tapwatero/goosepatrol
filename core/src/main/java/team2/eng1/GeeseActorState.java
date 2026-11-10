package team2.eng1;


import com.badlogic.gdx.math.Vector2;


// Both Individual Geese and the Player are actors, the GeeseHoard is essentially a wrapper on an array of geese actors

public class GeeseActorState extends AbstractActorState {



    public GeeseActorState(Vector2 startingPosition) {
        setPosition(startingPosition);
        direction = Direction.RIGHT;
        animationState = AnimationState.WALKING;
    }

}
