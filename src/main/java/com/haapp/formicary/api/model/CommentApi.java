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
public class CommentApi {

    private Long id;
    private String text;
    private LocalDateTime launchDate;

    private CommentLikesApi likes;
    private UserApi user;

    public CommentLikesApi getLikes(){
        if(isNull(likes)){
            likes = new CommentLikesApi();
        }
        return likes;
    }
}
