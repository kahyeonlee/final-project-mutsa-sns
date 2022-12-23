package com.mutsasns.controller;

import com.mutsasns.domain.response.Response;
import com.mutsasns.domain.dto.PostCreateRequest;
import com.mutsasns.domain.response.PostCreateResponse;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping ("/posts")
    public Response<PostCreateResponse> list(@RequestBody PostCreateRequest dto,@ApiIgnore Authentication authentication){
        PostDto postDto = postService.create(dto,authentication.getName());
        PostCreateResponse response = new PostCreateResponse(postDto.getId(),"포스트 등록 완료");
        return Response.success(response);
    }

}
