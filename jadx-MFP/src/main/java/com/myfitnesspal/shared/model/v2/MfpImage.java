package com.myfitnesspal.shared.model.v2;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.db.table.ImagesTable.Columns;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MfpImage implements Parcelable {
    public static final Creator<MfpImage> CREATOR = new Creator<MfpImage>() {
        public MfpImage createFromParcel(Parcel parcel) {
            return new MfpImage(parcel);
        }

        public MfpImage[] newArray(int i) {
            return new MfpImage[i];
        }
    };
    @Expose
    private String checksum;
    @Expose
    private String createdAt;
    @Expose
    private String description;
    @Expose
    private boolean flagged;
    @Expose
    private String format;
    @Expose
    private int height;
    @Expose
    private String id;
    private String localFilepath;
    private long localId = -1;
    @Expose
    private String status;
    @Expose
    private String updatedAt;
    @Expose(deserialize = true, serialize = false)
    private String uploadLocation;
    @Expose(deserialize = true, serialize = false)
    private Date uploadLocationTimeout;
    @Expose
    private String userId;
    @Expose
    private int width;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpImage> {
    }

    public interface Status {
        public static final String AVAILABLE = "available";
    }

    public int describeContents() {
        return 0;
    }

    public MfpImage() {
    }

    public MfpImage(Parcel parcel) {
        readFromParcel(parcel);
    }

    public MfpImage(CursorMapper cursorMapper) {
        this.localId = cursorMapper.getLong("id");
        this.id = cursorMapper.getString("uid");
        this.userId = cursorMapper.getString("user_id");
        this.format = cursorMapper.getString(Columns.FORMAT);
        this.width = cursorMapper.getInt("width");
        this.height = cursorMapper.getInt("height");
        this.description = cursorMapper.getString("description");
        this.flagged = cursorMapper.getInt(Columns.FLAGGED) != 0;
        this.status = cursorMapper.getString(Columns.REMOTE_STATUS);
        this.localFilepath = cursorMapper.getString(Columns.LOCAL_FILEPATH);
        this.checksum = cursorMapper.getString(Columns.CHECKSUM);
        this.createdAt = cursorMapper.getString("created_at");
        this.updatedAt = cursorMapper.getString("updated_at");
    }

    public long getLocalId() {
        return this.localId;
    }

    public void setLocalId(long j) {
        this.localId = j;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getFormat() {
        return this.format;
    }

    public String getDescription() {
        return this.description;
    }

    public String getChecksum() {
        return this.checksum;
    }

    public String getUploadLocation() {
        return this.uploadLocation;
    }

    public Date getUploadLocationTimeout() {
        return this.uploadLocationTimeout;
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

    public boolean isFlagged() {
        return this.flagged;
    }

    public String getStatus() {
        return this.status;
    }

    public String getLocalFilepath() {
        return this.localFilepath;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public static MfpImage fromCursor(Cursor cursor) {
        List listFromCursor = listFromCursor(cursor, 1);
        if (CollectionUtils.isEmpty((Collection<?>) listFromCursor)) {
            return null;
        }
        return (MfpImage) listFromCursor.get(0);
    }

    public static List<MfpImage> listFromCursor(Cursor cursor) {
        return listFromCursor(cursor, -1);
    }

    public static List<MfpImage> listFromCursor(Cursor cursor, int i) {
        ArrayList arrayList = new ArrayList();
        if (cursor == null || i == 0) {
            return arrayList;
        }
        int i2 = 0;
        CursorMapper cursorMapper = new CursorMapper(cursor);
        while (cursor.moveToNext()) {
            try {
                arrayList.add(new MfpImage(cursorMapper));
                if (i > 0) {
                    i2++;
                    if (i2 >= i) {
                        break;
                    }
                }
            } finally {
                cursorMapper.close();
            }
        }
        return arrayList;
    }

    private void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.userId = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.format = parcel.readString();
        this.flagged = ParcelableUtil.readBoolean(parcel);
        this.description = parcel.readString();
        this.checksum = parcel.readString();
        this.uploadLocation = parcel.readString();
        this.uploadLocationTimeout = ParcelableUtil.readDate(parcel);
        this.status = parcel.readString();
        this.createdAt = parcel.readString();
        this.updatedAt = parcel.readString();
        this.localId = parcel.readLong();
        this.localFilepath = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.userId);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeString(this.format);
        ParcelableUtil.writeBoolean(parcel, this.flagged);
        parcel.writeString(this.description);
        parcel.writeString(this.checksum);
        parcel.writeString(this.uploadLocation);
        ParcelableUtil.writeDate(parcel, this.uploadLocationTimeout);
        parcel.writeString(this.status);
        parcel.writeString(this.createdAt);
        parcel.writeString(this.updatedAt);
        parcel.writeLong(this.localId);
        parcel.writeString(this.localFilepath);
    }
}
