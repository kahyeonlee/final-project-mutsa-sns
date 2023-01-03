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

    //포스트 작성
    public PostDto createPost(PostCreateRequest dto,String userName) {
        //userName 못찾을때 에러
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        //저장
        Post post = Post.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .user(user)
                .build();

        Post savedPost = postRepository.save(post);

        return PostDto.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .body(savedPost.getBody())
                .build();
    }

    //포스트 삭제
    @Transactional
    public boolean deletePost(Long postId, String userName) {
        //postId 없을때 에러 처리
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        //userName 정보를 못찾을때 에러처리
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        //userName이 일치하지 않을때 에러 처리
        if (!Objects.equals(post.getUser().getUserName(),userName)){
            throw new AppException(ErrorCode.INVALID_PERMISSION);
        }
        //삭제
        postRepository.delete(post);

        return true;

    }
    //포스트 수정
    @Transactional
    public boolean modifyPost(PostModifyRequest dto, Long postId, String userName){
        //postId 없을때 에러 처리
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        //userName 정보를 못찾을때 에러처리
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        //userName이 일치하지 않을때 에러 처리
        if (!Objects.equals(post.getUser().getUserName(),userName)){
            throw new AppException(ErrorCode.INVALID_PERMISSION);
        }

        //수정
        post.modify(dto.getTitle(),dto.getBody());
        postRepository.save(post);

        return true;
    }

    //포스트 상세
    public PostDto detailPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        return PostDto.fromEntity(post);
    }

    //포스트 목록 조뢰
    public Page<PostDto> pageList(Pageable pageable){
        Page<Post>  post = postRepository.findAll(pageable);
        Page<PostDto> postDto = PostDto.toDto(post);
        return postDto;
    }

}

