package com.mutsasns.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mutsasns.domain.entity.Post;
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
public class PostDto {
    private Long id;
    private String title;
    private String body;
    private String userName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedAt;

    public static PostDto fromEntity(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .userName(post.getUser().getUserName())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .build();
    }
    public static Page<PostDto> toDto(Page<Post> post){
        Page<PostDto> postDto = post.map(p -> PostDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .body(p.getBody())
                .userName(p.getUser().getUserName())
                .createdAt(p.getCreatedAt())
                .lastModifiedAt(p.getLastModifiedAt())
                .build());
        return postDto;
    }

}
