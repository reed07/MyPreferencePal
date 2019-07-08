package com.myfitnesspal.feature.search.ui.viewmodel;

import com.myfitnesspal.shared.model.FoodImages;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "images", "Lcom/myfitnesspal/shared/model/FoodImages;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchViewModel.kt */
final class LocalFoodSearchViewModel$fetchImagesIfNecessary$1<T> implements Consumer<FoodImages> {
    final /* synthetic */ LocalFoodSearchViewModel this$0;

    LocalFoodSearchViewModel$fetchImagesIfNecessary$1(LocalFoodSearchViewModel localFoodSearchViewModel) {
        this.this$0 = localFoodSearchViewModel;
    }

    public final void accept(FoodImages foodImages) {
        this.this$0.getItemImages().postValue(foodImages);
    }
}
