package com.haapp.formicary.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private LocalDateTime launchDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "campaign_id", nullable = false, foreignKey = @ForeignKey(name = "comment_campaign_FK"))
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "comment_user_FK"))
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comment")
    private Set<Like> likes;
}
