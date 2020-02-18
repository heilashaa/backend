package com.haapp.formicary.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long id;
    private String text;
    private LocalDateTime launchDate;

    private Campaign campaign;

    private User user;

    private Set<Like> likes = new HashSet<>();
}