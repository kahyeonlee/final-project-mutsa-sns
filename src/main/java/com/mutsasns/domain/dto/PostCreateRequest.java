package com.mutsasns.domain.dto;

import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateRequest {
    private String title;
    private String body;

}
