import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

public class GameGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Game game;
    private boolean running = false;

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

        //System.out.println("render");
        fg.clearRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        /*for (IPositionable d : game.getPositionables()) {
            Image i = Assets.INSTANCE.get(d.getClass());
            System.out.println(i.getUrl());
            fg.drawImage(i, d.getX(), d.getY(), d.getWidth(), d.getHeight());
        }*/

        Tile[][] board =  game.getBoard().getTiles();

        for (int r=0; r<board.length; r++) {
                for (int c=0; c<board[r].length; c++) {
                Tile t = board[r][c];
                fg.setFill(Color.BLACK);
                fg.fillRect(t.getX(), t.getY(), t.getWidth(), t.getHeight());
                fg.setFill(t.getColor());
                fg.fillRect(t.getX() + 3, t.getY() + 3, t.getWidth() - 6, t.getHeight() - 6);
            }
        }

        fg.setFill(Color.ORANGE);
        fg.fillRect(game.getPlayer().getX() + 3, game.getPlayer().getY() + 3, game.getPlayer().getWidth() - 6, game.getPlayer().getHeight() - 6);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

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

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("slide_game");

        primaryStage.show();
    }

    private void newGame() {
        game = new Game();
        renderBackground();
        timer.start();
        running = true;
    }

    private void renderBackground() {
        bg.drawImage(Assets.INSTANCE.background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
    }
}
