/*
        TODO

        -   Program should render when New Game is pressed, but shouldn't run until Run is pressed.
        -   When hovering over a tile, it should light up with the selected color.
        -   Color/action is selected by scrolling the mouse wheel.
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Game game;
    private boolean running = false;

    private Scene scene;
    private BorderPane root;
    private Group tiles;
    private Group labels;

    private AnimationTimer timer;
    private GraphicsContext fg;
    private GraphicsContext bg;
    private GameMenu menuBar;

    private void handleMenu(ActionEvent e) {
        MenuItem mi = (MenuItem) e.getSource();
        switch (mi.getText()) {
            case "New Game":
                newGame();
                break;
            default: // Nothing
        }
    }

    private void render() {
        if (!running) {
            System.out.println("hej");
            newGame();
        }

        renderTiles();
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        menuBar = new GameMenu(this::handleMenu);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        Canvas backGround = new Canvas(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        bg = backGround.getGraphicsContext2D();
        Canvas foreGround = new Canvas(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        fg = foreGround.getGraphicsContext2D();

        Pane pane = new Pane(backGround, foreGround);
        root.setCenter(pane);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.update(now);
                render();
            }
        };

        bg.drawImage(Assets.INSTANCE.background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("slide_game");

        primaryStage.show();
    }

    private void newGame() {
        game = new Game();
        renderBackground();

        labels = new Group();
        tiles = new Group();

        root.getChildren().add(tiles);
        root.getChildren().add(game.getPlayer());
        root.getChildren().add(labels);

        timer.start();
        running = true;
    }

    private void renderTiles() {
        tiles.getChildren().clear();
        labels.getChildren().clear();

        Tile[][] board =  game.getBoard().getTiles();

        for (int r=0; r<board.length; r++) {
            for (int c=0; c<board[r].length; c++) {
                Tile t = board[r][c];

                t.setStroke(Color.BLACK);
                t.setFill(t.getColor());
                tiles.getChildren().add(t);

                if (t.getAction() != Action.Type.NONE) {
                    Text text = new Text(t.getX() + 6, t.getY() + 20, t.getActionName());
                    text.setFont(new Font(10));
                    text.setFill(Color.WHITE);

                    labels.getChildren().add(text);
                }
            }
        }

        Text text = new Text(game.getPlayer().getX() + 4, game.getPlayer().getY() + 20, "PLAYER");
        text.setFont(new Font(10));
        text.setFill(Color.WHITE);
        labels.getChildren().add(text);
    }

    private void renderBackground() {
        bg.drawImage(Assets.INSTANCE.background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
    }
}
