package com.haapp.formicary.api.message.article;

import com.haapp.formicary.api.model.Article;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ArticleRequest {

    @Valid
    @NotNull
    private Article article;

}

