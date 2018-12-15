package slide_game;/*
        TODO

        -   slide_game.Tile under player?
        -   slide_game.Tile under goal can be changed - not good.
        -   New Game should reset the board.
        -   AbstractMoveable shouldn't have to be a Tile - what should Player be?
        -   Create a factory, so as to reduce the number of imports.
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        GameView view = new GameView(game);
        Scene scene = new Scene(view);
        GameController controller = new GameController(scene, game);

        GameMenu menuBar = new GameMenu(game, view);
        view.menuBar = menuBar;

        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        view.setTop(menuBar);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (game.isRunning()) {
                    view.render();
                    if (game.isPlaying()) {
                        game.update(now);
                    }
                }
            }
        };

        primaryStage.setScene(scene);
        primaryStage.setTitle("slide_game");

        primaryStage.show();
        timer.start();
    }
}
