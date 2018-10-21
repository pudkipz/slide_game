import javafx.scene.shape.*;

public abstract class AbstractMoveable extends Rectangle implements IPositionable {
    private double dx;
    private double dy;

    public AbstractMoveable(double x, double y, double dx, double dy, double width, double height) {
        super(x, y, width, height);

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
