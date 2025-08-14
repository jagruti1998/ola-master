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
@Table(name = "ola_driver")
public class OlaDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private OlaUser user;

    @Column(nullable = true)
    private String dob;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = true)
    private OlaVehicle vehicle;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(  cascade = CascadeType.ALL)
    private OlaBankingDetails bankingDetails;

}