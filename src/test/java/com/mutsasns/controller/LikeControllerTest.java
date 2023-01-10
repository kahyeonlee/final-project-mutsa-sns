package com.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.service.LikeService;
import com.mutsasns.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LikeController.class)
class LikeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    LikeService likeService;

    @Test
    @DisplayName("좋아요 누르기 성공")
    @WithMockUser
    void like_push_success() throws Exception {
        //when
        when(likeService.addLike(any(), any())).thenReturn(true);
        //then
        mockMvc.perform(post("/api/v1/posts/1/likes")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("좋아요 누르기 실패 - 로그인 하지 않은 경우")
    @WithAnonymousUser
    void like_push_fail() throws Exception {
        //when
        when(likeService.addLike(any(), any())).thenReturn(true);
        //then
        mockMvc.perform(post("/api/v1/posts/1/likes")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    @Test
    @DisplayName("좋아요 누르기 실패 - 해당 post가 없는 경우")
    @WithMockUser
    void like_push_fail2() throws Exception {
        //when
        when(likeService.addLike(any(), any())).thenThrow(new AppException(ErrorCode.POST_NOT_FOUND));
        //then
        mockMvc.perform(post("/api/v1/posts/1/likes")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is(ErrorCode.POST_NOT_FOUND.getHttpStatus().value()));
    }
}