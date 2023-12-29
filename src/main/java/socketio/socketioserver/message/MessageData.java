package socketio.socketioserver.message;

public class MessageData {
    private String inputMessage;
    private boolean isMine;

    public MessageData() {
    }

    public MessageData(String inputMessage, boolean isMine) {
        this.inputMessage = inputMessage;
        this.isMine = isMine;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public boolean isMine() {
        return isMine;
    }
}
