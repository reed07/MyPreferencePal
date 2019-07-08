package com.myfitnesspal.feature.goals.service;

import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.uacf.core.util.Function1;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import java.util.Date;
import java.util.List;

public interface NutrientGoalService extends SyncItemHandler<MfpNutrientGoal> {
    MfpDailyGoal getCachedDefaultGoal();

    void getDailyGoalForDate(Function1<MfpDailyGoal> function1, Function1<List<Exception>> function12, Date date);

    void getDailyGoalForDayOfWeek(Function1<MfpDailyGoal> function1, Function1<List<Exception>> function12, Date date);

    MfpDailyGoal getDailyGoalForDayOfWeekSync(Date date);

    MfpDailyGoal getMfpDailyGoalForDayOfWeek(MfpNutrientGoal mfpNutrientGoal, String str);

    MfpDailyGoal getMfpDailyGoalFromDB(Date date);

    MfpNutrientGoal getMfpNutrientGoalFromDB(Date date);

    MfpNutrientGoal getNutrientGoal(Date date) throws Exception;

    void getNutrientGoal(Function1<MfpNutrientGoal> function1, Function1<List<Exception>> function12);

    void getNutrientGoal(Function1<MfpNutrientGoal> function1, Function1<List<Exception>> function12, Date date);

    void setNutrientGoal(MfpNutrientGoal mfpNutrientGoal) throws Exception;

    void setNutrientGoalAsync(Function1<Boolean> function1, Function1<List<Exception>> function12, MfpNutrientGoal mfpNutrientGoal);
}
