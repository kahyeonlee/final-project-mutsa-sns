package com.mutsasns.controller;

import com.mutsasns.domain.dto.AlarmDto;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.domain.entity.Alarm;
import com.mutsasns.domain.response.Response;
import com.mutsasns.service.AlarmService;
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
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/alarms")
    public Response<Page<AlarmDto>> pageable(@ApiIgnore Authentication authentication, @PageableDefault(sort = "createdAt",size = 20,direction = Sort.Direction.DESC) Pageable pageable){
        Page<AlarmDto> alarms = alarmService.alarmsList(pageable, authentication.getName());
        return Response.success(alarms);
    }
}
