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
public class CampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer targetAmount;
    @CreationTimestamp
    private LocalDateTime launchDate;
    private LocalDateTime expirationDate;
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "campaign_user_FK"))
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "campaign_category_FK"))
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "campaign")
    private Set<BonusEntity> bonuses = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "campaign")
    private Set<CommentEntity> comments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "campaign")
    private Set<ArticleEntity> articles = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "campaign")
    private Set<RatingEntity> ratings = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "campaign")
    private Set<ImageEntity> images = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "campaign_tag",
            joinColumns = @JoinColumn(name = "campaign_id"), foreignKey = @ForeignKey(name = "tag_campaign_FK"),
            inverseJoinColumns = @JoinColumn(name = "tag_id") , inverseForeignKey = @ForeignKey(name = "campaign_tag_FK"))
    private Set<TagEntity> tags;
}
