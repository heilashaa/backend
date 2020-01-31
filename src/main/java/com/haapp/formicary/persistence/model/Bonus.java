package com.haapp.formicary.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Setter
@Getter
@Entity
public class Bonus {

    @Id
    private Long id;
    private String name;
    private String description;
    private Integer amount;
    @OneToMany
    private Campaign campaign;
}
