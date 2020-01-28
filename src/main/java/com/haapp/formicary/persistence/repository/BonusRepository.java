package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {
}
