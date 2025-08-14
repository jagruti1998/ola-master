package ola.application.entity.liveChat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    @Enumerated(EnumType.STRING)
    private ChatSessionStatus status;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Message> messages;
}