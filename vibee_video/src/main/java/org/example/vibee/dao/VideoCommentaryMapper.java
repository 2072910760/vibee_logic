package org.example.vibee.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.vibee.entity.VideoCommentary;
import org.example.vibee.entity.VideoCommentaryExample;

public interface VideoCommentaryMapper {
    long countByExample(VideoCommentaryExample example);

    int deleteByExample(VideoCommentaryExample example);

    int deleteByPrimaryKey(Integer interactionId);

    int insert(VideoCommentary record);

    int insertSelective(VideoCommentary record);

    List<VideoCommentary> selectByExample(VideoCommentaryExample example);

    VideoCommentary selectByPrimaryKey(Integer interactionId);

    int updateByExampleSelective(@Param("record") VideoCommentary record, @Param("example") VideoCommentaryExample example);

    int updateByExample(@Param("record") VideoCommentary record, @Param("example") VideoCommentaryExample example);

    int updateByPrimaryKeySelective(VideoCommentary record);

    int updateByPrimaryKey(VideoCommentary record);
}