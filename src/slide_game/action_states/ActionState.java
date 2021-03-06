package slide_game.action_states;


import javafx.scene.paint.Color;
import slide_game.AbstractMoveable;
import slide_game.Action;

public abstract class ActionState {
    protected Action.Type action;
    protected Color color;
    protected String actionName;
    protected boolean replaceable;

    public abstract void doAction(AbstractMoveable m);

    public abstract ActionState getNew();

    public Action.Type getActionType() {
        return action;
    }

    public Color getColor() {
        return color;
    }

    public String getActionName() {
        return actionName;
    }

    public boolean isReplaceable() {
        return replaceable;
    }
}
