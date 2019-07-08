package com.myfitnesspal.feature.achievementinterstitialad.service;

import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "userSummary", "Lcom/myfitnesspal/shared/model/v15/UserSummaryObject;", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
final class AchievementAdManager$whatIsStreakDayNow$1<T, R> implements Function<T, R> {
    public static final AchievementAdManager$whatIsStreakDayNow$1 INSTANCE = new AchievementAdManager$whatIsStreakDayNow$1();

    AchievementAdManager$whatIsStreakDayNow$1() {
    }

    public /* bridge */ /* synthetic */ Object apply(Object obj) {
        return Integer.valueOf(apply((UserSummaryObject) obj));
    }

    public final int apply(@Nullable UserSummaryObject userSummaryObject) {
        if (userSummaryObject != null) {
            return userSummaryObject.getLoginStreak();
        }
        return 0;
    }
}
