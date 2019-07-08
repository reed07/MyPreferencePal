package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzabg;
import com.google.android.gms.internal.ads.zzacp;
import com.google.android.gms.internal.ads.zzaeb;
import com.google.android.gms.internal.ads.zzaee;
import com.google.android.gms.internal.ads.zzaeh;
import com.google.android.gms.internal.ads.zzaek;
import com.google.android.gms.internal.ads.zzaen;
import com.google.android.gms.internal.ads.zzaeq;
import com.google.android.gms.internal.ads.zzafz;
import com.google.android.gms.internal.ads.zzagf;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzauu;
import com.google.android.gms.internal.ads.zzavb;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;
import com.google.android.gms.internal.ads.zzaxh;
import com.google.android.gms.internal.ads.zzaxs;
import com.google.android.gms.internal.ads.zzaxv;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzazb;
import com.google.android.gms.internal.ads.zzbai;
import com.google.android.gms.internal.ads.zzbat;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzcq;
import com.google.android.gms.internal.ads.zzcu;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzxa;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzxt;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyv;
import com.google.android.gms.internal.ads.zzzw;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzbw implements OnGlobalLayoutListener, OnScrollChangedListener {
    boolean zzbpa;
    final String zzbsm;
    public String zzbsn;
    final zzcu zzbso;
    public final zzbbi zzbsp;
    @Nullable
    zzbx zzbsq;
    @Nullable
    public zzaxv zzbsr;
    @Nullable
    public zzazb zzbss;
    public zzwf zzbst;
    @Nullable
    public zzaxf zzbsu;
    public zzaxg zzbsv;
    @Nullable
    public zzaxh zzbsw;
    @Nullable
    zzwx zzbsx;
    @Nullable
    zzxa zzbsy;
    @Nullable
    zzxt zzbsz;
    @Nullable
    zzxq zzbta;
    @Nullable
    zzxz zzbtb;
    @Nullable
    zzaeb zzbtc;
    @Nullable
    zzaee zzbtd;
    @Nullable
    zzaeq zzbte;
    @Nullable
    zzagf zzbtf;
    SimpleArrayMap<String, zzaeh> zzbtg;
    SimpleArrayMap<String, zzaek> zzbth;
    zzacp zzbti;
    @Nullable
    zzzw zzbtj;
    @Nullable
    zzafz zzbtk;
    @Nullable
    zzyv zzbtl;
    @Nullable
    zzaen zzbtm;
    @Nullable
    List<Integer> zzbtn;
    @Nullable
    zzabg zzbto;
    @Nullable
    zzavb zzbtp;
    @Nullable
    zzauu zzbtq;
    @Nullable
    public String zzbtr;
    @Nullable
    public String zzbts;
    @Nullable
    List<String> zzbtt;
    @Nullable
    public zzaxs zzbtu;
    @Nullable
    View zzbtv;
    public int zzbtw;
    private HashSet<zzaxh> zzbtx;
    private int zzbty;
    private int zzbtz;
    private zzbai zzbua;
    private boolean zzbub;
    private boolean zzbuc;
    private boolean zzbud;
    public final Context zzsp;

    public zzbw(Context context, zzwf zzwf, String str, zzbbi zzbbi) {
        this(context, zzwf, str, zzbbi, null);
    }

    private zzbw(Context context, zzwf zzwf, String str, zzbbi zzbbi, zzcu zzcu) {
        this.zzbtu = null;
        this.zzbtv = null;
        this.zzbtw = 0;
        this.zzbpa = false;
        this.zzbtx = null;
        this.zzbty = -1;
        this.zzbtz = -1;
        this.zzbub = true;
        this.zzbuc = true;
        this.zzbud = false;
        zzaan.initialize(context);
        if (zzbv.zzlj().zzyh() != null) {
            List zzqx = zzaan.zzqx();
            if (zzbbi.zzeou != 0) {
                zzqx.add(Integer.toString(zzbbi.zzeou));
            }
            zzbv.zzlj().zzyh().zzg(zzqx);
        }
        this.zzbsm = UUID.randomUUID().toString();
        if (zzwf.zzckl || zzwf.zzckn) {
            this.zzbsq = null;
        } else {
            zzbx zzbx = new zzbx(context, str, zzbbi.zzdp, this, this);
            this.zzbsq = zzbx;
            this.zzbsq.setMinimumWidth(zzwf.widthPixels);
            this.zzbsq.setMinimumHeight(zzwf.heightPixels);
            this.zzbsq.setVisibility(4);
        }
        this.zzbst = zzwf;
        this.zzbsn = str;
        this.zzsp = context;
        this.zzbsp = zzbbi;
        this.zzbso = new zzcu(new zzag(this));
        this.zzbua = new zzbai(200);
        this.zzbth = new SimpleArrayMap<>();
    }

    /* access modifiers changed from: 0000 */
    public final void zzj(View view) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzctk)).booleanValue()) {
            zzcq zzab = this.zzbso.zzab();
            if (zzab != null) {
                zzab.zzb(view);
            }
        }
    }

    public final void zza(HashSet<zzaxh> hashSet) {
        this.zzbtx = hashSet;
    }

    public final HashSet<zzaxh> zzmg() {
        return this.zzbtx;
    }

    public final void zzmh() {
        zzaxf zzaxf = this.zzbsu;
        if (zzaxf != null && zzaxf.zzdrv != null) {
            this.zzbsu.zzdrv.destroy();
        }
    }

    public final void zzmi() {
        zzaxf zzaxf = this.zzbsu;
        if (!(zzaxf == null || zzaxf.zzdnc == null)) {
            try {
                this.zzbsu.zzdnc.destroy();
            } catch (RemoteException unused) {
                zzaxz.zzeo("Could not destroy mediation adapter.");
            }
        }
    }

    public final boolean zzmj() {
        return this.zzbtw == 0;
    }

    public final boolean zzmk() {
        return this.zzbtw == 1;
    }

    public final void onGlobalLayout() {
        zzq(false);
    }

    public final void onScrollChanged() {
        zzq(true);
        this.zzbud = true;
    }

    private final void zzq(boolean z) {
        if (this.zzbsq != null) {
            zzaxf zzaxf = this.zzbsu;
            if (!(zzaxf == null || zzaxf.zzdrv == null || this.zzbsu.zzdrv.zzadl() == null)) {
                if (!z || this.zzbua.tryAcquire()) {
                    if (this.zzbsu.zzdrv.zzadl().zzmu()) {
                        int[] iArr = new int[2];
                        this.zzbsq.getLocationOnScreen(iArr);
                        zzwu.zzpv();
                        int zzb = zzbat.zzb(this.zzsp, iArr[0]);
                        zzwu.zzpv();
                        int zzb2 = zzbat.zzb(this.zzsp, iArr[1]);
                        if (!(zzb == this.zzbty && zzb2 == this.zzbtz)) {
                            this.zzbty = zzb;
                            this.zzbtz = zzb2;
                            this.zzbsu.zzdrv.zzadl().zza(this.zzbty, this.zzbtz, !z);
                        }
                    }
                    zzbx zzbx = this.zzbsq;
                    if (zzbx != null) {
                        View findViewById = zzbx.getRootView().findViewById(16908290);
                        if (findViewById != null) {
                            Rect rect = new Rect();
                            Rect rect2 = new Rect();
                            this.zzbsq.getGlobalVisibleRect(rect);
                            findViewById.getGlobalVisibleRect(rect2);
                            if (rect.top != rect2.top) {
                                this.zzbub = false;
                            }
                            if (rect.bottom != rect2.bottom) {
                                this.zzbuc = false;
                            }
                        }
                    }
                }
            }
        }
    }

    public final String zzml() {
        if (this.zzbub && this.zzbuc) {
            return "";
        }
        if (this.zzbub) {
            return this.zzbud ? "top-scrollable" : "top-locked";
        }
        if (this.zzbuc) {
            return this.zzbud ? "bottom-scrollable" : "bottom-locked";
        }
        return "";
    }

    public final void zzr(boolean z) {
        if (this.zzbtw == 0) {
            zzaxf zzaxf = this.zzbsu;
            if (!(zzaxf == null || zzaxf.zzdrv == null)) {
                this.zzbsu.zzdrv.stopLoading();
            }
        }
        zzaxv zzaxv = this.zzbsr;
        if (zzaxv != null) {
            zzaxv.cancel();
        }
        zzazb zzazb = this.zzbss;
        if (zzazb != null) {
            zzazb.cancel();
        }
        if (z) {
            this.zzbsu = null;
        }
    }
}
