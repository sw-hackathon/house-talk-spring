package com.house.talk.domain.community.dao;

import com.house.talk.domain.community.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
