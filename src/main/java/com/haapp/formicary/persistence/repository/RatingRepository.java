package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUserIdAndCampaignId(long userId, long campaignId);
}
