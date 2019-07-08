package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.feature.challenges.model.ChallengeDynamicTab;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import java.util.List;

public class ChallengeDetailsViewModel extends BaseViewModel {
    private String challengeGoal;
    private String challengeId;
    private String challengeSponsor;
    private String challengeTitle;
    private List<ChallengeDynamicTab> dynamicLinks;
    private ChallengeFriendsAndAchievementsViewModel friendsViewModel;
    private List<ChallengeAchievement> previewAchievements;
    private ChallengeDetailsSummaryViewModel summaryViewModel;

    public ChallengeDetailsViewModel(ChallengeViewModel challengeViewModel) {
        this.challengeId = challengeViewModel.getChallengeId();
        this.summaryViewModel = challengeViewModel.getChallengeSummaryViewModel();
        this.friendsViewModel = challengeViewModel.getChallengeFriendsAndAchievementsViewModel();
        this.previewAchievements = challengeViewModel.getChallengeAchievementsForPreview();
        this.challengeTitle = challengeViewModel.getChallengeTitle();
        this.challengeSponsor = challengeViewModel.getChallengeSponsor();
        this.challengeGoal = challengeViewModel.getChallengeGoal();
        this.dynamicLinks = challengeViewModel.getChallengeDynamicTabs();
    }

    public String getChallengeId() {
        return this.challengeId;
    }

    public ChallengeDetailsSummaryViewModel getSummaryViewModel() {
        return this.summaryViewModel;
    }

    public ChallengeFriendsAndAchievementsViewModel getFriendsViewModel() {
        return this.friendsViewModel;
    }

    public List<ChallengeAchievement> getPreviewAchievements() {
        return this.previewAchievements;
    }

    public String getChallengeTitle() {
        return this.challengeTitle;
    }

    public String getChallengeSponsor() {
        return this.challengeSponsor;
    }

    public String getChallengeGoal() {
        return this.challengeGoal;
    }

    public List<ChallengeDynamicTab> getDynamicLinks() {
        return this.dynamicLinks;
    }
}
