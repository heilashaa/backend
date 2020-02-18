package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.ArticleDto;
import io.swagger.annotations.ApiParam;
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

