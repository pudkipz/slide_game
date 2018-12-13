import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
// import javafx.event.ActionEvent;

public class GameController {
    private Game game;

    GameController(GameView view, Game game) {
        this.game = game;
        initComponents(view);
    }

    private void initComponents(GameView view) {
        System.out.println(view.getPane());
        view.getPane().setOnMouseClicked(this::handleMouseClicked);
        view.getPane().setOnScroll(this::handleScroll);

        Tile[][] board = game.getBoard().getTiles();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                Tile t = board[r][c];

                t.setOnMouseEntered(this::handleMouseEntered);
            }
        }
    }

    private void handleScroll(ScrollEvent event) {
        if (event.getDeltaY() > 0) {
            game.nextHover(1);
        } else {
            game.nextHover(-1);
        }
    }

    private void handleMouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            game.setTileToHover();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            game.setTileToHover(true);
        }
    }

    private void handleMouseEntered(MouseEvent event) {
        Tile t = (Tile) event.getSource();
        game.setHoverPos(t);
    }
}
