package org.example.vibee.controller;

import org.example.vibee.entity.WatchHistory;
import org.example.vibee.service.WatchHistoryService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("history")
public class WatchHistoryController {

    @Autowired
    private WatchHistoryService watchHistoryService;

    /**
     * 添加观看历史
     */
    @PostMapping("/add")
    public ResponseData addHistory(@RequestBody WatchHistory watchHistory) {
        if (watchHistory.getUserId() == null || watchHistory.getVideoId() == null) {
            return ResponseData.missParam("用户ID或视频ID");
        }
        watchHistoryService.add(watchHistory);
        return ResponseData.success("记录成功");
    }

    /**
     * 获取用户的观看历史
     */
    @GetMapping("/user/{userId}")
    public ResponseData getHistory(@PathVariable Integer userId) {
        List<WatchHistory> history = watchHistoryService.getHistory(userId);
        return ResponseData.success(history);
    }
} 