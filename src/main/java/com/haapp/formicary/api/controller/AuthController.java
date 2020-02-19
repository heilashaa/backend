package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.article.ArticlesResponse;
import com.haapp.formicary.domain.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for authorization and authentication. API allows registration, social and email authentication."
        /*, authorizations = {@Authorization(BASIC_AUTH)}*/)
@RestController
@RequestMapping(value = "/api/v1/auth", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthController {

    private ArticleService articleService;

//    @ApiOperation(value = "Login with email"/*,
//            authorizations = {@Authorization(BASIC_AUTH)}*/)
//    @PostMapping(name = "/", consumes = {APPLICATION_JSON_VALUE})
//    @ResponseStatus(OK)
//    public AuthResponse loginWhitEmail(
//            @ApiParam(value = "User", required = true)
//            @RequestBody @Valid AuthRequest request) {
//        Category category = CategoryMapper.INSTANCE.categoryDtoToCategoryDomain(request.getCategoryDto());
//        category = categoryService.create(category);
//
//        return new LoginResponse(ArticleMapper.INSTANCE.articleDomainToArticleDto(article));
//    }

    @ApiOperation(value = "Select all articles"/*,
            authorizations = {@Authorization(BASIC_AUTH)}*/)
    @GetMapping("/social-login")
    @ResponseStatus(OK)
    public ArticlesResponse socialLogin() {
        System.out.println("@@@@@@@@@");
        return null;
/*        List<Article> articles = articleService.getAll();
        List<ArticleDto> articlesDto = articles.stream().map(ArticleMapper.INSTANCE::articleDomainToArticleDto).collect(Collectors.toList());
        return new ArticlesResponse(articlesDto);*/
    }

//    @ApiOperation(value = "Registration with email"/*,
//            authorizations = {@Authorization(BASIC_AUTH)}*/)
//    @PostMapping(name = "/", consumes = {APPLICATION_JSON_VALUE})
//    @ResponseStatus(OK)
//    public LoginResponse registration(
//            @ApiParam(value = "Article", required = true)
//            /*@RequestPart*/ @Valid ArticleRequest request,
//            @ApiParam(value = "image" /*allowMultiple = true,*/)
//            @RequestPart(value = "file") MultipartFile/*[]*/ image) {
//
//        Article article = ArticleMapper.INSTANCE.articleDtoToArticleDomain(request.getArticleDto());
//        article = articleService.create(article);
//
//        return new LoginResponse(ArticleMapper.INSTANCE.articleDomainToArticleDto(article));
//    }




//    @PostMapping(value = "/login")
//    @ResponseStatus(value = HttpStatus.OK)
//    public LoginResponseDto login(
//            @RequestBody final LoginRequestDto loginRequestDto
//    ) {
//        return authenticationService.login(loginRequestDto);
//    }
//
//    @GetMapping(value = "/me")
//    @ResponseStatus(value = HttpStatus.OK)
//    public AuthUserDto me() {
//        return authenticationService.getMe();
//    }

}



