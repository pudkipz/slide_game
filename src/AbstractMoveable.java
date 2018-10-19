public abstract class AbstractMoveable implements IPositionable {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private final int width;
    private final int height;

    public AbstractMoveable(int x, int y, int dx, int dy, int width, int height) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
    }

    public void move() {
        x += dx * Game.TILE_SIZE;
        y += dy * Game.TILE_SIZE;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
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
