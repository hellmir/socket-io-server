package socketio.socketioserver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageData {
    private String inputMessage;
    private boolean isMine;
}
