package com.myfitnesspal.feature.permissions;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;
import java.util.Arrays;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J!\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u000b\"\u00020\tH\u0002¢\u0006\u0002\u0010\fJ)\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u000b\"\u00020\t¢\u0006\u0002\u0010\u0010J1\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u000b\"\u00020\t¢\u0006\u0002\u0010\u0014J)\u0010\u0015\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u000b\"\u00020\t¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lcom/myfitnesspal/feature/permissions/PermissionAnalyticsHelper;", "", "analyticsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;)V", "getAnalyticsService", "()Ldagger/Lazy;", "getTypeAsString", "", "types", "", "([Ljava/lang/String;)Ljava/lang/String;", "reportUserPermissionAllowed", "", "screen", "(Ljava/lang/String;[Ljava/lang/String;)V", "reportUserPermissionDenied", "isCheckboxChecked", "", "(ZLjava/lang/String;[Ljava/lang/String;)V", "reportUserPermissionDisplayed", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: PermissionAnalyticsHelper.kt */
public final class PermissionAnalyticsHelper {
    @NotNull
    public static final String ATTRIBUTE_IS_CHECKBOX_CHECKED = "checkbox_checked";
    @NotNull
    public static final String ATTRIBUTE_SCREEN = "screen";
    @NotNull
    public static final String ATTRIBUTE_TYPE = "type";
    @NotNull
    public static final String CAMERA = "camera";
    @NotNull
    public static final String CONTACTS = "contacts";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EVENT_USER_PERMISSION_ALLOWED = "user_permission_allowed";
    @NotNull
    public static final String EVENT_USER_PERMISSION_DENIED = "user_permission_denied";
    @NotNull
    public static final String EVENT_USER_PERMISSION_DISPLAYED = "user_permission_displayed";
    @NotNull
    public static final String LOCATION = "location";
    @NotNull
    public static final String STORAGE = "storage";
    @NotNull
    private final Lazy<AnalyticsService> analyticsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/permissions/PermissionAnalyticsHelper$Companion;", "", "()V", "ATTRIBUTE_IS_CHECKBOX_CHECKED", "", "ATTRIBUTE_SCREEN", "ATTRIBUTE_TYPE", "CAMERA", "CONTACTS", "EVENT_USER_PERMISSION_ALLOWED", "EVENT_USER_PERMISSION_DENIED", "EVENT_USER_PERMISSION_DISPLAYED", "LOCATION", "STORAGE", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: PermissionAnalyticsHelper.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public PermissionAnalyticsHelper(@NotNull Lazy<AnalyticsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsService");
        this.analyticsService = lazy;
    }

    @NotNull
    public final Lazy<AnalyticsService> getAnalyticsService() {
        return this.analyticsService;
    }

    public final void reportUserPermissionDisplayed(@Nullable String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "types");
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str2 = EVENT_USER_PERMISSION_DISPLAYED;
        String[] strArr2 = new String[4];
        strArr2[0] = "screen";
        if (str == null) {
            str = "";
        }
        strArr2[1] = str;
        strArr2[2] = "type";
        strArr2[3] = getTypeAsString((String[]) Arrays.copyOf(strArr, strArr.length));
        analyticsService2.reportEvent(str2, MapUtil.createMap(strArr2));
    }

    public final void reportUserPermissionDenied(boolean z, @Nullable String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "types");
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str2 = EVENT_USER_PERMISSION_DENIED;
        String[] strArr2 = new String[6];
        strArr2[0] = "screen";
        if (str == null) {
            str = "";
        }
        strArr2[1] = str;
        strArr2[2] = "type";
        strArr2[3] = getTypeAsString((String[]) Arrays.copyOf(strArr, strArr.length));
        strArr2[4] = ATTRIBUTE_IS_CHECKBOX_CHECKED;
        strArr2[5] = String.valueOf(z);
        analyticsService2.reportEvent(str2, MapUtil.createMap(strArr2));
    }

    public final void reportUserPermissionAllowed(@Nullable String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "types");
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str2 = EVENT_USER_PERMISSION_ALLOWED;
        String[] strArr2 = new String[4];
        strArr2[0] = "screen";
        if (str == null) {
            str = "";
        }
        strArr2[1] = str;
        strArr2[2] = "type";
        strArr2[3] = getTypeAsString((String[]) Arrays.copyOf(strArr, strArr.length));
        analyticsService2.reportEvent(str2, MapUtil.createMap(strArr2));
    }

    private final String getTypeAsString(String... strArr) {
        return ArraysKt.joinToString$default((Object[]) strArr, (CharSequence) ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) PermissionAnalyticsHelper$getTypeAsString$1.INSTANCE, 30, (Object) null);
    }
}
