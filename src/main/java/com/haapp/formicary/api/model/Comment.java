package com.haapp.formicary.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long id;
    private String text;
    private LocalDateTime launchDate;

    private CommentLikes likes;
    private User user;

    public CommentLikes getLikes(){
        if(isNull(likes)){
            likes = new CommentLikes();
        }
        return likes;
    }
}
