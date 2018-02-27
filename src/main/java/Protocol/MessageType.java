package Protocol;

public enum MessageType {
    MESSAGE_TYPE_TEXT("01"),
    MOVE_GAME("02"),
    SURRENDER("03"),
    RESTART_GAME("04");

    private String type;
    MessageType(String s) {
        this.type = s;
    }
}
