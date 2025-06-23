package org.example.vibee.service;

import org.example.vibee.entity.VideoCollect;
import java.util.List;

public interface VideoCollectService {

    /**
     * 收藏视频
     * @param videoCollect 收藏信息
     * @return a int
     */
    int collect(VideoCollect videoCollect);

    /**
     * 取消收藏
     * @param userId 用户ID
     * @param videoId 视频ID
     * @return a int
     */
    int uncollect(Integer userId, Integer videoId);

    /**
     * 检查用户是否已收藏某视频
     * @param userId 用户ID
     * @param videoId 视频ID
     * @return a boolean
     */
    boolean hasCollected(Integer userId, Integer videoId);

    /**
     * 获取用户收藏的所有视频
     * @param userId 用户ID
     * @return a {@link List}
     */
    List<VideoCollect> getUserCollections(Integer userId);
} 