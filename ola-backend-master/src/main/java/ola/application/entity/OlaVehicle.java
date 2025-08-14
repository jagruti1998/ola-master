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
@Table(name = "ola_vehicle")
public class OlaVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private OlaDriver driver;

    @Column
    private String adhaarNumber;

    @Column
    private String panNumber;

    @Column
    private String licenseNumber;

    @Column
    private String licenseExpiryDate;

    @Column
    private String vehicleNumber;

    @Column
    private String insuranceId;

    @Column
    private String insuranceStartDate;

    @Column
    private String insuranceEndDate;

    @Column
    private String pucId;

    @Column
    private String pucStartDate;

    @Column
    private String pucEndDate;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}