package com.example.springredis.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.springredis.config.LocalCacheConfig;
import com.example.springredis.config.RedisConfig;
import com.example.springredis.dto.PostDto;
import com.example.springredis.dto.PostRequestDto;
import com.example.springredis.entity.Post;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
@Rollback
class BlogServiceTest {

    @Autowired
    private BlogService blogService;

//    @Autowired
//    private CacheManager cacheManager;

//    @Autowired
//    private RedisConfig redisConfig;

    private Long latestPostId;

    @BeforeAll
    @Transactional
    void setup() {
        IntStream.range(0, 5).forEach((i) -> {
            PostRequestDto postRequestDto = new PostRequestDto();
            postRequestDto.setTitle("제목");
            postRequestDto.setContent("내용");
            latestPostId = blogService.createPost(postRequestDto).getId();
        });
    }

    @Test
    @DisplayName("초기화 테스트")
    void 초기화_테스트() {
        System.out.println(latestPostId);
        PostDto post = blogService.getPost(latestPostId);
        assertThat(post.getContent()).isEqualTo("내용");
        assertThat(post.getTitle()).isEqualTo("제목");
    }

//    @Test
//    @DisplayName("캐시 출력")
//    void 캐시_출력() {
//        ConcurrentHashMap map = (ConcurrentHashMap) cacheManager.getCache("exampleStore")
//            .getNativeCache();
//        map.keySet().forEach((key) -> System.out.println(key));
//    }

    @Test
    @DisplayName("레디스 캐시 출력")
    void 레디스_캐시_출력() {
    }
}