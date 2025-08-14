package ola.application.repository;

import ola.application.entity.OlaTermAndCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermAndConditionRepo extends JpaRepository<OlaTermAndCondition,Integer> {
}
