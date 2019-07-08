package com.myfitnesspal.shared.service.imagesync.ops;

import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.ABTest.EnableImageDeletions;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.model.v2.MfpImageAssociation;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;

public class DisassociateOp implements UacfScheduleOp {
    private final Lazy<ConfigService> configService;
    private final Lazy<ImageAssociationService> imageAssociationService;
    private final Lazy<ImageService> imageService;

    public void onRetriesExhausted() {
    }

    public DisassociateOp(Lazy<ImageAssociationService> lazy, Lazy<ImageService> lazy2, Lazy<ConfigService> lazy3) {
        this.imageAssociationService = lazy;
        this.imageService = lazy2;
        this.configService = lazy3;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        boolean z;
        boolean z2;
        List<MfpImageAssociation> pendingDisassociations = ((ImageAssociationService) this.imageAssociationService.get()).getPendingDisassociations();
        Ln.d("ImageSync.DisassociateOp: cleaned up %d orphaned associations", Integer.valueOf(((ImageAssociationService) this.imageAssociationService.get()).removeLocalDisassociations()));
        if (CollectionUtils.isEmpty((Collection<?>) pendingDisassociations)) {
            Ln.d("ImageSync.DisassociateOp: nothing to disassociate", new Object[0]);
            z = true;
        } else {
            for (MfpImageAssociation remoteDisassociate : pendingDisassociations) {
                try {
                    ((ImageAssociationService) this.imageAssociationService.get()).remoteDisassociate(remoteDisassociate);
                } catch (ApiException e) {
                    Ln.d("ImageSync.DisassociateOp: disassociate failed! returning Retry", new Object[0]);
                    return Result.retry(new UacfScheduleException(e));
                }
            }
            z = false;
        }
        if (((ConfigService) this.configService.get()).isVariantEnabled(EnableImageDeletions.NAME)) {
            List<MfpImage> pendingDeletions = ((ImageService) this.imageService.get()).getPendingDeletions();
            if (CollectionUtils.isEmpty((Collection<?>) pendingDeletions)) {
                Ln.d("ImageSync.DisassociateOp: no images to delete", new Object[0]);
                z2 = true;
            } else {
                for (MfpImage deleteImage : pendingDeletions) {
                    try {
                        ((ImageService) this.imageService.get()).deleteImage(deleteImage);
                    } catch (ApiException e2) {
                        Ln.d("ImageSync.DisassociateOp: deletion failed! returning Retry", new Object[0]);
                        return Result.retry(new UacfScheduleException(e2));
                    }
                }
                z2 = false;
            }
        } else {
            z2 = true;
        }
        if (!z || !z2) {
            Ln.d("ImageSync.DisassociateOp: disassociated %d images", Integer.valueOf(pendingDisassociations.size()));
            return Result.yield();
        }
        Ln.d("no associations OR images. returning completed!", new Object[0]);
        return Result.completed();
    }
}
