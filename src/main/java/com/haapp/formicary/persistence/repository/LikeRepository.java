package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.Like;
import com.haapp.formicary.persistence.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndCommentId(long userId, long comment);
}
