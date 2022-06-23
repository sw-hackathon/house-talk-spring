package com.house.talk.domain.community.api;

import com.house.talk.domain.community.application.CommunityService;
import com.house.talk.domain.community.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityApi {

    private final CommunityService communityService;

    @PostMapping
    public ResponseEntity<Void> post(@ModelAttribute PostRequest request) throws IOException {
        communityService.insertPost(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/home/{homeId}")
    public ResponseEntity<PostResponse> getPostByHomeId(@PathVariable("homeId") Long homeId) {
        return ResponseEntity.ok(communityService.getPostsByHomeId(homeId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDetailResponse> getPostByPostId(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(communityService.getPostByPostId(postId));
    }

    @PostMapping("/comment")
    public ResponseEntity<Void> writeComment(@RequestBody CommentRequest request) {
        communityService.createComment(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
