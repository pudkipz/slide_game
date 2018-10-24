import javafx.scene.paint.Color;

public class Player extends AbstractMoveable {

    public Player(double x, double y, double dx, double dy, double width, double height) {
        super(x, y, dx, dy, width, height, Action.Type.PLAYER);
        setFill(Color.ORANGE);
        setStroke(Color.BLACK);
    }
}