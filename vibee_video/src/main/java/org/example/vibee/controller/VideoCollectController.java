package org.example.vibee.controller;

import org.example.vibee.entity.VideoCollect;
import org.example.vibee.service.VideoCollectService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("collect")
public class VideoCollectController {

    @Autowired
    private VideoCollectService videoCollectService;

    /**
     * 收藏视频
     */
    @PostMapping("/add")
    public ResponseData collectVideo(@RequestBody VideoCollect videoCollect) {
        if (videoCollect.getUserId() == null || videoCollect.getVideoId() == null) {
            return ResponseData.missParam("用户ID或视频ID");
        }
        int result = videoCollectService.collect(videoCollect);
        if (result == 0) {
            return ResponseData.fail(70001, "您已收藏过该视频");
        }
        return ResponseData.success("收藏成功");
    }

    /**
     * 取消收藏
     */
    @DeleteMapping("/cancel")
    public ResponseData uncollectVideo(@RequestParam Integer userId, @RequestParam Integer videoId) {
        videoCollectService.uncollect(userId, videoId);
        return ResponseData.success("取消收藏成功");
    }

    /**
     * 检查是否已收藏
     */
    @GetMapping("/check")
    public ResponseData checkCollection(@RequestParam Integer userId, @RequestParam Integer videoId) {
        boolean hasCollected = videoCollectService.hasCollected(userId, videoId);
        return ResponseData.success(hasCollected);
    }

    /**
     * 获取用户收藏列表
     */
    @GetMapping("/user/{userId}")
    public ResponseData getUserCollections(@PathVariable Integer userId) {
        List<VideoCollect> userCollections = videoCollectService.getUserCollections(userId);
        return ResponseData.success(userCollections);
    }
} 