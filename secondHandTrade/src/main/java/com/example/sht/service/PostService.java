package com.example.sht.service;

import com.example.sht.domain.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<Post> findAll();
    Post findById(long id);
    List<Post> findByUserId(long userId);
    List<Post> findByKeywords(String[] keywordList, int postCategory);
    int savePost(Post post);
    int updatePost(Post post);
    int deletePost(long id);
    List<Post> findAllByPostCategory(int postCategory);

}
