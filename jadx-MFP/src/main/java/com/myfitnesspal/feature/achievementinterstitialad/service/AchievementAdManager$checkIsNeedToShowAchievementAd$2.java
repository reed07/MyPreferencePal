package com.myfitnesspal.feature.achievementinterstitialad.service;

import com.myfitnesspal.feature.achievementinterstitialad.service.Optional.Some;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "pair", "Lkotlin/Pair;", "", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
final class AchievementAdManager$checkIsNeedToShowAchievementAd$2<T, R> implements Function<T, R> {
    final /* synthetic */ AchievementAdManager this$0;

    AchievementAdManager$checkIsNeedToShowAchievementAd$2(AchievementAdManager achievementAdManager) {
        this.this$0 = achievementAdManager;
    }

    public /* bridge */ /* synthetic */ Object apply(Object obj) {
        return Boolean.valueOf(apply((Pair) obj));
    }

    public final boolean apply(@NotNull Pair<Boolean, Integer> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        if (((Boolean) pair.getFirst()).booleanValue()) {
            AchievementAdManager achievementAdManager = this.this$0;
            Object second = pair.getSecond();
            Intrinsics.checkExpressionValueIsNotNull(second, "pair.second");
            Optional achievementDayByStreakDay$app_googleRelease = achievementAdManager.achievementDayByStreakDay$app_googleRelease(((Number) second).intValue());
            if (achievementDayByStreakDay$app_googleRelease instanceof Some) {
                ((AchievementAdAnalyticsEvents) this.this$0.achievementAdAnalyticsEvents.get()).reportAchievementReachedEvent(((Number) ((Some) achievementDayByStreakDay$app_googleRelease).getValue()).intValue());
            }
        }
        return ((Boolean) pair.getFirst()).booleanValue();
    }
}
