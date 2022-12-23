package com.mutsasns.service;

import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.dto.PostCreateRequest;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.domain.entity.User;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.repository.PostRepository;
import com.mutsasns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
