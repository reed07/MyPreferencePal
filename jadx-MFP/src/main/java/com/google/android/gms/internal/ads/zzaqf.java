package com.google.android.gms.internal.ads;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.gmsg.zzac;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

@zzark
public final class zzaqf {
    private final Context mContext;
    private OnGlobalLayoutListener mGlobalLayoutListener;
    private final Object mLock = new Object();
    private final zzaba zzbln;
    @GuardedBy("mLock")
    private int zzbty = -1;
    @GuardedBy("mLock")
    private int zzbtz = -1;
    private zzbai zzbua;
    private final DisplayMetrics zzbwk;
    private final zzcu zzdcf;
    private final zzaxg zzdsk;
    /* access modifiers changed from: private */
    public final zzbb zzdug;
    private OnScrollChangedListener zzduh;

    public zzaqf(Context context, zzcu zzcu, zzaxg zzaxg, zzaba zzaba, zzbb zzbb) {
        this.mContext = context;
        this.zzdcf = zzcu;
        this.zzdsk = zzaxg;
        this.zzbln = zzaba;
        this.zzdug = zzbb;
        this.zzbua = new zzbai(200);
        zzbv.zzlf();
        this.zzbwk = zzayh.zza((WindowManager) context.getSystemService("window"));
    }

    private final void zza(zzbgg zzbgg, boolean z) {
        zzbgg.zza("/video", zzf.zzdfe);
        zzbgg.zza("/videoMeta", zzf.zzdff);
        zzbgg.zza("/precache", (zzu<? super zzbgg>) new zzbfq<Object>());
        zzbgg.zza("/delayPageLoaded", zzf.zzdfi);
        zzbgg.zza("/instrument", zzf.zzdfg);
        zzbgg.zza("/log", zzf.zzdez);
        zzbgg.zza("/videoClicked", zzf.zzdfa);
        zzbgg.zza("/trackActiveViewUnit", (zzu<? super zzbgg>) new zzaql<Object>(this));
        zzbgg.zza("/untrackActiveViewUnit", (zzu<? super zzbgg>) new zzaqm<Object>(this));
        if (z) {
            zzbgg.zza("/open", (zzu<? super zzbgg>) new zzac<Object>(null, null));
        }
    }

    private final OnGlobalLayoutListener zza(WeakReference<zzbgg> weakReference) {
        if (this.mGlobalLayoutListener == null) {
            this.mGlobalLayoutListener = new zzaqn(this, weakReference);
        }
        return this.mGlobalLayoutListener;
    }

    private final OnScrollChangedListener zzb(WeakReference<zzbgg> weakReference) {
        if (this.zzduh == null) {
            this.zzduh = new zzaqo(this, weakReference);
        }
        return this.zzduh;
    }

    /* access modifiers changed from: private */
    public final void zza(WeakReference<zzbgg> weakReference, boolean z) {
        if (weakReference != null) {
            zzbgg zzbgg = (zzbgg) weakReference.get();
            if (zzbgg != null && zzbgg.getView() != null) {
                if (!z || this.zzbua.tryAcquire()) {
                    int[] iArr = new int[2];
                    zzbgg.getView().getLocationOnScreen(iArr);
                    zzwu.zzpv();
                    boolean z2 = false;
                    int zzb = zzbat.zzb(this.zzbwk, iArr[0]);
                    zzwu.zzpv();
                    int zzb2 = zzbat.zzb(this.zzbwk, iArr[1]);
                    synchronized (this.mLock) {
                        if (!(this.zzbty == zzb && this.zzbtz == zzb2)) {
                            this.zzbty = zzb;
                            this.zzbtz = zzb2;
                            zzbhn zzadl = zzbgg.zzadl();
                            int i = this.zzbty;
                            int i2 = this.zzbtz;
                            if (!z) {
                                z2 = true;
                            }
                            zzadl.zza(i, i2, z2);
                        }
                    }
                }
            }
        }
    }

    @VisibleForTesting
    private final zzbgg zzwg() throws zzbgq {
        zzbv.zzlg();
        return zzbgm.zza(this.mContext, zzbht.zzaey(), "native-video", false, false, this.zzdcf, this.zzdsk.zzeag.zzbsp, this.zzbln, null, this.zzdug.zzid(), this.zzdsk.zzehw);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(boolean z, zzbcl zzbcl, String str, String str2) {
        try {
            zzbgg zzwg = zzwg();
            if (z) {
                zzwg.zza(zzbht.zzafa());
            } else {
                zzwg.zza(zzbht.zzaez());
            }
            this.zzdug.zzf(zzwg);
            WeakReference weakReference = new WeakReference(zzwg);
            zzwg.zzadl().zza(zza(weakReference), zzb(weakReference));
            zza(zzwg, z);
            zzwg.zzadl().zza((zzbho) new zzaqi(this, zzbcl, zzwg));
            zzwg.zzc(str, str2, null);
        } catch (Exception e) {
            zzaxz.zzc("Exception occurred while getting video view", e);
            zzbcl.set(null);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(zzbcl zzbcl, zzbgg zzbgg, boolean z) {
        this.zzdug.zzks();
        zzbcl.set(zzbgg);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(boolean z, JSONObject jSONObject, zzbcl zzbcl) {
        try {
            zzbgg zzwg = zzwg();
            if (z) {
                zzwg.zza(zzbht.zzafa());
            } else {
                zzwg.zza(zzbht.zzaez());
            }
            this.zzdug.zzf(zzwg);
            WeakReference weakReference = new WeakReference(zzwg);
            zzwg.zzadl().zza(zza(weakReference), zzb(weakReference));
            zza(zzwg, z);
            zzwg.zzadl().zza((zzbhp) new zzaqj(zzwg, jSONObject));
            zzwg.zzadl().zza((zzbho) new zzaqk(this, zzbcl, zzwg));
            zzwg.loadUrl((String) zzwu.zzpz().zzd(zzaan.zzcug));
        } catch (Exception e) {
            zzaxz.zzc("Exception occurred while getting video view", e);
            zzbcl.set(null);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzb(zzbcl zzbcl, zzbgg zzbgg, boolean z) {
        this.zzdug.zzks();
        zzbcl.set(zzbgg);
    }
}
