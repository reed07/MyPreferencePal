package com.myfitnesspal.feature.debug.util;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0016J\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\tH\u0003R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/myfitnesspal/feature/debug/util/DebugSettingsServiceImpl;", "Lcom/myfitnesspal/feature/debug/util/DebugSettingsService;", "prefs", "Ldagger/Lazy;", "Landroid/content/SharedPreferences;", "(Ldagger/Lazy;)V", "getPrefs", "()Ldagger/Lazy;", "getIntValue", "", "key", "", "getLoginStreakDays", "setLoginStreakDays", "", "days", "writeIntValue", "value", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: DebugSettingsServiceImpl.kt */
public final class DebugSettingsServiceImpl implements DebugSettingsService {
    public static final Companion Companion = new Companion(null);
    private static final String KEY_LOGIN_STREAK_DAYS = "debug_login_streak_days";
    @NotNull
    private final Lazy<SharedPreferences> prefs;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/debug/util/DebugSettingsServiceImpl$Companion;", "", "()V", "KEY_LOGIN_STREAK_DAYS", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: DebugSettingsServiceImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DebugSettingsServiceImpl(@NotNull Lazy<SharedPreferences> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "prefs");
        this.prefs = lazy;
    }

    @NotNull
    public final Lazy<SharedPreferences> getPrefs() {
        return this.prefs;
    }

    public void setLoginStreakDays(int i) {
        writeIntValue(KEY_LOGIN_STREAK_DAYS, i);
    }

    public int getLoginStreakDays() {
        return getIntValue(KEY_LOGIN_STREAK_DAYS);
    }

    @SuppressLint({"ApplySharedPref"})
    private final void writeIntValue(String str, int i) {
        ((SharedPreferences) this.prefs.get()).edit().putInt(str, i).commit();
    }

    private final int getIntValue(String str) {
        return ((SharedPreferences) this.prefs.get()).getInt(str, 0);
    }
}
