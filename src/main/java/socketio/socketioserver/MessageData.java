package socketio.socketioserver;

public class MessageData {
    private String inputMessage;
    private boolean isMine;

    public MessageData() {
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public boolean isMine() {
        return isMine;
    }
}
