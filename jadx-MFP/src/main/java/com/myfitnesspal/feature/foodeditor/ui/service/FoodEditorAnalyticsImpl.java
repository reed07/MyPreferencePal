package com.myfitnesspal.feature.foodeditor.ui.service;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras;
import com.myfitnesspal.feature.search.model.SearchResultType;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FoodEditorAnalyticsImpl implements FoodEditorAnalytics {
    private static final String ANLT_EVENT_FOOD_DETAILS_BANNER_CLICKED = "food_details_info_ad_tapped";
    private static final String ANLT_EVENT_FOOD_DETAILS_BANNER_DISPLAYED = "food_details_info_ad_displayed";
    private static final String DEFAULT_RECIPE_COUNT = "1";
    final Lazy<ActionTrackingService> actionTrackingService;
    final Lazy<AnalyticsService> analyticsService;
    final Lazy<CountryService> countryService;
    final Lazy<DiaryService> diaryService;

    public FoodEditorAnalyticsImpl(Lazy<DiaryService> lazy, Lazy<AnalyticsService> lazy2, Lazy<ActionTrackingService> lazy3, Lazy<CountryService> lazy4) {
        this.diaryService = lazy;
        this.analyticsService = lazy2;
        this.actionTrackingService = lazy3;
        this.countryService = lazy4;
    }

    public void reportFoodAddedToDiary(String str, MfpFood mfpFood, String str2, String str3, String str4, Date date, TimeValue timeValue, String str5, int i) {
        String str6 = str4;
        String str7 = str;
        String str8 = str2;
        String str9 = str3;
        Date date2 = date;
        reportFoodAddedToDiary(str7, str8, str9, str6, date2, "", Builder.fromMfpFood(mfpFood).source(str6).build(), timeValue, str5, i);
    }

    public void reportFoodAddedToDiary(String str, MfpFood mfpFood, String str2, String str3, String str4, Date date, String str5, int i, int i2, String str6, TimeValue timeValue, String str7, int i3) {
        String str8 = str4;
        String str9 = str6;
        reportFoodAddedToDiary(str, str2, str3, str8, date, str9, Builder.fromMfpFood(mfpFood).searchTerm(str5).index(i2).servingSizeIndex(i).source(str8).listType(str9).build(), timeValue, str7, i3);
    }

    private void reportFoodAddedToDiary(String str, String str2, String str3, String str4, Date date, String str5, FoodV2Logging foodV2Logging, TimeValue timeValue, String str6, int i) {
        String str7 = str6;
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.ADDFOOD_ADDBTN_CLICK);
        String str8 = Strings.notEmpty(str3) ? "scan" : str4;
        if (Strings.isEmpty(str8)) {
            str8 = "unknown";
        }
        String[] strArr = new String[18];
        strArr[0] = "flow_id";
        strArr[1] = str;
        strArr[2] = "meal";
        strArr[3] = str2.toLowerCase();
        strArr[4] = "source";
        strArr[5] = str8;
        strArr[6] = "locale";
        strArr[7] = ((CountryService) this.countryService.get()).getCurrentLocaleIdentifierForV2();
        strArr[8] = "foods";
        strArr[9] = new ApiJsonMapper().reverseMap((Object) Arrays.asList(new FoodV2Logging[]{foodV2Logging}));
        strArr[10] = "list_type";
        strArr[11] = str5;
        strArr[12] = Attributes.DIARY_DATE;
        strArr[13] = DateTimeUtils.diaryDateAnalyticsFormat(date);
        strArr[14] = Attributes.CONTAINS_FOOD_AD;
        strArr[15] = Strings.toString(Boolean.valueOf(foodV2Logging.getType() == SearchResultType.FOOD_AD));
        strArr[16] = "version";
        strArr[17] = Strings.toString(Integer.valueOf(i));
        Map createMap = MapUtil.createMap(strArr);
        if (str7 != null) {
            createMap.put(Attributes.CORRECTED, str7);
        }
        if (timeValue != null) {
            createMap.put(TimestampAnalyticsHelper.ATTR_TIME, timeValue.getAnalyticsName());
        }
        ((DiaryService) this.diaryService.get()).endFoodLoggingFlow(createMap);
        if (Strings.toBoolean(((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent("is_last_pressed_search", "is_last_pressed_search"))) {
            ActionTrackingService actionTrackingService2 = (ActionTrackingService) this.actionTrackingService.get();
            String str9 = Events.ONLINE_SEARCH_SUMMARY;
            String[] strArr2 = new String[4];
            strArr2[0] = Attributes.LOGGED;
            strArr2[1] = "yes";
            strArr2[2] = "flow_id";
            strArr2[3] = Strings.isEmpty(str) ? UUID.randomUUID().toString() : str;
            actionTrackingService2.appendToEvent(str9, MapUtil.createMap(strArr2));
            ((ActionTrackingService) this.actionTrackingService.get()).reportEventToAnalytics(Events.ONLINE_SEARCH_SUMMARY, Events.ONLINE_SEARCH_SUMMARY);
        }
    }

    public void reportRecipeAddedToDiary(String str, Date date, TimeValue timeValue) {
        Map createMap = MapUtil.createMap("meal", Strings.toString(str), Attributes.DIARY_DATE, DateTimeUtils.diaryDateAnalyticsFormat(date), Attributes.RECIPE_COUNT, "1");
        if (timeValue != null) {
            createMap.put(TimestampAnalyticsHelper.ATTR_TIME, timeValue.getAnalyticsName());
        }
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent("recipe_importer", createMap);
        if (Strings.notEmpty(((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent("recipe_importer", "source"))) {
            ((ActionTrackingService) this.actionTrackingService.get()).reportEventToAnalytics(Events.FOOD_LOGGED, "recipe_importer", true, "channel", Attributes.BOX_ONLY, "meal", Attributes.METADATA_EDITED, Attributes.RECIPE_COUNT, TimestampAnalyticsHelper.ATTR_TIME);
        }
    }

    public void reportFoodDeletion(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        hashMap.put(Attributes.NUM_DELETED, Strings.toString(Integer.valueOf(i)));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.FOOD_DELETED, (Map<String, String>) hashMap);
    }

    public void reportPairedFoodsLogged(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.PAIRED_FOOD_LOGGED, MapUtil.createMap(Attributes.NUMBER_OF_FOODS_LOGGED, Integer.toString(i)));
    }

    public void reportSponsoredFoodLogged(MfpFood mfpFood, String str, String str2, String str3, Date date, int i, FoodEditorAnalyticsExtras foodEditorAnalyticsExtras, int i2) {
        String listType = foodEditorAnalyticsExtras != null ? foodEditorAnalyticsExtras.getListType() : "";
        int i3 = 0;
        Builder index = Builder.fromMfpFood(mfpFood).searchTerm(foodEditorAnalyticsExtras != null ? foodEditorAnalyticsExtras.getSearchQuery() : null).index(foodEditorAnalyticsExtras != null ? foodEditorAnalyticsExtras.getResultsListPosition() : 0);
        if (foodEditorAnalyticsExtras != null) {
            i3 = foodEditorAnalyticsExtras.getPositionWithAd();
        }
        String str4 = str3;
        reportFoodAddedToDiary(null, str, str2, str4, date, listType, index.indexWithAd(i3).servingSizeIndex(i).source(str4).type(SearchResultType.FOOD_AD).build(), null, null, i2);
    }

    public void reportSponsoredFoodBannerDisplayed(String str, String str2, String str3, String str4) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ANLT_EVENT_FOOD_DETAILS_BANNER_DISPLAYED, MapUtil.createMap("flow_id", str, "food_id", str2, Attributes.FOOD_VERSION_ID, str3, "source", str4));
    }

    public void reportSponsoredFoodBannerClicked(String str, String str2, String str3, String str4) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(ANLT_EVENT_FOOD_DETAILS_BANNER_CLICKED, MapUtil.createMap("flow_id", str, "food_id", str2, Attributes.FOOD_VERSION_ID, str3, "source", str4));
    }
}
