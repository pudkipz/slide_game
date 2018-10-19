public class Action {
    public enum Type {
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN,
        MOVE_STOP,
        NONE
    }

    public final Action.Type type;

    public Action(Action.Type type) {
        this.type = type;
    }
}
