package com.google.android.exoplayer2.drm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class DrmInitData implements Parcelable, Comparator<SchemeData> {
    public static final Creator<DrmInitData> CREATOR = new Creator<DrmInitData>() {
        public DrmInitData createFromParcel(Parcel parcel) {
            return new DrmInitData(parcel);
        }

        public DrmInitData[] newArray(int i) {
            return new DrmInitData[i];
        }
    };
    private int hashCode;
    public final int schemeDataCount;
    private final SchemeData[] schemeDatas;
    @Nullable
    public final String schemeType;

    public static final class SchemeData implements Parcelable {
        public static final Creator<SchemeData> CREATOR = new Creator<SchemeData>() {
            public SchemeData createFromParcel(Parcel parcel) {
                return new SchemeData(parcel);
            }

            public SchemeData[] newArray(int i) {
                return new SchemeData[i];
            }
        };
        public final byte[] data;
        private int hashCode;
        @Nullable
        public final String licenseServerUrl;
        public final String mimeType;
        public final boolean requiresSecureDecryption;
        /* access modifiers changed from: private */
        public final UUID uuid;

        public int describeContents() {
            return 0;
        }

        public SchemeData(UUID uuid2, String str, byte[] bArr) {
            this(uuid2, str, bArr, false);
        }

        public SchemeData(UUID uuid2, String str, byte[] bArr, boolean z) {
            this(uuid2, null, str, bArr, z);
        }

        public SchemeData(UUID uuid2, @Nullable String str, String str2, byte[] bArr, boolean z) {
            this.uuid = (UUID) Assertions.checkNotNull(uuid2);
            this.licenseServerUrl = str;
            this.mimeType = (String) Assertions.checkNotNull(str2);
            this.data = bArr;
            this.requiresSecureDecryption = z;
        }

        SchemeData(Parcel parcel) {
            this.uuid = new UUID(parcel.readLong(), parcel.readLong());
            this.licenseServerUrl = parcel.readString();
            this.mimeType = parcel.readString();
            this.data = parcel.createByteArray();
            this.requiresSecureDecryption = parcel.readByte() != 0;
        }

        public boolean matches(UUID uuid2) {
            return C.UUID_NIL.equals(this.uuid) || uuid2.equals(this.uuid);
        }

        public boolean canReplace(SchemeData schemeData) {
            return hasData() && !schemeData.hasData() && matches(schemeData.uuid);
        }

        public boolean hasData() {
            return this.data != null;
        }

        public SchemeData copyWithData(@Nullable byte[] bArr) {
            SchemeData schemeData = new SchemeData(this.uuid, this.licenseServerUrl, this.mimeType, bArr, this.requiresSecureDecryption);
            return schemeData;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof SchemeData)) {
                return false;
            }
            boolean z = true;
            if (obj == this) {
                return true;
            }
            SchemeData schemeData = (SchemeData) obj;
            if (!Util.areEqual(this.licenseServerUrl, schemeData.licenseServerUrl) || !Util.areEqual(this.mimeType, schemeData.mimeType) || !Util.areEqual(this.uuid, schemeData.uuid) || !Arrays.equals(this.data, schemeData.data)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                int hashCode2 = this.uuid.hashCode() * 31;
                String str = this.licenseServerUrl;
                this.hashCode = ((((hashCode2 + (str == null ? 0 : str.hashCode())) * 31) + this.mimeType.hashCode()) * 31) + Arrays.hashCode(this.data);
            }
            return this.hashCode;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.uuid.getMostSignificantBits());
            parcel.writeLong(this.uuid.getLeastSignificantBits());
            parcel.writeString(this.licenseServerUrl);
            parcel.writeString(this.mimeType);
            parcel.writeByteArray(this.data);
            parcel.writeByte(this.requiresSecureDecryption ? (byte) 1 : 0);
        }
    }

    public int describeContents() {
        return 0;
    }

    @Nullable
    public static DrmInitData createSessionCreationData(@Nullable DrmInitData drmInitData, @Nullable DrmInitData drmInitData2) {
        String str;
        SchemeData[] schemeDataArr;
        SchemeData[] schemeDataArr2;
        ArrayList arrayList = new ArrayList();
        if (drmInitData != null) {
            str = drmInitData.schemeType;
            for (SchemeData schemeData : drmInitData.schemeDatas) {
                if (schemeData.hasData()) {
                    arrayList.add(schemeData);
                }
            }
        } else {
            str = null;
        }
        if (drmInitData2 != null) {
            if (str == null) {
                str = drmInitData2.schemeType;
            }
            int size = arrayList.size();
            for (SchemeData schemeData2 : drmInitData2.schemeDatas) {
                if (schemeData2.hasData() && !containsSchemeDataWithUuid(arrayList, size, schemeData2.uuid)) {
                    arrayList.add(schemeData2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new DrmInitData(str, (List<SchemeData>) arrayList);
    }

    public DrmInitData(List<SchemeData> list) {
        this(null, false, (SchemeData[]) list.toArray(new SchemeData[list.size()]));
    }

    public DrmInitData(String str, List<SchemeData> list) {
        this(str, false, (SchemeData[]) list.toArray(new SchemeData[list.size()]));
    }

    public DrmInitData(SchemeData... schemeDataArr) {
        this((String) null, schemeDataArr);
    }

    public DrmInitData(@Nullable String str, SchemeData... schemeDataArr) {
        this(str, true, schemeDataArr);
    }

    private DrmInitData(@Nullable String str, boolean z, SchemeData... schemeDataArr) {
        this.schemeType = str;
        if (z) {
            schemeDataArr = (SchemeData[]) schemeDataArr.clone();
        }
        Arrays.sort(schemeDataArr, this);
        this.schemeDatas = schemeDataArr;
        this.schemeDataCount = schemeDataArr.length;
    }

    DrmInitData(Parcel parcel) {
        this.schemeType = parcel.readString();
        this.schemeDatas = (SchemeData[]) parcel.createTypedArray(SchemeData.CREATOR);
        this.schemeDataCount = this.schemeDatas.length;
    }

    @Deprecated
    public SchemeData get(UUID uuid) {
        SchemeData[] schemeDataArr;
        for (SchemeData schemeData : this.schemeDatas) {
            if (schemeData.matches(uuid)) {
                return schemeData;
            }
        }
        return null;
    }

    public SchemeData get(int i) {
        return this.schemeDatas[i];
    }

    public DrmInitData copyWithSchemeType(@Nullable String str) {
        if (Util.areEqual(this.schemeType, str)) {
            return this;
        }
        return new DrmInitData(str, false, this.schemeDatas);
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.schemeType;
            this.hashCode = ((str == null ? 0 : str.hashCode()) * 31) + Arrays.hashCode(this.schemeDatas);
        }
        return this.hashCode;
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DrmInitData drmInitData = (DrmInitData) obj;
        if (!Util.areEqual(this.schemeType, drmInitData.schemeType) || !Arrays.equals(this.schemeDatas, drmInitData.schemeDatas)) {
            z = false;
        }
        return z;
    }

    public int compare(SchemeData schemeData, SchemeData schemeData2) {
        if (C.UUID_NIL.equals(schemeData.uuid)) {
            return C.UUID_NIL.equals(schemeData2.uuid) ? 0 : 1;
        }
        return schemeData.uuid.compareTo(schemeData2.uuid);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.schemeType);
        parcel.writeTypedArray(this.schemeDatas, 0);
    }

    private static boolean containsSchemeDataWithUuid(ArrayList<SchemeData> arrayList, int i, UUID uuid) {
        for (int i2 = 0; i2 < i; i2++) {
            if (((SchemeData) arrayList.get(i2)).uuid.equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}
