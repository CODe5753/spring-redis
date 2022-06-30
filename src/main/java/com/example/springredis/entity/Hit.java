package com.example.springredis.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;


//@RedisHash(value = "hit", timeToLive = 3600)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer count;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
