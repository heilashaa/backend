package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.TagsResponse;
import com.haapp.formicary.api.model.TagApi;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.OK;

@Api(value = "API for tags.")
@RestController
@RequestMapping(value = "/api/v1/tags")
@AllArgsConstructor
@ApiService
public class TagController {

    private TagService tagService;
    private ModelMapper modelMapper;

    @ApiOperation(value = "Get tags", nickname = "getTags")
    @GetMapping
    @ResponseStatus(OK)
    public TagsResponse getTags() {
        var tags = tagService.getAll();
        List<TagApi> apiTags =  asList(modelMapper.map(tags, TagApi[].class));
        return new TagsResponse(apiTags);
    }
}
