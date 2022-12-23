package com.mutsasns.domain.dto;

import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreateRequest {
    private String title;
    private String body;

}
