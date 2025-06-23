package org.example.vibee.service;

import org.example.vibee.entity.Video;
import java.util.List;

public interface VideoService {

    /**
     * 上传视频（保存视频信息到数据库）
     * @param video 视频信息
     * @return 成功返回视频ID，失败返回-1
     */
    int upload(Video video);

    /**
     * 根据视频ID删除视频
     * @param videoId 视频ID
     * @return a int
     */
    int deleteById(Integer videoId);

    /**
     * 根据视频ID查找视频
     * @param videoId 视频ID
     * @return a Video
     */
    Video findById(Integer videoId);

    /**
     * 根据用户ID查找该用户上传的所有视频
     * @param userId 用户ID
     * @return a {@link List}
     */
    List<Video> findByUserId(Integer userId);

} 