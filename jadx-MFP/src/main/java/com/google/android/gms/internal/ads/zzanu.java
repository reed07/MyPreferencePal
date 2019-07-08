package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.common.util.PlatformVersion;

@zzark
public final class zzanu implements MediationInterstitialAdapter {
    private Uri mUri;
    /* access modifiers changed from: private */
    public Activity zzdow;
    /* access modifiers changed from: private */
    public MediationInterstitialListener zzdox;

    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzdox = mediationInterstitialListener;
        if (this.zzdox == null) {
            zzbbd.zzeo("Listener not set for mediation. Returning.");
        } else if (!(context instanceof Activity)) {
            zzbbd.zzeo("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzdox.onAdFailedToLoad(this, 0);
        } else {
            if (!(PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzabk.zzj(context))) {
                zzbbd.zzeo("Default browser does not support custom tabs. Bailing out.");
                this.zzdox.onAdFailedToLoad(this, 0);
                return;
            }
            String string = bundle.getString("tab_url");
            if (TextUtils.isEmpty(string)) {
                zzbbd.zzeo("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.zzdox.onAdFailedToLoad(this, 0);
                return;
            }
            this.zzdow = (Activity) context;
            this.mUri = Uri.parse(string);
            this.zzdox.onAdLoaded(this);
        }
    }

    public final void showInterstitial() {
        CustomTabsIntent build = new Builder().build();
        build.intent.setData(this.mUri);
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(new zzc(build.intent), null, new zzanv(this), null, new zzbbi(0, 0, false));
        zzayh.zzelc.post(new zzanw(this, adOverlayInfoParcel));
        zzbv.zzlj().zzyl();
    }

    public final void onDestroy() {
        zzbbd.zzdn("Destroying AdMobCustomTabsAdapter adapter.");
    }

    public final void onPause() {
        zzbbd.zzdn("Pausing AdMobCustomTabsAdapter adapter.");
    }

    public final void onResume() {
        zzbbd.zzdn("Resuming AdMobCustomTabsAdapter adapter.");
    }
}
