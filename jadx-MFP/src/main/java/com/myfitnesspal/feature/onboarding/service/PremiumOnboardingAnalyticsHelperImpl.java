package com.myfitnesspal.feature.onboarding.service;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016J\b\u0010\u0015\u001a\u00020\fH\u0002R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/service/PremiumOnboardingAnalyticsHelperImpl;", "Lcom/myfitnesspal/feature/onboarding/service/PremiumOnboardingAnalyticsHelper;", "localSettingsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "analytics", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;Ldagger/Lazy;)V", "getAnalytics", "()Ldagger/Lazy;", "getLocalSettingsService", "crownTapped", "", "featureListSeen", "source", "", "manageGooglePlaySubscriptionLinkClicked", "onboardingDisplayed", "screen", "onboardingTapped", "tooltipDisplayed", "tooltipTapped", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PremiumOnboardingAnalyticsHelperImpl.kt */
public final class PremiumOnboardingAnalyticsHelperImpl implements PremiumOnboardingAnalyticsHelper {
    public static final Companion Companion = new Companion(null);
    private static final long DELAY_CROWN_TAP = 120;
    private static final String EVENT_MANAGE_SUBSCRIPTION_GOOGLE_PLAY_LINK_CLICKED = "manage_subscription_google_play_link_clicked";
    private static final String EVENT_PREMIUM_FEATURE_LIST_SEE = "premium_feature_list_see";
    private static final String EVENT_PREMIUM_ONBOARDING_DISPLAYED = "premium_onboarding_displayed";
    private static final String EVENT_PREMIUM_ONBOARDING_TAPPED = "premium_onboarding_tapped";
    private static final String EVENT_TOOL_TIP_DISPLAYED = "tool_tip_displayed";
    private static final String EVENT_TOOL_TIP_TAPPED = "tool_tip_tapped";
    private static final String KEY_CARD_TYPE = "card_type";
    private static final String KEY_SCREEN = "screen";
    private static final String KEY_SOURCE = "source";
    private static final String VALUE_HEADER_CROWN_TAPPED = "header_crown_tapped";
    private static final String VALUE_HOME_PREMIUM_CROWN = "home_premium_crown";
    private static final String VALUE_PREMIUM_ONBOARDING = "premium_onboarding";
    @NotNull
    private final Lazy<AnalyticsService> analytics;
    @NotNull
    private final Lazy<LocalSettingsService> localSettingsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/service/PremiumOnboardingAnalyticsHelperImpl$Companion;", "", "()V", "DELAY_CROWN_TAP", "", "EVENT_MANAGE_SUBSCRIPTION_GOOGLE_PLAY_LINK_CLICKED", "", "EVENT_PREMIUM_FEATURE_LIST_SEE", "EVENT_PREMIUM_ONBOARDING_DISPLAYED", "EVENT_PREMIUM_ONBOARDING_TAPPED", "EVENT_TOOL_TIP_DISPLAYED", "EVENT_TOOL_TIP_TAPPED", "KEY_CARD_TYPE", "KEY_SCREEN", "KEY_SOURCE", "VALUE_HEADER_CROWN_TAPPED", "VALUE_HOME_PREMIUM_CROWN", "VALUE_PREMIUM_ONBOARDING", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: PremiumOnboardingAnalyticsHelperImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PremiumOnboardingAnalyticsHelperImpl(@NotNull Lazy<LocalSettingsService> lazy, @NotNull Lazy<AnalyticsService> lazy2) {
        Intrinsics.checkParameterIsNotNull(lazy, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(lazy2, "analytics");
        this.localSettingsService = lazy;
        this.analytics = lazy2;
    }

    @NotNull
    public final Lazy<LocalSettingsService> getLocalSettingsService() {
        return this.localSettingsService;
    }

    @NotNull
    public final Lazy<AnalyticsService> getAnalytics() {
        return this.analytics;
    }

    public void onboardingDisplayed(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "screen");
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_PREMIUM_ONBOARDING_DISPLAYED, MapsKt.mapOf(TuplesKt.to("screen", str)));
    }

    public void onboardingTapped(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "screen");
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_PREMIUM_ONBOARDING_TAPPED, MapsKt.mapOf(TuplesKt.to("screen", str)));
    }

    public void featureListSeen() {
        featureListSeen(VALUE_PREMIUM_ONBOARDING);
    }

    private final void featureListSeen(String str) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_PREMIUM_FEATURE_LIST_SEE, MapsKt.mapOf(TuplesKt.to("source", str)));
    }

    public void manageGooglePlaySubscriptionLinkClicked() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_MANAGE_SUBSCRIPTION_GOOGLE_PLAY_LINK_CLICKED, MapsKt.mapOf(TuplesKt.to("source", VALUE_PREMIUM_ONBOARDING)));
    }

    public void tooltipDisplayed() {
        Object obj = this.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "localSettingsService.get()");
        long premiumCrownTooltipShownTime = ((LocalSettingsService) obj).getPremiumCrownTooltipShownTime();
        Object obj2 = this.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj2, "localSettingsService.get()");
        ((LocalSettingsService) obj2).setPremiumCrownTooltipShownTime(System.currentTimeMillis());
        if (premiumCrownTooltipShownTime == 0) {
            ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_TOOL_TIP_DISPLAYED, MapsKt.mapOf(TuplesKt.to("card_type", VALUE_HOME_PREMIUM_CROWN)));
        }
    }

    private final void tooltipTapped() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_TOOL_TIP_TAPPED, MapsKt.mapOf(TuplesKt.to("card_type", VALUE_HOME_PREMIUM_CROWN)));
    }

    public void crownTapped() {
        featureListSeen(VALUE_HEADER_CROWN_TAPPED);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long currentTimeMillis = System.currentTimeMillis();
        Object obj = this.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "localSettingsService.get()");
        if (timeUnit.toSeconds(currentTimeMillis - ((LocalSettingsService) obj).getPremiumCrownTooltipShownTime()) <= DELAY_CROWN_TAP) {
            tooltipTapped();
        }
    }
}
