package com.example.sht.service.impl;

import com.example.sht.dao.PostDao;
import com.example.sht.domain.Post;
import com.example.sht.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Value("${upload.server.host}${upload.server.port}")
    public String pictureServer;

    @Autowired
    private PostDao postDao;
    @Override
    public List<Post> findAll() {
        List<Post> posts = postDao.findAll();
        return setPictureUrl(posts);
    }

    @Override
    public Post findById(long id) {
        Post post = postDao.findById(id);
        return setPictureUrl(post);
    }

    @Override
    public List<Post> findByUserId(long userId) {
        List<Post> posts = postDao.findByUserId(userId);
        return setPictureUrl(posts);
    }

    @Override
    public List<Post> findByKeywords(String[] keywordList, int postCategory) {
        List<Post> posts = new ArrayList<>();
        for (String keyword : keywordList) {
            posts.addAll(postDao.findByKeyword(keyword,postCategory));
            posts.addAll(postDao.findByName(keyword,postCategory));
        }
        return new ArrayList<>(new HashSet<>(setPictureUrl(posts)));
    }

    @Override
    public int savePost(Post post) {
        return postDao.savePost(post);
    }

    @Override
    public int updatePost(Post post) {
        return postDao.updatePost(post);
    }

    @Override
    public int deletePost(long id) {
        return postDao.deletePost(id);
    }

    @Override
    public List<Post> findAllByPostCategory(int postCategory) {
        return setPictureUrl(postDao.findAllByPostCategory(postCategory));
    }

    private List<Post> setPictureUrl(List<Post> posts) {
        for (Post post : posts) {
            String url = post.getCoverPictureUrl();
            String[] split = url.split("\\\\");
            post.setCoverPictureUrl(pictureServer+"/"+split[split.length-1]);
        }
        return posts;
    }
    private Post setPictureUrl(Post post) {
        String url = post.getCoverPictureUrl();
        String[] split = url.split("\\\\");
        post.setCoverPictureUrl(pictureServer+"/"+split[split.length-1]);
        return post;
    }
}
