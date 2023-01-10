package com.mutsasns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasns.service.AlarmService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AlarmController.class)
class AlarmControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AlarmService alarmService;

    @Test
    @DisplayName("알람 목록 조회 성공")
    @WithMockUser
    void alarm_list_success() throws Exception {
        //when
        when(alarmService.alarmsList(any(),any())).thenReturn(Page.empty());
        //then
        mockMvc.perform(get("/api/v1/alarms")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("알람 목록 조회 실패 - 로그인 하지 않은 경우")
    @WithAnonymousUser
    void alarm_list_fail() throws Exception {
/*
        - `userService`에 알림 목록을 가져오는 매서드 호출 시, `Page.*empty*()`를 리턴합니다.
        - `/api/v1/users/alarm`에 GET 요청 시, 로그인 하지 않았기 때문에 권한 없음 401 오류를 리턴합니다.
*/
        //when
        when(alarmService.alarmsList(any(),any())).thenReturn(Page.empty());
        //then
        mockMvc.perform(get("/api/v1/alarms")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }
}