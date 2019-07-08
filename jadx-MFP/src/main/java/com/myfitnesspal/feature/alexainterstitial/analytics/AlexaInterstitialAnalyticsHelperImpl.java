package com.myfitnesspal.feature.alexainterstitial.analytics;

import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.myfitnesspal.feature.alexainterstitial.ui.activity.AlexaInterstitialActivity.Mode;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/alexainterstitial/analytics/AlexaInterstitialAnalyticsHelperImpl;", "Lcom/myfitnesspal/feature/alexainterstitial/analytics/AlexaInterstitialAnalyticsHelper;", "analyticsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;)V", "getAnalyticsService", "()Ldagger/Lazy;", "reportInterstitialLearnMoreTapped", "", "mode", "Lcom/myfitnesspal/feature/alexainterstitial/ui/activity/AlexaInterstitialActivity$Mode;", "reportInterstitialSeen", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AlexaInterstitialAnalyticsHelperImpl.kt */
public final class AlexaInterstitialAnalyticsHelperImpl implements AlexaInterstitialAnalyticsHelper {
    private static final String ALEXA_INTERSTITIAL_LOG_WATER_LEARN_MORE = "alexa_interstitial_log_water_learn_more_tapped";
    private static final String ALEXA_INTERSTITIAL_LOG_WATER_SEEN = "alexa_interstitial_log_water_seen";
    private static final String ALEXA_INTERSTITIAL_LOG_WEIGHT_LEARN_MORE = "alexa_interstitial_log_weight_learn_more_tapped";
    private static final String ALEXA_INTERSTITIAL_LOG_WEIGHT_SEEN = "alexa_interstitial_log_weight_seen";
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Lazy<AnalyticsService> analyticsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/alexainterstitial/analytics/AlexaInterstitialAnalyticsHelperImpl$Companion;", "", "()V", "ALEXA_INTERSTITIAL_LOG_WATER_LEARN_MORE", "", "ALEXA_INTERSTITIAL_LOG_WATER_SEEN", "ALEXA_INTERSTITIAL_LOG_WEIGHT_LEARN_MORE", "ALEXA_INTERSTITIAL_LOG_WEIGHT_SEEN", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AlexaInterstitialAnalyticsHelperImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AlexaInterstitialAnalyticsHelperImpl(@NotNull Lazy<AnalyticsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsService");
        this.analyticsService = lazy;
    }

    @NotNull
    public final Lazy<AnalyticsService> getAnalyticsService() {
        return this.analyticsService;
    }

    public void reportInterstitialSeen(@NotNull Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, InternalAvidAdSessionContext.CONTEXT_MODE);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(mode == Mode.LOG_WATER ? ALEXA_INTERSTITIAL_LOG_WATER_SEEN : ALEXA_INTERSTITIAL_LOG_WEIGHT_SEEN);
    }

    public void reportInterstitialLearnMoreTapped(@NotNull Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, InternalAvidAdSessionContext.CONTEXT_MODE);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(mode == Mode.LOG_WATER ? ALEXA_INTERSTITIAL_LOG_WATER_LEARN_MORE : ALEXA_INTERSTITIAL_LOG_WEIGHT_LEARN_MORE);
    }
}
