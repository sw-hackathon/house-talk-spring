package com.house.talk.domain.community.api;

import com.house.talk.domain.community.application.CommunityService;
import com.house.talk.domain.community.dto.CommentRequest;
import com.house.talk.domain.community.dto.PostDetailResponse;
import com.house.talk.domain.community.dto.PostRequest;
import com.house.talk.domain.community.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping
    public ResponseEntity<Void> post(@Valid @ModelAttribute PostRequest request) throws IOException {
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
