package org.example.vibee.service;

import org.example.vibee.entity.WatchHistory;
import java.util.List;

public interface WatchHistoryService {

    /**
     * 添加观看历史
     * @param watchHistory 观看历史记录
     * @return a int
     */
    int add(WatchHistory watchHistory);

    /**
     * 获取用户的观看历史
     * @param userId 用户ID
     * @return a {@link List}
     */
    List<WatchHistory> getHistory(Integer userId);
} 