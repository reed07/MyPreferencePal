package com.myfitnesspal.shared.extension;

import android.os.Build.VERSION;
import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"isNougatOrHigher", "", "isOreoOrHigher", "isPieOrHigher", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "BuildUtil")
/* compiled from: BuildExt.kt */
public final class BuildUtil {
    public static final boolean isOreoOrHigher() {
        return VERSION.SDK_INT >= 26;
    }

    public static final boolean isNougatOrHigher() {
        return VERSION.SDK_INT >= 24;
    }

    public static final boolean isPieOrHigher() {
        return VERSION.SDK_INT >= 28;
    }
}
