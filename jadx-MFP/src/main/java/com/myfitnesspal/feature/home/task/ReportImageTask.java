package com.myfitnesspal.feature.home.task;

import android.content.Context;
import com.myfitnesspal.feature.home.service.ImageReportingService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;

public class ReportImageTask extends EventedTaskBase<Boolean, ApiException> {
    private static final String TASK_NAME_BASE = "ExecuteReportImageTask-";
    final boolean emailCh;
    final String imageId;
    Lazy<ImageReportingService> imageReportingService;
    final String reason;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public ReportImageTask(Lazy<ImageReportingService> lazy, boolean z, String str, String str2) {
        super((TaskEventBase) new CompletedEvent());
        this.imageReportingService = lazy;
        this.emailCh = z;
        this.reason = str;
        this.imageId = str2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        ((ImageReportingService) this.imageReportingService.get()).reportImage(this.emailCh, this.reason, this.imageId);
        return Boolean.valueOf(true);
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        StringBuilder sb = new StringBuilder();
        sb.append(TASK_NAME_BASE);
        sb.append(this.imageId);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.None;
    }
}
