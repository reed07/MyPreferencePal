package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class zzhp implements Parcelable, Comparator<zza> {
    public static final Creator<zzhp> CREATOR = new zzhq();
    private int zzaac;
    private final zza[] zzagq;
    public final int zzagr;

    public static final class zza implements Parcelable {
        public static final Creator<zza> CREATOR = new zzhr();
        private final byte[] data;
        private final String mimeType;
        /* access modifiers changed from: private */
        public final UUID uuid;
        private int zzaac;
        public final boolean zzags;

        public zza(UUID uuid2, String str, byte[] bArr) {
            this(uuid2, str, bArr, false);
        }

        public final int describeContents() {
            return 0;
        }

        public zza(UUID uuid2, String str, byte[] bArr, boolean z) {
            this.uuid = (UUID) zzpo.checkNotNull(uuid2);
            this.mimeType = (String) zzpo.checkNotNull(str);
            this.data = (byte[]) zzpo.checkNotNull(bArr);
            this.zzags = z;
        }

        zza(Parcel parcel) {
            this.uuid = new UUID(parcel.readLong(), parcel.readLong());
            this.mimeType = parcel.readString();
            this.data = parcel.createByteArray();
            this.zzags = parcel.readByte() != 0;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            zza zza = (zza) obj;
            return this.mimeType.equals(zza.mimeType) && zzqe.zza((Object) this.uuid, (Object) zza.uuid) && Arrays.equals(this.data, zza.data);
        }

        public final int hashCode() {
            if (this.zzaac == 0) {
                this.zzaac = (((this.uuid.hashCode() * 31) + this.mimeType.hashCode()) * 31) + Arrays.hashCode(this.data);
            }
            return this.zzaac;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.uuid.getMostSignificantBits());
            parcel.writeLong(this.uuid.getLeastSignificantBits());
            parcel.writeString(this.mimeType);
            parcel.writeByteArray(this.data);
            parcel.writeByte(this.zzags ? (byte) 1 : 0);
        }
    }

    public zzhp(List<zza> list) {
        this(false, (zza[]) list.toArray(new zza[list.size()]));
    }

    public final int describeContents() {
        return 0;
    }

    public zzhp(zza... zzaArr) {
        this(true, zzaArr);
    }

    private zzhp(boolean z, zza... zzaArr) {
        if (z) {
            zzaArr = (zza[]) zzaArr.clone();
        }
        Arrays.sort(zzaArr, this);
        int i = 1;
        while (i < zzaArr.length) {
            if (!zzaArr[i - 1].uuid.equals(zzaArr[i].uuid)) {
                i++;
            } else {
                String valueOf = String.valueOf(zzaArr[i].uuid);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
                sb.append("Duplicate data for uuid: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        this.zzagq = zzaArr;
        this.zzagr = zzaArr.length;
    }

    zzhp(Parcel parcel) {
        this.zzagq = (zza[]) parcel.createTypedArray(zza.CREATOR);
        this.zzagr = this.zzagq.length;
    }

    public final zza zzu(int i) {
        return this.zzagq[i];
    }

    public final int hashCode() {
        if (this.zzaac == 0) {
            this.zzaac = Arrays.hashCode(this.zzagq);
        }
        return this.zzaac;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zzagq, ((zzhp) obj).zzagq);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.zzagq, 0);
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zza zza2 = (zza) obj;
        zza zza3 = (zza) obj2;
        if (zzfe.zzwm.equals(zza2.uuid)) {
            return zzfe.zzwm.equals(zza3.uuid) ? 0 : 1;
        }
        return zza2.uuid.compareTo(zza3.uuid);
    }
}
