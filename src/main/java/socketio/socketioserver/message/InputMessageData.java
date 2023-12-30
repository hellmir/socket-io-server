package socketio.socketioserver.message;

public class InputMessageData {
    private String inputMessage;
    private String senderStatus;

    public InputMessageData() {
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public String getSenderStatus() {
        return senderStatus;
    }

    public boolean isSender() {
        return senderStatus.equals("true");
    }
}
