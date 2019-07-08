package com.myfitnesspal.feature.appgallery.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;
import java.util.List;
import javax.annotation.Nonnull;

public class QueryAppListTask extends EventedTaskBase<List<MfpPlatformApp>, ApiException> {
    private static final String TASK_NAME_BASE = "GetAppList-";
    private final Lazy<AppGalleryService> appGalleryService;
    final String type;

    public static class CompletedEvent extends TaskEventBase<List<MfpPlatformApp>, Exception> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public QueryAppListTask(String str, @Nonnull Lazy<AppGalleryService> lazy) {
        super(CompletedEvent.class);
        this.type = str;
        this.appGalleryService = lazy;
    }

    /* access modifiers changed from: protected */
    public List<MfpPlatformApp> exec(Context context) throws ApiException {
        return ((AppGalleryService) this.appGalleryService.get()).getAppList(this.type);
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        StringBuilder sb = new StringBuilder();
        sb.append(TASK_NAME_BASE);
        sb.append(this.type);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.None;
    }
}
