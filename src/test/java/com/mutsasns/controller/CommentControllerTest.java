package com.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasns.domain.UserRole;
import com.mutsasns.domain.dto.CommentRequest;
import com.mutsasns.domain.entity.Comment;
import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.entity.User;
import com.mutsasns.domain.response.CommentCreateResponse;
import com.mutsasns.domain.response.CommentModifyResponse;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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


    CommentRequest request = CommentRequest.builder()
            .comment("mockComment")
            .build();
    User user = new User(1l,"userName","password", UserRole.USER);
    Post post = Post.builder()
            .id(1l)
            .title("title")
            .user(user)
            .body("body")
            .build();
    Comment comment = Comment.builder()
            .id(1l)
            .comment("comment")
            .user(user)
            .post(post)
            .build();

    /*==========================================?????? ?????? =====================================================*/
    @Nested
    @DisplayName("????????????")
    class createComment {

        @Test
        @DisplayName("?????? ?????? ??????")
        @WithMockUser
        void create_success() throws Exception {
            //given
            //when
            when(commentService.createComment(any(), any(), any())).thenReturn(mock(CommentCreateResponse.class));

            //then
            mockMvc.perform(post("/api/v1/posts/1/comments")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(comment)))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("?????? ?????? ?????? - ????????? ?????? ?????? ??????")
        void comment_fail() throws Exception {
            //given
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
        @DisplayName("?????? ?????? ?????? - ???????????? ???????????? ?????? ??????")
        void comment_fail2() throws Exception {
            //given
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

    /*==========================================?????? ?????? =====================================================*/


    @Nested
    @DisplayName("????????????")
    class ModifyComment {
        @Test
        @DisplayName("?????? ?????? ??????")
        @WithMockUser
        void comment_success() throws Exception {
            //given
            //when
            when(commentService.modifyComment(any(), any(), any(), any())).thenReturn(mock(CommentModifyResponse.class));

            //then
            mockMvc.perform(put("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.resultCode").exists());
        }

        @Test
        @DisplayName("?????? ?????? ?????? - ?????? ??????")
        @WithAnonymousUser
        void comment_fail() throws Exception {
            //given
            //when
            when(commentService.modifyComment(any(), any(), any(), any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION));

            //then
            mockMvc.perform(put("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("?????? ?????? ?????? - Post ?????? ??????")
        void comment_fail2() throws Exception {
            //given
            //when
            when(commentService.modifyComment(any(), any(), any(), any())).thenThrow(new AppException(ErrorCode.POST_NOT_FOUND));

            //then
            mockMvc.perform(put("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.POST_NOT_FOUND.getHttpStatus().value()));
        }

        @Test
        @DisplayName("?????? ?????? ?????? - ????????? ?????????")
        @WithMockUser
        void comment_fail3() throws Exception {
            //given
            //when
            when(commentService.modifyComment(any(), any(), any(), any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION));

            //then
            mockMvc.perform(put("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getHttpStatus().value()));
        }

        @Test
        @DisplayName("?????? ?????? ?????? - DB??????")
        void comment_fail4() throws Exception {
            //given
            //when
            when(commentService.modifyComment(any(), any(), any(), any())).thenThrow(new AppException(ErrorCode.DATABASE_ERROR));

            //then
            mockMvc.perform(put("/api/v1/posts/1/comments/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.DATABASE_ERROR.getHttpStatus().value()));
        }
    }

    /*==========================================?????? ?????? =====================================================*/
    @Nested
    @DisplayName("?????? ??????")
    class CommentDelete {
        @Test
        @DisplayName("?????? ?????? ??????")
        @WithMockUser
        void comment_delete_success() throws Exception {

            when(commentService.deleteComment(any(), any(), any())).thenReturn(true);

            mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(request)))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("?????? ?????? ?????? - ?????? ??????")
        @WithAnonymousUser
        void comment_delete_fail() throws Exception {
            when(commentService.deleteComment(any(), any(), any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION));

            mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("?????? ?????? ?????? - ????????? ?????????")
        @WithMockUser
        void comment_delete_fail2() throws Exception {
            when(commentService.deleteComment(any(), any(), any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION));

            mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getHttpStatus().value()));
        }

        @Test
        @DisplayName("?????? ?????? ?????? - ?????????????????? ??????")
        @WithMockUser
        void comment_delete_fail3() throws Exception {
            when(commentService.deleteComment(any(), any(), any())).thenThrow(new AppException(ErrorCode.DATABASE_ERROR));

            mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.DATABASE_ERROR.getHttpStatus().value()));
        }
    }

    /*==========================================?????? ????????? =====================================================*/

    @Test
    @DisplayName("?????? ????????? ?????? ??????")
    @WithMockUser
    void comment_list_success() throws Exception {

        //given
        //when
        when(commentService.pageList(any())).thenReturn(Page.empty());
        //then
        mockMvc.perform(get("/api/v1/posts/1/comments")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("?????? ?????? ?????? ?????? - ????????????????????? ??????")
    @WithAnonymousUser
    void comment_list_fail() throws Exception {

        //given
        //when
        when(commentService.pageList(any())).thenReturn(Page.empty());
        //then
        mockMvc.perform(get("/api/v1/posts/1/comments")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}