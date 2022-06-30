package com.example.springredis.repository;

import com.example.springredis.entity.Hit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitRepository extends JpaRepository<Hit, Long> {

    Hit findByPostId(Long postId);
}
