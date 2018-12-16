package slide_game;

import javafx.scene.Scene;
import javafx.scene.input.*;

public class GameController {
    private Game game;

    GameController(Scene scene, Game game) {
        this.game = game;
        initComponents(scene);
    }

    private void initComponents(Scene scene) {
        scene.setOnMouseClicked(this::handleMouseClicked);
        scene.setOnScroll(this::handleScroll);
        scene.setOnKeyPressed(this::handleKeyPressed);

        Tile[][] board = game.getBoard().getTiles();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                Tile t = board[r][c];

                if (t.isReplaceable()) {
                    t.setOnMouseEntered(this::handleMouseEntered);
                }
            }
        }
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            game.nextHover(1);
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
