package com.myfitnesspal.shared.util;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"APP_PACKAGE_NAME", "", "PACKAGE_NAME", "intentTo", "Landroid/content/Intent;", "module", "Lcom/myfitnesspal/shared/util/MfpModuleLibrary;", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "ModuleHelper")
/* compiled from: ModuleHelper.kt */
public final class ModuleHelper {
    private static final String APP_PACKAGE_NAME = "com.myfitnesspal.android";
    private static final String PACKAGE_NAME = "com.myfitnesspal";

    @NotNull
    public static final Intent intentTo(@NotNull MfpModuleLibrary mfpModuleLibrary) {
        Intrinsics.checkParameterIsNotNull(mfpModuleLibrary, "module");
        Intent className = new Intent("android.intent.action.VIEW").setClassName("com.myfitnesspal.android", mfpModuleLibrary.getActivityName());
        Intrinsics.checkExpressionValueIsNotNull(className, "Intent(Intent.ACTION_VIE…AME, module.activityName)");
        return className;
    }
}
