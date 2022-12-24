package com.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasns.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;


    @Test
    @DisplayName("포스트 작성 성공")
    @WithMockUser
    void create_success() {
    }

    @Test
    @DisplayName("포스트 작성 실패 - JWT를 Bearer Token으로 보내지 않은 경우")
    @WithMockUser
    void create_fail() {
    }

    @Test
    @DisplayName("포스트 작성 실패- JWT가 유효하지 않은 경우")
    @WithMockUser
    void create_fail2() {
    }

    @Test
    @DisplayName("포스트 삭제 성공")
    @WithMockUser
    void delete_success() {
    }

    @Test
    @DisplayName("포스트 삭제 실패 - JWT를 Bearer Token으로 보내지 않은 경우")
    @WithMockUser
    void delete_fail() {
    }

    @Test
    @DisplayName("포스트 삭제 실패 - JWT가 유효하지 않은 경우")
    @WithMockUser
    void delete_fail2() {
    }

    @Test
    @DisplayName("포스트 삭제 실패 - JWT가 유효하지 않은 경우")
    @WithMockUser
    void delete_fail3() {
    }


}