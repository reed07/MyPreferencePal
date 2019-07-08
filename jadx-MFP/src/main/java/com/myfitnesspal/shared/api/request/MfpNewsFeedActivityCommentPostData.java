package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedActivityCommentPostData {
    @Expose
    private MfpNewsFeedActivityParticipantPostData sourceUser;
    @Expose
    private String text;

    public MfpNewsFeedActivityCommentPostData(String str, String str2) {
        this.text = str;
        setSourceUser(new MfpNewsFeedActivityParticipantPostData(str2));
    }

    public MfpNewsFeedActivityParticipantPostData getSourceUser() {
        return this.sourceUser;
    }

    public MfpNewsFeedActivityCommentPostData setSourceUser(MfpNewsFeedActivityParticipantPostData mfpNewsFeedActivityParticipantPostData) {
        this.sourceUser = mfpNewsFeedActivityParticipantPostData;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public MfpNewsFeedActivityCommentPostData setText(String str) {
        this.text = str;
        return this;
    }
}
