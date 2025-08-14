package ola.application.dto.liveChat;

import lombok.Data;
import ola.application.entity.liveChat.ChatSessionStatus;

@Data
public class ChatSessionDTO {
    private int sessionId;
    private int userId;
    private ChatSessionStatus status;
}