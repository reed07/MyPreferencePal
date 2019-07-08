package com.myfitnesspal.feature.profile.service;

import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import java.util.List;

public interface ProfileAggregatorService {

    public interface OfflineData {
        LocalizedWeight getCurrentWeight();

        MfpDailyGoal getDailyGoal();

        LocalizedWeight getGoalWeight();

        LocalizedWeight getStartingWeight();

        LocalizedWeight getWeightDelta();
    }

    public interface OnlineData {
        List<MfpPlatformApp> getApps();

        ProfileChallenges getChallenges();

        ProfileFriends getFriends();
    }

    public interface ProfileChallenges {
        List<ChallengeSummary> getActiveChallenges();

        int getNewChallengeCount();
    }

    public interface ProfileFriend {
        String getCity();

        String getCountryCode();

        String getId();

        String getImageUrl();

        String getState();

        String getUsername();
    }

    public interface ProfileFriends {
        List<ProfileFriend> getFriends();

        int getTotalFriendCount();
    }

    OfflineData getOfflineData();

    OnlineData getOnlineData() throws Throwable;
}
