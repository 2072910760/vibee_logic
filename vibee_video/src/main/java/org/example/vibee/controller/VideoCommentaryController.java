package org.example.vibee.controller;

import org.example.vibee.entity.VideoCommentary;
import org.example.vibee.service.VideoCommentaryService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
public class VideoCommentaryController {

    @Autowired
    private VideoCommentaryService videoCommentaryService;

    /**
     * 添加视频评论
     */
    @PostMapping("add")
    public ResponseData createComment(@RequestBody VideoCommentary videoCommentary) {
        // 校验参数
        if (videoCommentary.getVideoId() == null) {
            return ResponseData.missParam("视频ID");
        }
        if (videoCommentary.getUserId() == null) {
            return ResponseData.missParam("用户ID");
        }
        if (videoCommentary.getInteractionContent() == null || "".equals(videoCommentary.getInteractionContent().trim())) {
            return ResponseData.missParam("评论内容");
        }
        int rows = videoCommentaryService.insert(videoCommentary);
        return ResponseData.success(rows);
    }

    /**
     * 删除评论
     */
    @DeleteMapping("remove/{id}")
    public ResponseData removeComment(@PathVariable("id") int id) {
        VideoCommentary c = videoCommentaryService.selectById(id);
        if (c == null) {
            return ResponseData.createFail("评论不存在");
        }
        // todo: 增加权限校验，只有评论发布者或管理员可以删除
        int rows = videoCommentaryService.deleteById(id);
        return ResponseData.success(rows);
    }
}