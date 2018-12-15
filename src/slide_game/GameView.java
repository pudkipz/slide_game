package slide_game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameView extends BorderPane {
    private Game game;

    private Pane pane;
    private Group tiles;
    private Group labels;

    GameMenu menuBar;

    public GameView(Game game) {
        this.game = game;
        initComponents();
    }

    private void initComponents() {
        setPrefSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);

        pane = new Pane();
        setCenter(pane);
    }

    public void render() {
        renderTiles();
    }

    void newGame() {
        labels = new Group();
        tiles = new Group();

        pane.getChildren().add(tiles);
        pane.getChildren().add(labels);

        Tile[][] board = game.getBoard().getTiles();
        for (int r=0; r<board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                Tile t = board[r][c];
                t.setStroke(Color.BLACK);
                t.setFill(t.getColor());
                tiles.getChildren().add(t);

                renderTiles();
            }
        }

        tiles.getChildren().add(game.getPlayer());
        tiles.getChildren().add(game.getHover());
    }

    private void renderTiles() {
        labels.getChildren().clear();

        Tile[][] board =  game.getBoard().getTiles();

        for (int r=0; r<board.length; r++) {
            for (int c=0; c<board[r].length; c++) {
                Tile t = board[r][c];
                    renderTile(t);
                }
            }

        renderTile(game.getHover());

        Tile player = game.getPlayer();
        Text playerText = new Text(player.getX() + 4, player.getY() + 20, "PLAYER");
        playerText.setFont(new Font(10));
        playerText.setFill(Color.WHITE);
        labels.getChildren().add(playerText);
    }

    private void renderTile(Tile t) {
        t.setStroke(Color.BLACK);
        t.setFill(t.getColor());

        Text text = new Text(t.getX() + 6, t.getY() + 20, t.getActionName());
        text.setFont(new Font(10));
        text.setFill(Color.WHITE);

        labels.getChildren().add(text);
    }

    Pane getPane() {
        return pane;
    }
}
