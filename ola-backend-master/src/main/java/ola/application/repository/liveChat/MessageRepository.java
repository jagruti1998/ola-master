package ola.application.repository.liveChat;

import ola.application.entity.liveChat.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySessionId(int sessionId);
}