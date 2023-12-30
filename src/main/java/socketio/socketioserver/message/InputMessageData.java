package socketio.socketioserver.message;

public class InputMessageData {
    private String inputMessage;
    private String isMine;

    public InputMessageData() {
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public String getIsMine() {
        return isMine;
    }

    public boolean isSender() {
        return isMine.equals("true");
    }
}
