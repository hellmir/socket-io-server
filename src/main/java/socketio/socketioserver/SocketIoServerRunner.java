package socketio.socketioserver;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SocketIoServerRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(SocketIoServerRunner.class);

    @Value("${server.socket-io.port}")
    private int socketIoPort;

    private static final String SOCKET_IO_PORT_LOG = "Socket IO Server Port: ";
    private static final String CONNECTED_LOG = "Client Connected: ";
    private static final String DISCONNECTED_LOG = "Client Disconnected: ";
    private static final String SEND_MESSAGE = "send_message";
    private static final String RECEIVE_MESSAGE = "receive_message";
    private static final String RECEIVED_MESSAGE = "Received message: ";

    @Override
    public void run(String... args) throws Exception {
        log.info(SOCKET_IO_PORT_LOG + socketIoPort);

        Configuration config = new Configuration();
        config.setPort(socketIoPort);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                log.info(CONNECTED_LOG + client.getSessionId());
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                log.info(DISCONNECTED_LOG + client.getSessionId());
            }
        });

        server.addEventListener(SEND_MESSAGE, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String message, AckRequest ackRequest) {
                log.info(RECEIVED_MESSAGE + message);
                server.getBroadcastOperations().sendEvent(RECEIVE_MESSAGE, message);
            }
        });

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop();
        }));
    }
}
