package com.myfitnesspal.feature.images.service;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.uacf.core.util.Strings;

public final class ImageServiceUtil {
    public static String getImageThumbnailUri(Context context, ImageService imageService, String str) {
        return imageService.getStableImageUrlById(str, context.getResources().getDimensionPixelSize(R.dimen.progress_photo_thumbnail_download_size), 0);
    }

    public static String getImageThumbnailUri(ImageService imageService, String str, int i, int i2) {
        return imageService.getStableImageUrlById(str, i, i2);
    }

    public static String getImageThumbnailUri(Context context, ImageService imageService, MfpImage mfpImage) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.progress_photo_thumbnail_download_size);
        String id = mfpImage.getId();
        if (Strings.notEmpty(id)) {
            return imageService.getStableImageUrlById(id, dimensionPixelSize, 0);
        }
        return mfpImage.getLocalFilepath();
    }

    public static String getImageUri(ImageService imageService, MfpImage mfpImage) {
        if (Strings.notEmpty(mfpImage.getId())) {
            return imageService.getStableImageUrlById(mfpImage.getId());
        }
        return mfpImage.getLocalFilepath();
    }
}
