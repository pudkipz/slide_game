package slide_game.action_states;

import javafx.scene.paint.Color;
import slide_game.AbstractMoveable;
import slide_game.Action;

public class Wall extends ActionState {

    public Wall() {
        action = Action.Type.WALL;
        color = Color.BLACK;
        actionName = "";
    }

    public void doAction(AbstractMoveable m) {
        m.turnAround();
        m.move();
        m.stop();
    }

    public ActionState getNew() {
        return new Wall();
    }
}
