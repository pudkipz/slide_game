package slide_game.action_states;

import javafx.scene.paint.Color;
import slide_game.AbstractMoveable;
import slide_game.Action;

public class Stop extends ActionState {

    public Stop() {
        action = Action.Type.MOVE_STOP;
        color = Color.SALMON;
        actionName = "STOP";
    }

    public void doAction(AbstractMoveable m) {
        m.stop();
    }

    public ActionState getNew() {
        return new Stop();
    }
}
