package com.haapp.formicary.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Category {

    @Id
    private Long id;

    private String name;

}

