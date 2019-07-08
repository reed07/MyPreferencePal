package com.myfitnesspal.feature.search.ui.task;

import android.content.Context;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.model.FoodSearchModel;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.FoodImages;
import dagger.Lazy;
import java.util.List;

public class LocalFoodSearchTask extends EventedTaskBase<FoodSearchModel, Exception> {
    private static final int DEFAULT_SORT_ORDER = -1;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final int limit;
    private final String mealId;
    private final Lazy<SearchService> searchService;
    private final SortOrder sortOrder;
    private final int tabId;

    public static class CompletedEvent extends TaskEventBase<FoodSearchModel, Exception> {
    }

    public LocalFoodSearchTask(Lazy<SearchService> lazy, String str, int i, int i2, Lazy<DbConnectionManager> lazy2) {
        this(lazy, str, SortOrder.NONE, i, i2, lazy2);
    }

    public LocalFoodSearchTask(Lazy<SearchService> lazy, String str, SortOrder sortOrder2, int i, int i2, Lazy<DbConnectionManager> lazy2) {
        super((TaskEventBase) new CompletedEvent());
        this.searchService = lazy;
        this.mealId = str;
        this.tabId = i;
        this.limit = i2;
        this.sortOrder = sortOrder2;
        this.dbConnectionManager = lazy2;
    }

    /* access modifiers changed from: protected */
    public FoodSearchModel exec(Context context) {
        List list;
        if (this.sortOrder == SortOrder.NONE) {
            list = ((SearchService) this.searchService.get()).fetchResultsFromDBSync(this.mealId, this.tabId, this.limit);
        } else {
            list = ((SearchService) this.searchService.get()).fetchResultsFromDBSync(this.mealId, this.sortOrder, this.tabId, this.limit);
        }
        FoodImages foodImages = null;
        if (this.tabId == FoodSearchTab.MEALS.getTabId()) {
            foodImages = ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().fetchImagesForFoodsOfType(3);
        }
        return new FoodSearchModel(list, foodImages, this.limit);
    }
}
