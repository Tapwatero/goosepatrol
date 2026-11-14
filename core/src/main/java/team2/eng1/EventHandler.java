package team2.eng1;

import com.badlogic.gdx.math.Vector2;

public class EventHandler {

    private static EventHandler instance  = new EventHandler();
    GeeseHorde geeseHorde  = GeeseHorde.getInstance();
    public U2BusActorState u2Bus;

    private boolean longBoiTriggered;
    private boolean u2BusTriggered;

    EventHandler() {
        longBoiTriggered = false;
        u2BusTriggered = false;
    }

    public static EventHandler getInstance() {
        return instance;
    }

    public void longBoi() {
        if (longBoiTriggered == false) {
            geeseHorde.spawnLongBoi();
            longBoiTriggered = true;
        }
    }

    public void u2Bus() {
        if (u2BusTriggered == false) {
            u2Bus = new U2BusActorState(new Vector2(GameScreen.gameCam.position.x,GameScreen.gameCam.position.y));
            BusRenderer.u2BusActorState = u2Bus;
            GeeseHorde.u2BusActorState = u2Bus;
            BusRenderer.u2BusActive = true;
            u2BusTriggered = true;
        }
    }

    public void dispose() {
        BusRenderer.u2BusActorState = null;
        GeeseHorde.u2BusActorState = null;
        instance = new EventHandler();
    }
}