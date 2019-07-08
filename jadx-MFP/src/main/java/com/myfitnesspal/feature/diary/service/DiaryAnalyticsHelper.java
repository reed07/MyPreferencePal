package com.myfitnesspal.feature.diary.service;

import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Date;

public class DiaryAnalyticsHelper {
    private static final String ACTION_ATTRIBUTE = "action";
    private static final String ATTR_NAME_TIME = "time";
    private static final String ATTR_NAME_USER_CHANGED_TIME = "user_changed_time";
    public static final String ATTR_VALUE_CURRENT_TIME = "current_time";
    public static final String ATTR_VALUE_CUSTOM_TIME = "custom_time";
    private static final String ATTR_VALUE_FALSE = "false";
    public static final String ATTR_VALUE_HAS_TIME = "has_time";
    public static final String ATTR_VALUE_NO_TIME = "no_time";
    private static final String ATTR_VALUE_TRUE = "true";
    private static final String CANCEL_ATTRIBUTE = "cancel";
    public static final String CHANGE_UNIT = "changeUnit";
    public static final String COPY_MEAL = "copyMeal";
    private static final String DATE_DIFF_ATTRIBUTE = "date_diff";
    private static final String DAYS_FROM_CURRENT_DAY_ATTRIBUTE = "days_from_current_day";
    private static final String DELETE_ATTRIBUTE = "delete";
    private static final String DIARY_COMPLETE_BUTTON_TAP_EVENT = "diary_complete_button_tap";
    private static final String DIARY_COPY_MEAL_EVENT = "copy_meal";
    private static final String DIARY_DELETE_CONFIRMATION_EVENT = "diary_delete_confirmation";
    private static final String DIARY_DELETE_ITEMS_BTN_CLICK_EVENT = "Diary_Items_Btn_Click";
    private static final String DIARY_EDIT_BTN_CLICK_EVENT = "User_entered_edit_mode";
    private static final String DIARY_EDIT_CLOSE_BTN_CLICK_EVENT = "User_exited_edit_mode";
    private static final String DIARY_EDIT_MODE_TAP_OVERFLOW_TAP_ITEM_EVENT = "diary_edit_mode_tap_overflow_tap_item";
    private static final String DIARY_EDIT_SELECT_ALL_EVENT = "diary_edit_select_all";
    private static final String DIARY_EVENT = "_diary";
    private static final String DIARY_FOOD_ENTRY_MOVED_EVENT = "Diary_Food_Entry_Moved";
    private static final String DIARY_MEAL_GOALS_TAPPED_EVENT = "diary_meal_goals_tapped";
    private static final String DIARY_MEAL_MORE_OPTION_TAPPED_EVENT = "diary_meal_more_option_tapped";
    private static final String DIARY_MEAL_MORE_TAPPED_EVENT = "diary_meal_more_tapped";
    private static final String DIARY_NUMBER_ITEMS_DELETED_ATTRIBUTE = "diary.number_of_items_deleted";
    private static final String DIARY_OVERFLOW_TAP_ITEM_EVENT = "diary_overflow_tap_item";
    private static final String DIARY_VIEW_APPEARED_EVENT = "diary_view_appeared";
    private static final String DO_NOT_SHOW_ATTRIBUTE = "do_not_show";
    private static final String EVENT_DAIRY_COPY_SAVE_TAPPED = "diary_copy_save_tapped";
    private static final String EVENT_DIARY_CONNECT_DEVICE_DISPLAYED = "diary_connect_device_displayed";
    private static final String EVENT_DIARY_CONNECT_DEVICE_TAPPED = "diary_connect_device_tapped";
    private static final String EVENT_DIARY_COPY_SAVE_SHOWN = "diary_copy_save_message_shown";
    private static final String EVENT_DIARY_FOOD_HEADER_TAPPED = "diary_food_time_header_tapped";
    private static final String EVENT_DIARY_FOOD_TIME_HEADER_SAVED = "diary_food_time_header_saved";
    private static final String EVENT_DIARY_NUTRITION_TAP = "diary_tap_nutrition";
    private static final String EVENT_PREMIUM_MEAL_MACRO_UNIT_ATTRIBUTE = "premium_meal_macro_unit";
    private static final String FRIEND_DIARY_COPY_EXERCISE_EVENT = "friend_diary_copy_exercise";
    private static final String FRIEND_DIARY_COPY_MEAL_EVENT = "friend_diary_copy_meal";
    private static final String ITEM_ATTRIBUTE = "item";
    private static final String KEY_UNIT_ATTRIBUTE = "unit";
    public static final String LOCATION_BOTTOM = "bottom";
    public static final String LOCATION_HEADER = "header";
    private static final String MEAL_ATTRIBUTE = "meal";
    private static final String MEAL_INDEX_ATTRIBUTE = "meal_index";
    private static final String MEAL_NAME_ATTRIBUTE = "meal_name";
    private static final String NAME_ATTRIBUTE = "name";
    public static final String QUICK_ADD = "quickAdd";
    public static final String REMINDERS = "reminders";
    public static final String SAVE_MEAL = "saveMeal";
    private static final String SCREEN_ATTRIBUTE = "screen";
    private static final String SELECT_ATTRIBUTE = "select";
    private static final String SPOTLIGHT_ADD_ANOTHER_SEE_EVENT = "spotlight_add_another_see";
    private static final String SPOTLIGHT_ADD_ANOTHER_SKIP_EVENT = "spotlight_add_another_skip";
    private static final String STATUS_ATTRIBUTE = "status";
    private static final String TAP_LOCATION_ATTRIBUTE = "tap_location";
    private static final String UNSELECT_ATTRIBUTE = "unselect";
    private static final String USER_ID_ATTRIBUTE = "user_id";
    private final Lazy<AnalyticsService> analyticsService;
    private boolean connectDisplayEventReported;

    public DiaryAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportDiaryEditCloseButtonEvent() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_EDIT_CLOSE_BTN_CLICK_EVENT);
    }

    public void reportReferrer(String str) {
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_diary");
        analyticsService2.reportEvent(sb.toString());
    }

    public void reportFoodEntryMoved() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_FOOD_ENTRY_MOVED_EVENT);
    }

    public void reportSkippedWalkthrough() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(SPOTLIGHT_ADD_ANOTHER_SKIP_EVENT);
    }

    public void reportShownWalkthrough() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(SPOTLIGHT_ADD_ANOTHER_SEE_EVENT);
    }

    public void reportItemsDeletedEvent(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_DELETE_ITEMS_BTN_CLICK_EVENT, MapUtil.createMap(DIARY_NUMBER_ITEMS_DELETED_ATTRIBUTE, Strings.toString(Integer.valueOf(i))));
    }

    public void reportToolbarEditClicked() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_EDIT_BTN_CLICK_EVENT);
    }

    public void reportNewDateDeltaEvent(Date date, User user) {
        int numberOfDaysSince = DateTimeUtils.getNumberOfDaysSince(date);
        if (numberOfDaysSince != 0) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_VIEW_APPEARED_EVENT, MapUtil.createMap("user_id", user.getUid(), "days_from_current_day", Strings.toString(Integer.valueOf(numberOfDaysSince))));
        }
    }

    public void reportCopyMealEvent(String str, long j) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_COPY_MEAL_EVENT, MapUtil.createMap("meal_name", str, DATE_DIFF_ATTRIBUTE, String.valueOf(j)));
    }

    public void reportPremiumMealMacroUnitEvent(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_PREMIUM_MEAL_MACRO_UNIT_ATTRIBUTE, MapUtil.createMap("unit", str));
    }

    public void reportDeleteConfirmationEvent(String str, boolean z, boolean z2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_DELETE_CONFIRMATION_EVENT, new Builder().put("screen", str).put(DO_NOT_SHOW_ATTRIBUTE, Strings.toString(Boolean.valueOf(z))).put(CANCEL_ATTRIBUTE, Strings.toString(Boolean.valueOf(!z2))).put("delete", Strings.toString(Boolean.valueOf(z2))).build());
    }

    public void reportOverflowItemClickedEvent(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_EDIT_MODE_TAP_OVERFLOW_TAP_ITEM_EVENT, new Builder().put("item", str).build());
    }

    public void reportOverFlowTapItem(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_OVERFLOW_TAP_ITEM_EVENT, new Builder().put("item", str).build());
    }

    public void reportCompleteButtonTapEvent(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_COMPLETE_BUTTON_TAP_EVENT, MapUtil.createMap(TAP_LOCATION_ATTRIBUTE, Strings.toString(str)));
    }

    public void reportEditSelectAllEvent(boolean z) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_EDIT_SELECT_ALL_EVENT, new Builder().put("status", z ? "select" : UNSELECT_ATTRIBUTE).build());
    }

    public void reportFriendDiaryCopyExerciseEvent() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FRIEND_DIARY_COPY_EXERCISE_EVENT);
    }

    public void reportFriendDiaryCopuMealEvent(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FRIEND_DIARY_COPY_MEAL_EVENT, new Builder().put("meal", Strings.toString(Integer.valueOf(i))).build());
    }

    public void reportMealMoreTappedEvent(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_MEAL_MORE_TAPPED_EVENT, new Builder().put("name", Strings.toString(str)).build());
    }

    public void reportMealMoreOptionTappedEvent(String str, String str2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_MEAL_MORE_OPTION_TAPPED_EVENT, new Builder().put("name", Strings.toString(str)).put("action", str2).build());
    }

    public void reportMealGoalHeaderTappedEvent(String str, int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIARY_MEAL_GOALS_TAPPED_EVENT, new Builder().put("meal_name", Strings.toString(str)).put(MEAL_INDEX_ATTRIBUTE, Integer.toString(i)).build());
    }

    public void reportDiaryCopySaveShownEvent() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_DIARY_COPY_SAVE_SHOWN);
    }

    public void reportDiaryCopySaveTappedEvent() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_DAIRY_COPY_SAVE_TAPPED);
    }

    public void reportDiaryTapNutrition(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_DIARY_NUTRITION_TAP, MapUtil.createMap(TAP_LOCATION_ATTRIBUTE, str));
    }

    public void reportyConnectDeviceDisplayed() {
        if (!this.connectDisplayEventReported) {
            this.connectDisplayEventReported = true;
            ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_DIARY_CONNECT_DEVICE_DISPLAYED);
        }
    }

    public void reportConnectDeviceTapped() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_DIARY_CONNECT_DEVICE_TAPPED);
    }

    public void reportTimeHeaderTapped(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_DIARY_FOOD_HEADER_TAPPED, new Builder().put("time", str).build());
    }

    public void reportTimeHeaderSaved(TimestampOption timestampOption, boolean z) {
        String str;
        switch (timestampOption) {
            case SET_TIME:
            case LATEST_MEAL_ENTRY_TIME:
                str = ATTR_VALUE_CUSTOM_TIME;
                break;
            case CURRENT_TIME:
                str = ATTR_VALUE_CURRENT_TIME;
                break;
            case NO_TIME:
                str = ATTR_VALUE_NO_TIME;
                break;
            default:
                str = null;
                break;
        }
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_DIARY_FOOD_TIME_HEADER_SAVED, new Builder().put("time", str).put("user_changed_time", z ? "true" : "false").build());
    }
}
