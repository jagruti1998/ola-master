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
@Table(name = "ola_wallet")
public class OlaWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="wallet_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private OlaUser user;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}