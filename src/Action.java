//Made action abstract, since the only objects that exist and interact are tiles.

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
        NONE,
        PLAYER
    }


    private static final Action.Type[] actions= {
            Type.MOVE_LEFT,
            Type.MOVE_RIGHT,
            Type.MOVE_UP,
            Type.MOVE_DOWN,
            Type.MOVE_STOP,
            Type.ROTATE_90,
            Type.ROTATE_180,
            Type.ROTATE_270,
            Type.JUMP};

    //Could maybe create two enums, one for action tiles and one for player. Also, shouldn't you be able to place
    //player and goal?
    public static Action.Type[] getActions () {
        return actions;
    }
}
