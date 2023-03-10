package com.mutsasns.controller;

import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.response.PostDetailResponse;
import com.mutsasns.domain.response.Response;
import com.mutsasns.domain.dto.PostCreateRequest;
import com.mutsasns.domain.response.PostCreateResponse;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.security.core.Authentication;


import com.mutsasns.domain.dto.*;
import com.mutsasns.domain.response.*;

//http://ec2-13-124-191-48.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/#/
//http://localhost:8080/swagger-ui/#/

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "2. 포스트")
public class PostController {
    private final PostService postService;
    //게시물 등록
    @PostMapping
    @Operation(summary = "포스트 작성", description = "로그인 후, 게시물 등록")
    public Response<PostCreateResponse> create(@RequestBody PostCreateRequest dto, @ApiIgnore Authentication authentication) {
        PostDto postDto = postService.createPost(dto, authentication.getName());
        log.info("포스트 등록 성공");
        PostCreateResponse response = new PostCreateResponse("포스트 등록 완료",postDto.getId());
        return Response.success(response);
    }
    //게시물 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "포스트 삭제", description = "로그인 후, 자신이 작성한 게시물만 삭제 가능")
    public Response<PostDeleteResponse> delete(@PathVariable Long id, @ApiIgnore Authentication authentication) {
        postService.deletePost(id, authentication.getName());
        log.info("포스트 삭제 성공");
        return Response.success(new PostDeleteResponse("포스트 삭제 완료",id));
    }

    //게시물 수정
    @PutMapping("/{id}")
    @Operation(summary = "포스트 수정", description = "로그인 후, 자신이 작성한 게시물만 수정 가능")
    public Response<PostModifyResponse> modify(@RequestBody PostModifyRequest dto,@PathVariable Long id, @ApiIgnore Authentication authentication) {
        Post post = postService.modifyPost(dto,id, authentication.getName());
        PostModifyResponse response = new PostModifyResponse("포스트 수정 완료",post.getId());
        return Response.success(response);
    }

    //게시물 1개 조회
    @GetMapping("/{id}")
    @Operation(summary = "포스트 상세", description = "게시물 상세")
    public Response<PostDetailResponse> detail(@PathVariable Long id) {
        PostDto postDto = postService.detailPost(id);
        log.info("포스트 조회 성공");
        return Response.success(new PostDetailResponse(postDto.getId(), postDto.getTitle(), postDto.getBody(), postDto.getUserName(),postDto.getCreatedAt(),postDto.getLastModifiedAt()));
    }

    //게시물 리스트
    @GetMapping
    @Operation(summary = "포스트 목록 조회", description = "게시물 목록 최신순으로 20개씩 조회")
    public Response<Page<PostDto>> pageable(@PageableDefault(sort = "createdAt",size = 20,direction = Sort.Direction.DESC) Pageable pageable){
        Page<PostDto> postDto = postService.pageList(pageable);
        return Response.success(postDto);
    }

    //마이 피드 조회
    @GetMapping("/my")
    @Operation(summary = "마이 피드 조회", description = "내 게시물만 보기")
    public Response<Page<PostDto>> myPostList(@PageableDefault(sort = "createdAt",size = 20,direction = Sort.Direction.DESC)Pageable pageable,@ApiIgnore Authentication authentication){
        Page<PostDto> postDto = postService.myPostList(pageable,authentication.getName());
        return Response.success(postDto);
    }
}