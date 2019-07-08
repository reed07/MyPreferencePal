package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import java.util.List;

public class JoinedProgressViewModel extends BaseViewModel {
    private String challengeId;
    private String challengeName;
    private boolean isChallengePrivate;
    private List<ChallengeAchievement> overallProgressList;

    public JoinedProgressViewModel(List<ChallengeAchievement> list, String str, String str2, boolean z) {
        this.overallProgressList = list;
        this.challengeId = str;
        this.challengeName = str2;
        this.isChallengePrivate = z;
    }

    public List<ChallengeAchievement> getOverallProgressList() {
        return this.overallProgressList;
    }

    public String getChallengeId() {
        return this.challengeId;
    }

    public String getChallengeName() {
        return this.challengeName;
    }

    public boolean isChallengePrivate() {
        return this.isChallengePrivate;
    }
}
