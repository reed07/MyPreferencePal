package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import java.util.List;

public class JoinedSummaryViewModel extends BaseViewModel {
    private List<ChallengeAchievement> achievementList;
    private String challengeId;
    private String challengeName;
    private boolean isChallengeActive;
    private boolean isChallengePrivate;
    private String participantId;
    private JoinedProgressViewModel progressViewModel;
    private ChallengeDetailsSummaryViewModel summaryViewModel;

    public JoinedSummaryViewModel(ChallengeViewModel challengeViewModel) {
        this.summaryViewModel = challengeViewModel.getChallengeSummaryViewModel();
        this.progressViewModel = challengeViewModel.getJoinedProgressViewModel();
        this.achievementList = challengeViewModel.getChallengeAchievements(true);
        this.challengeId = challengeViewModel.getChallengeId();
        this.participantId = challengeViewModel.getParticipantId();
        this.isChallengeActive = challengeViewModel.isChallengeActive();
        this.challengeName = challengeViewModel.getChallengeTitle();
        this.isChallengePrivate = challengeViewModel.isChallengePrivate();
    }

    public ChallengeDetailsSummaryViewModel getSummaryViewModel() {
        return this.summaryViewModel;
    }

    public JoinedProgressViewModel getProgressViewModel() {
        return this.progressViewModel;
    }

    public List<ChallengeAchievement> getAchievementList() {
        return this.achievementList;
    }

    public String getChallengeId() {
        return this.challengeId;
    }

    public String getParticipantId() {
        return this.participantId;
    }

    public boolean isChallengeActive() {
        return this.isChallengeActive;
    }

    public String getChallengeName() {
        return this.challengeName;
    }

    public boolean isChallengePrivate() {
        return this.isChallengePrivate;
    }
}
