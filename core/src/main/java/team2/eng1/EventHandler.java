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

    // The Event Handler only manages the events starting, it doesn't contain the code.
    // The code says longBoi but it simply is a goose with 2x size on both axes. A pale imitation.
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