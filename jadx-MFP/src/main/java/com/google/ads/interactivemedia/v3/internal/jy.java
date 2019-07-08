package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.ads.interactivemedia.v3.internal.js.a;

/* compiled from: IMASDK */
public final class jy implements a {
    public static final Creator<jy> CREATOR = new jz();
    public final int a;
    public final int b;
    private final String c;
    private final String d;
    private final String e;
    private final boolean f;

    public final int describeContents() {
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0115 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.ads.interactivemedia.v3.internal.jy a(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r13) {
        /*
            java.lang.String r0 = "icy-br"
            java.lang.Object r0 = r13.get(r0)
            java.util.List r0 = (java.util.List) r0
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x005c
            java.lang.Object r0 = r0.get(r3)
            java.lang.String r0 = (java.lang.String) r0
            int r4 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x003d }
            int r4 = r4 * 1000
            if (r4 <= 0) goto L_0x001e
            r5 = r4
            r0 = 1
            goto L_0x005e
        L_0x001e:
            java.lang.String r5 = "IcyHeaders"
            java.lang.String r6 = "Invalid bitrate: "
            java.lang.String r7 = java.lang.String.valueOf(r0)     // Catch:{ NumberFormatException -> 0x003e }
            int r8 = r7.length()     // Catch:{ NumberFormatException -> 0x003e }
            if (r8 == 0) goto L_0x0031
            java.lang.String r6 = r6.concat(r7)     // Catch:{ NumberFormatException -> 0x003e }
            goto L_0x0037
        L_0x0031:
            java.lang.String r7 = new java.lang.String     // Catch:{ NumberFormatException -> 0x003e }
            r7.<init>(r6)     // Catch:{ NumberFormatException -> 0x003e }
            r6 = r7
        L_0x0037:
            android.util.Log.w(r5, r6)     // Catch:{ NumberFormatException -> 0x003e }
            r0 = 0
            r5 = -1
            goto L_0x005e
        L_0x003d:
            r4 = -1
        L_0x003e:
            java.lang.String r5 = "IcyHeaders"
            java.lang.String r6 = "Invalid bitrate header: "
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r7 = r0.length()
            if (r7 == 0) goto L_0x0051
            java.lang.String r0 = r6.concat(r0)
            goto L_0x0056
        L_0x0051:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r6)
        L_0x0056:
            android.util.Log.w(r5, r0)
            r5 = r4
            r0 = 0
            goto L_0x005e
        L_0x005c:
            r0 = 0
            r5 = -1
        L_0x005e:
            java.lang.String r4 = "icy-genre"
            java.lang.Object r4 = r13.get(r4)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            if (r4 == 0) goto L_0x0071
            java.lang.Object r0 = r4.get(r3)
            java.lang.String r0 = (java.lang.String) r0
            r4 = 1
            goto L_0x0073
        L_0x0071:
            r4 = r0
            r0 = r6
        L_0x0073:
            java.lang.String r7 = "icy-name"
            java.lang.Object r7 = r13.get(r7)
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L_0x0086
            java.lang.Object r4 = r7.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            r7 = r4
            r4 = 1
            goto L_0x0087
        L_0x0086:
            r7 = r6
        L_0x0087:
            java.lang.String r8 = "icy-url"
            java.lang.Object r8 = r13.get(r8)
            java.util.List r8 = (java.util.List) r8
            if (r8 == 0) goto L_0x009a
            java.lang.Object r4 = r8.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            r8 = r4
            r4 = 1
            goto L_0x009b
        L_0x009a:
            r8 = r6
        L_0x009b:
            java.lang.String r9 = "icy-pub"
            java.lang.Object r9 = r13.get(r9)
            java.util.List r9 = (java.util.List) r9
            if (r9 == 0) goto L_0x00b4
            java.lang.Object r4 = r9.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r9 = "1"
            boolean r4 = r4.equals(r9)
            r9 = r4
            r4 = 1
            goto L_0x00b5
        L_0x00b4:
            r9 = 0
        L_0x00b5:
            java.lang.String r10 = "icy-metaint"
            java.lang.Object r13 = r13.get(r10)
            java.util.List r13 = (java.util.List) r13
            if (r13 == 0) goto L_0x010a
            java.lang.Object r13 = r13.get(r3)
            java.lang.String r13 = (java.lang.String) r13
            int r3 = java.lang.Integer.parseInt(r13)     // Catch:{ NumberFormatException -> 0x00ed }
            if (r3 <= 0) goto L_0x00ce
            r10 = r3
            r4 = 1
            goto L_0x010b
        L_0x00ce:
            java.lang.String r2 = "IcyHeaders"
            java.lang.String r10 = "Invalid metadata interval: "
            java.lang.String r11 = java.lang.String.valueOf(r13)     // Catch:{ NumberFormatException -> 0x00ec }
            int r12 = r11.length()     // Catch:{ NumberFormatException -> 0x00ec }
            if (r12 == 0) goto L_0x00e1
            java.lang.String r10 = r10.concat(r11)     // Catch:{ NumberFormatException -> 0x00ec }
            goto L_0x00e7
        L_0x00e1:
            java.lang.String r11 = new java.lang.String     // Catch:{ NumberFormatException -> 0x00ec }
            r11.<init>(r10)     // Catch:{ NumberFormatException -> 0x00ec }
            r10 = r11
        L_0x00e7:
            android.util.Log.w(r2, r10)     // Catch:{ NumberFormatException -> 0x00ec }
            r10 = -1
            goto L_0x010b
        L_0x00ec:
            r1 = r3
        L_0x00ed:
            java.lang.String r2 = "IcyHeaders"
            java.lang.String r3 = "Invalid metadata interval: "
            java.lang.String r13 = java.lang.String.valueOf(r13)
            int r10 = r13.length()
            if (r10 == 0) goto L_0x0100
            java.lang.String r13 = r3.concat(r13)
            goto L_0x0105
        L_0x0100:
            java.lang.String r13 = new java.lang.String
            r13.<init>(r3)
        L_0x0105:
            android.util.Log.w(r2, r13)
            r10 = r1
            goto L_0x010b
        L_0x010a:
            r10 = -1
        L_0x010b:
            if (r4 == 0) goto L_0x0115
            com.google.ads.interactivemedia.v3.internal.jy r13 = new com.google.ads.interactivemedia.v3.internal.jy
            r4 = r13
            r6 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10)
            return r13
        L_0x0115:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.jy.a(java.util.Map):com.google.ads.interactivemedia.v3.internal.jy");
    }

    private jy(int i, String str, String str2, String str3, boolean z, int i2) {
        qi.b(i2 == -1 || i2 > 0);
        this.a = i;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = z;
        this.b = i2;
    }

    jy(Parcel parcel) {
        this.a = parcel.readInt();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = vf.a(parcel);
        this.b = parcel.readInt();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        jy jyVar = (jy) obj;
        return this.a == jyVar.a && vf.a((Object) this.c, (Object) jyVar.c) && vf.a((Object) this.d, (Object) jyVar.d) && vf.a((Object) this.e, (Object) jyVar.e) && this.f == jyVar.f && this.b == jyVar.b;
    }

    public final int hashCode() {
        int i = (this.a + 527) * 31;
        String str = this.c;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.d;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.e;
        if (str3 != null) {
            i2 = str3.hashCode();
        }
        return ((((hashCode2 + i2) * 31) + (this.f ? 1 : 0)) * 31) + this.b;
    }

    public final String toString() {
        String str = this.d;
        String str2 = this.c;
        int i = this.a;
        int i2 = this.b;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 80 + String.valueOf(str2).length());
        sb.append("IcyHeaders: name=\"");
        sb.append(str);
        sb.append("\", genre=\"");
        sb.append(str2);
        sb.append("\", bitrate=");
        sb.append(i);
        sb.append(", metadataInterval=");
        sb.append(i2);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        vf.a(parcel, this.f);
        parcel.writeInt(this.b);
    }
}
