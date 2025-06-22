package org.example.vibee.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.vibee.entity.VideoLike;
import org.example.vibee.entity.VideoLikeExample;

public interface VideoLikeMapper {
    long countByExample(VideoLikeExample example);

    int deleteByExample(VideoLikeExample example);

    int deleteByPrimaryKey(Integer recordId);

    int insert(VideoLike record);

    int insertSelective(VideoLike record);

    List<VideoLike> selectByExample(VideoLikeExample example);

    VideoLike selectByPrimaryKey(Integer recordId);

    int updateByExampleSelective(@Param("record") VideoLike record, @Param("example") VideoLikeExample example);

    int updateByExample(@Param("record") VideoLike record, @Param("example") VideoLikeExample example);

    int updateByPrimaryKeySelective(VideoLike record);

    int updateByPrimaryKey(VideoLike record);
}