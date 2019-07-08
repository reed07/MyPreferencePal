package com.myfitnesspal.feature.search.service;

import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet.AddItemOption;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.SearchResultItem;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Tuple2;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH&J,\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\tH&JH\u0010\u001c\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u00052\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\b\b\u0002\u0010\"\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\u0017H&J0\u0010$\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010&\u001a\u00020'H&JN\u0010$\u001a\u00020\u00032\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010%\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010+\u001a\u00020\u0017H&JF\u0010,\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u00052\b\u0010/\u001a\u0004\u0018\u00010\u00052\b\u0010*\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010&\u001a\u00020'H&J\u0018\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\tH&J$\u00102\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u0017H&J\u0010\u00105\u001a\u00020\u00032\u0006\u00106\u001a\u000207H&J\u0010\u00108\u001a\u00020\u00032\u0006\u00109\u001a\u00020\u0017H&J$\u0010:\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0018\u001a\u00020\u0019H&J\b\u0010;\u001a\u00020\u0003H&J\u0010\u0010<\u001a\u00020\u00032\u0006\u0010(\u001a\u00020=H&J\u0010\u0010>\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010?\u001a\u00020\u00032\u0006\u0010@\u001a\u00020\u0005H&¨\u0006A"}, d2 = {"Lcom/myfitnesspal/feature/search/service/FoodSearchAnalyticsHelper;", "", "fireFoodLoggedFromMultiAdd", "", "searchFlowId", "", "mealName", "mealAndRecipeFoodCount", "Lcom/uacf/core/util/Tuple2;", "", "timeValue", "Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$TimeValue;", "logEventsAndReportToAnalytics", "reportAddAllClickAnalyticsEvent", "reportBarcodeEvent", "reportDeletedRecentsEvent", "food", "Lcom/myfitnesspal/shared/model/v1/Food;", "position", "reportDisplayOptionsClicked", "foodSearchTab", "Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "scopeByMeal", "", "sortOrder", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "reportExisitingUserSawTooltipWithIndex", "index", "reportFoodLogged", "flowId", "channel", "foods", "", "Lcom/myfitnesspal/shared/model/FoodV2Logging;", "searchVersion", "multiAddEnabled", "reportFoodLookupEvent", "query", "source", "Lcom/myfitnesspal/feature/search/model/SearchSource;", "item", "Lcom/myfitnesspal/shared/model/v2/SearchResultItem;", "requestId", "isAdPresent", "reportFoodSearchAdDisplayed", "foodId", "foodVersionId", "searchTerm", "reportFoodSearchItemClick", "localyticsTabName", "reportMealFilterChanged", "reportMultiAddEnabled", "isEnabled", "reportOnPause", "function", "Lcom/uacf/core/util/Function0;", "reportSearchEvent", "isInOnlineSearch", "reportSortOrderChanged", "reportToolbarPlusClicked", "reportToolbarPlusItemSelected", "Lcom/myfitnesspal/feature/search/ui/dialog/AddItemBottomSheet$AddItemOption;", "sendSearchAnalytics", "updateFoodSearchBreadcrumb", "value", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodSearchAnalyticsHelper.kt */
public interface FoodSearchAnalyticsHelper {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: FoodSearchAnalyticsHelper.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void reportFoodLogged$default(FoodSearchAnalyticsHelper foodSearchAnalyticsHelper, String str, String str2, String str3, List list, int i, boolean z, int i2, Object obj) {
            if (obj == null) {
                foodSearchAnalyticsHelper.reportFoodLogged(str, str2, str3, list, (i2 & 16) != 0 ? 1 : i, (i2 & 32) != 0 ? false : z);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reportFoodLogged");
        }

        public static /* synthetic */ void reportFoodLookupEvent$default(FoodSearchAnalyticsHelper foodSearchAnalyticsHelper, SearchResultItem searchResultItem, String str, String str2, int i, SearchSource searchSource, String str3, boolean z, int i2, Object obj) {
            if (obj == null) {
                foodSearchAnalyticsHelper.reportFoodLookupEvent(searchResultItem, str, str2, i, searchSource, (i2 & 32) != 0 ? null : str3, (i2 & 64) != 0 ? false : z);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reportFoodLookupEvent");
        }
    }

    void fireFoodLoggedFromMultiAdd(@NotNull String str, @NotNull String str2, @NotNull Tuple2<Integer, Integer> tuple2, @Nullable TimeValue timeValue);

    void logEventsAndReportToAnalytics();

    void reportAddAllClickAnalyticsEvent();

    void reportBarcodeEvent();

    void reportDeletedRecentsEvent(@NotNull Food food, int i);

    void reportDisplayOptionsClicked(@Nullable FoodSearchTab foodSearchTab, @Nullable String str, boolean z, @NotNull SortOrder sortOrder);

    void reportExisitingUserSawTooltipWithIndex(int i);

    void reportFoodLogged(@Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull List<FoodV2Logging> list, int i, boolean z);

    void reportFoodLookupEvent(@NotNull Food food, @NotNull String str, @NotNull String str2, int i, @NotNull SearchSource searchSource);

    void reportFoodLookupEvent(@Nullable SearchResultItem searchResultItem, @Nullable String str, @Nullable String str2, int i, @Nullable SearchSource searchSource, @Nullable String str3, boolean z);

    void reportFoodSearchAdDisplayed(@Nullable String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable String str5, int i, @NotNull SearchSource searchSource);

    void reportFoodSearchItemClick(@NotNull String str, int i);

    void reportMealFilterChanged(@Nullable FoodSearchTab foodSearchTab, @Nullable String str, boolean z);

    void reportMultiAddEnabled(boolean z);

    void reportOnPause(@NotNull Function0 function0);

    void reportSearchEvent(boolean z);

    void reportSortOrderChanged(@Nullable FoodSearchTab foodSearchTab, @Nullable String str, @NotNull SortOrder sortOrder);

    void reportToolbarPlusClicked();

    void reportToolbarPlusItemSelected(@NotNull AddItemOption addItemOption);

    void sendSearchAnalytics(@NotNull String str);

    void updateFoodSearchBreadcrumb(@NotNull String str);
}
