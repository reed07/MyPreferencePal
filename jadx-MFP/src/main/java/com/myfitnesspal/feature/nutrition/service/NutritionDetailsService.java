package com.myfitnesspal.feature.nutrition.service;

import android.view.View;
import java.util.Date;

public interface NutritionDetailsService {
    View buildDailyNutrientDetails(Date date, boolean z);

    View buildWeeklyNutrientDetails(Date date, boolean z);
}
