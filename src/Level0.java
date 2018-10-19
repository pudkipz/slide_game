public class Level0 {
    private final int width = Game.TILES_X;
    private final int height = Game.TILES_Y;
    private final double tileSize = Game.TILE_SIZE;
    private Tile[][] tiles = newBoard();

    private Tile[][] newBoard() {
        tiles = new Tile[height][width];
        for (int r=0; r<height; r++) {
            for (int c = 0; c < width; c++) {
                tiles[r][c] = new Tile(r * tileSize, c * tileSize, tileSize, tileSize, Action.Type.NONE);
            }
        }

        tiles[5][5].setAction(Action.Type.MOVE_RIGHT);
        tiles[7][5].setAction(Action.Type.MOVE_UP);

        return tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
