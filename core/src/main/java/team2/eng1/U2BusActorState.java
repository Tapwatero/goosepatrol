package team2.eng1;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class U2BusActorState extends AbstractActorState {
    public Rectangle busHitbox;

    public U2BusActorState(Vector2 startingPosition) {
        setPosition(startingPosition);
        busHitbox = new Rectangle();
        direction = Direction.RIGHT;
    }

}
