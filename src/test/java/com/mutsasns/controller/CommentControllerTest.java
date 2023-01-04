package com.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasns.domain.dto.CommentRequest;
import com.mutsasns.domain.response.CommentResponse;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CommentService commentService;
@Nested
@DisplayName("댓글작성")
class createComment {
    @Test
    @DisplayName("댓글 작성 성공")
    @WithMockUser
    void comment_success() throws Exception {
        //given
        CommentRequest request = CommentRequest.builder()
                .comment("mockComment")
                .build();

        //when
        when(commentService.createComment(any(), any(), any())).thenReturn(mock(CommentResponse.class));

        //then
        mockMvc.perform(post("/api/v1/posts/1/comments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").exists())
                .andExpect(jsonPath("$.result.id").exists())
                .andExpect(jsonPath("$.result.comment").exists())
                .andExpect(jsonPath("$.result.userName").exists())
                .andExpect(jsonPath("$.result.postId").exists())
                .andExpect(jsonPath("$.result.createdAt").exists());
    }

    @Test
    @DisplayName("댓글 실패 - 로그인 하지 않은 경우")
    void comment_fail() throws Exception {
        //given
        CommentRequest request = CommentRequest.builder()
                .comment("mockComment")
                .build();

        //when
        when(commentService.createComment(any(), any(), any())).thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND));

        //then
        mockMvc.perform(post("/api/v1/posts/1/comments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("댓글 성공 - 게시물이 존재하지 않은 경우")
    void comment_fail2() throws Exception {
        //given
        CommentRequest request = CommentRequest.builder()
                .comment("mockComment")
                .build();

        //when
        when(commentService.createComment(any(), any(), any())).thenThrow(new AppException(ErrorCode.POST_NOT_FOUND));

        //then
        mockMvc.perform(post("/api/v1/posts/1/comments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}

    @Nested
    @DisplayName("댓글수정")
    class ModifyComment {
        @Test
        @DisplayName("댓글 수정 성공")
        @WithMockUser
        void comment_success() throws Exception {
            //given
            CommentRequest request = CommentRequest.builder()
                    .comment("mockComment")
                    .build();

            //when
            when(commentService.modifyComment(any(),any(), any(), any())).thenReturn(mock(CommentResponse.class));

            //then
            mockMvc.perform(post("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.resultCode").exists())
                    .andExpect(jsonPath("$.result.id").exists())
                    .andExpect(jsonPath("$.result.comment").exists())
                    .andExpect(jsonPath("$.result.userName").exists())
                    .andExpect(jsonPath("$.result.postId").exists())
                    .andExpect(jsonPath("$.result.createdAt").exists())
                    .andExpect(jsonPath("$.result.lastModifiedAt").exists());
        }

        @Test
        @DisplayName("댓글 수정 실패 - 인증실패")
        void comment_fail() throws Exception {
            //given
            CommentRequest request = CommentRequest.builder()
                    .comment("mockComment")
                    .build();

            //when
            when(commentService.modifyComment(any(),any(), any(), any())).thenThrow(new AppException(ErrorCode.INVALID_TOKEN));

            //then
            mockMvc.perform(post("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("댓글 수정 실패 - Post 없는 경우")
        void comment_fail2() throws Exception {
            //given
            CommentRequest request = CommentRequest.builder()
                    .comment("mockComment")
                    .build();

            //when
            when(commentService.modifyComment(any(),any(), any(), any())).thenThrow(new AppException(ErrorCode.POST_NOT_FOUND));

            //then
            mockMvc.perform(post("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("댓글 수정 실패 - 작성자 불일치")
        void comment_fail3() throws Exception {
            //given
            CommentRequest request = CommentRequest.builder()
                    .comment("mockComment")
                    .build();

            //when
            when(commentService.modifyComment(any(),any(), any(), any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION));

            //then
            mockMvc.perform(post("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }
        @Test
        @DisplayName("댓글 수정 실패 - DB에러")
        void comment_fail4() throws Exception {
            //given
            CommentRequest request = CommentRequest.builder()
                    .comment("mockComment")
                    .build();

            //when
            when(commentService.modifyComment(any(),any(), any(), any())).thenThrow(new AppException(ErrorCode.DATABASE_ERROR));

            //then
            mockMvc.perform(post("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.DATABASE_ERROR.getHttpStatus().value()));

        }

    }

}