package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findDistinctByUserId(Long userId);
}
