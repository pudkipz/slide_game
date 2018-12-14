package slide_game.action_states;

import javafx.scene.paint.Color;
import slide_game.AbstractMoveable;
import slide_game.Action;

public class TurnRight extends ActionState {

    public TurnRight() {
        action = Action.Type.ROTATE_90;
        color = Color.INDIANRED;
        actionName = "TURN\nRight";
    }

    public void doAction(AbstractMoveable m) {
        m.turnRight();
    }

    public ActionState getNew() {
        return new TurnRight();
    }
}
