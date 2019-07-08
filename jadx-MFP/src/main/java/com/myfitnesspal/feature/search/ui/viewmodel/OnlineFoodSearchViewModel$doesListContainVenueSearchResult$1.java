package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.myfitnesspal.shared.model.v2.SearchResultItem;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/shared/model/v2/SearchResultItem;", "kotlin.jvm.PlatformType", "it", "Lcom/myfitnesspal/shared/model/v2/MfpFoodSearchResult;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchViewModel.kt */
final class OnlineFoodSearchViewModel$doesListContainVenueSearchResult$1 extends Lambda implements Function1<MfpFoodSearchResult, SearchResultItem> {
    public static final OnlineFoodSearchViewModel$doesListContainVenueSearchResult$1 INSTANCE = new OnlineFoodSearchViewModel$doesListContainVenueSearchResult$1();

    OnlineFoodSearchViewModel$doesListContainVenueSearchResult$1() {
        super(1);
    }

    public final SearchResultItem invoke(@NotNull MfpFoodSearchResult mfpFoodSearchResult) {
        Intrinsics.checkParameterIsNotNull(mfpFoodSearchResult, "it");
        return mfpFoodSearchResult.getSearchResultItem();
    }
}
