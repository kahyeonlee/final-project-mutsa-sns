package com.mutsasns.service;

import com.mutsasns.domain.dto.PostModifyRequest;
import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.dto.PostCreateRequest;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.domain.entity.User;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.repository.PostRepository;
import com.mutsasns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto create(PostCreateRequest dto,String userName) {
        //userName 못찾을때 에러
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("%s not founded", userName)));

        //저장
        Post savedPost = postRepository.save(Post.of(dto.getTitle(),dto.getBody(),user));

        return PostDto.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .body(savedPost.getBody())
                .build();
    }

    public boolean delete(Long postId, String userName) {
        //postId 없을때 에러 처리
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", userName)));

        //userName 정보를 못찾을때 에러처리
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("%s not founded", userName)));

        //userName이 일치하지 않을때 에러 처리
        if (!Objects.equals(user.getUserName(),userName)) {
            throw new AppException(ErrorCode.INVALID_PERMISSION,String.format("%s invaild permission", userName));
        }

        //삭제
        postRepository.delete(post);

        return true;
    }
    @Transactional
    public Post modify(PostModifyRequest dto, Long postId, String userName){
        //postId 없을때 에러 처리
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", postId)));

        //userName 정보를 못찾을때 에러처리
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, String.format("%s not founded", userName)));

        //userName이 일치하지 않을때 에러 처리
        if (!Objects.equals(user.getUserName(),userName)) {
            throw new AppException(ErrorCode.INVALID_PERMISSION,String.format("%s invaild permission", userName));
        }

        //수정
        post.setTitle(dto.getTitle());
        post.setBody(dto.getBody());
        Post savedPost = postRepository.save(post);

        return savedPost;
    }

    public PostDto detail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", postId)));

        return PostDto.fromEntity(post);
    }

    public Page<PostDto> pageList(Pageable pageable){
        Page<Post>  post = postRepository.findAll(pageable);
        Page<PostDto> postDto = PostDto.toDto(post);
        return postDto;
    }

}

