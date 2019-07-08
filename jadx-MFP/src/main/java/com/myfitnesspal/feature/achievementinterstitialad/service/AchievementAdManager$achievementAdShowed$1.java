package com.myfitnesspal.feature.achievementinterstitialad.service;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Completable;", "kotlin.jvm.PlatformType", "it", "", "apply", "(Lkotlin/Unit;)Lio/reactivex/Completable;"}, k = 3, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
final class AchievementAdManager$achievementAdShowed$1<T, R> implements Function<Unit, CompletableSource> {
    public static final AchievementAdManager$achievementAdShowed$1 INSTANCE = new AchievementAdManager$achievementAdShowed$1();

    AchievementAdManager$achievementAdShowed$1() {
    }

    public final Completable apply(@NotNull Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "it");
        return Completable.complete();
    }
}
