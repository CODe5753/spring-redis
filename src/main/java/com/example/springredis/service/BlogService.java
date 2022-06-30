package com.example.springredis.service;

import com.example.springredis.dto.PostDto;
import com.example.springredis.dto.PostRequestDto;
import com.example.springredis.entity.Hit;
import com.example.springredis.entity.Post;
import com.example.springredis.repository.HitRepository;
import com.example.springredis.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class BlogService {

    private final PostRepository postRepository;
    private final HitRepository hitRepository;

    @Cacheable(key = "#postId")
    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(IllegalArgumentException::new);
        Hit hit = hitRepository.findByPostId(postId);
        return PostDto.builder()
            .id(post.getId())
            .content(post.getContent())
            .title(post.getTitle())
            .count(hit.getCount())
            .build();
    }

    @CachePut(key = "#result.id")
    public Post createPost(PostRequestDto postRequestDto) {
        Post post = postRepository.save(postRequestDto.toEntity());
        Hit hit = Hit.builder()
            .post(post)
            .count(0)
            .build();
        hitRepository.save(hit);
        return post;
    }

    @CacheEvict(key = "#postId")
    public Boolean deletePost(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(IllegalArgumentException::new);
        postRepository.delete(post);
        return true;
    }
}
