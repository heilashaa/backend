package com.haapp.formicary.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Setter
@Getter
@Entity
public class Campaign {

    @Id
    private Long id;
    private String name;
    private String description;
    private Integer targetAmount;
    private Date launchDate;
    private Date expirationDate;
    private User user;
    private Category category;

    @ManyToOne(optional = false)
    private Bonus bonuses;

    public Bonus getBonuses() {
        return bonuses;
    }

    public void setBonuses(Bonus bonuses) {
        this.bonuses = bonuses;
    }
}
