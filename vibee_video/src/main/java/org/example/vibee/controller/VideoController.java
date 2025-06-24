package org.example.vibee.controller;

import jakarta.servlet.http.HttpSession;
import org.example.vibee.entity.User;
import org.example.vibee.entity.Video;
import org.example.vibee.service.VideoService;
import org.example.vibee.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    
    // 允许上传的MIME类型白名单
    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("video/mp4", "video/webm", "video/ogg", "video/quicktime");
    // 允许上传的文件扩展名白名单
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".mp4", ".webm", ".mov");


    /**
     * 上传视频
     */
    @PostMapping("/upload")
    public ResponseData uploadVideo(@RequestParam("file") MultipartFile file,
                                    @RequestParam("title") String title,
                                    @RequestParam("description") String description,
                                    @RequestParam("categoryId") Integer categoryId,
                                    @RequestParam(value = "videoLabel", required = false) String videoLabel,
                                    HttpSession session) {
        
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseData.fail(401, "用户未登录");
        }

        if (file.isEmpty()) {
            return ResponseData.fail(50001, "上传失败，请选择文件");
        }

        // 验证文件MIME类型
        if (!ALLOWED_MIME_TYPES.contains(file.getContentType())) {
            return ResponseData.fail(50003, "不支持的文件类型，请上传有效的视频文件");
        }

        String originalFilename = file.getOriginalFilename();
        // 清理文件名，防止路径遍历
        String safeFilename = StringUtils.cleanPath(originalFilename);
        
        // 验证文件扩展名
        String fileExtension = safeFilename.substring(safeFilename.lastIndexOf(".")).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            return ResponseData.fail(50004, "不支持的文件扩展名");
        }
        
        try {
            // 1. 保存文件到服务器
            String newFileName = UUID.randomUUID().toString() + fileExtension;
            
            // 按日期创建子目录
            String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            File destDir = new File(uploadDir, datePath);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            File dest = new File(destDir, newFileName);
            // 再次检查目标路径，确保它在预期的上传目录下
            if (!dest.getCanonicalPath().startsWith(new File(uploadDir).getCanonicalPath())) {
                return ResponseData.fail(50005, "检测到非法路径，上传失败");
            }
            
            file.transferTo(dest);

            // 2. 保存视频信息到数据库
            Video video = new Video();
            video.setUserId(currentUser.getUserId());
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
     * 获取指定用户上传的视频列表
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
    public ResponseData deleteVideo(@PathVariable("id") Integer id, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseData.fail(401, "用户未登录");
        }
        
        Video video = videoService.findById(id);
        if (video == null) {
            // 即使视频不存在，也返回成功，避免信息泄露
            return ResponseData.success("删除成功");
        }
        
        // 权限校验：确保是视频上传者本人操作
        if (!video.getUserId().equals(currentUser.getUserId())) {
            return ResponseData.fail(40301, "无权删除他人视频");
        }

        // 1. 删除视频文件
        try {
            File videoFile = new File(uploadDir, video.getVideoUrl().replace("/uploads/", ""));
            if (videoFile.exists()) {
                // 安全性检查，确保文件在上传目录内
                if (videoFile.getCanonicalPath().startsWith(new File(uploadDir).getCanonicalPath())) {
                    videoFile.delete();
                }
            }
        } catch (IOException e) {
             e.printStackTrace();
             // 即使文件删除失败，也继续删除数据库记录
        }
        

        // 2. 删除数据库记录
        videoService.deleteById(id);
        return ResponseData.success("删除成功");
    }
} 