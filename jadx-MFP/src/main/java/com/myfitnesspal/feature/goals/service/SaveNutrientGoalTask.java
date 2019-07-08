package com.myfitnesspal.feature.goals.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import dagger.Lazy;

public class SaveNutrientGoalTask extends EventedTaskBase<Boolean, Exception> {
    private static final String TASK_NAME_BASE = "SaveNutrientGoalTask";
    private final MfpNutrientGoal nutrientGoal;
    private Lazy<NutrientGoalService> nutrientGoalService;

    public static class CompletedEvent extends TaskEventBase<Boolean, Exception> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public SaveNutrientGoalTask(Lazy<NutrientGoalService> lazy, MfpNutrientGoal mfpNutrientGoal) {
        super(CompletedEvent.class);
        this.nutrientGoalService = lazy;
        this.nutrientGoal = mfpNutrientGoal;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws Exception {
        ((NutrientGoalService) this.nutrientGoalService.get()).setNutrientGoal(this.nutrientGoal);
        return Boolean.valueOf(true);
    }
}
