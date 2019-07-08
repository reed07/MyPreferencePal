package com.myfitnesspal.shared.task;

import android.content.Context;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.util.DateTimeUtils;
import dagger.Lazy;
import java.util.Date;

public class FetchNutrientGoalForDateTask extends EventedTaskBase<MfpDailyGoal, Exception> {
    private Date date;
    private Lazy<NutrientGoalService> nutrientGoalService;

    public static class CompletedEvent extends TaskEventBase<MfpDailyGoal, Exception> {
        private Date activeDate;
        private double caloriesEnergy;
        private double carbs;
        private double fat;
        private boolean isPremiumSubscribed;
        private double protein;

        public CompletedEvent(boolean z, Date date, double d, double d2, double d3, double d4) {
            this.isPremiumSubscribed = z;
            this.activeDate = date;
            this.carbs = d;
            this.fat = d2;
            this.protein = d3;
            this.caloriesEnergy = d4;
        }

        public boolean isPremiumSubscribed() {
            return this.isPremiumSubscribed;
        }

        public Date getActiveDate() {
            return this.activeDate;
        }

        public double getCarbs() {
            return this.carbs;
        }

        public double getFat() {
            return this.fat;
        }

        public double getProtein() {
            return this.protein;
        }

        public double getCaloriesEnergy() {
            return this.caloriesEnergy;
        }
    }

    public FetchNutrientGoalForDateTask(Lazy<NutrientGoalService> lazy, boolean z, Date date2, double d, double d2, double d3, double d4) {
        CompletedEvent completedEvent = new CompletedEvent(z, date2, d, d2, d3, d4);
        super((TaskEventBase) completedEvent);
        this.nutrientGoalService = lazy;
        this.date = date2;
    }

    /* access modifiers changed from: protected */
    public MfpDailyGoal exec(Context context) throws Exception {
        return ((NutrientGoalService) this.nutrientGoalService.get()).getMfpDailyGoalForDayOfWeek(((NutrientGoalService) this.nutrientGoalService.get()).getNutrientGoal(this.date), DateTimeUtils.getDayOfTheWeek(this.date));
    }
}
