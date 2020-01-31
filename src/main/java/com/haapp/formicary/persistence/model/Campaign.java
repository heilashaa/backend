package com.haapp.formicary.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer targetAmount;
    private Date launchDate;
    private Date expirationDate;

    @ManyToOne(optional = false)
    @JoinColumn()
    private User user;

    @ManyToOne
    @JoinColumn
    private Category category;

    @OneToMany(/*optional = false*/)
    private Set<Bonus> bonuses;

//    public Bonus getBonuses() {
//        return bonuses;
//    }
//
//    public void setBonuses(Bonus bonuses) {
//        this.bonuses = bonuses;
//    }
}
