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
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
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
    private boolean running = false;    // this is if new game has been created.
    private boolean playing = false;    // this is if simulation is playing or is paused.

    private Scene scene;
    private BorderPane root;
    private Pane pane;
    private Group tiles;
    private Group labels;

    private AnimationTimer timer;
    private GraphicsContext bg;
    private GameMenu menuBar;

    private void handleMenu(ActionEvent e) {
        MenuItem mi = (MenuItem) e.getSource();
        switch (mi.getText()) {
            case "New Game":
                newGame();
                break;
            case "Play":
                playing = true;
                break;
            case "Stop":
                playing = false;
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

    private void handleScroll(ScrollEvent event) {
        if (event.getDeltaY() > 0) {
            game.nextHover(1);
        } else {
            game.nextHover(-1);
        }
    }

    private void handleMouseClicked(MouseEvent event) {
        Tile t = (Tile) event.getSource();

        if (event.getButton() == MouseButton.PRIMARY) {
            game.getUnderHover().setAction(t.getAction());
        } else if (event.getButton() == MouseButton.SECONDARY) {
            game.getUnderHover().setAction(Action.Type.NONE);
        }
    }

    private void handleMouseEntered(MouseEvent event) {
        Tile t = (Tile) event.getSource();
        game.setUnderHover(t);
        game.setHover(t);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        root.setPrefSize(game.GAME_WIDTH, game.GAME_HEIGHT);

        menuBar = new GameMenu(this::handleMenu);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        pane = new Pane();
        root.setCenter(pane);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (playing) { game.update(now); }
                render();
            }
        };

        scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("slide_game");

        primaryStage.show();
    }

    private void newGame() {
        game = new Game();

        labels = new Group();
        tiles = new Group();

        pane.getChildren().add(tiles);
        pane.getChildren().add(game.getPlayer());
        pane.getChildren().add(labels);
        pane.getChildren().add(game.getHover());
        game.getHover().setOnMouseClicked(this::handleMouseClicked);
        game.getHover().setOnScroll(this::handleScroll);

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
                //t.setOnMouseClicked(this::handleMouseClicked);
                t.setOnMouseEntered(this::handleMouseEntered);

                if (t.getAction() != Action.Type.NONE) {
                    Text text = new Text(t.getX() + 6, t.getY() + 20, t.getActionName());
                    text.setFont(new Font(10));
                    text.setFill(Color.WHITE);

                    labels.getChildren().add(text);
                }
            }
        }

        game.getHover().setFill(game.getHover().getColor());

        Text text = new Text(game.getPlayer().getX() + 4, game.getPlayer().getY() + 20, "PLAYER");
        text.setFont(new Font(10));
        text.setFill(Color.WHITE);
        labels.getChildren().add(text);

        //game.getPlayer().toFront();
        //text.toFront();
    }
}
