package com.myfitnesspal.feature.challenges.ui.viewmodel;

import android.content.Context;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.model.ChallengeMetric;
import com.myfitnesspal.feature.challenges.model.ChallengePrize;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.uacf.core.util.NumberUtils;
import java.util.List;

public class ChallengeDetailsSummaryViewModel extends BaseViewModel {
    private ChallengeImageOutput bannerImage;
    private String endAt;
    private ChallengePrize grandPrize;
    private boolean isChallengeActive;
    private int participantCount;
    private String startAt;
    private List<ChallengeMetric> vanityMetrics;

    public ChallengeDetailsSummaryViewModel(ChallengeImageOutput challengeImageOutput, String str, String str2, int i, List<ChallengeMetric> list, boolean z, ChallengePrize challengePrize) {
        this.bannerImage = challengeImageOutput;
        this.startAt = str;
        this.endAt = str2;
        this.participantCount = i;
        this.vanityMetrics = list;
        this.isChallengeActive = z;
        this.grandPrize = challengePrize;
    }

    public ChallengeImageOutput getBannerImage() {
        return this.bannerImage;
    }

    public String getStartAtAsString() {
        return this.startAt;
    }

    public String getEndAtAsString() {
        return this.endAt;
    }

    public String getParticipantCountAsString() {
        return NumberUtils.localeStringFromIntWithSeparators(this.participantCount);
    }

    public List<ChallengeMetric> getVanityMetrics() {
        return this.vanityMetrics;
    }

    public boolean isChallengeActive() {
        return this.isChallengeActive;
    }

    public String getTimeRemainingInChallenge(Context context) {
        return ChallengesUtil.getTimeRemainingString(context, getEndAtAsString());
    }

    public ChallengePrize getGrandPrize() {
        return this.grandPrize;
    }
}
