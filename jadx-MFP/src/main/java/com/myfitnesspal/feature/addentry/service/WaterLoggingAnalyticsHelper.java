package com.myfitnesspal.feature.addentry.service;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

public class WaterLoggingAnalyticsHelper {
    public static final String ADD = "add";
    public static final String ADD_WATER_SCREEN = "add_water_screen";
    public static final String CLICK_SOURCE = "click_source";
    public static final String CUPS = "cups";
    public static final String CUPS_ATTRIBUTE = "cups";
    public static final String DIALOG_DISMISSED = "add_water_action_sheet_dismissed";
    public static final String DIALOG_SAVED = "add_water_action_sheet_saved";
    public static final String DIALOG_SHOWN = "add_water_action_sheet_displayed";
    public static final String DIARY = "diary";
    public static final String DIARY_FOOTER = "diary_footer";
    private static final String DIARY_WATER_ROW = "diary_water_row";
    public static final String EDIT = "edit";
    public static final String FAB = "fab";
    public static final String ML = "ml";
    public static final String OPTION = "option";
    public static final String OZ = "oz";
    public static final String PROFILE_UNIT_PREF = "profile_unit_pref";
    private static final String TOTAL = "total";
    public static final String TYPE = "type";
    private static final String UNIT = "unit";
    public static final String UPDATE_SOURCE = "update_source";
    public static final String VALUE = "value";
    public static final String WATER_ADDED = "water_added";
    public static final String WATER_ENTRY_TOTAL_CLICK = "water_entry_total_click";
    public static final String WATER_ENTRY_TOTAL_MODIFIED = "water_entry_total_modified";
    private static final String WATER_LOGGED = "water_logged";
    public static final String WATER_QUICK_OPTION_CLICK = "water_quick_option_clicked";
    public static final String WATER_SCREEN_VIEWED = "water_screen_viewed";
    public static final String WATER_UNIT_CHANGE_CLICK = "water_unit_change_clicked";
    public static final String WATER_UNIT_UPDATED = "water_unit_updated";
    private final Lazy<AnalyticsService> analyticsService;
    private final Lazy<ConfigService> configService;

    public WaterLoggingAnalyticsHelper(Lazy<AnalyticsService> lazy, Lazy<ConfigService> lazy2) {
        this.analyticsService = lazy;
        this.configService = lazy2;
    }

    public void reportDialogShown() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIALOG_SHOWN);
    }

    public void reportWaterScreenViewed(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click_source", str);
        hashMap.put("type", str2);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(WATER_SCREEN_VIEWED, (Map<String, String>) hashMap);
    }

    public void reportDialogCanceled() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIALOG_DISMISSED);
    }

    public void reportCups(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("cups", String.valueOf(i));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(DIALOG_SAVED, (Map<String, String>) hashMap);
    }

    public void reportSponsoredWaterLogged(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("unit", "cups");
        hashMap.put("total", Strings.toString(Integer.valueOf(i)));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(WATER_LOGGED, (Map<String, String>) hashMap);
    }

    public void reportWaterLogged(Water water, Float f, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("unit", getUnitAttributeFromWater(water));
        hashMap.put("value", Strings.toString(f));
        hashMap.put("type", str);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(WATER_LOGGED, (Map<String, String>) hashMap);
    }

    public void reportWaterUnitChangeClick(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click_source", str);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(WATER_UNIT_CHANGE_CLICK, (Map<String, String>) hashMap);
    }

    public void reportWaterUnitUpdated(String str, Water water) {
        HashMap hashMap = new HashMap();
        hashMap.put(UPDATE_SOURCE, str);
        hashMap.put("unit", getUnitAttributeFromWater(water));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(WATER_UNIT_UPDATED, (Map<String, String>) hashMap);
    }

    public void reportWaterQuickOptionClick(Water water, Float f) {
        HashMap hashMap = new HashMap();
        hashMap.put("value", Strings.toString(f));
        hashMap.put("unit", getUnitAttributeFromWater(water));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(WATER_QUICK_OPTION_CLICK, (Map<String, String>) hashMap);
    }

    private String getUnitAttributeFromWater(Water water) {
        if (water == Water.CUPS) {
            return "cups";
        }
        return water == Water.FL_OZ ? OZ : ML;
    }
}
