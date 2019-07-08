package com.myfitnesspal.feature.goals.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import dagger.Lazy;
import java.util.Calendar;

public class FetchNutrientGoalTask extends EventedTaskBase<MfpNutrientGoal, Exception> {
    private static final String TASK_NAME_BASE = "QueryNutrientGoalsList";
    private Lazy<NutrientGoalService> nutrientGoalService;

    public static class CompletedEvent extends TaskEventBase<MfpNutrientGoal, Exception> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public FetchNutrientGoalTask(Lazy<NutrientGoalService> lazy) {
        super(CompletedEvent.class);
        this.nutrientGoalService = lazy;
    }

    /* access modifiers changed from: protected */
    public MfpNutrientGoal exec(Context context) throws Exception {
        return ((NutrientGoalService) this.nutrientGoalService.get()).getNutrientGoal(Calendar.getInstance().getTime());
    }
}
