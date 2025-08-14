package ola.application.dto.liveChat;

import lombok.Data;

@Data
public class SendMessageRequest {
    private int sessionId;
    private String message;
    private String sender;
}