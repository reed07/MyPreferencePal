package com.myfitnesspal.feature.challenges.ui.adapter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ChallengeListItemWithTitle implements ChallengeListItem {
    int listType;
    private String title;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ListType {
        public static final int ACTIVE = 1;
        public static final int NEW = 0;
        public static final int PAST = 2;
    }

    public ChallengeListItemWithTitle(String str, int i) {
        this.title = str;
        this.listType = i;
    }

    public String getTitle() {
        return this.title;
    }

    public int getListType() {
        return this.listType;
    }
}
