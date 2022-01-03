package com.example.sht.controller;

import com.example.sht.domain.Category;
import com.example.sht.domain.Quality;
import com.example.sht.domain.Data;
import com.example.sht.service.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/quality")
public class QualityController {

    @Autowired
    QualityService qualityService;

    Data data = null;
    HashMap<String, Object> map = null;

    /**
     * 获取所有成色
     * @return
     */
    @GetMapping("/findAllQualities")
    public Data getQualityList(){
        data = new Data();
        map = new HashMap<>();
        List<Quality> qualities = qualityService.findAll();
        map.put("qualities",qualities);
        data.setData(map);
        data.setMsg("查询成功");
        return data;
    }

    /**
     * 根据id获取成色
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Data getQualityById(@PathVariable("id") int id){
        data = new Data();
        map = new HashMap<>();
        Quality quality = qualityService.findById(id);
        map.put("quality",quality);
        data.setData(map);
        data.setMsg("查询成功");
        return data;
    }

    /**
     * 根据名字获取成色
     * @param quality
     * @return
     */
    @GetMapping("/findqualitiesByName")
    public Data getQualityListByName(@RequestParam("quality") String quality) {
        data = new Data();
        map = new HashMap<>();
        List<Quality> qualities = qualityService.findByName(quality);
        if (qualities != null){
            if (qualities.size()!=0){
                map.put("qualities",qualities);
                data.setData(map);
                data.setMsg("查询成功");
            } else {
                map.put("qualities",qualities);
                data.setData(map);
                data.setErrorCode(2);
                data.setMsg("没有这个成色");
            }
        } else {
            map.put("qualities", Collections.singletonList(new Quality()));
            data.setData(map);
            data.setErrorCode(1);
            data.setMsg("查询失败");
        }
        return data;
    }

    /**
     * 保存一个成色
     * @param quality
     * @return
     */
    @PostMapping("/doQuality")
    public Data saveQuality(@RequestParam("quality") String quality) {
        data = new Data();
        map = new HashMap<>();
        Quality qualityForSave = new Quality();
        qualityForSave.setQuality(quality);
        int i = qualityService.saveQuality(qualityForSave);
        if (i > 0) {
            map.put("qualityOperation",i);
            data.setMsg("保存成功");
            data.setData(map);
        } else {
            data.setMsg("保存失败");
            data.setErrorCode(1);
        }
        return data;
    }

    /**
     * 更新一个成色
     * @param id
     * @return
     */
    @PutMapping("/doQuality")
    public Data updateQuality(int id) {
        data = new Data();
        map = new HashMap<>();
        Quality qualityForUpdate = new Quality();
        qualityForUpdate.setId(id);
        int i = qualityService.updateQuality(qualityForUpdate);
        if (i > 0) {
            map.put("qualityOperation",i);
            data.setMsg("修改成功");
            data.setData(map);
        } else {
            data.setMsg("修改失败");
            data.setErrorCode(1);
        }
        return data;
    }

    /**
     * 删除一个成色
     * @param id
     * @return
     */
    @DeleteMapping("/doQuality")
    public Data deleteQuality(int id) {
        data = new Data();
        map = new HashMap<>();
        int i = qualityService.deleteQuality(id);
        if (i > 0) {
            map.put("qualityOperation",i);
            data.setMsg("删除成功");
            data.setData(map);
        } else {
            data.setMsg("删除失败");
            data.setErrorCode(1);
        }
        return data;
    }
}
