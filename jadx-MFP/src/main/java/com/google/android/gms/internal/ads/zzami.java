package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.zzc;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzark
public final class zzami<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzalk {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzdog;
    private final NETWORK_EXTRAS zzdoh;

    public zzami(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.zzdog = mediationAdapter;
        this.zzdoh = network_extras;
    }

    public final zzyp getVideoController() {
        return null;
    }

    public final boolean isInitialized() {
        return true;
    }

    public final void setImmersiveMode(boolean z) {
    }

    public final void showVideo() {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzavz zzavz, List<String> list) {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, zzavz zzavz, String str2) throws RemoteException {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, String str2, zzalm zzalm, zzacp zzacp, List<String> list) {
    }

    public final void zza(zzwb zzwb, String str, String str2) {
    }

    public final void zzc(zzwb zzwb, String str) {
    }

    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final zzals zzuu() {
        return null;
    }

    public final zzalv zzuv() {
        return null;
    }

    public final boolean zzuy() {
        return false;
    }

    public final zzadx zzuz() {
        return null;
    }

    public final zzaly zzva() {
        return null;
    }

    public final IObjectWrapper zzut() throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzdog;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String str = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
        try {
            return ObjectWrapper.wrap(((MediationBannerAdapter) mediationAdapter).getBannerView());
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwf zzwf, zzwb zzwb, String str, zzalm zzalm) throws RemoteException {
        zza(iObjectWrapper, zzwf, zzwb, str, null, zzalm);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwf zzwf, zzwb zzwb, String str, String str2, zzalm zzalm) throws RemoteException {
        AdSize adSize;
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzdog;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String str3 = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
        zzbbd.zzdn("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzdog;
            zzamj zzamj = new zzamj(zzalm);
            Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
            MediationServerParameters zza = zza(str, zzwb.zzcjf, str2);
            int i = 0;
            AdSize[] adSizeArr = {AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
            while (true) {
                if (i < 6) {
                    if (adSizeArr[i].getWidth() == zzwf.width && adSizeArr[i].getHeight() == zzwf.height) {
                        adSize = adSizeArr[i];
                        break;
                    }
                    i++;
                } else {
                    adSize = new AdSize(zzc.zza(zzwf.width, zzwf.height, zzwf.zzckk));
                    break;
                }
            }
            mediationBannerAdapter.requestBannerAd(zzamj, activity, zza, adSize, zzamv.zza(zzwb, zzm(zzwb)), this.zzdoh);
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            throw new RemoteException();
        }
    }

    public final Bundle zzuw() {
        return new Bundle();
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, zzalm zzalm) throws RemoteException {
        zza(iObjectWrapper, zzwb, str, (String) null, zzalm);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzwb zzwb, String str, String str2, zzalm zzalm) throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzdog;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String str3 = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
        zzbbd.zzdn("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzdog).requestInterstitialAd(new zzamj(zzalm), (Activity) ObjectWrapper.unwrap(iObjectWrapper), zza(str, zzwb.zzcjf, str2), zzamv.zza(zzwb, zzm(zzwb)), this.zzdoh);
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            throw new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    public final void showInterstitial() throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzdog;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String str = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
        zzbbd.zzdn("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzdog).showInterstitial();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            throw new RemoteException();
        }
    }

    public final Bundle zzux() {
        return new Bundle();
    }

    public final void destroy() throws RemoteException {
        try {
            this.zzdog.destroy();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            throw new RemoteException();
        }
    }

    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    public final void resume() throws RemoteException {
        throw new RemoteException();
    }

    private final SERVER_PARAMETERS zza(String str, int i, String str2) throws RemoteException {
        HashMap hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    hashMap.put(str3, jSONObject.getString(str3));
                }
            } catch (Throwable th) {
                zzbbd.zzb("", th);
                throw new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class serverParametersType = this.zzdog.getServerParametersType();
        if (serverParametersType == null) {
            return null;
        }
        SERVER_PARAMETERS server_parameters = (MediationServerParameters) serverParametersType.newInstance();
        server_parameters.load(hashMap);
        return server_parameters;
    }

    private static boolean zzm(zzwb zzwb) {
        if (!zzwb.zzcje) {
            zzwu.zzpv();
            if (!zzbat.zzaaq()) {
                return false;
            }
        }
        return true;
    }
}
