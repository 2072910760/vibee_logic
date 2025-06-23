package org.example.vibee.service.impl;

import org.example.vibee.dao.VideoCommentaryMapper;
import org.example.vibee.entity.VideoCommentary;
import org.example.vibee.service.VideoCommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VideoCommentaryServiceImpl implements VideoCommentaryService {

    @Autowired
    private VideoCommentaryMapper videoCommentaryMapper;

    @Override
    public int insert(VideoCommentary videoCommentary) {
        videoCommentary.setInteractionTime(new Date());
        return videoCommentaryMapper.insert(videoCommentary);
    }

    @Override
    public int deleteById(Integer id) {
        return videoCommentaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public VideoCommentary selectById(Integer id) {
        return videoCommentaryMapper.selectByPrimaryKey(id);
    }
} 