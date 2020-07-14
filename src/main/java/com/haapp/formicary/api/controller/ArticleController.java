package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.ArticleRequest;
import com.haapp.formicary.api.message.ArticleResponse;
import com.haapp.formicary.api.message.ArticlesResponse;
import com.haapp.formicary.api.message.ConfirmationResponse;
import com.haapp.formicary.api.model.ArticleApi;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.model.Article;
import com.haapp.formicary.domain.service.ArticleService;
import com.haapp.formicary.domain.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.*;

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

    private final ArticleService articleService;
    private final ImageService imageService;
    private final ModelMapper modelMapper;

    @ApiOperation(value = "Add new article to campaign", nickname = "createArticle")
    @PostMapping(value = "/users/{userId}/campaigns/{campaignId}/articles")
    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public ArticleResponse createArticle(
            @PathVariable Long userId,
            @PathVariable Long campaignId,
            @ApiParam(value = "article")
            @RequestBody @Valid ArticleRequest request) {
        var article = modelMapper.map(request.getArticle(), Article.class);
        article = articleService.create(campaignId, article);
        var apiArticle = modelMapper.map(article, ArticleApi.class);
        return new ArticleResponse(apiArticle);


    }


    @ApiOperation(value = "Get article", nickname = "getArticle")
    @GetMapping(value = "/articles/{articleId}")
    @ResponseStatus(OK)
    public ArticleResponse getArticle(@PathVariable Long articleId) {
        var article = articleService.findByIdRequired(articleId);
        var apiArticle = modelMapper.map(article, ArticleApi.class);
        return new ArticleResponse(apiArticle);
    }

    @ApiOperation(value = "Get articles by campaign", nickname = "getArticlesByCampaign")
    @GetMapping(value = "/campaigns/{campaignId}/articles")
    @ResponseStatus(OK)
    public ArticlesResponse getByCampaign(@PathVariable Long campaignId) {
        var articles = articleService.findByCampaignId(campaignId);
        List<ArticleApi> apiArticles =  asList(modelMapper.map(articles, ArticleApi[].class));
        return new ArticlesResponse(apiArticles);
    }

    @ApiOperation(value = "Update article", nickname = "updateArticle")
    @PutMapping(value = "/users/{userId}/articles/{articleId}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public ArticleResponse updateArticle(
            @PathVariable Long userId,
            @PathVariable Long articleId,
            @ApiParam(value = "article")
            @RequestBody @Valid ArticleRequest request) {
        var article = modelMapper.map(request.getArticle(), Article.class);
        article = articleService.update(articleId, article);
        var apiArticle = modelMapper.map(article, ArticleApi.class);
        return new ArticleResponse(apiArticle);
    }

    @ApiOperation(value = "Delete article", nickname = "deleteArticle")
    @DeleteMapping(value = "/users/{userId}/articles/{articleId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    @ResponseStatus(OK)
    public ConfirmationResponse deleteArticle(
            @PathVariable Long userId,
            @PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return SUCCESS;
    }

    @ApiOperation(value = "Update image", nickname = "updateImage")
    @PutMapping(value = "/users/{userId}/{articleId}/images")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    @ResponseStatus(OK)
    public ArticleResponse updateImage(
            @PathVariable Long userId,
            @PathVariable Long articleId,
            @RequestPart MultipartFile image) {
        var article = imageService.updateArticleImage(articleId, image);
        var apiArticle = modelMapper.map(article, ArticleApi.class);
        return new ArticleResponse(apiArticle);
    }
}
