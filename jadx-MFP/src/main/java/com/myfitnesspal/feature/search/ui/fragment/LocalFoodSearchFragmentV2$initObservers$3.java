package com.myfitnesspal.feature.search.ui.fragment;

import android.arch.lifecycle.Observer;
import com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "query", "", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initObservers$3<T> implements Observer<String> {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initObservers$3(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
    }

    public final void onChanged(@Nullable String str) {
        if (str != null) {
            LocalFoodSearchAdapter access$getFoodAdapter$p = LocalFoodSearchFragmentV2.access$getFoodAdapter$p(this.this$0);
            Intrinsics.checkExpressionValueIsNotNull(str, "it");
            access$getFoodAdapter$p.setQuery(str);
        }
    }
}
