public class Action {
    public enum Type {
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN,
        MOVE_STOP,
        ROTATE_90,
        ROTATE_180,
        ROTATE_270,

        GOAL,
        NONE,
        PLAYER
    }

    public final Action.Type type;

    public Action(Action.Type type) {
        this.type = type;
    }

    public static Action.Type[] getActions () {
        return new Action.Type[]{
                Type.MOVE_LEFT,
                Type.MOVE_RIGHT,
                Type.MOVE_UP,
                Type.MOVE_DOWN,
                Type.MOVE_STOP,
                Type.ROTATE_90,
                Type.ROTATE_180,
                Type.ROTATE_270,};
    }
}
