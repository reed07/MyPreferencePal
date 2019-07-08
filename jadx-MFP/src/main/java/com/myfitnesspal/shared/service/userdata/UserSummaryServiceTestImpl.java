package com.myfitnesspal.shared.service.userdata;

import com.myfitnesspal.feature.debug.util.DebugSettingsService;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.RetrieveUserSummaryRequestPacket;
import com.uacf.core.util.Function1;
import com.uacf.core.util.ReturningFunction1;
import dagger.Lazy;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\fH\u0002R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/myfitnesspal/shared/service/userdata/UserSummaryServiceTestImpl;", "Lcom/myfitnesspal/shared/service/userdata/UserSummaryServiceImpl;", "api", "Ljavax/inject/Provider;", "Lcom/myfitnesspal/shared/api/v1/MfpInformationApi;", "debugSettingsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/debug/util/DebugSettingsService;", "(Ljavax/inject/Provider;Ldagger/Lazy;)V", "getDebugSettingsService", "()Ldagger/Lazy;", "fetchUserSummary", "Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "username", "", "userUid", "fetchUserSummaryAsync", "", "successCallback", "Lcom/uacf/core/util/Function1;", "errorCallback", "Lcom/myfitnesspal/shared/api/ApiErrorCallback;", "handleUserSummaryObject", "uso", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: UserSummaryServiceTestImpl.kt */
public final class UserSummaryServiceTestImpl extends UserSummaryServiceImpl {
    @NotNull
    private final Lazy<DebugSettingsService> debugSettingsService;

    public UserSummaryServiceTestImpl(@NotNull Provider<MfpInformationApi> provider, @NotNull Lazy<DebugSettingsService> lazy) {
        Intrinsics.checkParameterIsNotNull(provider, "api");
        Intrinsics.checkParameterIsNotNull(lazy, "debugSettingsService");
        super(provider);
        this.debugSettingsService = lazy;
    }

    @NotNull
    public final Lazy<DebugSettingsService> getDebugSettingsService() {
        return this.debugSettingsService;
    }

    public void fetchUserSummaryAsync(@NotNull String str, @Nullable String str2, @NotNull Function1<UserSummaryObject> function1, @NotNull ApiErrorCallback apiErrorCallback) {
        Intrinsics.checkParameterIsNotNull(str, "username");
        Intrinsics.checkParameterIsNotNull(function1, "successCallback");
        Intrinsics.checkParameterIsNotNull(apiErrorCallback, "errorCallback");
        ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new RetrieveUserSummaryRequestPacket(str, str2))).postAsync(new UserSummaryServiceTestImpl$fetchUserSummaryAsync$1<>(this, function1), apiErrorCallback, new PacketPayloadExtractor<>(120), null);
    }

    @NotNull
    public UserSummaryObject fetchUserSummary(@NotNull String str, @Nullable String str2) throws ApiException {
        Intrinsics.checkParameterIsNotNull(str, "username");
        UserSummaryObject userSummaryObject = (UserSummaryObject) ((MfpInformationApi) ((MfpInformationApi) this.api.get()).addPacket(new RetrieveUserSummaryRequestPacket(str, str2))).post((ReturningFunction1<TTransform, T>) new PacketPayloadExtractor<TTransform,T>(120), new Object[0]);
        Intrinsics.checkExpressionValueIsNotNull(userSummaryObject, "uso");
        handleUserSummaryObject(userSummaryObject);
        setCache(userSummaryObject);
        UserSummaryObject cache = getCache();
        Intrinsics.checkExpressionValueIsNotNull(cache, "cache");
        return cache;
    }

    /* access modifiers changed from: private */
    public final void handleUserSummaryObject(UserSummaryObject userSummaryObject) {
        if (((DebugSettingsService) this.debugSettingsService.get()).getLoginStreakDays() > 0) {
            userSummaryObject.setLoginStreak(((DebugSettingsService) this.debugSettingsService.get()).getLoginStreakDays());
        }
    }
}
