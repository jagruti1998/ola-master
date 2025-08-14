package ola.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ola_ride_request")
public class OlaRideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_request_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private OlaUser user;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private OlaDriver driver;

    @ManyToOne
    @JoinColumn(name = "ride_id")
    private OlaRide ride;

    @Column(name = "request_time", nullable = false)
    private Date requestTime;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
}
