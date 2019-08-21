package by.epam.pialetskialiaksei.util;

public class Response {
    private final String source;
    private final ActionType actionType;

    public Response(String source, ActionType actionType) {
        this.source = source;
        this.actionType = actionType;
    }

    public String getSource() {
        return source;
    }

    public ActionType getActionType() {
        return actionType;
    }

    @Override
    public String toString() {
        return "Response{" +
                "source='" + source + '\'' +
                ", actionType=" + actionType +
                '}';
    }
}
