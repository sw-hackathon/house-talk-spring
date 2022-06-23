package com.house.talk.domain.community.dao;

import com.house.talk.domain.community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByHome_id(@Param("homeId") Long homeId);
}
