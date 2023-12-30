package socketio.socketioserver;

import org.springframework.stereotype.Service;
import socketio.socketioserver.message.InputMessageData;
import socketio.socketioserver.message.OutputMessageData;

@Service
public class ChatService {
    public OutputMessageData convertToOutputMessage(InputMessageData inputMessageData) {
        return new OutputMessageData(inputMessageData.getInputMessage(), inputMessageData.isSender());
    }
}
