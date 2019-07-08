package com.myfitnesspal.feature.achievementinterstitialad.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/service/StoredAchievementEvents;", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "clearStoredEvents", "", "isAchievementHasStoredEvents", "", "achievementDay", "", "makeKeyForAchievementDay", "", "previousShowedDate", "Ljava/util/Date;", "storeAchievementShowedEvent", "currentDateInMs", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: StoredAchievementEvents.kt */
public final class StoredAchievementEvents {
    private final SharedPreferences sharedPreferences;

    public StoredAchievementEvents(@NotNull SharedPreferences sharedPreferences2) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences2, "sharedPreferences");
        this.sharedPreferences = sharedPreferences2;
    }

    public static /* synthetic */ void storeAchievementShowedEvent$default(StoredAchievementEvents storedAchievementEvents, int i, long j, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j = new Date().getTime();
        }
        storedAchievementEvents.storeAchievementShowedEvent(i, j);
    }

    public final void storeAchievementShowedEvent(int i, long j) {
        this.sharedPreferences.edit().putLong(makeKeyForAchievementDay(i), j).commit();
    }

    public final boolean isAchievementHasStoredEvents(int i) {
        return this.sharedPreferences.contains(makeKeyForAchievementDay(i));
    }

    @NotNull
    public final Date previousShowedDate(int i) {
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        StringBuilder sb = new StringBuilder();
        sb.append(AchievementAdDefines.SHARED_PREFERENCES_ACHIEVEMENT_PREFIX);
        sb.append(i);
        return new Date(sharedPreferences2.getLong(sb.toString(), 0));
    }

    public final void clearStoredEvents() {
        Editor edit = this.sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        edit.clear();
        edit.apply();
    }

    private final String makeKeyForAchievementDay(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(AchievementAdDefines.SHARED_PREFERENCES_ACHIEVEMENT_PREFIX);
        sb.append(i);
        return sb.toString();
    }
}
