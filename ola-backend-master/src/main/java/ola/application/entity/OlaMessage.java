package ola.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ola_message")
public class OlaMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private OlaRide ride;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private OlaUser sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private OlaUser receiver;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "is_received",nullable = false)
    private Boolean isReceived;

    @Column(name = "is_seen",nullable = false)
    private Boolean isSeen;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

}
