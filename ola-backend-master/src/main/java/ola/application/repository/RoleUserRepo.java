package ola.application.repository;

import ola.application.entity.OlaRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserRepo extends JpaRepository<OlaRoleUser, Integer> {
}