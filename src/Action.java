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
        NONE
    }

    public final Action.Type type;

    public Action(Action.Type type) {
        this.type = type;
    }
}
