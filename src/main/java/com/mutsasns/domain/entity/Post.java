package com.mutsasns.domain.entity;

import com.mutsasns.domain.dto.PostDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 300)
    private String body;

    public void modify(String modifyTitle,String modifyBody){
        this.title = modifyTitle;
        this.body = modifyBody;
    }

}
