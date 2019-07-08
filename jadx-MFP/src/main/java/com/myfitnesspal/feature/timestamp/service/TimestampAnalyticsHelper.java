package com.myfitnesspal.feature.timestamp.service;

import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0004\u0015\u0016\u0017\u0018B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0019"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper;", "", "analyticsService", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;)V", "getAnalyticsService", "()Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "reportFeatureHighlightEvent", "", "event", "Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FeatureHighlightEvent;", "reportTimeFieldSaved", "screenType", "Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FoodScreenType;", "timeValue", "Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$TimeValue;", "isTimeChanged", "", "reportTimeFieldTapped", "reportTimestampDiarySettingToggled", "toggled", "Companion", "FeatureHighlightEvent", "FoodScreenType", "TimeValue", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TimestampAnalyticsHelper.kt */
public final class TimestampAnalyticsHelper {
    @NotNull
    public static final String ATTR_EVENT = "event";
    @NotNull
    public static final String ATTR_TIME = "time";
    @NotNull
    public static final String ATTR_TIME_CHANGED = "user_changed_time";
    @NotNull
    public static final String ATTR_TYPE = "type";
    @NotNull
    public static final String ATTR_VALUE = "value";
    @NotNull
    public static final String ATTR_VALUE_FALSE = "false";
    @NotNull
    public static final String ATTR_VALUE_OFF = "off";
    @NotNull
    public static final String ATTR_VALUE_ON = "on";
    @NotNull
    public static final String ATTR_VALUE_TRUE = "true";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EVENT_FOOD_TIMESTAMP_DIARY_SETTINGS_TOGGLED = "food_timestamp_diary_setting_toggled";
    @NotNull
    public static final String EVENT_FOOD_TIMESTAMP_TAP_TARGET = "food_timestamp_tooltip";
    @NotNull
    public static final String EVENT_FOOD_TIME_FIELD_SAVED = "food_time_field_saved";
    @NotNull
    public static final String EVENT_FOOD_TIME_FIELD_TAPPED = "food_time_field_tapped";
    @NotNull
    public static final String FEATURE_TIMESTAMP_ADD_FOOD = "food_timestamp_add_food";
    @NotNull
    public static final String FEATURE_TIMESTAMP_DIARY_SETTINGS = "food_timestamp_diary_setting";
    @NotNull
    public static final String FEATURE_TIMESTAMP_TOOLTIP = "food_timestamp_tooltip";
    @NotNull
    private final AnalyticsService analyticsService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$Companion;", "", "()V", "ATTR_EVENT", "", "ATTR_TIME", "ATTR_TIME_CHANGED", "ATTR_TYPE", "ATTR_VALUE", "ATTR_VALUE_FALSE", "ATTR_VALUE_OFF", "ATTR_VALUE_ON", "ATTR_VALUE_TRUE", "EVENT_FOOD_TIMESTAMP_DIARY_SETTINGS_TOGGLED", "EVENT_FOOD_TIMESTAMP_TAP_TARGET", "EVENT_FOOD_TIME_FIELD_SAVED", "EVENT_FOOD_TIME_FIELD_TAPPED", "FEATURE_TIMESTAMP_ADD_FOOD", "FEATURE_TIMESTAMP_DIARY_SETTINGS", "FEATURE_TIMESTAMP_TOOLTIP", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TimestampAnalyticsHelper.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FeatureHighlightEvent;", "", "analyticsName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAnalyticsName", "()Ljava/lang/String;", "DISPLAYED", "DISMISSED", "NO_THANKS_CLICKED", "TRACK_TIME_CLICKED", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TimestampAnalyticsHelper.kt */
    public enum FeatureHighlightEvent {
        DISPLAYED("displayed"),
        DISMISSED(ChallengesAnalyticsHelper.SCREEN_DISMISSED),
        NO_THANKS_CLICKED("no_thanks_clicked"),
        TRACK_TIME_CLICKED("track_time_clicked");
        
        @NotNull
        private final String analyticsName;

        protected FeatureHighlightEvent(String str) {
            Intrinsics.checkParameterIsNotNull(str, "analyticsName");
            this.analyticsName = str;
        }

        @NotNull
        public final String getAnalyticsName() {
            return this.analyticsName;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FoodScreenType;", "", "analyticsName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAnalyticsName", "()Ljava/lang/String;", "FOOD", "RECIPE", "RESTAURANT_MENU", "MEAL", "QUICK_ADD", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TimestampAnalyticsHelper.kt */
    public enum FoodScreenType {
        FOOD("food"),
        RECIPE("recipe"),
        RESTAURANT_MENU("restaurant_menu_item"),
        MEAL("meal"),
        QUICK_ADD("quick_add");
        
        @NotNull
        private final String analyticsName;

        protected FoodScreenType(String str) {
            Intrinsics.checkParameterIsNotNull(str, "analyticsName");
            this.analyticsName = str;
        }

        @NotNull
        public final String getAnalyticsName() {
            return this.analyticsName;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$TimeValue;", "", "analyticsName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAnalyticsName", "()Ljava/lang/String;", "CURRENT_TIME", "PREVIOUS_FOOD_TIME", "CUSTOM_TIME", "NO_TIME", "LOGGED_TIME", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TimestampAnalyticsHelper.kt */
    public enum TimeValue {
        CURRENT_TIME("default_current_time"),
        PREVIOUS_FOOD_TIME("default_previous_food_time"),
        CUSTOM_TIME(DiaryAnalyticsHelper.ATTR_VALUE_CUSTOM_TIME),
        NO_TIME(DiaryAnalyticsHelper.ATTR_VALUE_NO_TIME),
        LOGGED_TIME("editing_logged_time");
        
        public static final Companion Companion = null;
        @NotNull
        private final String analyticsName;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$TimeValue$Companion;", "", "()V", "fromTimestampOption", "Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$TimeValue;", "timestampOption", "Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
        /* compiled from: TimestampAnalyticsHelper.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            @NotNull
            public final TimeValue fromTimestampOption(@NotNull TimestampOption timestampOption) {
                Intrinsics.checkParameterIsNotNull(timestampOption, "timestampOption");
                switch (timestampOption) {
                    case SET_TIME:
                        return TimeValue.CUSTOM_TIME;
                    case CURRENT_TIME:
                        return TimeValue.CURRENT_TIME;
                    case LATEST_MEAL_ENTRY_TIME:
                        return TimeValue.PREVIOUS_FOOD_TIME;
                    case NO_TIME:
                        return TimeValue.NO_TIME;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
        }

        @JvmStatic
        @NotNull
        public static final TimeValue fromTimestampOption(@NotNull TimestampOption timestampOption) {
            return Companion.fromTimestampOption(timestampOption);
        }

        protected TimeValue(String str) {
            Intrinsics.checkParameterIsNotNull(str, "analyticsName");
            this.analyticsName = str;
        }

        @NotNull
        public final String getAnalyticsName() {
            return this.analyticsName;
        }

        static {
            Companion = new Companion(null);
        }
    }

    @Inject
    public TimestampAnalyticsHelper(@NotNull AnalyticsService analyticsService2) {
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        this.analyticsService = analyticsService2;
    }

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        return this.analyticsService;
    }

    public final void reportTimeFieldTapped(@NotNull FoodScreenType foodScreenType, @NotNull TimeValue timeValue) {
        Intrinsics.checkParameterIsNotNull(foodScreenType, "screenType");
        Intrinsics.checkParameterIsNotNull(timeValue, "timeValue");
        this.analyticsService.reportEvent(EVENT_FOOD_TIME_FIELD_TAPPED, (Map<String, String>) MapsKt.hashMapOf(new Pair("type", foodScreenType.getAnalyticsName()), new Pair(ATTR_TIME, timeValue.getAnalyticsName())));
    }

    public final void reportTimeFieldSaved(@NotNull FoodScreenType foodScreenType, @NotNull TimeValue timeValue, boolean z) {
        Intrinsics.checkParameterIsNotNull(foodScreenType, "screenType");
        Intrinsics.checkParameterIsNotNull(timeValue, "timeValue");
        AnalyticsService analyticsService2 = this.analyticsService;
        String str = EVENT_FOOD_TIME_FIELD_SAVED;
        Pair[] pairArr = new Pair[3];
        pairArr[0] = new Pair("type", foodScreenType.getAnalyticsName());
        pairArr[1] = new Pair(ATTR_TIME, timeValue.getAnalyticsName());
        pairArr[2] = new Pair(ATTR_TIME_CHANGED, z ? "true" : "false");
        analyticsService2.reportEvent(str, (Map<String, String>) MapsKt.hashMapOf(pairArr));
    }

    public final void reportTimestampDiarySettingToggled(boolean z) {
        AnalyticsService analyticsService2 = this.analyticsService;
        String str = EVENT_FOOD_TIMESTAMP_DIARY_SETTINGS_TOGGLED;
        Pair[] pairArr = new Pair[1];
        pairArr[0] = new Pair("value", z ? "on" : "off");
        analyticsService2.reportEvent(str, (Map<String, String>) MapsKt.hashMapOf(pairArr));
    }

    public final void reportFeatureHighlightEvent(@NotNull FeatureHighlightEvent featureHighlightEvent) {
        Intrinsics.checkParameterIsNotNull(featureHighlightEvent, "event");
        this.analyticsService.reportEvent("food_timestamp_tooltip", (Map<String, String>) MapsKt.hashMapOf(new Pair("event", featureHighlightEvent.getAnalyticsName())));
    }
}
