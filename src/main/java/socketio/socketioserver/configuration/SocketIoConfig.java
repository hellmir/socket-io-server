package socketio.socketioserver.configuration;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration

public class SocketIoConfig {
    @Value("${server.socket-io.port}")
    private int serverPort;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setPort(serverPort);
        config.setOrigin("*");

        return new SocketIOServer(config);
    }
}
