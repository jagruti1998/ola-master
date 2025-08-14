package ola.application.controller.rideController;

import ola.application.dto.ride.RideDTO;
import ola.application.dto.ride.RideSearchDTO;
import ola.application.exception.BadRequestException;
import ola.application.services.rideService.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {
    @Autowired
    private RideService rideService;

    // Get all available rides
    @GetMapping("/available")
    public ResponseEntity<List<RideDTO>> getAllAvailableRides() {
        return ResponseEntity.ok(rideService.getAllAvailableRides());
    }

    // Book a ride
    @PostMapping("/book")
    public ResponseEntity<RideDTO> bookRide(@RequestBody RideDTO rideDTO) {
        try {
            return ResponseEntity.ok(rideService.bookRide(rideDTO));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get ride details using ride ID
    @GetMapping("/details/{rideId}")
    public ResponseEntity<RideDTO> rideDetails(@PathVariable int rideId) {
        try {
            return ResponseEntity.ok(rideService.getDetails(rideId));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Cancel a ride
    @DeleteMapping("/cancel/{rideId}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable int rideId) {
        try {
            return ResponseEntity.ok(rideService.cancelRide(rideId));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Accept a ride and get details with OTP generation
    @PutMapping("/{rideId}/accept")
    public ResponseEntity<RideDTO> acceptRideAndGetDetails(@PathVariable int rideId, @RequestParam int userId) {
        try {
            return ResponseEntity.ok(rideService.acceptRideAndGetDetails(rideId, userId));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Verify OTP and start ride
    @PutMapping("/{rideId}/start")
    public ResponseEntity<RideDTO> verifyOtpAndStartRide(@PathVariable int rideId, @RequestParam String otp) {
        try {
            return ResponseEntity.ok(rideService.verifyOtpAndStartRide(rideId, otp));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Complete a ride
    @PutMapping("/{rideId}/complete")
    public ResponseEntity<RideDTO> completeRide(@PathVariable int rideId) {
        try {
            return ResponseEntity.ok(rideService.completeRide(rideId));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get rides for a driver
    @GetMapping("/driver/{driverUserId}")
    public ResponseEntity<List<RideDTO>> getRidesForDriver(@PathVariable int driverUserId) {
        try {
            return ResponseEntity.ok(rideService.getRidesForDriver(driverUserId));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Find rides by location
    @PostMapping("/find")
    public ResponseEntity<List<RideDTO>> findRidesByLocation(@RequestBody RideSearchDTO rideSearchDTO) {
        return ResponseEntity.ok(rideService.findRidesByLocation(
                rideSearchDTO.getStartLocation(),
                rideSearchDTO.getCurrentLocation(),
                rideSearchDTO.getEndLocation()));
    }
}
