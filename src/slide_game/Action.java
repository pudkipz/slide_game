package slide_game;//Made action abstract, since the only objects that exist and interact are tiles.

import slide_game.action_states.*;

public abstract class Action {
    public enum Type {
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN,
        MOVE_STOP,
        ROTATE_90,
        ROTATE_180,
        ROTATE_270,
        JUMP,

        GOAL,
        WALL,
        NONE,
        PLAYER
    }


    /*private static final Action.Type[] actions= {
            Type.MOVE_LEFT,
            Type.MOVE_RIGHT,
            Type.MOVE_UP,
            Type.MOVE_DOWN,
            Type.MOVE_STOP,
            Type.ROTATE_90,
            Type.ROTATE_180,
            Type.ROTATE_270,
            Type.JUMP};*/

    private static final ActionState[] actions = {
            new Stop(),
            new TurnRight(),
            new TurnAround(),
            new TurnLeft(),
            new Jump()
    };

    public static ActionState[] getActions () {
        return actions;
    }
}
