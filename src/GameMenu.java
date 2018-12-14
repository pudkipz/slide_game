import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

public class GameMenu extends MenuBar {
    Game game;
    GameView view;

    public GameMenu(Game game, GameView view) {
        this.game = game;
        this.view = view;

        Menu menuGame = createMenuGame();

        menuGame.getItems().forEach(item -> item.setOnAction(this::handleMenu));

        this.getMenus().addAll(menuGame);
    }

    private Menu createMenuGame() {
        Menu menuGame = new Menu("Game");

        MenuItem newItem = new MenuItem("New Game");
        MenuItem playItem = new MenuItem("Play");
        MenuItem stopItem = new MenuItem("Stop");
        MenuItem exitItem = new MenuItem("Exit");

        menuGame.getItems().addAll(newItem, playItem, stopItem, exitItem);
        return menuGame;
    }

    private void handleMenu(ActionEvent e) {
        MenuItem mi = (MenuItem) e.getSource();
        switch (mi.getText()) {
            case "New Game":
                view.newGame();
                game.newGame();
                break;
            case "Play":
                game.startSim();
                break;
            case "Stop":
                game.stopSim();
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
        }
    }
}
