package com.haapp.formicary.persistence.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Immutable
@Table(name = "campaign_rating")
public class CampaignRating {

    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;
    private Integer value;

}
