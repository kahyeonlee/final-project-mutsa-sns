package com.mutsasns.service;

import com.mutsasns.domain.dto.PostCreateRequest;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.domain.dto.PostModifyRequest;
import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.entity.User;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.repository.PostRepository;
import com.mutsasns.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    PostService postService;

    PostRepository postRepository = Mockito.mock(PostRepository.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    @BeforeEach
    void setUp() {
        postService = new PostService(postRepository, userRepository);
    }

    @Test
    @DisplayName("등록 성공")
    void create_success() {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                .title("hi")
                .body("it's me")
                .build();
        String userName = "kahyun";

        Post mockPost = mock(Post.class);
        User mockUser = mock(User.class);

        when(userRepository.findByUserName(userName))
                .thenReturn(Optional.of(mockUser));
        when(postRepository.save(any()))
                .thenReturn(mockPost);

        //애러가 발생하지 않으면 true
        Assertions.assertDoesNotThrow(() -> postService.create(postCreateRequest,userName));
    }

    @Test
    @DisplayName("등록 실패 - 유저가 존재하지 않을 때")
    void create_fail() {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                .title("hi")
                .body("it's me")
                .build();
        String userName = "kahyun";

        when(userRepository.findByUserName(userName))
                .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        //애러가 발생하면 true
        AppException exception = Assertions.assertThrows(AppException.class, () -> postService.create(postCreateRequest,userName));
        assertEquals(ErrorCode.USERNAME_NOT_FOUND, exception.getErrorCode());

    }

    @Test
    @DisplayName("수정 실패 - 포스트 존재하지 않음")
    void modify_fail() {
        PostModifyRequest postModifyRequest = PostModifyRequest.builder()
                .title("hi")
                .body("it's me")
                .build();
        Long postId = 1l;
        String userName = "kahyun";

        when(postRepository.findById(postId))
                .thenThrow(new AppException(ErrorCode.POST_NOT_FOUND,""));

        //애러가 발생하면 true
        Assertions.assertThrows(AppException.class, () -> postService.modify(postModifyRequest,postId,userName));

    }

    @Test
    @DisplayName("수정 실패2 - 작성자!=유저")
    void modify_fail2() {
        PostModifyRequest postModifyRequest = PostModifyRequest.builder()
                .title("hi")
                .body("it's me")
                .build();
        Long postId = 1l;
        String userName = "kahyun";

        User user = User.builder()
                .userName("kh")
                .build();

        when(userRepository.findByUserName(userName))
                .thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        //애러가 발생하면 true
        Assertions.assertThrows(AppException.class, () -> postService.modify(postModifyRequest,postId,userName));

    }

    @Test
    @DisplayName("수정 실패3 - 유저 존재하지 않음")
    void modify_fail3() {
        PostModifyRequest postModifyRequest = PostModifyRequest.builder()
                .title("hi")
                .body("it's me")
                .build();
        Long postId = 1l;
        String userName = "kahyun";

        when(userRepository.findByUserName(userName))
                .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        //애러가 발생하면 true
        Assertions.assertThrows(AppException.class, () -> postService.modify(postModifyRequest,postId,userName));
    }

    @Test
    @DisplayName("삭제 실패 - 유저 존재하지 않음")
    void delete_fail() {
        Long postId = 1l;
        String userName = "kahyun";

        when(userRepository.findByUserName(userName))
                .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        //애러가 발생하면 true
        Assertions.assertThrows(AppException.class, () -> postService.delete(postId,userName));
    }

    @Test
    @DisplayName("삭제 실패2 - 포스트 존재하지 않음")
    void delete_fail2() {
        Long postId = 1l;
        String userName = "kahyun";

        when(postRepository.findById(postId))
                .thenThrow(new AppException(ErrorCode.POST_NOT_FOUND,""));

        //애러가 발생하면 true
        Assertions.assertThrows(AppException.class, () -> postService.delete(postId,userName));
    }

    @Test
    @DisplayName("포스트 상세 성공")
    void detail_success() {
        Long postId = 1l;

        User user = User.builder()
                .userName("kh")
                .build();

        Post post = new Post(1l,"title",user,"body");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        //애러가 발생하지 않으면 true
        Assertions.assertDoesNotThrow(() -> postService.detail(postId));
    }

}
