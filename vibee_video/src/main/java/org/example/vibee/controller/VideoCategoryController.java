package org.example.vibee.controller;

import org.example.vibee.entity.VideoCategory;
import org.example.vibee.service.VideoCategoryService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class VideoCategoryController {

    @Autowired
    private VideoCategoryService videoCategoryService;

    /**
     * 添加视频分类
     */
    @PostMapping("/add")
    public ResponseData addCategory(@RequestBody VideoCategory category) {
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            return ResponseData.missParam("分类名称");
        }
        videoCategoryService.add(category);
        return ResponseData.success(category.getCategoryId());
    }

    /**
     * 获取所有视频分类
     */
    @GetMapping("/list")
    public ResponseData listCategories() {
        List<VideoCategory> list = videoCategoryService.list();
        return ResponseData.success(list);
    }
} 