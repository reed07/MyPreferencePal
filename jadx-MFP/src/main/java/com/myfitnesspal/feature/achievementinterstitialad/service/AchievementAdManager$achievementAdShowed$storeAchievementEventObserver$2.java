package com.myfitnesspal.feature.achievementinterstitialad.service;

import com.myfitnesspal.feature.achievementinterstitialad.service.Optional.Some;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "achievementDayOptional", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional;", "", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
final class AchievementAdManager$achievementAdShowed$storeAchievementEventObserver$2<T, R> implements Function<T, R> {
    final /* synthetic */ AchievementAdManager this$0;

    AchievementAdManager$achievementAdShowed$storeAchievementEventObserver$2(AchievementAdManager achievementAdManager) {
        this.this$0 = achievementAdManager;
    }

    public /* bridge */ /* synthetic */ Object apply(Object obj) {
        apply((Optional) obj);
        return Unit.INSTANCE;
    }

    public final void apply(@NotNull Optional<Integer> optional) {
        Intrinsics.checkParameterIsNotNull(optional, "achievementDayOptional");
        if (optional instanceof Some) {
            Some some = (Some) optional;
            ((AchievementAdAnalyticsEvents) this.this$0.achievementAdAnalyticsEvents.get()).reportAchievementAdViewedEvent(((Number) some.getValue()).intValue());
            StoredAchievementEvents.storeAchievementShowedEvent$default((StoredAchievementEvents) this.this$0.storedAchievementEvents.get(), ((Number) some.getValue()).intValue(), 0, 2, null);
        }
    }
}
