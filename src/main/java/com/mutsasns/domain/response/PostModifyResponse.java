package com.mutsasns.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostModifyResponse {
    private Long postId;
    private String message;
}