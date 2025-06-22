package org.example.vibee.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.vibee.entity.VideoCollect;
import org.example.vibee.entity.VideoCollectExample;

public interface VideoCollectMapper {
    long countByExample(VideoCollectExample example);

    int deleteByExample(VideoCollectExample example);

    int deleteByPrimaryKey(Integer recordId);

    int insert(VideoCollect record);

    int insertSelective(VideoCollect record);

    List<VideoCollect> selectByExample(VideoCollectExample example);

    VideoCollect selectByPrimaryKey(Integer recordId);

    int updateByExampleSelective(@Param("record") VideoCollect record, @Param("example") VideoCollectExample example);

    int updateByExample(@Param("record") VideoCollect record, @Param("example") VideoCollectExample example);

    int updateByPrimaryKeySelective(VideoCollect record);

    int updateByPrimaryKey(VideoCollect record);
}