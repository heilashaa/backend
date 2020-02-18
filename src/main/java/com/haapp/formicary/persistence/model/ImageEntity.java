package com.haapp.formicary.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageLink;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false, foreignKey = @ForeignKey(name = "image_campaign_FK"))
    private CampaignEntity campaign;
}
