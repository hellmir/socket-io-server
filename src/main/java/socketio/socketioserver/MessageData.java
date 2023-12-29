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

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
