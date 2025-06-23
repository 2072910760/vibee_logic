package org.example.vibee.service;

import org.example.vibee.entity.VideoLike;
import java.util.List;

public interface VideoLikeService {

    /**
     * 点赞视频
     * @param videoLike 点赞信息
     * @return a int
     */
    int like(VideoLike videoLike);

    /**
     * 取消点赞
     * @param userId 用户ID
     * @param videoId 视频ID
     * @return a int
     */
    int unlike(Integer userId, Integer videoId);

    /**
     * 检查用户是否已点赞某视频
     * @param userId 用户ID
     * @param videoId 视频ID
     * @return a boolean
     */
    boolean hasLiked(Integer userId, Integer videoId);

    /**
     * 获取用户点赞过的所有视频
     * @param userId 用户ID
     * @return a {@link List}
     */
    List<VideoLike> getUserLikes(Integer userId);
} 