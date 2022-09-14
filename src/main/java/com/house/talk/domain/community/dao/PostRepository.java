package com.house.talk.domain.community.dao;

import com.house.talk.domain.community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findByHome_id(@Param("homeId") Long homeId);
}
