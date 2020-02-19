package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.ArticleDto;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.mapping.ArticleMapper;
import com.haapp.formicary.persistence.model.Article;
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

    public List<ArticleDto> getAll(){
        List<Article> articles= articleRepository.findAll();
        return articles.stream().map(ArticleMapper.INSTANCE::articleToArticleDto)
                .collect(Collectors.toList());
    }

    public ArticleDto findById(Long id){
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException(ARTICLE_NOT_FOUND));
        return ArticleMapper.INSTANCE.articleToArticleDto(article);
    }

    public ArticleDto create(ArticleDto articleDto){
        if(nonNull(articleDto)) {
            Article article = articleRepository.save(
                    ArticleMapper.INSTANCE.articleDtoToArticle(articleDto)
            );
            return ArticleMapper.INSTANCE.articleToArticleDto(article);
        }
        return null;
    }

    public ArticleDto deleteById(Long id){
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException(ARTICLE_NOT_FOUND));
        articleRepository.delete(article);
        return ArticleMapper.INSTANCE.articleToArticleDto(article);
    }

    public ArticleDto update(ArticleDto articleDto, Long id){
        articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ARTICLE_NOT_FOUND));
        Article article = ArticleMapper.INSTANCE.articleDtoToArticle(articleDto);
        article.setId(id);
        article = articleRepository.save(article);
        return ArticleMapper.INSTANCE.articleToArticleDto(article);
    }
}
