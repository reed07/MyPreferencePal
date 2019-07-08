package com.myfitnesspal.feature.achievementinterstitialad.service;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
final class AchievementAdManager$sam$io_reactivex_functions_Function$0 implements Function {
    private final /* synthetic */ Function1 function;

    AchievementAdManager$sam$io_reactivex_functions_Function$0(Function1 function1) {
        this.function = function1;
    }

    public final /* synthetic */ Object apply(@NonNull Object obj) {
        return this.function.invoke(obj);
    }
}
