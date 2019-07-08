package com.myfitnesspal.shared.service.imagesync.ops;

import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.feature.images.util.ImageUploadUtil;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.uacf.core.util.Ln;
import com.uacf.sync.engine.UacfScheduleContext;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduleOp;
import com.uacf.sync.engine.UacfScheduleOp.Progress;
import com.uacf.sync.engine.UacfScheduleOp.Result;
import dagger.Lazy;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadOp implements UacfScheduleOp {
    private static final int MAXIMUM_IMAGE_SIZE_LONG_EDGE_PX = 1500;
    private long firstLocalId = -1;
    private final Lazy<ImageAssociationService> imageAssociationService;
    private final Lazy<ImageUploadService> imageUploadService;

    public void onRetriesExhausted() {
    }

    public UploadOp(Lazy<ImageUploadService> lazy, Lazy<ImageAssociationService> lazy2) {
        this.imageUploadService = lazy;
        this.imageAssociationService = lazy2;
    }

    public Result sync(UacfScheduleContext uacfScheduleContext, Progress progress) throws UacfScheduleException {
        ImageUploadService imageUploadService2 = (ImageUploadService) this.imageUploadService.get();
        MfpImage nextUpload = imageUploadService2.getNextUpload();
        if (nextUpload == null || nextUpload.getLocalId() == this.firstLocalId) {
            Ln.d("ImageSync.UploadOp: no images ready for upload. Completed", new Object[0]);
            return Result.completed();
        }
        long localId = nextUpload.getLocalId();
        if (this.firstLocalId == -1) {
            this.firstLocalId = localId;
        }
        try {
            Ln.d("ImageSync.UploadOp: uploading image at path %s", nextUpload.getLocalFilepath());
            MfpImage uploadImage = imageUploadService2.uploadImage(localId, nextUpload.getLocalFilepath(), 1500, getImageTypeForAnalytics(nextUpload));
            if (uploadImage != null) {
                ((ImageAssociationService) this.imageAssociationService.get()).updateImageUidForAssociationsWithImageLocalId(nextUpload.getLocalId(), nextUpload.getId());
                if (imageUploadService2.markUploadSucceeded(localId, uploadImage, 4)) {
                    Ln.d("ImageSync.UploadOp: upload finished, marking as success", new Object[0]);
                    ImageUploadUtil.checkDeleteTempImage(uacfScheduleContext.getContext(), nextUpload.getLocalFilepath());
                    return Result.yield();
                }
            } else {
                imageUploadService2.markUploadFailed(localId, 0);
            }
            Ln.d("ImageSync.UploadOp: upload finished, but unable to mark as success", new Object[0]);
            return Result.retry(null);
        } catch (SecurityException unused) {
            Ln.d("ImageSync.UploadOp: upload failed with SecurityException, removing association", new Object[0]);
            imageUploadService2.markUploadFailed(localId, 1);
            ImageUploadUtil.checkDeleteTempImage(uacfScheduleContext.getContext(), nextUpload.getLocalFilepath());
            return Result.yield();
        } catch (FileNotFoundException unused2) {
            Ln.d("ImageSync.UploadOp: upload failed with FileNotFoundException, removing association", new Object[0]);
            imageUploadService2.markUploadFailed(localId, 1);
            ImageUploadUtil.checkDeleteTempImage(uacfScheduleContext.getContext(), nextUpload.getLocalFilepath());
            return Result.yield();
        } catch (IOException e) {
            Ln.d("ImageSync.UploadOp: upload failed with generic IOException, re-scheduling...", new Object[0]);
            imageUploadService2.markUploadFailed(localId, 0);
            return Result.retry(new UacfScheduleException(e));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004b, code lost:
        if (r5.equals("measurement") != false) goto L_0x004f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.feature.images.service.ImageUploadService.ImageType getImageTypeForAnalytics(com.myfitnesspal.shared.model.v2.MfpImage r5) {
        /*
            r4 = this;
            dagger.Lazy<com.myfitnesspal.feature.images.service.ImageAssociationService> r0 = r4.imageAssociationService
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.feature.images.service.ImageAssociationService r0 = (com.myfitnesspal.feature.images.service.ImageAssociationService) r0
            long r1 = r5.getLocalId()
            java.lang.String r5 = r5.getId()
            java.util.List r5 = r0.findAssociationsForImage(r1, r5)
            boolean r0 = com.uacf.core.util.CollectionUtils.notEmpty(r5)
            if (r0 == 0) goto L_0x0059
            r0 = 0
            java.lang.Object r5 = r5.get(r0)
            com.myfitnesspal.shared.model.v2.MfpImageAssociation r5 = (com.myfitnesspal.shared.model.v2.MfpImageAssociation) r5
            java.lang.String r5 = r5.getResourceType()
            java.lang.String r1 = ""
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5, r1)
            r1 = -1
            int r2 = r5.hashCode()
            r3 = -1812800580(0xffffffff93f2dbbc, float:-6.1306063E-27)
            if (r2 == r3) goto L_0x0045
            r0 = -77579951(0xfffffffffb603951, float:-1.164237E36)
            if (r2 == r0) goto L_0x003b
            goto L_0x004e
        L_0x003b:
            java.lang.String r0 = "food_entry"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x004e
            r0 = 1
            goto L_0x004f
        L_0x0045:
            java.lang.String r2 = "measurement"
            boolean r5 = r5.equals(r2)
            if (r5 == 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r0 = -1
        L_0x004f:
            switch(r0) {
                case 0: goto L_0x0056;
                case 1: goto L_0x0053;
                default: goto L_0x0052;
            }
        L_0x0052:
            goto L_0x0059
        L_0x0053:
            com.myfitnesspal.feature.images.service.ImageUploadService$ImageType r5 = com.myfitnesspal.feature.images.service.ImageUploadService.ImageType.MealPhoto
            return r5
        L_0x0056:
            com.myfitnesspal.feature.images.service.ImageUploadService$ImageType r5 = com.myfitnesspal.feature.images.service.ImageUploadService.ImageType.ProgressPhoto
            return r5
        L_0x0059:
            com.myfitnesspal.feature.images.service.ImageUploadService$ImageType r5 = com.myfitnesspal.feature.images.service.ImageUploadService.ImageType.ProgressPhoto
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.imagesync.ops.UploadOp.getImageTypeForAnalytics(com.myfitnesspal.shared.model.v2.MfpImage):com.myfitnesspal.feature.images.service.ImageUploadService$ImageType");
    }
}
