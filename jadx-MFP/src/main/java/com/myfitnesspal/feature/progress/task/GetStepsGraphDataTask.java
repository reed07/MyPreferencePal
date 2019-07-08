package com.myfitnesspal.feature.progress.task;

import android.content.Context;
import com.myfitnesspal.feature.progress.constants.GraphPeriod;
import com.myfitnesspal.feature.progress.model.StepsProgressModel;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Date;

public class GetStepsGraphDataTask extends EventedTaskBase<StepsProgressModel, Exception> {
    private final int days;
    private final Lazy<Session> session;
    private final Lazy<StepService> stepService;

    public static class CompletedEvent extends TaskEventBase<StepsProgressModel, Exception> {
    }

    public GetStepsGraphDataTask(Lazy<StepService> lazy, Lazy<Session> lazy2, int i) {
        super((TaskEventBase) new CompletedEvent());
        this.stepService = lazy;
        this.session = lazy2;
        this.days = i;
    }

    /* access modifiers changed from: protected */
    public StepsProgressModel exec(Context context) throws Exception {
        Date date;
        if (GraphPeriod.AllTime.getDaysBack((Session) this.session.get()) == this.days) {
            date = new Date(0);
        } else {
            Calendar instance = Calendar.getInstance();
            instance.add(5, this.days * -1);
            date = new Date(instance.getTime().getTime());
        }
        return new StepsProgressModel(((StepService) this.stepService.get()).fetchStepsEntriesByDateRange(date, Calendar.getInstance().getTime()), this.stepService);
    }

    /* access modifiers changed from: protected */
    public DedupeMode getDefaultDedupeMode() {
        return DedupeMode.CancelExisting;
    }
}
