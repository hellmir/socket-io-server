package socketio.socketioserver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageData {
    private String inputMessage;

    @JsonProperty(value = "isMine")
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
