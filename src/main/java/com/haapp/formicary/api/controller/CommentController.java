package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.*;
import com.haapp.formicary.api.model.Comment;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.model.CommentDto;
import com.haapp.formicary.domain.model.LikeDto;
import com.haapp.formicary.domain.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "API for comments.")
@RestController
@RequestMapping(value = "/api/v1/", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@ApiService
public class CommentController {

    private ModelMapper modelMapper;
    private CommentService commentService;


    @ApiOperation(value = "Add comment", nickname = "addComment")
    @PostMapping(value = "/campaigns/{campaignId}/comments")
    @ResponseStatus(CREATED)
    public CommentResponse addComment(
            @PathVariable Long campaignId,
            @ApiParam(value = "comment")
            @RequestBody @Valid CommentRequest request) {
        var comment = modelMapper.map(request.getComment(), CommentDto.class);
        comment = commentService.addComment(campaignId, comment);
        var apiComment = modelMapper.map(comment, com.haapp.formicary.api.model.Comment.class);
        return new CommentResponse(apiComment);
    }

    @ApiOperation(value = "Get comments by campaign", nickname = "getCommentsByCampaign")
    @GetMapping(value = "/{campaignId}/comments")
    @ResponseStatus(OK)
    public CommentsResponse getCommentsByCampaign(@PathVariable Long campaignId) {
        var comments = commentService.findByCampaignId(campaignId);
        List<Comment> apiComments =  asList(modelMapper.map(comments, Comment[].class));
        return new CommentsResponse(apiComments);
    }

    @ApiOperation(value = "Like comment", nickname = "likeComment")
    @PatchMapping(value = "/comments/{commentId}/likes")
    @ResponseStatus(OK)
    public LikeResponse likeComment(
            @PathVariable Long commentId,
            @ApiParam(value = "like")
            @RequestBody @Valid LikeRequest request) {
        var like = modelMapper.map(request.getLike(), LikeDto.class);
        like = commentService.likeComment(commentId, like);
        var apiLike = modelMapper.map(like, com.haapp.formicary.api.model.Like.class);
        return new LikeResponse(apiLike);
    }
}
