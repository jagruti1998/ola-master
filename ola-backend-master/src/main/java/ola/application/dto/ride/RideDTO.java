package ola.application.dto.ride;

import lombok.Data;

@Data
public class RideDTO {
    private int ride_id;
    private int user_id;
    private Integer driver_id; // Use Integer to allow null value
    private String startLocation;
    private String currentLocation;
    private String endLocation;
    private String status;
    private double fare;
    private double distanceKm;
    private int durationMin;
    private String user_name; // Add user details
    private String driver_name; // Add driver details
    private String driver_contact; // Add driver contact details
    private String otp;
}
