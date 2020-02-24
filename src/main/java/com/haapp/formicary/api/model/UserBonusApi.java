package com.haapp.formicary.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserBonusApi {

    private Long id;
    private LocalDateTime creationTime;
    private UserApi user;
    private BonusApi bonus;
}
