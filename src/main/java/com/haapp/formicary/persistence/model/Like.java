package com.haapp.formicary.persistence.model;

import io.swagger.models.auth.In;
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
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "likes_user_FK"))
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "comment_id", nullable = false, foreignKey = @ForeignKey(name = "likes_comment_FK"))
    private Comment comment;
}
