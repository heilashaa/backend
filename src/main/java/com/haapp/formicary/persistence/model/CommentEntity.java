package com.haapp.formicary.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private LocalDateTime launchDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false, foreignKey = @ForeignKey(name = "comment_campaign_FK"))
    private CampaignEntity campaign;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "comment_user_FK"))
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
    private Set<LikeEntity> likes = new HashSet<>();
}
