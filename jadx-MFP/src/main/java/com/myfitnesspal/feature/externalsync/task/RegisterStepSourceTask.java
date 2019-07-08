package com.myfitnesspal.feature.externalsync.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.steps.StepService;
import dagger.Lazy;

public class RegisterStepSourceTask extends EventedTaskBase<Boolean, ApiException> {
    public static final int MODE_REGISTER = 0;
    public static final int MODE_UNREGISTER = 1;
    private final int mode;
    private final Lazy<StepService> stepService;
    private final MfpStepSource stepSource;

    private static class Event extends TaskEventBase<Boolean, ApiException> {
        private Event() {
        }
    }

    public RegisterStepSourceTask(Lazy<StepService> lazy, MfpStepSource mfpStepSource, int i) {
        super((TaskEventBase) new Event());
        this.stepService = lazy;
        this.stepSource = mfpStepSource;
        this.mode = i;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        if (this.mode == 0) {
            ((StepService) this.stepService.get()).registerClientSideStepSource(this.stepSource);
        } else {
            ((StepService) this.stepService.get()).unregisterClientSideStepSource(this.stepSource);
        }
        return Boolean.valueOf(true);
    }
}
