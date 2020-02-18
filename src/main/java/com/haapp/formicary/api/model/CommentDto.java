package com.haapp.formicary.api.model;

import com.haapp.formicary.persistence.model.CampaignEntity;
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
public class CommentDto {

    private Long id;
    private String text;
    private LocalDateTime launchDate;

    private CampaignEntity campaign;

    private UserDto user;

    private Set<LikeDto> likes = new HashSet<>();
}
