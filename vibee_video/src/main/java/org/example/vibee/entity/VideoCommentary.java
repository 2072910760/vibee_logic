package org.example.vibee.entity;

import java.util.Date;

public class VideoCommentary {
    private Integer interactionId;

    private Integer videoId;

    private Integer userId;

    private String interactionContent;

    private Date interactionTime;

    private Integer replyToCommentId;

    public Integer getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(Integer interactionId) {
        this.interactionId = interactionId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getInteractionContent() {
        return interactionContent;
    }

    public void setInteractionContent(String interactionContent) {
        this.interactionContent = interactionContent == null ? null : interactionContent.trim();
    }

    public Date getInteractionTime() {
        return interactionTime;
    }

    public void setInteractionTime(Date interactionTime) {
        this.interactionTime = interactionTime;
    }

    public Integer getReplyToCommentId() {
        return replyToCommentId;
    }

    public void setReplyToCommentId(Integer replyToCommentId) {
        this.replyToCommentId = replyToCommentId;
    }
}