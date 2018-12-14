package slide_game.action_states;

import javafx.scene.paint.Color;
import slide_game.AbstractMoveable;
import slide_game.Action;

public class Jump extends ActionState {

    public Jump() {
        action = Action.Type.JUMP;
        color = Color.GREEN;
        actionName = "JUMP";
    }

    public void doAction(AbstractMoveable m) {
        m.move();
    }

    public ActionState getNew() {
        return new Jump();
    }
}
