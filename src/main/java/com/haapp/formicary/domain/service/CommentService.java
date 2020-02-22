package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.*;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.persistence.model.Comment;
import com.haapp.formicary.persistence.model.Like;
import com.haapp.formicary.persistence.repository.CommentRepository;
import com.haapp.formicary.persistence.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private LikeRepository likeRepository;
    private CampaignService campaignService;
    private UserService userService;
    private ModelMapper modelMapper;


    public CommentDto addComment(Long campaignId, CommentDto comment){
        if(nonNull(comment)) {
            var campaign = campaignService.findByIdRequired(campaignId);
            comment.setCampaign(campaign);
            comment.setUser(userService.getCurrentUser());
            return save(comment);
        }
        return null;
    }

    public LikeDto likeComment(Long commentId, LikeDto like){
        var comment = findByIdRequired(commentId);
        var user = userService.getCurrentUser();
        var optional = findByUserAndComment(user, comment);
        if(optional.isPresent()){
            LikeDto saved = optional.get();
            modelMapper.map(like, saved);
            return saveLike(saved);
        } else {
            like.setUser(user);
            like.setComment(comment);
            return saveLike(like);
        }
    }

    public CommentDto findByIdRequired(Long id){
        var optional = commentRepository.findById(id);
        return optional.map(comment -> modelMapper.map(comment, CommentDto.class))
                .orElseThrow(()-> new NotFoundException(""));
    }

    public List<CommentDto> findByCampaignId(Long campaignId){
        var articles = commentRepository.findAllByCampaignIdOrderByLaunchDate(campaignId);
        return asList(modelMapper.map(articles, CommentDto[].class));
    }

    public CommentDto save(CommentDto comment) {
        var dataComment = modelMapper.map(comment, Comment.class);
        dataComment  = commentRepository.save(dataComment);
        return modelMapper.map(dataComment, CommentDto.class);
    }

    public LikeDto saveLike(LikeDto like) {
        var dataLike = modelMapper.map(like, Like.class);
        dataLike  = likeRepository.save(dataLike);
        return modelMapper.map(dataLike, LikeDto.class);
    }

    public Optional<LikeDto> findByUserAndComment(UserDto user, CommentDto comment) {
        return likeRepository.findByUserIdAndCommentId(user.getId(), comment.getId())
                .map(like -> modelMapper.map(like, LikeDto.class));
    }
}
