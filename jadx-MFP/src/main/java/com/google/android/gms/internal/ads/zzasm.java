package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@Class(creator = "AdResponseParcelCreator")
@ParametersAreNonnullByDefault
public final class zzasm extends AbstractSafeParcelable {
    public static final Creator<zzasm> CREATOR = new zzasn();
    @Field(id = 5)
    public final int errorCode;
    @Field(id = 12)
    public final int orientation;
    @Field(id = 1)
    private final int versionCode;
    @Field(id = 2)
    public final String zzbde;
    @Field(id = 49)
    public final boolean zzbph;
    @Field(id = 53)
    public final boolean zzbpi;
    @Field(id = 30)
    public final String zzcgx;
    @Field(id = 23)
    public final boolean zzckn;
    @Field(id = 31)
    public final boolean zzcko;
    @Field(id = 32)
    public final boolean zzckp;
    @Field(id = 4)
    public final List<String> zzdlq;
    @Field(id = 6)
    public final List<String> zzdlr;
    @Field(id = 52)
    public final List<String> zzdls;
    @Field(id = 40)
    public final List<String> zzdlu;
    @Field(id = 42)
    public final boolean zzdlv;
    @Field(id = 11)
    public final long zzdlx;
    private zzasi zzdnh;
    @Field(id = 24)
    public final boolean zzdwn;
    @Field(id = 38)
    public final boolean zzdxb;
    @Nullable
    @Field(id = 39)
    public String zzdxc;
    @Field(id = 47)
    public final boolean zzdxn;
    @Field(id = 3)
    public String zzdyb;
    @Field(id = 7)
    public final long zzdyc;
    @Field(id = 8)
    public final boolean zzdyd;
    @Field(id = 9)
    public final long zzdye;
    @Field(id = 10)
    public final List<String> zzdyf;
    @Field(id = 13)
    public final String zzdyg;
    @Field(id = 14)
    public final long zzdyh;
    @Field(id = 15)
    public final String zzdyi;
    @Field(id = 18)
    public final boolean zzdyj;
    @Field(id = 19)
    public final String zzdyk;
    @Field(id = 21)
    public final String zzdyl;
    @Field(id = 22)
    public final boolean zzdym;
    @Field(id = 25)
    public final boolean zzdyn;
    @Field(id = 26)
    public final boolean zzdyo;
    @Field(id = 28)
    private zzasy zzdyp;
    @Field(id = 29)
    public String zzdyq;
    @Nullable
    @Field(id = 33)
    public final zzawd zzdyr;
    @Nullable
    @Field(id = 34)
    public final List<String> zzdys;
    @Nullable
    @Field(id = 35)
    public final List<String> zzdyt;
    @Field(id = 36)
    public final boolean zzdyu;
    @Nullable
    @Field(id = 37)
    public final zzaso zzdyv;
    @Nullable
    @Field(id = 43)
    public final String zzdyw;
    @Nullable
    @Field(id = 44)
    public final zzawo zzdyx;
    @Nullable
    @Field(id = 45)
    public final String zzdyy;
    @Field(id = 46)
    public final boolean zzdyz;
    @Field(id = 48)
    private Bundle zzdza;
    @Field(id = 50)
    public final int zzdzb;
    @Field(id = 51)
    public final boolean zzdzc;
    @Nullable
    @Field(id = 54)
    public final String zzdzd;
    @Nullable
    @Field(id = 55)
    public String zzdze;
    @Field(id = 56)
    public boolean zzdzf;

    public zzasm(zzasi zzasi, String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, boolean z2, String str5, String str6, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str7, boolean z8, boolean z9, zzawd zzawd, List<String> list4, List<String> list5, boolean z10, zzaso zzaso, boolean z11, String str8, List<String> list6, boolean z12, String str9, zzawo zzawo, String str10, boolean z13, boolean z14, boolean z15, int i2, boolean z16, List<String> list7, boolean z17, String str11, @Nullable String str12, boolean z18) {
        this(19, str, str2, list, -2, list2, j, z, j2, list3, j3, i, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6, z7, null, null, str7, z8, z9, zzawd, list4, list5, z10, zzaso, z11, str8, list6, z12, str9, zzawo, str10, z13, z14, null, z15, 0, z16, list7, z17, str11, str12, z18);
        this.zzdnh = zzasi;
    }

    public zzasm(zzasi zzasi, String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, String str5, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str6, boolean z7, boolean z8, zzawd zzawd, List<String> list4, List<String> list5, boolean z9, zzaso zzaso, boolean z10, String str7, List<String> list6, boolean z11, String str8, zzawo zzawo, String str9, boolean z12, boolean z13, boolean z14, int i2, boolean z15, List<String> list7, boolean z16, String str10, @Nullable String str11, boolean z17) {
        this(19, str, str2, list, -2, list2, j, z, -1, list3, j3, i, str3, j4, str4, false, null, str5, z2, z3, z4, z5, false, null, null, str6, z7, z8, zzawd, list4, list5, z9, zzaso, z10, str7, list6, z11, str8, zzawo, str9, z12, z13, null, z14, i2, z15, list7, z16, str10, str11, z17);
        this.zzdnh = zzasi;
    }

    public zzasm(int i) {
        this(19, null, null, null, i, null, -1, false, -1, null, -1, -1, null, -1, null, false, null, null, false, false, false, true, false, null, null, null, false, false, null, null, null, false, null, false, null, null, false, null, null, null, true, false, null, false, 0, false, null, false, null, null, false);
    }

    public zzasm(int i, long j) {
        this(19, null, null, null, i, null, -1, false, -1, null, j, -1, null, -1, null, false, null, null, false, false, false, true, false, null, null, null, false, false, null, null, null, false, null, false, null, null, false, null, null, null, true, false, null, false, 0, false, null, false, null, null, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00f1  */
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzasm(@com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 1) int r5, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 2) java.lang.String r6, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 3) java.lang.String r7, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 4) java.util.List<java.lang.String> r8, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 5) int r9, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 6) java.util.List<java.lang.String> r10, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 7) long r11, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 8) boolean r13, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 9) long r14, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 10) java.util.List<java.lang.String> r16, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 11) long r17, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 12) int r19, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 13) java.lang.String r20, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 14) long r21, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 15) java.lang.String r23, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 18) boolean r24, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 19) java.lang.String r25, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 21) java.lang.String r26, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 22) boolean r27, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 23) boolean r28, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 24) boolean r29, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 25) boolean r30, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 26) boolean r31, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 28) com.google.android.gms.internal.ads.zzasy r32, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 29) java.lang.String r33, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 30) java.lang.String r34, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 31) boolean r35, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 32) boolean r36, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 33) com.google.android.gms.internal.ads.zzawd r37, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 34) java.util.List<java.lang.String> r38, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 35) java.util.List<java.lang.String> r39, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 36) boolean r40, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 37) com.google.android.gms.internal.ads.zzaso r41, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 38) boolean r42, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 39) java.lang.String r43, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 40) java.util.List<java.lang.String> r44, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 42) boolean r45, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 43) java.lang.String r46, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 44) com.google.android.gms.internal.ads.zzawo r47, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 45) java.lang.String r48, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 46) boolean r49, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 47) boolean r50, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 48) android.os.Bundle r51, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 49) boolean r52, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 50) int r53, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 51) boolean r54, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 52) java.util.List<java.lang.String> r55, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 53) boolean r56, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 54) java.lang.String r57, @android.support.annotation.Nullable @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 55) java.lang.String r58, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 56) boolean r59) {
        /*
            r4 = this;
            r0 = r4
            r4.<init>()
            r1 = r5
            r0.versionCode = r1
            r1 = r6
            r0.zzbde = r1
            r1 = r7
            r0.zzdyb = r1
            r1 = 0
            if (r8 == 0) goto L_0x0015
            java.util.List r2 = java.util.Collections.unmodifiableList(r8)
            goto L_0x0016
        L_0x0015:
            r2 = r1
        L_0x0016:
            r0.zzdlq = r2
            r2 = r9
            r0.errorCode = r2
            if (r10 == 0) goto L_0x0022
            java.util.List r2 = java.util.Collections.unmodifiableList(r10)
            goto L_0x0023
        L_0x0022:
            r2 = r1
        L_0x0023:
            r0.zzdlr = r2
            r2 = r11
            r0.zzdyc = r2
            r2 = r13
            r0.zzdyd = r2
            r2 = r14
            r0.zzdye = r2
            if (r16 == 0) goto L_0x0035
            java.util.List r2 = java.util.Collections.unmodifiableList(r16)
            goto L_0x0036
        L_0x0035:
            r2 = r1
        L_0x0036:
            r0.zzdyf = r2
            r2 = r17
            r0.zzdlx = r2
            r2 = r19
            r0.orientation = r2
            r2 = r20
            r0.zzdyg = r2
            r2 = r21
            r0.zzdyh = r2
            r2 = r23
            r0.zzdyi = r2
            r2 = r24
            r0.zzdyj = r2
            r2 = r25
            r0.zzdyk = r2
            r2 = r26
            r0.zzdyl = r2
            r2 = r27
            r0.zzdym = r2
            r2 = r28
            r0.zzckn = r2
            r2 = r29
            r0.zzdwn = r2
            r2 = r30
            r0.zzdyn = r2
            r2 = r49
            r0.zzdyz = r2
            r2 = r31
            r0.zzdyo = r2
            r2 = r32
            r0.zzdyp = r2
            r2 = r33
            r0.zzdyq = r2
            r2 = r34
            r0.zzcgx = r2
            java.lang.String r2 = r0.zzdyb
            if (r2 != 0) goto L_0x00a3
            com.google.android.gms.internal.ads.zzasy r2 = r0.zzdyp
            if (r2 == 0) goto L_0x00a3
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzatm> r3 = com.google.android.gms.internal.ads.zzatm.CREATOR
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r2 = r2.zza(r3)
            com.google.android.gms.internal.ads.zzatm r2 = (com.google.android.gms.internal.ads.zzatm) r2
            if (r2 == 0) goto L_0x00a0
            java.lang.String r3 = r2.zzczq
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x009d
            java.lang.String r2 = r2.zzczq
            r0.zzdyb = r2
            r2 = r35
            goto L_0x00a5
        L_0x009d:
            r2 = r35
            goto L_0x00a5
        L_0x00a0:
            r2 = r35
            goto L_0x00a5
        L_0x00a3:
            r2 = r35
        L_0x00a5:
            r0.zzcko = r2
            r2 = r36
            r0.zzckp = r2
            r2 = r37
            r0.zzdyr = r2
            r2 = r38
            r0.zzdys = r2
            r2 = r39
            r0.zzdyt = r2
            r2 = r40
            r0.zzdyu = r2
            r2 = r41
            r0.zzdyv = r2
            r2 = r42
            r0.zzdxb = r2
            r2 = r43
            r0.zzdxc = r2
            r2 = r44
            r0.zzdlu = r2
            r2 = r45
            r0.zzdlv = r2
            r2 = r46
            r0.zzdyw = r2
            r2 = r47
            r0.zzdyx = r2
            r2 = r48
            r0.zzdyy = r2
            r2 = r50
            r0.zzdxn = r2
            r2 = r51
            r0.zzdza = r2
            r2 = r52
            r0.zzbph = r2
            r2 = r53
            r0.zzdzb = r2
            r2 = r54
            r0.zzdzc = r2
            if (r55 == 0) goto L_0x00f5
            java.util.List r1 = java.util.Collections.unmodifiableList(r55)
        L_0x00f5:
            r0.zzdls = r1
            r1 = r56
            r0.zzbpi = r1
            r1 = r57
            r0.zzdzd = r1
            r1 = r58
            r0.zzdze = r1
            r1 = r59
            r0.zzdzf = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzasm.<init>(int, java.lang.String, java.lang.String, java.util.List, int, java.util.List, long, boolean, long, java.util.List, long, int, java.lang.String, long, java.lang.String, boolean, java.lang.String, java.lang.String, boolean, boolean, boolean, boolean, boolean, com.google.android.gms.internal.ads.zzasy, java.lang.String, java.lang.String, boolean, boolean, com.google.android.gms.internal.ads.zzawd, java.util.List, java.util.List, boolean, com.google.android.gms.internal.ads.zzaso, boolean, java.lang.String, java.util.List, boolean, java.lang.String, com.google.android.gms.internal.ads.zzawo, java.lang.String, boolean, boolean, android.os.Bundle, boolean, int, boolean, java.util.List, boolean, java.lang.String, java.lang.String, boolean):void");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzasi zzasi = this.zzdnh;
        if (zzasi != null && zzasi.versionCode >= 9 && !TextUtils.isEmpty(this.zzdyb)) {
            this.zzdyp = new zzasy((SafeParcelable) new zzatm(this.zzdyb));
            this.zzdyb = null;
        }
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.zzbde, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzdyb, false);
        SafeParcelWriter.writeStringList(parcel, 4, this.zzdlq, false);
        SafeParcelWriter.writeInt(parcel, 5, this.errorCode);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzdlr, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzdyc);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdyd);
        SafeParcelWriter.writeLong(parcel, 9, this.zzdye);
        SafeParcelWriter.writeStringList(parcel, 10, this.zzdyf, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzdlx);
        SafeParcelWriter.writeInt(parcel, 12, this.orientation);
        SafeParcelWriter.writeString(parcel, 13, this.zzdyg, false);
        SafeParcelWriter.writeLong(parcel, 14, this.zzdyh);
        SafeParcelWriter.writeString(parcel, 15, this.zzdyi, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzdyj);
        SafeParcelWriter.writeString(parcel, 19, this.zzdyk, false);
        SafeParcelWriter.writeString(parcel, 21, this.zzdyl, false);
        SafeParcelWriter.writeBoolean(parcel, 22, this.zzdym);
        SafeParcelWriter.writeBoolean(parcel, 23, this.zzckn);
        SafeParcelWriter.writeBoolean(parcel, 24, this.zzdwn);
        SafeParcelWriter.writeBoolean(parcel, 25, this.zzdyn);
        SafeParcelWriter.writeBoolean(parcel, 26, this.zzdyo);
        SafeParcelWriter.writeParcelable(parcel, 28, this.zzdyp, i, false);
        SafeParcelWriter.writeString(parcel, 29, this.zzdyq, false);
        SafeParcelWriter.writeString(parcel, 30, this.zzcgx, false);
        SafeParcelWriter.writeBoolean(parcel, 31, this.zzcko);
        SafeParcelWriter.writeBoolean(parcel, 32, this.zzckp);
        SafeParcelWriter.writeParcelable(parcel, 33, this.zzdyr, i, false);
        SafeParcelWriter.writeStringList(parcel, 34, this.zzdys, false);
        SafeParcelWriter.writeStringList(parcel, 35, this.zzdyt, false);
        SafeParcelWriter.writeBoolean(parcel, 36, this.zzdyu);
        SafeParcelWriter.writeParcelable(parcel, 37, this.zzdyv, i, false);
        SafeParcelWriter.writeBoolean(parcel, 38, this.zzdxb);
        SafeParcelWriter.writeString(parcel, 39, this.zzdxc, false);
        SafeParcelWriter.writeStringList(parcel, 40, this.zzdlu, false);
        SafeParcelWriter.writeBoolean(parcel, 42, this.zzdlv);
        SafeParcelWriter.writeString(parcel, 43, this.zzdyw, false);
        SafeParcelWriter.writeParcelable(parcel, 44, this.zzdyx, i, false);
        SafeParcelWriter.writeString(parcel, 45, this.zzdyy, false);
        SafeParcelWriter.writeBoolean(parcel, 46, this.zzdyz);
        SafeParcelWriter.writeBoolean(parcel, 47, this.zzdxn);
        SafeParcelWriter.writeBundle(parcel, 48, this.zzdza, false);
        SafeParcelWriter.writeBoolean(parcel, 49, this.zzbph);
        SafeParcelWriter.writeInt(parcel, 50, this.zzdzb);
        SafeParcelWriter.writeBoolean(parcel, 51, this.zzdzc);
        SafeParcelWriter.writeStringList(parcel, 52, this.zzdls, false);
        SafeParcelWriter.writeBoolean(parcel, 53, this.zzbpi);
        SafeParcelWriter.writeString(parcel, 54, this.zzdzd, false);
        SafeParcelWriter.writeString(parcel, 55, this.zzdze, false);
        SafeParcelWriter.writeBoolean(parcel, 56, this.zzdzf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
