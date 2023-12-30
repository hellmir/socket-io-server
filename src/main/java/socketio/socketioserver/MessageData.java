package socketio.socketioserver;

public class MessageData {
    private boolean isMine;
    private String inputMessage;

    public MessageData() {
    }

    public boolean isMine() {
        return isMine;
    }

    public String getInputMessage() {
        return inputMessage;
    }
}
