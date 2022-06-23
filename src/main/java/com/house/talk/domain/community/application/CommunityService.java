package com.house.talk.domain.community.application;

import com.house.talk.domain.community.dao.PostCommentRepository;
import com.house.talk.domain.community.dao.PostImageRepository;
import com.house.talk.domain.community.dao.PostRepository;
import com.house.talk.domain.community.domain.Post;
import com.house.talk.domain.community.domain.PostImage;
import com.house.talk.domain.community.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final PostCommentRepository postCommentRepository;

    private static final String BASE_URL = "https://sw-hackathon.s3.ap-northeast-2.amazonaws.com/origin/";

    @Transactional
    public void insertPost(PostRequest request) throws IOException {
        Long postId = postRepository.save(request.toPostEntity()).getId();

        List<String> uploadedFiles = uploadPostImg(request);
        savePostImg(postId, uploadedFiles);
    }

    private boolean isNotEmptyFile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }

    private void savePostImg(Long postId, List<String> uploadedFiles) {
        for(String file : uploadedFiles) {
            postImageRepository.save(
                    PostImage.builder()
                            .post(new Post(postId))
                            .img(BASE_URL+file)
                    .build());
        }
    }

    private List<String> uploadPostImg(PostRequest request) throws IOException {
        List<String> files = new ArrayList<>();

        if (isNotEmptyFile(request.getImg1())) {
            String fileName = getFileName(request.getImg1(), "1");

            upload(request.getImg1(), fileName);
            files.add(fileName);
        }
        if (isNotEmptyFile(request.getImg2())) {
            String fileName = getFileName(request.getImg2(), "2");

            upload(request.getImg2(), fileName);
            files.add(fileName);
        }
        if (isNotEmptyFile(request.getImg3())) {
            String fileName = getFileName(request.getImg3(), "3");

            upload(request.getImg3(), fileName);
            files.add(fileName);
        }
        if (isNotEmptyFile(request.getImg4())) {
            String fileName = getFileName(request.getImg4(), "4");

            upload(request.getImg4(), fileName);
            files.add(fileName);
        }
        if (isNotEmptyFile(request.getImg5())) {
            String fileName = getFileName(request.getImg5(), "5");

            upload(request.getImg5(), fileName);
            files.add(fileName);
        }

        return files;
    }

    private String getFileName(MultipartFile file, String num) {
        return new Timestamp(System.currentTimeMillis()).getTime() + num + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }

    private void upload(MultipartFile file, String fileName) throws IOException {
        File newFile = new File(fileName);

        file.transferTo(newFile);
    }

    @Transactional(readOnly = true)
    public PostResponse getPostsByHomeId(Long homeId) {
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
        return PostDetailResponse.of(postRepository.findById(postId).orElse(new Post(postId)));
    }

    @Transactional
    public void createComment(CommentRequest request) {
        postCommentRepository.save(request.toEntity());
    }
}
