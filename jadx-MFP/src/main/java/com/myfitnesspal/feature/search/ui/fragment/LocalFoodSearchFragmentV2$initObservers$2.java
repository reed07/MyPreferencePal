package com.myfitnesspal.feature.search.ui.fragment;

import android.arch.lifecycle.Observer;
import com.myfitnesspal.shared.model.FoodImages;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "images", "Lcom/myfitnesspal/shared/model/FoodImages;", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initObservers$2<T> implements Observer<FoodImages> {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initObservers$2(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
    }

    public final void onChanged(@Nullable FoodImages foodImages) {
        if (foodImages != null) {
            LocalFoodSearchFragmentV2.access$getFoodAdapter$p(this.this$0).setFoodImages(foodImages);
            LocalFoodSearchFragmentV2.access$getFoodAdapter$p(this.this$0).notifyDataSetChanged();
        }
    }
}
