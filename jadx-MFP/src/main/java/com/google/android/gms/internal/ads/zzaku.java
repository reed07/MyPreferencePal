package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzaku implements zzaky {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final zzalg zzbma;
    private final zzacp zzbnx;
    private final zzbbi zzbob;
    private zzwb zzbqo;
    private final zzwf zzbqu;
    private final boolean zzbum;
    /* access modifiers changed from: private */
    public final String zzdml;
    private final long zzdmm;
    private final zzakr zzdmn;
    private final zzakq zzdmo;
    private final List<String> zzdmp;
    private final List<String> zzdmq;
    private final List<String> zzdmr;
    private final boolean zzdms;
    private final boolean zzdmt;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public zzalj zzdmu;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public int zzdmv = -2;
    private zzalp zzdmw;

    public zzaku(Context context, String str, zzalg zzalg, zzakr zzakr, zzakq zzakq, zzwb zzwb, zzwf zzwf, zzbbi zzbbi, boolean z, boolean z2, zzacp zzacp, List<String> list, List<String> list2, List<String> list3, boolean z3) {
        zzwb zzwb2;
        String str2 = str;
        zzakr zzakr2 = zzakr;
        zzakq zzakq2 = zzakq;
        this.mContext = context;
        this.zzbma = zzalg;
        this.zzdmo = zzakq2;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.zzdml = zzum();
        } else {
            this.zzdml = str2;
        }
        this.zzdmn = zzakr2;
        if (zzakq2.zzdlo != -1) {
            this.zzdmm = zzakq2.zzdlo;
            zzwb2 = zzwb;
        } else if (zzakr2.zzdlo != -1) {
            this.zzdmm = zzakr2.zzdlo;
            zzwb2 = zzwb;
        } else {
            this.zzdmm = 10000;
            zzwb2 = zzwb;
        }
        this.zzbqo = zzwb2;
        this.zzbqu = zzwf;
        this.zzbob = zzbbi;
        this.zzbum = z;
        this.zzdms = z2;
        this.zzbnx = zzacp;
        this.zzdmp = list;
        this.zzdmq = list2;
        this.zzdmr = list3;
        this.zzdmt = z3;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            try {
                if (this.zzdmu != null) {
                    this.zzdmu.destroy();
                }
            } catch (RemoteException e) {
                zzaxz.zzc("Could not destroy mediation adapter.", e);
            }
            this.zzdmv = -1;
            this.mLock.notify();
        }
    }

    private final String zzum() {
        try {
            if (!TextUtils.isEmpty(this.zzdmo.zzdky)) {
                return this.zzbma.zzcq(this.zzdmo.zzdky) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException unused) {
            zzaxz.zzeo("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    public final void zzco(int i) {
        synchronized (this.mLock) {
            this.zzdmv = i;
            this.mLock.notify();
        }
    }

    public final void zza(int i, zzalp zzalp) {
        synchronized (this.mLock) {
            this.zzdmv = 0;
            this.zzdmw = zzalp;
            this.mLock.notify();
        }
    }

    @VisibleForTesting
    private static zzalj zza(MediationAdapter mediationAdapter) {
        return new zzamd(mediationAdapter);
    }

    public final zzakx zzj(long j, long j2) {
        zzakx zzakx;
        synchronized (this.mLock) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            zzakt zzakt = new zzakt();
            zzayh.zzelc.post(new zzakv(this, zzakt));
            long j3 = this.zzdmm;
            while (this.zzdmv == -2) {
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                long j4 = j3 - (elapsedRealtime2 - elapsedRealtime);
                long j5 = j2 - (elapsedRealtime2 - j);
                if (j4 <= 0 || j5 <= 0) {
                    zzaxz.zzen("Timed out waiting for adapter.");
                    this.zzdmv = 3;
                } else {
                    try {
                        this.mLock.wait(Math.min(j4, j5));
                    } catch (InterruptedException unused) {
                        this.zzdmv = 5;
                    }
                }
            }
            zzakx = new zzakx(this.zzdmo, this.zzdmu, this.zzdml, zzakt, this.zzdmv, zzun(), zzbv.zzlm().elapsedRealtime() - elapsedRealtime);
        }
        return zzakx;
    }

    @GuardedBy("mLock")
    private final zzalp zzun() {
        if (this.zzdmv != 0 || !zzup()) {
            return null;
        }
        try {
            if (!(!zzcp(4) || this.zzdmw == null || this.zzdmw.zzur() == 0)) {
                return this.zzdmw;
            }
        } catch (RemoteException unused) {
            zzaxz.zzeo("Could not get cpm value from MediationResponseMetadata");
        }
        return new zzakw(zzuq());
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final zzalj zzuo() {
        String str = "Instantiating mediation adapter: ";
        String valueOf = String.valueOf(this.zzdml);
        zzaxz.zzen(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        if (!this.zzbum && !this.zzdmo.zzuk()) {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzdml)) {
                return zza((MediationAdapter) new AdMobAdapter());
            }
            if ("com.google.ads.mediation.AdUrlAdapter".equals(this.zzdml)) {
                return zza((MediationAdapter) new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzdml)) {
                return new zzamd(new zzanu());
            }
        }
        try {
            return this.zzbma.zzcp(this.zzdml);
        } catch (RemoteException e) {
            String str2 = "Could not instantiate mediation adapter: ";
            String valueOf2 = String.valueOf(this.zzdml);
            zzaxz.zza(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zza(zzakt zzakt) {
        String zzcm = zzcm(this.zzdmo.zzdle);
        try {
            if (this.zzbob.zzeov >= 4100000) {
                if (!this.zzbum) {
                    if (!this.zzdmo.zzuk()) {
                        if (this.zzbqu.zzckl) {
                            this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqo, zzcm, this.zzdmo.zzdku, (zzalm) zzakt);
                            return;
                        } else if (!this.zzdms) {
                            this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqu, this.zzbqo, zzcm, this.zzdmo.zzdku, zzakt);
                            return;
                        } else if (this.zzdmo.zzdli != null) {
                            this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqo, zzcm, this.zzdmo.zzdku, zzakt, new zzacp(zzcn(this.zzdmo.zzdlm)), this.zzdmo.zzdll);
                            return;
                        } else {
                            this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqu, this.zzbqo, zzcm, this.zzdmo.zzdku, zzakt);
                            return;
                        }
                    }
                }
                ArrayList arrayList = new ArrayList(this.zzdmp);
                if (this.zzdmq != null) {
                    for (String str : this.zzdmq) {
                        String str2 = ":false";
                        if (this.zzdmr != null && this.zzdmr.contains(str)) {
                            str2 = ":true";
                        }
                        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 7 + String.valueOf(str2).length());
                        sb.append("custom:");
                        sb.append(str);
                        sb.append(str2);
                        arrayList.add(sb.toString());
                    }
                }
                this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqo, zzcm, this.zzdmo.zzdku, zzakt, this.zzbnx, arrayList);
            } else if (this.zzbqu.zzckl) {
                this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqo, zzcm, zzakt);
            } else {
                this.zzdmu.zza(ObjectWrapper.wrap(this.mContext), this.zzbqu, this.zzbqo, zzcm, (zzalm) zzakt);
            }
        } catch (RemoteException e) {
            zzaxz.zzc("Could not request ad from mediation adapter.", e);
            zzco(5);
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zzcp(int i) {
        Bundle bundle;
        try {
            if (this.zzbum) {
                bundle = this.zzdmu.zzux();
            } else if (this.zzbqu.zzckl) {
                bundle = this.zzdmu.getInterstitialAdapterInfo();
            } else {
                bundle = this.zzdmu.zzuw();
            }
            if (bundle == null || (bundle.getInt("capabilities", 0) & i) != i) {
                return false;
            }
            return true;
        } catch (RemoteException unused) {
            zzaxz.zzeo("Could not get adapter info. Returning false");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzup() {
        return this.zzdmn.zzdma != -1;
    }

    @GuardedBy("mLock")
    private final String zzcm(String str) {
        if (str == null || !zzup() || zzcp(2)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.remove("cpm_floor_cents");
            return jSONObject.toString();
        } catch (JSONException unused) {
            zzaxz.zzeo("Could not remove field. Returning the original value");
            return str;
        }
    }

    @GuardedBy("mLock")
    private final int zzuq() {
        if (this.zzdmo.zzdle == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzdmo.zzdle);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzdml)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzcp(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            if (optInt == 0) {
                optInt = jSONObject.optInt("penalized_average_cpm_cents", 0);
            }
            return optInt;
        } catch (JSONException unused) {
            zzaxz.zzeo("Could not convert to json. Returning 0");
            return 0;
        }
    }

    private static NativeAdOptions zzcn(String str) {
        Builder builder = new Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = 0;
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            String optString = jSONObject.optString("native_image_orientation", "any");
            if (Extras.ORIENTATION_LANDSCAPE.equals(optString)) {
                i = 2;
            } else if (Extras.ORIENTATION_PORTRAIT.equals(optString)) {
                i = 1;
            } else if (!"any".equals(optString)) {
                i = -1;
            }
            builder.setImageOrientation(i);
        } catch (JSONException e) {
            zzaxz.zzc("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }
}
