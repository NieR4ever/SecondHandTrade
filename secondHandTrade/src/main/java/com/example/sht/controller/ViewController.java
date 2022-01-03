package com.example.sht.controller;

import com.example.sht.domain.Message;
import com.example.sht.domain.Post;
import com.example.sht.service.MessageService;
import com.example.sht.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    PostService postService;
    @Autowired
    MessageService messageService;

    /**
     * 跳转到商品详情页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/postDetailPage/{id}")
    public String toPostDetailPage(@PathVariable long id, Model model){
        Post post = postService.findById(id);
        model.addAttribute("post",post);
        return "postDetail";
    }

    /**
     * 跳转到id用户的聊天汇总页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/messagePage/{id}")
    public String toMessagePage(@PathVariable long id, Model model){
        List<Message> messageUserList = messageService.findMessageUserListByUserId(id);
        model.addAttribute("messageUserList",messageUserList);
        return "messagePage";
    }
    @GetMapping("/postEditPage")
    public String toPostEditPage(){
        return "postEdit";
    }
    @PutMapping("/postEditPage")
    public String toPostEditPageForUpdate(@RequestParam("id") long id,Model model){
        Post post = postService.findById(id);
        model.addAttribute("post",post);
        return "postEdit";
    }
    @GetMapping("/loginPage")
    public String toLoginPage(){
        return "/loginPage";
    }
    @GetMapping("/registerPage")
    public String toRegisterPage(){
        return "/registerPage";
    }

    @GetMapping({"/","/index.html"})
    public String toIndex(Model model){
        List<Post> posts = postService.findAllByPostCategory(0);
        model.addAttribute("posts",posts);
        return "index1";
    }
}
