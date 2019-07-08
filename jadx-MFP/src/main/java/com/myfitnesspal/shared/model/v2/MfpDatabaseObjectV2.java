package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.ContentValuesMapper.Mappable;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.Strings;
import java.util.UUID;

public abstract class MfpDatabaseObjectV2 implements Parcelable, Mappable {
    @Expose
    private String id;
    private long localId;
    private long masterId;
    private int syncFlags;

    public static final class SyncFlags {
        public static final int CREATE = 1;
        public static final int DELETE = 3;
        public static final int SYNCED = 0;
        public static final int UPDATE = 2;
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract String getSyncResourceName();

    protected MfpDatabaseObjectV2() {
    }

    protected MfpDatabaseObjectV2(CursorMapper cursorMapper) {
        this.localId = cursorMapper.getLong("id");
        this.masterId = cursorMapper.getLong("master_id");
        this.id = cursorMapper.getString("uid");
        this.syncFlags = cursorMapper.getInt(Columns.SYNC_FLAGS);
    }

    protected MfpDatabaseObjectV2(Parcel parcel) {
        this();
        this.localId = parcel.readLong();
        this.masterId = parcel.readLong();
        this.id = parcel.readString();
        this.syncFlags = parcel.readInt();
    }

    public long getLocalId() {
        return this.localId;
    }

    public void setLocalId(long j) {
        this.localId = j;
    }

    public boolean hasLocalId() {
        return this.localId != 0;
    }

    public long getMasterId() {
        return this.masterId;
    }

    public void setMasterId(long j) {
        this.masterId = j;
    }

    public boolean hasMasterId() {
        return this.masterId != 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public boolean hasId() {
        return Strings.notEmpty(this.id);
    }

    public void assignExternalId() {
        setId(generateExternalId());
    }

    public int getSyncFlags() {
        return this.syncFlags;
    }

    public void setSyncFlags(int i) {
        this.syncFlags = i;
    }

    /* access modifiers changed from: protected */
    public String generateExternalId() {
        return Strings.toString(UUID.randomUUID());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.localId);
        parcel.writeLong(this.masterId);
        parcel.writeString(this.id);
        parcel.writeInt(this.syncFlags);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MfpDatabaseObjectV2 mfpDatabaseObjectV2 = (MfpDatabaseObjectV2) obj;
        if (this.localId != mfpDatabaseObjectV2.localId || this.masterId != mfpDatabaseObjectV2.masterId || this.syncFlags != mfpDatabaseObjectV2.syncFlags) {
            return false;
        }
        String str = this.id;
        if (str != null) {
            z = str.equals(mfpDatabaseObjectV2.id);
        } else if (mfpDatabaseObjectV2.id != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long j = this.localId;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        long j2 = this.masterId;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        String str = this.id;
        return ((i2 + (str != null ? str.hashCode() : 0)) * 31) + this.syncFlags;
    }

    public void toContentValues(ContentValuesMapper contentValuesMapper) {
        contentValuesMapper.put("id", Long.valueOf(getLocalId()));
        contentValuesMapper.put("master_id", Long.valueOf(getMasterId()));
        contentValuesMapper.put("uid", getId());
        contentValuesMapper.put(Columns.SYNC_FLAGS, Integer.valueOf(getSyncFlags()));
    }
}
