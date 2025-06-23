package org.example.vibee.service.impl;

import org.example.vibee.dao.WatchHistoryMapper;
import org.example.vibee.entity.WatchHistory;
import org.example.vibee.entity.WatchHistoryExample;
import org.example.vibee.service.WatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WatchHistoryServiceImpl implements WatchHistoryService {

    @Autowired
    private WatchHistoryMapper watchHistoryMapper;

    @Override
    public int add(WatchHistory watchHistory) {
        WatchHistoryExample example = new WatchHistoryExample();
        example.createCriteria().andUserIdEqualTo(watchHistory.getUserId()).andVideoIdEqualTo(watchHistory.getVideoId());
        List<WatchHistory> existingRecords = watchHistoryMapper.selectByExample(example);

        if (existingRecords.isEmpty()) {
            // 记录不存在，插入新纪录
            watchHistory.setWatchTime(new Date());
            return watchHistoryMapper.insert(watchHistory);
        } else {
            // 记录已存在，更新观看时间
            WatchHistory recordToUpdate = existingRecords.get(0);
            recordToUpdate.setWatchTime(new Date());
            return watchHistoryMapper.updateByPrimaryKey(recordToUpdate);
        }
    }

    @Override
    public List<WatchHistory> getHistory(Integer userId) {
        WatchHistoryExample example = new WatchHistoryExample();
        example.createCriteria().andUserIdEqualTo(userId);
        example.setOrderByClause("watch_time DESC"); // 按观看时间降序排序
        return watchHistoryMapper.selectByExample(example);
    }
} 