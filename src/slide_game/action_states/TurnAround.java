package slide_game.action_states;

import javafx.scene.paint.Color;
import slide_game.AbstractMoveable;
import slide_game.Action;

public class TurnAround extends ActionState {

    public TurnAround() {
        action = Action.Type.ROTATE_180;
        color = Color.SADDLEBROWN;
        actionName = "TURN\nAROUND";
    }

    public void doAction(AbstractMoveable m) {
        m.turnAround();
    }

    public ActionState getNew() {
        return new TurnAround();
    }
}
