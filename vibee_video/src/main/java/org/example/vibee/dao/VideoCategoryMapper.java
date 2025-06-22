package org.example.vibee.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.vibee.entity.VideoCategory;
import org.example.vibee.entity.VideoCategoryExample;

public interface VideoCategoryMapper {
    long countByExample(VideoCategoryExample example);

    int deleteByExample(VideoCategoryExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(VideoCategory record);

    int insertSelective(VideoCategory record);

    List<VideoCategory> selectByExample(VideoCategoryExample example);

    VideoCategory selectByPrimaryKey(Integer categoryId);

    int updateByExampleSelective(@Param("record") VideoCategory record, @Param("example") VideoCategoryExample example);

    int updateByExample(@Param("record") VideoCategory record, @Param("example") VideoCategoryExample example);

    int updateByPrimaryKeySelective(VideoCategory record);

    int updateByPrimaryKey(VideoCategory record);
}