package socketio.socketioserver.configuration;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration

public class SocketIoConfig {
    @Value("${server.socket-io.port}")
    private int serverPort;

    private final Logger log = LoggerFactory.getLogger(SocketIoConfig.class);

    private static final String WILD_CARD = "*";
    private static final String CONNECTED_LOG = "Client Connected: ";
    private static final String DISCONNECTED_LOG = "Client Disconnected: ";

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setPort(serverPort);
        config.setOrigin(WILD_CARD);

        SocketIOServer server = new SocketIOServer(config);

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

        return server;
    }
}
