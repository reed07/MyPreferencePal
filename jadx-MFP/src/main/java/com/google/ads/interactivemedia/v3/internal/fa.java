package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/* compiled from: IMASDK */
public final class fa implements Parcelable, Comparator<a> {
    public static final Creator<fa> CREATOR = new fb();
    public final String a;
    public final int b;
    private final a[] c;
    private int d;

    /* compiled from: IMASDK */
    public static final class a implements Parcelable {
        public static final Creator<a> CREATOR = new fc();
        public final byte[] a;
        public final boolean b;
        private int c;
        /* access modifiers changed from: private */
        public final UUID d;
        private final String e;
        private final String f;

        public a(UUID uuid, String str, byte[] bArr) {
            this(uuid, str, bArr, false);
        }

        public final int describeContents() {
            return 0;
        }

        private a(UUID uuid, String str, byte[] bArr, boolean z) {
            this(uuid, null, str, bArr, false);
        }

        public a(UUID uuid, String str, String str2, byte[] bArr, boolean z) {
            this.d = (UUID) qi.a(uuid);
            this.e = str;
            this.f = (String) qi.a(str2);
            this.a = bArr;
            this.b = z;
        }

        a(Parcel parcel) {
            this.d = new UUID(parcel.readLong(), parcel.readLong());
            this.e = parcel.readString();
            this.f = (String) vf.a(parcel.readString());
            this.a = parcel.createByteArray();
            this.b = parcel.readByte() != 0;
        }

        public final boolean a(UUID uuid) {
            return at.a.equals(this.d) || uuid.equals(this.d);
        }

        public final boolean a(a aVar) {
            return a() && !aVar.a() && a(aVar.d);
        }

        public final boolean a() {
            return this.a != null;
        }

        public final a a(byte[] bArr) {
            a aVar = new a(this.d, this.e, this.f, null, this.b);
            return aVar;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            a aVar = (a) obj;
            return vf.a((Object) this.e, (Object) aVar.e) && vf.a((Object) this.f, (Object) aVar.f) && vf.a((Object) this.d, (Object) aVar.d) && Arrays.equals(this.a, aVar.a);
        }

        public final int hashCode() {
            if (this.c == 0) {
                int hashCode = this.d.hashCode() * 31;
                String str = this.e;
                this.c = ((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.f.hashCode()) * 31) + Arrays.hashCode(this.a);
            }
            return this.c;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.d.getMostSignificantBits());
            parcel.writeLong(this.d.getLeastSignificantBits());
            parcel.writeString(this.e);
            parcel.writeString(this.f);
            parcel.writeByteArray(this.a);
            parcel.writeByte(this.b ? (byte) 1 : 0);
        }
    }

    public static fa a(fa faVar, fa faVar2) {
        String str;
        a[] aVarArr;
        boolean z;
        a[] aVarArr2;
        ArrayList arrayList = new ArrayList();
        if (faVar != null) {
            str = faVar.a;
            for (a aVar : faVar.c) {
                if (aVar.a()) {
                    arrayList.add(aVar);
                }
            }
        } else {
            str = null;
        }
        if (faVar2 != null) {
            if (str == null) {
                str = faVar2.a;
            }
            int size = arrayList.size();
            for (a aVar2 : faVar2.c) {
                if (aVar2.a()) {
                    UUID b2 = aVar2.d;
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            z = false;
                            break;
                        } else if (((a) arrayList.get(i)).d.equals(b2)) {
                            z = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (!z) {
                        arrayList.add(aVar2);
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new fa(str, (List<a>) arrayList);
    }

    public final int describeContents() {
        return 0;
    }

    public fa(List<a> list) {
        this(null, false, (a[]) list.toArray(new a[0]));
    }

    public fa(String str, List<a> list) {
        this(str, false, (a[]) list.toArray(new a[0]));
    }

    public fa(a... aVarArr) {
        this((String) null, aVarArr);
    }

    public fa(String str, a... aVarArr) {
        this(str, true, aVarArr);
    }

    private fa(String str, boolean z, a... aVarArr) {
        this.a = str;
        if (z) {
            aVarArr = (a[]) aVarArr.clone();
        }
        this.c = aVarArr;
        this.b = aVarArr.length;
        Arrays.sort(this.c, this);
    }

    fa(Parcel parcel) {
        this.a = parcel.readString();
        this.c = (a[]) vf.a((a[]) parcel.createTypedArray(a.CREATOR));
        this.b = this.c.length;
    }

    public final a a(int i) {
        return this.c[i];
    }

    public final fa a(String str) {
        if (vf.a((Object) this.a, (Object) str)) {
            return this;
        }
        return new fa(str, false, this.c);
    }

    public final int hashCode() {
        if (this.d == 0) {
            String str = this.a;
            this.d = ((str == null ? 0 : str.hashCode()) * 31) + Arrays.hashCode(this.c);
        }
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        fa faVar = (fa) obj;
        return vf.a((Object) this.a, (Object) faVar.a) && Arrays.equals(this.c, faVar.c);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeTypedArray(this.c, 0);
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        a aVar = (a) obj;
        a aVar2 = (a) obj2;
        if (at.a.equals(aVar.d)) {
            return at.a.equals(aVar2.d) ? 0 : 1;
        }
        return aVar.d.compareTo(aVar2.d);
    }
}
