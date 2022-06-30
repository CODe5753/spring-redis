package com.example.springredis.dto;

import com.example.springredis.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

    String title;
    String content;

    public Post toEntity() {
        return Post.builder()
            .content(content)
            .title(title)
            .build();
    }
}
