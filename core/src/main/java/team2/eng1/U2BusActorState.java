package team2.eng1;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class U2BusActorState extends AbstractActorState {
    public Rectangle busHitbox;
    private static U2BusActorState instance  = new U2BusActorState();
    // Counter as only 30 geese may get on the bus before it leaves, for balance concerns. 
    // It can still remove geese it hits after it is leaving. This is intended behaviour.
    public int geeseClaimed;

    public U2BusActorState() {
        setPosition(new Vector2(0, 0));
        busHitbox = new Rectangle();
        direction = Direction.RIGHT;
        geeseClaimed = 0;
    }

    public static U2BusActorState getInstance() {
        return instance;
    }

    public void dispose() {
        instance  = new U2BusActorState();
    }
}
