import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView extends Application {
    private Game game;
    private boolean running = false;    // this is if new game has been created.
    private boolean playing = false;    // this is if simulation is playing or is paused.

    private Scene scene;
    private BorderPane root;
    private Pane pane;
    private Group tiles;
    private Group labels;

    private AnimationTimer timer;
    private GameMenu menuBar;

    public GameView(Game game) {
        this.game = game;
        initComponents();
    }

    public void main(String[] args) {
        launch(args);
    }

    private void initComponents() {
        root = new BorderPane();
        root.setPrefSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);

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
    }

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
            case "Exit":
            System.exit(0);
            break;
            default:
        }
    }

    private void render() {
        if (!running) {
            newGame();
        }

        renderTiles();
    }

    @Override
    public void start(Stage primaryStage) {

        menuBar = new GameMenu(this::handleMenu);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        primaryStage.setScene(scene);
        primaryStage.setTitle("slide_game");

        primaryStage.show();
    }

    private void newGame() {
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

        timer.start();
        running = true;
        playing = false;
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

        if (t.getAction() != Action.Type.NONE) {
            Text text = new Text(t.getX() + 6, t.getY() + 20, t.getActionName());
            text.setFont(new Font(10));
            text.setFill(Color.WHITE);

            labels.getChildren().add(text);
        }
    }

    Pane getPane() {
        return pane;
    }
}
