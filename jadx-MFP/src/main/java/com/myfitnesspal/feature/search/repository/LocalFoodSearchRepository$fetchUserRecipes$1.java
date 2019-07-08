package com.myfitnesspal.feature.search.repository;

import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchRepository.kt */
final class LocalFoodSearchRepository$fetchUserRecipes$1 extends Lambda implements Function0<List<DiaryEntryCellModel>> {
    final /* synthetic */ int $limit;
    final /* synthetic */ SortOrder $sortOrder;
    final /* synthetic */ LocalFoodSearchRepository this$0;

    LocalFoodSearchRepository$fetchUserRecipes$1(LocalFoodSearchRepository localFoodSearchRepository, SortOrder sortOrder, int i) {
        this.this$0 = localFoodSearchRepository;
        this.$sortOrder = sortOrder;
        this.$limit = i;
        super(0);
    }

    @NotNull
    public final List<DiaryEntryCellModel> invoke() {
        ArrayList fetchRecipeBoxItemsWithSortOrder = this.this$0.dbConnectionManager.recipeBoxItemsDBAdapter().fetchRecipeBoxItemsWithSortOrder(this.$sortOrder, this.$limit + 1, 0);
        Intrinsics.checkExpressionValueIsNotNull(fetchRecipeBoxItemsWithSortOrder, "dbConnectionManager.reci…(sortOrder, limit + 1, 0)");
        return SequencesKt.toMutableList(SequencesKt.map(CollectionsKt.asSequence(fetchRecipeBoxItemsWithSortOrder), new Function1<RecipeBoxItem, DiaryEntryCellModel>(this) {
            final /* synthetic */ LocalFoodSearchRepository$fetchUserRecipes$1 this$0;

            {
                this.this$0 = r1;
            }

            @NotNull
            public final DiaryEntryCellModel invoke(RecipeBoxItem recipeBoxItem) {
                RecipeFood recipeFood = recipeBoxItem.recipeFood(this.this$0.this$0.dbConnectionManager);
                if (recipeFood != null) {
                    return recipeFood;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.model.v1.DiaryEntryCellModel");
            }
        }));
    }
}
