package com.myfitnesspal.feature.home.model;

import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.strings.GrammarService;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import dagger.Lazy;

public class ProfileHeaderModel {
    private final Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    private final Lazy<FriendService> friendService;
    private final Lazy<GrammarService> grammarService;
    private final boolean isChallengesAvailable;
    private final MiniUserInfoMapper miniUserInfoMapper;
    private final Lazy<PremiumAnalyticsHelper> premiumAnalyticsHelper;
    private final Lazy<PremiumService> premiumService;
    private final Lazy<UserSummaryService> userSummaryService;
    private final String userUid;
    private final Lazy<UserWeightService> userWeightService;

    public ProfileHeaderModel(Lazy<UserWeightService> lazy, Lazy<GrammarService> lazy2, Lazy<FriendService> lazy3, Lazy<UserSummaryService> lazy4, Lazy<ChallengesAnalyticsHelper> lazy5, Lazy<PremiumService> lazy6, Lazy<PremiumAnalyticsHelper> lazy7, MiniUserInfoMapper miniUserInfoMapper2, boolean z, String str) {
        this.userWeightService = lazy;
        this.grammarService = lazy2;
        this.friendService = lazy3;
        this.userSummaryService = lazy4;
        this.challengesAnalyticsHelper = lazy5;
        this.premiumService = lazy6;
        this.premiumAnalyticsHelper = lazy7;
        this.miniUserInfoMapper = miniUserInfoMapper2;
        this.isChallengesAvailable = z;
        this.userUid = str;
    }

    public Lazy<UserWeightService> getUserWeightService() {
        return this.userWeightService;
    }

    public Lazy<GrammarService> getGrammarService() {
        return this.grammarService;
    }

    public Lazy<FriendService> getFriendService() {
        return this.friendService;
    }

    public Lazy<UserSummaryService> getUserSummaryService() {
        return this.userSummaryService;
    }

    public Lazy<ChallengesAnalyticsHelper> getChallengesAnalyticsHelper() {
        return this.challengesAnalyticsHelper;
    }

    public Lazy<PremiumService> getPremiumService() {
        return this.premiumService;
    }

    public MiniUserInfoMapper getMiniUserInfoMapper() {
        return this.miniUserInfoMapper;
    }

    public boolean isChallengesAvailable() {
        return this.isChallengesAvailable;
    }

    public String getUserUid() {
        return this.userUid;
    }

    public Lazy<PremiumAnalyticsHelper> getPremiumAnalyticsHelper() {
        return this.premiumAnalyticsHelper;
    }
}
