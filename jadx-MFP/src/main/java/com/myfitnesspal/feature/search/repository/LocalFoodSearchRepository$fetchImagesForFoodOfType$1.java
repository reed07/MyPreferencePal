package com.myfitnesspal.feature.search.repository;

import com.myfitnesspal.shared.model.FoodImages;
import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/myfitnesspal/shared/model/FoodImages;", "kotlin.jvm.PlatformType", "call"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchRepository.kt */
final class LocalFoodSearchRepository$fetchImagesForFoodOfType$1<V> implements Callable<T> {
    final /* synthetic */ int $type;
    final /* synthetic */ LocalFoodSearchRepository this$0;

    LocalFoodSearchRepository$fetchImagesForFoodOfType$1(LocalFoodSearchRepository localFoodSearchRepository, int i) {
        this.this$0 = localFoodSearchRepository;
        this.$type = i;
    }

    public final FoodImages call() {
        return this.this$0.dbConnectionManager.foodDbAdapter().fetchImagesForFoodsOfType(this.$type);
    }
}
