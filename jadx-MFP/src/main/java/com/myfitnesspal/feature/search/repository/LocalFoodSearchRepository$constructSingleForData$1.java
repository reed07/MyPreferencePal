package com.myfitnesspal.feature.search.repository;

import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00040\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "kotlin.jvm.PlatformType", "", "call"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchRepository.kt */
final class LocalFoodSearchRepository$constructSingleForData$1<V> implements Callable<T> {
    final /* synthetic */ Function0 $fetchData;
    final /* synthetic */ SortOrder $sortOrder;
    final /* synthetic */ LocalFoodSearchRepository this$0;

    LocalFoodSearchRepository$constructSingleForData$1(LocalFoodSearchRepository localFoodSearchRepository, Function0 function0, SortOrder sortOrder) {
        this.this$0 = localFoodSearchRepository;
        this.$fetchData = function0;
        this.$sortOrder = sortOrder;
    }

    public final List<DiaryEntryCellModel> call() {
        List list = (List) this.$fetchData.invoke();
        SortOrder sortOrder = this.$sortOrder;
        if (sortOrder != null) {
            this.this$0.sortResultsList(list, sortOrder);
        }
        return this.this$0.dbConnectionManager.foodEntriesDbAdapter().replaceFoodsWithCorrespondingRecentFoodEntries(list);
    }
}
