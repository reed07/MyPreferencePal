package com.myfitnesspal.feature.appgallery.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;
import java.util.List;
import javax.annotation.Nonnull;

public class QuerySingleAppTask extends EventedTaskBase<MfpPlatformApp, ApiException> {
    private static final String TASK_NAME_BASE = "GetSingleApp-";
    private final Lazy<AppGalleryService> appGalleryService;
    /* access modifiers changed from: private */
    public final String applicationId;

    public static class CompletedEvent extends TaskEventBase<List<MfpPlatformApp>, Exception> {
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    public QuerySingleAppTask(String str, @Nonnull Lazy<AppGalleryService> lazy) {
        super(CompletedEvent.class);
        this.applicationId = str;
        this.appGalleryService = lazy;
    }

    /* access modifiers changed from: protected */
    public MfpPlatformApp exec(Context context) throws ApiException {
        return (MfpPlatformApp) Enumerable.firstOrDefault(((AppGalleryService) this.appGalleryService.get()).getAppList("all"), new ReturningFunction1<Boolean, MfpPlatformApp>() {
            public Boolean execute(MfpPlatformApp mfpPlatformApp) {
                return Boolean.valueOf(Strings.equals((Object) Integer.valueOf(mfpPlatformApp.getId()), (Object) QuerySingleAppTask.this.applicationId));
            }
        });
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        StringBuilder sb = new StringBuilder();
        sb.append(TASK_NAME_BASE);
        sb.append(this.applicationId);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.None;
    }
}
