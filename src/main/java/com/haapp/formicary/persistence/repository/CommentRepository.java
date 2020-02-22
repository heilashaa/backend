package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.Comment;
import com.haapp.formicary.persistence.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByCampaignIdOrderByLaunchDate(Long campainId);
}
