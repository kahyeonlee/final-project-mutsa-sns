package com.mutsasns.controller;

import com.mutsasns.domain.response.Response;
import com.mutsasns.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Slf4j
@Api(tags = "4. 좋아요")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/likes")
    @Operation(summary = "좋아요 누르기", description = "게시물의 좋아요 누르기")
    public Response<String> like(@PathVariable Long postId, @ApiIgnore Authentication authentication){
        log.info("hello" );
        likeService.addLike(postId, authentication.getName());
        return Response.success("좋아요를 눌렀습니다.");
    }

    @GetMapping("/{postsId}/likes")
    @Operation(summary = "좋아요 count", description = "게시물의 좋아요 수")
    public Response<Integer> likeCount(@PathVariable Long postsId){
        return Response.success(likeService.cntLike(postsId));
    }
}
