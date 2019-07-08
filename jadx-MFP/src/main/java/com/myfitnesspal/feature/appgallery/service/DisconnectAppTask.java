package com.myfitnesspal.feature.appgallery.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;
import javax.annotation.Nonnull;

public class DisconnectAppTask extends EventedTaskBase<ApiResponseBase, ApiException> {
    private final Lazy<AppGalleryService> appGalleryService;
    private String clientId;

    public static class CompletedEvent extends TaskEventBase<ApiResponseBase, ApiException> {
    }

    public DisconnectAppTask(String str, @Nonnull Lazy<AppGalleryService> lazy) {
        super(CompletedEvent.class);
        this.clientId = str;
        this.appGalleryService = lazy;
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.None;
    }

    /* access modifiers changed from: protected */
    public ApiResponseBase exec(Context context) throws ApiException {
        return ((AppGalleryService) this.appGalleryService.get()).disconnectFromApp(this.clientId);
    }
}
