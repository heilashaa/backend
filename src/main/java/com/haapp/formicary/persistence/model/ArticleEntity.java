package com.haapp.formicary.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String headline;
    private String text;
    private String imageLink;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false, foreignKey = @ForeignKey(name = "article_campaign_FK"))
    private CampaignEntity campaign;
}
