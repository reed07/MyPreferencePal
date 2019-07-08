package com.myfitnesspal.feature.achievementinterstitialad.service;

import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "streakDay", "apply", "(Ljava/lang/Integer;)Lkotlin/Pair;"}, k = 3, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
final class AchievementAdManager$checkIsNeedToShowAchievementAd$1<T, R> implements Function<T, R> {
    final /* synthetic */ AchievementAdManager this$0;

    AchievementAdManager$checkIsNeedToShowAchievementAd$1(AchievementAdManager achievementAdManager) {
        this.this$0 = achievementAdManager;
    }

    @NotNull
    public final Pair<Boolean, Integer> apply(@NotNull Integer num) {
        Integer num2;
        Intrinsics.checkParameterIsNotNull(num, "streakDay");
        int size = AchievementAdDefines.INSTANCE.getACHIEVEMENT_DAYS().size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            Object obj = AchievementAdDefines.INSTANCE.getACHIEVEMENT_DAYS().get(i);
            Intrinsics.checkExpressionValueIsNotNull(obj, "AchievementAdDefines.ACHIEVEMENT_DAYS[i]");
            int intValue = ((Number) obj).intValue();
            if (i < AchievementAdDefines.INSTANCE.getACHIEVEMENT_DAYS().size() - 1) {
                num2 = (Integer) AchievementAdDefines.INSTANCE.getACHIEVEMENT_DAYS().get(i + 1);
            } else {
                num2 = Integer.valueOf(Integer.MAX_VALUE);
            }
            Intrinsics.checkExpressionValueIsNotNull(num2, "if (i < AchievementAdDef…                        }");
            if (this.this$0.checkIsNeedToShowInterstitialAdForDay(num.intValue(), intValue, num2.intValue())) {
                z = true;
                break;
            }
            i++;
        }
        return new Pair<>(Boolean.valueOf(z), num);
    }
}
