package com.example.springredis.dto;

import com.example.springredis.entity.Hit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    Long id;
    String title;
    String content;
    Integer count;
}
