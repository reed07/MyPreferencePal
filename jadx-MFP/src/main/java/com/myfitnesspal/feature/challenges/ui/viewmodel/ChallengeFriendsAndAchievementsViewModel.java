package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.feature.challenges.model.AvailableAchievementCriteria;
import com.myfitnesspal.feature.challenges.model.ChallengeAvailableAchievement;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.model.ChallengeSocial;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import java.util.List;

public class ChallengeFriendsAndAchievementsViewModel extends BaseViewModel {
    private ChallengeAchievementsViewModel achievementsViewModel;
    private ChallengeImageOutput bannerImage;
    private boolean challengeEnded;
    private ChallengeSocial challengeSocial;
    private ChallengeDetailsFriendsViewModel friendsViewModel;
    private List<ChallengeAvailableAchievement> mainAchievements;

    public ChallengeFriendsAndAchievementsViewModel(ChallengeAchievementsViewModel challengeAchievementsViewModel, ChallengeDetailsFriendsViewModel challengeDetailsFriendsViewModel, List<ChallengeAvailableAchievement> list, boolean z, ChallengeSocial challengeSocial2, ChallengeImageOutput challengeImageOutput) {
        this.achievementsViewModel = challengeAchievementsViewModel;
        this.friendsViewModel = challengeDetailsFriendsViewModel;
        this.mainAchievements = list;
        this.challengeEnded = z;
        this.challengeSocial = challengeSocial2;
        this.bannerImage = challengeImageOutput;
    }

    public ChallengeAchievementsViewModel getAchievementsViewModel() {
        return this.achievementsViewModel;
    }

    public ChallengeDetailsFriendsViewModel getFriendsViewModel() {
        return this.friendsViewModel;
    }

    public List<ChallengeAvailableAchievement> getMainAchievements() {
        return this.mainAchievements;
    }

    public boolean hasChallengeEnded() {
        return this.challengeEnded;
    }

    public ChallengeSocial getChallengeSocial() {
        return this.challengeSocial;
    }

    public String getCriteriaTypeForChallenge() {
        return ((AvailableAchievementCriteria) ((ChallengeAvailableAchievement) this.mainAchievements.get(0)).getCriteria().get(0)).getType();
    }

    public ChallengeImageOutput getBannerImage() {
        return this.bannerImage;
    }
}
