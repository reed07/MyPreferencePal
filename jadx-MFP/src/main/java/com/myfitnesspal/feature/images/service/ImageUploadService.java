package com.myfitnesspal.feature.images.service;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImageUploadService {

    public enum ImageType {
        ProgressPhoto("progress_photo"),
        MealPhoto("meal_photo"),
        ProgressPhotoArtifact("progress_photo_artifact"),
        MealPhotoArtifact("meal_photo_artifact"),
        StatusPhoto("status_photo");
        
        private String analyticsValue;

        private ImageType(String str) {
            this.analyticsValue = str;
        }

        public String getAnalyticsValue() {
            return this.analyticsValue;
        }
    }

    MfpImage getNextUpload();

    boolean markUploadFailed(long j, int i);

    boolean markUploadSucceeded(long j, MfpImage mfpImage, int i);

    MfpImage uploadImage(long j, String str, int i, ImageType imageType) throws ApiException, FileNotFoundException, IOException;

    MfpImage uploadImage(long j, String str, ImageType imageType) throws ApiException, FileNotFoundException, IOException;
}
