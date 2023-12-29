package socketio.socketioserver;

public class MessageData {
    private String inputMessage;
    private String isMine;

    public MessageData() {
    }

    public MessageData(String inputMessage, String isMine) {
        this.inputMessage = inputMessage;
        this.isMine = isMine;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public String isMine() {
        return isMine;
    }
}
