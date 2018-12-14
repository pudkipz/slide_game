package slide_game;

import slide_game.action_states.ActionState;

public abstract class AbstractMoveable extends Tile implements IPositionable {
    private double dx;
    private double dy;

    public AbstractMoveable(double x, double y, double dx, double dy, double width, double height, ActionState action) {
        super(x, y, width, height, action);

        this.dx = dx;
        this.dy = dy;
    }

    public void move() {
        setX(getX() + dx * Game.TILE_SIZE);
        setY(getY() + dy * Game.TILE_SIZE);
    }

    public void turnLeft() {
        if (dx != 0) {
            dy = dx*(-1);
            dx = 0;
        } else {
            dx = dy;
            dy = 0;
        }
    }

    public void turnRight() {
        turnAround();
        turnLeft();
    }

    public void turnAround() {
        turnLeft();
        turnLeft();
    }

    public void stop() {
        dx = 0;
        dy = 0;
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
