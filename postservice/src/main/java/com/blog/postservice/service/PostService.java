package com.blog.postservice.service;

import com.blog.postservice.domain.Post;
import com.blog.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findOne(Long id) {
        return postRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Post not found"));
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

}