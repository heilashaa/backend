package com.haapp.formicary.infrastructure.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {

    public static String CATEGORY_NOT_FOUND = "category.not.found";
    public static String ARTICLE_NOT_FOUND = "article.not.found";
    public static String IMAGE_NOT_UPLOAD = "file.not.upload";
    public static String INTERNAL_SERVICE_ERROR = "internal.service.error";
    public static String VALIDATION_ERROR = "validation.error";
    public static String USER_NOT_FOUND = "user.not.found";
    public static String EMAIL_SHOULD_BE_PASSED = "email.should.be.passed";
    public static String PASSWORD_SHOULD_BE_PASSED = "password.should.be.passed";
    public static String USERNAME_SHOULD_BE_PASSED = "username.should.be.passed";
    public static String USER_NOT_EXIST = "user.not.exist";
    public static String AUTHENTICATION_FAILED = "authentication.failed";
    public static String INCORRECT_AYTH_DATA = "incorrect.auth.data";
    public static String EMAIL_ALREADY_USE = "email.already.use";
    public static String CAN_SET_THIS_TOKEN = "can.set.this.token";
    public static String ERROR_GENERATING_TOKEN = "error.generating.token";
    public static String TOKEN_NULL_OR_BLANK = "token.null.or.blank";
    public static String TOKEN_SIGNATURE_VERIFICATION_FAILED = "token.signature.verification.failed";
    public static String TOKEN_PARSING_FAILED = "token.parsing.failed";
    public static String TOKEN_NOT_FOUND_IN_REQUEST_HEADER = "token.not.found.in.request.header";
    public static String TOKEN_NOT_CONTAIN_USER_ID = "token.not.contain.user.id";
    public static String TOKEN_NOT_CONTAIN_EXISTED_USER_ID = "token.not.contain.existed.user.id";
    public static String AUTHENTICATION_TOKEN_EXPIRED = "authentication.token.expired";
    public static String SOCIAL_LOGIN_FAILED = "social.login.failed";

}

