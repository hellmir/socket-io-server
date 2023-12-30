package socketio.socketioserver.message;

public class OutputMessageData {
    private String outputMessage;
    private boolean isMine;

    public OutputMessageData(String outputMessage, boolean isMine) {
        this.outputMessage = outputMessage;
        this.isMine = isMine;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public boolean isMine() {
        return isMine;
    }
}
