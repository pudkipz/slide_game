package slide_game.action_states;

import javafx.scene.paint.Color;
import slide_game.AbstractMoveable;
import slide_game.Action;

public class None extends ActionState {

    public None() {
        action = Action.Type.NONE;
        color = Color.CADETBLUE;
        actionName = "";
        replaceable = true;
    }

    public void doAction(AbstractMoveable m) {

    }

    public ActionState getNew() {
        return new None();
    }
}
