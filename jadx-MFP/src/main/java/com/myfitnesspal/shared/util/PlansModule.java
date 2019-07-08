package com.myfitnesspal.shared.util;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/shared/util/PlansModule;", "Lcom/myfitnesspal/shared/util/MfpModuleLibrary;", "()V", "activityName", "", "getActivityName", "()Ljava/lang/String;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ModuleHelper.kt */
public final class PlansModule implements MfpModuleLibrary {
    public static final PlansModule INSTANCE = new PlansModule();
    @NotNull
    private static final String activityName = activityName;

    private PlansModule() {
    }

    @NotNull
    public String getActivityName() {
        return activityName;
    }
}
