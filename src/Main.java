/*
        TODO

        -   Tile under player?
        -   Tile under goal can be changed - not good.
        -   collision/bordering detection in Game.java, add walls.
        -   Better system for different tiles (states?)
        -   hiya
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
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
        GameController controller = new GameController(view, game);
        Scene scene = new Scene(view);

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
