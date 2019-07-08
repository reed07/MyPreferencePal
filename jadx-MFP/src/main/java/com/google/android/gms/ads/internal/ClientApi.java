package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.overlay.zzr;
import com.google.android.gms.ads.internal.overlay.zzs;
import com.google.android.gms.ads.internal.overlay.zzx;
import com.google.android.gms.ads.internal.overlay.zzy;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzacr;
import com.google.android.gms.internal.ads.zzact;
import com.google.android.gms.internal.ads.zzadf;
import com.google.android.gms.internal.ads.zzadk;
import com.google.android.gms.internal.ads.zzalg;
import com.google.android.gms.internal.ads.zzaop;
import com.google.android.gms.internal.ads.zzaoz;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaun;
import com.google.android.gms.internal.ads.zzauw;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzxg;
import com.google.android.gms.internal.ads.zzxl;
import com.google.android.gms.internal.ads.zzxx;
import com.google.android.gms.internal.ads.zzyc;
import java.util.HashMap;
import javax.annotation.ParametersAreNonnullByDefault;

@Keep
@zzark
@KeepForSdkWithMembers
@DynamiteApi
@RetainForClient
@ParametersAreNonnullByDefault
public class ClientApi extends zzxx {
    public zzaoz createInAppPurchaseManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    @Nullable
    public zzauw createRewardedVideoAdSku(IObjectWrapper iObjectWrapper, int i) {
        return null;
    }

    @Nullable
    public zzyc getMobileAdsSettingsManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    public zzxl createBannerAdManager(IObjectWrapper iObjectWrapper, zzwf zzwf, String str, zzalg zzalg, int i) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzlf();
        zzx zzx = new zzx(context, zzwf, str, zzalg, new zzbbi(14300000, i, true, zzayh.zzav(context)), zzv.zzd(context));
        return zzx;
    }

    public zzxl createSearchAdManager(IObjectWrapper iObjectWrapper, zzwf zzwf, String str, int i) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzlf();
        return new zzbp(context, zzwf, str, new zzbbi(14300000, i, true, zzayh.zzav(context)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0034, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcrz)).booleanValue() == false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0048, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.ads.zzwu.zzpz().zzd(com.google.android.gms.internal.ads.zzaan.zzcsa)).booleanValue() != false) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.ads.zzxl createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper r8, com.google.android.gms.internal.ads.zzwf r9, java.lang.String r10, com.google.android.gms.internal.ads.zzalg r11, int r12) throws android.os.RemoteException {
        /*
            r7 = this;
            java.lang.Object r8 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r8)
            r1 = r8
            android.content.Context r1 = (android.content.Context) r1
            com.google.android.gms.internal.ads.zzaan.initialize(r1)
            com.google.android.gms.internal.ads.zzbbi r5 = new com.google.android.gms.internal.ads.zzbbi
            com.google.android.gms.ads.internal.zzbv.zzlf()
            boolean r8 = com.google.android.gms.internal.ads.zzayh.zzav(r1)
            r0 = 1
            r2 = 14300000(0xda3360, float:2.0038568E-38)
            r5.<init>(r2, r12, r0, r8)
            java.lang.String r8 = "reward_mb"
            java.lang.String r12 = r9.zzckk
            boolean r8 = r8.equals(r12)
            if (r8 != 0) goto L_0x0036
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r12 = com.google.android.gms.internal.ads.zzaan.zzcrz
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r12 = r2.zzd(r12)
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 != 0) goto L_0x004c
        L_0x0036:
            if (r8 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r8 = com.google.android.gms.internal.ads.zzaan.zzcsa
            com.google.android.gms.internal.ads.zzaak r12 = com.google.android.gms.internal.ads.zzwu.zzpz()
            java.lang.Object r8 = r12.zzd(r8)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x004b
            goto L_0x004c
        L_0x004b:
            r0 = 0
        L_0x004c:
            if (r0 == 0) goto L_0x005d
            com.google.android.gms.internal.ads.zzahr r8 = new com.google.android.gms.internal.ads.zzahr
            com.google.android.gms.ads.internal.zzv r9 = com.google.android.gms.ads.internal.zzv.zzd(r1)
            r0 = r8
            r2 = r10
            r3 = r11
            r4 = r5
            r5 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            return r8
        L_0x005d:
            com.google.android.gms.ads.internal.zzal r8 = new com.google.android.gms.ads.internal.zzal
            com.google.android.gms.ads.internal.zzv r6 = com.google.android.gms.ads.internal.zzv.zzd(r1)
            r0 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.ClientApi.createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.ads.zzwf, java.lang.String, com.google.android.gms.internal.ads.zzalg, int):com.google.android.gms.internal.ads.zzxl");
    }

    public zzxg createAdLoaderBuilder(IObjectWrapper iObjectWrapper, String str, zzalg zzalg, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzlf();
        zzak zzak = new zzak(context, str, zzalg, new zzbbi(14300000, i, true, zzayh.zzav(context)), zzv.zzd(context));
        return zzak;
    }

    public zzyc getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper iObjectWrapper, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzlf();
        return zzay.zza(context, new zzbbi(14300000, i, true, zzayh.zzav(context)));
    }

    public zzadf createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return new zzacr((FrameLayout) ObjectWrapper.unwrap(iObjectWrapper), (FrameLayout) ObjectWrapper.unwrap(iObjectWrapper2));
    }

    public zzadk createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return new zzact((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public zzauw createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzalg zzalg, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzbv.zzlf();
        return new zzaun(context, zzv.zzd(context), zzalg, new zzbbi(14300000, i, true, zzayh.zzav(context)));
    }

    public zzaop createAdOverlay(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
        AdOverlayInfoParcel zzc = AdOverlayInfoParcel.zzc(activity.getIntent());
        if (zzc == null) {
            return new zzr(activity);
        }
        switch (zzc.zzdsa) {
            case 1:
                return new zzq(activity);
            case 2:
                return new zzx(activity);
            case 3:
                return new zzy(activity);
            case 4:
                return new zzs(activity, zzc);
            default:
                return new zzr(activity);
        }
    }
}
