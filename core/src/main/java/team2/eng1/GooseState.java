package team2.eng1;


import com.badlogic.gdx.math.Vector2;

enum Direction {
    LEFT,
    RIGHT
}




public class GooseState {

    public Direction direction;
    public Vector2 position;


    public GooseState() {

        position = new Vector2(-50, 0);
        direction = Direction.RIGHT;

    }

}
