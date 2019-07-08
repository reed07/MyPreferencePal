package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseArray;
import java.io.IOException;

final class zzlc implements zzib, zzlm, zzlu, zzpg<zzlh> {
    /* access modifiers changed from: private */
    public final Handler handler;
    private final Uri uri;
    private long zzaan;
    private final zzov zzagy;
    private final int zzawn;
    /* access modifiers changed from: private */
    public final zzll zzawo;
    private final zzlp zzawp;
    private final zzot zzawq;
    /* access modifiers changed from: private */
    public final String zzawr;
    /* access modifiers changed from: private */
    public final long zzaws;
    private final zzpf zzawt = new zzpf("Loader:ExtractorMediaPeriod");
    private final zzli zzawu;
    private final zzpq zzawv;
    private final Runnable zzaww;
    /* access modifiers changed from: private */
    public final Runnable zzawx;
    /* access modifiers changed from: private */
    public final SparseArray<zzls> zzawy;
    /* access modifiers changed from: private */
    public zzln zzawz;
    private zzig zzaxa;
    private boolean zzaxb;
    private boolean zzaxc;
    private boolean zzaxd;
    private int zzaxe;
    private zzma zzaxf;
    private boolean[] zzaxg;
    private boolean[] zzaxh;
    private boolean zzaxi;
    private long zzaxj;
    private long zzaxk;
    private int zzaxl;
    private boolean zzaxm;
    private long zzcc;
    private final Handler zzwx;
    /* access modifiers changed from: private */
    public boolean zzyb;
    private boolean zzyu;

    public zzlc(Uri uri2, zzov zzov, zzhz[] zzhzArr, int i, Handler handler2, zzll zzll, zzlp zzlp, zzot zzot, String str, int i2) {
        this.uri = uri2;
        this.zzagy = zzov;
        this.zzawn = i;
        this.zzwx = handler2;
        this.zzawo = zzll;
        this.zzawp = zzlp;
        this.zzawq = zzot;
        this.zzawr = str;
        this.zzaws = (long) i2;
        this.zzawu = new zzli(zzhzArr, this);
        this.zzawv = new zzpq();
        this.zzaww = new zzld(this);
        this.zzawx = new zzle(this);
        this.handler = new Handler();
        this.zzaxk = -9223372036854775807L;
        this.zzawy = new SparseArray<>();
        this.zzcc = -1;
    }

    public final void zzaa(long j) {
    }

    public final void release() {
        this.zzawt.zza((Runnable) new zzlf(this, this.zzawu));
        this.handler.removeCallbacksAndMessages(null);
        this.zzyb = true;
    }

    public final void zza(zzln zzln, long j) {
        this.zzawz = zzln;
        this.zzawv.zzgz();
        startLoading();
    }

    public final void zzew() throws IOException {
        this.zzawt.zzbi(Integer.MIN_VALUE);
    }

    public final zzma zzex() {
        return this.zzaxf;
    }

    public final long zza(zzom[] zzomArr, boolean[] zArr, zzlv[] zzlvArr, boolean[] zArr2, long j) {
        zzpo.checkState(this.zzyu);
        for (int i = 0; i < zzomArr.length; i++) {
            if (zzlvArr[i] != null && (zzomArr[i] == null || !zArr[i])) {
                int zza = zzlvArr[i].track;
                zzpo.checkState(this.zzaxg[zza]);
                this.zzaxe--;
                this.zzaxg[zza] = false;
                ((zzls) this.zzawy.valueAt(zza)).disable();
                zzlvArr[i] = null;
            }
        }
        boolean z = false;
        for (int i2 = 0; i2 < zzomArr.length; i2++) {
            if (zzlvArr[i2] == null && zzomArr[i2] != null) {
                zzom zzom = zzomArr[i2];
                zzpo.checkState(zzom.length() == 1);
                zzpo.checkState(zzom.zzbd(0) == 0);
                int zza2 = this.zzaxf.zza(zzom.zzgk());
                zzpo.checkState(!this.zzaxg[zza2]);
                this.zzaxe++;
                this.zzaxg[zza2] = true;
                zzlvArr[i2] = new zzlj(this, zza2);
                zArr2[i2] = true;
                z = true;
            }
        }
        if (!this.zzaxc) {
            int size = this.zzawy.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (!this.zzaxg[i3]) {
                    ((zzls) this.zzawy.valueAt(i3)).disable();
                }
            }
        }
        if (this.zzaxe == 0) {
            this.zzaxd = false;
            if (this.zzawt.isLoading()) {
                this.zzawt.zzgy();
            }
        } else if (!this.zzaxc ? j != 0 : z) {
            j = zzab(j);
            for (int i4 = 0; i4 < zzlvArr.length; i4++) {
                if (zzlvArr[i4] != null) {
                    zArr2[i4] = true;
                }
            }
        }
        this.zzaxc = true;
        return j;
    }

    public final boolean zzy(long j) {
        if (this.zzaxm || (this.zzyu && this.zzaxe == 0)) {
            return false;
        }
        boolean zzgz = this.zzawv.zzgz();
        if (!this.zzawt.isLoading()) {
            startLoading();
            zzgz = true;
        }
        return zzgz;
    }

    public final long zzeu() {
        if (this.zzaxe == 0) {
            return Long.MIN_VALUE;
        }
        return zzez();
    }

    public final long zzey() {
        if (!this.zzaxd) {
            return -9223372036854775807L;
        }
        this.zzaxd = false;
        return this.zzaxj;
    }

    public final long zzez() {
        long j;
        if (this.zzaxm) {
            return Long.MIN_VALUE;
        }
        if (zzfd()) {
            return this.zzaxk;
        }
        if (this.zzaxi) {
            j = Long.MAX_VALUE;
            int size = this.zzawy.size();
            for (int i = 0; i < size; i++) {
                if (this.zzaxh[i]) {
                    j = Math.min(j, ((zzls) this.zzawy.valueAt(i)).zzfc());
                }
            }
        } else {
            j = zzfc();
        }
        return j == Long.MIN_VALUE ? this.zzaxj : j;
    }

    public final long zzab(long j) {
        if (!this.zzaxa.zzdw()) {
            j = 0;
        }
        this.zzaxj = j;
        int size = this.zzawy.size();
        boolean z = !zzfd();
        int i = 0;
        while (z && i < size) {
            if (this.zzaxg[i]) {
                z = ((zzls) this.zzawy.valueAt(i)).zze(j, false);
            }
            i++;
        }
        if (!z) {
            this.zzaxk = j;
            this.zzaxm = false;
            if (this.zzawt.isLoading()) {
                this.zzawt.zzgy();
            } else {
                for (int i2 = 0; i2 < size; i2++) {
                    ((zzls) this.zzawy.valueAt(i2)).zzh(this.zzaxg[i2]);
                }
            }
        }
        this.zzaxd = false;
        return j;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzap(int i) {
        return this.zzaxm || (!zzfd() && ((zzls) this.zzawy.valueAt(i)).zzfm());
    }

    /* access modifiers changed from: 0000 */
    public final void zzev() throws IOException {
        this.zzawt.zzbi(Integer.MIN_VALUE);
    }

    /* access modifiers changed from: 0000 */
    public final int zza(int i, zzfu zzfu, zzho zzho, boolean z) {
        if (this.zzaxd || zzfd()) {
            return -3;
        }
        return ((zzls) this.zzawy.valueAt(i)).zza(zzfu, zzho, z, this.zzaxm, this.zzaxj);
    }

    /* access modifiers changed from: 0000 */
    public final void zzd(int i, long j) {
        zzls zzls = (zzls) this.zzawy.valueAt(i);
        if (!this.zzaxm || j <= zzls.zzfc()) {
            zzls.zze(j, true);
        } else {
            zzls.zzfp();
        }
    }

    public final zzii zzb(int i, int i2) {
        zzls zzls = (zzls) this.zzawy.get(i);
        if (zzls != null) {
            return zzls;
        }
        zzls zzls2 = new zzls(this.zzawq);
        zzls2.zza((zzlu) this);
        this.zzawy.put(i, zzls2);
        return zzls2;
    }

    public final void zzdy() {
        this.zzaxb = true;
        this.handler.post(this.zzaww);
    }

    public final void zza(zzig zzig) {
        this.zzaxa = zzig;
        this.handler.post(this.zzaww);
    }

    public final void zzg(zzfs zzfs) {
        this.handler.post(this.zzaww);
    }

    /* access modifiers changed from: private */
    public final void zzfa() {
        if (!this.zzyb && !this.zzyu && this.zzaxa != null && this.zzaxb) {
            int size = this.zzawy.size();
            int i = 0;
            while (i < size) {
                if (((zzls) this.zzawy.valueAt(i)).zzfn() != null) {
                    i++;
                } else {
                    return;
                }
            }
            this.zzawv.zzha();
            zzlz[] zzlzArr = new zzlz[size];
            this.zzaxh = new boolean[size];
            this.zzaxg = new boolean[size];
            this.zzaan = this.zzaxa.getDurationUs();
            int i2 = 0;
            while (true) {
                boolean z = true;
                if (i2 < size) {
                    zzfs zzfn = ((zzls) this.zzawy.valueAt(i2)).zzfn();
                    zzlzArr[i2] = new zzlz(zzfn);
                    String str = zzfn.zzzj;
                    if (!zzpt.zzac(str) && !zzpt.zzab(str)) {
                        z = false;
                    }
                    this.zzaxh[i2] = z;
                    this.zzaxi = z | this.zzaxi;
                    i2++;
                } else {
                    this.zzaxf = new zzma(zzlzArr);
                    this.zzyu = true;
                    this.zzawp.zzb(new zzly(this.zzaan, this.zzaxa.zzdw()), null);
                    this.zzawz.zza(this);
                    return;
                }
            }
        }
    }

    private final void zza(zzlh zzlh) {
        if (this.zzcc == -1) {
            this.zzcc = zzlh.zzcc;
        }
    }

    private final void startLoading() {
        zzlh zzlh = new zzlh(this, this.uri, this.zzagy, this.zzawu, this.zzawv);
        if (this.zzyu) {
            zzpo.checkState(zzfd());
            long j = this.zzaan;
            if (j == -9223372036854775807L || this.zzaxk < j) {
                zzlh.zze(this.zzaxa.zzr(this.zzaxk), this.zzaxk);
                this.zzaxk = -9223372036854775807L;
            } else {
                this.zzaxm = true;
                this.zzaxk = -9223372036854775807L;
                return;
            }
        }
        this.zzaxl = zzfb();
        int i = this.zzawn;
        if (i == -1) {
            if (this.zzyu && this.zzcc == -1) {
                zzig zzig = this.zzaxa;
                if (zzig == null || zzig.getDurationUs() == -9223372036854775807L) {
                    i = 6;
                }
            }
            i = 3;
        }
        this.zzawt.zza(zzlh, this, i);
    }

    private final int zzfb() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzawy.size(); i2++) {
            i += ((zzls) this.zzawy.valueAt(i2)).zzfk();
        }
        return i;
    }

    private final long zzfc() {
        int size = this.zzawy.size();
        long j = Long.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            j = Math.max(j, ((zzls) this.zzawy.valueAt(i)).zzfc());
        }
        return j;
    }

    private final boolean zzfd() {
        return this.zzaxk != -9223372036854775807L;
    }

    public final /* synthetic */ int zza(zzpi zzpi, long j, long j2, IOException iOException) {
        zzlh zzlh = (zzlh) zzpi;
        zza(zzlh);
        Handler handler2 = this.zzwx;
        if (!(handler2 == null || this.zzawo == null)) {
            handler2.post(new zzlg(this, iOException));
        }
        if (iOException instanceof zzmb) {
            return 3;
        }
        boolean z = zzfb() > this.zzaxl;
        if (this.zzcc == -1) {
            zzig zzig = this.zzaxa;
            if (zzig == null || zzig.getDurationUs() == -9223372036854775807L) {
                this.zzaxj = 0;
                this.zzaxd = this.zzyu;
                int size = this.zzawy.size();
                for (int i = 0; i < size; i++) {
                    ((zzls) this.zzawy.valueAt(i)).zzh(!this.zzyu || this.zzaxg[i]);
                }
                zzlh.zze(0, 0);
            }
        }
        this.zzaxl = zzfb();
        return z ? 1 : 0;
    }

    public final /* synthetic */ void zza(zzpi zzpi, long j, long j2, boolean z) {
        zza((zzlh) zzpi);
        if (!z && this.zzaxe > 0) {
            int size = this.zzawy.size();
            for (int i = 0; i < size; i++) {
                ((zzls) this.zzawy.valueAt(i)).zzh(this.zzaxg[i]);
            }
            this.zzawz.zza(this);
        }
    }

    public final /* synthetic */ void zza(zzpi zzpi, long j, long j2) {
        zza((zzlh) zzpi);
        this.zzaxm = true;
        if (this.zzaan == -9223372036854775807L) {
            long zzfc = zzfc();
            this.zzaan = zzfc == Long.MIN_VALUE ? 0 : zzfc + 10000;
            this.zzawp.zzb(new zzly(this.zzaan, this.zzaxa.zzdw()), null);
        }
        this.zzawz.zza(this);
    }
}
