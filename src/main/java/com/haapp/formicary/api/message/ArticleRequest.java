package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.ArticleApi;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ArticleRequest {

    @Valid
    @NotNull
    private ArticleApi article;

}

