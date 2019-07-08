package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.model.ChallengePrize;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import java.util.List;

public class ChallengePrizesViewModel extends BaseViewModel {
    private ChallengeImageOutput bannerImage;
    private List<ChallengePrize> challengePrizeList;

    public ChallengePrizesViewModel(List<ChallengePrize> list, ChallengeImageOutput challengeImageOutput) {
        this.challengePrizeList = list;
        this.bannerImage = challengeImageOutput;
    }

    public List<ChallengePrize> getChallengePrizeList() {
        return this.challengePrizeList;
    }

    public ChallengeImageOutput getBannerImage() {
        return this.bannerImage;
    }
}
