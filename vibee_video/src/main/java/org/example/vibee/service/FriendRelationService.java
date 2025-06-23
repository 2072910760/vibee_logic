package org.example.vibee.service;

import org.example.vibee.entity.FriendRelation;
import java.util.List;

public interface FriendRelationService {

    /**
     * 添加好友
     * @param friendRelation 好友关系
     * @return a int
     */
    int addFriend(FriendRelation friendRelation);

    /**
     * 删除好友
     * @param userId 用户ID
     * @param friendId 好友ID
     * @return a int
     */
    int removeFriend(Integer userId, Integer friendId);

    /**
     * 获取用户的好友列表
     * @param userId 用户ID
     * @return a {@link List}
     */
    List<FriendRelation> getFriends(Integer userId);

    /**
     * 检查是否为好友关系
     * @param userId 用户ID
     * @param friendId 好友ID
     * @return a boolean
     */
    boolean isFriend(Integer userId, Integer friendId);
} 