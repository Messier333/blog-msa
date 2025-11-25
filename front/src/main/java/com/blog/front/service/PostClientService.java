package com.blog.front.service;

import com.blog.front.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostClientService {

    private final RestTemplate restTemplate;

    @Value("${blog.api.post-url}")
    private String postApiUrl;

    public List<PostDto> getAllPosts() {
        PostDto[] response =
                restTemplate.getForObject(postApiUrl, PostDto[].class);

        assert response != null;
        return Arrays.asList(response);
    }

    public PostDto getPostById(Long id) {
        String url = postApiUrl + "/" + id;
        return restTemplate.getForObject(url, PostDto.class);
    }
}