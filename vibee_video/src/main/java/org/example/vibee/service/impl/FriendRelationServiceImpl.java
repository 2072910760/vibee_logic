package org.example.vibee.service.impl;

import org.example.vibee.dao.FriendRelationMapper;
import org.example.vibee.entity.FriendRelation;
import org.example.vibee.entity.FriendRelationExample;
import org.example.vibee.service.FriendRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FriendRelationServiceImpl implements FriendRelationService {

    @Autowired
    private FriendRelationMapper friendRelationMapper;

    @Transactional
    @Override
    public int addFriend(FriendRelation friendRelation) {
        if (isFriend(friendRelation.getUserId(), friendRelation.getFriendId())) {
            return 0; // 已经是好友
        }

        // 添加 A -> B 的关系
        friendRelation.setFollowTime(new Date());
        friendRelationMapper.insert(friendRelation);

        // 添加 B -> A 的关系
        FriendRelation reverseRelation = new FriendRelation();
        reverseRelation.setUserId(friendRelation.getFriendId());
        reverseRelation.setFriendId(friendRelation.getUserId());
        reverseRelation.setFollowTime(new Date());

        return friendRelationMapper.insert(reverseRelation);
    }

    @Override
    public int removeFriend(Integer userId, Integer friendId) {
        FriendRelationExample example = new FriendRelationExample();
        // 删除 A -> B
        example.createCriteria().andUserIdEqualTo(userId).andFriendIdEqualTo(friendId);
        // 同时删除 B -> A
        example.or(example.createCriteria().andUserIdEqualTo(friendId).andFriendIdEqualTo(userId));
        return friendRelationMapper.deleteByExample(example);
    }

    @Override
    public List<FriendRelation> getFriends(Integer userId) {
        FriendRelationExample example = new FriendRelationExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return friendRelationMapper.selectByExample(example);
    }

    @Override
    public boolean isFriend(Integer userId, Integer friendId) {
        FriendRelationExample example = new FriendRelationExample();
        example.createCriteria().andUserIdEqualTo(userId).andFriendIdEqualTo(friendId);
        return friendRelationMapper.countByExample(example) > 0;
    }
} 