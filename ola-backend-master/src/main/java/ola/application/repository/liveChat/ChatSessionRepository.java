package ola.application.repository.liveChat;

import ola.application.entity.liveChat.ChatSession;
import ola.application.entity.liveChat.ChatSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Integer> {
    List<ChatSession> findByStatus(ChatSessionStatus status);
}