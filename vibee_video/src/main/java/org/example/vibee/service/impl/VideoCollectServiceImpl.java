package org.example.vibee.service.impl;

import org.example.vibee.dao.VideoCollectMapper;
import org.example.vibee.entity.VideoCollect;
import org.example.vibee.entity.VideoCollectExample;
import org.example.vibee.service.VideoCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoCollectServiceImpl implements VideoCollectService {

    @Autowired
    private VideoCollectMapper videoCollectMapper;

    @Override
    public int collect(VideoCollect videoCollect) {
        if (hasCollected(videoCollect.getUserId(), videoCollect.getVideoId())) {
            return 0; // 已收藏
        }
        videoCollect.setOperationTime(new Date());
        return videoCollectMapper.insert(videoCollect);
    }

    @Override
    public int uncollect(Integer userId, Integer videoId) {
        VideoCollectExample example = new VideoCollectExample();
        example.createCriteria().andUserIdEqualTo(userId).andVideoIdEqualTo(videoId);
        return videoCollectMapper.deleteByExample(example);
    }

    @Override
    public boolean hasCollected(Integer userId, Integer videoId) {
        VideoCollectExample example = new VideoCollectExample();
        example.createCriteria().andUserIdEqualTo(userId).andVideoIdEqualTo(videoId);
        return videoCollectMapper.countByExample(example) > 0;
    }

    @Override
    public List<VideoCollect> getUserCollections(Integer userId) {
        VideoCollectExample example = new VideoCollectExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return videoCollectMapper.selectByExample(example);
    }
} 