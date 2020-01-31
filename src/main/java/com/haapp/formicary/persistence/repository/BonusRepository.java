package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {
}
