package org.example.vibee.service;

import org.example.vibee.entity.VideoCommentary;

public interface VideoCommentaryService {
    /**
     * 添加评论
     *
     * @param videoCommentary 评论内容
     * @return a int
     */
    int insert(VideoCommentary videoCommentary);

    /**
     * 根据Id删除评论
     *
     * @param id 评论id
     * @return a int
     */
    int deleteById(Integer id);

    /**
     * 根据Id查询评论
     *
     * @param id 评论id
     * @return a VideoCommentary
     */
    VideoCommentary selectById(Integer id);
} 