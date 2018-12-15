package slide_game;

import slide_game.action_states.ActionState;
import slide_game.action_states.None;
import slide_game.action_states.TurnLeft;

public class Game {
    public static final double GAME_WIDTH = 800;
    public static final double GAME_HEIGHT = 400;

    public static final long ONE_SEC = 1_000_000_000;
    public static final long HALF_SEC = 500_000_000;
    public static final long TENTH_SEC = 100_000_000;
    public long previousTick;

    private boolean running = false;    // this is if new game has been created.
    private boolean playing = false;    // this is if simulation is playing or is paused.

    public static final double TILE_SIZE = 40.0;
    public static final int TILES_X = 20;
    public static final int TILES_Y = 10;

    private Board board = new Board(40, 40, new Level0().getTiles());
    private Player player = new Player(5.0*TILE_SIZE, 5.0*TILE_SIZE, 1, 0, TILE_SIZE, TILE_SIZE);
    private Tile hover = new Tile(0, 0, new TurnLeft());

    public void update(long now) {

        if (now - previousTick < HALF_SEC) {
            return;
        }

        previousTick = now;

        doCurrentTile();

        if (playerAtEdge()) {
            player.setDx(0);
            player.setDy(0);
        }

        player.move();
    }

    private void doCurrentTile() {
        Tile currentTile = getTileAt(player.getX(), player.getY());
        currentTile.getAction().doAction(player);
    }

    private boolean playerAtEdge() {
        return (player.getX() <= 0 ||
            player.getX() + player.getWidth() >= GAME_WIDTH ||
            player.getY() <= 0 ||
            player.getY() + player.getHeight() >= GAME_HEIGHT);
    }

    public void newGame() {
        //board = new Board(40, 40, new Level0().getTiles());
        //player = new Player(5.0*TILE_SIZE, 5.0*TILE_SIZE, 1, 0, TILE_SIZE, TILE_SIZE);

        playing = false;
        running = true;
    }

    private Tile getTileAt(double x, double y) {
        return board.getTiles()[(int) (y/TILE_SIZE)][(int) (x/TILE_SIZE)];
    }

    public void setTileToHover(boolean clear) {
        Tile t = getTileAt(hover.getX(), hover.getY());
        if (!clear) {
            setTileToHover();
        } else {
            t.setAction(new None());
        }
    }

    public void setTileToHover() {
        Tile t = getTileAt(hover.getX(), hover.getY());
        t.setAction(hover.getNew());

    }

    public void nextHover(int n) {
        ActionState[] actions = Action.getActions();

        for (int i=0; i<Action.Type.values().length; i++) {
            if (hover.getActionType() == actions[i].getActionType()) {
                hover.setAction(actions[(i + n + actions.length) % actions.length].getNew());
                return;
            }
        }
    }

    public Tile getHover() {
        return hover;
    }

    public void setHoverPos(Tile t) {
        hover.setX(t.getX());
        hover.setY(t.getY());
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void startSim() {
        playing = true;
    }

    public void stopSim() {
        playing = false;
    }
}
