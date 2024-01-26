package com.connector.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "posts")
@NoArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "post_id")
    private Long post_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "content")
    private String content;

    @Builder
    public Posts(Long id, Long post_id, Long user_id, String content) {
        this.id = id;
        this.post_id = post_id;
        this.user_id = user_id;
        this.content = content;
    }
}
