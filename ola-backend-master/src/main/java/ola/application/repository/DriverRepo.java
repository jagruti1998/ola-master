package ola.application.repository;

import ola.application.entity.OlaDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepo extends JpaRepository<OlaDriver,Integer> {

    Optional<OlaDriver> findByUserId(int userId);
}
