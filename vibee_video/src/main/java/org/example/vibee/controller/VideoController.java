package org.example.vibee.controller;

import org.example.vibee.entity.Video;
import org.example.vibee.service.VideoService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    /**
     * 上传视频
     */
    @PostMapping("/upload")
    public ResponseData uploadVideo(@RequestParam("file") MultipartFile file,
                                    @RequestParam("userId") Integer userId,
                                    @RequestParam("title") String title,
                                    @RequestParam("description") String description,
                                    @RequestParam("categoryId") Integer categoryId,
                                    @RequestParam(value = "videoLabel", required = false) String videoLabel) {
        if (file.isEmpty()) {
            return ResponseData.fail(50001, "上传失败，请选择文件");
        }

        try {
            // 1. 保存文件到服务器
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + fileExtension;
            
            // 按日期创建子目录
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = dateFormat.format(new Date());
            File destDir = new File(uploadDir + File.separator + datePath);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            File dest = new File(destDir, newFileName);
            file.transferTo(dest);

            // 2. 保存视频信息到数据库
            Video video = new Video();
            video.setUserId(userId);
            video.setTitle(title);
            video.setDescription(description);
            video.setCategoryId(categoryId);
            video.setVideoLabel(videoLabel);
            video.setVideoUrl("/uploads/" + datePath + "/" + newFileName); // 相对访问路径
            // video.setCoverUrl(...); // 封面可以后续生成或上传

            int videoId = videoService.upload(video);
            return ResponseData.success(videoId);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseData.fail(50002, "文件上传失败");
        }
    }

    /**
     * 获取视频信息
     */
    @GetMapping("/{id}")
    public ResponseData getVideoById(@PathVariable("id") Integer id) {
        Video video = videoService.findById(id);
        if (video == null) {
            return ResponseData.fail(40401, "视频不存在");
        }
        return ResponseData.success(video);
    }

    /**
     * 获取用户上传的视频列表
     */
    @GetMapping("/user/{userId}")
    public ResponseData getVideosByUserId(@PathVariable("userId") Integer userId) {
        List<Video> videos = videoService.findByUserId(userId);
        return ResponseData.success(videos);
    }

    /**
     * 删除视频
     */
    @DeleteMapping("/{id}")
    public ResponseData deleteVideo(@PathVariable("id") Integer id, @RequestParam("userId") Integer userId) {
        Video video = videoService.findById(id);
        if (video == null) {
            return ResponseData.fail(40401, "视频不存在");
        }
        // 权限校验：确保是视频上传者本人操作
        if (!video.getUserId().equals(userId)) {
            return ResponseData.fail(40301, "无权删除他人视频");
        }

        // 1. 删除视频文件
        File videoFile = new File(uploadDir + video.getVideoUrl().replace("/uploads", ""));
        if (videoFile.exists()) {
            videoFile.delete();
        }

        // 2. 删除数据库记录
        videoService.deleteById(id);
        return ResponseData.success("删除成功");
    }
} 