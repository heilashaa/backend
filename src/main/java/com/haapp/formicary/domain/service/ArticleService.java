package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.Article;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.persistence.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Transactional
public class ArticleService {

    final private CampaignService campaignService;
    final private ArticleRepository articleRepository;
    final private ModelMapper modelMapper;

    public Article findByIdRequired(Long id){
        var optional = articleRepository.findById(id);
        return optional.map(article -> modelMapper.map(article, Article.class))
                .orElseThrow(()-> new NotFoundException(""));
    }

    public Article create(Long campaignId, Article article){
        if(nonNull(article)) {
            var campaign = campaignService.findByIdRequired(campaignId);
            article.setCampaign(campaign);
            return save(article);
        }
        return null;
    }

    public Article update(Long id, Article article){
        var saved = findByIdRequired(id);
        modelMapper.map(article, saved);
        return save(saved);
    }


    public List<Article> findByCampaignId(Long campaignId){
        var articles = articleRepository.findAllByCampaignId(campaignId);
        return asList(modelMapper.map(articles, Article[].class));
    }

    public void deleteArticle(Long articleId){
        articleRepository.deleteById(articleId);
    }


    public Article save(Article article) {
        var dataArticle = modelMapper.map(article, com.haapp.formicary.persistence.model.Article.class);
        dataArticle  = articleRepository.save(dataArticle);
        return modelMapper.map(dataArticle, Article.class);
    }
}
