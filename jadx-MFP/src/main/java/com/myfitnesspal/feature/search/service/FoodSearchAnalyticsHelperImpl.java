package com.myfitnesspal.feature.search.service;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.search.model.SearchResultType;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.feature.search.model.SponsoredFood;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet.AddItemOption;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.SearchResultItem;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple2;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 c2\u00020\u0001:\u0001cBK\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003¢\u0006\u0002\u0010\rJR\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00180 2\b\u0010!\u001a\u0004\u0018\u00010\u00182\b\u0010\"\u001a\u0004\u0018\u00010\u00182\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010(\u001a\u00020)H\u0002J6\u0010*\u001a\u00020+2\u0006\u0010!\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u00182\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020$0.2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\u0010\u00101\u001a\u00020+2\u0006\u0010!\u001a\u00020\u0018H\u0002J\b\u00102\u001a\u00020+H\u0016J\b\u00103\u001a\u00020+H\u0016J\b\u00104\u001a\u00020+H\u0016J\u0018\u00105\u001a\u00020+2\u0006\u00106\u001a\u0002072\u0006\u0010#\u001a\u00020$H\u0016J,\u00108\u001a\u00020+2\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010,\u001a\u0004\u0018\u00010\u00182\u0006\u0010;\u001a\u00020)2\u0006\u0010<\u001a\u00020=H\u0016J\u0010\u0010>\u001a\u00020+2\u0006\u0010?\u001a\u00020$H\u0016JD\u0010@\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u00182\b\u0010A\u001a\u0004\u0018\u00010\u00182\b\u0010B\u001a\u0004\u0018\u00010\u00182\f\u0010C\u001a\b\u0012\u0004\u0012\u00020E0D2\u0006\u0010F\u001a\u00020$2\u0006\u0010G\u001a\u00020)H\u0016J0\u0010H\u001a\u00020+2\u0006\u00106\u001a\u0002072\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016JJ\u0010H\u001a\u00020+2\b\u0010I\u001a\u0004\u0018\u00010J2\b\u0010!\u001a\u0004\u0018\u00010\u00182\b\u0010\"\u001a\u0004\u0018\u00010\u00182\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010\u00182\u0006\u0010(\u001a\u00020)H\u0016JF\u0010K\u001a\u00020+2\b\u0010A\u001a\u0004\u0018\u00010\u00182\u0006\u0010L\u001a\u00020\u00182\u0006\u0010M\u001a\u00020\u00182\b\u0010N\u001a\u0004\u0018\u00010\u00182\b\u0010'\u001a\u0004\u0018\u00010\u00182\u0006\u0010?\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010O\u001a\u00020+2\u0006\u0010P\u001a\u00020\u00182\u0006\u0010#\u001a\u00020$H\u0016J$\u0010Q\u001a\u00020+2\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010,\u001a\u0004\u0018\u00010\u00182\u0006\u0010;\u001a\u00020)H\u0016J\u0010\u0010R\u001a\u00020+2\u0006\u0010S\u001a\u00020)H\u0016J\u0010\u0010T\u001a\u00020+2\u0006\u0010U\u001a\u00020VH\u0016J\u0010\u0010W\u001a\u00020+2\u0006\u0010X\u001a\u00020)H\u0016J$\u0010Y\u001a\u00020+2\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010,\u001a\u0004\u0018\u00010\u00182\u0006\u0010<\u001a\u00020=H\u0016J\b\u0010Z\u001a\u00020+H\u0016J\u0010\u0010[\u001a\u00020+2\u0006\u0010I\u001a\u00020\\H\u0016J\u001a\u0010]\u001a\u00020\u00182\u0006\u0010;\u001a\u00020)2\b\u00109\u001a\u0004\u0018\u00010:H\u0002J\u0010\u0010^\u001a\u00020+2\u0006\u0010!\u001a\u00020\u0018H\u0016J \u0010_\u001a\n `*\u0004\u0018\u00010\u00180\u00182\u0006\u0010#\u001a\u00020$2\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010a\u001a\u00020+2\u0006\u0010b\u001a\u00020\u0018H\u0016R\u001b\u0010\u000e\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0012\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u00188BX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\b8BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0012\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Lcom/myfitnesspal/feature/search/service/FoodSearchAnalyticsHelperImpl;", "Lcom/myfitnesspal/feature/search/service/FoodSearchAnalyticsHelper;", "actionTrackingServiceLazy", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/ActionTrackingService;", "analyticsServiceLazy", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "multiAddFoodHelperLazy", "Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "diaryService", "Lcom/myfitnesspal/feature/diary/service/DiaryService;", "(Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;)V", "actionTrackingService", "getActionTrackingService", "()Lcom/myfitnesspal/shared/service/analytics/ActionTrackingService;", "actionTrackingService$delegate", "Lkotlin/Lazy;", "analyticsService", "getAnalyticsService", "()Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "analyticsService$delegate", "locale", "", "getLocale", "()Ljava/lang/String;", "multiAddFoodHelper", "getMultiAddFoodHelper", "()Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;", "multiAddFoodHelper$delegate", "convertFoodLookupParamsToMap", "", "searchFlowId", "query", "position", "", "source", "Lcom/myfitnesspal/feature/search/model/SearchSource;", "requestId", "isAdPresent", "", "fireFoodLoggedFromMultiAdd", "", "mealName", "mealAndRecipeFoodCount", "Lcom/uacf/core/util/Tuple2;", "timeValue", "Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$TimeValue;", "initOnlineSearchSummaryForAnalytics", "logEventsAndReportToAnalytics", "reportAddAllClickAnalyticsEvent", "reportBarcodeEvent", "reportDeletedRecentsEvent", "food", "Lcom/myfitnesspal/shared/model/v1/Food;", "reportDisplayOptionsClicked", "foodSearchTab", "Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "scopeByMeal", "sortOrder", "Lcom/myfitnesspal/feature/search/model/SortOrder;", "reportExisitingUserSawTooltipWithIndex", "index", "reportFoodLogged", "flowId", "channel", "foods", "", "Lcom/myfitnesspal/shared/model/FoodV2Logging;", "searchVersion", "multiAddEnabled", "reportFoodLookupEvent", "item", "Lcom/myfitnesspal/shared/model/v2/SearchResultItem;", "reportFoodSearchAdDisplayed", "foodId", "foodVersionId", "searchTerm", "reportFoodSearchItemClick", "localyticsTabName", "reportMealFilterChanged", "reportMultiAddEnabled", "isEnabled", "reportOnPause", "function", "Lcom/uacf/core/util/Function0;", "reportSearchEvent", "isInOnlineSearch", "reportSortOrderChanged", "reportToolbarPlusClicked", "reportToolbarPlusItemSelected", "Lcom/myfitnesspal/feature/search/ui/dialog/AddItemBottomSheet$AddItemOption;", "scopeByMealToAnltValue", "sendSearchAnalytics", "shiftPositionIfAdPresent", "kotlin.jvm.PlatformType", "updateFoodSearchBreadcrumb", "value", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodSearchAnalyticsHelperImpl.kt */
public final class FoodSearchAnalyticsHelperImpl implements FoodSearchAnalyticsHelper {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(FoodSearchAnalyticsHelperImpl.class), "actionTrackingService", "getActionTrackingService()Lcom/myfitnesspal/shared/service/analytics/ActionTrackingService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(FoodSearchAnalyticsHelperImpl.class), "analyticsService", "getAnalyticsService()Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(FoodSearchAnalyticsHelperImpl.class), "multiAddFoodHelper", "getMultiAddFoodHelper()Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;"))};
    private static final String ANLT_ATTR_INDEX_WITH_AD = "index_with_ad";
    private static final String ANLT_ATTR_ITEM = "item";
    private static final String ANLT_ATTR_MEAL_NAME = "meal_name";
    private static final String ANLT_ATTR_MULTI_ADD = "multi_add";
    private static final String ANLT_ATTR_NEW_VALUE = "new_value";
    private static final String ANLT_ATTR_OLD_VALUE = "old_value";
    private static final String ANLT_ATTR_PAGE_TYPE = "page_type";
    private static final String ANLT_ATTR_REQUEST_ID = "request_id";
    private static final String ANLT_ATTR_SCOPE = "scope";
    private static final String ANLT_ATTR_SETTING = "setting";
    private static final String ANLT_ATTR_SORT_ORDER = "sort_order";
    private static final String ANLT_ATTR_STEP = "step";
    private static final String ANLT_ATTR_TAB = "tab";
    private static final String ANLT_ATTR_TYPE = "type";
    private static final String ANLT_ATTR_VENUE_ID = "venue_id";
    private static final String ANLT_EVENT_ADD_FOOD_DISPLAY_OPTIONS_DISPLAYED = "add_food_display_options_displayed";
    private static final String ANLT_EVENT_EXISTING_USER_WALKTHROUGH_VIEWED = "existing_user_walkthrough_viewed";
    private static final String ANLT_EVENT_FOOD_SEARCH_AD_DISPLAYED = "food_search_ad_displayed";
    private static final String ANLT_EVENT_MEAL_FILTER_CHANGED = "meal_filter_changed";
    private static final String ANLT_EVENT_MULTI_ADD_BUTTON_TOGGLED = "multi_add_button_toggled";
    private static final String ANLT_EVENT_SEARCH_FOOD_SCREEN_OVERFLOW_TAPPED = "search_food_screen_overflow_tapped";
    private static final String ANLT_EVENT_SEARCH_FOOD_SCREEN_OVERFLOW_TAP_ITEM = "search_food_screen_overflow_tap_item";
    private static final String ANLT_EVENT_SORT_ORDER_CHANGED = "sort_order_changed";
    private static final String ANLT_VALUE_OFF = "off";
    private static final String ANLT_VALUE_ON = "on";
    private static final String ANLT_VALUE_SCOPE_BY_MEAL = "by_meal";
    private static final String ANLT_VALUE_SCOPE_UNKNOWN = "unknown";
    private static final String ANLT_VALUE_SCOPE_VALUE_ALL = "all";
    public static final Companion Companion = new Companion(null);
    private static final int POSITION_SPONSORED_FOOD = 0;
    private static final HashMap<Integer, String> existingUserWalkthroughStepToAnltName = MapsKt.hashMapOf(TuplesKt.to(Integer.valueOf(0), "1_add_items"), TuplesKt.to(Integer.valueOf(1), "2_your_history"), TuplesKt.to(Integer.valueOf(2), "3_sort_filters"), TuplesKt.to(Integer.valueOf(3), "4_completed"));
    private final Lazy actionTrackingService$delegate = LazyKt.lazy(new FoodSearchAnalyticsHelperImpl$actionTrackingService$2(this));
    /* access modifiers changed from: private */
    public final dagger.Lazy<ActionTrackingService> actionTrackingServiceLazy;
    private final Lazy analyticsService$delegate = LazyKt.lazy(new FoodSearchAnalyticsHelperImpl$analyticsService$2(this));
    /* access modifiers changed from: private */
    public final dagger.Lazy<AnalyticsService> analyticsServiceLazy;
    private final dagger.Lazy<CountryService> countryService;
    private final dagger.Lazy<DiaryService> diaryService;
    private final Lazy multiAddFoodHelper$delegate = LazyKt.lazy(new FoodSearchAnalyticsHelperImpl$multiAddFoodHelper$2(this));
    /* access modifiers changed from: private */
    public final dagger.Lazy<MultiAddFoodHelper> multiAddFoodHelperLazy;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!XT¢\u0006\u0002\n\u0000R*\u0010\"\u001a\u001e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00040#j\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0004`$X\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/myfitnesspal/feature/search/service/FoodSearchAnalyticsHelperImpl$Companion;", "", "()V", "ANLT_ATTR_INDEX_WITH_AD", "", "ANLT_ATTR_ITEM", "ANLT_ATTR_MEAL_NAME", "ANLT_ATTR_MULTI_ADD", "ANLT_ATTR_NEW_VALUE", "ANLT_ATTR_OLD_VALUE", "ANLT_ATTR_PAGE_TYPE", "ANLT_ATTR_REQUEST_ID", "ANLT_ATTR_SCOPE", "ANLT_ATTR_SETTING", "ANLT_ATTR_SORT_ORDER", "ANLT_ATTR_STEP", "ANLT_ATTR_TAB", "ANLT_ATTR_TYPE", "ANLT_ATTR_VENUE_ID", "ANLT_EVENT_ADD_FOOD_DISPLAY_OPTIONS_DISPLAYED", "ANLT_EVENT_EXISTING_USER_WALKTHROUGH_VIEWED", "ANLT_EVENT_FOOD_SEARCH_AD_DISPLAYED", "ANLT_EVENT_MEAL_FILTER_CHANGED", "ANLT_EVENT_MULTI_ADD_BUTTON_TOGGLED", "ANLT_EVENT_SEARCH_FOOD_SCREEN_OVERFLOW_TAPPED", "ANLT_EVENT_SEARCH_FOOD_SCREEN_OVERFLOW_TAP_ITEM", "ANLT_EVENT_SORT_ORDER_CHANGED", "ANLT_VALUE_OFF", "ANLT_VALUE_ON", "ANLT_VALUE_SCOPE_BY_MEAL", "ANLT_VALUE_SCOPE_UNKNOWN", "ANLT_VALUE_SCOPE_VALUE_ALL", "POSITION_SPONSORED_FOOD", "", "existingUserWalkthroughStepToAnltName", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodSearchAnalyticsHelperImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final ActionTrackingService getActionTrackingService() {
        Lazy lazy = this.actionTrackingService$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ActionTrackingService) lazy.getValue();
    }

    private final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (AnalyticsService) lazy.getValue();
    }

    private final MultiAddFoodHelper getMultiAddFoodHelper() {
        Lazy lazy = this.multiAddFoodHelper$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (MultiAddFoodHelper) lazy.getValue();
    }

    public FoodSearchAnalyticsHelperImpl(@NotNull dagger.Lazy<ActionTrackingService> lazy, @NotNull dagger.Lazy<AnalyticsService> lazy2, @NotNull dagger.Lazy<MultiAddFoodHelper> lazy3, @NotNull dagger.Lazy<CountryService> lazy4, @NotNull dagger.Lazy<DiaryService> lazy5) {
        Intrinsics.checkParameterIsNotNull(lazy, "actionTrackingServiceLazy");
        Intrinsics.checkParameterIsNotNull(lazy2, "analyticsServiceLazy");
        Intrinsics.checkParameterIsNotNull(lazy3, "multiAddFoodHelperLazy");
        Intrinsics.checkParameterIsNotNull(lazy4, "countryService");
        Intrinsics.checkParameterIsNotNull(lazy5, "diaryService");
        this.actionTrackingServiceLazy = lazy;
        this.analyticsServiceLazy = lazy2;
        this.multiAddFoodHelperLazy = lazy3;
        this.countryService = lazy4;
        this.diaryService = lazy5;
    }

    private final String getLocale() {
        return ((CountryService) this.countryService.get()).getCurrentLocaleIdentifierForV2();
    }

    public void reportOnPause(@NotNull Function0 function0) {
        Intrinsics.checkParameterIsNotNull(function0, "function");
        List breadCrumbs = getActionTrackingService().getBreadCrumbs(Attributes.FOOD_SEARCH_BREADCRUMB);
        if (!CollectionUtils.isEmpty((Collection<?>) breadCrumbs) && Intrinsics.areEqual((Object) (String) breadCrumbs.get(0), (Object) Attributes.SEARCH_VIEW)) {
            function0.execute();
        }
    }

    public void logEventsAndReportToAnalytics() {
        getActionTrackingService().reportEventToAnalytics(Events.ONLINE_SEARCH_SUMMARY, Events.ONLINE_SEARCH_SUMMARY);
    }

    public void reportBarcodeEvent() {
        getActionTrackingService().appendToEvent(Events.ONLINE_SEARCH_SUMMARY, "source", "scan");
    }

    public void updateFoodSearchBreadcrumb(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        ActionTrackingService actionTrackingService = getActionTrackingService();
        actionTrackingService.deleteBreadcrumbs(Attributes.FOOD_SEARCH_BREADCRUMB);
        actionTrackingService.addToBreadcrumbs(Attributes.FOOD_SEARCH_BREADCRUMB, str, "");
    }

    public void fireFoodLoggedFromMultiAdd(@NotNull String str, @NotNull String str2, @NotNull Tuple2<Integer, Integer> tuple2, @Nullable TimeValue timeValue) {
        String str3;
        Intrinsics.checkParameterIsNotNull(str, "searchFlowId");
        Intrinsics.checkParameterIsNotNull(str2, Extras.MEAL_NAME);
        Intrinsics.checkParameterIsNotNull(tuple2, "mealAndRecipeFoodCount");
        List foodV2LoggingList = getMultiAddFoodHelper().getFoodV2LoggingList();
        ActionTrackingService actionTrackingService = getActionTrackingService();
        String str4 = Events.FOOD_LOGGED;
        Pair[] pairArr = new Pair[12];
        pairArr[0] = TuplesKt.to("flow_id", str);
        pairArr[1] = TuplesKt.to("multi_add", "on");
        pairArr[2] = TuplesKt.to("meal", str2);
        pairArr[3] = TuplesKt.to("locale", ((CountryService) this.countryService.get()).getCurrentLocaleIdentifierForV2());
        pairArr[4] = TuplesKt.to("channel", "multi_add");
        pairArr[5] = TuplesKt.to("foods", new ApiJsonMapper().reverseMap((Object) foodV2LoggingList));
        pairArr[6] = TuplesKt.to(Attributes.ITEM_COUNT, Strings.toString(Integer.valueOf(CollectionUtils.size((Collection<?>) foodV2LoggingList))));
        String str5 = Attributes.DIARY_DATE;
        Object obj = this.diaryService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "diaryService.get()");
        DiaryDay diaryDayForActiveDateSync = ((DiaryService) obj).getDiaryDayForActiveDateSync();
        Intrinsics.checkExpressionValueIsNotNull(diaryDayForActiveDateSync, "diaryService.get().diaryDayForActiveDateSync");
        pairArr[7] = TuplesKt.to(str5, DateTimeUtils.diaryDateAnalyticsFormat(diaryDayForActiveDateSync.getDate()));
        pairArr[8] = TuplesKt.to(Attributes.MEAL_COUNT, Strings.toString(tuple2.getItem1()));
        pairArr[9] = TuplesKt.to(Attributes.RECIPE_COUNT, Strings.toString(tuple2.getItem2()));
        String str6 = Attributes.CONTAINS_FOOD_AD;
        com.myfitnesspal.shared.model.FoodV2Logging.Companion companion = FoodV2Logging.Companion;
        Intrinsics.checkExpressionValueIsNotNull(foodV2LoggingList, "foodV2LoggingList");
        pairArr[10] = TuplesKt.to(str6, Strings.toString(Boolean.valueOf(companion.listContainsAdFood(foodV2LoggingList))));
        String str7 = TimestampAnalyticsHelper.ATTR_TIME;
        if (timeValue != null) {
            str3 = timeValue.getAnalyticsName();
        } else {
            str3 = null;
        }
        pairArr[11] = TuplesKt.to(str7, str3);
        actionTrackingService.appendToEventAndReport(str4, MapsKt.mapOf(pairArr));
    }

    public void reportFoodLogged(@Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull List<FoodV2Logging> list, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(list, "foods");
        ActionTrackingService actionTrackingService = getActionTrackingService();
        String str4 = Events.FOOD_LOGGED;
        Pair[] pairArr = new Pair[8];
        String str5 = Attributes.DIARY_DATE;
        Object obj = this.diaryService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "diaryService.get()");
        DiaryDay diaryDayForActiveDateSync = ((DiaryService) obj).getDiaryDayForActiveDateSync();
        Intrinsics.checkExpressionValueIsNotNull(diaryDayForActiveDateSync, "diaryService.get().diaryDayForActiveDateSync");
        pairArr[0] = TuplesKt.to(str5, DateTimeUtils.diaryDateAnalyticsFormat(diaryDayForActiveDateSync.getDate()));
        pairArr[1] = TuplesKt.to("locale", ((CountryService) this.countryService.get()).getCurrentLocaleIdentifierForV2());
        pairArr[2] = TuplesKt.to("meal", str);
        pairArr[3] = TuplesKt.to("flow_id", str2);
        pairArr[4] = TuplesKt.to("channel", str3);
        pairArr[5] = TuplesKt.to("foods", new ApiJsonMapper().reverseMap((Object) list));
        pairArr[6] = TuplesKt.to("version", Strings.toString(Integer.valueOf(i)));
        pairArr[7] = TuplesKt.to("multi_add", z ? "on" : "off");
        actionTrackingService.appendToEventAndReport(str4, MapsKt.mapOf(pairArr));
    }

    public void reportAddAllClickAnalyticsEvent() {
        getAnalyticsService().reportEvent(Events.MULTIADD_ADDITEMSBTN_CLICK);
    }

    public void sendSearchAnalytics(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "searchFlowId");
        initOnlineSearchSummaryForAnalytics(str);
        ActionTrackingService actionTrackingService = getActionTrackingService();
        actionTrackingService.appendToEvent(Attributes.SEARCH_EVENT, Attributes.SEARCH_FLOWID, str);
        actionTrackingService.appendToEvent(Attributes.SEARCH_EVENT, "locale", getLocale());
        actionTrackingService.registerEvent(Events.SERVING_SIZE_LOOKUP, "flow_id", str);
    }

    private final void initOnlineSearchSummaryForAnalytics(String str) {
        ActionTrackingService actionTrackingService = getActionTrackingService();
        String trackingDataForEvent = actionTrackingService.getTrackingDataForEvent("channel", "referrer");
        String str2 = Events.ONLINE_SEARCH_SUMMARY;
        Pair[] pairArr = new Pair[9];
        pairArr[0] = TuplesKt.to("channel", trackingDataForEvent);
        pairArr[1] = TuplesKt.to(Attributes.CATEGORY, "food");
        pairArr[2] = TuplesKt.to("source", "online_search");
        pairArr[3] = TuplesKt.to(Attributes.LOGGED, "no");
        pairArr[4] = TuplesKt.to(Attributes.SELECTED_COUNT, Strings.toString(Integer.valueOf(0)));
        pairArr[5] = TuplesKt.to(Attributes.FIRST_SELECTION_INDEX, "none");
        pairArr[6] = TuplesKt.to(Attributes.LAST_SELECTION_INDEX, "none");
        pairArr[7] = TuplesKt.to(Attributes.RESULT_COUNT, "none");
        String str3 = "flow_id";
        if (!Strings.notEmpty(str)) {
            str = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(str, "UUID.randomUUID().toString()");
        }
        pairArr[8] = TuplesKt.to(str3, str);
        actionTrackingService.appendToEvent(str2, MapsKt.mapOf(pairArr));
    }

    public void reportFoodLookupEvent(@Nullable SearchResultItem searchResultItem, @Nullable String str, @Nullable String str2, int i, @Nullable SearchSource searchSource, @Nullable String str3, boolean z) {
        String str4;
        Map convertFoodLookupParamsToMap = convertFoodLookupParamsToMap(str, str2, i, searchSource, str3, z);
        if (searchResultItem instanceof MfpFood) {
            MfpFood mfpFood = (MfpFood) searchResultItem;
            convertFoodLookupParamsToMap.put("food_id", Strings.toString(mfpFood.getId()));
            convertFoodLookupParamsToMap.put(Attributes.FOOD_VERSION_ID, Strings.toString(mfpFood.getVersion()));
            String str5 = "type";
            if (searchResultItem instanceof SponsoredFood) {
                str4 = SearchResultType.FOOD_AD.getTitle();
            } else {
                str4 = SearchResultType.FOOD.getTitle();
            }
            convertFoodLookupParamsToMap.put(str5, str4);
        } else if (searchResultItem instanceof Venue) {
            convertFoodLookupParamsToMap.put("type", SearchResultType.VENUE.getTitle());
            convertFoodLookupParamsToMap.put(ANLT_ATTR_VENUE_ID, ((Venue) searchResultItem).getId());
        }
        getAnalyticsService().reportFoodLookup(convertFoodLookupParamsToMap);
    }

    public void reportFoodLookupEvent(@NotNull Food food, @NotNull String str, @NotNull String str2, int i, @NotNull SearchSource searchSource) {
        Intrinsics.checkParameterIsNotNull(food, "food");
        Intrinsics.checkParameterIsNotNull(str, "searchFlowId");
        Intrinsics.checkParameterIsNotNull(str2, "query");
        Intrinsics.checkParameterIsNotNull(searchSource, "source");
        Map convertFoodLookupParamsToMap$default = convertFoodLookupParamsToMap$default(this, str, str2, i, searchSource, null, false, 48, null);
        convertFoodLookupParamsToMap$default.put("food_id", Strings.toString(food.getOriginalUid()));
        convertFoodLookupParamsToMap$default.put(Attributes.FOOD_VERSION_ID, Strings.toString(food.getUid()));
        convertFoodLookupParamsToMap$default.put("type", SearchResultType.FOOD.getTitle());
        getAnalyticsService().reportFoodLookup(convertFoodLookupParamsToMap$default);
    }

    public void reportSearchEvent(boolean z) {
        getActionTrackingService().appendToEvent("is_last_pressed_search", "is_last_pressed_search", Strings.toString(Boolean.valueOf(z)));
    }

    public void reportFoodSearchItemClick(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "localyticsTabName");
        getAnalyticsService().reportEvent(Events.FOODSEARCH_RESULTITEM_CLICK, MapsKt.mapOf(TuplesKt.to("index", Integer.toString(i)), TuplesKt.to("list_type", str)));
    }

    public void reportDeletedRecentsEvent(@NotNull Food food, int i) {
        Intrinsics.checkParameterIsNotNull(food, "food");
        getAnalyticsService().reportEvent(Events.FOOD_DELETE_RECENTS, MapsKt.mapOf(TuplesKt.to("food_id", Strings.toString(food.getOriginalUid())), TuplesKt.to("index", Strings.toString(Integer.valueOf(i)))));
    }

    public void reportFoodSearchAdDisplayed(@Nullable String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable String str5, int i, @NotNull SearchSource searchSource) {
        Intrinsics.checkParameterIsNotNull(str2, "foodId");
        Intrinsics.checkParameterIsNotNull(str3, "foodVersionId");
        Intrinsics.checkParameterIsNotNull(searchSource, "source");
        getAnalyticsService().reportEvent(ANLT_EVENT_FOOD_SEARCH_AD_DISPLAYED, MapsKt.mapOf(TuplesKt.to("flow_id", str), TuplesKt.to("food_id", str2), TuplesKt.to(Attributes.FOOD_VERSION_ID, str3), TuplesKt.to("search_term", str4), TuplesKt.to("request_id", str5), TuplesKt.to("index", String.valueOf(i)), TuplesKt.to("source", searchSource.getTitle())));
    }

    public void reportToolbarPlusClicked() {
        getAnalyticsService().reportEvent(ANLT_EVENT_SEARCH_FOOD_SCREEN_OVERFLOW_TAPPED);
    }

    public void reportToolbarPlusItemSelected(@NotNull AddItemOption addItemOption) {
        Intrinsics.checkParameterIsNotNull(addItemOption, "item");
        getAnalyticsService().reportEvent("search_food_screen_overflow_tap_item", MapsKt.mapOf(TuplesKt.to("item", addItemOption.getAnltName())));
    }

    public void reportMultiAddEnabled(boolean z) {
        getAnalyticsService().reportEvent(ANLT_EVENT_MULTI_ADD_BUTTON_TOGGLED, MapsKt.mapOf(TuplesKt.to("setting", z ? "on" : "off")));
    }

    public void reportDisplayOptionsClicked(@Nullable FoodSearchTab foodSearchTab, @Nullable String str, boolean z, @NotNull SortOrder sortOrder) {
        Intrinsics.checkParameterIsNotNull(sortOrder, "sortOrder");
        AnalyticsService analyticsService = getAnalyticsService();
        String str2 = ANLT_EVENT_ADD_FOOD_DISPLAY_OPTIONS_DISPLAYED;
        Pair[] pairArr = new Pair[4];
        pairArr[0] = TuplesKt.to("tab", foodSearchTab != null ? foodSearchTab.getAnalyticsTabName() : null);
        pairArr[1] = TuplesKt.to("meal_name", str);
        pairArr[2] = TuplesKt.to("scope", scopeByMealToAnltValue(z, foodSearchTab));
        pairArr[3] = TuplesKt.to("sort_order", sortOrder.getAnltValue());
        analyticsService.reportEvent(str2, MapsKt.mapOf(pairArr));
    }

    public void reportMealFilterChanged(@Nullable FoodSearchTab foodSearchTab, @Nullable String str, boolean z) {
        getAnalyticsService().reportEvent(ANLT_EVENT_MEAL_FILTER_CHANGED, MapsKt.mapOf(TuplesKt.to("meal_name", str), TuplesKt.to(ANLT_ATTR_OLD_VALUE, scopeByMealToAnltValue(!z, foodSearchTab)), TuplesKt.to(ANLT_ATTR_NEW_VALUE, scopeByMealToAnltValue(z, foodSearchTab))));
    }

    public void reportSortOrderChanged(@Nullable FoodSearchTab foodSearchTab, @Nullable String str, @NotNull SortOrder sortOrder) {
        Intrinsics.checkParameterIsNotNull(sortOrder, "sortOrder");
        AnalyticsService analyticsService = getAnalyticsService();
        String str2 = ANLT_EVENT_SORT_ORDER_CHANGED;
        Pair[] pairArr = new Pair[3];
        pairArr[0] = TuplesKt.to("tab", foodSearchTab != null ? foodSearchTab.getAnalyticsTabName() : null);
        pairArr[1] = TuplesKt.to("page_type", str);
        pairArr[2] = TuplesKt.to("sort_order", sortOrder.getAnltValue());
        analyticsService.reportEvent(str2, MapsKt.mapOf(pairArr));
    }

    public void reportExisitingUserSawTooltipWithIndex(int i) {
        getAnalyticsService().reportEvent(ANLT_EVENT_EXISTING_USER_WALKTHROUGH_VIEWED, MapsKt.mapOf(TuplesKt.to(ANLT_ATTR_STEP, existingUserWalkthroughStepToAnltName.get(Integer.valueOf(i)))));
    }

    private final String scopeByMealToAnltValue(boolean z, FoodSearchTab foodSearchTab) {
        if (foodSearchTab != FoodSearchTab.ALL) {
            return "unknown";
        }
        return z ? ANLT_VALUE_SCOPE_BY_MEAL : "all";
    }

    static /* synthetic */ Map convertFoodLookupParamsToMap$default(FoodSearchAnalyticsHelperImpl foodSearchAnalyticsHelperImpl, String str, String str2, int i, SearchSource searchSource, String str3, boolean z, int i2, Object obj) {
        return foodSearchAnalyticsHelperImpl.convertFoodLookupParamsToMap(str, str2, i, searchSource, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? false : z);
    }

    private final Map<String, String> convertFoodLookupParamsToMap(String str, String str2, int i, SearchSource searchSource, String str3, boolean z) {
        Pair[] pairArr = new Pair[4];
        pairArr[0] = TuplesKt.to("flow_id", str);
        pairArr[1] = TuplesKt.to("search_term", Strings.toString(str2));
        pairArr[2] = TuplesKt.to("locale", getLocale());
        pairArr[3] = TuplesKt.to("source", searchSource != null ? searchSource.getTitle() : null);
        Map<String, String> mutableMapOf = MapsKt.mutableMapOf(pairArr);
        if (str3 != null) {
            mutableMapOf.put("request_id", str3);
        }
        if (z) {
            mutableMapOf.put("index", shiftPositionIfAdPresent(i, z));
            mutableMapOf.put(ANLT_ATTR_INDEX_WITH_AD, Strings.toString(Integer.valueOf(i)));
        } else {
            mutableMapOf.put("index", Strings.toString(Integer.valueOf(i)));
        }
        return mutableMapOf;
    }

    private final String shiftPositionIfAdPresent(int i, boolean z) {
        if (z && i > 0) {
            i--;
        }
        return Strings.toString(Integer.valueOf(i));
    }
}
