package com.haapp.formicary.infrastructure.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {

    public static String CATEGORY_NOT_FOUND = "category.not.found";
    public static String ARTICLE_NOT_FOUND = "article.not.found";
    public static String IMAGE_NOT_UPLOAD = "file.not.upload";

    public static String INTERNAL_SERVICE_ERROR = "internal.service.error";
    public static String VALIDATION_ERROR = "validation.error";
}

