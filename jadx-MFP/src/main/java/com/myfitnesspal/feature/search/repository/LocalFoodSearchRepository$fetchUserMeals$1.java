package com.myfitnesspal.feature.search.repository;

import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.MealFood;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchRepository.kt */
final class LocalFoodSearchRepository$fetchUserMeals$1 extends Lambda implements Function0<List<DiaryEntryCellModel>> {
    final /* synthetic */ int $limit;
    final /* synthetic */ String $mealName;
    final /* synthetic */ SortOrder $sortOrder;
    final /* synthetic */ LocalFoodSearchRepository this$0;

    LocalFoodSearchRepository$fetchUserMeals$1(LocalFoodSearchRepository localFoodSearchRepository, SortOrder sortOrder, int i, String str) {
        this.this$0 = localFoodSearchRepository;
        this.$sortOrder = sortOrder;
        this.$limit = i;
        this.$mealName = str;
        super(0);
    }

    @NotNull
    public final List<DiaryEntryCellModel> invoke() {
        List<DiaryEntryCellModel> list;
        ArrayList fetchOwnedFoodsOfType = this.this$0.dbConnectionManager.foodDbAdapter().fetchOwnedFoodsOfType(3, this.$sortOrder, this.$limit + 1, 0);
        if (fetchOwnedFoodsOfType != null) {
            list = fetchOwnedFoodsOfType;
        } else {
            list = new ArrayList<>();
        }
        this.this$0.sortResultsList(list, this.$sortOrder);
        MealFood mealFoodFromMealEntries = ((MealUtil) this.this$0.mealUtil.get()).getMealFoodFromMealEntries(this.this$0.latestPreviousFoodEntriesForMealName(this.$mealName));
        if (mealFoodFromMealEntries != null) {
            list.add(0, mealFoodFromMealEntries);
        }
        return list;
    }
}
