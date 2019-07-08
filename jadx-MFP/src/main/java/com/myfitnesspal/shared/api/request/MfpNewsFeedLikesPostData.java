package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;

public class MfpNewsFeedLikesPostData {
    @Expose
    private String action;

    public static final class Actions {
        public static final String LIKE = "like";
        public static final String UNLIKE = "unlike";
    }

    public MfpNewsFeedLikesPostData(String str) {
        if (Strings.equalsIgnoreCase(str, Actions.LIKE) || Strings.equalsIgnoreCase(str, Actions.UNLIKE)) {
            this.action = str;
        } else {
            throw new IllegalArgumentException(String.format("action must be %s or %s, but was %s", new Object[]{Actions.LIKE, Actions.UNLIKE, str}));
        }
    }

    public String getAction() {
        return this.action;
    }

    public MfpNewsFeedLikesPostData setAction(String str) {
        this.action = str;
        return this;
    }
}
