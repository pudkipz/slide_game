import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

public class GameMenu extends MenuBar {
    public GameMenu(EventHandler<ActionEvent> menuHandler) {
        Menu menuGame = createMenuGame();

        menuGame.getItems().forEach(item -> item.setOnAction(menuHandler));

        this.getMenus().addAll(menuGame);
    }

    private Menu createMenuGame() {
        Menu menuGame = new Menu("Game");

        MenuItem newItem = new MenuItem("New Game");
        MenuItem exitItem = new MenuItem("Exit");

        menuGame.getItems().addAll(newItem, exitItem);
        return menuGame;
    }


}
