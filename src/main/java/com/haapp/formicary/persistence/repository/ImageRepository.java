package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByCampaignId(Long campaignId);
}
