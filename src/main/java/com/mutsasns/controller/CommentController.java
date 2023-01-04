package com.mutsasns.controller;

import com.mutsasns.domain.dto.CommentRequest;
import com.mutsasns.domain.response.CommentDeleteResponse;
import com.mutsasns.domain.response.CommentCreateResponse;
import com.mutsasns.domain.response.CommentModifyResponse;
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
    public Response<CommentCreateResponse> create(@RequestBody CommentRequest dto, @PathVariable Long postId, @ApiIgnore Authentication authentication){
        log.info("댓글 작성자 이름 {}",authentication.getName());
        return Response.success(commentService.createComment(dto, postId, authentication.getName()));
    }
    //댓글 수정
    @PutMapping("/{postId}/comments/{id}")
    public Response<CommentModifyResponse> modify(@RequestBody CommentRequest dto, @PathVariable Long postId, @PathVariable Long id, @ApiIgnore Authentication authentication){
        log.info("댓글 수정자 이름 {}",authentication.getName());
        return Response.success(commentService.modifyComment(dto, postId, id,authentication.getName()));
    }

    //댓글 삭제
    @DeleteMapping("/{postsId}/comments/{id}")
    public Response<CommentDeleteResponse> delete(@PathVariable Long postsId,@PathVariable Long id, @ApiIgnore Authentication authentication){
        log.info("댓글 수정자 이름 {}",authentication.getName());
        log.info("postsId {}",postsId);
        commentService.deleteComment(postsId, id, authentication.getName());
        return Response.success(new CommentDeleteResponse("댓글 삭제 완료",id));
    }

}
