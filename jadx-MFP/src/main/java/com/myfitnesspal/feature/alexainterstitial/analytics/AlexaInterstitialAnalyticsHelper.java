package com.myfitnesspal.feature.alexainterstitial.analytics;

import com.myfitnesspal.feature.alexainterstitial.ui.activity.AlexaInterstitialActivity.Mode;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/alexainterstitial/analytics/AlexaInterstitialAnalyticsHelper;", "", "reportInterstitialLearnMoreTapped", "", "mode", "Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity$Mode;", "reportInterstitialSeen", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AlexaInterstitialAnalyticsHelper.kt */
public interface AlexaInterstitialAnalyticsHelper {
    void reportInterstitialLearnMoreTapped(@NotNull Mode mode);

    void reportInterstitialSeen(@NotNull Mode mode);
}
