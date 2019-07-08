package com.myfitnesspal.shared.model.v1;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import java.io.ByteArrayOutputStream;
import java.util.Date;

public class UserImage extends DatabaseObject {
    private Date createdAt;
    private String creatorUid;
    private long creatorUserId;
    private String fileType;
    private String filename;
    private String fullImageURL;
    private int height;
    private byte[] imageData;
    private boolean isVisible;
    private int position;
    private String thumbnailURL;
    private Date updatedAt;
    private int width;

    public int itemType() {
        return 15;
    }

    public static UserImage createNewProfileImageForCurrentUser(UserImageService userImageService, Session session, Bitmap bitmap) {
        UserImage userImage = new UserImage();
        User user = session.getUser();
        userImage.setCreatorUserId(user.getLocalId());
        userImage.setCreatorUid(user.getUid());
        userImage.setVisible(true);
        userImage.setPosition(0);
        userImage.setFileType("png");
        userImage.setCreatedAt(new Date());
        userImage.setUpdatedAt(new Date());
        userImageService.insertOrUpdateUserImage(userImage);
        if (userImage.hasLocalId()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
            userImageService.updateImageDataForImageId(userImage.localId, byteArrayOutputStream.toByteArray(), false);
        }
        return userImage;
    }

    public static Bitmap fetchMostRecentUserImage(UserImageService userImageService, Session session, DbConnectionManager dbConnectionManager) {
        Long[] fetchUnsyncedEntryItemIdsOfType = dbConnectionManager.genericDbAdapter().fetchUnsyncedEntryItemIdsOfType(15, session.getUser().getLocalId(), 20);
        if (fetchUnsyncedEntryItemIdsOfType == null || fetchUnsyncedEntryItemIdsOfType.length <= 0) {
            return null;
        }
        byte[] imageDataForImageId = userImageService.getImageDataForImageId(userImageService.getUserImageWithLocalId(fetchUnsyncedEntryItemIdsOfType[fetchUnsyncedEntryItemIdsOfType.length - 1].longValue()).localId, false);
        return BitmapFactory.decodeByteArray(imageDataForImageId, 0, imageDataForImageId.length);
    }

    public long getCreatorUserId() {
        return this.creatorUserId;
    }

    public void setCreatorUserId(long j) {
        this.creatorUserId = j;
    }

    public String getCreatorUid() {
        return this.creatorUid;
    }

    public void setCreatorUid(String str) {
        this.creatorUid = str;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean z) {
        this.isVisible = z;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String str) {
        this.fileType = str;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String str) {
        this.filename = str;
    }

    public String getThumbnailURL() {
        return this.thumbnailURL;
    }

    public void setThumbnailURL(String str) {
        this.thumbnailURL = str;
    }

    public String getFullImageURL() {
        return this.fullImageURL;
    }

    public void setFullImageURL(String str) {
        this.fullImageURL = str;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date date) {
        this.updatedAt = date;
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public void setImageData(byte[] bArr) {
        this.imageData = bArr;
    }
}
