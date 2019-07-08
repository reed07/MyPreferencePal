package com.myfitnesspal.feature.nutrition.service;

import android.content.Context;
import android.view.View;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import dagger.Lazy;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class NutritionDetailsServiceImpl implements NutritionDetailsService {
    private Lazy<LocalSettingsService> localSettingsService;
    private NutritionDetailsDelegate nutritionDetailsDelegate;

    @Inject
    public NutritionDetailsServiceImpl(Context context, Lazy<LocalSettingsService> lazy, boolean z) {
        this.nutritionDetailsDelegate = new NutritionDetailsDelegate(context, z);
        this.localSettingsService = lazy;
    }

    public List<NutrientEntry> getDailyNutrientDetails(Date date) {
        return this.nutritionDetailsDelegate.getDailyNutrientDetails(date, 1);
    }

    public View buildDailyNutrientDetails(Date date, boolean z) {
        return this.nutritionDetailsDelegate.buildNutrientLayout(getDailyNutrientDetails(date), false, z);
    }

    public View buildWeeklyNutrientDetails(Date date, boolean z) {
        return this.nutritionDetailsDelegate.buildNutrientLayout(getWeeklyNutrientDetails(date), true, z);
    }

    public List<NutrientEntry> getWeeklyNutrientDetails(Date date) {
        return this.nutritionDetailsDelegate.getWeeklyNutrientDetails(DateTimeUtils.startDayOnOrPriorTo(date, ((LocalSettingsService) this.localSettingsService.get()).getWeeklyStartDay()), 1);
    }
}
