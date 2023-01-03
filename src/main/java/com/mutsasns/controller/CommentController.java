package com.mutsasns.controller;

import com.mutsasns.domain.dto.CommentCreateRequest;
import com.mutsasns.domain.dto.CommentDto;
import com.mutsasns.domain.response.CommentCreateResponse;
import com.mutsasns.domain.response.PostCreateResponse;
import com.mutsasns.domain.response.Response;
import com.mutsasns.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

//http://ec2-13-124-191-48.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/#/
//http://localhost:8080/swagger-ui/#/

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/{postId}/comments")
    public Response<CommentCreateResponse> create(@RequestBody CommentCreateRequest dto,@PathVariable long postId, @ApiIgnore Authentication authentication){
        log.info("댓글 작성자 이름 {}",authentication.getName());
        return Response.success(commentService.createComment(dto, postId, authentication.getName()));
    }
}
