package com.myfitnesspal.shared.model;

import com.google.gson.annotations.Expose;

public class InAppNotifications {
    @Expose
    private int diaryCommentsFromCoachCount;
    @Expose
    private int friendRequestCount;
    @Expose
    private long lastUpdateTime;
    @Expose
    private int messageCount;
    @Expose
    private int messagesFromCoachCount;

    public int getFriendRequestCount() {
        return this.friendRequestCount;
    }

    public void setFriendRequestCount(int i) {
        this.friendRequestCount = i;
    }

    public int getMessageCount() {
        return this.messageCount;
    }

    public void setMessageCount(int i) {
        this.messageCount = i;
    }

    public void setLastUpdateTime(long j) {
        this.lastUpdateTime = j;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public int getAggregateCount() {
        return getMessageCount() + getFriendRequestCount();
    }

    public void setMessagesFromCoachCount(int i) {
        this.messagesFromCoachCount = i;
    }

    public void setDiaryCommentsFromCoachCount(int i) {
        this.diaryCommentsFromCoachCount = i;
    }

    public int getMessagesFromCoachCount() {
        return this.messagesFromCoachCount;
    }

    public int getDiaryCommentsFromCoachCount() {
        return this.diaryCommentsFromCoachCount;
    }
}
