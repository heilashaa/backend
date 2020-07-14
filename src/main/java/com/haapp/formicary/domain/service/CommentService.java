package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.*;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
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


    public Comment addComment(Long campaignId, Comment comment){
        if(nonNull(comment)) {
            var campaign = campaignService.findByIdRequired(campaignId);
            comment.setCampaign(campaign);
            comment.setUser(userService.getCurrentUser());
            return save(comment);
        }
        return null;
    }

    public Like likeComment(Long commentId, Like like){
        var comment = findByIdRequired(commentId);
        var user = userService.getCurrentUser();
        var optional = findByUserAndComment(user, comment);
        if(optional.isPresent()){
            Like saved = optional.get();
            modelMapper.map(like, saved);
            return saveLike(saved);
        } else {
            like.setUser(user);
            like.setComment(comment);
            return saveLike(like);
        }
    }

    public Comment findByIdRequired(Long id){
        var optional = commentRepository.findById(id);
        return optional.map(comment -> modelMapper.map(comment, Comment.class))
                .orElseThrow(()-> new NotFoundException(""));
    }

    public List<Comment> findByCampaignId(Long campaignId){
        var articles = commentRepository.findAllByCampaignIdOrderByLaunchDate(campaignId);
        return asList(modelMapper.map(articles, Comment[].class));
    }

    public Comment save(Comment comment) {
        var dataComment = modelMapper.map(comment, com.haapp.formicary.persistence.model.Comment.class);
        dataComment  = commentRepository.save(dataComment);
        return modelMapper.map(dataComment, Comment.class);
    }

    public Like saveLike(Like like) {
        var dataLike = modelMapper.map(like, com.haapp.formicary.persistence.model.Like.class);
        dataLike  = likeRepository.save(dataLike);
        return modelMapper.map(dataLike, Like.class);
    }

    public Optional<Like> findByUserAndComment(User user, Comment comment) {
        return likeRepository.findByUserIdAndCommentId(user.getId(), comment.getId())
                .map(like -> modelMapper.map(like, Like.class));
    }
}
