package com.mutsasns.domain.entity;

import com.mutsasns.domain.UserRole;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
