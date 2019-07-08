package com.myfitnesspal.feature.diary.model;

import java.util.Date;

public class FriendDiaryRequestData {
    private final Date date;
    private final FriendDiaryBundleInfo friendDiaryBundleInfo;

    public FriendDiaryRequestData(FriendDiaryBundleInfo friendDiaryBundleInfo2, Date date2) {
        this.friendDiaryBundleInfo = friendDiaryBundleInfo2;
        this.date = date2;
    }

    public String getUsername() {
        return this.friendDiaryBundleInfo.getUsername();
    }

    public String getUserUid() {
        return this.friendDiaryBundleInfo.getUserUid();
    }

    public String getDiaryToken() {
        return this.friendDiaryBundleInfo.getDiaryToken();
    }

    public long getUserMasterId() {
        return this.friendDiaryBundleInfo.getUserMasterId();
    }

    public void setUserMasterId(long j) {
        this.friendDiaryBundleInfo.setUserMasterId(j);
    }

    public Date getDate() {
        return this.date;
    }
}
