package com.myfitnesspal.shared.service.userdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.facebook.appevents.AppEventsConstants;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.ProfileImagesTable;
import com.myfitnesspal.shared.db.table.ProfileImagesTable.Columns;
import com.myfitnesspal.shared.model.v1.UserImage;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;

public class UserImageServiceImpl implements UserImageService {
    private static final SimpleDateFormat DATE_FORMAT = Format.new24HourDatabaseDateTimeFormat();
    private static final String DATE_FORMAT_STRING = DATE_FORMAT.toPattern();
    private final Context context;
    private final ProfileImagesTable profileImagesTable;
    private final Lazy<Session> session;

    @Inject
    public UserImageServiceImpl(Context context2, Lazy<Session> lazy, ProfileImagesTable profileImagesTable2) {
        this.context = context2;
        this.session = lazy;
        this.profileImagesTable = profileImagesTable2;
    }

    public void insertOrUpdateUserImage(UserImage userImage) {
        if (userImage.hasMasterDatabaseId()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("master_id", Long.valueOf(userImage.masterDatabaseId));
            contentValues.put("image_type", Integer.valueOf(1));
            contentValues.putNull(Columns.SECONDARY_ID);
            contentValues.putNull(Columns.SECONDARY_MASTER_ID);
            contentValues.put(Columns.IS_VISIBLE, Integer.valueOf(userImage.isVisible() ? 1 : 0));
            contentValues.put("position", Integer.valueOf(userImage.getPosition()));
            contentValues.put("width", Integer.valueOf(userImage.getWidth()));
            contentValues.put("height", Integer.valueOf(userImage.getHeight()));
            if (Strings.notEmpty(userImage.getUid())) {
                contentValues.put("uid", userImage.getUid());
            } else {
                contentValues.putNull("uid");
            }
            if (userImage.getFileType() != null) {
                contentValues.put(Columns.FILE_TYPE, userImage.getFileType());
            } else {
                contentValues.putNull(Columns.FILE_TYPE);
            }
            if (userImage.getFilename() != null) {
                contentValues.put(Columns.FILENAME, userImage.getFilename());
            } else {
                contentValues.putNull(Columns.FILENAME);
            }
            if (userImage.getThumbnailURL() != null) {
                contentValues.put(Columns.THUMBNAIL_IMAGE_URL, userImage.getThumbnailURL());
            } else {
                contentValues.putNull(Columns.THUMBNAIL_IMAGE_URL);
            }
            if (userImage.getFullImageURL() != null) {
                contentValues.put(Columns.FULL_IMAGE_URL, userImage.getFullImageURL());
            } else {
                contentValues.putNull(Columns.FULL_IMAGE_URL);
            }
            contentValues.put("created_at", encodeDateAndTime(userImage.getCreatedAt()));
            contentValues.put("updated_at", encodeDateAndTime(userImage.getUpdatedAt()));
            if (DbConnectionManager.getDb(this.context).update(ProfileImagesTable.TABLE_NAME, contentValues, "master_id = ? and image_type = ?", new String[]{String.valueOf(userImage.masterDatabaseId), String.valueOf(1)}) > 0) {
                return;
            }
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("master_id", userImage.hasMasterDatabaseId() ? Long.valueOf(userImage.getMasterDatabaseId()) : null);
        contentValues2.put("uid", userImage.hasUid() ? userImage.getUid() : null);
        contentValues2.put("user_id", Long.valueOf(userImage.getCreatorUserId()));
        contentValues2.put(Columns.CREATOR_UID, Strings.notEmpty(userImage.getCreatorUid()) ? userImage.getCreatorUid() : null);
        contentValues2.put("image_type", Long.valueOf(1));
        byte[] bArr = null;
        contentValues2.put(Columns.THUMBNAIL_IMAGE_DATA, bArr);
        contentValues2.put(Columns.FULLSIZE_IMAGE_DATA, bArr);
        bindUserImageProperties(userImage, contentValues2);
        userImage.setLocalId(this.profileImagesTable.insertData(contentValues2));
    }

    public UserImage getUserImageWithLocalId(long j) {
        UserImage userImage;
        Cursor query = DbConnectionManager.getDb(this.context).query(ProfileImagesTable.TABLE_NAME, new String[]{"master_id", "uid", "user_id", Columns.CREATOR_UID, "image_type", Columns.SECONDARY_ID, Columns.SECONDARY_MASTER_ID, Columns.IS_VISIBLE, "position", "width", "height", Columns.FILE_TYPE, Columns.FILENAME, Columns.THUMBNAIL_IMAGE_URL, Columns.FULL_IMAGE_URL, "created_at", "updated_at"}, "id=?", new String[]{String.valueOf(j)}, null, null, null);
        try {
            if (query.moveToFirst()) {
                do {
                    userImage = new UserImage();
                    userImage.setLocalId(j);
                    userImage.setMasterDatabaseId(query.getLong(query.getColumnIndex("master_id")));
                    userImage.setUid(query.getString(query.getColumnIndex("uid")));
                    userImage.setCreatorUserId(query.getLong(query.getColumnIndex("user_id")));
                    userImage.setCreatorUid(query.getString(query.getColumnIndex(Columns.CREATOR_UID)));
                    userImage.setVisible(query.getLong(query.getColumnIndex(Columns.IS_VISIBLE)) == 1);
                    userImage.setPosition((int) query.getLong(query.getColumnIndex("position")));
                    userImage.setWidth((int) query.getLong(query.getColumnIndex("width")));
                    userImage.setHeight((int) query.getLong(query.getColumnIndex("height")));
                    userImage.setFileType(query.getString(query.getColumnIndex(Columns.FILE_TYPE)));
                    userImage.setFilename(query.getString(query.getColumnIndex(Columns.FILENAME)));
                    userImage.setThumbnailURL(query.getString(query.getColumnIndex(Columns.THUMBNAIL_IMAGE_URL)));
                    userImage.setFullImageURL(query.getString(query.getColumnIndex(Columns.FULL_IMAGE_URL)));
                    userImage.setCreatedAt(decodeDateString(query.getString(query.getColumnIndex("created_at"))));
                    userImage.setUpdatedAt(decodeDateString(query.getString(query.getColumnIndex("updated_at"))));
                } while (query.moveToNext());
            } else {
                userImage = null;
            }
            return userImage;
        } finally {
            query.close();
        }
    }

    /* JADX INFO: finally extract failed */
    public long getImageLocalIdForMasterId(long j, int i) {
        int i2;
        Cursor query = DbConnectionManager.getDb(this.context).query(ProfileImagesTable.TABLE_NAME, new String[]{"id"}, String.format("%s=? and %s=? and %s=?", new Object[]{"user_id", "master_id", "image_type"}), new String[]{String.valueOf(((Session) this.session.get()).getUser().getLocalId()), String.valueOf(j), String.valueOf(i)}, null, null, null);
        try {
            if (query.moveToFirst()) {
                do {
                    i2 = (int) query.getLong(0);
                } while (query.moveToNext());
            } else {
                i2 = 0;
            }
            query.close();
            return (long) i2;
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }

    public int deleteImageWithLocalId(long j) {
        return DbConnectionManager.getDb(this.context).delete(ProfileImagesTable.TABLE_NAME, "id=?", new String[]{String.valueOf(j)});
    }

    public long getImageIdForMostRecentThumbnail(long j) {
        long j2;
        String[] strArr = {"id"};
        String format = String.format("%s=? AND %s=? AND IFNULL(%s, '') != ''", new Object[]{"user_id", "image_type", Columns.THUMBNAIL_IMAGE_URL});
        Cursor query = DbConnectionManager.getDb(this.context).query(ProfileImagesTable.TABLE_NAME, strArr, format, new String[]{String.valueOf(j), String.valueOf(1)}, null, null, "position asc", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        try {
            if (query.moveToFirst()) {
                do {
                    j2 = query.getLong(0);
                } while (query.moveToNext());
            } else {
                j2 = -1;
            }
            return j2;
        } finally {
            query.close();
        }
    }

    public void updateImageDataForImageId(long j, byte[] bArr, boolean z) {
        String str = z ? Columns.THUMBNAIL_IMAGE_DATA : Columns.FULLSIZE_IMAGE_DATA;
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, bArr);
        DbConnectionManager.getDb(this.context).update(ProfileImagesTable.TABLE_NAME, contentValues, "id = ?", new String[]{String.valueOf(j)});
    }

    public byte[] getImageDataForImageId(long j, boolean z) {
        Cursor query = DbConnectionManager.getDb(this.context).query(ProfileImagesTable.TABLE_NAME, new String[]{z ? Columns.THUMBNAIL_IMAGE_DATA : Columns.FULLSIZE_IMAGE_DATA}, "id=?", new String[]{String.valueOf(j)}, null, null, null);
        try {
            return query.moveToFirst() ? query.getBlob(0) : null;
        } finally {
            query.close();
        }
    }

    private void bindUserImageProperties(UserImage userImage, ContentValues contentValues) {
        String str = null;
        contentValues.put(Columns.SECONDARY_ID, str);
        contentValues.put(Columns.SECONDARY_MASTER_ID, str);
        contentValues.put(Columns.IS_VISIBLE, Integer.valueOf(userImage.isVisible() ? 1 : 0));
        contentValues.put("position", Integer.valueOf(userImage.getPosition()));
        contentValues.put("width", Integer.valueOf(userImage.getWidth()));
        contentValues.put("height", Integer.valueOf(userImage.getHeight()));
        contentValues.put(Columns.FILE_TYPE, userImage.getFileType());
        contentValues.put(Columns.FILENAME, userImage.getFilename());
        contentValues.put(Columns.THUMBNAIL_IMAGE_URL, userImage.getThumbnailURL());
        contentValues.put(Columns.FULL_IMAGE_URL, userImage.getFullImageURL());
        contentValues.put("created_at", encodeDateAndTime(userImage.getCreatedAt()));
        contentValues.put("updated_at", encodeDateAndTime(userImage.getUpdatedAt()));
    }

    private String encodeDateAndTime(Date date) {
        return DATE_FORMAT.format(date);
    }

    private Date decodeDateString(String str) {
        return DateTimeUtils.parse(DATE_FORMAT_STRING, str);
    }
}
