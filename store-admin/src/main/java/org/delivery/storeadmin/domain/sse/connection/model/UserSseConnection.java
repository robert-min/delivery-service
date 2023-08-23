package org.delivery.storeadmin.domain.sse.connection.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@ToString
@EqualsAndHashCode
public class UserSseConnection {

    private final String uniqueKey;

    private final SseEmitter sseEmitter;

    private final ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs;

    private final ObjectMapper objectMapper;

    private UserSseConnection(
            String uniqueKey,
            ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs,
            ObjectMapper objectMapper
    ) {
        // key init
        this.uniqueKey = uniqueKey;

        // sse init
        this.sseEmitter = new SseEmitter(60 * 1000L);

        // on completion
        this.connectionPoolIfs = connectionPoolIfs;

        // object mapper init
        this.objectMapper = objectMapper;

        this.sseEmitter.onCompletion(() -> {
            // connection pool remove
            this.connectionPoolIfs.onCompletionCallback(this);
        });

        // on timeout
        this.sseEmitter.onTimeout(() -> {
            this.sseEmitter.complete();
        });


        // opopen message
        sendMessage("onopen", "connect");
    }

    public static UserSseConnection connect(
            String uniqueKey,
            ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs,
            ObjectMapper objectMapper
    ) {
        return new UserSseConnection(uniqueKey, connectionPoolIfs, objectMapper);
    }


    public void sendMessage(String eventName, Object data) {
        try {
            var json = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event()
                    .name(eventName)
                    .data(json)
                    ;
            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }

    }

    public void sendMessage(Object data) {


        try {
            var json = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event()
                    .data(json)
                    ;
            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

}
