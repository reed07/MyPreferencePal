package com.myfitnesspal.feature.progress.task;

import android.content.Context;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.List;

public class GetMeasurementGraphDataTask extends EventedTaskBase<List<ProgressEntryViewModel>, Exception> {
    private final int days;
    private final String measurementName;
    private final Lazy<ProgressService> progressService;
    private final Lazy<UserHeightService> userHeightService;
    private final Lazy<UserWeightService> userWeightService;

    public static class CompletedEvent extends TaskEventBase<List<ProgressEntryViewModel>, Exception> {
    }

    public GetMeasurementGraphDataTask(Lazy<ProgressService> lazy, Lazy<UserWeightService> lazy2, Lazy<UserHeightService> lazy3, String str, int i) {
        super((TaskEventBase) new CompletedEvent());
        this.progressService = lazy;
        this.userWeightService = lazy2;
        this.userHeightService = lazy3;
        this.measurementName = str;
        this.days = i;
    }

    /* access modifiers changed from: protected */
    public List<ProgressEntryViewModel> exec(Context context) throws Exception {
        return ((ProgressService) this.progressService.get()).getProgressReport(this.measurementName, this.days, ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit(), ((UserHeightService) this.userHeightService.get()).getUserCurrentHeightUnit());
    }

    /* access modifiers changed from: protected */
    public DedupeMode getDefaultDedupeMode() {
        return DedupeMode.CancelExisting;
    }
}
