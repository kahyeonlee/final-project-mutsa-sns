package com.mutsasns.controller;

import com.mutsasns.domain.response.Response;
import com.mutsasns.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Slf4j
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/likes")
    public Response<String> like(@PathVariable Long postId, @ApiIgnore Authentication authentication){
        log.info("hello" );
        likeService.addLike(postId, authentication.getName());
        return Response.success("좋아요를 눌렀습니다.");
    }
}
