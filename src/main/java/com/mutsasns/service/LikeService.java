package com.mutsasns.service;

import com.mutsasns.domain.AlarmType;
import com.mutsasns.domain.entity.Alarm;
import com.mutsasns.domain.entity.Like;
import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.entity.User;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.repository.AlarmRepository;
import com.mutsasns.repository.LikeRepository;
import com.mutsasns.repository.PostRepository;
import com.mutsasns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;

    public boolean addLike(Long postId, String userName) {
        //postId 없을때 에러 처리
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        //userName 정보를 못찾을때 에러처리
        User foundUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        //좋아요 중복체크 확인
        likeRepository.findByUserAndPost(foundUser, foundPost)
                .ifPresent(like -> {
                    throw new AppException(ErrorCode.LIKE_DUPLICATION);
                });

        //좋아요 저장
        Like savedlike = Like.createLike(foundUser,foundPost);
        likeRepository.save(savedlike);
        log.info("좋아요 저장 성공");

        //알람에 저장
        if (userName != foundUser.getUserName()) {
            alarmRepository.save(Alarm.of(savedlike));
        }
        log.info("알람 저장 성공");

        return true;
    }

    public Integer cntLike(Long postsId){
        //postId 없을때 에러 처리
        Post foundPost = postRepository.findById(postsId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        return likeRepository.countByPostId(foundPost.getId());
    }
}
