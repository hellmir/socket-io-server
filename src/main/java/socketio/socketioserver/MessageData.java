package socketio.socketioserver;

public class MessageData {
    private String inputMessage;
    private Boolean isMine;

    public MessageData() {
    }

    public MessageData(String inputMessage, Boolean isMine) {
        this.inputMessage = inputMessage;
        this.isMine = isMine;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public Boolean isMine() {
        return isMine;
    }
}
