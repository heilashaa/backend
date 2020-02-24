package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.Bonus;
import com.haapp.formicary.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {

    public List<Bonus> findAllByCampaignId(Long campaignId);

}
