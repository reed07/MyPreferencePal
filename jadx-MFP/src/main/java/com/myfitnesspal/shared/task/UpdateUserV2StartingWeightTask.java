package com.myfitnesspal.shared.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.UserV2;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import dagger.Lazy;

public class UpdateUserV2StartingWeightTask extends EventedTaskBase<UserV2, ApiException> {
    private static final String TASK_NAME_BASE = "UpdateUserV2Task";
    String startingWeightDate;
    Lazy<UserWeightService> userWeightService;
    float weightInPounds;

    public static class CompletedEvent extends TaskEventBase<UserV2, ApiException> {
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        return TASK_NAME_BASE;
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public UpdateUserV2StartingWeightTask(Lazy<UserWeightService> lazy, float f, String str) {
        super((TaskEventBase) new CompletedEvent());
        this.userWeightService = lazy;
        this.weightInPounds = f;
        this.startingWeightDate = str;
    }

    /* access modifiers changed from: protected */
    public UserV2 exec(Context context) throws ApiException {
        return ((UserWeightService) this.userWeightService.get()).updateStartingWeight(this.weightInPounds, this.startingWeightDate);
    }
}
