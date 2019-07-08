package com.myfitnesspal.feature.search.repository;

import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00040\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "kotlin.jvm.PlatformType", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchRepository.kt */
final class LocalFoodSearchRepository$fetchHistoryItems$1 extends Lambda implements Function0<List<DiaryEntryCellModel>> {
    final /* synthetic */ int $limit;
    final /* synthetic */ String $mealName;
    final /* synthetic */ SortOrder $sortOrder;
    final /* synthetic */ LocalFoodSearchRepository this$0;

    LocalFoodSearchRepository$fetchHistoryItems$1(LocalFoodSearchRepository localFoodSearchRepository, String str, SortOrder sortOrder, int i) {
        this.this$0 = localFoodSearchRepository;
        this.$mealName = str;
        this.$sortOrder = sortOrder;
        this.$limit = i;
        super(0);
    }

    public final List<DiaryEntryCellModel> invoke() {
        List<DiaryEntryCellModel> list;
        FoodEntriesDBAdapter foodEntriesDbAdapter = this.this$0.dbConnectionManager.foodEntriesDbAdapter();
        User user = ((Session) this.this$0.session.get()).getUser();
        int mealIdForName = ((LocalSettingsService) this.this$0.localSettingsService.get()).shouldShowAllMeals() ? 0 : user.getMealNames().mealIdForName(this.$mealName);
        if (this.$sortOrder == SortOrder.FREQUENTLY_USED) {
            list = foodEntriesDbAdapter.fetchFrequentFoodsForUserId(user.getLocalId(), mealIdForName, this.$limit + 1);
        } else {
            LocalFoodSearchRepository localFoodSearchRepository = this.this$0;
            Intrinsics.checkExpressionValueIsNotNull(foodEntriesDbAdapter, "foodEntriesDbAdapter");
            list = localFoodSearchRepository.fetchRecentFoods(foodEntriesDbAdapter, user.getLocalId(), mealIdForName, this.$limit + 1);
        }
        LocalFoodSearchRepository localFoodSearchRepository2 = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(list, "results");
        localFoodSearchRepository2.removeRecentlyDeletedItems(list);
        this.this$0.filterDuplicates(list);
        return list;
    }
}
