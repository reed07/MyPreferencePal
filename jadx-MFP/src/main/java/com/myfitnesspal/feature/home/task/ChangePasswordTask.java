package com.myfitnesspal.feature.home.task;

import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0014¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/feature/home/task/ChangePasswordTask;", "Lcom/myfitnesspal/framework/taskrunner/EventedTaskBase$Unchecked;", "", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "configService", "Lcom/myfitnesspal/shared/service/config/ConfigService;", "(Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;Lcom/myfitnesspal/shared/service/config/ConfigService;)V", "exec", "context", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Boolean;", "CompletedEvent", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ChangePasswordTask.kt */
public final class ChangePasswordTask extends Unchecked<Boolean> {
    private final ConfigService configService;
    private final LocalSettingsService localSettingsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/feature/home/task/ChangePasswordTask$CompletedEvent;", "Lcom/myfitnesspal/framework/taskrunner/TaskEventBase$Unchecked;", "", "()V", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ChangePasswordTask.kt */
    public static final class CompletedEvent extends TaskEventBase.Unchecked<Boolean> {
    }

    public ChangePasswordTask(@NotNull LocalSettingsService localSettingsService2, @NotNull ConfigService configService2) {
        Intrinsics.checkParameterIsNotNull(localSettingsService2, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(configService2, "configService");
        super((TaskEventBase) new CompletedEvent());
        this.localSettingsService = localSettingsService2;
        this.configService = configService2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean exec(@org.jetbrains.annotations.NotNull android.content.Context r4) {
        /*
            r3 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            com.myfitnesspal.shared.service.config.ConfigService r4 = r3.configService
            boolean r4 = com.myfitnesspal.shared.util.ConfigUtils.isChangePasswordEnabled(r4)
            r0 = 0
            if (r4 == 0) goto L_0x003e
            java.util.Date r4 = new java.util.Date
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r1 = r3.localSettingsService
            long r1 = r1.getPasswordResetDateTime()
            r4.<init>(r1)
            boolean r4 = com.myfitnesspal.shared.util.DateTimeUtils.isDateInThePast(r4)
            if (r4 == 0) goto L_0x003e
            com.uacf.identity.sdk.model.UacfAccountStatus r4 = com.uacf.identity.sdk.model.UacfAccountStatus.PASSWORD_CHANGE_REQUIRED     // Catch:{ UacfApiException -> 0x003e }
            com.uacf.identity.sdk.UacfIdentitySdk r1 = com.myfitnesspal.shared.api.auth.SSO.getSdk()     // Catch:{ UacfApiException -> 0x003e }
            java.lang.String r2 = "SSO.getSdk()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)     // Catch:{ UacfApiException -> 0x003e }
            com.uacf.identity.sdk.model.UacfUser r1 = r1.getCachedCurrentUser()     // Catch:{ UacfApiException -> 0x003e }
            if (r1 == 0) goto L_0x0037
            com.uacf.identity.sdk.model.UacfAccountStatus r1 = r1.getAccountStatus()     // Catch:{ UacfApiException -> 0x003e }
            if (r1 == 0) goto L_0x0037
            goto L_0x003b
        L_0x0037:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)     // Catch:{ UacfApiException -> 0x003e }
        L_0x003b:
            if (r4 != r1) goto L_0x003e
            r0 = 1
        L_0x003e:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.home.task.ChangePasswordTask.exec(android.content.Context):java.lang.Boolean");
    }
}
