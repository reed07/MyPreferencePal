package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.feature.search.model.SponsoredFood;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "kotlin.jvm.PlatformType", "sponsoredCategory", "", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchViewModel.kt */
final class OnlineFoodSearchViewModel$fetchSponsoredFood$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ OnlineFoodSearchViewModel this$0;

    OnlineFoodSearchViewModel$fetchSponsoredFood$1(OnlineFoodSearchViewModel onlineFoodSearchViewModel) {
        this.this$0 = onlineFoodSearchViewModel;
    }

    public final Single<SponsoredFood> apply(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "sponsoredCategory");
        if (!(str.length() == 0)) {
            this.this$0.getSponsoredCategory().postValue(str);
            return this.this$0.onlineSearchRepo.fetchSearchAd(str).timeout(2500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io());
        }
        throw new IllegalStateException("Category Empty".toString());
    }
}
