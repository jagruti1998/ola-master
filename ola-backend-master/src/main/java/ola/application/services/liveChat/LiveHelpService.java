package ola.application.services.liveChat;

import ola.application.dto.liveChat.ChatSessionDTO;
import ola.application.dto.liveChat.MessageDTO;
import ola.application.entity.liveChat.ChatSession;
import ola.application.entity.liveChat.ChatSessionStatus;
import ola.application.entity.liveChat.Message;
import ola.application.exception.BadRequestException;
import ola.application.repository.UserRepo;
import ola.application.repository.liveChat.ChatSessionRepository;
import ola.application.repository.liveChat.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiveHelpService {

    @Autowired
    private ChatSessionRepository chatSessionRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ChatSessionDTO> getOpenChatSessions() {
        List<ChatSession> openSessions = chatSessionRepository.findByStatus(ChatSessionStatus.OPEN);
        return openSessions.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ChatSessionDTO acceptChatSession(int sessionId) throws BadRequestException {
        ChatSession session = chatSessionRepository.findById(sessionId)
                .orElseThrow(() -> new BadRequestException(sessionId,"Invalid session ID"));

        session.setStatus(ChatSessionStatus.IN_PROGRESS);
        ChatSession updatedSession = chatSessionRepository.save(session);

        return mapToDTO(updatedSession);
    }

    public ChatSessionDTO startChat(int userId) throws BadRequestException {
        validateUserId(userId);

        ChatSession session = new ChatSession();
        session.setUserId(userId);
        session.setStatus(ChatSessionStatus.OPEN);
        ChatSession savedSession = chatSessionRepository.save(session);

        return mapToDTO(savedSession);
    }

    public MessageDTO sendMessage(int sessionId, String message, String sender) throws BadRequestException {
        ChatSession session = chatSessionRepository.findById(sessionId)
                .orElseThrow(() -> new BadRequestException(sessionId,"Invalid session ID"));

        Message newMessage = new Message();
        newMessage.setSession(session);
        newMessage.setMessage(message);
        newMessage.setSender(sender);
        newMessage.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(newMessage);

        return mapToDTO(savedMessage);
    }

    public List<MessageDTO> getChatHistory(int sessionId) throws BadRequestException {
        if (!chatSessionRepository.existsById(sessionId)) {
            throw new BadRequestException(sessionId,"Invalid session ID");
        }

        List<Message> messages = messageRepository.findBySessionId(sessionId);

        return messages.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private void validateUserId(int userId) throws BadRequestException {
        userRepo.findById(userId)
                .orElseThrow(() -> new BadRequestException(userId,"Invalid user ID"));
    }

    private ChatSessionDTO mapToDTO(ChatSession session) {
        return modelMapper.map(session, ChatSessionDTO.class);
    }

    private MessageDTO mapToDTO(Message message) {
        return modelMapper.map(message, MessageDTO.class);
    }
}
