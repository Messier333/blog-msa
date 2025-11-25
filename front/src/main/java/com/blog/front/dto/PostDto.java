package com.blog.front.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String createdAt;
    private String category;
}