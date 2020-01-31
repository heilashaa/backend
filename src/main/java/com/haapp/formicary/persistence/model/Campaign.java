package com.haapp.formicary.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer targetAmount;
    private LocalDateTime launchDate;
    private LocalDateTime expirationDate;
    private LocalDateTime modificationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "campaign_user_FK"))
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "campaign_category_FK"))
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "campaign")
    private Set<Bonus> bonuses;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "campaign")
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "campaign")
    private Set<News> news;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "campaign")
    private Set<Rating> ratings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "campaign_tag",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

}
