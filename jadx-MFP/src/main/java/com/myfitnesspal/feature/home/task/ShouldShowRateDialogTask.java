package com.myfitnesspal.feature.home.task;

import android.content.Context;
import com.myfitnesspal.feature.home.service.AppRatingService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import dagger.Lazy;

public class ShouldShowRateDialogTask extends Unchecked<Boolean> {
    private Lazy<AppRatingService> appRatingService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<Boolean> {
    }

    public ShouldShowRateDialogTask(Lazy<AppRatingService> lazy) {
        super(CompletedEvent.class);
        this.appRatingService = lazy;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) {
        return Boolean.valueOf(((AppRatingService) this.appRatingService.get()).shouldShowDialog());
    }
}
