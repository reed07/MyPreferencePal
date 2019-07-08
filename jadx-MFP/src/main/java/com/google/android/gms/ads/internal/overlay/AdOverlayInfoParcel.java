package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.zzaq;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzvt;

@zzark
@Class(creator = "AdOverlayInfoCreator")
@Reserved({1})
public final class AdOverlayInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<AdOverlayInfoParcel> CREATOR = new zzm();
    @Field(id = 11)
    public final int orientation;
    @Field(id = 13)
    public final String url;
    @Field(id = 7)
    public final String zzbde;
    @Field(id = 14)
    public final zzbbi zzbsp;
    @Field(id = 2)
    public final zzc zzdrs;
    @Field(getter = "getAdClickListenerAsBinder", id = 3, type = "android.os.IBinder")
    public final zzvt zzdrt;
    @Field(getter = "getAdOverlayListenerAsBinder", id = 4, type = "android.os.IBinder")
    public final zzn zzdru;
    @Field(getter = "getAdWebViewAsBinder", id = 5, type = "android.os.IBinder")
    public final zzbgg zzdrv;
    @Field(getter = "getAppEventGmsgListenerAsBinder", id = 6, type = "android.os.IBinder")
    public final zzd zzdrw;
    @Field(id = 8)
    public final boolean zzdrx;
    @Field(id = 9)
    public final String zzdry;
    @Field(getter = "getLeaveApplicationListenerAsBinder", id = 10, type = "android.os.IBinder")
    public final zzt zzdrz;
    @Field(id = 12)
    public final int zzdsa;
    @Field(id = 16)
    public final String zzdsb;
    @Field(id = 17)
    public final zzaq zzdsc;
    @Field(getter = "getAdMetadataGmsgListenerAsBinder", id = 18, type = "android.os.IBinder")
    public final zzb zzdsd;

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static AdOverlayInfoParcel zzc(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception unused) {
            return null;
        }
    }

    public AdOverlayInfoParcel(zzvt zzvt, zzn zzn, zzt zzt, zzbgg zzbgg, int i, zzbbi zzbbi, String str, zzaq zzaq) {
        this.zzdrs = null;
        this.zzdrt = zzvt;
        this.zzdru = zzn;
        this.zzdrv = zzbgg;
        this.zzdsd = null;
        this.zzdrw = null;
        this.zzbde = null;
        this.zzdrx = false;
        this.zzdry = null;
        this.zzdrz = zzt;
        this.orientation = i;
        this.zzdsa = 1;
        this.url = null;
        this.zzbsp = zzbbi;
        this.zzdsb = str;
        this.zzdsc = zzaq;
    }

    public AdOverlayInfoParcel(zzvt zzvt, zzn zzn, zzt zzt, zzbgg zzbgg, boolean z, int i, zzbbi zzbbi) {
        this.zzdrs = null;
        this.zzdrt = zzvt;
        this.zzdru = zzn;
        this.zzdrv = zzbgg;
        this.zzdsd = null;
        this.zzdrw = null;
        this.zzbde = null;
        this.zzdrx = z;
        this.zzdry = null;
        this.zzdrz = zzt;
        this.orientation = i;
        this.zzdsa = 2;
        this.url = null;
        this.zzbsp = zzbbi;
        this.zzdsb = null;
        this.zzdsc = null;
    }

    public AdOverlayInfoParcel(zzvt zzvt, zzn zzn, zzb zzb, zzd zzd, zzt zzt, zzbgg zzbgg, boolean z, int i, String str, zzbbi zzbbi) {
        this.zzdrs = null;
        this.zzdrt = zzvt;
        this.zzdru = zzn;
        this.zzdrv = zzbgg;
        this.zzdsd = zzb;
        this.zzdrw = zzd;
        this.zzbde = null;
        this.zzdrx = z;
        this.zzdry = null;
        this.zzdrz = zzt;
        this.orientation = i;
        this.zzdsa = 3;
        this.url = str;
        this.zzbsp = zzbbi;
        this.zzdsb = null;
        this.zzdsc = null;
    }

    public AdOverlayInfoParcel(zzvt zzvt, zzn zzn, zzb zzb, zzd zzd, zzt zzt, zzbgg zzbgg, boolean z, int i, String str, String str2, zzbbi zzbbi) {
        this.zzdrs = null;
        this.zzdrt = zzvt;
        this.zzdru = zzn;
        this.zzdrv = zzbgg;
        this.zzdsd = zzb;
        this.zzdrw = zzd;
        this.zzbde = str2;
        this.zzdrx = z;
        this.zzdry = str;
        this.zzdrz = zzt;
        this.orientation = i;
        this.zzdsa = 3;
        this.url = null;
        this.zzbsp = zzbbi;
        this.zzdsb = null;
        this.zzdsc = null;
    }

    public AdOverlayInfoParcel(zzc zzc, zzvt zzvt, zzn zzn, zzt zzt, zzbbi zzbbi) {
        this.zzdrs = zzc;
        this.zzdrt = zzvt;
        this.zzdru = zzn;
        this.zzdrv = null;
        this.zzdsd = null;
        this.zzdrw = null;
        this.zzbde = null;
        this.zzdrx = false;
        this.zzdry = null;
        this.zzdrz = zzt;
        this.orientation = -1;
        this.zzdsa = 4;
        this.url = null;
        this.zzbsp = zzbbi;
        this.zzdsb = null;
        this.zzdsc = null;
    }

    @Constructor
    AdOverlayInfoParcel(@Param(id = 2) zzc zzc, @Param(id = 3) IBinder iBinder, @Param(id = 4) IBinder iBinder2, @Param(id = 5) IBinder iBinder3, @Param(id = 6) IBinder iBinder4, @Param(id = 7) String str, @Param(id = 8) boolean z, @Param(id = 9) String str2, @Param(id = 10) IBinder iBinder5, @Param(id = 11) int i, @Param(id = 12) int i2, @Param(id = 13) String str3, @Param(id = 14) zzbbi zzbbi, @Param(id = 16) String str4, @Param(id = 17) zzaq zzaq, @Param(id = 18) IBinder iBinder6) {
        this.zzdrs = zzc;
        this.zzdrt = (zzvt) ObjectWrapper.unwrap(Stub.asInterface(iBinder));
        this.zzdru = (zzn) ObjectWrapper.unwrap(Stub.asInterface(iBinder2));
        this.zzdrv = (zzbgg) ObjectWrapper.unwrap(Stub.asInterface(iBinder3));
        this.zzdsd = (zzb) ObjectWrapper.unwrap(Stub.asInterface(iBinder6));
        this.zzdrw = (zzd) ObjectWrapper.unwrap(Stub.asInterface(iBinder4));
        this.zzbde = str;
        this.zzdrx = z;
        this.zzdry = str2;
        this.zzdrz = (zzt) ObjectWrapper.unwrap(Stub.asInterface(iBinder5));
        this.orientation = i;
        this.zzdsa = i2;
        this.url = str3;
        this.zzbsp = zzbbi;
        this.zzdsb = str4;
        this.zzdsc = zzaq;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdrs, i, false);
        SafeParcelWriter.writeIBinder(parcel, 3, ObjectWrapper.wrap(this.zzdrt).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 4, ObjectWrapper.wrap(this.zzdru).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 5, ObjectWrapper.wrap(this.zzdrv).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 6, ObjectWrapper.wrap(this.zzdrw).asBinder(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbde, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdrx);
        SafeParcelWriter.writeString(parcel, 9, this.zzdry, false);
        SafeParcelWriter.writeIBinder(parcel, 10, ObjectWrapper.wrap(this.zzdrz).asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 11, this.orientation);
        SafeParcelWriter.writeInt(parcel, 12, this.zzdsa);
        SafeParcelWriter.writeString(parcel, 13, this.url, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzbsp, i, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzdsb, false);
        SafeParcelWriter.writeParcelable(parcel, 17, this.zzdsc, i, false);
        SafeParcelWriter.writeIBinder(parcel, 18, ObjectWrapper.wrap(this.zzdsd).asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
