package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.auth.*;
import com.haapp.formicary.domain.model.LoginData;
import com.haapp.formicary.domain.model.RegistrationData;
import com.haapp.formicary.domain.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for authorization and authentication. API allows registration, social and email authentication.")
@RestController
@RequestMapping(value = "/api/v1/auth", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "Get JWT with email and password")
    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public LoginResponse login(
            @ApiParam(value = "Login data {email, password}", required = true)
            @RequestBody @Valid LoginRequest request) {
        LoginData loginRequestDto = request.getLoginRequest();
        return new LoginResponse(authService.login(loginRequestDto));
    }

    @ApiOperation(value = "Get auth user profile information")
    @GetMapping(value = "/user-info")
    @ResponseStatus(OK)
    public AuthResponse getUserInfo() {
        return new AuthResponse(authService.getUserInfo());
    }

    @ApiOperation(value = "Register user with email and password")
    @PostMapping(value = "/registration", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public RegistrationResponse registration(
            @ApiParam(value = "Registration data {username, email, password}", required = true)
            @RequestBody @Valid RegistrationRequest request) {
        RegistrationData registrationRequestDto = request.getRegistrationRequest();
        return new RegistrationResponse(authService.registrationAndLogin(registrationRequestDto));
    }

    @ApiOperation(value = "Social login with google or facebook account")
    @PostMapping(value = "/social-login", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public SocialLoginResponse socialLogin(
            @ApiParam(value = "Login data {username, email, password}", required = true)
            @RequestBody @Valid RegistrationRequest request) {
        RegistrationData registrationRequestDto = request.getRegistrationRequest();
        return new SocialLoginResponse(authService.socialLogin(registrationRequestDto));
    }
}



