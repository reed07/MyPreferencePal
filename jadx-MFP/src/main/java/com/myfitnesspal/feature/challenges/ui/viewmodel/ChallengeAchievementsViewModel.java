package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.feature.challenges.model.ChallengeAvailableAchievement;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import java.util.List;

public class ChallengeAchievementsViewModel extends BaseViewModel {
    private List<ChallengeAvailableAchievement> availableAchievementList;
    private String challengeId;
    private String challengeName;

    public ChallengeAchievementsViewModel(List<ChallengeAvailableAchievement> list, String str, String str2) {
        this.availableAchievementList = list;
        this.challengeName = str;
        this.challengeId = str2;
    }

    public List<ChallengeAvailableAchievement> getAvailableAchievementList() {
        return this.availableAchievementList;
    }

    public String getChallengeName() {
        return this.challengeName;
    }

    public String getChallengeId() {
        return this.challengeId;
    }
}
