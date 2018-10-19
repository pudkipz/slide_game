import javafx.scene.paint.Color;

public class Tile implements IPositionable {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private Action.Type action;

    public Tile(int x, int y, int width, int height, Action.Type action) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.action = action;
    }

    public Action.Type getAction() {
        return action;
    }

    public Color getColor() {
        switch (action) {
            case NONE:
                return Color.CADETBLUE;
            case MOVE_LEFT:
                return Color.LEMONCHIFFON;
            case MOVE_RIGHT:
                return Color.INDIANRED;
            case MOVE_UP:
                return Color.PLUM;
            case MOVE_DOWN:
                return Color.GOLD;
            case MOVE_STOP:
                return Color.SALMON;
            default:
                return null;
        }
    }

    public void setAction(Action.Type action) {
        this.action = action;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
