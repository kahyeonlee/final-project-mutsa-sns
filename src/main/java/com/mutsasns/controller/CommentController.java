package com.mutsasns.controller;

import com.mutsasns.domain.dto.CommentDto;
import com.mutsasns.domain.dto.CommentRequest;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.domain.entity.Comment;
import com.mutsasns.domain.response.CommentDeleteResponse;
import com.mutsasns.domain.response.CommentCreateResponse;
import com.mutsasns.domain.response.CommentModifyResponse;
import com.mutsasns.domain.response.Response;
import com.mutsasns.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

//http://ec2-13-124-191-48.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/#/
//http://localhost:8080/swagger-ui/#/

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "3. 댓글")
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/{postId}/comments")
    @Operation(summary = "댓글 작성", description = "로그인 후, 댓글 작성")
    public Response<CommentCreateResponse> create(@RequestBody CommentRequest dto, @PathVariable Long postId, @ApiIgnore Authentication authentication){
        log.info("댓글 작성자 이름 {}",authentication.getName());
        return Response.success(commentService.createComment(dto, postId, authentication.getName()));
    }
    //댓글 수정
    @PutMapping("/{postId}/comments/{id}")
    @Operation(summary = "댓글 수정", description = "로그인 후, 댓글 수정")
    public Response<CommentModifyResponse> modify(@RequestBody CommentRequest dto, @PathVariable Long postId, @PathVariable Long id, @ApiIgnore Authentication authentication){
        log.info("댓글 수정자 이름 {}",authentication.getName());
        return Response.success(commentService.modifyComment(dto, postId, id,authentication.getName()));
    }

    //댓글 삭제
    @DeleteMapping("/{postsId}/comments/{id}")
    @Operation(summary = "댓글 삭제", description = "로그인 후, 댓글 삭제")
    public Response<CommentDeleteResponse> delete(@PathVariable Long postsId,@PathVariable Long id, @ApiIgnore Authentication authentication){
        log.info("댓글 삭제하는 user 이름 {}",authentication.getName());
        log.info("postsId {}",postsId);
        commentService.deleteComment(postsId, id, authentication.getName());
        return Response.success(new CommentDeleteResponse("댓글 삭제 완료",id));
    }

    //댓글 조회
    @GetMapping("{postId}/comments")
    @Operation(summary = "댓글 조회", description = "로그인 후, 댓글 조회")
    public Response<Page<CommentDto>> pageable(@PageableDefault(sort = "createdAt",size = 20,direction = Sort.Direction.DESC) Pageable pageable){
        Page<CommentDto> commentDto = commentService.pageList(pageable);
        return Response.success(commentDto);
    }


}
