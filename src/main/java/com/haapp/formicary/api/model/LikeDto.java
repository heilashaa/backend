package com.haapp.formicary.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {

    private Long id;
    private Integer status;

    private UserDto user;

    private CommentDto comment;
}
