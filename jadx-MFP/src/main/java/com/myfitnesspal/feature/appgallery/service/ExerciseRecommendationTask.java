package com.myfitnesspal.feature.appgallery.service;

import android.content.Context;
import com.myfitnesspal.feature.appgallery.api.ExerciseTrackingAppRecommendation;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;
import java.util.List;
import javax.annotation.Nonnull;

public class ExerciseRecommendationTask extends EventedTaskBase<List<ExerciseTrackingAppRecommendation>, ApiException> {
    private static final String TASK_NAME = "ExerciseAppTrackingRecommendation";
    private Lazy<AppGalleryService> appGalleryService;
    private final String exerciseId;

    public static class CompletedEvent extends TaskEventBase<List<ExerciseTrackingAppRecommendation>, ApiException> {
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        return TASK_NAME;
    }

    public ExerciseRecommendationTask(String str, @Nonnull Lazy<AppGalleryService> lazy) {
        super(CompletedEvent.class);
        this.exerciseId = str;
        this.appGalleryService = lazy;
    }

    /* access modifiers changed from: protected */
    public List<ExerciseTrackingAppRecommendation> exec(Context context) throws ApiException {
        return ((AppGalleryService) this.appGalleryService.get()).getExerciseRecommendationApp(this.exerciseId);
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.None;
    }
}
