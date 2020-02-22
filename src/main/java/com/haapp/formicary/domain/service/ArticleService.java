package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.ArticleDto;
import com.haapp.formicary.domain.model.CampaignDto;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.persistence.model.Article;
import com.haapp.formicary.persistence.model.Campaign;
import com.haapp.formicary.persistence.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    public ArticleDto findByIdRequired(Long id){
        var optional = articleRepository.findById(id);
        return optional.map(article -> modelMapper.map(article, ArticleDto.class))
                .orElseThrow(()-> new NotFoundException(""));
    }

    public ArticleDto create(Long campaignId, ArticleDto article){
        if(nonNull(article)) {
            var campaign = campaignService.findByIdRequired(campaignId);
            article.setCampaign(campaign);
            return save(article);
        }
        return null;
    }

    public ArticleDto update(Long id, ArticleDto article){
        var saved = findByIdRequired(id);
        modelMapper.map(article, saved);
        return save(saved);
    }


    public List<ArticleDto> findByCampaignId(Long campaignId){
        var articles = articleRepository.findAllByCampaignId(campaignId);
        return asList(modelMapper.map(articles, ArticleDto[].class));
    }

    public void deleteArticle(Long articleId){
        articleRepository.deleteById(articleId);
    }


    public ArticleDto save(ArticleDto campaign) {
        var dataArticle = modelMapper.map(campaign, Article.class);
        dataArticle  = articleRepository.save(dataArticle);
        return modelMapper.map(dataArticle, ArticleDto.class);
    }
}
