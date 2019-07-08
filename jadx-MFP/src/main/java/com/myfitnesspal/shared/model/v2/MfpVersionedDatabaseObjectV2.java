package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import com.google.gson.annotations.Expose;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;

public abstract class MfpVersionedDatabaseObjectV2 extends MfpDatabaseObjectV2 {
    protected long originalLocalId;
    protected long originalMasterId;
    protected long ownerUserLocalId;
    protected long ownerUserMasterId;
    @Expose
    private String version;

    /* access modifiers changed from: protected */
    public abstract void readV1Information(CursorMapper cursorMapper);

    /* access modifiers changed from: protected */
    public abstract void writeV1Information(ContentValuesMapper contentValuesMapper);

    public long getOriginalLocalId() {
        return this.originalLocalId;
    }

    public void setOriginalLocalId(long j) {
        this.originalLocalId = j;
    }

    public long getOriginalMasterId() {
        return this.originalMasterId;
    }

    public void setOriginalMasterId(long j) {
        this.originalMasterId = j;
    }

    public long getOwnerUserLocalId() {
        return this.ownerUserLocalId;
    }

    public void setOwnerUserLocalId(long j) {
        this.ownerUserLocalId = j;
    }

    public long getOwnerUserMasterId() {
        return this.ownerUserMasterId;
    }

    public void setOwnerUserMasterId(long j) {
        this.ownerUserMasterId = j;
    }

    protected MfpVersionedDatabaseObjectV2() {
    }

    protected MfpVersionedDatabaseObjectV2(Parcel parcel) {
        super(parcel);
        this.originalLocalId = parcel.readLong();
        this.originalMasterId = parcel.readLong();
        this.ownerUserLocalId = parcel.readLong();
        this.ownerUserMasterId = parcel.readLong();
        setVersion(parcel.readString());
    }

    public MfpVersionedDatabaseObjectV2(CursorMapper cursorMapper) {
        super(cursorMapper);
        readV1Information(cursorMapper);
        setId(cursorMapper.getString("original_uid"));
        setVersion(cursorMapper.getString("uid"));
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.originalLocalId);
        parcel.writeLong(this.originalMasterId);
        parcel.writeLong(this.ownerUserLocalId);
        parcel.writeLong(this.ownerUserMasterId);
        parcel.writeString(this.version);
    }

    public void toContentValues(ContentValuesMapper contentValuesMapper) {
        super.toContentValues(contentValuesMapper);
        writeV1Information(contentValuesMapper);
        contentValuesMapper.put("original_uid", getId()).put("uid", getVersion());
    }

    public String getVersion() {
        return this.version;
    }

    public void setId(String str) {
        super.setId(str);
        ensureVersion();
    }

    public void setVersion(String str) {
        this.version = str;
        ensureVersion();
    }

    public boolean hasVersion() {
        return Strings.notEmpty(this.version);
    }

    private void ensureVersion() {
        if (Strings.isEmpty(this.version)) {
            this.version = getId();
        }
    }

    public void assignExternalVersion() {
        setVersion(generateExternalId());
    }

    public String generateExternalId() {
        return super.generateExternalId();
    }

    public static Tuple2<String, String> getIdAndVersionFromCompoundId(String str) {
        String[] split = Strings.toString(str).split("@");
        return Tuple.create(split[0], split.length > 1 ? split[1] : null);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        MfpVersionedDatabaseObjectV2 mfpVersionedDatabaseObjectV2 = (MfpVersionedDatabaseObjectV2) obj;
        if (this.originalLocalId != mfpVersionedDatabaseObjectV2.originalLocalId || this.originalMasterId != mfpVersionedDatabaseObjectV2.originalMasterId || this.ownerUserLocalId != mfpVersionedDatabaseObjectV2.ownerUserLocalId || this.ownerUserMasterId != mfpVersionedDatabaseObjectV2.ownerUserMasterId) {
            return false;
        }
        String str = this.version;
        if (str != null) {
            z = str.equals(mfpVersionedDatabaseObjectV2.version);
        } else if (mfpVersionedDatabaseObjectV2.version != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.version;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        long j = this.originalLocalId;
        int i = (hashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.originalMasterId;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.ownerUserLocalId;
        int i3 = (i2 + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        long j4 = this.ownerUserMasterId;
        return i3 + ((int) (j4 ^ (j4 >>> 32)));
    }
}
