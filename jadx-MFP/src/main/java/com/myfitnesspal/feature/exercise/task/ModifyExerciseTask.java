package com.myfitnesspal.feature.exercise.task;

import android.content.Context;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import dagger.Lazy;

public class ModifyExerciseTask extends EventedTaskBase<Boolean, ApiException> {
    private final MfpExercise exercise;
    private final Lazy<ExerciseService> exerciseService;
    private final Operation operation;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
    }

    public enum Operation {
        Add,
        Delete
    }

    public ModifyExerciseTask(Lazy<ExerciseService> lazy, Operation operation2, MfpExercise mfpExercise) {
        super(CompletedEvent.class);
        this.exerciseService = lazy;
        this.operation = operation2;
        this.exercise = mfpExercise;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        switch (this.operation) {
            case Add:
                ((ExerciseService) this.exerciseService.get()).createNewExerciseLocally(this.exercise);
                break;
            case Delete:
                ((ExerciseService) this.exerciseService.get()).deleteExerciseLocally(this.exercise, true);
                break;
        }
        return Boolean.valueOf(true);
    }
}
