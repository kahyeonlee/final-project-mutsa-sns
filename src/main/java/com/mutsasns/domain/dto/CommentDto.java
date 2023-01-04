package com.mutsasns.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mutsasns.domain.entity.Comment;
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
public class CommentDto {
    private Long id;
    private String comment;
    private String userName;
    private Long postId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;


    public static Page<CommentDto> toDto(Page<Comment> comment) {
        Page<CommentDto> commentDto = comment.map(c -> CommentDto.builder()
                .id(c.getId())
                .comment(c.getComment())
                .userName(c.getUser().getUserName())
                .postId(c.getPost().getId())
                .createdAt(c.getCreatedAt())
                .build());
        return commentDto;

    }
}
