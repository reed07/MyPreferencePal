package com.myfitnesspal.feature.deleteaccount.service;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\u0007R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/myfitnesspal/feature/deleteaccount/service/DeleteAccountAnalyticsHelper;", "", "analyticsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;)V", "reportAccountDeleteButton", "", "reportAccountDeleteStarted", "source", "", "entryPoint", "reportAccountDeleteSuccessful", "consentType", "reportDeleteFailBackend", "reportDeleteFailPassword", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: DeleteAccountAnalyticsHelper.kt */
public final class DeleteAccountAnalyticsHelper {
    public static final Companion Companion = new Companion(null);
    private static final String EVENT_ACCOUNT_DELETE_BUTTON = "account_delete_button";
    private static final String EVENT_ACCOUNT_DELETE_FAIL_BACKEND = "account_delete_failure_backend_error";
    private static final String EVENT_ACCOUNT_DELETE_FAIL_PASSWORD = "account_delete_failure_password";
    private static final String EVENT_ACCOUNT_DELETE_STARTED = "account_delete_started";
    private static final String EVENT_ACCOUNT_DELETE_SUCCESSFUL = "account_delete_successful";
    private final Lazy<AnalyticsService> analyticsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/deleteaccount/service/DeleteAccountAnalyticsHelper$Companion;", "", "()V", "EVENT_ACCOUNT_DELETE_BUTTON", "", "EVENT_ACCOUNT_DELETE_FAIL_BACKEND", "EVENT_ACCOUNT_DELETE_FAIL_PASSWORD", "EVENT_ACCOUNT_DELETE_STARTED", "EVENT_ACCOUNT_DELETE_SUCCESSFUL", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: DeleteAccountAnalyticsHelper.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DeleteAccountAnalyticsHelper(@NotNull Lazy<AnalyticsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsService");
        this.analyticsService = lazy;
    }

    public final void reportAccountDeleteStarted(@NotNull String str, @NotNull String str2) {
        Map map;
        Intrinsics.checkParameterIsNotNull(str, "source");
        Intrinsics.checkParameterIsNotNull(str2, "entryPoint");
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str3 = EVENT_ACCOUNT_DELETE_STARTED;
        if (str.length() == 0) {
            map = MapUtil.createMap("entry_point", str2);
        } else {
            map = MapUtil.createMap("source", str, "entry_point", str2);
        }
        analyticsService2.reportEvent(str3, map);
    }

    public final void reportAccountDeleteButton() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_ACCOUNT_DELETE_BUTTON);
    }

    public final void reportAccountDeleteSuccessful(@NotNull String str, @NotNull String str2) {
        Map map;
        Intrinsics.checkParameterIsNotNull(str, "entryPoint");
        Intrinsics.checkParameterIsNotNull(str2, "consentType");
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str3 = EVENT_ACCOUNT_DELETE_SUCCESSFUL;
        if (Strings.isEmpty(str2)) {
            map = MapUtil.createMap("entry_point", str);
        } else {
            map = MapUtil.createMap("entry_point", str, Attributes.CONSENT_TYPE, str2);
        }
        analyticsService2.reportEvent(str3, map);
    }

    public final void reportDeleteFailPassword() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_ACCOUNT_DELETE_FAIL_PASSWORD);
    }

    public final void reportDeleteFailBackend() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_ACCOUNT_DELETE_FAIL_BACKEND);
    }
}
