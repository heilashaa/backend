package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> , JpaSpecificationExecutor<Campaign> {

    List<Campaign> findDistinctByUserId(Long userId);
}
