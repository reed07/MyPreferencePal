package com.myfitnesspal.feature.search.ui.fragment;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$filterResults$1 implements Runnable {
    final /* synthetic */ String $query;
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$filterResults$1(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2, String str) {
        this.this$0 = localFoodSearchFragmentV2;
        this.$query = str;
    }

    public final void run() {
        LocalFoodSearchFragmentV2.access$getLocalSearchViewModel$p(this.this$0).setQuery(this.$query);
    }
}
