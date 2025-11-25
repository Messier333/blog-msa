package com.blog.postservice.api;

import com.blog.postservice.domain.Post;
import com.blog.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> list() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post detail(@PathVariable Long id) {
        return postService.findOne(id);
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        post.setPostTime(LocalDateTime.now());
        return postService.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }
}