package com.myfitnesspal.feature.achievementinterstitialad.service;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0015\u0010\u0003\u001a\u00110\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional;", "", "p1", "Lkotlin/ParameterName;", "name", "streakDay", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
final class AchievementAdManager$achievementAdShowed$storeAchievementEventObserver$1 extends FunctionReference implements Function1<Integer, Optional<? extends Integer>> {
    AchievementAdManager$achievementAdShowed$storeAchievementEventObserver$1(AchievementAdManager achievementAdManager) {
        super(1, achievementAdManager);
    }

    public final String getName() {
        return "achievementDayByStreakDay";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(AchievementAdManager.class);
    }

    public final String getSignature() {
        return "achievementDayByStreakDay$app_googleRelease(I)Lcom/myfitnesspal/feature/achievementinterstitialad/service/Optional;";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    @NotNull
    public final Optional<Integer> invoke(int i) {
        return ((AchievementAdManager) this.receiver).achievementDayByStreakDay$app_googleRelease(i);
    }
}
