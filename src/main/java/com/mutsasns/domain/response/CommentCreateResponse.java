package com.mutsasns.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mutsasns.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentCreateResponse {
    private Long id;
    private String comment;
    private String userName;
    private Long postId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedAt;


    public static CommentCreateResponse of(Comment c) {
        return CommentCreateResponse.builder()
                .id(c.getId())
                .comment(c.getComment())
                .userName(c.getUser().getUserName())
                .postId(c.getPost().getId())
                .createdAt(c.getCreatedAt())
                .build();
    }
}
