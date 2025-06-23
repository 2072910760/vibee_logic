package org.example.vibee.service.impl;

import org.example.vibee.dao.VideoMapper;
import org.example.vibee.entity.Video;
import org.example.vibee.entity.VideoExample;
import org.example.vibee.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public int upload(Video video) {
        video.setUploadTime(new Date());
        video.setVideoStatus((byte) 1); // 1-正常，0-审核中，-1-下架
        videoMapper.insertSelective(video);
        return video.getVideoId();
    }

    @Override
    public int deleteById(Integer videoId) {
        return videoMapper.deleteByPrimaryKey(videoId);
    }

    @Override
    public Video findById(Integer videoId) {
        return videoMapper.selectByPrimaryKey(videoId);
    }

    @Override
    public List<Video> findByUserId(Integer userId) {
        VideoExample example = new VideoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return videoMapper.selectByExample(example);
    }
} 