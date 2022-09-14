package com.house.talk.domain.community.application;

import com.house.talk.domain.community.dao.PostCommentRepository;
import com.house.talk.domain.community.dao.PostImageRepository;
import com.house.talk.domain.community.dao.PostRepository;
import com.house.talk.domain.community.domain.Post;
import com.house.talk.domain.community.domain.PostImage;
import com.house.talk.domain.community.dto.*;
import com.house.talk.global.error.ErrorCode;
import com.house.talk.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final PostCommentRepository postCommentRepository;

    @Value("${s3.bucket.url}")
    private String BUCKET_URL;

    @Transactional
    public void insertPost(PostRequest request) {
        Long postId = postRepository.save(
                request.toPostEntity()
        ).getId();

        List<String> uploadedPostImages = uploadPostImages(request);

        savePostImages(postId, uploadedPostImages);
    }

    private List<String> uploadPostImages(PostRequest request) {
        List<MultipartFile> images = request.getImgs();

        Stream<MultipartFile> uploadableImages = images.stream()
                .filter(file -> !ObjectUtils.isEmpty(file));

        uploadableImages.forEach(image ->
            upload(image, PostRequest.toEachFileName(image, images.indexOf(image)))
        );

        return uploadableImages
                .map(image -> PostRequest.toEachFileName(image, images.indexOf(image)))
                .collect(Collectors.toList());
    }

    private void upload(MultipartFile file, String fileName) {
        try {
            file.transferTo(new File(fileName));
        } catch(Exception exception) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


    private void savePostImages(Long postId, List<String> uploadedImages) {
        uploadedImages.forEach(image ->
                postImageRepository.save(
                        PostImage.of(postId, BUCKET_URL+image)
                )
        );
    }

    @Transactional(readOnly = true)
    public PostResponse getPostsByHomeId(Long homeId) {
        List<PostByHomeResponse> postByHomeResponses = postRepository.findByHome_id(homeId)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(PostByHomeResponse::from)
                .collect(Collectors.toList());

        return PostResponse.from(postByHomeResponses);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPostByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElse(Post.from(postId));

        return PostDetailResponse.from(post);
    }

    @Transactional
    public void createComment(CommentRequest request) {
        postCommentRepository.save(
                request.toEntity()
        );
    }
}
