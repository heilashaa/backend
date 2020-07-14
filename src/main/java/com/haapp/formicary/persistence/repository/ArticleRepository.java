package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByCampaignId(Long campainId);
}
