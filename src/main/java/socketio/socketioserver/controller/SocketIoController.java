package socketio.socketioserver.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socketio.socketioserver.message.MessageData;

@SocketController
public class SocketIoController {
    private static final Logger log = LoggerFactory.getLogger(SocketIoController.class);

    @SocketMapping(endpoint = "send_message", requestCls = MessageData.class)
    public void onSendMessage(SocketIOClient client, MessageData data) {
        log.info("Received message from client [{}]: {}", client.getSessionId(), data.getInputMessage());
        // 추가적인 처리 로직
    }

    @SocketMapping(endpoint = "receive_message", requestCls = MessageData.class)
    public void onReceiveMessage(SocketIOClient client, MessageData data, SocketIOServer server) {
        log.info("Broadcasting message: {}", data.getInputMessage());
        server.getBroadcastOperations().sendEvent("receive_message", data);
    }
}
