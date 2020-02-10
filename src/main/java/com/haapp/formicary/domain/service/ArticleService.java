package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.Article;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.mapping.ArticleMapper;
import com.haapp.formicary.persistence.model.ArticleEntity;
import com.haapp.formicary.persistence.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.ARTICLE_NOT_FOUND;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Transactional
public class ArticleService {

    final private ArticleRepository articleRepository;

    public List<Article> getAll(){
        List<ArticleEntity> articleEntities= articleRepository.findAll();
        return articleEntities.stream().map(ArticleMapper.INSTANCE::articlePersistenceToArticleDomain)
                .collect(Collectors.toList());
    }

    public Article findById(Long id){
        ArticleEntity articleEntity = articleRepository.findById(id).orElseThrow(() -> new NotFoundException(ARTICLE_NOT_FOUND));
        return ArticleMapper.INSTANCE.articlePersistenceToArticleDomain(articleEntity);
    }

    public Article create(Article article){
        if(nonNull(article)) {
            ArticleEntity articleEntity = articleRepository.save(
                    ArticleMapper.INSTANCE.articleDomainToArticlePersistence(article)
            );
            return ArticleMapper.INSTANCE.articlePersistenceToArticleDomain(articleEntity);
        }
        return null;
    }

    public Article deleteById(Long id){
        ArticleEntity articleEntity = articleRepository.findById(id).orElseThrow(() -> new NotFoundException(ARTICLE_NOT_FOUND));
        articleRepository.delete(articleEntity);
        return ArticleMapper.INSTANCE.articlePersistenceToArticleDomain(articleEntity);
    }

    public Article update(Article article, Long id){
        articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ARTICLE_NOT_FOUND));
        ArticleEntity articleEntity = ArticleMapper.INSTANCE.articleDomainToArticlePersistence(article);
        articleEntity.setId(id);
        articleEntity = articleRepository.save(articleEntity);
        return ArticleMapper.INSTANCE.articlePersistenceToArticleDomain(articleEntity);
    }
}
