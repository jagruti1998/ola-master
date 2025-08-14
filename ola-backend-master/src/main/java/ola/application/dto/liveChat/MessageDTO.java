package ola.application.dto.liveChat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private int messageId;
    private int sessionId;
    private String message;
    private String sender;
    private LocalDateTime timestamp;
}