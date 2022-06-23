package com.house.talk.domain.community.dao;

import com.house.talk.domain.community.domain.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
