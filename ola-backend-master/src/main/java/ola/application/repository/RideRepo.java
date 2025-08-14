package ola.application.repository;

import ola.application.entity.OlaRide;
import ola.application.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepo extends JpaRepository<OlaRide,Integer> {
    List<OlaRide> findByDriverIdAndStatus(int driverId, RideStatus status);
    List<OlaRide> findByStatus(RideStatus status);
    List<OlaRide> findByStartLocationAndCurrentLocationAndEndLocation(String startLocation, String currentLocation, String endLocation);

}
