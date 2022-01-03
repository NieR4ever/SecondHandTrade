package com.example.sht.controller;

import com.example.sht.domain.Collected;
import com.example.sht.domain.Data;
import com.example.sht.domain.Post;
import com.example.sht.domain.User;
import com.example.sht.service.CollectedService;
import com.example.sht.service.PostService;
import com.example.sht.service.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    CollectedService collectedService;

    Data data = null;
    HashMap<String, Object> map = null;

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/showUsers")
    public Data getUserList(){
        List<User> users = userService.findAll();
        data = new Data();
        map = new HashMap<>();
        if (users==null){
            data.setErrorCode(1);
            data.setMsg("查询失败");
        }else{
            map.put("user",users);
            data.setData(map);
        }
        return data;
    }


    /**
     * 根据id更新用户信息
     * @param id
     * @param user
     * @return
     */
    @PutMapping("{id}")
    public Data updateUser(@PathVariable("id") long id,@RequestBody User user) {
        System.out.println(user);
        int i = userService.updateUser(user);
        data = new Data();
        map = new HashMap<>();
        if (i > 0) {
            map.put("userOperation",i);
            data.setData(map);
        } else {
            data.setErrorCode(1);
            data.setMsg("修改失败");
        }
        return data;
    }

    /**
     * 进行登录校验
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/login")
    public Data login(@RequestBody User user, HttpSession session){
        User user1 = userService.findUserForLogin(user);
        data = new Data();
        map = new HashMap<>();
        map.put("user",user1);
        if (user1 != null){
            session.setAttribute("loginUser",user1);
            map.put("user",user1);
            data.setData(map);
            data.setMsg("登录成功");
        } else {
            data.setErrorCode(0);
            data.setMsg("登录失败");
        }
        return data;
    }

    /**
     * 进行注册校验
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Data register(@RequestBody User user){
        User user1 = userService.findByName(user);
        data = new Data();
        map = new HashMap<>();
        if (user1 != null){
            data.setMsg("该用户名已被注册");
        } else {
            int i = userService.saveUser(user);
            if (i>0){
                map.put("userOperation",i);
                data.setData(map);
                data.setMsg("注册成功");
            } else {
                data.setMsg("网络原因，注册失败");
            }
        }
        return data;
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Data getUser(@PathVariable("id") long id){
        System.out.println(id);
        User user = userService.findById(id);
        List<Post> posts = postService.findByUserId(id);
//        List<Collected> collectedPosts = collectedService.findByUserId(id);
        data = new Data();
        map = new HashMap<>();
        if (user != null){
            map.put("user",user);
            map.put("posts",posts);
//            map.put("collectedPosts",collectedPosts);
        } else {
            data.setErrorCode(2);
            data.setMsg("查无此人");
        }
        data.setData(map);
        return data;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public Data logout(HttpSession session){
        session.removeAttribute("loginUser");
        data = new Data();
        data.setMsg("退出成功");
        return data;
    }
}
