import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static Scanner in = new Scanner(System.in);

    public static final double GAME_WIDTH = 800;
    public static final double GAME_HEIGHT = 400;

    public static final long ONE_SEC = 1_000_000_000;
    public static final long HALF_SEC = 500_000_000;
    public static final long TENTH_SEC = 100_000_000;
    public long previousTick;

    public static final double TILE_SIZE = 40.0;
    public static final int TILES_X = 10;
    public static final int TILES_Y = 20;

    private Board board = new Board(40, 20, new Level0().getTiles());
    private Player player = new Player(5.0*TILE_SIZE, 5.0*TILE_SIZE, 0, 0, TILE_SIZE, TILE_SIZE);
    private Tile hover = new Tile(0, 0, TILE_SIZE, TILE_SIZE, Action.Type.MOVE_LEFT);
    private Tile underHover;

    public void update(long now) {

        if (now - previousTick < HALF_SEC) {
            //System.out.println("waiting");
            return;
        }

        previousTick = now;

        doCurrentTile();

        if (playerAtEdge()) {
            player.setDx(0);
            player.setDy(0);
        }

       /* COLLISION / BORDERING DETECTION

       for (int c=0; c<board.getHeight(); c++) {
            for (Tile t : board.getTiles()[c]) {
                if (!t.isSolid() && !bordering(player, t)) {

                }
            }

            if sp1.rect.centerx in range(sp2.rect.left, sp2.rect.right) and sp1.rect.top == sp2.rect.bottom:
		if sp1.direction == 'up':
			return True
	elif sp1.rect.centery in range(sp2.rect.top, sp2.rect.bottom) and sp1.rect.right == sp2.rect.left:
		if sp1.direction ==  'right':
			return True
	elif sp1.rect.centerx in range(sp2.rect.left, sp2.rect.right) and sp1.rect.bottom == sp2.rect.top:
		if sp1.direction ==  'down':
			return True
	elif sp1.rect.centery in range(sp2.rect.top, sp2.rect.bottom) and sp1.rect.left == sp2.rect.right:
		if sp1.direction ==  'left':
			return True
	else:
		return False


        }*/

        player.move();

    }

    private void doCurrentTile() {
        switch (board.getTiles()[(int) (player.getX()/TILE_SIZE)][(int) (player.getY()/TILE_SIZE)].getAction()) {
            case MOVE_UP:
                player.setDx(0);
                player.setDy(-1);
                break;
            case MOVE_DOWN:
                player.setDx(0);
                player.setDy(1);
                break;
            case MOVE_LEFT:
                player.setDx(-1);
                player.setDy(0);
                break;
            case MOVE_RIGHT:
                player.setDx(1);
                player.setDy(0);
                break;
            case MOVE_STOP:
                player.setDx(0);
                player.setDy(0);
                break;
            case ROTATE_90:
                turnRight(player);
                break;
            case ROTATE_180:
                turnAround(player);
                break;
            case ROTATE_270:
                turnLeft(player);
                break;
            case GOAL:
                System.out.println("Yay, you made it!");
                player.setDx(0);
                player.setDy(0);
                break;
            default:
                break;
        }
    }

    private void turnRight(AbstractMoveable m) {
        turnLeft(m);
        turnLeft(m);
        turnLeft(m);
    }

    private void turnAround(AbstractMoveable m) {
        turnLeft(m);
        turnLeft(m);
    }

    private void turnLeft(AbstractMoveable m) {
        if (m.getDx() != 0) {
            m.setDy(m.getDx()*(-1));
            m.setDx(0);
        } else {
            m.setDx(m.getDy());
            m.setDy(0);
        }
    }

    private boolean playerAtEdge() {
        if (player.getX() <= 0 ||
            player.getX() + player.getWidth() >= GAME_WIDTH ||
            player.getY() <= 0 ||
            player.getY() + player.getHeight() >= GAME_HEIGHT) {

            return true;
        }

        return false;
    }

    public void nextHover(int n) {
        int currentIndex = -1;

        for (int i=0; i<Action.Type.values().length; i++) {
            if (hover.getAction() == Action.Type.values()[i]) {
                currentIndex = i;
            }
        }

        hover.setAction(Action.getActions()[((currentIndex+n+Action.getActions().length)%Action.getActions().length)]);
    }

    public Tile getUnderHover() {
        return underHover;
    }

    public void setUnderHover(Tile t) {
        underHover = t;
    }

    public Tile getHover() {
        return hover;
    }

    public void setHover(Tile t) {
        hover.setX(t.getX());
        hover.setY(t.getY());
        //hover.setAction(t.getAction());
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }
}
