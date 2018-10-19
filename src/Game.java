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

    public static final int TILE_SIZE = 40;
    public static final int TILES_X = 10;
    public static final int TILES_Y = 20;

    private Board board = new Board(40, 20, new Level0().getTiles());
    private Player player = new Player(5*TILE_SIZE, 5*TILE_SIZE, 0, 0, TILE_SIZE, TILE_SIZE);

    public void update(long now) {

        if (now - previousTick < HALF_SEC) {
            //System.out.println("waiting");
            return;
        }

        previousTick = now;

        switch (board.getTiles()[player.getX()/TILE_SIZE][player.getY()/TILE_SIZE].getAction()) {
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
            default:
                break;
        }

        if (playerAtEdge()) {
            player.setDx(0);
            player.setDy(0);
        }

        player.move();

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

    public List<IPositionable> getPositionables() {
        List<IPositionable> ps = new ArrayList<>();
        ps.add(player);

        for (int r=0; r<TILES_Y; r++) {
            for (int c=0; c<TILES_X; c++) {
                ps.add(board.getTiles()[c][r]);
            }
        }

        //System.out.println(ps.size());
        return ps;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }
}
