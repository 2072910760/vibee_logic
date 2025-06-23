package org.example.vibee.service.impl;

import org.example.vibee.dao.VideoLikeMapper;
import org.example.vibee.entity.VideoLike;
import org.example.vibee.entity.VideoLikeExample;
import org.example.vibee.service.VideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoLikeServiceImpl implements VideoLikeService {

    @Autowired
    private VideoLikeMapper videoLikeMapper;

    @Override
    public int like(VideoLike videoLike) {
        if (hasLiked(videoLike.getUserId(), videoLike.getVideoId())) {
            return 0; // 已点赞，无需重复操作
        }
        videoLike.setOperationTime(new Date());
        return videoLikeMapper.insert(videoLike);
    }

    @Override
    public int unlike(Integer userId, Integer videoId) {
        VideoLikeExample example = new VideoLikeExample();
        example.createCriteria().andUserIdEqualTo(userId).andVideoIdEqualTo(videoId);
        return videoLikeMapper.deleteByExample(example);
    }

    @Override
    public boolean hasLiked(Integer userId, Integer videoId) {
        VideoLikeExample example = new VideoLikeExample();
        example.createCriteria().andUserIdEqualTo(userId).andVideoIdEqualTo(videoId);
        return videoLikeMapper.countByExample(example) > 0;
    }

    @Override
    public List<VideoLike> getUserLikes(Integer userId) {
        VideoLikeExample example = new VideoLikeExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return videoLikeMapper.selectByExample(example);
    }
} 