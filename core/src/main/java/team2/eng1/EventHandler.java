package team2.eng1;

public class EventHandler {

    private static EventHandler instance  = new EventHandler();
    GeeseHorde geeseHorde  = GeeseHorde.getInstance();
    U2BusEvent u2BusEvent = U2BusEvent.getInstance();

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
            GameState.getInstance().eventsActivated += 1;
        }
    }

    public void u2Bus() {
        if (u2BusTriggered == false) {
            u2BusEvent.spawnU2Bus();
            u2BusTriggered = true;
            GameState.getInstance().eventsActivated += 1;
        }
    }

    public void dispose() {
        instance = new EventHandler();
    }
}