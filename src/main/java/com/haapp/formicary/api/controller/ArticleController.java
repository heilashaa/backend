package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.ArticlesResponse;
import com.haapp.formicary.api.message.CategoriesResponse;
import com.haapp.formicary.api.message.ArticleRequest;
import com.haapp.formicary.api.message.ArticleResponse;
import com.haapp.formicary.api.model.ArticleDto;
import com.haapp.formicary.domain.model.Article;
import com.haapp.formicary.domain.service.ArticleService;
import com.haapp.formicary.mapping.ArticleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(
        value = "REST API for campaigns Articles. API allows operations with article: create, read, update, delete."
        /*, authorizations = {@Authorization(BASIC_AUTH)}*/)
@RestController
@RequestMapping(value = "/api/v1/article", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    @ApiOperation(value = "Select all articles"/*,
            authorizations = {@Authorization(BASIC_AUTH)}*/)
    @GetMapping(name = "/")
    @ResponseStatus(OK)
    public ArticlesResponse getArticles() {
        List<Article> articles = articleService.getAll();
        List<ArticleDto> articlesDto = articles.stream().map(ArticleMapper.INSTANCE::articleDomainToArticleDto).collect(Collectors.toList());
        return new ArticlesResponse(articlesDto);
    }

    @ApiOperation(value = "Find article by id")
//            authorizations = {@Authorization(BASIC_AUTH)})
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ArticleResponse getArticle(
            @ApiParam(value = "Article ID", required = true)
            @PathVariable Long id) {
        Article article = articleService.findById(id);
        return new ArticleResponse(ArticleMapper.INSTANCE.articleDomainToArticleDto(article));
    }

    @ApiOperation(value = "Add new article"/*,
            authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PostMapping(name = "/", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public ArticleResponse createArticle(
            @ApiParam(value = "Article", required = true)
            @RequestBody @Valid ArticleRequest request) {
        Article article = ArticleMapper.INSTANCE.articleDtoToArticleDomain(request.getArticleDto());
        article = articleService.create(article);
        return new ArticleResponse(ArticleMapper.INSTANCE.articleDomainToArticleDto(article));
    }

    @ApiOperation(value = "Delete article by id"
            /* authorizations = {@Authorization(BASIC_AUTH)}*/)
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public ArticleResponse deleteArticle(
            @ApiParam(value = "Article ID", required = true)
            @PathVariable Long id) {
        Article article = articleService.deleteById(id);
        return new ArticleResponse(ArticleMapper.INSTANCE.articleDomainToArticleDto(article));
    }

    @ApiOperation(value = "Update article"
            /* authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public ArticleResponse updateArticle(
            @ApiParam(value = "Article ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Article", required = true)
            @RequestBody @Valid ArticleRequest request) {
        Article article = ArticleMapper.INSTANCE.articleDtoToArticleDomain(request.getArticleDto());
        article = articleService.update(article, id);
        return new ArticleResponse(ArticleMapper.INSTANCE.articleDomainToArticleDto(article));
    }
}
