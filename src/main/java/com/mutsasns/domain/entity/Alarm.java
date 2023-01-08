package com.mutsasns.domain.entity;

import com.mutsasns.domain.AlarmType;
import com.mutsasns.domain.UserRole;
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

}
