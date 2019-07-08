package com.myfitnesspal.shared.model.v15;

import com.google.gson.annotations.Expose;

public class AssociatedMfpFriend {
    @Expose
    private int commonFriends;
    @Expose
    private String emailOrId;
    @Expose
    private String mfpUsername;

    public AssociatedMfpFriend() {
    }

    public AssociatedMfpFriend(String str, int i, String str2) {
        this.mfpUsername = str;
        this.commonFriends = i;
        this.emailOrId = str2;
    }

    public String getMfpUsername() {
        return this.mfpUsername;
    }

    public void setMfpUsername(String str) {
        this.mfpUsername = str;
    }

    public int getCommonFriends() {
        return this.commonFriends;
    }

    public void setCommonFriends(int i) {
        this.commonFriends = i;
    }

    public String getEmailOrId() {
        return this.emailOrId;
    }

    public void setEmailOrId(String str) {
        this.emailOrId = str;
    }
}
