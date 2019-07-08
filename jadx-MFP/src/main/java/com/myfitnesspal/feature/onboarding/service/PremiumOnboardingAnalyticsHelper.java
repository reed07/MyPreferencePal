package com.myfitnesspal.feature.onboarding.service;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\n\u001a\u00020\u0003H&¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/service/PremiumOnboardingAnalyticsHelper;", "", "crownTapped", "", "featureListSeen", "manageGooglePlaySubscriptionLinkClicked", "onboardingDisplayed", "screen", "", "onboardingTapped", "tooltipDisplayed", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumOnboardingAnalyticsHelper.kt */
public interface PremiumOnboardingAnalyticsHelper {
    void crownTapped();

    void featureListSeen();

    void manageGooglePlaySubscriptionLinkClicked();

    void onboardingDisplayed(@NotNull String str);

    void onboardingTapped(@NotNull String str);

    void tooltipDisplayed();
}
