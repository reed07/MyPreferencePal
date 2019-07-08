package com.myfitnesspal.feature.explore.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics;
import com.myfitnesspal.feature.profile.ui.view.ChallengesCard;

public class NewChallengesCard extends ChallengesCard {
    private ExploreAnalytics exploreAnalytics = new ExploreAnalytics();

    public String getAnalyticsType() {
        return "challenges";
    }

    public NewChallengesCard(@NonNull Context context) {
        super(context);
    }

    public NewChallengesCard(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NewChallengesCard(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void reportCardTapped() {
        this.exploreAnalytics.reportCardTapped(getAnalyticsType());
    }

    /* access modifiers changed from: protected */
    public void reportChallengeTapped(String str, String str2) {
        this.exploreAnalytics.reportChallengeTapped(str, str2);
    }
}
