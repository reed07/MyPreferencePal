package com.myfitnesspal.feature.images.service;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import java.util.List;

public interface ImageService extends SyncItemHandler<MfpImage> {
    MfpImage createImage(long j, String str, String str2, int i, int i2) throws ApiException;

    long createLocalImage(String str, String str2);

    void deleteImage(MfpImage mfpImage) throws ApiException;

    List<MfpImage> getImagesForResource(String str, String str2, long j);

    List<MfpImage> getPendingDeletions();

    String getStableImageUrlById(String str);

    String getStableImageUrlById(String str, int i, int i2);

    boolean updateSyncFlag(MfpImage mfpImage, int i);
}
