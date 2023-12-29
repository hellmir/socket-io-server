package socketio.socketioserver.support;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import socketio.socketioserver.controller.SocketController;
import socketio.socketioserver.controller.SocketMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class WebSocketAddMappingSupporter {
    private final ConfigurableListableBeanFactory beanFactory;
    private SocketIOServer socketIOServer;

    private static final Logger log = LoggerFactory.getLogger(WebSocketAddMappingSupporter.class);

    private static final String ADD_LISTENERS_LOG = "Adding listeners for SocketControllers";
    private static final String ADD_LISTENERS_FOR_CONTROLLER_LOG = "Adding event listeners for controller: {}";
    private static final String REGISTERING_LOG = "Registering method {} for endpoint '{}'";
    private static final String ERROR_LOG = "Error invoking method: {}";

    public void addListeners(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
        final List<Class<?>> classes = beanFactory.getBeansWithAnnotation(SocketController.class).values()
                .stream().map(Object::getClass)
                .collect(Collectors.toList());

        log.info(ADD_LISTENERS_LOG);

        for (Class<?> cls : classes) {
            List<Method> methods = findSocketMappingAnnotatedMethods(cls);
            addSocketServerEventListener(cls, methods);
        }
    }

    private void addSocketServerEventListener(Class<?> controller, List<Method> methods) {
        log.info(ADD_LISTENERS_FOR_CONTROLLER_LOG, controller.getName());

        for (Method method : methods) {
            SocketMapping socketMapping = method.getAnnotation(SocketMapping.class);
            String endpoint = socketMapping.endpoint();
            Class<?> dtoClass = socketMapping.requestCls();

            log.info(REGISTERING_LOG, method.getName(), endpoint);

            socketIOServer.addEventListener(endpoint, dtoClass, ((client, data, ackSender) -> {
                try {
                    List<Object> args = new ArrayList<>();
                    for (Class<?> params : method.getParameterTypes()) {
                        if (params.equals(SocketIOServer.class)) args.add(socketIOServer);
                        else if (params.equals(SocketIOClient.class)) args.add(client);
                        else if (params.equals(dtoClass)) args.add(data);
                    }
                    method.invoke(beanFactory.getBean(controller), args.toArray());
                } catch (Exception e) {
                    log.error(ERROR_LOG, method.getName(), e);
                }
            }));
        }
    }

    private List<Method> findSocketMappingAnnotatedMethods(Class<?> cls) {
        return Arrays.stream(cls.getMethods())
                .filter(method -> method.getAnnotation(SocketMapping.class) != null)
                .collect(Collectors.toList());
    }
}
