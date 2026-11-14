package team2.eng1;

import com.badlogic.gdx.math.Vector2;

public class U2BusEvent {

    public U2BusActorState u2BusState = new U2BusActorState();
    public boolean active;

    Vector2 position;

    private static U2BusEvent instance  = new U2BusEvent();

    public static U2BusEvent getInstance() {
        return instance;
    }
    
    public void update() {
        if (active == true & u2BusState.geeseClaimed < 30) {
            movement();
        } else if (u2BusState.geeseClaimed >= 30) {
            exit();
        }
    }

    public void spawnU2Bus() {
        u2BusState.setPosition(new Vector2(GameScreen.gameCam.position.x,GameScreen.gameCam.position.y));
        active = true;
    }

    public void movement() {
        float x = u2BusState.getX();
        float y = u2BusState.getY();
        Vector2 movementVector = new Vector2(0, 0);
        u2BusState.busHitbox.set(x, y, 40f, 20f);

        switch (u2BusState.direction) {
            case UP:
                movementVector = new Vector2(0, 1);
                if (y-20 > GameScreen.gameCam.position.y) {
                    u2BusState.setDirection(Direction.RIGHT);
                }
                break;
            case DOWN:
                movementVector = new Vector2(0, -1);
                if (y+40 < GameScreen.gameCam.position.y) {
                    u2BusState.setDirection(Direction.LEFT);
                }
                break;
            case LEFT:
                movementVector = new Vector2(-1, 0);
                if (x+60 < GameScreen.gameCam.position.x) {
                    movementVector = new Vector2(0, 1);
                    u2BusState.setDirection(Direction.UP);
                }
                break;
            case RIGHT:
                movementVector = new Vector2(1, 0);
                if (x-30 > GameScreen.gameCam.position.x) {
                    u2BusState.setDirection(Direction.DOWN);
                }
                break;
        }

        movementVector.nor();
        movementVector.scl(1f);
        u2BusState.addPosition(movementVector);
    }

    public void exit() {
        float x = u2BusState.getX();
        float y = u2BusState.getY();
        Vector2 movementVector = new Vector2(1, 0);
        u2BusState.setDirection(Direction.RIGHT);
        u2BusState.busHitbox.set(x, y, 40f, 20f);
        movementVector.nor();
        movementVector.scl(1f);
        u2BusState.addPosition(movementVector);
    }

    public void dispose() {
        instance  = new U2BusEvent();
    }
}
