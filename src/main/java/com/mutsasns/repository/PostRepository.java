package com.mutsasns.repository;

import com.mutsasns.domain.entity.Post;
import com.mutsasns.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findById(Long id);
}
