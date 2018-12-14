public abstract class AbstractMoveable extends Tile implements IPositionable {
    private double dx;
    private double dy;

    public AbstractMoveable(double x, double y, double dx, double dy, double width, double height, Action.Type action) {
        super(x, y, width, height, action);

        this.dx = dx;
        this.dy = dy;
    }

    public void move() {
        setX(getX() + dx * Game.TILE_SIZE);
        setY(getY() + dy * Game.TILE_SIZE);
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
