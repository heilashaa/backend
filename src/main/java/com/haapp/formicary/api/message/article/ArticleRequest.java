package com.haapp.formicary.api.message.article;

import com.haapp.formicary.domain.model.ArticleDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ArticleRequest {

    @Valid
    @NotNull
    private ArticleDto articleDto;

    private MultipartFile image;
}

