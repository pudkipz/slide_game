package slide_game.action_states;

import javafx.scene.paint.Color;
import slide_game.AbstractMoveable;
import slide_game.Action;

public class Goal extends ActionState {

    public Goal() {
        action = Action.Type.GOAL;
        color = Color.WHITE;
        actionName = "GOAL";
    }

    public void doAction(AbstractMoveable m) {
        System.out.println("GOAL!");
    }

    public ActionState getNew() {
        return new Goal();
    }
}
