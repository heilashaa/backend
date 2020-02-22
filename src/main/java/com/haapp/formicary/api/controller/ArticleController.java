package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.ConfirmationResponse;
import com.haapp.formicary.api.message.article.ArticleRequest;
import com.haapp.formicary.api.message.article.ArticleResponse;
import com.haapp.formicary.api.message.article.ArticlesResponse;
import com.haapp.formicary.api.model.Article;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.model.ArticleDto;
import com.haapp.formicary.domain.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.haapp.formicary.api.message.ConfirmationResponse.SUCCESS;
import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for campaigns articles.")
@RestController
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@ApiService
public class ArticleController {

    private ArticleService articleService;
    private ModelMapper modelMapper;

    @ApiOperation(value = "Add new article to campaign", nickname = "createArticle")
    @PostMapping(value = "/{campaignId}/articles")
    @ResponseStatus(CREATED)
    public ArticleResponse createArticle(
            @PathVariable Long campaignId,
            @ApiParam(value = "article")
            @RequestBody @Valid ArticleRequest request) {
        var article = modelMapper.map(request.getArticle(), ArticleDto.class);
        article = articleService.create(campaignId, article);
        var apiArticle = modelMapper.map(article, com.haapp.formicary.api.model.Article.class);
        return new ArticleResponse(apiArticle);
    }

    @ApiOperation(value = "Get article", nickname = "getArticle")
    @GetMapping(value = "/articles/{articleId}")
    @ResponseStatus(OK)
    public ArticleResponse getArticle(@PathVariable Long articleId) {
        var article = articleService.findByIdRequired(articleId);
        var apiArticle = modelMapper.map(article, com.haapp.formicary.api.model.Article.class);
        return new ArticleResponse(apiArticle);
    }

    @ApiOperation(value = "Get articles by campaign", nickname = "getArticlesByCampaign")
    @GetMapping(value = "/campaign/{campaignId}/articles")
    @ResponseStatus(OK)
    public ArticlesResponse getByCampaign(@PathVariable Long campaignId) {
        var articles = articleService.findByCampaignId(campaignId);
        List<Article> apiArticles =  asList(modelMapper.map(articles, Article[].class));
        return new ArticlesResponse(apiArticles);
    }

    @ApiOperation(value = "Update article", nickname = "updateArticle")
    @PutMapping(value = "/articles/{articleId}")
    @ResponseStatus(OK)
    public ArticleResponse updateArticle(
            @PathVariable Long articleId,
            @ApiParam(value = "article")
            @RequestBody @Valid ArticleRequest request) {
        var article = modelMapper.map(request.getArticle(), ArticleDto.class);
        article = articleService.update(articleId, article);
        var apiArticle = modelMapper.map(article, com.haapp.formicary.api.model.Article.class);
        return new ArticleResponse(apiArticle);
    }

    @ApiOperation(value = "Delete article", nickname = "deleteArticle")
    @DeleteMapping(value = "/articles/{articleId}")
    @ResponseStatus(OK)
    public ConfirmationResponse deleteArticle(
            @PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return SUCCESS;
    }
}
