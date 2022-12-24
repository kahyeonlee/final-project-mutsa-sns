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


    public static Post of(String title, String body, User user) {
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setUser(user);
        return post;
    }

}
