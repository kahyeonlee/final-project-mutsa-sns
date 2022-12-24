package com.mutsasns.controller;

import com.mutsasns.domain.dto.*;
import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.response.*;
import com.mutsasns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.security.core.Authentication;

import javax.persistence.PostUpdate;


@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    //게시물 등록
    @PostMapping
    public Response<PostCreateResponse> create(@RequestBody PostCreateRequest dto, @ApiIgnore Authentication authentication) {
        PostDto postDto = postService.create(dto, authentication.getName());
        PostCreateResponse response = new PostCreateResponse(postDto.getId(), "포스트 등록 완료");
        return Response.success(response);
    }
    //게시물 삭제
    @DeleteMapping("/{postId}")
    public Response<PostDeleteResponse> delete(@PathVariable Long postId, @ApiIgnore Authentication authentication) {
        postService.delete(postId, authentication.getName());
        return Response.success(new PostDeleteResponse(postId, "포스트 삭제 완료"));
    }
    //게시물 수정
    @PutMapping("/{id}")
    public Response<PostModifyResponse> create(@RequestBody PostModifyRequest dto,Long postId, @ApiIgnore Authentication authentication) {
        Post post = postService.modify(dto,postId, authentication.getName());
        PostModifyResponse response = new PostModifyResponse(post.getId(), "포스트 수정 완료");
        return Response.success(response);
    }


}