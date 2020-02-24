package com.haapp.formicary.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserBonus {

    private Long id;
    private LocalDateTime creationTime;
    private User user;
    private Bonus bonus;
}
