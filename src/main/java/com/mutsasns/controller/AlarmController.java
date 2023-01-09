package com.mutsasns.controller;

import com.mutsasns.domain.dto.AlarmDto;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.domain.entity.Alarm;
import com.mutsasns.domain.response.Response;
import com.mutsasns.service.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = "5. 알람")
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/alarms")
    @Operation(summary = "알람 조회", description = "로그인 후, 내 게시물의 댓글, 좋아요 알람 조회")
    public Response<Page<AlarmDto>> pageable(@ApiIgnore Authentication authentication, @PageableDefault(sort = "createdAt",size = 20,direction = Sort.Direction.DESC) Pageable pageable){
        Page<AlarmDto> alarms = alarmService.alarmsList(pageable, authentication.getName());
        return Response.success(alarms);
    }
}
