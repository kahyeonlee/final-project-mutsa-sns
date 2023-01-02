package com.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasns.domain.dto.UserJoinRequest;
import com.mutsasns.domain.dto.UserLoginRequest;
import com.mutsasns.domain.response.UserJoinResponse;
import com.mutsasns.domain.response.UserLoginResponse;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;
    UserJoinRequest userJoinRequest = UserJoinRequest.builder()
            .userName("mockUserName")
            .password("mockPassword")
            .build();

    UserLoginRequest userLoginRequest = UserLoginRequest.builder()
            .userName("mockUserName")
            .password("mockPassword")
            .build();
    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join_success() throws Exception {

        when(userService.join(any())).thenReturn(new UserJoinResponse(1l, userJoinRequest.getUserName()));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    @WithMockUser
    void join_fail() throws Exception {

        when(userService.join(any()))
                .thenThrow(new AppException(ErrorCode.USERNAME_DUPLICATION));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().is(ErrorCode.USERNAME_DUPLICATION.getHttpStatus().value()));
    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    void login_success() throws Exception {
        String token = "mockToken";

        when(userService.login(any())).thenReturn(new UserLoginResponse(token));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userLoginRequest)))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").exists())
                .andExpect(jsonPath("$.result.jwt").exists());
    }

    @Test
    @DisplayName("로그인 실패 - userName 없음")
    @WithMockUser
    void login_fail_USERNAME_NOTFOUND() throws Exception {

        when(userService.login(any()))
                .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userLoginRequest)))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().is(ErrorCode.USERNAME_NOT_FOUND.getHttpStatus().value()));

    }

    @Test
    @DisplayName("로그인 실패 - password 틀림")
    @WithMockUser
    void login_fail_INVAILD_PASSWORD() throws Exception {

        when(userService.login(any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userLoginRequest)))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().is(ErrorCode.INVALID_PASSWORD.getHttpStatus().value()));

    }
}
