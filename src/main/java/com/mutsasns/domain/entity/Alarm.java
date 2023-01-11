package com.mutsasns.domain.entity;

import com.mutsasns.domain.AlarmType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE alarm SET deleted = true WHERE id = ?")
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //알람 받는 유저
    private User user;

    private Long fromUserId; //알람 발생시키는 유저
    private Long targetId; //알람이 발생된 post의 id

    private String text;

    public static Alarm of(Like savedLike) {
        return Alarm.builder()
                .fromUserId(savedLike.getUser().getId())
                .targetId(savedLike.getPost().getId())
                .alarmType(AlarmType.NEW_LIKE_ON_POST)
                .text(AlarmType.NEW_LIKE_ON_POST.getText())
                .user(savedLike.getUser())
                .build();
    }

    public static Alarm of(Comment savedComment) {
        return Alarm.builder()
                .fromUserId(savedComment.getUser().getId())
                .targetId(savedComment.getPost().getId())
                .alarmType(AlarmType.NEW_COMMENT_ON_POST)
                .text(AlarmType.NEW_COMMENT_ON_POST.getText())
                .user(savedComment.getUser())
                .build();
    }

}
