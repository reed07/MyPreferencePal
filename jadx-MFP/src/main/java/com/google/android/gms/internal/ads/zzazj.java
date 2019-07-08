package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzazj {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private String zzemd = "";
    @GuardedBy("mLock")
    private String zzeme = "";
    @GuardedBy("mLock")
    private boolean zzemf = false;
    @VisibleForTesting
    private String zzemg = "";

    public final void zzf(Context context, String str, String str2) {
        if (!zzg(context, str, str2)) {
            zza(context, "In-app preview failed to load because of a system error. Please try again later.", true, true);
        } else if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(this.zzemg)) {
            zzaxz.zzdn("Creative is not pushed for this device.");
            zza(context, "There was no creative pushed from DFP to the device.", false, false);
        } else if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzemg)) {
            zzaxz.zzdn("The app is not linked for creative preview.");
            zzj(context, str, str2);
        } else {
            if ("0".equals(this.zzemg)) {
                zzaxz.zzdn("Device is linked for in app preview.");
                zza(context, "The device is successfully linked for creative preview.", false, true);
            }
        }
    }

    public final void zza(Context context, String str, String str2, @Nullable String str3) {
        boolean zzaah = zzaah();
        if (zzh(context, str, str2)) {
            if (!zzaah && !TextUtils.isEmpty(str3)) {
                zzb(context, str2, str3, str);
            }
            zzaxz.zzdn("Device is linked for debug signals.");
            zza(context, "The device is successfully linked for troubleshooting.", false, true);
            return;
        }
        zzj(context, str, str2);
    }

    @VisibleForTesting
    private final boolean zzg(Context context, String str, String str2) {
        String zzi = zzi(context, zzc(context, (String) zzwu.zzpz().zzd(zzaan.zzcwg), str, str2).toString(), str2);
        if (TextUtils.isEmpty(zzi)) {
            zzaxz.zzdn("Not linked for in app preview.");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(zzi.trim());
            String optString = jSONObject.optString("gct");
            this.zzemg = jSONObject.optString("status");
            synchronized (this.mLock) {
                this.zzeme = optString;
            }
            return true;
        } catch (JSONException e) {
            zzaxz.zzc("Fail to get in app preview response json.", e);
            return false;
        }
    }

    @VisibleForTesting
    private final boolean zzh(Context context, String str, String str2) {
        String zzi = zzi(context, zzc(context, (String) zzwu.zzpz().zzd(zzaan.zzcwh), str, str2).toString(), str2);
        if (TextUtils.isEmpty(zzi)) {
            zzaxz.zzdn("Not linked for debug signals.");
            return false;
        }
        try {
            boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(new JSONObject(zzi.trim()).optString("debug_mode"));
            synchronized (this.mLock) {
                this.zzemf = equals;
            }
            return equals;
        } catch (JSONException e) {
            zzaxz.zzc("Fail to get debug mode response json.", e);
            return false;
        }
    }

    @VisibleForTesting
    private static String zzi(Context context, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", zzbv.zzlf().zzo(context, str2));
        zzbcb zzc = new zzazs(context).zzc(str, hashMap);
        try {
            return (String) zzc.get((long) ((Integer) zzwu.zzpz().zzd(zzaan.zzcwj)).intValue(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            String str3 = "Timeout while retriving a response from: ";
            String valueOf = String.valueOf(str);
            zzaxz.zzb(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), e);
            zzc.cancel(true);
            return null;
        } catch (InterruptedException e2) {
            String str4 = "Interrupted while retriving a response from: ";
            String valueOf2 = String.valueOf(str);
            zzaxz.zzb(valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4), e2);
            zzc.cancel(true);
            return null;
        } catch (Exception e3) {
            String str5 = "Error retriving a response from: ";
            String valueOf3 = String.valueOf(str);
            zzaxz.zzb(valueOf3.length() != 0 ? str5.concat(valueOf3) : new String(str5), e3);
            return null;
        }
    }

    private final void zzj(Context context, String str, String str2) {
        zzbv.zzlf();
        zzayh.zza(context, zzc(context, (String) zzwu.zzpz().zzd(zzaan.zzcwf), str, str2));
    }

    public final void zzb(Context context, String str, String str2, String str3) {
        Builder buildUpon = zzc(context, (String) zzwu.zzpz().zzd(zzaan.zzcwi), str3, str).buildUpon();
        buildUpon.appendQueryParameter("debugData", str2);
        zzbv.zzlf();
        zzayh.zzc(context, str, buildUpon.build().toString());
    }

    private final Uri zzc(Context context, String str, String str2, String str3) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("linkedDeviceId", zzbc(context));
        buildUpon.appendQueryParameter("adSlotPath", str2);
        buildUpon.appendQueryParameter("afmaVersion", str3);
        return buildUpon.build();
    }

    private final String zzbc(Context context) {
        String str;
        synchronized (this.mLock) {
            if (TextUtils.isEmpty(this.zzemd)) {
                zzbv.zzlf();
                this.zzemd = zzayh.zzp(context, "debug_signals_id.txt");
                if (TextUtils.isEmpty(this.zzemd)) {
                    zzbv.zzlf();
                    this.zzemd = zzayh.zzzs();
                    zzbv.zzlf();
                    zzayh.zzd(context, "debug_signals_id.txt", this.zzemd);
                }
            }
            str = this.zzemd;
        }
        return str;
    }

    public final String zzaag() {
        String str;
        synchronized (this.mLock) {
            str = this.zzeme;
        }
        return str;
    }

    public final boolean zzaah() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzemf;
        }
        return z;
    }

    @VisibleForTesting
    private final void zza(Context context, String str, boolean z, boolean z2) {
        if (!(context instanceof Activity)) {
            zzaxz.zzen("Can not create dialog without Activity Context");
            return;
        }
        Handler handler = zzayh.zzelc;
        zzazk zzazk = new zzazk(this, context, str, z, z2);
        handler.post(zzazk);
    }
}
