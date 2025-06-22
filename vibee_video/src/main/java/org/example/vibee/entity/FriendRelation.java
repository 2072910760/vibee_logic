package org.example.vibee.entity;

import java.util.Date;

public class FriendRelation {
    private Integer relationId;

    private Integer userId;

    private Integer friendId;

    private Byte relationStatus;

    private Date followTime;

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Byte getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Byte relationStatus) {
        this.relationStatus = relationStatus;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }
}