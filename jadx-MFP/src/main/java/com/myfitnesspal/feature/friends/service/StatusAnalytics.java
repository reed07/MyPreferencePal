package com.myfitnesspal.feature.friends.service;

import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;

public interface StatusAnalytics {
    public static final String EXTRA_SOURCE = "StatusAnalytics.EXTRA_SOURCE";

    public enum Source {
        Fab("FAB"),
        Profile("profile_screen"),
        Friends("friends_screen"),
        NewsFeed(AchievementAdDefines.ACHIEVEMENT_SHOWED_ON_NEWS_FEED),
        Unknown("unknown");
        
        private String analyticsValue;

        private Source(String str) {
            this.analyticsValue = str;
        }

        public String getAnalyticsValue() {
            return this.analyticsValue;
        }
    }

    void reportPostStatusUpdateCompleted(String str);

    void reportPostStatusUpdateFailed(String str);

    void reportPostStatusUpdateStarted();

    void reportStatusUpdateAddPhotoClicked();

    void reportStatusUpdateAddPhotoOptionClicked(String str);

    void reportStatusUpdateViewed();

    void reportUpdateStatusClicked(Source source);
}
