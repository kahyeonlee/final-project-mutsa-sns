package com.mutsasns.service;

import com.mutsasns.domain.AlarmType;
import com.mutsasns.domain.dto.AlarmDto;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.domain.entity.Alarm;
import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.entity.User;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.repository.AlarmRepository;
import com.mutsasns.repository.CommentRepository;
import com.mutsasns.repository.PostRepository;
import com.mutsasns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;


    //알람 리스트
    public Page<AlarmDto> alarmsList(Pageable pageable,String userName) {
        //userName 정보를 못찾을때 에러처리
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        Page<Alarm>  alarms = alarmRepository.findByUserId(user.getId(), pageable);
        Page<AlarmDto> alarmDto = AlarmDto.toDto(alarms);
        return alarmDto;
    }
}
