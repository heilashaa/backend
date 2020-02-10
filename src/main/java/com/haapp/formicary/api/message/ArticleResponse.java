package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse extends BaseResponse{

    private ArticleDto articleDto;
}
