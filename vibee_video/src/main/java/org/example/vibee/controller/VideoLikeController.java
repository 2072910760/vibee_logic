package org.example.vibee.controller;

import org.example.vibee.entity.VideoLike;
import org.example.vibee.service.VideoLikeService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("like")
public class VideoLikeController {

    @Autowired
    private VideoLikeService videoLikeService;

    /**
     * 点赞视频
     */
    @PostMapping("/add")
    public ResponseData likeVideo(@RequestBody VideoLike videoLike) {
        if (videoLike.getUserId() == null || videoLike.getVideoId() == null) {
            return ResponseData.missParam("用户ID或视频ID");
        }
        int result = videoLikeService.like(videoLike);
        if (result == 0) {
            return ResponseData.fail(60001, "您已点赞过该视频");
        }
        return ResponseData.success("点赞成功");
    }

    /**
     * 取消点赞
     */
    @DeleteMapping("/cancel")
    public ResponseData unlikeVideo(@RequestParam Integer userId, @RequestParam Integer videoId) {
        videoLikeService.unlike(userId, videoId);
        return ResponseData.success("取消点赞成功");
    }

    /**
     * 检查是否已点赞
     */
    @GetMapping("/check")
    public ResponseData checkLike(@RequestParam Integer userId, @RequestParam Integer videoId) {
        boolean hasLiked = videoLikeService.hasLiked(userId, videoId);
        return ResponseData.success(hasLiked);
    }

    /**
     * 获取用户点赞列表
     */
    @GetMapping("/user/{userId}")
    public ResponseData getUserLikes(@PathVariable Integer userId) {
        List<VideoLike> userLikes = videoLikeService.getUserLikes(userId);
        return ResponseData.success(userLikes);
    }
} 