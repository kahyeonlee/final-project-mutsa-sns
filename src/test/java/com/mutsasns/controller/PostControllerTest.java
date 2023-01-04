package com.mutsasns.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasns.domain.dto.PostCreateRequest;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.domain.dto.PostModifyRequest;
import com.mutsasns.domain.entity.Post;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PostService postService;

    /*----------------------------------------포스트 작성-------------------------------------------------- */
    @Nested
    @DisplayName("포스트 작성")
    class createPost {
        @Test
        @DisplayName("포스트 작성 성공")
        @WithMockUser
        void create_success() throws Exception {
            PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                    .title("hi")
                    .body("it's me")
                    .build();

            when(postService.createPost(any(), any())).thenReturn(mock(PostDto.class));

            mockMvc.perform(post("/api/v1/posts")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postCreateRequest)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result.message").exists())
                    .andExpect(jsonPath("$.result.postId").exists());
        }

        @Test
        @DisplayName("포스트 작성 실패 - 로그인 하지 않은 경우")
        @WithAnonymousUser
        void create_fail() throws Exception {
            PostCreateRequest postCreateRequest = PostCreateRequest.builder()
                    .title("hi")
                    .body("it's me")
                    .build();

            when(postService.createPost(any(), any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION));

            mockMvc.perform(post("/api/v1/posts")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postCreateRequest)))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }
    }

    /*--------------------------------포스트 삭제-------------------------------------------------- */
    @Nested
    @DisplayName("포스트 실패")
    class deletePost {
        @Test
        @DisplayName("포스트 삭제 성공")
        @WithMockUser
        void delete_success() throws Exception {

            when(postService.deletePost(any(), any())).thenReturn(true);

            mockMvc.perform(delete("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect((jsonPath("$.result.postId").exists()))
                    .andExpect((jsonPath("$.result.message").exists()))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("포스트 삭제 실패 - 인증 실패")
        @WithAnonymousUser
        void delete_fail() throws Exception {
            when(postService.deletePost(any(), any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION));

            mockMvc.perform(delete("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("포스트 삭제 실패 - 작성자 불일치")
        @WithMockUser
        void delete_fail2() throws Exception {

            when(postService.deletePost(any(), any())).thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND));

            mockMvc.perform(delete("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.USERNAME_NOT_FOUND.getHttpStatus().value()));

        }

        @Test
        @DisplayName("포스트 삭제 실패 - 데이터베이스 에러(게시글 못찾을때)")
        @WithMockUser
        void delete_fail3() throws Exception {
            when(postService.deletePost(any(), any())).thenThrow(new AppException(ErrorCode.POST_NOT_FOUND));

            mockMvc.perform(delete("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.POST_NOT_FOUND.getHttpStatus().value()));

        }
    }

    /*----------------------------------------포스트 수정-------------------------------------------------- */
    @Nested
    @DisplayName("포스트 수정")
    class modifyPost {
        @Test
        @DisplayName("포스트 수정 성공")
        @WithMockUser
        void modify_success() throws Exception {

            PostModifyRequest postModifyRequest = PostModifyRequest.builder()
                    .title("hi")
                    .body("it's me")
                    .build();

            when(postService.modifyPost(any(),any(), any())).thenReturn(mock(Post.class));

            mockMvc.perform(put("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postModifyRequest)))
                    .andDo(print())
                    .andExpect((jsonPath("$.resultCode").exists()))
                    .andExpect((jsonPath("$.result.message").exists()))
                    .andExpect((jsonPath("$.result.postId").exists()))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("포스트 수정 실패1 - 인증 실패")
        @WithAnonymousUser
        void modify_fail() throws Exception {
            PostModifyRequest postModifyRequest = PostModifyRequest.builder()
                    .title("hi")
                    .body("it's me")
                    .build();

            when(postService.modifyPost(any(), any(), any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION));

            mockMvc.perform(put("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postModifyRequest)))
                    .andDo(print())
                    .andExpect(status().isUnauthorized());

        }

        @Test
        @DisplayName("포스트 수정 실패2 - 작성자 불일치")
        @WithMockUser
        void modify_fail2() throws Exception {
            PostModifyRequest postModifyRequest = PostModifyRequest.builder()
                    .title("hi")
                    .body("it's me")
                    .build();


            when(postService.modifyPost(any(), any(), any())).thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND));

            mockMvc.perform(put("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postModifyRequest)))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.USERNAME_NOT_FOUND.getHttpStatus().value()));


        }

        @Test
        @DisplayName("포스트 수정 실패3 - 데이터베이스 에러(post 못찾을때)")
        @WithMockUser
        void modify_fail3() throws Exception {
            PostModifyRequest postModifyRequest = PostModifyRequest.builder()
                    .title("hi")
                    .body("it's me")
                    .build();

            when(postService.modifyPost(any(), any(), any())).thenThrow(new AppException(ErrorCode.POST_NOT_FOUND));

            mockMvc.perform(put("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postModifyRequest)))
                    .andDo(print())
                    .andExpect(status().is(ErrorCode.POST_NOT_FOUND.getHttpStatus().value()));

        }
    }

    /*----------------------------------------포스트 상세-------------------------------------------------- */
    @Nested
    @DisplayName("포스트 조회")
    class detailPost {
        @Test    // id, title, body, userName
        @DisplayName("포스트 상세 성공 - 항목 4개 조회")
        @WithMockUser
        void detail_success() throws Exception {

            PostDto postDto = PostDto.builder()
                    .id(new Long(1))
                    .title("hi")
                    .body("it's me")
                    .userName("kh")
                    .build();

            when(postService.detailPost(any())).thenReturn(postDto);

            mockMvc.perform(get("/api/v1/posts/1")
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result.id").exists())
                    .andExpect(jsonPath("$.result.title").exists())
                    .andExpect(jsonPath("$.result.body").exists())
                    .andExpect(jsonPath("$.result.userName").exists());
        }

        /*----------------------------------------포스트 페이징-------------------------------------------------- */
        @Test
        @DisplayName("포스트 리스트 성공 - 날찌가 최신순")
        @WithMockUser
        void list_success() throws Exception {

            mockMvc.perform(get("/api/v1/posts")
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }
}