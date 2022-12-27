package com.mutsasns.controller;

import com.mutsasns.domain.response.PostDetailResponse;
import com.mutsasns.domain.response.Response;
import com.mutsasns.domain.dto.PostCreateRequest;
import com.mutsasns.domain.response.PostCreateResponse;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.security.core.Authentication;


import com.mutsasns.domain.dto.*;
import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.response.*;


@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    //게시물 등록
    @PostMapping
    public Response<PostCreateResponse> create(@RequestBody PostCreateRequest dto, @ApiIgnore Authentication authentication) {
        PostDto postDto = postService.create(dto, authentication.getName());
        PostCreateResponse response = new PostCreateResponse("포스트 등록 완료",postDto.getId());
        return Response.success(response);
    }
    //게시물 삭제
    @DeleteMapping("/{postId}")
    public Response<PostDeleteResponse> delete(@PathVariable Long postId, @ApiIgnore Authentication authentication) {
        postService.delete(postId, authentication.getName());
        return Response.success(new PostDeleteResponse("포스트 삭제 완료",postId));
    }
    //게시물 수정
    @PutMapping("/{id}")
    public Response<PostModifyResponse> create(@RequestBody PostModifyRequest dto,Long postId, @ApiIgnore Authentication authentication) {
        Post post = postService.modify(dto,postId, authentication.getName());
        PostModifyResponse response = new PostModifyResponse("포스트 수정 완료",post.getId());
        return Response.success(response);
    }
    //게시물 1개 조회
    @GetMapping("/{postId}")
    public Response<PostDetailResponse> delete(@PathVariable Long postId) {
        PostDto postDto = postService.detail(postId);
        return Response.success(new PostDetailResponse(postDto.getId(), postDto.getTitle(), postDto.getBody(), postDto.getUserName(),postDto.getCreatedAt(),postDto.getLastModifiedAt()));
    }
    //게시물 리스트
    @GetMapping
    public Response<Page<PostDto>> pageable(@PageableDefault(sort = "createdAt",size = 20,direction = Sort.Direction.DESC) Pageable pageable){
        Page<PostDto> postDto = postService.pageList(pageable);
        return Response.success(postDto);
    }
}