package com.myfitnesspal.feature.progress.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.feature.progress.ui.viewmodel.ArtifactViewModel;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import java.util.List;

public class LoadArtifactDataTask extends EventedTaskBase<Tuple2<Bitmap, List<ArtifactViewModel>>, Exception> {
    private final String fromDate;
    private final String imageFilename;
    private final boolean isOneImageCongrats;
    private final Lazy<ProgressService> progressService;
    private final String toDate;

    public static class CompletedEvent extends TaskEventBase<Tuple2<Bitmap, List<ArtifactViewModel>>, Exception> {
    }

    public LoadArtifactDataTask(Lazy<ProgressService> lazy, String str, String str2, String str3, boolean z) {
        super((TaskEventBase) new CompletedEvent());
        this.progressService = lazy;
        this.fromDate = str;
        this.toDate = str2;
        this.imageFilename = str3;
        this.isOneImageCongrats = z;
    }

    /* access modifiers changed from: protected */
    public Tuple2<Bitmap, List<ArtifactViewModel>> exec(Context context) throws Exception {
        return Tuple2.create(BitmapFactory.decodeFile(this.imageFilename), ((ProgressService) this.progressService.get()).getShareProgressArtifactData(this.fromDate, this.toDate, this.isOneImageCongrats));
    }
}
