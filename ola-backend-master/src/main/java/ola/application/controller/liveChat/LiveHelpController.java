package ola.application.controller.liveChat;

import ola.application.dto.liveChat.ChatSessionDTO;
import ola.application.dto.liveChat.MessageDTO;
import ola.application.dto.liveChat.StartChatRequest;
import ola.application.dto.liveChat.SendMessageRequest;
import ola.application.services.liveChat.LiveHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/live-help")
public class LiveHelpController {

    @Autowired
    private LiveHelpService liveHelpService;
//2.
    @GetMapping("/open-sessions")
    public ResponseEntity<List<ChatSessionDTO>> getOpenChatSessions() {
        List<ChatSessionDTO> openSessions = liveHelpService.getOpenChatSessions();
        return ResponseEntity.ok(openSessions);
    }
//3.
    @PostMapping("/accept-session/{sessionId}")
    public ResponseEntity<ChatSessionDTO> acceptChatSession(@PathVariable int sessionId) {
        ChatSessionDTO session = liveHelpService.acceptChatSession(sessionId);
        return ResponseEntity.ok(session);
    }
//1.
    @PostMapping("/start-chat")
    public ResponseEntity<ChatSessionDTO> startChat(@RequestBody StartChatRequest request) {
        ChatSessionDTO session = liveHelpService.startChat(request.getUserId());
        return ResponseEntity.ok(session);
    }

//4.
    @PostMapping("/send-message")
    public ResponseEntity<MessageDTO> sendMessage(@RequestBody SendMessageRequest request) {
        MessageDTO message = liveHelpService.sendMessage(request.getSessionId(), request.getMessage(), request.getSender());
        return ResponseEntity.ok(message);
    }
//5.
    @GetMapping("/chat-history/{sessionId}")
    public ResponseEntity<List<MessageDTO>> getChatHistory(@PathVariable int sessionId) {
        List<MessageDTO> chatHistory = liveHelpService.getChatHistory(sessionId);
        return ResponseEntity.ok(chatHistory);
    }
}
