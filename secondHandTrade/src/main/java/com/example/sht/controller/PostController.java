package com.example.sht.controller;

import com.example.sht.domain.Data;
import com.example.sht.domain.Post;
import com.example.sht.service.PostService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {


    @Autowired
    public PostService postService;
    Data data = null;
    HashMap<String, Object> map = null;

    /**
     * 获取所有帖子
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getPost")
    public Data getPostList(@RequestParam("pageNum") int pageNum,
                            @RequestParam("pageSize") int pageSize) {
        data = new Data();
        map = new HashMap<>();
        List<Post> posts = postService.findAll();
        map.put("posts",posts);
        data.setData(map);
        return data;
    }

    /**
     * 根据分类获取帖子列表
     * @param postCategory
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getPostByPostCategory")
    public Data getPostSellList(@RequestParam("postCategory") int postCategory,
                                @RequestParam("pageNum") int pageNum,
                                @RequestParam("pageSize") int pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize, true);
        List<Post> posts = postService.findAllByPostCategory(postCategory);
        data = new Data();
        map = new HashMap<>();
        map.put("total",page.getTotal());
        map.put("posts",posts);
        data.setData(map);
        return data;
    }

    /**
     * 根据id获取帖子
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Data getPostById(@PathVariable("id") long id){
        Post post = postService.findById(id);
        data = new Data();
        map = new HashMap<>();
        if (post!= null) {
            map.put("post",post);
        } else {
            data.setMsg("无法找到这个帖子");
            data.setErrorCode(2);
        }
        data.setData(map);
        return data;
    }

    /**
     * 保存一个帖子
     * @param post
     * @return
     */
    @PostMapping("/doPost")
    public Data savePost(@RequestBody Post post){
        int i = postService.savePost(post);
        data = new Data();
        map = new HashMap<>();
        if (i>0){
            map.put("postOperation",i);
            data.setMsg("提交成功");
        } else {
            data.setErrorCode(1);
            data.setMsg("提交失败");
        }
        data.setData(map);
        return data;
    }

    /**
     * 更新一个帖子
     * @param post
     * @return
     */
    @PutMapping("/doPost")
    public Data updatePost(Post post){
        int i = postService.updatePost(post);
        data = new Data();
        map = new HashMap<>();
        if (i>0){
            map.put("postOperation",i);
            data.setMsg("修改成功");
        } else {
            data.setErrorCode(1);
            data.setMsg("修改失败");
        }
        data.setData(map);
        return data;
    }

    /**
     * 删除一个帖子
     * @param id
     * @return
     */
    @DeleteMapping("/doPost")
    public Data deletePost(@RequestParam long id){
        int i = postService.deletePost(id);
        data = new Data();
        map = new HashMap<>();
        if (i>0){
            map.put("postOperation",i);
            data.setMsg("删除成功");
        } else {
            data.setErrorCode(1);
            data.setMsg("删除失败");
        }
        data.setData(map);
        return data;
    }

    /**
     * 根据关键词和分类搜素帖子
     * @param keywords
     * @param postCategory
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/searchPost")
    public Data getPostListByKeywords(@RequestParam(value = "keywords",required = false,defaultValue = "") String keywords,
                                            @RequestParam int postCategory,
                                      @RequestParam("pageNum") int pageNum,
                                      @RequestParam("pageSize") int pageSize){
        String[] keywordList = keywords.split(" ");
        Page<Object> page = PageHelper.startPage(pageNum, pageSize, true);
        List<Post> posts = postService.findByKeywords(keywordList, postCategory);
        data = new Data();
        map = new HashMap<>();
        map.put("total",page.getTotal());
        map.put("posts",posts);
        data.setData(map);
        return data;
    }
}
