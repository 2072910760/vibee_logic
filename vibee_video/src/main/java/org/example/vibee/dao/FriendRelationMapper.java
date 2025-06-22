package org.example.vibee.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.vibee.entity.FriendRelation;
import org.example.vibee.entity.FriendRelationExample;

public interface FriendRelationMapper {
    long countByExample(FriendRelationExample example);

    int deleteByExample(FriendRelationExample example);

    int deleteByPrimaryKey(Integer relationId);

    int insert(FriendRelation record);

    int insertSelective(FriendRelation record);

    List<FriendRelation> selectByExample(FriendRelationExample example);

    FriendRelation selectByPrimaryKey(Integer relationId);

    int updateByExampleSelective(@Param("record") FriendRelation record, @Param("example") FriendRelationExample example);

    int updateByExample(@Param("record") FriendRelation record, @Param("example") FriendRelationExample example);

    int updateByPrimaryKeySelective(FriendRelation record);

    int updateByPrimaryKey(FriendRelation record);
}