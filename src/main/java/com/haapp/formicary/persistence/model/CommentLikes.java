package com.haapp.formicary.persistence.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Immutable
@Table(name = "comment_likes")
public class CommentLikes {

    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;
    private long likes;
    private long dislikes;
}
