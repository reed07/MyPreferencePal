package com.myfitnesspal.feature.externalsync.service.ops;

import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.feature.externalsync.service.ExternalStepsService;
import com.myfitnesspal.feature.externalsync.service.ExternalSyncCooldown;
import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import com.uacf.sync.engine.UacfScheduleOpBase;
import dagger.Lazy;

public class ExternalSyncOp extends UacfScheduleOpBase {
    private final Lazy<ExternalExerciseService> externalExerciseService;
    private final Lazy<ExternalNutritionService> externalNutritionService;
    private final Lazy<ExternalStepsService> externalStepsService;
    private final Lazy<ExternalUserService> externalUserService;

    public ExternalSyncOp(Lazy<ExternalExerciseService> lazy, Lazy<ExternalNutritionService> lazy2, Lazy<ExternalStepsService> lazy3, Lazy<ExternalUserService> lazy4) {
        this.externalExerciseService = lazy;
        this.externalNutritionService = lazy2;
        this.externalStepsService = lazy3;
        this.externalUserService = lazy4;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) {
        if (!ExternalSyncCooldown.active()) {
            ((ExternalExerciseService) this.externalExerciseService.get()).sync();
            ((ExternalNutritionService) this.externalNutritionService.get()).sync();
            ((ExternalStepsService) this.externalStepsService.get()).sync();
            ((ExternalUserService) this.externalUserService.get()).sync();
            ExternalSyncCooldown.set();
        }
        return Result.completed();
    }
}
