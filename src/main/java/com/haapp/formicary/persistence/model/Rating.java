package com.haapp.formicary.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "rating_user_FK"))
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false, foreignKey = @ForeignKey(name = "rating_campaign_FK"))
    private Campaign campaign;
}
