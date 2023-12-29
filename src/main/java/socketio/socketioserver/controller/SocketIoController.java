package socketio.socketioserver.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socketio.socketioserver.message.MessageData;

@SocketController
public class SocketIoController {
    private static final Logger log = LoggerFactory.getLogger(SocketIoController.class);

    private static final String SEND_MESSAGE = "send_message";
    private static final String RECEIVE_MESSAGE = "receive_message";

    private static final String RECEIVED_MESSAGE_LOG = "Received message from client [{}]: {}";
    private static final String BROADCASTING_LOG = "Broadcasting message: {}";

    @SocketMapping(endpoint = SEND_MESSAGE, requestCls = MessageData.class)
    public void onSendMessage(SocketIOClient client, MessageData data) {
        log.info(RECEIVED_MESSAGE_LOG, client.getSessionId(), data.getInputMessage());
    }

    @SocketMapping(endpoint = RECEIVE_MESSAGE, requestCls = MessageData.class)
    public void onReceiveMessage(SocketIOClient client, MessageData data, SocketIOServer server) {
        log.info(BROADCASTING_LOG, data.getInputMessage());
        server.getBroadcastOperations().sendEvent(RECEIVE_MESSAGE, data);
    }
}
