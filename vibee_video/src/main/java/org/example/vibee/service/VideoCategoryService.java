package org.example.vibee.service;

import org.example.vibee.entity.VideoCategory;
import java.util.List;

public interface VideoCategoryService {

    /**
     * 添加视频分类
     * @param category 分类信息
     * @return a int
     */
    int add(VideoCategory category);

    /**
     * 获取所有视频分类
     * @return a {@link List}
     */
    List<VideoCategory> list();
} 