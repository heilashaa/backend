package com.haapp.formicary.api.message.article;

import com.haapp.formicary.api.message.BaseResponse;
import com.haapp.formicary.domain.model.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse extends BaseResponse {

    private ArticleDto articleDto;
}
