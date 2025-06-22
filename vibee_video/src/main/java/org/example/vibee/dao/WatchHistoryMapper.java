package org.example.vibee.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.vibee.entity.WatchHistory;
import org.example.vibee.entity.WatchHistoryExample;

public interface WatchHistoryMapper {
    long countByExample(WatchHistoryExample example);

    int deleteByExample(WatchHistoryExample example);

    int deleteByPrimaryKey(Integer historyId);

    int insert(WatchHistory record);

    int insertSelective(WatchHistory record);

    List<WatchHistory> selectByExample(WatchHistoryExample example);

    WatchHistory selectByPrimaryKey(Integer historyId);

    int updateByExampleSelective(@Param("record") WatchHistory record, @Param("example") WatchHistoryExample example);

    int updateByExample(@Param("record") WatchHistory record, @Param("example") WatchHistoryExample example);

    int updateByPrimaryKeySelective(WatchHistory record);

    int updateByPrimaryKey(WatchHistory record);
}