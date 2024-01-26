package com.connector.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "likes")
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "post_id")
    private Long post_id;

    @Column(name = "user_id")
    private Long user_id;

    @Builder
    public Likes(Long id, Long post_id, Long user_id) {
        this.id = id;
        this.post_id = post_id;
        this.user_id = user_id;
    }
}
