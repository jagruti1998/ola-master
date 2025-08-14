package ola.application.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ola_ride")
public class OlaRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private OlaUser user;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = true)
    private OlaDriver driver;

    @OneToMany(mappedBy = "ride")
    private List<OlaMessage> messages;

    @Column(name = "start_location",nullable = false)
    private String startLocation;

    @Column(name = "current_location", nullable = false)
    private String currentLocation;

    @Column(name = "end_location",nullable = false)
    private String endLocation;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private RideStatus status;

    @Column(nullable = false)
    private double fare;

    @Column
    private Double distanceKm;

    @Column
    private Integer durationMin;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column
    private String otp;


}