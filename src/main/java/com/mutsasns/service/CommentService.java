package com.mutsasns.service;

import com.mutsasns.domain.AlarmType;
import com.mutsasns.domain.dto.CommentDto;
import com.mutsasns.domain.dto.CommentRequest;
import com.mutsasns.domain.dto.PostDto;
import com.mutsasns.domain.entity.Alarm;
import com.mutsasns.domain.entity.Comment;
import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.entity.User;
import com.mutsasns.domain.response.CommentCreateResponse;
import com.mutsasns.domain.response.CommentModifyResponse;
import com.mutsasns.exception.AppException;
import com.mutsasns.exception.ErrorCode;
import com.mutsasns.repository.AlarmRepository;
import com.mutsasns.repository.CommentRepository;
import com.mutsasns.repository.PostRepository;
import com.mutsasns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;

    /*
    댓글작성
    */
    public CommentCreateResponse createComment(CommentRequest dto, Long postId, String userName) {
        //userName 정보를 못찾을때 에러처리
        User foundUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        //postId 없을때 에러 처리
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        //저장
        Comment comment = Comment.builder()
                .comment(dto.getComment())
                .user(foundUser)
                .post(foundPost)
                .build();

        Comment savedComment = commentRepository.save(comment);
        log.info("저장성공");

        //알람 저장
        if (userName != foundUser.getUserName()) {
            alarmRepository.save(Alarm.of(savedComment));
        }
        log.info("알람 저장 성공");

        return CommentCreateResponse.of(savedComment);
    }

    /*
    댓글수정
    */
    public CommentModifyResponse modifyComment(CommentRequest dto, Long postId, Long commentId, String userName) {
        //commentId가 없을 때
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        //postId 없을때 에러 처리
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        //userName 정보를 못찾을때 에러처리
        User foundUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        //userName이 일치하지 않을때 에러 처리
        if (!Objects.equals(foundPost.getUser().getUserName(), userName)) {
            throw new AppException(ErrorCode.INVALID_PERMISSION);
        }

        //수정
        foundComment.setComment(dto.getComment());

        Comment savedComment = commentRepository.save(foundComment);

        return CommentModifyResponse.of(savedComment);
    }

    /*
    댓글 삭제
    */
    public boolean deleteComment(Long postId,Long commentId,String userName) {

        //commentId가 없을 때
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        //postId 없을때 에러 처리
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        //userName 정보를 못찾을때 에러처리
        User foundUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        //userName이 일치하지 않을때 에러 처리
        if (!Objects.equals(foundPost.getUser().getUserName(), userName)) {
            throw new AppException(ErrorCode.INVALID_PERMISSION);
        }

        //삭제
        commentRepository.delete(foundComment);
        log.info("삭제 서비스 성공");
        return true;
    }
    /*
    댓글 목록 조회
     */
    public Page<CommentDto> pageList(Pageable pageable){
        Page<Comment>  comment = commentRepository.findAll(pageable);
        Page<CommentDto> commentDto = CommentDto.toDto(comment);
        return commentDto;
    }

}
