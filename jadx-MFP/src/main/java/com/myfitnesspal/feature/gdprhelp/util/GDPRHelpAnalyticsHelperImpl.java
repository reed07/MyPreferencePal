package com.myfitnesspal.feature.gdprhelp.util;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/myfitnesspal/feature/gdprhelp/util/GDPRHelpAnalyticsHelperImpl;", "Lcom/myfitnesspal/feature/gdprhelp/util/GDPRHelpAnalyticsHelper;", "analyticsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;)V", "getAnalyticsService", "()Ldagger/Lazy;", "reportAboutUsSee", "", "reportFaqFeedbackSee", "reportHelpSee", "source", "", "reportLogout", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: GDPRHelpAnalyticsHelperImpl.kt */
public final class GDPRHelpAnalyticsHelperImpl implements GDPRHelpAnalyticsHelper {
    private static final String ABOUT_US_SEE = "about_us_see";
    public static final Companion Companion = new Companion(null);
    private static final String FAQ_FEEDBACK_SEE = "faqs_feedback_see";
    private static final String HELP_LOGOUT = "tos_or_consent_interruption_help_logout";
    @NotNull
    public static final String HELP_SEE = "tos_or_consent_interruption_help_see";
    @NotNull
    private final Lazy<AnalyticsService> analyticsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/gdprhelp/util/GDPRHelpAnalyticsHelperImpl$Companion;", "", "()V", "ABOUT_US_SEE", "", "FAQ_FEEDBACK_SEE", "HELP_LOGOUT", "HELP_SEE", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: GDPRHelpAnalyticsHelperImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public GDPRHelpAnalyticsHelperImpl(@NotNull Lazy<AnalyticsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsService");
        this.analyticsService = lazy;
    }

    @NotNull
    public final Lazy<AnalyticsService> getAnalyticsService() {
        return this.analyticsService;
    }

    public void reportHelpSee(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "source");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(HELP_SEE, MapUtil.createMap("source", str));
    }

    public void reportAboutUsSee() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ABOUT_US_SEE, MapUtil.createMap("source", HELP_SEE));
    }

    public void reportLogout() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(HELP_LOGOUT);
    }

    public void reportFaqFeedbackSee() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FAQ_FEEDBACK_SEE, MapUtil.createMap("source", HELP_SEE));
    }
}
