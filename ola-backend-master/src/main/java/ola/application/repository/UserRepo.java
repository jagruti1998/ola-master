package ola.application.repository;

import ola.application.entity.OlaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<OlaUser,Integer> {
    Optional<OlaUser> findByUsernameAndIsDeletedFalse(String username);
    List<OlaUser> findAllByIsDeletedFalse();
    Optional<OlaUser> findByIdAndIsDeletedFalse(int id);
}
