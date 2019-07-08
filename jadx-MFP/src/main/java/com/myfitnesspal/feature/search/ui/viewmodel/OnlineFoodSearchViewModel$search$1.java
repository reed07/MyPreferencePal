package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.feature.search.model.OnlineSearchResult;
import com.myfitnesspal.feature.search.model.SponsoredFood;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.uacf.core.util.Tuple3;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/myfitnesspal/feature/search/model/OnlineSearchResult;", "kotlin.jvm.PlatformType", "sponsoredFood", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchViewModel.kt */
final class OnlineFoodSearchViewModel$search$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ String $query;
    final /* synthetic */ OnlineFoodSearchViewModel this$0;

    OnlineFoodSearchViewModel$search$1(OnlineFoodSearchViewModel onlineFoodSearchViewModel, String str) {
        this.this$0 = onlineFoodSearchViewModel;
        this.$query = str;
    }

    public final Single<OnlineSearchResult> apply(@NotNull final SponsoredFood sponsoredFood) {
        Intrinsics.checkParameterIsNotNull(sponsoredFood, "sponsoredFood");
        return this.this$0.onlineSearchRepo.queryFoods(this.$query, this.this$0.getFlowId(), this.this$0.getShouldShowVenues(), !Intrinsics.areEqual((Object) sponsoredFood, (Object) SponsoredFood.Companion.getEMPTY()), Intrinsics.areEqual((Object) (Boolean) this.this$0.isVerifiedFoodsOnly().getValue(), (Object) Boolean.valueOf(true))).subscribeOn(Schedulers.io()).map(new Function<T, R>() {
            @NotNull
            public final OnlineSearchResult apply(@NotNull Tuple3<ApiResponse<MfpFoodSearchResult>, String, String> tuple3) {
                Intrinsics.checkParameterIsNotNull(tuple3, "tuple");
                Object item1 = tuple3.getItem1();
                Intrinsics.checkExpressionValueIsNotNull(item1, "tuple.item1");
                if (((ApiResponse) item1).getItems() != null) {
                    Object item12 = tuple3.getItem1();
                    Intrinsics.checkExpressionValueIsNotNull(item12, "tuple.item1");
                    List items = ((ApiResponse) item12).getItems();
                    Intrinsics.checkExpressionValueIsNotNull(items, "tuple.item1.items");
                    return new OnlineSearchResult(items, (String) tuple3.getItem3(), (String) tuple3.getItem2(), sponsoredFood);
                }
                throw new IllegalArgumentException("Items list is null");
            }
        });
    }
}
