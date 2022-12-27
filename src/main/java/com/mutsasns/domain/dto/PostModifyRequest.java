package com.mutsasns.domain.dto;

import io.swagger.annotations.Info;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostModifyRequest {
    private String title;
    private String body;

}
