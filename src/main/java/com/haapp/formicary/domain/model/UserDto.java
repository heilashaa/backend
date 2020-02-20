package com.haapp.formicary.domain.model;

import com.haapp.formicary.persistence.model.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotNull
    @Size(min = 6)
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 8)
    private String password;
    private UserRole role;
    private UserTheme theme;
    private UserLanguage language;

    private List<CampaignDto> campaignsDto = new ArrayList<>();

    private Set<CommentDto> commentsDto = new HashSet<>();

    private Set<LikeDto> likesDto = new HashSet<>();

    private Set<RatingDto> ratingsDto = new HashSet<>();

    private Set<BonusDto> bonusesDto = new HashSet<>();;
}
