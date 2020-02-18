package com.haapp.formicary.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bonus {

    private Long id;
    private String name;
    private String description;
    private Integer amount;

    private Campaign campaign;

    private Set<User> users = new HashSet<>();
}
