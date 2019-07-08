package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedActivityParticipantPostData {
    @Expose
    private String userId;

    public MfpNewsFeedActivityParticipantPostData(String str) {
        setUserId(str);
    }

    public String getUserId() {
        return this.userId;
    }

    public MfpNewsFeedActivityParticipantPostData setUserId(String str) {
        this.userId = str;
        return this;
    }
}
