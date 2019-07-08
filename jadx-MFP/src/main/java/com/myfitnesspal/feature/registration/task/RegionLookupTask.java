package com.myfitnesspal.feature.registration.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;
import javax.annotation.Nonnull;

public class RegionLookupTask extends EventedTaskBase<String, ApiException> {
    private static final String TASK_NAME = "RegionLookup";
    final String countryCode;
    private final Lazy<SignUpService> signUpService;

    public static class CompletedEvent extends TaskEventBase<String, ApiException> {
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        return TASK_NAME;
    }

    public RegionLookupTask(String str, @Nonnull Lazy<SignUpService> lazy) {
        super(CompletedEvent.class);
        this.countryCode = str;
        this.signUpService = lazy;
    }

    /* access modifiers changed from: protected */
    public String exec(Context context) throws ApiException {
        return ((SignUpService) this.signUpService.get()).getRegionFromCountryCode(this.countryCode);
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.None;
    }
}
