package org.example.vibee.service.impl;

import org.example.vibee.dao.VideoCategoryMapper;
import org.example.vibee.entity.VideoCategory;
import org.example.vibee.entity.VideoCategoryExample;
import org.example.vibee.service.VideoCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoCategoryServiceImpl implements VideoCategoryService {

    @Autowired
    private VideoCategoryMapper videoCategoryMapper;

    @Override
    public int add(VideoCategory category) {
        return videoCategoryMapper.insertSelective(category);
    }

    @Override
    public List<VideoCategory> list() {
        return videoCategoryMapper.selectByExample(new VideoCategoryExample());
    }
} 