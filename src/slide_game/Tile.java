package slide_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import slide_game.action_states.ActionState;

public class Tile extends Rectangle implements IPositionable {
    private ActionState actionState;

    public Tile(double x, double y, double width, double height, ActionState as) {
        super(x, y, width, height);
        actionState = as;
    }

    public Tile(double x, double y, ActionState as) {
        this(x, y, 40, 40, as);
    }

    public ActionState getAction() {
        return actionState;
    }

    public Color getTileColor(Action.Type action) {
        return actionState.getColor();

        /*switch (action) {
            case NONE:
                return Color.CADETBLUE;
            case MOVE_LEFT:
                return Color.ROSYBROWN;
            case MOVE_RIGHT:
                return Color.INDIANRED;
            case MOVE_UP:
                return Color.PLUM;
            case MOVE_DOWN:
                return Color.GOLD;
            case MOVE_STOP:
                return Color.SALMON;
            case ROTATE_90:
                return Color.BURLYWOOD;
            case ROTATE_180:
                return Color.SADDLEBROWN;
            case ROTATE_270:
                return Color.FIREBRICK;
            case JUMP:
                return Color.GREEN;
            case GOAL:
                return Color.WHITE;
            default:
                return null;
        }*/
    }

    public void setAction(ActionState actionState) {
        this.actionState = actionState;
    }

    public Color getColor() {
        return actionState.getColor();
    }

    public String getActionName() {
        return actionState.getActionName();
    }

    public ActionState getNew() {
        return actionState.getNew();
    }
}
