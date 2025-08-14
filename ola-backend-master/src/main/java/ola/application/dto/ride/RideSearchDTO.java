package ola.application.dto.ride;

import lombok.Data;

@Data
public class RideSearchDTO {
    private String startLocation;
    private String currentLocation;
    private String endLocation;
}
