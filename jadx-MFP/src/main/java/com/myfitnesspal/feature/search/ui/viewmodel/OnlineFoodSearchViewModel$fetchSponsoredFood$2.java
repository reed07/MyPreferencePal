package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.feature.search.model.SponsoredFood;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "it", "", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchViewModel.kt */
final class OnlineFoodSearchViewModel$fetchSponsoredFood$2<T, R> implements Function<Throwable, SponsoredFood> {
    public static final OnlineFoodSearchViewModel$fetchSponsoredFood$2 INSTANCE = new OnlineFoodSearchViewModel$fetchSponsoredFood$2();

    OnlineFoodSearchViewModel$fetchSponsoredFood$2() {
    }

    @NotNull
    public final SponsoredFood apply(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "it");
        return SponsoredFood.Companion.getEMPTY();
    }
}
