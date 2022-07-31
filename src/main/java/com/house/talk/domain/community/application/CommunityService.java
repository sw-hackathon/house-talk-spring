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
import java.io.IOException;
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
    public void insertPost(PostRequest request) throws IOException {
        Long postId = postRepository.save(request.toPostEntity()).getId();

        List<String> uploadPostImgs = uploadPostImgs(request);
        savePostImgs(postId, uploadPostImgs);
    }

    private List<String> uploadPostImgs(PostRequest request) {
        List<MultipartFile> imgs = request.getImgs();
        Stream<MultipartFile> uploadableImgs = imgs.stream()
                .filter(file -> !ObjectUtils.isEmpty(file));

        uploadableImgs.forEach(img ->
            upload(img, PostRequest.toEachFileName(img, imgs.indexOf(img)))
        );

        return uploadableImgs
                .map(img -> PostRequest.toEachFileName(img, imgs.indexOf(img)))
                .collect(Collectors.toList());
    }

    private void upload(MultipartFile file, String fileName) {
        try {
            file.transferTo(new File(fileName));
        } catch(Exception exception) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


    private void savePostImgs(Long postId, List<String> uploadedImgs) {
        uploadedImgs.forEach(img ->
                postImageRepository.save(PostImage.of(postId, BUCKET_URL+img))
        );
    }

    @Transactional(readOnly = true)
    public PostResponse getPostsByHomeId(Long homeId) {
        // todo findbyhomeid else throw
        return PostResponse.builder()
                .items(
                        postRepository.findByHome_id(homeId).stream()
                                .map(PostByHomeResponse::from)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPostByPostId(Long postId) {
        return PostDetailResponse.of(postRepository.findById(postId).orElse(Post.from(postId)));
    }

    @Transactional
    public void createComment(CommentRequest request) {
        postCommentRepository.save(request.toEntity());
    }
}
