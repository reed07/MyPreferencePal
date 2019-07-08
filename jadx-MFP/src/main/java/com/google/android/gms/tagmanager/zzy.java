package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzl;
import com.google.android.gms.internal.measurement.zzo;
import com.google.android.gms.internal.measurement.zzre;
import com.google.android.gms.internal.measurement.zzrf;
import com.google.android.gms.internal.measurement.zzrk;

@ShowFirstParty
public final class zzy extends BasePendingResult<ContainerHolder> {
    private final String zzazq;
    /* access modifiers changed from: private */
    public long zzazv;
    private final Looper zzazy;
    private final TagManager zzbaf;
    private final zzaf zzbai;
    /* access modifiers changed from: private */
    public final zzej zzbaj;
    private final int zzbak;
    /* access modifiers changed from: private */
    public final zzai zzbal;
    private zzah zzbam;
    private zzrf zzban;
    /* access modifiers changed from: private */
    public volatile zzv zzbao;
    /* access modifiers changed from: private */
    public volatile boolean zzbap;
    /* access modifiers changed from: private */
    public zzo zzbaq;
    private String zzbar;
    private zzag zzbas;
    private zzac zzbat;
    private final Context zzri;
    /* access modifiers changed from: private */
    public final Clock zzrz;

    public zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzal) {
        Context context2 = context;
        String str2 = str;
        zzex zzex = new zzex(context2, str2);
        zzes zzes = new zzes(context2, str2, zzal);
        zzrf zzrf = new zzrf(context2);
        Clock instance = DefaultClock.getInstance();
        zzdg zzdg = new zzdg(1, 5, 900000, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, "refreshing", DefaultClock.getInstance());
        this(context2, tagManager, looper, str2, i, zzex, zzes, zzrf, instance, zzdg, new zzai(context2, str2));
        this.zzban.zzfh(zzal.zzoe());
    }

    @VisibleForTesting
    private zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzah zzah, zzag zzag, zzrf zzrf, Clock clock, zzej zzej, zzai zzai) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.zzri = context;
        this.zzbaf = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzazy = looper;
        this.zzazq = str;
        this.zzbak = i;
        this.zzbam = zzah;
        this.zzbas = zzag;
        this.zzban = zzrf;
        this.zzbai = new zzaf(this, null);
        this.zzbaq = new zzo();
        this.zzrz = clock;
        this.zzbaj = zzej;
        this.zzbal = zzai;
        if (zznw()) {
            zzdf(zzeh.zzpm().zzpo());
        }
    }

    public final void zznt() {
        zzrk zzv = this.zzbam.zzv(this.zzbak);
        if (zzv != null) {
            Container container = new Container(this.zzri, this.zzbaf.getDataLayer(), this.zzazq, 0, zzv);
            setResult(new zzv(this.zzbaf, this.zzazy, container, new zzaa(this)));
        } else {
            String str = "Default was requested, but no default container was found";
            zzdi.e(str);
            setResult(createFailedResult(new Status(10, str, null)));
        }
        this.zzbas = null;
        this.zzbam = null;
    }

    public final void zznu() {
        zzn(false);
    }

    public final void zznv() {
        zzn(true);
    }

    private final void zzn(boolean z) {
        this.zzbam.zza((zzdh<zzre>) new zzad<zzre>(this, null));
        this.zzbas.zza(new zzae(this, null));
        zzrk zzv = this.zzbam.zzv(this.zzbak);
        if (zzv != null) {
            TagManager tagManager = this.zzbaf;
            Looper looper = this.zzazy;
            Container container = new Container(this.zzri, tagManager.getDataLayer(), this.zzazq, 0, zzv);
            this.zzbao = new zzv(tagManager, looper, container, this.zzbai);
        }
        this.zzbat = new zzab(this, z);
        if (zznw()) {
            this.zzbas.zza(0, "");
        } else {
            this.zzbam.zzny();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(com.google.android.gms.internal.measurement.zzo r10, long r11, boolean r13) {
        /*
            r9 = this;
            monitor-enter(r9)
            if (r13 == 0) goto L_0x0005
            boolean r13 = r9.zzbap     // Catch:{ all -> 0x0070 }
        L_0x0005:
            boolean r13 = r9.isReady()     // Catch:{ all -> 0x0070 }
            if (r13 == 0) goto L_0x0011
            com.google.android.gms.tagmanager.zzv r13 = r9.zzbao     // Catch:{ all -> 0x0070 }
            if (r13 != 0) goto L_0x0011
            monitor-exit(r9)
            return
        L_0x0011:
            r9.zzbaq = r10     // Catch:{ all -> 0x0070 }
            r9.zzazv = r11     // Catch:{ all -> 0x0070 }
            com.google.android.gms.tagmanager.zzai r13 = r9.zzbal     // Catch:{ all -> 0x0070 }
            long r0 = r13.zznz()     // Catch:{ all -> 0x0070 }
            r2 = 0
            long r4 = r9.zzazv     // Catch:{ all -> 0x0070 }
            long r4 = r4 + r0
            com.google.android.gms.common.util.Clock r13 = r9.zzrz     // Catch:{ all -> 0x0070 }
            long r6 = r13.currentTimeMillis()     // Catch:{ all -> 0x0070 }
            long r4 = r4 - r6
            long r0 = java.lang.Math.min(r0, r4)     // Catch:{ all -> 0x0070 }
            long r0 = java.lang.Math.max(r2, r0)     // Catch:{ all -> 0x0070 }
            r9.zzao(r0)     // Catch:{ all -> 0x0070 }
            com.google.android.gms.tagmanager.Container r13 = new com.google.android.gms.tagmanager.Container     // Catch:{ all -> 0x0070 }
            android.content.Context r3 = r9.zzri     // Catch:{ all -> 0x0070 }
            com.google.android.gms.tagmanager.TagManager r0 = r9.zzbaf     // Catch:{ all -> 0x0070 }
            com.google.android.gms.tagmanager.DataLayer r4 = r0.getDataLayer()     // Catch:{ all -> 0x0070 }
            java.lang.String r5 = r9.zzazq     // Catch:{ all -> 0x0070 }
            r2 = r13
            r6 = r11
            r8 = r10
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x0070 }
            com.google.android.gms.tagmanager.zzv r10 = r9.zzbao     // Catch:{ all -> 0x0070 }
            if (r10 != 0) goto L_0x0056
            com.google.android.gms.tagmanager.zzv r10 = new com.google.android.gms.tagmanager.zzv     // Catch:{ all -> 0x0070 }
            com.google.android.gms.tagmanager.TagManager r11 = r9.zzbaf     // Catch:{ all -> 0x0070 }
            android.os.Looper r12 = r9.zzazy     // Catch:{ all -> 0x0070 }
            com.google.android.gms.tagmanager.zzaf r0 = r9.zzbai     // Catch:{ all -> 0x0070 }
            r10.<init>(r11, r12, r13, r0)     // Catch:{ all -> 0x0070 }
            r9.zzbao = r10     // Catch:{ all -> 0x0070 }
            goto L_0x005b
        L_0x0056:
            com.google.android.gms.tagmanager.zzv r10 = r9.zzbao     // Catch:{ all -> 0x0070 }
            r10.zza(r13)     // Catch:{ all -> 0x0070 }
        L_0x005b:
            boolean r10 = r9.isReady()     // Catch:{ all -> 0x0070 }
            if (r10 != 0) goto L_0x006e
            com.google.android.gms.tagmanager.zzac r10 = r9.zzbat     // Catch:{ all -> 0x0070 }
            boolean r10 = r10.zzb(r13)     // Catch:{ all -> 0x0070 }
            if (r10 == 0) goto L_0x006e
            com.google.android.gms.tagmanager.zzv r10 = r9.zzbao     // Catch:{ all -> 0x0070 }
            r9.setResult(r10)     // Catch:{ all -> 0x0070 }
        L_0x006e:
            monitor-exit(r9)
            return
        L_0x0070:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzy.zza(com.google.android.gms.internal.measurement.zzo, long, boolean):void");
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final synchronized void zzdf(String str) {
        this.zzbar = str;
        if (this.zzbas != null) {
            this.zzbas.zzdg(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized String zznq() {
        return this.zzbar;
    }

    /* access modifiers changed from: private */
    public final synchronized void zzao(long j) {
        if (this.zzbas == null) {
            zzdi.zzab("Refresh requested, but no network load scheduler.");
        } else {
            this.zzbas.zza(j, this.zzbaq.zzqh);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public final ContainerHolder createFailedResult(Status status) {
        if (this.zzbao != null) {
            return this.zzbao;
        }
        if (status == Status.RESULT_TIMEOUT) {
            zzdi.e("timer expired: setting result to failure");
        }
        return new zzv(status);
    }

    /* access modifiers changed from: private */
    public final boolean zznw() {
        zzeh zzpm = zzeh.zzpm();
        return (zzpm.zzpn() == zza.CONTAINER || zzpm.zzpn() == zza.CONTAINER_DEBUG) && this.zzazq.equals(zzpm.getContainerId());
    }

    /* access modifiers changed from: private */
    public final synchronized void zza(zzo zzo) {
        if (this.zzbam != null) {
            zzre zzre = new zzre();
            zzre.zzbqe = this.zzazv;
            zzre.zzqg = new zzl();
            zzre.zzbqf = zzo;
            this.zzbam.zza(zzre);
        }
    }
}
