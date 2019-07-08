package com.myfitnesspal.feature.progress.task;

import android.content.Context;
import com.myfitnesspal.feature.progress.constants.GalleryDataMode;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryImageViewModel;
import com.myfitnesspal.framework.taskrunner.TaskBase;
import dagger.Lazy;
import java.util.List;

public final class GetGalleryImagesTask extends TaskBase<List<GalleryImageViewModel>, Exception> {
    private static final String TASK_NAME = GetGalleryImagesTask.class.getCanonicalName();
    private final GalleryDataMode dataMode;
    private final String measurementType;
    private final Lazy<ProgressService> progressService;

    public static boolean matches(String str) {
        return str.equals(TASK_NAME);
    }

    public GetGalleryImagesTask(Lazy<ProgressService> lazy, String str, GalleryDataMode galleryDataMode) {
        this.progressService = lazy;
        this.measurementType = str;
        this.dataMode = galleryDataMode;
    }

    /* access modifiers changed from: protected */
    public List<GalleryImageViewModel> exec(Context context) throws Exception {
        if (this.dataMode == GalleryDataMode.Import) {
            return ((ProgressService) this.progressService.get()).getGalleryImagesForImportMode(this.measurementType);
        }
        return ((ProgressService) this.progressService.get()).getGalleryImagesForViewMode(this.measurementType);
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        return TASK_NAME;
    }
}
