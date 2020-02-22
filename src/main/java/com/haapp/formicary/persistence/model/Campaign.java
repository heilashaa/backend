package com.haapp.formicary.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer targetAmount;
    private String video;
    @CreationTimestamp
    private LocalDateTime launchDate;
    private LocalDateTime expirationDate;
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "campaign_user_FK"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,  optional = false)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "campaign_category_FK"))
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "campaign")
    private Set<Bonus> bonuses = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "campaign")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "campaign")
    private Set<Article> articles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "campaign")
    private CampaignRating rating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "campaign")
    private Set<Image> images = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "campaign_tag",
            joinColumns = @JoinColumn(name = "campaign_id"), foreignKey = @ForeignKey(name = "tag_campaign_FK"),
            inverseJoinColumns = @JoinColumn(name = "tag_id") , inverseForeignKey = @ForeignKey(name = "campaign_tag_FK"))
    private Set<Tag> tags;
}
