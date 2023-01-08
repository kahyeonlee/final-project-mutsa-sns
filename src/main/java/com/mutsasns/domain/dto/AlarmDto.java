package com.mutsasns.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mutsasns.domain.AlarmType;
import com.mutsasns.domain.entity.Alarm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmDto {

    private Long id;
    private AlarmType alarmType;
    private  Long fromUserId;
    private Long targetId;
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public static Page<AlarmDto> toDto(Page<Alarm> alarms){
        Page<AlarmDto> alarmDto = alarms.map(p -> AlarmDto.builder()
                .id(p.getId())
                .alarmType(p.getAlarmType())
                .fromUserId(p.getFromUserId())
                .createdAt(p.getCreatedAt())
                .targetId(p.getTargetId())
                .text(p.getText())
                .build());
        return alarmDto;
    }
}
