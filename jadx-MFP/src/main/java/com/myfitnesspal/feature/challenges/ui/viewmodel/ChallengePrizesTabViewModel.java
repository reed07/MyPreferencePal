package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import java.util.List;

public class ChallengePrizesTabViewModel extends BaseViewModel {
    List<ChallengeAchievement> achievements;
    private ChallengeImageOutput bannerImage;
    private String challengeId;
    private String challengeName;
    private boolean isChallengePrivate;
    private ChallengePrizesViewModel prizesViewModel;

    public ChallengePrizesTabViewModel(ChallengeImageOutput challengeImageOutput, List<ChallengeAchievement> list, ChallengePrizesViewModel challengePrizesViewModel, String str, String str2, boolean z) {
        this.bannerImage = challengeImageOutput;
        this.achievements = list;
        this.prizesViewModel = challengePrizesViewModel;
        this.challengeName = str;
        this.challengeId = str2;
        this.isChallengePrivate = z;
    }

    public List<ChallengeAchievement> getAchievements() {
        return this.achievements;
    }

    public ChallengePrizesViewModel getPrizesViewModel() {
        return this.prizesViewModel;
    }

    public String getChallengeName() {
        return this.challengeName;
    }

    public String getChallengeId() {
        return this.challengeId;
    }

    public boolean isChallengePrivate() {
        return this.isChallengePrivate;
    }

    public ChallengeImageOutput getBannerImage() {
        return this.bannerImage;
    }
}
