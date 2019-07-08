package com.myfitnesspal.feature.progress.task;

import android.content.Context;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import dagger.Lazy;

public class GetImageCountForMeasurementTypeTask extends EventedTaskBase<Integer, Exception> {
    private final String measurementType;
    private final Lazy<ProgressService> progressService;

    public static final class CompletedEvent extends TaskEventBase<Integer, Exception> {
    }

    public GetImageCountForMeasurementTypeTask(Lazy<ProgressService> lazy, String str) {
        super(CompletedEvent.class);
        this.progressService = lazy;
        this.measurementType = str;
    }

    /* access modifiers changed from: protected */
    public Integer exec(Context context) throws Exception {
        return Integer.valueOf(((ProgressService) this.progressService.get()).getImageCountForMeasurementType(this.measurementType));
    }
}
