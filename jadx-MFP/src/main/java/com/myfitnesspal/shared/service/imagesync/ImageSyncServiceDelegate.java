package com.myfitnesspal.shared.service.imagesync;

import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.imagesync.ops.AssociateOp;
import com.myfitnesspal.shared.service.imagesync.ops.DisassociateOp;
import com.myfitnesspal.shared.service.imagesync.ops.UploadOp;
import com.uacf.core.util.Ln;
import com.uacf.sync.engine.UacfScheduleEnqueuedInfo;
import com.uacf.sync.engine.UacfScheduleFailedInfo;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import com.uacf.sync.engine.UacfScheduleOp;
import com.uacf.sync.engine.UacfScheduleProgressInfo;
import com.uacf.sync.engine.UacfScheduleStartedInfo;
import com.uacf.sync.engine.UacfSchedulerEngineDelegate;
import dagger.Lazy;
import java.util.Arrays;
import java.util.List;

public class ImageSyncServiceDelegate implements UacfSchedulerEngineDelegate<ImageSyncMode> {
    private final Lazy<ConfigService> configService;
    private final Lazy<ImageAssociationService> imageAssociationService;
    private final Lazy<ImageService> imageService;
    private final Lazy<ImageUploadService> imageUploadService;

    public ImageSyncServiceDelegate(Lazy<ImageService> lazy, Lazy<ImageAssociationService> lazy2, Lazy<ImageUploadService> lazy3, Lazy<ConfigService> lazy4) {
        this.imageService = lazy;
        this.imageAssociationService = lazy2;
        this.imageUploadService = lazy3;
        this.configService = lazy4;
    }

    public void onSyncFinished(UacfScheduleFinishedInfo<ImageSyncMode> uacfScheduleFinishedInfo) {
        Ln.d("ImageSyncService finished. id=%s, successful=%s", uacfScheduleFinishedInfo.getScheduleId(), Boolean.valueOf(uacfScheduleFinishedInfo.isSuccessful()));
    }

    public void onSyncFailed(UacfScheduleFailedInfo<ImageSyncMode> uacfScheduleFailedInfo) {
        Ln.d(uacfScheduleFailedInfo.getException(), "ImageSyncService failed. id=%s", uacfScheduleFailedInfo.getScheduleId());
    }

    public void onSyncEnqueued(UacfScheduleEnqueuedInfo<ImageSyncMode> uacfScheduleEnqueuedInfo) {
        Ln.d("ImageSyncService operation enqueued. type=%s, id=%s", uacfScheduleEnqueuedInfo.getScheduleGroup(), uacfScheduleEnqueuedInfo.getScheduleId());
    }

    public void onSyncStarted(UacfScheduleStartedInfo<ImageSyncMode> uacfScheduleStartedInfo) {
        Ln.d("ImageSyncService operation started. type=%s, id=%s", uacfScheduleStartedInfo.getScheduleType(), uacfScheduleStartedInfo.getScheduleId());
    }

    public void onSyncProgress(UacfScheduleProgressInfo<ImageSyncMode> uacfScheduleProgressInfo) {
        Ln.d("ImageSyncService progress: id=%s, message=%s", uacfScheduleProgressInfo.getScheduleId(), uacfScheduleProgressInfo.getText());
    }

    public List<UacfScheduleOp> getSyncOpsForType(ImageSyncMode imageSyncMode) {
        return Arrays.asList(new UacfScheduleOp[]{new UploadOp(this.imageUploadService, this.imageAssociationService), new AssociateOp(this.imageAssociationService), new DisassociateOp(this.imageAssociationService, this.imageService, this.configService)});
    }
}
