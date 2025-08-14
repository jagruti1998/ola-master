package ola.application.services.rideService;

import ola.application.dto.ride.RideDTO;
import ola.application.entity.OlaDriver;
import ola.application.entity.OlaRide;
import ola.application.entity.OlaUser;
import ola.application.entity.RideStatus;
import ola.application.exception.BadRequestException;
import ola.application.repository.DriverRepo;
import ola.application.repository.RideRepo;
import ola.application.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RideService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RideRepo rideRepo;
    @Autowired
    private DriverRepo driverRepo;

    // fetch all available rides
    public List<RideDTO> getAllAvailableRides() {
        List<OlaRide> rides = rideRepo.findByStatus(RideStatus.BOOKED); // Or any other condition to filter available rides
        return rides.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // with three points
    public List<RideDTO> findRidesByLocation(String startLocation, String currentLocation, String endLocation) {
        List<OlaRide> rides = rideRepo.findByStartLocationAndCurrentLocationAndEndLocation(startLocation, currentLocation, endLocation); // Assuming this method exists in rideRepo
        return rides.stream().map(this::mapToDtoWithUserDetails).collect(Collectors.toList());
    }

    // Book ride
    public RideDTO bookRide(RideDTO rideDTO) throws BadRequestException {
        OlaUser user = userRepo.findById(rideDTO.getUser_id())
                .orElseThrow(() -> new BadRequestException(404, "Invalid user ID"));

        OlaRide ride = OlaRide.builder()
                .user(user)
                .driver(null)  // Set driver to null initially
                .startLocation(rideDTO.getStartLocation())
                .currentLocation(rideDTO.getStartLocation()) // Set current location initially
                .endLocation(rideDTO.getEndLocation())
                .startTime(LocalDateTime.now())
                .status(RideStatus.BOOKED)
                .fare(rideDTO.getFare())
                .distanceKm(rideDTO.getDistanceKm())
                .durationMin(rideDTO.getDurationMin())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        OlaRide savedRide = rideRepo.save(ride);
        return mapToDtoWithUserDetails(savedRide);
    }

    // Get ride details using ride ID
    public RideDTO getDetails(int rideId) throws BadRequestException {
        OlaRide ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new BadRequestException(401, "No ride with id is found"));

        return mapToDtoWithUserDetails(ride);
    }

    // Cancel ride
    public RideDTO cancelRide(int rideId) throws BadRequestException {
        OlaRide ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new BadRequestException(401, "Invalid ride ID"));

        if (ride.getStatus() == RideStatus.BOOKED || ride.getStatus() == RideStatus.ACCEPTED || ride.getStatus()==RideStatus.STARTED) {
            ride.setStatus(RideStatus.CANCELLED);
            ride.setEndTime(LocalDateTime.now());
            ride.setUpdatedAt(LocalDateTime.now());

            OlaRide savedRide = rideRepo.save(ride);
            return mapToDtoWithUserDetails(savedRide);
        } else {
            throw new BadRequestException(401, "Ride cannot be cancelled");
        }
    }

    // Accept ride and get details with OTP generation
    public RideDTO acceptRideAndGetDetails(int rideId, int userId) throws BadRequestException {
        OlaRide ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new BadRequestException(401, "No ride with id is found"));

        if (ride.getStatus() != RideStatus.BOOKED) {
            throw new BadRequestException(401, "Ride cannot be accepted");
        }

        OlaDriver driver = driverRepo.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException(404, "Invalid user ID"));

        String otp = generateOtp();
        ride.setDriver(driver);
        ride.setStatus(RideStatus.ACCEPTED);
        ride.setOtp(otp);
        ride.setUpdatedAt(LocalDateTime.now());

        OlaRide savedRide = rideRepo.save(ride);

        return mapToDtoWithUserDetails(savedRide);
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Verify OTP and start ride
    public RideDTO verifyOtpAndStartRide(int rideId, String otp) throws BadRequestException {
        OlaRide ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new BadRequestException(401, "No ride with id is found"));

        if (ride.getStatus() != RideStatus.ACCEPTED) {
            throw new BadRequestException(401, "Ride is not in an accepted state");
        }

        if (!ride.getOtp().equals(otp)) {
            throw new BadRequestException(401, "Invalid OTP");
        }

        ride.setStatus(RideStatus.STARTED);  // Add STARTED status to RideStatus enum
        ride.setUpdatedAt(LocalDateTime.now());
        ride.setOtp(null);  // Clear the OTP after verification

        OlaRide savedRide = rideRepo.save(ride);
        return mapToDtoWithUserDetails(savedRide);
    }

    // Complete ride
    public RideDTO completeRide(int rideId) throws BadRequestException {
        OlaRide ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new BadRequestException(401, "No ride with id is found"));

        if (ride.getStatus() != RideStatus.STARTED) {
            throw new BadRequestException(401, "Only started rides can be completed");
        }

        ride.setStatus(RideStatus.COMPLETED);
        ride.setEndTime(LocalDateTime.now());
        ride.setUpdatedAt(LocalDateTime.now());

        OlaRide savedRide = rideRepo.save(ride);
        return mapToDtoWithUserDetails(savedRide);
    }

    // Get rides for driver
    public List<RideDTO> getRidesForDriver(int driverUserId) throws BadRequestException {
        OlaDriver driver = driverRepo.findByUserId(driverUserId)
                .orElseThrow(() -> new BadRequestException(404, "Invalid driver user ID"));

        List<OlaRide> rides = rideRepo.findByDriverIdAndStatus(driverUserId, RideStatus.ACCEPTED);

        return rides.stream().map(this::mapToDtoWithUserDetails).collect(Collectors.toList());
    }

    private RideDTO mapToDto(OlaRide ride) {
        RideDTO rideDTO = new RideDTO();
        rideDTO.setRide_id(ride.getId());
        rideDTO.setUser_id(ride.getUser().getId());
        if (ride.getDriver() != null) {
            rideDTO.setDriver_id(ride.getDriver().getUser().getId());
            rideDTO.setDriver_name(ride.getDriver().getUser().getName()); // Map driver name
            rideDTO.setDriver_contact(ride.getDriver().getUser().getPhoneNumber()); // Map driver contact details
        }
        rideDTO.setStartLocation(ride.getStartLocation());
        rideDTO.setCurrentLocation(ride.getCurrentLocation()); // Map currentLocation
        rideDTO.setEndLocation(ride.getEndLocation());
        rideDTO.setStatus(ride.getStatus().name());
        rideDTO.setFare(ride.getFare());
        rideDTO.setDistanceKm(ride.getDistanceKm());
        rideDTO.setDurationMin(ride.getDurationMin());
        return rideDTO;
    }

    private RideDTO mapToDtoWithUserDetails(OlaRide ride) {
        RideDTO rideDTO = new RideDTO();
        rideDTO.setRide_id(ride.getId());
        rideDTO.setUser_id(ride.getUser().getId());
        if (ride.getDriver() != null) {
            rideDTO.setDriver_id(ride.getDriver().getUser().getId());
            rideDTO.setDriver_name(ride.getDriver().getUser().getName()); // Map driver name
            rideDTO.setDriver_contact(ride.getDriver().getUser().getPhoneNumber()); // Map driver contact details
        }
        rideDTO.setStartLocation(ride.getStartLocation());
        rideDTO.setCurrentLocation(ride.getCurrentLocation()); // Map currentLocation
        rideDTO.setEndLocation(ride.getEndLocation());
        rideDTO.setStatus(ride.getStatus().name());
        rideDTO.setFare(ride.getFare());
        rideDTO.setDistanceKm(ride.getDistanceKm());
        rideDTO.setDurationMin(ride.getDurationMin());
        rideDTO.setUser_name(ride.getUser().getName()); // Add user details
        rideDTO.setOtp(ride.getOtp()); // Add OTP field
        return rideDTO;
    }
}
