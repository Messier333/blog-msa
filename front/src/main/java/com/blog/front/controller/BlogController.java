package com.blog.front.controller;

import com.blog.front.dto.PostDto;
import com.blog.front.service.PostClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class BlogController {

    private final PostClientService postClientService;

    public BlogController(PostClientService postClientService) {
        this.postClientService = postClientService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<PostDto> posts = postClientService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/posts/{id}")
    public String post(@PathVariable Long id, Model model) {
        PostDto post = postClientService.getPostById(id);
        model.addAttribute("post", post);
        return "post-detail";
    }
}