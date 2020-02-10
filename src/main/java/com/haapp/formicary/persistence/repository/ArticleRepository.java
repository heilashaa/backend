package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
