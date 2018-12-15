package slide_game;

import slide_game.action_states.*;

public class Level0 {
    private final int width = Game.TILES_X;
    private final int height = Game.TILES_Y;
    private final double tileSize = Game.TILE_SIZE;
    private Tile[][] tiles = newBoard();

    private Tile[][] newBoard() {
        tiles = new Tile[height][width];
        for (int r=0; r<tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                tiles[r][c] = new Tile(c * tileSize, r * tileSize, new None());
            }
        }

        //tiles[5][5].setAction(new TurnRight());
        tiles[5][7].setAction(new TurnLeft());
        tiles[8][10].setAction(new Wall());
        tiles[1][13].setAction(new Goal());

        return tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
