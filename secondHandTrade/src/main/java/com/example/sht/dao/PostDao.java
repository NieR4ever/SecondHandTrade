package com.example.sht.dao;

import com.example.sht.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface PostDao {
    List<Post> findAll();
    List<Post> findAllByPostCategory(int postCategory);
    Post findById(long id);
    List<Post> findByUserId(long userId);
    List<Post> findByKeyword(@Param("keyword") String keyword, @Param("postCategory") int postCategory);
    int savePost(Post post);
    int updatePost(Post post);
    int deletePost(long id);

    List<Post> findByName( @Param("name") String name, @Param("postCategory") int postCategory);
}
