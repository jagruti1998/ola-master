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
@Table(name = "ola_feedback")
public class OlaFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private OlaRide ride;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private OlaUser user;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private OlaDriver driver;

    @Column
    private Integer rating;

    @Column
    private String comment;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}