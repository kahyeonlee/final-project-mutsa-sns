package com.mutsasns.repository;

import com.mutsasns.domain.entity.Alarm;
import com.mutsasns.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlarmRepository  extends JpaRepository<Alarm,Long> {
    Page<Alarm> findByUserId(Long id, Pageable pageable);
}
