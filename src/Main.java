/*
        TODO

        -   Tile under player?
        -   Tile under goal can be changed - not good.
        -   collision/bordering detection in Game.java, add walls.
 */

import javafx.application.Platform;
import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
        Platform.startup(() -> {
            Game game = new Game();
            Stage stage = new Stage();
            GameView view = new GameView(game);
            GameController controller = new GameController(view, game);
            view.start(stage);
        });
    }
}
