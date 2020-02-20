package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.auth.*;
import com.haapp.formicary.domain.model.LoginRequestDto;
import com.haapp.formicary.domain.model.RegistrationRequestDto;
import com.haapp.formicary.domain.model.RegistrationResponseDto;
import com.haapp.formicary.domain.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for authorization and authentication. API allows registration, social and email authentication."
        /*, authorizations = {@Authorization(BASIC_AUTH)}*/)
@RestController
@RequestMapping(value = "/api/v1/auth", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "Get JWT with email and password"/*,authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public LoginResponse login(
            @ApiParam(value = "Login data {email, password}", required = true)
            @RequestBody @Valid LoginRequest request) {
        LoginRequestDto loginRequestDto = request.getLoginRequestDto();
        return new LoginResponse(authService.login(loginRequestDto));
    }

    @ApiOperation(value = "Get auth user profile information"/*,authorizations = {@Authorization(BASIC_AUTH)}*/)
    @GetMapping(value = "/user-info")
    @ResponseStatus(OK)
    public AuthResponse getUserInfo() {
        return new AuthResponse(authService.getUserInfo());
    }

    @ApiOperation(value = "Register user with email and password"/*,authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PostMapping(value = "/registration", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public RegistrationResponse registration(
            @ApiParam(value = "Registration data {username, email, password}", required = true)
            @RequestBody @Valid RegistrationRequest request) {
        RegistrationRequestDto registrationRequestDto = request.getRegistrationRequestDto();
        return new RegistrationResponse(authService.registrationWithLogin(registrationRequestDto));
    }

//    @ApiOperation(value = "Select all articles"/*,
//            authorizations = {@Authorization(BASIC_AUTH)}*/)
//    @GetMapping("/social-login")
//    @ResponseStatus(OK)
//    public ArticlesResponse socialLogin() {
//        System.out.println("@@@@@@@@@");
//        return null;
/*        List<Article> articles = articleService.getAll();
        List<ArticleDto> articlesDto = articles.stream().map(ArticleMapper.INSTANCE::articleDomainToArticleDto).collect(Collectors.toList());
        return new ArticlesResponse(articlesDto);*/
//    }

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

}



