package slide_game.action_states;

import javafx.scene.paint.Color;
import slide_game.AbstractMoveable;
import slide_game.Action;

public class TurnLeft extends ActionState {

    public TurnLeft() {
        action = Action.Type.ROTATE_270;
        color = Color.ROSYBROWN;
        actionName = "TURN\nLEFT";
        replaceable = true;
    }

    public void doAction(AbstractMoveable m) {
        m.turnLeft();
    }

    public ActionState getNew() {
        return new TurnLeft();
    }
}
