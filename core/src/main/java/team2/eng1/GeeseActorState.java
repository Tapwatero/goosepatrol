package team2.eng1;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

// Both Individual Geese and the Player are actors, the GeeseHorde is essentially a wrapper on an array of geese actors

public class GeeseActorState extends AbstractActorState {

    public Rectangle gooseHitbox;
    public int size;

    public GeeseActorState(Vector2 startingPosition, boolean boss) {
        setPosition(startingPosition);
        gooseHitbox = new Rectangle(startingPosition.x+5, startingPosition.y+8, 8.75f, 8.75f);
        direction = Direction.RIGHT;
        animationState = AnimationState.WALKING;
        //Size is passed to the animator to control its sprite and hitbox size.
        if (boss) {
            size = 2;
            health = 6;
        }
        else {
            size = 1;
            health = 2;
        }
    }
}
