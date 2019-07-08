package com.myfitnesspal.feature.challenges.ui.adapter;

import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeSummaryViewModel;

public class ChallengeSummaryListItem implements ChallengeListItem {
    private ChallengeSummaryViewModel challengeSummaryViewModel;

    public ChallengeSummaryListItem(ChallengeSummaryViewModel challengeSummaryViewModel2) {
        this.challengeSummaryViewModel = challengeSummaryViewModel2;
    }

    public ChallengeSummaryViewModel getChallengeSummary() {
        return this.challengeSummaryViewModel;
    }
}
