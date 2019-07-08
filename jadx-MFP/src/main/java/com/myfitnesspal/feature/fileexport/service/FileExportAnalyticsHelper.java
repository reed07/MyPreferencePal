package com.myfitnesspal.feature.fileexport.service;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0007J\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/myfitnesspal/feature/fileexport/service/FileExportAnalyticsHelper;", "", "analyticsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;)V", "reportExportDataCustomerSupport", "", "reportExportDataRequestmade", "reportExportDataSee", "source", "", "reportFileExportCtaViewed", "reportFileExportIconClicked", "isPremium", "", "reportVerifyEmailRecheck", "reportVerifyEmailSee", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FileExportAnalyticsHelper.kt */
public final class FileExportAnalyticsHelper {
    private static final String ATTRIBUTE_SOURCE = "source";
    private static final String ATTRIBUTE_USER = "user";
    public static final Companion Companion = new Companion(null);
    private static final String EVENT_EXPORT_DATA_CUSTOMER_SUPPORT = "export_data_customer_support";
    private static final String EVENT_EXPORT_DATA_REQUEST_MADE = "export_data_request_made";
    private static final String EVENT_EXPORT_DATA_SEE = "export_data_see";
    private static final String EVENT_FILE_EXPORT_ICON_CLICKED = "file_export_icon_clicked";
    private static final String EVENT_FILE_EXPORT_SCREEN_PREMIUM_CTA_VIEWED = "file_export_screen_premium_cta_viewed";
    private static final String EVENT_VERIFY_EMAIL_RECHECK = "verify_email_recheck_tap";
    private static final String EVENT_VERIFY_EMAIL_SEE = "verify_email_see";
    @NotNull
    public static final String VALUE_SOURCE_NUTRITION = "nutrition";
    @NotNull
    public static final String VALUE_SOURCE_PROGRESS = "progress";
    private static final String VALUE_USER_NON_PREMIUM = "non_premium";
    private static final String VALUE_USER_PREMIUM = "premium";
    private final Lazy<AnalyticsService> analyticsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/myfitnesspal/feature/fileexport/service/FileExportAnalyticsHelper$Companion;", "", "()V", "ATTRIBUTE_SOURCE", "", "ATTRIBUTE_USER", "EVENT_EXPORT_DATA_CUSTOMER_SUPPORT", "EVENT_EXPORT_DATA_REQUEST_MADE", "EVENT_EXPORT_DATA_SEE", "EVENT_FILE_EXPORT_ICON_CLICKED", "EVENT_FILE_EXPORT_SCREEN_PREMIUM_CTA_VIEWED", "EVENT_VERIFY_EMAIL_RECHECK", "EVENT_VERIFY_EMAIL_SEE", "VALUE_SOURCE_NUTRITION", "VALUE_SOURCE_PROGRESS", "VALUE_USER_NON_PREMIUM", "VALUE_USER_PREMIUM", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FileExportAnalyticsHelper.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public FileExportAnalyticsHelper(@NotNull Lazy<AnalyticsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsService");
        this.analyticsService = lazy;
    }

    public final void reportFileExportIconClicked(@NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "source");
        Map hashMap = new HashMap();
        hashMap.put("source", str);
        hashMap.put("user", z ? "premium" : VALUE_USER_NON_PREMIUM);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FILE_EXPORT_ICON_CLICKED, hashMap);
    }

    public final void reportFileExportCtaViewed(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "source");
        Map hashMap = new HashMap();
        hashMap.put("source", str);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FILE_EXPORT_SCREEN_PREMIUM_CTA_VIEWED, hashMap);
    }

    public final void reportExportDataSee(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "source");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_EXPORT_DATA_SEE, MapUtil.createMap("source", str));
    }

    public final void reportExportDataRequestmade() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_EXPORT_DATA_REQUEST_MADE);
    }

    public final void reportExportDataCustomerSupport() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_EXPORT_DATA_CUSTOMER_SUPPORT);
    }

    public final void reportVerifyEmailSee(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "source");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_VERIFY_EMAIL_SEE, MapUtil.createMap("source", str));
    }

    public final void reportVerifyEmailRecheck() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_VERIFY_EMAIL_RECHECK);
    }
}
