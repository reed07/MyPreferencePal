package com.myfitnesspal.shared.service.userdata;

import com.myfitnesspal.shared.model.v1.UserImage;

public interface UserImageService {
    int deleteImageWithLocalId(long j);

    byte[] getImageDataForImageId(long j, boolean z);

    long getImageIdForMostRecentThumbnail(long j);

    long getImageLocalIdForMasterId(long j, int i);

    UserImage getUserImageWithLocalId(long j);

    void insertOrUpdateUserImage(UserImage userImage);

    void updateImageDataForImageId(long j, byte[] bArr, boolean z);
}
