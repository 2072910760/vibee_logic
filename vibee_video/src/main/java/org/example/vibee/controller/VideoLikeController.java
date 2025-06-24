package org.example.vibee.controller;

import jakarta.servlet.http.HttpSession;
import org.example.vibee.entity.User;
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
    public ResponseData likeVideo(@RequestBody VideoLike videoLike, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        videoLike.setUserId(currentUser.getUserId());

        if (videoLike.getVideoId() == null) {
            return ResponseData.missParam("视频ID");
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
    public ResponseData unlikeVideo(@RequestParam Integer videoId, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        videoLikeService.unlike(currentUser.getUserId(), videoId);
        return ResponseData.success("取消点赞成功");
    }

    /**
     * 检查是否已点赞
     */
    @GetMapping("/check")
    public ResponseData checkLike(@RequestParam Integer videoId, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        boolean hasLiked = videoLikeService.hasLiked(currentUser.getUserId(), videoId);
        return ResponseData.success(hasLiked);
    }

    /**
     * 获取当前登录用户的点赞列表
     */
    @GetMapping("/list")
    public ResponseData getCurrentUserLikes(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        List<VideoLike> userLikes = videoLikeService.getUserLikes(currentUser.getUserId());
        return ResponseData.success(userLikes);
    }
} 