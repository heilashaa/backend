package com.haapp.formicary.api.message.article;

import com.haapp.formicary.api.message.BaseResponse;
import com.haapp.formicary.api.model.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse extends BaseResponse {

    @Valid
    @NotNull
    private Article article;
}
