package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.BonusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusRepository extends JpaRepository<BonusEntity, Long> {
}
