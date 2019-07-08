package com.myfitnesspal.shared.service.imagesync.ops;

import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.exception.DuplicateResourceException;
import com.myfitnesspal.shared.model.v2.MfpImageAssociation;
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

public class AssociateOp implements UacfScheduleOp {
    private static final String ALREADY_ASSOCIATED = "image-association/005";
    private Lazy<ImageAssociationService> imageAssociationService;

    public void onRetriesExhausted() {
    }

    public AssociateOp(Lazy<ImageAssociationService> lazy) {
        this.imageAssociationService = lazy;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        List<MfpImageAssociation> pendingAssociations = ((ImageAssociationService) this.imageAssociationService.get()).getPendingAssociations();
        if (CollectionUtils.isEmpty((Collection<?>) pendingAssociations)) {
            Ln.d("ImageSync.AssociateOp: nothing to process remotely, returning Completed", new Object[0]);
            return Result.completed();
        }
        for (MfpImageAssociation mfpImageAssociation : pendingAssociations) {
            try {
                ((ImageAssociationService) this.imageAssociationService.get()).remoteAssociate(mfpImageAssociation.getId(), mfpImageAssociation.getLocalId(), mfpImageAssociation.getImageId(), mfpImageAssociation.getResourceType(), mfpImageAssociation.getResourceId());
            } catch (DuplicateResourceException unused) {
            } catch (ApiException e) {
                if (!ALREADY_ASSOCIATED.equals(e.getApiError().getError())) {
                    return Result.retry(new UacfScheduleException(e));
                }
            }
            ((ImageAssociationService) this.imageAssociationService.get()).updateSyncFlag(mfpImageAssociation.getId(), 4);
        }
        Ln.d("ImageSync.AssociateOp: processed %d image_associations remotely", Integer.valueOf(pendingAssociations.size()));
        return Result.yield();
    }
}
