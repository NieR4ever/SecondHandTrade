package com.example.sht.controller;

import com.example.sht.domain.Data;
import com.example.sht.domain.User;
import com.example.sht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${upload.path}")
    String picturePath;
    Data data = null;
    HashMap<String, Object> map = null;

    /**
     * 上传帖子封面图到服务器
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/coverPicture")
    public Data uploadCoverPicture(@RequestParam(value = "file") MultipartFile file) throws Exception {
        data = new Data();
        map = new HashMap<>();
        //上传到的文件夹路径
        File dir = new File(picturePath);
        //判断目录存在并创建
        if(!(dir.exists() && dir.isDirectory())) {
            boolean mkdir = dir.mkdir();
            if(!mkdir){
                throw new Exception("图片上传文件夹创建失败");
            }
        }
        //获取将被上传的文件的名字
        String fileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID()  + fileName;
        File newFile = new File(picturePath+newFileName);
        //上传
        file.transferTo(newFile);
        map.put("coverPictureUrl",picturePath+newFileName);
        data.setData(map);
        data.setMsg("上传成功");
        return data;
    }

    /**
     * 上传头像到服务器
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/avatar")
    public Data uploadAvatar(@RequestParam(value = "file") MultipartFile file) throws Exception {
        data = new Data();
        map = new HashMap<>();
        //上传到的文件夹路径
        File dir = new File(picturePath);
        //判断目录存在并创建
        if(!(dir.exists() && dir.isDirectory())) {
            boolean mkdir = dir.mkdir();
            if(!mkdir){
                throw new Exception("图片上传文件夹创建失败");
            }
        }
        //获取将被上传的文件的名字
        String fileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID()  + fileName;
        File newFile = new File(picturePath+newFileName);
        //上传
        file.transferTo(newFile);
        map.put("avatarUrl",picturePath+newFileName);
        data.setData(map);
        data.setMsg("上传成功");
        return data;
    }
}
