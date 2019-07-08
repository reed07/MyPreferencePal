package com.myfitnesspal.feature.meals.task;

import android.content.Context;
import com.myfitnesspal.feature.meals.model.MealFoodLoggedInfo;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;

public class AddMealFoodToDiaryTask extends EventedTaskBase<Boolean, ApiException> {
    private final List<FoodEntry> foodEntries;
    private final String imagePath;
    private final MealFood mealFood;
    private final MealFoodLoggedInfo mealFoodLoggedInfo;
    private final String mealName;
    private final Lazy<MealService> mealService;
    private final int searchVersion;
    private final TimeValue selectedTime;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
    }

    public AddMealFoodToDiaryTask(Lazy<MealService> lazy, List<FoodEntry> list, String str, MealFoodLoggedInfo mealFoodLoggedInfo2, MealFood mealFood2, String str2, TimeValue timeValue, int i) {
        super(CompletedEvent.class);
        this.mealService = lazy;
        this.foodEntries = list;
        this.mealName = str;
        this.mealFoodLoggedInfo = mealFoodLoggedInfo2;
        this.mealFood = mealFood2;
        this.imagePath = str2;
        this.selectedTime = timeValue;
        this.searchVersion = i;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        if (Strings.notEmpty(this.imagePath)) {
            ((MealService) this.mealService.get()).associateMealImages(this.mealFood, null, this.imagePath);
        }
        ((MealService) this.mealService.get()).saveMealFoodToDiary(this.foodEntries, this.mealName, this.mealFoodLoggedInfo, this.mealFood, this.selectedTime, this.searchVersion);
        return Boolean.valueOf(true);
    }
}
