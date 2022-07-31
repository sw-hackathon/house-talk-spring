package com.house.talk.domain.community.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "post_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", updatable = false)
    private Post post;

    @Column(name = "img")
    private String img;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostImage postImage = (PostImage) o;
        return id != null && Objects.equals(id, postImage.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Builder
    private PostImage(Post post, String img) {
        this.post = post;
        this.img = img;
    }

    public static PostImage of(Long postId, String img) {
        return PostImage.builder()
                .post(Post.from(postId))
                .img(img)
                .build();
    }
}
