package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzfp;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.common.net.HttpHeaders;
import com.mopub.common.Constants;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzfn implements zzct {
    private static volatile zzfn zzati;
    private final zzbw zzada;
    private zzbq zzatj;
    private zzaw zzatk;
    private zzt zzatl;
    private zzbb zzatm;
    private zzfj zzatn;
    private zzm zzato;
    private final zzft zzatp;
    private zzdv zzatq;
    private boolean zzatr;
    private boolean zzats;
    @VisibleForTesting
    private long zzatt;
    private List<Runnable> zzatu;
    private int zzatv;
    private int zzatw;
    private boolean zzatx;
    private boolean zzaty;
    private boolean zzatz;
    private FileLock zzaua;
    private FileChannel zzaub;
    private List<Long> zzauc;
    private List<Long> zzaud;
    private long zzaue;
    private boolean zzvz;

    class zza implements zzv {
        zzfw zzaui;
        List<Long> zzauj;
        List<zzft> zzauk;
        private long zzaul;

        private zza() {
        }

        public final void zzb(zzfw zzfw) {
            Preconditions.checkNotNull(zzfw);
            this.zzaui = zzfw;
        }

        public final boolean zza(long j, zzft zzft) {
            Preconditions.checkNotNull(zzft);
            if (this.zzauk == null) {
                this.zzauk = new ArrayList();
            }
            if (this.zzauj == null) {
                this.zzauj = new ArrayList();
            }
            if (this.zzauk.size() > 0 && zza((zzft) this.zzauk.get(0)) != zza(zzft)) {
                return false;
            }
            long zzvx = this.zzaul + ((long) zzft.zzvx());
            if (zzvx >= ((long) Math.max(0, ((Integer) zzai.zzajc.get()).intValue()))) {
                return false;
            }
            this.zzaul = zzvx;
            this.zzauk.add(zzft);
            this.zzauj.add(Long.valueOf(j));
            if (this.zzauk.size() >= Math.max(1, ((Integer) zzai.zzajd.get()).intValue())) {
                return false;
            }
            return true;
        }

        private static long zza(zzft zzft) {
            return ((zzft.zzaxd.longValue() / 1000) / 60) / 60;
        }

        /* synthetic */ zza(zzfn zzfn, zzfo zzfo) {
            this();
        }
    }

    public static zzfn zzn(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzati == null) {
            synchronized (zzfn.class) {
                if (zzati == null) {
                    zzati = new zzfn(new zzfs(context));
                }
            }
        }
        return zzati;
    }

    private zzfn(zzfs zzfs) {
        this(zzfs, null);
    }

    private zzfn(zzfs zzfs, zzbw zzbw) {
        this.zzvz = false;
        Preconditions.checkNotNull(zzfs);
        this.zzada = zzbw.zza(zzfs.zzri, (zzan) null);
        this.zzaue = -1;
        zzft zzft = new zzft(this);
        zzft.zzq();
        this.zzatp = zzft;
        zzaw zzaw = new zzaw(this);
        zzaw.zzq();
        this.zzatk = zzaw;
        zzbq zzbq = new zzbq(this);
        zzbq.zzq();
        this.zzatj = zzbq;
        this.zzada.zzgs().zzc((Runnable) new zzfo(this, zzfs));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzfs zzfs) {
        this.zzada.zzgs().zzaf();
        zzt zzt = new zzt(this);
        zzt.zzq();
        this.zzatl = zzt;
        this.zzada.zzgv().zza((zzs) this.zzatj);
        zzm zzm = new zzm(this);
        zzm.zzq();
        this.zzato = zzm;
        zzdv zzdv = new zzdv(this);
        zzdv.zzq();
        this.zzatq = zzdv;
        zzfj zzfj = new zzfj(this);
        zzfj.zzq();
        this.zzatn = zzfj;
        this.zzatm = new zzbb(this);
        if (this.zzatv != this.zzatw) {
            this.zzada.zzgt().zzjg().zze("Not all upload components initialized", Integer.valueOf(this.zzatv), Integer.valueOf(this.zzatw));
        }
        this.zzvz = true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void start() {
        this.zzada.zzgs().zzaf();
        zzjt().zzij();
        if (this.zzada.zzgu().zzanc.get() == 0) {
            this.zzada.zzgu().zzanc.set(this.zzada.zzbx().currentTimeMillis());
        }
        zzmb();
    }

    public final zzn zzgw() {
        return this.zzada.zzgw();
    }

    public final zzq zzgv() {
        return this.zzada.zzgv();
    }

    public final zzas zzgt() {
        return this.zzada.zzgt();
    }

    public final zzbr zzgs() {
        return this.zzada.zzgs();
    }

    private final zzbq zzls() {
        zza((zzfm) this.zzatj);
        return this.zzatj;
    }

    public final zzaw zzlt() {
        zza((zzfm) this.zzatk);
        return this.zzatk;
    }

    public final zzt zzjt() {
        zza((zzfm) this.zzatl);
        return this.zzatl;
    }

    private final zzbb zzlu() {
        zzbb zzbb = this.zzatm;
        if (zzbb != null) {
            return zzbb;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzfj zzlv() {
        zza((zzfm) this.zzatn);
        return this.zzatn;
    }

    public final zzm zzjs() {
        zza((zzfm) this.zzato);
        return this.zzato;
    }

    public final zzdv zzlw() {
        zza((zzfm) this.zzatq);
        return this.zzatq;
    }

    public final zzft zzjr() {
        zza((zzfm) this.zzatp);
        return this.zzatp;
    }

    public final zzaq zzgq() {
        return this.zzada.zzgq();
    }

    public final Context getContext() {
        return this.zzada.getContext();
    }

    public final Clock zzbx() {
        return this.zzada.zzbx();
    }

    public final zzfx zzgr() {
        return this.zzada.zzgr();
    }

    @WorkerThread
    private final void zzaf() {
        this.zzada.zzgs().zzaf();
    }

    /* access modifiers changed from: 0000 */
    public final void zzlx() {
        if (!this.zzvz) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zza(zzfm zzfm) {
        if (zzfm == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzfm.isInitialized()) {
            String valueOf = String.valueOf(zzfm.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zze(zzk zzk) {
        zzaf();
        zzlx();
        Preconditions.checkNotEmpty(zzk.packageName);
        zzg(zzk);
    }

    private final long zzly() {
        long currentTimeMillis = this.zzada.zzbx().currentTimeMillis();
        zzbd zzgu = this.zzada.zzgu();
        zzgu.zzcl();
        zzgu.zzaf();
        long j = zzgu.zzang.get();
        if (j == 0) {
            j = 1 + ((long) zzgu.zzgr().zzmk().nextInt(86400000));
            zzgu.zzang.set(j);
        }
        return ((((currentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzd(zzag zzag, String str) {
        zzag zzag2 = zzag;
        String str2 = str;
        zzg zzbm = zzjt().zzbm(str2);
        if (zzbm == null || TextUtils.isEmpty(zzbm.zzak())) {
            this.zzada.zzgt().zzjn().zzg("No app data available; dropping event", str2);
            return;
        }
        Boolean zzc = zzc(zzbm);
        if (zzc == null) {
            if (!"_ui".equals(zzag2.name)) {
                this.zzada.zzgt().zzjj().zzg("Could not find package. appId", zzas.zzbw(str));
            }
        } else if (!zzc.booleanValue()) {
            this.zzada.zzgt().zzjg().zzg("App version does not match; dropping event. appId", zzas.zzbw(str));
            return;
        }
        zzk zzk = r2;
        zzg zzg = zzbm;
        zzk zzk2 = new zzk(str, zzbm.getGmpAppId(), zzbm.zzak(), zzbm.zzhf(), zzbm.zzhg(), zzbm.zzhh(), zzbm.zzhi(), (String) null, zzbm.isMeasurementEnabled(), false, zzg.getFirebaseInstanceId(), zzg.zzhv(), 0, 0, zzg.zzhw(), zzg.zzhx(), false, zzg.zzhb());
        zzc(zzag2, zzk);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzc(zzag zzag, zzk zzk) {
        List<zzo> list;
        List<zzo> list2;
        List<zzo> list3;
        zzag zzag2 = zzag;
        zzk zzk2 = zzk;
        Preconditions.checkNotNull(zzk);
        Preconditions.checkNotEmpty(zzk2.packageName);
        zzaf();
        zzlx();
        String str = zzk2.packageName;
        long j = zzag2.zzaig;
        if (zzjr().zze(zzag2, zzk2)) {
            if (!zzk2.zzafr) {
                zzg(zzk2);
                return;
            }
            zzjt().beginTransaction();
            try {
                zzt zzjt = zzjt();
                Preconditions.checkNotEmpty(str);
                zzjt.zzaf();
                zzjt.zzcl();
                int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                if (i < 0) {
                    zzjt.zzgt().zzjj().zze("Invalid time querying timed out conditional properties", zzas.zzbw(str), Long.valueOf(j));
                    list = Collections.emptyList();
                } else {
                    list = zzjt.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzo zzo : list) {
                    if (zzo != null) {
                        this.zzada.zzgt().zzjn().zzd("User property timed out", zzo.packageName, this.zzada.zzgq().zzbv(zzo.zzags.name), zzo.zzags.getValue());
                        if (zzo.zzagt != null) {
                            zzd(new zzag(zzo.zzagt, j), zzk2);
                        }
                        zzjt().zzk(str, zzo.zzags.name);
                    }
                }
                zzt zzjt2 = zzjt();
                Preconditions.checkNotEmpty(str);
                zzjt2.zzaf();
                zzjt2.zzcl();
                if (i < 0) {
                    zzjt2.zzgt().zzjj().zze("Invalid time querying expired conditional properties", zzas.zzbw(str), Long.valueOf(j));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zzjt2.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzo zzo2 : list2) {
                    if (zzo2 != null) {
                        this.zzada.zzgt().zzjn().zzd("User property expired", zzo2.packageName, this.zzada.zzgq().zzbv(zzo2.zzags.name), zzo2.zzags.getValue());
                        zzjt().zzh(str, zzo2.zzags.name);
                        if (zzo2.zzagv != null) {
                            arrayList.add(zzo2.zzagv);
                        }
                        zzjt().zzk(str, zzo2.zzags.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList2.get(i2);
                    i2++;
                    zzd(new zzag((zzag) obj, j), zzk2);
                }
                zzt zzjt3 = zzjt();
                String str2 = zzag2.name;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zzjt3.zzaf();
                zzjt3.zzcl();
                if (i < 0) {
                    zzjt3.zzgt().zzjj().zzd("Invalid time querying triggered conditional properties", zzas.zzbw(str), zzjt3.zzgq().zzbt(str2), Long.valueOf(j));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zzjt3.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                for (zzo zzo3 : list3) {
                    if (zzo3 != null) {
                        zzfu zzfu = zzo3.zzags;
                        zzfw zzfw = r4;
                        zzfw zzfw2 = new zzfw(zzo3.packageName, zzo3.origin, zzfu.name, j, zzfu.getValue());
                        if (zzjt().zza(zzfw)) {
                            this.zzada.zzgt().zzjn().zzd("User property triggered", zzo3.packageName, this.zzada.zzgq().zzbv(zzfw.name), zzfw.value);
                        } else {
                            this.zzada.zzgt().zzjg().zzd("Too many active user properties, ignoring", zzas.zzbw(zzo3.packageName), this.zzada.zzgq().zzbv(zzfw.name), zzfw.value);
                        }
                        if (zzo3.zzagu != null) {
                            arrayList3.add(zzo3.zzagu);
                        }
                        zzo3.zzags = new zzfu(zzfw);
                        zzo3.active = true;
                        zzjt().zza(zzo3);
                    }
                }
                zzd(zzag, zzk);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i3 = 0;
                while (i3 < size2) {
                    Object obj2 = arrayList4.get(i3);
                    i3++;
                    zzd(new zzag((zzag) obj2, j), zzk2);
                }
                zzjt().setTransactionSuccessful();
            } finally {
                zzjt().endTransaction();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:194:0x0780 A[Catch:{ SQLiteException -> 0x0224, all -> 0x07f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x07ad A[Catch:{ SQLiteException -> 0x0224, all -> 0x07f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0257 A[Catch:{ SQLiteException -> 0x0224, all -> 0x07f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x028a A[Catch:{ SQLiteException -> 0x0224, all -> 0x07f2 }] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzd(com.google.android.gms.measurement.internal.zzag r25, com.google.android.gms.measurement.internal.zzk r26) {
        /*
            r24 = this;
            r1 = r24
            r2 = r25
            r3 = r26
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r26)
            java.lang.String r0 = r3.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            long r4 = java.lang.System.nanoTime()
            r24.zzaf()
            r24.zzlx()
            java.lang.String r15 = r3.packageName
            com.google.android.gms.measurement.internal.zzft r0 = r24.zzjr()
            boolean r0 = r0.zze(r2, r3)
            if (r0 != 0) goto L_0x0025
            return
        L_0x0025:
            boolean r0 = r3.zzafr
            if (r0 != 0) goto L_0x002d
            r1.zzg(r3)
            return
        L_0x002d:
            com.google.android.gms.measurement.internal.zzbq r0 = r24.zzls()
            java.lang.String r6 = r2.name
            boolean r0 = r0.zzo(r15, r6)
            r14 = 0
            r21 = 1
            if (r0 == 0) goto L_0x00d7
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjj()
            java.lang.String r3 = "Dropping blacklisted event. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzas.zzbw(r15)
            com.google.android.gms.measurement.internal.zzbw r5 = r1.zzada
            com.google.android.gms.measurement.internal.zzaq r5 = r5.zzgq()
            java.lang.String r6 = r2.name
            java.lang.String r5 = r5.zzbt(r6)
            r0.zze(r3, r4, r5)
            com.google.android.gms.measurement.internal.zzbq r0 = r24.zzls()
            boolean r0 = r0.zzcl(r15)
            if (r0 != 0) goto L_0x006f
            com.google.android.gms.measurement.internal.zzbq r0 = r24.zzls()
            boolean r0 = r0.zzcm(r15)
            if (r0 == 0) goto L_0x0070
        L_0x006f:
            r14 = 1
        L_0x0070:
            if (r14 != 0) goto L_0x008d
            java.lang.String r0 = "_err"
            java.lang.String r3 = r2.name
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x008d
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada
            com.google.android.gms.measurement.internal.zzfx r6 = r0.zzgr()
            r8 = 11
            java.lang.String r9 = "_ev"
            java.lang.String r10 = r2.name
            r11 = 0
            r7 = r15
            r6.zza(r7, r8, r9, r10, r11)
        L_0x008d:
            if (r14 == 0) goto L_0x00d6
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()
            com.google.android.gms.measurement.internal.zzg r0 = r0.zzbm(r15)
            if (r0 == 0) goto L_0x00d6
            long r2 = r0.zzhl()
            long r4 = r0.zzhk()
            long r2 = java.lang.Math.max(r2, r4)
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada
            com.google.android.gms.common.util.Clock r4 = r4.zzbx()
            long r4 = r4.currentTimeMillis()
            long r4 = r4 - r2
            long r2 = java.lang.Math.abs(r4)
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Long> r4 = com.google.android.gms.measurement.internal.zzai.zzajt
            java.lang.Object r4 = r4.get()
            java.lang.Long r4 = (java.lang.Long) r4
            long r4 = r4.longValue()
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x00d6
            com.google.android.gms.measurement.internal.zzbw r2 = r1.zzada
            com.google.android.gms.measurement.internal.zzas r2 = r2.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjn()
            java.lang.String r3 = "Fetching config for blacklisted app"
            r2.zzby(r3)
            r1.zzb(r0)
        L_0x00d6:
            return
        L_0x00d7:
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()
            r13 = 2
            boolean r0 = r0.isLoggable(r13)
            if (r0 == 0) goto L_0x00fd
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjo()
            java.lang.String r6 = "Logging event"
            com.google.android.gms.measurement.internal.zzbw r7 = r1.zzada
            com.google.android.gms.measurement.internal.zzaq r7 = r7.zzgq()
            java.lang.String r7 = r7.zzb(r2)
            r0.zzg(r6, r7)
        L_0x00fd:
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()
            r0.beginTransaction()
            r1.zzg(r3)     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = "_iap"
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x07f2 }
            boolean r0 = r0.equals(r6)     // Catch:{ all -> 0x07f2 }
            if (r0 != 0) goto L_0x011b
            java.lang.String r0 = "ecommerce_purchase"
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x07f2 }
            boolean r0 = r0.equals(r6)     // Catch:{ all -> 0x07f2 }
            if (r0 == 0) goto L_0x0299
        L_0x011b:
            com.google.android.gms.measurement.internal.zzad r0 = r2.zzahu     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = "currency"
            java.lang.String r0 = r0.getString(r6)     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = "ecommerce_purchase"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x07f2 }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x07f2 }
            if (r6 == 0) goto L_0x0182
            com.google.android.gms.measurement.internal.zzad r6 = r2.zzahu     // Catch:{ all -> 0x07f2 }
            java.lang.String r7 = "value"
            java.lang.Double r6 = r6.zzbr(r7)     // Catch:{ all -> 0x07f2 }
            double r6 = r6.doubleValue()     // Catch:{ all -> 0x07f2 }
            r8 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r6 = r6 * r8
            r10 = 0
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x0157
            com.google.android.gms.measurement.internal.zzad r6 = r2.zzahu     // Catch:{ all -> 0x07f2 }
            java.lang.String r7 = "value"
            java.lang.Long r6 = r6.getLong(r7)     // Catch:{ all -> 0x07f2 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x07f2 }
            double r6 = (double) r6     // Catch:{ all -> 0x07f2 }
            double r6 = r6 * r8
        L_0x0157:
            r8 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 > 0) goto L_0x0168
            r8 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 < 0) goto L_0x0168
            long r6 = java.lang.Math.round(r6)     // Catch:{ all -> 0x07f2 }
            goto L_0x018f
        L_0x0168:
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjj()     // Catch:{ all -> 0x07f2 }
            java.lang.String r8 = "Data lost. Currency value is too big. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzas.zzbw(r15)     // Catch:{ all -> 0x07f2 }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ all -> 0x07f2 }
            r0.zze(r8, r9, r6)     // Catch:{ all -> 0x07f2 }
            r0 = 0
            goto L_0x0288
        L_0x0182:
            com.google.android.gms.measurement.internal.zzad r6 = r2.zzahu     // Catch:{ all -> 0x07f2 }
            java.lang.String r7 = "value"
            java.lang.Long r6 = r6.getLong(r7)     // Catch:{ all -> 0x07f2 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x07f2 }
        L_0x018f:
            boolean r8 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x07f2 }
            if (r8 != 0) goto L_0x0287
            java.util.Locale r8 = java.util.Locale.US     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = r0.toUpperCase(r8)     // Catch:{ all -> 0x07f2 }
            java.lang.String r8 = "[A-Z]{3}"
            boolean r8 = r0.matches(r8)     // Catch:{ all -> 0x07f2 }
            if (r8 == 0) goto L_0x0287
            java.lang.String r8 = "_ltv_"
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x07f2 }
            int r9 = r0.length()     // Catch:{ all -> 0x07f2 }
            if (r9 == 0) goto L_0x01b8
            java.lang.String r0 = r8.concat(r0)     // Catch:{ all -> 0x07f2 }
            goto L_0x01bd
        L_0x01b8:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x07f2 }
            r0.<init>(r8)     // Catch:{ all -> 0x07f2 }
        L_0x01bd:
            r9 = r0
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzi(r15, r9)     // Catch:{ all -> 0x07f2 }
            if (r0 == 0) goto L_0x01f2
            java.lang.Object r8 = r0.value     // Catch:{ all -> 0x07f2 }
            boolean r8 = r8 instanceof java.lang.Long     // Catch:{ all -> 0x07f2 }
            if (r8 != 0) goto L_0x01cf
            goto L_0x01f2
        L_0x01cf:
            java.lang.Object r0 = r0.value     // Catch:{ all -> 0x07f2 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x07f2 }
            long r10 = r0.longValue()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfw r0 = new com.google.android.gms.measurement.internal.zzfw     // Catch:{ all -> 0x07f2 }
            java.lang.String r8 = r2.origin     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r12 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.common.util.Clock r12 = r12.zzbx()     // Catch:{ all -> 0x07f2 }
            long r16 = r12.currentTimeMillis()     // Catch:{ all -> 0x07f2 }
            long r10 = r10 + r6
            java.lang.Long r12 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x07f2 }
            r6 = r0
            r7 = r15
            r10 = r16
            r6.<init>(r7, r8, r9, r10, r12)     // Catch:{ all -> 0x07f2 }
            goto L_0x024d
        L_0x01f2:
            com.google.android.gms.measurement.internal.zzt r8 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzq r0 = r0.zzgv()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Integer> r10 = com.google.android.gms.measurement.internal.zzai.zzajy     // Catch:{ all -> 0x07f2 }
            int r0 = r0.zzb(r15, r10)     // Catch:{ all -> 0x07f2 }
            int r0 = r0 + -1
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)     // Catch:{ all -> 0x07f2 }
            r8.zzaf()     // Catch:{ all -> 0x07f2 }
            r8.zzcl()     // Catch:{ all -> 0x07f2 }
            android.database.sqlite.SQLiteDatabase r10 = r8.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0224 }
            java.lang.String r11 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);"
            r12 = 3
            java.lang.String[] r12 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x0224 }
            r12[r14] = r15     // Catch:{ SQLiteException -> 0x0224 }
            r12[r21] = r15     // Catch:{ SQLiteException -> 0x0224 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ SQLiteException -> 0x0224 }
            r12[r13] = r0     // Catch:{ SQLiteException -> 0x0224 }
            r10.execSQL(r11, r12)     // Catch:{ SQLiteException -> 0x0224 }
            goto L_0x0236
        L_0x0224:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzas r8 = r8.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r8 = r8.zzjg()     // Catch:{ all -> 0x07f2 }
            java.lang.String r10 = "Error pruning currencies. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzas.zzbw(r15)     // Catch:{ all -> 0x07f2 }
            r8.zze(r10, r11, r0)     // Catch:{ all -> 0x07f2 }
        L_0x0236:
            com.google.android.gms.measurement.internal.zzfw r0 = new com.google.android.gms.measurement.internal.zzfw     // Catch:{ all -> 0x07f2 }
            java.lang.String r8 = r2.origin     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r10 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.common.util.Clock r10 = r10.zzbx()     // Catch:{ all -> 0x07f2 }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x07f2 }
            java.lang.Long r12 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x07f2 }
            r6 = r0
            r7 = r15
            r6.<init>(r7, r8, r9, r10, r12)     // Catch:{ all -> 0x07f2 }
        L_0x024d:
            com.google.android.gms.measurement.internal.zzt r6 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            boolean r6 = r6.zza(r0)     // Catch:{ all -> 0x07f2 }
            if (r6 != 0) goto L_0x0287
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r6 = r6.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r6 = r6.zzjg()     // Catch:{ all -> 0x07f2 }
            java.lang.String r7 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzas.zzbw(r15)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r9 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzaq r9 = r9.zzgq()     // Catch:{ all -> 0x07f2 }
            java.lang.String r10 = r0.name     // Catch:{ all -> 0x07f2 }
            java.lang.String r9 = r9.zzbv(r10)     // Catch:{ all -> 0x07f2 }
            java.lang.Object r0 = r0.value     // Catch:{ all -> 0x07f2 }
            r6.zzd(r7, r8, r9, r0)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfx r6 = r0.zzgr()     // Catch:{ all -> 0x07f2 }
            r8 = 9
            r9 = 0
            r10 = 0
            r11 = 0
            r7 = r15
            r6.zza(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x07f2 }
        L_0x0287:
            r0 = 1
        L_0x0288:
            if (r0 != 0) goto L_0x0299
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()
            r0.endTransaction()
            return
        L_0x0299:
            java.lang.String r0 = r2.name     // Catch:{ all -> 0x07f2 }
            boolean r0 = com.google.android.gms.measurement.internal.zzfx.zzct(r0)     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = "_err"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x07f2 }
            boolean r16 = r6.equals(r7)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r6 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            long r7 = r24.zzly()     // Catch:{ all -> 0x07f2 }
            r10 = 1
            r12 = 0
            r17 = 0
            r9 = r15
            r11 = r0
            r13 = r16
            r22 = r4
            r4 = 0
            r14 = r17
            com.google.android.gms.measurement.internal.zzu r5 = r6.zza(r7, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x07f2 }
            long r6 = r5.zzahi     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Integer> r8 = com.google.android.gms.measurement.internal.zzai.zzaje     // Catch:{ all -> 0x07f2 }
            java.lang.Object r8 = r8.get()     // Catch:{ all -> 0x07f2 }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ all -> 0x07f2 }
            int r8 = r8.intValue()     // Catch:{ all -> 0x07f2 }
            long r8 = (long) r8     // Catch:{ all -> 0x07f2 }
            long r6 = r6 - r8
            r8 = 1000(0x3e8, double:4.94E-321)
            r10 = 1
            r13 = 0
            int r12 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r12 <= 0) goto L_0x0307
            long r6 = r6 % r8
            int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r0 != 0) goto L_0x02f8
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ all -> 0x07f2 }
            java.lang.String r2 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzas.zzbw(r15)     // Catch:{ all -> 0x07f2 }
            long r4 = r5.zzahi     // Catch:{ all -> 0x07f2 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x07f2 }
            r0.zze(r2, r3, r4)     // Catch:{ all -> 0x07f2 }
        L_0x02f8:
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()
            r0.endTransaction()
            return
        L_0x0307:
            if (r0 == 0) goto L_0x0361
            long r6 = r5.zzahh     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzai.zzajg     // Catch:{ all -> 0x07f2 }
            java.lang.Object r12 = r12.get()     // Catch:{ all -> 0x07f2 }
            java.lang.Integer r12 = (java.lang.Integer) r12     // Catch:{ all -> 0x07f2 }
            int r12 = r12.intValue()     // Catch:{ all -> 0x07f2 }
            r17 = r5
            long r4 = (long) r12     // Catch:{ all -> 0x07f2 }
            long r6 = r6 - r4
            int r4 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r4 <= 0) goto L_0x035f
            long r6 = r6 % r8
            int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r0 != 0) goto L_0x033f
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ all -> 0x07f2 }
            java.lang.String r3 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzas.zzbw(r15)     // Catch:{ all -> 0x07f2 }
            r5 = r17
            long r5 = r5.zzahh     // Catch:{ all -> 0x07f2 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x07f2 }
            r0.zze(r3, r4, r5)     // Catch:{ all -> 0x07f2 }
        L_0x033f:
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfx r6 = r0.zzgr()     // Catch:{ all -> 0x07f2 }
            r8 = 16
            java.lang.String r9 = "_ev"
            java.lang.String r10 = r2.name     // Catch:{ all -> 0x07f2 }
            r11 = 0
            r7 = r15
            r6.zza(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()
            r0.endTransaction()
            return
        L_0x035f:
            r5 = r17
        L_0x0361:
            if (r16 == 0) goto L_0x03b1
            long r6 = r5.zzahk     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzq r4 = r4.zzgv()     // Catch:{ all -> 0x07f2 }
            java.lang.String r8 = r3.packageName     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Integer> r9 = com.google.android.gms.measurement.internal.zzai.zzajf     // Catch:{ all -> 0x07f2 }
            int r4 = r4.zzb(r8, r9)     // Catch:{ all -> 0x07f2 }
            r8 = 1000000(0xf4240, float:1.401298E-39)
            int r4 = java.lang.Math.min(r8, r4)     // Catch:{ all -> 0x07f2 }
            r12 = 0
            int r4 = java.lang.Math.max(r12, r4)     // Catch:{ all -> 0x07f2 }
            long r8 = (long) r4     // Catch:{ all -> 0x07f2 }
            long r6 = r6 - r8
            int r4 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r4 <= 0) goto L_0x03b2
            int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r0 != 0) goto L_0x03a2
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ all -> 0x07f2 }
            java.lang.String r2 = "Too many error events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzas.zzbw(r15)     // Catch:{ all -> 0x07f2 }
            long r4 = r5.zzahk     // Catch:{ all -> 0x07f2 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x07f2 }
            r0.zze(r2, r3, r4)     // Catch:{ all -> 0x07f2 }
        L_0x03a2:
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()
            r0.endTransaction()
            return
        L_0x03b1:
            r12 = 0
        L_0x03b2:
            com.google.android.gms.measurement.internal.zzad r4 = r2.zzahu     // Catch:{ all -> 0x07f2 }
            android.os.Bundle r4 = r4.zziy()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r5 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfx r5 = r5.zzgr()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = "_o"
            java.lang.String r7 = r2.origin     // Catch:{ all -> 0x07f2 }
            r5.zza(r4, r6, r7)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r5 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfx r5 = r5.zzgr()     // Catch:{ all -> 0x07f2 }
            boolean r5 = r5.zzcz(r15)     // Catch:{ all -> 0x07f2 }
            if (r5 == 0) goto L_0x03ef
            com.google.android.gms.measurement.internal.zzbw r5 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfx r5 = r5.zzgr()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = "_dbg"
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x07f2 }
            r5.zza(r4, r6, r7)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r5 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfx r5 = r5.zzgr()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = "_r"
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x07f2 }
            r5.zza(r4, r6, r7)     // Catch:{ all -> 0x07f2 }
        L_0x03ef:
            com.google.android.gms.measurement.internal.zzbw r5 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzq r5 = r5.zzgv()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x07f2 }
            boolean r5 = r5.zzbh(r6)     // Catch:{ all -> 0x07f2 }
            if (r5 == 0) goto L_0x0428
            java.lang.String r5 = "_s"
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x07f2 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x07f2 }
            if (r5 == 0) goto L_0x0428
            com.google.android.gms.measurement.internal.zzt r5 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x07f2 }
            java.lang.String r7 = "_sno"
            com.google.android.gms.measurement.internal.zzfw r5 = r5.zzi(r6, r7)     // Catch:{ all -> 0x07f2 }
            if (r5 == 0) goto L_0x0428
            java.lang.Object r6 = r5.value     // Catch:{ all -> 0x07f2 }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ all -> 0x07f2 }
            if (r6 == 0) goto L_0x0428
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfx r6 = r6.zzgr()     // Catch:{ all -> 0x07f2 }
            java.lang.String r7 = "_sno"
            java.lang.Object r5 = r5.value     // Catch:{ all -> 0x07f2 }
            r6.zza(r4, r7, r5)     // Catch:{ all -> 0x07f2 }
        L_0x0428:
            com.google.android.gms.measurement.internal.zzt r5 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            long r5 = r5.zzbn(r15)     // Catch:{ all -> 0x07f2 }
            int r7 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r7 <= 0) goto L_0x044b
            com.google.android.gms.measurement.internal.zzbw r7 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r7 = r7.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r7 = r7.zzjj()     // Catch:{ all -> 0x07f2 }
            java.lang.String r8 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzas.zzbw(r15)     // Catch:{ all -> 0x07f2 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x07f2 }
            r7.zze(r8, r9, r5)     // Catch:{ all -> 0x07f2 }
        L_0x044b:
            com.google.android.gms.measurement.internal.zzab r5 = new com.google.android.gms.measurement.internal.zzab     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r7 = r1.zzada     // Catch:{ all -> 0x07f2 }
            java.lang.String r8 = r2.origin     // Catch:{ all -> 0x07f2 }
            java.lang.String r10 = r2.name     // Catch:{ all -> 0x07f2 }
            long r12 = r2.zzaig     // Catch:{ all -> 0x07f2 }
            r18 = 0
            r6 = r5
            r9 = r15
            r2 = 0
            r11 = r12
            r13 = r18
            r2 = r15
            r15 = r4
            r6.<init>(r7, r8, r9, r10, r11, r13, r15)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r4 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = r5.name     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzac r4 = r4.zzg(r2, r6)     // Catch:{ all -> 0x07f2 }
            if (r4 != 0) goto L_0x04d4
            com.google.android.gms.measurement.internal.zzt r4 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            long r6 = r4.zzbq(r2)     // Catch:{ all -> 0x07f2 }
            r8 = 500(0x1f4, double:2.47E-321)
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 < 0) goto L_0x04ba
            if (r0 == 0) goto L_0x04ba
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ all -> 0x07f2 }
            java.lang.String r3 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzas.zzbw(r2)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzaq r6 = r6.zzgq()     // Catch:{ all -> 0x07f2 }
            java.lang.String r5 = r5.name     // Catch:{ all -> 0x07f2 }
            java.lang.String r5 = r6.zzbt(r5)     // Catch:{ all -> 0x07f2 }
            r6 = 500(0x1f4, float:7.0E-43)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x07f2 }
            r0.zzd(r3, r4, r5, r6)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfx r6 = r0.zzgr()     // Catch:{ all -> 0x07f2 }
            r8 = 8
            r9 = 0
            r10 = 0
            r11 = 0
            r7 = r2
            r6.zza(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()
            r0.endTransaction()
            return
        L_0x04ba:
            com.google.android.gms.measurement.internal.zzac r0 = new com.google.android.gms.measurement.internal.zzac     // Catch:{ all -> 0x07f2 }
            java.lang.String r8 = r5.name     // Catch:{ all -> 0x07f2 }
            r9 = 0
            r11 = 0
            long r13 = r5.timestamp     // Catch:{ all -> 0x07f2 }
            r15 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r6 = r0
            r7 = r2
            r6.<init>(r7, r8, r9, r11, r13, r15, r17, r18, r19, r20)     // Catch:{ all -> 0x07f2 }
            goto L_0x04e2
        L_0x04d4:
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            long r6 = r4.zzahx     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzab r5 = r5.zza(r0, r6)     // Catch:{ all -> 0x07f2 }
            long r6 = r5.timestamp     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzac r0 = r4.zzae(r6)     // Catch:{ all -> 0x07f2 }
        L_0x04e2:
            com.google.android.gms.measurement.internal.zzt r2 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            r2.zza(r0)     // Catch:{ all -> 0x07f2 }
            r24.zzaf()     // Catch:{ all -> 0x07f2 }
            r24.zzlx()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r26)     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = r5.zztt     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = r5.zztt     // Catch:{ all -> 0x07f2 }
            java.lang.String r2 = r3.packageName     // Catch:{ all -> 0x07f2 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r0)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.internal.measurement.zzfw r2 = new com.google.android.gms.internal.measurement.zzfw     // Catch:{ all -> 0x07f2 }
            r2.<init>()     // Catch:{ all -> 0x07f2 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r21)     // Catch:{ all -> 0x07f2 }
            r2.zzaxj = r0     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = "android"
            r2.zzaxr = r0     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = r3.packageName     // Catch:{ all -> 0x07f2 }
            r2.zztt = r0     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = r3.zzafp     // Catch:{ all -> 0x07f2 }
            r2.zzafp = r0     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = r3.zzts     // Catch:{ all -> 0x07f2 }
            r2.zzts = r0     // Catch:{ all -> 0x07f2 }
            long r6 = r3.zzafo     // Catch:{ all -> 0x07f2 }
            r8 = -2147483648(0xffffffff80000000, double:NaN)
            r0 = 0
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 != 0) goto L_0x052c
            r4 = r0
            goto L_0x0533
        L_0x052c:
            long r6 = r3.zzafo     // Catch:{ all -> 0x07f2 }
            int r4 = (int) r6     // Catch:{ all -> 0x07f2 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x07f2 }
        L_0x0533:
            r2.zzayd = r4     // Catch:{ all -> 0x07f2 }
            long r6 = r3.zzade     // Catch:{ all -> 0x07f2 }
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x07f2 }
            r2.zzaxv = r4     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r3.zzafi     // Catch:{ all -> 0x07f2 }
            r2.zzafi = r4     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r3.zzafv     // Catch:{ all -> 0x07f2 }
            r2.zzawr = r4     // Catch:{ all -> 0x07f2 }
            long r6 = r3.zzafq     // Catch:{ all -> 0x07f2 }
            r8 = 0
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 != 0) goto L_0x054f
            r4 = r0
            goto L_0x0555
        L_0x054f:
            long r6 = r3.zzafq     // Catch:{ all -> 0x07f2 }
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x07f2 }
        L_0x0555:
            r2.zzaxz = r4     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzq r4 = r4.zzgv()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzai.zzalg     // Catch:{ all -> 0x07f2 }
            boolean r4 = r4.zze(r6, r7)     // Catch:{ all -> 0x07f2 }
            if (r4 == 0) goto L_0x0571
            com.google.android.gms.measurement.internal.zzft r4 = r24.zzjr()     // Catch:{ all -> 0x07f2 }
            int[] r4 = r4.zzmi()     // Catch:{ all -> 0x07f2 }
            r2.zzayn = r4     // Catch:{ all -> 0x07f2 }
        L_0x0571:
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbd r4 = r4.zzgu()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x07f2 }
            android.util.Pair r4 = r4.zzbz(r6)     // Catch:{ all -> 0x07f2 }
            if (r4 == 0) goto L_0x059a
            java.lang.Object r6 = r4.first     // Catch:{ all -> 0x07f2 }
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ all -> 0x07f2 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x07f2 }
            if (r6 != 0) goto L_0x059a
            boolean r6 = r3.zzaft     // Catch:{ all -> 0x07f2 }
            if (r6 == 0) goto L_0x05f7
            java.lang.Object r6 = r4.first     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x07f2 }
            r2.zzaxx = r6     // Catch:{ all -> 0x07f2 }
            java.lang.Object r4 = r4.second     // Catch:{ all -> 0x07f2 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x07f2 }
            r2.zzaxy = r4     // Catch:{ all -> 0x07f2 }
            goto L_0x05f7
        L_0x059a:
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzaa r4 = r4.zzgp()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x07f2 }
            android.content.Context r6 = r6.getContext()     // Catch:{ all -> 0x07f2 }
            boolean r4 = r4.zzl(r6)     // Catch:{ all -> 0x07f2 }
            if (r4 != 0) goto L_0x05f7
            boolean r4 = r3.zzafu     // Catch:{ all -> 0x07f2 }
            if (r4 == 0) goto L_0x05f7
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            android.content.Context r4 = r4.getContext()     // Catch:{ all -> 0x07f2 }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = "android_id"
            java.lang.String r4 = android.provider.Settings.Secure.getString(r4, r6)     // Catch:{ all -> 0x07f2 }
            if (r4 != 0) goto L_0x05da
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r4 = r4.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r4 = r4.zzjj()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = "null secure ID. appId"
            java.lang.String r7 = r2.zztt     // Catch:{ all -> 0x07f2 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzas.zzbw(r7)     // Catch:{ all -> 0x07f2 }
            r4.zzg(r6, r7)     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = "null"
            goto L_0x05f5
        L_0x05da:
            boolean r6 = r4.isEmpty()     // Catch:{ all -> 0x07f2 }
            if (r6 == 0) goto L_0x05f5
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r6 = r6.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r6 = r6.zzjj()     // Catch:{ all -> 0x07f2 }
            java.lang.String r7 = "empty secure ID. appId"
            java.lang.String r10 = r2.zztt     // Catch:{ all -> 0x07f2 }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzas.zzbw(r10)     // Catch:{ all -> 0x07f2 }
            r6.zzg(r7, r10)     // Catch:{ all -> 0x07f2 }
        L_0x05f5:
            r2.zzayg = r4     // Catch:{ all -> 0x07f2 }
        L_0x05f7:
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzaa r4 = r4.zzgp()     // Catch:{ all -> 0x07f2 }
            r4.zzcl()     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = android.os.Build.MODEL     // Catch:{ all -> 0x07f2 }
            r2.zzaxt = r4     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzaa r4 = r4.zzgp()     // Catch:{ all -> 0x07f2 }
            r4.zzcl()     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x07f2 }
            r2.zzaxs = r4     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzaa r4 = r4.zzgp()     // Catch:{ all -> 0x07f2 }
            long r6 = r4.zziw()     // Catch:{ all -> 0x07f2 }
            int r4 = (int) r6     // Catch:{ all -> 0x07f2 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x07f2 }
            r2.zzaxu = r4     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzaa r4 = r4.zzgp()     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r4.zzix()     // Catch:{ all -> 0x07f2 }
            r2.zzahr = r4     // Catch:{ all -> 0x07f2 }
            r2.zzaxw = r0     // Catch:{ all -> 0x07f2 }
            r2.zzaxm = r0     // Catch:{ all -> 0x07f2 }
            r2.zzaxn = r0     // Catch:{ all -> 0x07f2 }
            r2.zzaxo = r0     // Catch:{ all -> 0x07f2 }
            long r6 = r3.zzafs     // Catch:{ all -> 0x07f2 }
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x07f2 }
            r2.zzayi = r4     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            boolean r4 = r4.isEnabled()     // Catch:{ all -> 0x07f2 }
            if (r4 == 0) goto L_0x064e
            boolean r4 = com.google.android.gms.measurement.internal.zzq.zzie()     // Catch:{ all -> 0x07f2 }
            if (r4 == 0) goto L_0x064e
            r2.zzayj = r0     // Catch:{ all -> 0x07f2 }
        L_0x064e:
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r3.packageName     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzg r0 = r0.zzbm(r4)     // Catch:{ all -> 0x07f2 }
            if (r0 != 0) goto L_0x06bc
            com.google.android.gms.measurement.internal.zzg r0 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x07f2 }
            r0.<init>(r4, r6)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfx r4 = r4.zzgr()     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r4.zzmm()     // Catch:{ all -> 0x07f2 }
            r0.zzaj(r4)     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r3.zzafk     // Catch:{ all -> 0x07f2 }
            r0.zzan(r4)     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r3.zzafi     // Catch:{ all -> 0x07f2 }
            r0.zzak(r4)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbd r4 = r4.zzgu()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r4.zzca(r6)     // Catch:{ all -> 0x07f2 }
            r0.zzam(r4)     // Catch:{ all -> 0x07f2 }
            r0.zzt(r8)     // Catch:{ all -> 0x07f2 }
            r0.zzo(r8)     // Catch:{ all -> 0x07f2 }
            r0.zzp(r8)     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r3.zzts     // Catch:{ all -> 0x07f2 }
            r0.setAppVersion(r4)     // Catch:{ all -> 0x07f2 }
            long r6 = r3.zzafo     // Catch:{ all -> 0x07f2 }
            r0.zzq(r6)     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = r3.zzafp     // Catch:{ all -> 0x07f2 }
            r0.zzao(r4)     // Catch:{ all -> 0x07f2 }
            long r6 = r3.zzade     // Catch:{ all -> 0x07f2 }
            r0.zzr(r6)     // Catch:{ all -> 0x07f2 }
            long r6 = r3.zzafq     // Catch:{ all -> 0x07f2 }
            r0.zzs(r6)     // Catch:{ all -> 0x07f2 }
            boolean r4 = r3.zzafr     // Catch:{ all -> 0x07f2 }
            r0.setMeasurementEnabled(r4)     // Catch:{ all -> 0x07f2 }
            long r6 = r3.zzafs     // Catch:{ all -> 0x07f2 }
            r0.zzac(r6)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r4 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            r4.zza(r0)     // Catch:{ all -> 0x07f2 }
        L_0x06bc:
            java.lang.String r4 = r0.getAppInstanceId()     // Catch:{ all -> 0x07f2 }
            r2.zzafh = r4     // Catch:{ all -> 0x07f2 }
            java.lang.String r0 = r0.getFirebaseInstanceId()     // Catch:{ all -> 0x07f2 }
            r2.zzafk = r0     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            java.lang.String r3 = r3.packageName     // Catch:{ all -> 0x07f2 }
            java.util.List r0 = r0.zzbl(r3)     // Catch:{ all -> 0x07f2 }
            int r3 = r0.size()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.internal.measurement.zzfz[] r3 = new com.google.android.gms.internal.measurement.zzfz[r3]     // Catch:{ all -> 0x07f2 }
            r2.zzaxl = r3     // Catch:{ all -> 0x07f2 }
            r3 = 0
        L_0x06db:
            int r4 = r0.size()     // Catch:{ all -> 0x07f2 }
            if (r3 >= r4) goto L_0x0714
            com.google.android.gms.internal.measurement.zzfz r4 = new com.google.android.gms.internal.measurement.zzfz     // Catch:{ all -> 0x07f2 }
            r4.<init>()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.internal.measurement.zzfz[] r6 = r2.zzaxl     // Catch:{ all -> 0x07f2 }
            r6[r3] = r4     // Catch:{ all -> 0x07f2 }
            java.lang.Object r6 = r0.get(r3)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfw r6 = (com.google.android.gms.measurement.internal.zzfw) r6     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = r6.name     // Catch:{ all -> 0x07f2 }
            r4.name = r6     // Catch:{ all -> 0x07f2 }
            java.lang.Object r6 = r0.get(r3)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfw r6 = (com.google.android.gms.measurement.internal.zzfw) r6     // Catch:{ all -> 0x07f2 }
            long r6 = r6.zzaum     // Catch:{ all -> 0x07f2 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x07f2 }
            r4.zzayw = r6     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzft r6 = r24.zzjr()     // Catch:{ all -> 0x07f2 }
            java.lang.Object r7 = r0.get(r3)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzfw r7 = (com.google.android.gms.measurement.internal.zzfw) r7     // Catch:{ all -> 0x07f2 }
            java.lang.Object r7 = r7.value     // Catch:{ all -> 0x07f2 }
            r6.zza(r4, r7)     // Catch:{ all -> 0x07f2 }
            int r3 = r3 + 1
            goto L_0x06db
        L_0x0714:
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ IOException -> 0x0783 }
            long r2 = r0.zza(r2)     // Catch:{ IOException -> 0x0783 }
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzad r4 = r5.zzahu     // Catch:{ all -> 0x07f2 }
            if (r4 == 0) goto L_0x0779
            com.google.android.gms.measurement.internal.zzad r4 = r5.zzahu     // Catch:{ all -> 0x07f2 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x07f2 }
        L_0x072a:
            boolean r6 = r4.hasNext()     // Catch:{ all -> 0x07f2 }
            if (r6 == 0) goto L_0x0740
            java.lang.Object r6 = r4.next()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x07f2 }
            java.lang.String r7 = "_r"
            boolean r6 = r7.equals(r6)     // Catch:{ all -> 0x07f2 }
            if (r6 == 0) goto L_0x072a
            r4 = 1
            goto L_0x077a
        L_0x0740:
            com.google.android.gms.measurement.internal.zzbq r4 = r24.zzls()     // Catch:{ all -> 0x07f2 }
            java.lang.String r6 = r5.zztt     // Catch:{ all -> 0x07f2 }
            java.lang.String r7 = r5.name     // Catch:{ all -> 0x07f2 }
            boolean r4 = r4.zzp(r6, r7)     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzt r10 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            long r11 = r24.zzly()     // Catch:{ all -> 0x07f2 }
            java.lang.String r13 = r5.zztt     // Catch:{ all -> 0x07f2 }
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            com.google.android.gms.measurement.internal.zzu r6 = r10.zza(r11, r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x07f2 }
            if (r4 == 0) goto L_0x0779
            long r6 = r6.zzahl     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzq r4 = r4.zzgv()     // Catch:{ all -> 0x07f2 }
            java.lang.String r10 = r5.zztt     // Catch:{ all -> 0x07f2 }
            int r4 = r4.zzaq(r10)     // Catch:{ all -> 0x07f2 }
            long r10 = (long) r4     // Catch:{ all -> 0x07f2 }
            int r4 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r4 >= 0) goto L_0x0779
            r4 = 1
            goto L_0x077a
        L_0x0779:
            r4 = 0
        L_0x077a:
            boolean r0 = r0.zza(r5, r2, r4)     // Catch:{ all -> 0x07f2 }
            if (r0 == 0) goto L_0x0799
            r1.zzatt = r8     // Catch:{ all -> 0x07f2 }
            goto L_0x0799
        L_0x0783:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r3 = r3.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ all -> 0x07f2 }
            java.lang.String r4 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r2 = r2.zztt     // Catch:{ all -> 0x07f2 }
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzas.zzbw(r2)     // Catch:{ all -> 0x07f2 }
            r3.zze(r4, r2, r0)     // Catch:{ all -> 0x07f2 }
        L_0x0799:
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()     // Catch:{ all -> 0x07f2 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x07f2 }
            r2 = 2
            boolean r0 = r0.isLoggable(r2)     // Catch:{ all -> 0x07f2 }
            if (r0 == 0) goto L_0x07c6
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjo()     // Catch:{ all -> 0x07f2 }
            java.lang.String r2 = "Event recorded"
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x07f2 }
            com.google.android.gms.measurement.internal.zzaq r3 = r3.zzgq()     // Catch:{ all -> 0x07f2 }
            java.lang.String r3 = r3.zza(r5)     // Catch:{ all -> 0x07f2 }
            r0.zzg(r2, r3)     // Catch:{ all -> 0x07f2 }
        L_0x07c6:
            com.google.android.gms.measurement.internal.zzt r0 = r24.zzjt()
            r0.endTransaction()
            r24.zzmb()
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjo()
            java.lang.String r2 = "Background event processing time, ms"
            long r3 = java.lang.System.nanoTime()
            long r3 = r3 - r22
            r5 = 500000(0x7a120, double:2.47033E-318)
            long r3 = r3 + r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = r3 / r5
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r0.zzg(r2, r3)
            return
        L_0x07f2:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzt r2 = r24.zzjt()
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfn.zzd(com.google.android.gms.measurement.internal.zzag, com.google.android.gms.measurement.internal.zzk):void");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:92|93) */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        r1.zzada.zzgt().zzjg().zze("Failed to parse upload URL. Not uploading. appId", com.google.android.gms.measurement.internal.zzas.zzbw(r5), r6);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:92:0x02a0 */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzlz() {
        /*
            r17 = this;
            r1 = r17
            r17.zzaf()
            r17.zzlx()
            r0 = 1
            r1.zzatz = r0
            r2 = 0
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x02dc }
            r3.zzgw()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzeb r3 = r3.zzgl()     // Catch:{ all -> 0x02dc }
            java.lang.Boolean r3 = r3.zzli()     // Catch:{ all -> 0x02dc }
            if (r3 != 0) goto L_0x0032
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjj()     // Catch:{ all -> 0x02dc }
            java.lang.String r3 = "Upload data called on the client side before use of service was decided"
            r0.zzby(r3)     // Catch:{ all -> 0x02dc }
            r1.zzatz = r2
            r17.zzmc()
            return
        L_0x0032:
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x02dc }
            if (r3 == 0) goto L_0x004d
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ all -> 0x02dc }
            java.lang.String r3 = "Upload called in the client side when service should be used"
            r0.zzby(r3)     // Catch:{ all -> 0x02dc }
            r1.zzatz = r2
            r17.zzmc()
            return
        L_0x004d:
            long r3 = r1.zzatt     // Catch:{ all -> 0x02dc }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x005e
            r17.zzmb()     // Catch:{ all -> 0x02dc }
            r1.zzatz = r2
            r17.zzmc()
            return
        L_0x005e:
            r17.zzaf()     // Catch:{ all -> 0x02dc }
            java.util.List<java.lang.Long> r3 = r1.zzauc     // Catch:{ all -> 0x02dc }
            if (r3 == 0) goto L_0x0067
            r3 = 1
            goto L_0x0068
        L_0x0067:
            r3 = 0
        L_0x0068:
            if (r3 == 0) goto L_0x007f
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjo()     // Catch:{ all -> 0x02dc }
            java.lang.String r3 = "Uploading requested multiple times"
            r0.zzby(r3)     // Catch:{ all -> 0x02dc }
            r1.zzatz = r2
            r17.zzmc()
            return
        L_0x007f:
            com.google.android.gms.measurement.internal.zzaw r3 = r17.zzlt()     // Catch:{ all -> 0x02dc }
            boolean r3 = r3.zzfb()     // Catch:{ all -> 0x02dc }
            if (r3 != 0) goto L_0x00a1
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjo()     // Catch:{ all -> 0x02dc }
            java.lang.String r3 = "Network not connected, ignoring upload request"
            r0.zzby(r3)     // Catch:{ all -> 0x02dc }
            r17.zzmb()     // Catch:{ all -> 0x02dc }
            r1.zzatz = r2
            r17.zzmc()
            return
        L_0x00a1:
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.common.util.Clock r3 = r3.zzbx()     // Catch:{ all -> 0x02dc }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x02dc }
            long r7 = com.google.android.gms.measurement.internal.zzq.zzic()     // Catch:{ all -> 0x02dc }
            long r7 = r3 - r7
            r9 = 0
            r1.zzd(r9, r7)     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzbw r7 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzbd r7 = r7.zzgu()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzbg r7 = r7.zzanc     // Catch:{ all -> 0x02dc }
            long r7 = r7.get()     // Catch:{ all -> 0x02dc }
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzbw r5 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzas r5 = r5.zzgt()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzau r5 = r5.zzjn()     // Catch:{ all -> 0x02dc }
            java.lang.String r6 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r7 = r3 - r7
            long r7 = java.lang.Math.abs(r7)     // Catch:{ all -> 0x02dc }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x02dc }
            r5.zzg(r6, r7)     // Catch:{ all -> 0x02dc }
        L_0x00de:
            com.google.android.gms.measurement.internal.zzt r5 = r17.zzjt()     // Catch:{ all -> 0x02dc }
            java.lang.String r5 = r5.zzih()     // Catch:{ all -> 0x02dc }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x02dc }
            r7 = -1
            if (r6 != 0) goto L_0x02b4
            long r10 = r1.zzaue     // Catch:{ all -> 0x02dc }
            int r6 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x00fe
            com.google.android.gms.measurement.internal.zzt r6 = r17.zzjt()     // Catch:{ all -> 0x02dc }
            long r6 = r6.zzio()     // Catch:{ all -> 0x02dc }
            r1.zzaue = r6     // Catch:{ all -> 0x02dc }
        L_0x00fe:
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzq r6 = r6.zzgv()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Integer> r7 = com.google.android.gms.measurement.internal.zzai.zzaja     // Catch:{ all -> 0x02dc }
            int r6 = r6.zzb(r5, r7)     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzbw r7 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzq r7 = r7.zzgv()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Integer> r8 = com.google.android.gms.measurement.internal.zzai.zzajb     // Catch:{ all -> 0x02dc }
            int r7 = r7.zzb(r5, r8)     // Catch:{ all -> 0x02dc }
            int r7 = java.lang.Math.max(r2, r7)     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzt r8 = r17.zzjt()     // Catch:{ all -> 0x02dc }
            java.util.List r6 = r8.zzb(r5, r6, r7)     // Catch:{ all -> 0x02dc }
            boolean r7 = r6.isEmpty()     // Catch:{ all -> 0x02dc }
            if (r7 != 0) goto L_0x02d6
            java.util.Iterator r7 = r6.iterator()     // Catch:{ all -> 0x02dc }
        L_0x012c:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x02dc }
            if (r8 == 0) goto L_0x0147
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x02dc }
            android.util.Pair r8 = (android.util.Pair) r8     // Catch:{ all -> 0x02dc }
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x02dc }
            com.google.android.gms.internal.measurement.zzfw r8 = (com.google.android.gms.internal.measurement.zzfw) r8     // Catch:{ all -> 0x02dc }
            java.lang.String r10 = r8.zzaxx     // Catch:{ all -> 0x02dc }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x02dc }
            if (r10 != 0) goto L_0x012c
            java.lang.String r7 = r8.zzaxx     // Catch:{ all -> 0x02dc }
            goto L_0x0148
        L_0x0147:
            r7 = r9
        L_0x0148:
            if (r7 == 0) goto L_0x0173
            r8 = 0
        L_0x014b:
            int r10 = r6.size()     // Catch:{ all -> 0x02dc }
            if (r8 >= r10) goto L_0x0173
            java.lang.Object r10 = r6.get(r8)     // Catch:{ all -> 0x02dc }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ all -> 0x02dc }
            java.lang.Object r10 = r10.first     // Catch:{ all -> 0x02dc }
            com.google.android.gms.internal.measurement.zzfw r10 = (com.google.android.gms.internal.measurement.zzfw) r10     // Catch:{ all -> 0x02dc }
            java.lang.String r11 = r10.zzaxx     // Catch:{ all -> 0x02dc }
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x02dc }
            if (r11 != 0) goto L_0x0170
            java.lang.String r10 = r10.zzaxx     // Catch:{ all -> 0x02dc }
            boolean r10 = r10.equals(r7)     // Catch:{ all -> 0x02dc }
            if (r10 != 0) goto L_0x0170
            java.util.List r6 = r6.subList(r2, r8)     // Catch:{ all -> 0x02dc }
            goto L_0x0173
        L_0x0170:
            int r8 = r8 + 1
            goto L_0x014b
        L_0x0173:
            com.google.android.gms.internal.measurement.zzfv r7 = new com.google.android.gms.internal.measurement.zzfv     // Catch:{ all -> 0x02dc }
            r7.<init>()     // Catch:{ all -> 0x02dc }
            int r8 = r6.size()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.internal.measurement.zzfw[] r8 = new com.google.android.gms.internal.measurement.zzfw[r8]     // Catch:{ all -> 0x02dc }
            r7.zzaxh = r8     // Catch:{ all -> 0x02dc }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x02dc }
            int r10 = r6.size()     // Catch:{ all -> 0x02dc }
            r8.<init>(r10)     // Catch:{ all -> 0x02dc }
            boolean r10 = com.google.android.gms.measurement.internal.zzq.zzie()     // Catch:{ all -> 0x02dc }
            if (r10 == 0) goto L_0x019d
            com.google.android.gms.measurement.internal.zzbw r10 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzq r10 = r10.zzgv()     // Catch:{ all -> 0x02dc }
            boolean r10 = r10.zzas(r5)     // Catch:{ all -> 0x02dc }
            if (r10 == 0) goto L_0x019d
            r10 = 1
            goto L_0x019e
        L_0x019d:
            r10 = 0
        L_0x019e:
            r11 = 0
        L_0x019f:
            com.google.android.gms.internal.measurement.zzfw[] r12 = r7.zzaxh     // Catch:{ all -> 0x02dc }
            int r12 = r12.length     // Catch:{ all -> 0x02dc }
            if (r11 >= r12) goto L_0x01f7
            com.google.android.gms.internal.measurement.zzfw[] r12 = r7.zzaxh     // Catch:{ all -> 0x02dc }
            java.lang.Object r13 = r6.get(r11)     // Catch:{ all -> 0x02dc }
            android.util.Pair r13 = (android.util.Pair) r13     // Catch:{ all -> 0x02dc }
            java.lang.Object r13 = r13.first     // Catch:{ all -> 0x02dc }
            com.google.android.gms.internal.measurement.zzfw r13 = (com.google.android.gms.internal.measurement.zzfw) r13     // Catch:{ all -> 0x02dc }
            r12[r11] = r13     // Catch:{ all -> 0x02dc }
            java.lang.Object r12 = r6.get(r11)     // Catch:{ all -> 0x02dc }
            android.util.Pair r12 = (android.util.Pair) r12     // Catch:{ all -> 0x02dc }
            java.lang.Object r12 = r12.second     // Catch:{ all -> 0x02dc }
            java.lang.Long r12 = (java.lang.Long) r12     // Catch:{ all -> 0x02dc }
            r8.add(r12)     // Catch:{ all -> 0x02dc }
            com.google.android.gms.internal.measurement.zzfw[] r12 = r7.zzaxh     // Catch:{ all -> 0x02dc }
            r12 = r12[r11]     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzbw r13 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzq r13 = r13.zzgv()     // Catch:{ all -> 0x02dc }
            long r13 = r13.zzhh()     // Catch:{ all -> 0x02dc }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x02dc }
            r12.zzaxw = r13     // Catch:{ all -> 0x02dc }
            com.google.android.gms.internal.measurement.zzfw[] r12 = r7.zzaxh     // Catch:{ all -> 0x02dc }
            r12 = r12[r11]     // Catch:{ all -> 0x02dc }
            java.lang.Long r13 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x02dc }
            r12.zzaxm = r13     // Catch:{ all -> 0x02dc }
            com.google.android.gms.internal.measurement.zzfw[] r12 = r7.zzaxh     // Catch:{ all -> 0x02dc }
            r12 = r12[r11]     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzbw r13 = r1.zzada     // Catch:{ all -> 0x02dc }
            r13.zzgw()     // Catch:{ all -> 0x02dc }
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x02dc }
            r12.zzayb = r13     // Catch:{ all -> 0x02dc }
            if (r10 != 0) goto L_0x01f4
            com.google.android.gms.internal.measurement.zzfw[] r12 = r7.zzaxh     // Catch:{ all -> 0x02dc }
            r12 = r12[r11]     // Catch:{ all -> 0x02dc }
            r12.zzayj = r9     // Catch:{ all -> 0x02dc }
        L_0x01f4:
            int r11 = r11 + 1
            goto L_0x019f
        L_0x01f7:
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzas r6 = r6.zzgt()     // Catch:{ all -> 0x02dc }
            r10 = 2
            boolean r6 = r6.isLoggable(r10)     // Catch:{ all -> 0x02dc }
            if (r6 == 0) goto L_0x020c
            com.google.android.gms.measurement.internal.zzft r6 = r17.zzjr()     // Catch:{ all -> 0x02dc }
            java.lang.String r9 = r6.zzb(r7)     // Catch:{ all -> 0x02dc }
        L_0x020c:
            com.google.android.gms.measurement.internal.zzft r6 = r17.zzjr()     // Catch:{ all -> 0x02dc }
            byte[] r14 = r6.zza(r7)     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.String> r6 = com.google.android.gms.measurement.internal.zzai.zzajk     // Catch:{ all -> 0x02dc }
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x02dc }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x02dc }
            java.net.URL r13 = new java.net.URL     // Catch:{ MalformedURLException -> 0x02a0 }
            r13.<init>(r6)     // Catch:{ MalformedURLException -> 0x02a0 }
            boolean r10 = r8.isEmpty()     // Catch:{ MalformedURLException -> 0x02a0 }
            if (r10 != 0) goto L_0x0229
            r10 = 1
            goto L_0x022a
        L_0x0229:
            r10 = 0
        L_0x022a:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r10)     // Catch:{ MalformedURLException -> 0x02a0 }
            java.util.List<java.lang.Long> r10 = r1.zzauc     // Catch:{ MalformedURLException -> 0x02a0 }
            if (r10 == 0) goto L_0x0241
            com.google.android.gms.measurement.internal.zzbw r8 = r1.zzada     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzas r8 = r8.zzgt()     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzau r8 = r8.zzjg()     // Catch:{ MalformedURLException -> 0x02a0 }
            java.lang.String r10 = "Set uploading progress before finishing the previous upload"
            r8.zzby(r10)     // Catch:{ MalformedURLException -> 0x02a0 }
            goto L_0x0248
        L_0x0241:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x02a0 }
            r10.<init>(r8)     // Catch:{ MalformedURLException -> 0x02a0 }
            r1.zzauc = r10     // Catch:{ MalformedURLException -> 0x02a0 }
        L_0x0248:
            com.google.android.gms.measurement.internal.zzbw r8 = r1.zzada     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzbd r8 = r8.zzgu()     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzbg r8 = r8.zzand     // Catch:{ MalformedURLException -> 0x02a0 }
            r8.set(r3)     // Catch:{ MalformedURLException -> 0x02a0 }
            java.lang.String r3 = "?"
            com.google.android.gms.internal.measurement.zzfw[] r4 = r7.zzaxh     // Catch:{ MalformedURLException -> 0x02a0 }
            int r4 = r4.length     // Catch:{ MalformedURLException -> 0x02a0 }
            if (r4 <= 0) goto L_0x0260
            com.google.android.gms.internal.measurement.zzfw[] r3 = r7.zzaxh     // Catch:{ MalformedURLException -> 0x02a0 }
            r3 = r3[r2]     // Catch:{ MalformedURLException -> 0x02a0 }
            java.lang.String r3 = r3.zztt     // Catch:{ MalformedURLException -> 0x02a0 }
        L_0x0260:
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzas r4 = r4.zzgt()     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzau r4 = r4.zzjo()     // Catch:{ MalformedURLException -> 0x02a0 }
            java.lang.String r7 = "Uploading data. app, uncompressed size, data"
            int r8 = r14.length     // Catch:{ MalformedURLException -> 0x02a0 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ MalformedURLException -> 0x02a0 }
            r4.zzd(r7, r3, r8, r9)     // Catch:{ MalformedURLException -> 0x02a0 }
            r1.zzaty = r0     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzaw r11 = r17.zzlt()     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzfp r0 = new com.google.android.gms.measurement.internal.zzfp     // Catch:{ MalformedURLException -> 0x02a0 }
            r0.<init>(r1, r5)     // Catch:{ MalformedURLException -> 0x02a0 }
            r11.zzaf()     // Catch:{ MalformedURLException -> 0x02a0 }
            r11.zzcl()     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzbr r3 = r11.zzgs()     // Catch:{ MalformedURLException -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzba r4 = new com.google.android.gms.measurement.internal.zzba     // Catch:{ MalformedURLException -> 0x02a0 }
            r15 = 0
            r10 = r4
            r12 = r5
            r16 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16)     // Catch:{ MalformedURLException -> 0x02a0 }
            r3.zzd(r4)     // Catch:{ MalformedURLException -> 0x02a0 }
            goto L_0x02d6
        L_0x02a0:
            com.google.android.gms.measurement.internal.zzbw r0 = r1.zzada     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzas r0 = r0.zzgt()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ all -> 0x02dc }
            java.lang.String r3 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzas.zzbw(r5)     // Catch:{ all -> 0x02dc }
            r0.zze(r3, r4, r6)     // Catch:{ all -> 0x02dc }
            goto L_0x02d6
        L_0x02b4:
            r1.zzaue = r7     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzt r0 = r17.zzjt()     // Catch:{ all -> 0x02dc }
            long r5 = com.google.android.gms.measurement.internal.zzq.zzic()     // Catch:{ all -> 0x02dc }
            long r3 = r3 - r5
            java.lang.String r0 = r0.zzad(r3)     // Catch:{ all -> 0x02dc }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x02dc }
            if (r3 != 0) goto L_0x02d6
            com.google.android.gms.measurement.internal.zzt r3 = r17.zzjt()     // Catch:{ all -> 0x02dc }
            com.google.android.gms.measurement.internal.zzg r0 = r3.zzbm(r0)     // Catch:{ all -> 0x02dc }
            if (r0 == 0) goto L_0x02d6
            r1.zzb(r0)     // Catch:{ all -> 0x02dc }
        L_0x02d6:
            r1.zzatz = r2
            r17.zzmc()
            return
        L_0x02dc:
            r0 = move-exception
            r1.zzatz = r2
            r17.zzmc()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfn.zzlz():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        r4 = r1;
        r22 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        r6 = null;
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009d, code lost:
        r6 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040 A[ExcHandler: all (th java.lang.Throwable), PHI: r3 
  PHI: (r3v177 android.database.Cursor) = (r3v172 android.database.Cursor), (r3v172 android.database.Cursor), (r3v172 android.database.Cursor), (r3v180 android.database.Cursor), (r3v180 android.database.Cursor), (r3v180 android.database.Cursor), (r3v180 android.database.Cursor), (r3v0 android.database.Cursor), (r3v0 android.database.Cursor) binds: [B:45:0x00e0, B:51:0x00ed, B:52:?, B:23:0x007f, B:29:0x008c, B:31:0x0090, B:32:?, B:9:0x0031, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0290 A[SYNTHETIC, Splitter:B:153:0x0290] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0297 A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02a5 A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x03bd A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x03bf A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x03c2 A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x03c3 A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x05e2 A[ADDED_TO_REGION, Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x06aa A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x072c A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:383:0x087e A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x0898 A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:390:0x08b5 A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:393:0x08bb A[Catch:{ IOException -> 0x0239, all -> 0x0df9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:594:0x0ddb  */
    /* JADX WARNING: Removed duplicated region for block: B:602:0x0df3 A[SYNTHETIC, Splitter:B:602:0x0df3] */
    /* JADX WARNING: Removed duplicated region for block: B:637:0x0895 A[SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzd(java.lang.String r46, long r47) {
        /*
            r45 = this;
            r1 = r45
            com.google.android.gms.measurement.internal.zzt r2 = r45.zzjt()
            r2.beginTransaction()
            com.google.android.gms.measurement.internal.zzfn$zza r2 = new com.google.android.gms.measurement.internal.zzfn$zza     // Catch:{ all -> 0x0df9 }
            r3 = 0
            r2.<init>(r1, r3)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzt r4 = r45.zzjt()     // Catch:{ all -> 0x0df9 }
            long r5 = r1.zzaue     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x0df9 }
            r4.zzaf()     // Catch:{ all -> 0x0df9 }
            r4.zzcl()     // Catch:{ all -> 0x0df9 }
            r8 = -1
            r10 = 2
            r11 = 0
            r12 = 1
            android.database.sqlite.SQLiteDatabase r15 = r4.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            boolean r13 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            if (r13 == 0) goto L_0x009f
            int r13 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r13 == 0) goto L_0x004c
            java.lang.String[] r14 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            java.lang.String r16 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            r14[r11] = r16     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            java.lang.String r16 = java.lang.String.valueOf(r47)     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            r14[r12] = r16     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            goto L_0x0054
        L_0x0040:
            r0 = move-exception
            r4 = r1
            r22 = r3
            goto L_0x0276
        L_0x0046:
            r0 = move-exception
            r6 = r3
            r7 = r6
        L_0x0049:
            r3 = r0
            goto L_0x027d
        L_0x004c:
            java.lang.String[] r14 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            java.lang.String r16 = java.lang.String.valueOf(r47)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            r14[r11] = r16     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
        L_0x0054:
            if (r13 == 0) goto L_0x0059
            java.lang.String r13 = "rowid <= ? and "
            goto L_0x005b
        L_0x0059:
            java.lang.String r13 = ""
        L_0x005b:
            java.lang.String r16 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            int r7 = r16.length()     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            int r7 = r7 + 148
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            r3.<init>(r7)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            java.lang.String r7 = "select app_id, metadata_fingerprint from raw_events where "
            r3.append(r7)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            r3.append(r13)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            java.lang.String r7 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r3.append(r7)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            android.database.Cursor r3 = r15.rawQuery(r3, r14)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            boolean r7 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x026d, all -> 0x0040 }
            if (r7 != 0) goto L_0x008c
            if (r3 == 0) goto L_0x0293
            r3.close()     // Catch:{ all -> 0x0df9 }
            goto L_0x0293
        L_0x008c:
            java.lang.String r7 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0040 }
            java.lang.String r13 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x009c, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x009c, all -> 0x0040 }
            r23 = r3
            r3 = r7
            r7 = r13
            goto L_0x00f8
        L_0x009c:
            r0 = move-exception
            r6 = r3
            goto L_0x0049
        L_0x009f:
            int r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r3 == 0) goto L_0x00b0
            java.lang.String[] r7 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            r13 = 0
            r7[r11] = r13     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            java.lang.String r13 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            r7[r12] = r13     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            r13 = r7
            goto L_0x00b5
        L_0x00b0:
            r7 = 0
            java.lang.String[] r13 = new java.lang.String[]{r7}     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
        L_0x00b5:
            if (r3 == 0) goto L_0x00ba
            java.lang.String r3 = " and rowid <= ?"
            goto L_0x00bc
        L_0x00ba:
            java.lang.String r3 = ""
        L_0x00bc:
            java.lang.String r7 = java.lang.String.valueOf(r3)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            int r7 = r7.length()     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            int r7 = r7 + 84
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            r14.<init>(r7)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            java.lang.String r7 = "select metadata_fingerprint from raw_events where app_id = ?"
            r14.append(r7)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            r14.append(r3)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            java.lang.String r3 = " order by rowid limit 1;"
            r14.append(r3)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            java.lang.String r3 = r14.toString()     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            android.database.Cursor r3 = r15.rawQuery(r3, r13)     // Catch:{ SQLiteException -> 0x0279, all -> 0x0272 }
            boolean r7 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x026d, all -> 0x0040 }
            if (r7 != 0) goto L_0x00ed
            if (r3 == 0) goto L_0x0293
            r3.close()     // Catch:{ all -> 0x0df9 }
            goto L_0x0293
        L_0x00ed:
            java.lang.String r13 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x026d, all -> 0x0040 }
            r23 = r3
            r7 = r13
            r3 = 0
        L_0x00f8:
            java.lang.String r14 = "raw_events_metadata"
            java.lang.String r13 = "metadata"
            java.lang.String[] r16 = new java.lang.String[]{r13}     // Catch:{ SQLiteException -> 0x0267, all -> 0x0262 }
            java.lang.String r17 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r13 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0267, all -> 0x0262 }
            r13[r11] = r3     // Catch:{ SQLiteException -> 0x0267, all -> 0x0262 }
            r13[r12] = r7     // Catch:{ SQLiteException -> 0x0267, all -> 0x0262 }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            java.lang.String r21 = "2"
            r24 = r13
            r13 = r15
            r25 = r15
            r15 = r16
            r16 = r17
            r17 = r24
            android.database.Cursor r15 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x0267, all -> 0x0262 }
            boolean r13 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            if (r13 != 0) goto L_0x0148
            com.google.android.gms.measurement.internal.zzas r5 = r4.zzgt()     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            com.google.android.gms.measurement.internal.zzau r5 = r5.zzjg()     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            java.lang.String r6 = "Raw event metadata record is missing. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzas.zzbw(r3)     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            r5.zzg(r6, r7)     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            if (r15 == 0) goto L_0x0293
            r15.close()     // Catch:{ all -> 0x0df9 }
            goto L_0x0293
        L_0x013d:
            r0 = move-exception
            r4 = r1
            r22 = r15
            goto L_0x0276
        L_0x0143:
            r0 = move-exception
            r7 = r3
            r6 = r15
            goto L_0x0049
        L_0x0148:
            byte[] r13 = r15.getBlob(r11)     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            int r14 = r13.length     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            com.google.android.gms.internal.measurement.zzxz r13 = com.google.android.gms.internal.measurement.zzxz.zzj(r13, r11, r14)     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            com.google.android.gms.internal.measurement.zzfw r14 = new com.google.android.gms.internal.measurement.zzfw     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r14.<init>()     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r14.zza(r13)     // Catch:{ IOException -> 0x0239 }
            boolean r13 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            if (r13 == 0) goto L_0x0170
            com.google.android.gms.measurement.internal.zzas r13 = r4.zzgt()     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            com.google.android.gms.measurement.internal.zzau r13 = r13.zzjj()     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            java.lang.String r10 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzas.zzbw(r3)     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            r13.zzg(r10, r12)     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
        L_0x0170:
            r15.close()     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r2.zzb(r14)     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x0190
            java.lang.String r10 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            r12 = 3
            java.lang.String[] r13 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            r13[r11] = r3     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            r12 = 1
            r13[r12] = r7     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            r6 = 2
            r13[r6] = r5     // Catch:{ SQLiteException -> 0x0143, all -> 0x013d }
            r16 = r10
            r17 = r13
            goto L_0x019e
        L_0x0190:
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r6 = 2
            java.lang.String[] r10 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r10[r11] = r3     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r6 = 1
            r10[r6] = r7     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r16 = r5
            r17 = r10
        L_0x019e:
            java.lang.String r14 = "raw_events"
            java.lang.String r5 = "rowid"
            java.lang.String r6 = "name"
            java.lang.String r7 = "timestamp"
            java.lang.String r10 = "data"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6, r7, r10}     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            r21 = 0
            r13 = r25
            r6 = r15
            r15 = r5
            android.database.Cursor r5 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x0255, all -> 0x0253 }
            boolean r6 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            if (r6 != 0) goto L_0x01da
            com.google.android.gms.measurement.internal.zzas r6 = r4.zzgt()     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            com.google.android.gms.measurement.internal.zzau r6 = r6.zzjj()     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            java.lang.String r7 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzas.zzbw(r3)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            r6.zzg(r7, r10)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            if (r5 == 0) goto L_0x0293
            r5.close()     // Catch:{ all -> 0x0df9 }
            goto L_0x0293
        L_0x01da:
            long r6 = r5.getLong(r11)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            r10 = 3
            byte[] r12 = r5.getBlob(r10)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            int r10 = r12.length     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            com.google.android.gms.internal.measurement.zzxz r10 = com.google.android.gms.internal.measurement.zzxz.zzj(r12, r11, r10)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            com.google.android.gms.internal.measurement.zzft r12 = new com.google.android.gms.internal.measurement.zzft     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            r12.<init>()     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            r12.zza(r10)     // Catch:{ IOException -> 0x020f }
            r10 = 1
            java.lang.String r13 = r5.getString(r10)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            r12.name = r13     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            r10 = 2
            long r13 = r5.getLong(r10)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            r12.zzaxd = r10     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            boolean r6 = r2.zza(r6, r12)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            if (r6 != 0) goto L_0x0222
            if (r5 == 0) goto L_0x0293
            r5.close()     // Catch:{ all -> 0x0df9 }
            goto L_0x0293
        L_0x020f:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.measurement.internal.zzas r7 = r4.zzgt()     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            com.google.android.gms.measurement.internal.zzau r7 = r7.zzjg()     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            java.lang.String r10 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzas.zzbw(r3)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            r7.zze(r10, r12, r6)     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
        L_0x0222:
            boolean r6 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x0234, all -> 0x022f }
            if (r6 != 0) goto L_0x01da
            if (r5 == 0) goto L_0x0293
            r5.close()     // Catch:{ all -> 0x0df9 }
            goto L_0x0293
        L_0x022f:
            r0 = move-exception
            r4 = r1
            r22 = r5
            goto L_0x0276
        L_0x0234:
            r0 = move-exception
            r7 = r3
            r6 = r5
            goto L_0x0049
        L_0x0239:
            r0 = move-exception
            r6 = r15
            r5 = r0
            com.google.android.gms.measurement.internal.zzas r7 = r4.zzgt()     // Catch:{ SQLiteException -> 0x0255, all -> 0x0253 }
            com.google.android.gms.measurement.internal.zzau r7 = r7.zzjg()     // Catch:{ SQLiteException -> 0x0255, all -> 0x0253 }
            java.lang.String r10 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzas.zzbw(r3)     // Catch:{ SQLiteException -> 0x0255, all -> 0x0253 }
            r7.zze(r10, r12, r5)     // Catch:{ SQLiteException -> 0x0255, all -> 0x0253 }
            if (r6 == 0) goto L_0x0293
            r6.close()     // Catch:{ all -> 0x0df9 }
            goto L_0x0293
        L_0x0253:
            r0 = move-exception
            goto L_0x0259
        L_0x0255:
            r0 = move-exception
            goto L_0x025f
        L_0x0257:
            r0 = move-exception
            r6 = r15
        L_0x0259:
            r4 = r1
            r22 = r6
            goto L_0x0276
        L_0x025d:
            r0 = move-exception
            r6 = r15
        L_0x025f:
            r7 = r3
            goto L_0x0049
        L_0x0262:
            r0 = move-exception
            r4 = r1
            r22 = r23
            goto L_0x0276
        L_0x0267:
            r0 = move-exception
            r7 = r3
            r6 = r23
            goto L_0x0049
        L_0x026d:
            r0 = move-exception
            r6 = r3
            r7 = 0
            goto L_0x0049
        L_0x0272:
            r0 = move-exception
            r4 = r1
            r22 = 0
        L_0x0276:
            r1 = r0
            goto L_0x0df1
        L_0x0279:
            r0 = move-exception
            r3 = r0
            r6 = 0
            r7 = 0
        L_0x027d:
            com.google.android.gms.measurement.internal.zzas r4 = r4.zzgt()     // Catch:{ all -> 0x0dec }
            com.google.android.gms.measurement.internal.zzau r4 = r4.zzjg()     // Catch:{ all -> 0x0dec }
            java.lang.String r5 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzas.zzbw(r7)     // Catch:{ all -> 0x0dec }
            r4.zze(r5, r7, r3)     // Catch:{ all -> 0x0dec }
            if (r6 == 0) goto L_0x0293
            r6.close()     // Catch:{ all -> 0x0df9 }
        L_0x0293:
            java.util.List<com.google.android.gms.internal.measurement.zzft> r3 = r2.zzauk     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x02a2
            java.util.List<com.google.android.gms.internal.measurement.zzft> r3 = r2.zzauk     // Catch:{ all -> 0x0df9 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x02a0
            goto L_0x02a2
        L_0x02a0:
            r3 = 0
            goto L_0x02a3
        L_0x02a2:
            r3 = 1
        L_0x02a3:
            if (r3 != 0) goto L_0x0ddb
            com.google.android.gms.internal.measurement.zzfw r3 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.util.List<com.google.android.gms.internal.measurement.zzft> r4 = r2.zzauk     // Catch:{ all -> 0x0df9 }
            int r4 = r4.size()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzft[] r4 = new com.google.android.gms.internal.measurement.zzft[r4]     // Catch:{ all -> 0x0df9 }
            r3.zzaxk = r4     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzq r4 = r4.zzgv()     // Catch:{ all -> 0x0df9 }
            java.lang.String r5 = r3.zztt     // Catch:{ all -> 0x0df9 }
            boolean r4 = r4.zzau(r5)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzbw r5 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzq r5 = r5.zzgv()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r6 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r6 = r6.zztt     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzai.zzalc     // Catch:{ all -> 0x0df9 }
            boolean r5 = r5.zze(r6, r7)     // Catch:{ all -> 0x0df9 }
            r8 = 0
            r9 = 0
            r10 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x02d4:
            java.util.List<com.google.android.gms.internal.measurement.zzft> r6 = r2.zzauk     // Catch:{ all -> 0x0df9 }
            int r6 = r6.size()     // Catch:{ all -> 0x0df9 }
            r18 = 1
            if (r10 >= r6) goto L_0x078a
            java.util.List<com.google.android.gms.internal.measurement.zzft> r6 = r2.zzauk     // Catch:{ all -> 0x0df9 }
            java.lang.Object r6 = r6.get(r10)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzft r6 = (com.google.android.gms.internal.measurement.zzft) r6     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzbq r7 = r45.zzls()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r11 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = r11.zztt     // Catch:{ all -> 0x0df9 }
            r21 = r12
            java.lang.String r12 = r6.name     // Catch:{ all -> 0x0df9 }
            boolean r7 = r7.zzo(r11, r12)     // Catch:{ all -> 0x0df9 }
            if (r7 == 0) goto L_0x036b
            com.google.android.gms.measurement.internal.zzbw r7 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r7 = r7.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r7 = r7.zzjj()     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = "Dropping blacklisted raw event. appId"
            com.google.android.gms.internal.measurement.zzfw r12 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r12 = r12.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzas.zzbw(r12)     // Catch:{ all -> 0x0df9 }
            r23 = r10
            com.google.android.gms.measurement.internal.zzbw r10 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzaq r10 = r10.zzgq()     // Catch:{ all -> 0x0df9 }
            r25 = r13
            java.lang.String r13 = r6.name     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = r10.zzbt(r13)     // Catch:{ all -> 0x0df9 }
            r7.zze(r11, r12, r10)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzbq r7 = r45.zzls()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r10 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = r10.zztt     // Catch:{ all -> 0x0df9 }
            boolean r7 = r7.zzcl(r10)     // Catch:{ all -> 0x0df9 }
            if (r7 != 0) goto L_0x033e
            com.google.android.gms.measurement.internal.zzbq r7 = r45.zzls()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r10 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = r10.zztt     // Catch:{ all -> 0x0df9 }
            boolean r7 = r7.zzcm(r10)     // Catch:{ all -> 0x0df9 }
            if (r7 == 0) goto L_0x033c
            goto L_0x033e
        L_0x033c:
            r7 = 0
            goto L_0x033f
        L_0x033e:
            r7 = 1
        L_0x033f:
            if (r7 != 0) goto L_0x0364
            java.lang.String r7 = "_err"
            java.lang.String r10 = r6.name     // Catch:{ all -> 0x0df9 }
            boolean r7 = r7.equals(r10)     // Catch:{ all -> 0x0df9 }
            if (r7 != 0) goto L_0x0364
            com.google.android.gms.measurement.internal.zzbw r7 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzfx r26 = r7.zzgr()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r7 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = r7.zztt     // Catch:{ all -> 0x0df9 }
            r28 = 11
            java.lang.String r29 = "_ev"
            java.lang.String r6 = r6.name     // Catch:{ all -> 0x0df9 }
            r31 = 0
            r27 = r7
            r30 = r6
            r26.zza(r27, r28, r29, r30, r31)     // Catch:{ all -> 0x0df9 }
        L_0x0364:
            r18 = r21
            r13 = r25
            r12 = 3
            goto L_0x0783
        L_0x036b:
            r23 = r10
            r25 = r13
            com.google.android.gms.measurement.internal.zzbq r7 = r45.zzls()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r10 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = r10.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = r6.name     // Catch:{ all -> 0x0df9 }
            boolean r7 = r7.zzp(r10, r11)     // Catch:{ all -> 0x0df9 }
            if (r7 != 0) goto L_0x03cb
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = r6.name     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)     // Catch:{ all -> 0x0df9 }
            int r12 = r11.hashCode()     // Catch:{ all -> 0x0df9 }
            r13 = 94660(0x171c4, float:1.32647E-40)
            if (r12 == r13) goto L_0x03af
            r13 = 95025(0x17331, float:1.33158E-40)
            if (r12 == r13) goto L_0x03a5
            r13 = 95027(0x17333, float:1.33161E-40)
            if (r12 == r13) goto L_0x039b
            goto L_0x03b9
        L_0x039b:
            java.lang.String r12 = "_ui"
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0df9 }
            if (r11 == 0) goto L_0x03b9
            r11 = 1
            goto L_0x03ba
        L_0x03a5:
            java.lang.String r12 = "_ug"
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0df9 }
            if (r11 == 0) goto L_0x03b9
            r11 = 2
            goto L_0x03ba
        L_0x03af:
            java.lang.String r12 = "_in"
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0df9 }
            if (r11 == 0) goto L_0x03b9
            r11 = 0
            goto L_0x03ba
        L_0x03b9:
            r11 = -1
        L_0x03ba:
            switch(r11) {
                case 0: goto L_0x03bf;
                case 1: goto L_0x03bf;
                case 2: goto L_0x03bf;
                default: goto L_0x03bd;
            }     // Catch:{ all -> 0x0df9 }
        L_0x03bd:
            r11 = 0
            goto L_0x03c0
        L_0x03bf:
            r11 = 1
        L_0x03c0:
            if (r11 == 0) goto L_0x03c3
            goto L_0x03cb
        L_0x03c3:
            r26 = r3
            r29 = r14
            r18 = r21
            goto L_0x05d2
        L_0x03cb:
            com.google.android.gms.internal.measurement.zzfu[] r11 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            if (r11 != 0) goto L_0x03d4
            r11 = 0
            com.google.android.gms.internal.measurement.zzfu[] r12 = new com.google.android.gms.internal.measurement.zzfu[r11]     // Catch:{ all -> 0x0df9 }
            r6.zzaxc = r12     // Catch:{ all -> 0x0df9 }
        L_0x03d4:
            com.google.android.gms.internal.measurement.zzfu[] r11 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            int r12 = r11.length     // Catch:{ all -> 0x0df9 }
            r13 = 0
            r26 = 0
            r27 = 0
        L_0x03dc:
            if (r13 >= r12) goto L_0x0410
            r10 = r11[r13]     // Catch:{ all -> 0x0df9 }
            r29 = r11
            java.lang.String r11 = "_c"
            r30 = r12
            java.lang.String r12 = r10.name     // Catch:{ all -> 0x0df9 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0df9 }
            if (r11 == 0) goto L_0x03f7
            java.lang.Long r11 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x0df9 }
            r10.zzaxg = r11     // Catch:{ all -> 0x0df9 }
            r26 = 1
            goto L_0x0409
        L_0x03f7:
            java.lang.String r11 = "_r"
            java.lang.String r12 = r10.name     // Catch:{ all -> 0x0df9 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0df9 }
            if (r11 == 0) goto L_0x0409
            java.lang.Long r11 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x0df9 }
            r10.zzaxg = r11     // Catch:{ all -> 0x0df9 }
            r27 = 1
        L_0x0409:
            int r13 = r13 + 1
            r11 = r29
            r12 = r30
            goto L_0x03dc
        L_0x0410:
            if (r26 != 0) goto L_0x0452
            if (r7 == 0) goto L_0x0452
            com.google.android.gms.measurement.internal.zzbw r10 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r10 = r10.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r10 = r10.zzjo()     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = "Marking event as conversion"
            com.google.android.gms.measurement.internal.zzbw r12 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzaq r12 = r12.zzgq()     // Catch:{ all -> 0x0df9 }
            java.lang.String r13 = r6.name     // Catch:{ all -> 0x0df9 }
            java.lang.String r12 = r12.zzbt(r13)     // Catch:{ all -> 0x0df9 }
            r10.zzg(r11, r12)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r10 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r11 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            int r11 = r11.length     // Catch:{ all -> 0x0df9 }
            r12 = 1
            int r11 = r11 + r12
            java.lang.Object[] r10 = java.util.Arrays.copyOf(r10, r11)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r10 = (com.google.android.gms.internal.measurement.zzfu[]) r10     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu r11 = new com.google.android.gms.internal.measurement.zzfu     // Catch:{ all -> 0x0df9 }
            r11.<init>()     // Catch:{ all -> 0x0df9 }
            java.lang.String r12 = "_c"
            r11.name = r12     // Catch:{ all -> 0x0df9 }
            java.lang.Long r12 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x0df9 }
            r11.zzaxg = r12     // Catch:{ all -> 0x0df9 }
            int r12 = r10.length     // Catch:{ all -> 0x0df9 }
            r13 = 1
            int r12 = r12 - r13
            r10[r12] = r11     // Catch:{ all -> 0x0df9 }
            r6.zzaxc = r10     // Catch:{ all -> 0x0df9 }
        L_0x0452:
            if (r27 != 0) goto L_0x0492
            com.google.android.gms.measurement.internal.zzbw r10 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r10 = r10.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r10 = r10.zzjo()     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = "Marking event as real-time"
            com.google.android.gms.measurement.internal.zzbw r12 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzaq r12 = r12.zzgq()     // Catch:{ all -> 0x0df9 }
            java.lang.String r13 = r6.name     // Catch:{ all -> 0x0df9 }
            java.lang.String r12 = r12.zzbt(r13)     // Catch:{ all -> 0x0df9 }
            r10.zzg(r11, r12)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r10 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r11 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            int r11 = r11.length     // Catch:{ all -> 0x0df9 }
            r12 = 1
            int r11 = r11 + r12
            java.lang.Object[] r10 = java.util.Arrays.copyOf(r10, r11)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r10 = (com.google.android.gms.internal.measurement.zzfu[]) r10     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu r11 = new com.google.android.gms.internal.measurement.zzfu     // Catch:{ all -> 0x0df9 }
            r11.<init>()     // Catch:{ all -> 0x0df9 }
            java.lang.String r12 = "_r"
            r11.name = r12     // Catch:{ all -> 0x0df9 }
            java.lang.Long r12 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x0df9 }
            r11.zzaxg = r12     // Catch:{ all -> 0x0df9 }
            int r12 = r10.length     // Catch:{ all -> 0x0df9 }
            r13 = 1
            int r12 = r12 - r13
            r10[r12] = r11     // Catch:{ all -> 0x0df9 }
            r6.zzaxc = r10     // Catch:{ all -> 0x0df9 }
        L_0x0492:
            com.google.android.gms.measurement.internal.zzt r29 = r45.zzjt()     // Catch:{ all -> 0x0df9 }
            long r30 = r45.zzly()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r10 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = r10.zztt     // Catch:{ all -> 0x0df9 }
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 1
            r32 = r10
            com.google.android.gms.measurement.internal.zzu r10 = r29.zza(r30, r32, r33, r34, r35, r36, r37)     // Catch:{ all -> 0x0df9 }
            long r10 = r10.zzahl     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzbw r12 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzq r12 = r12.zzgv()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r13 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r13 = r13.zztt     // Catch:{ all -> 0x0df9 }
            int r12 = r12.zzaq(r13)     // Catch:{ all -> 0x0df9 }
            long r12 = (long) r12     // Catch:{ all -> 0x0df9 }
            int r18 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r18 <= 0) goto L_0x0504
            r10 = 0
        L_0x04c4:
            com.google.android.gms.internal.measurement.zzfu[] r11 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            int r11 = r11.length     // Catch:{ all -> 0x0df9 }
            if (r10 >= r11) goto L_0x04ff
            java.lang.String r11 = "_r"
            com.google.android.gms.internal.measurement.zzfu[] r12 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            r12 = r12[r10]     // Catch:{ all -> 0x0df9 }
            java.lang.String r12 = r12.name     // Catch:{ all -> 0x0df9 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0df9 }
            if (r11 == 0) goto L_0x04fa
            com.google.android.gms.internal.measurement.zzfu[] r11 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            int r11 = r11.length     // Catch:{ all -> 0x0df9 }
            r12 = 1
            int r11 = r11 - r12
            com.google.android.gms.internal.measurement.zzfu[] r11 = new com.google.android.gms.internal.measurement.zzfu[r11]     // Catch:{ all -> 0x0df9 }
            if (r10 <= 0) goto L_0x04e6
            com.google.android.gms.internal.measurement.zzfu[] r12 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            r13 = 0
            java.lang.System.arraycopy(r12, r13, r11, r13, r10)     // Catch:{ all -> 0x0df9 }
        L_0x04e6:
            int r12 = r11.length     // Catch:{ all -> 0x0df9 }
            if (r10 >= r12) goto L_0x04f5
            com.google.android.gms.internal.measurement.zzfu[] r12 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            int r13 = r10 + 1
            r26 = r3
            int r3 = r11.length     // Catch:{ all -> 0x0df9 }
            int r3 = r3 - r10
            java.lang.System.arraycopy(r12, r13, r11, r10, r3)     // Catch:{ all -> 0x0df9 }
            goto L_0x04f7
        L_0x04f5:
            r26 = r3
        L_0x04f7:
            r6.zzaxc = r11     // Catch:{ all -> 0x0df9 }
            goto L_0x0501
        L_0x04fa:
            r26 = r3
            int r10 = r10 + 1
            goto L_0x04c4
        L_0x04ff:
            r26 = r3
        L_0x0501:
            r12 = r21
            goto L_0x0507
        L_0x0504:
            r26 = r3
            r12 = 1
        L_0x0507:
            java.lang.String r3 = r6.name     // Catch:{ all -> 0x0df9 }
            boolean r3 = com.google.android.gms.measurement.internal.zzfx.zzct(r3)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x05ce
            if (r7 == 0) goto L_0x05ce
            com.google.android.gms.measurement.internal.zzt r29 = r45.zzjt()     // Catch:{ all -> 0x0df9 }
            long r30 = r45.zzly()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r3 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r3 = r3.zztt     // Catch:{ all -> 0x0df9 }
            r33 = 0
            r34 = 0
            r35 = 1
            r36 = 0
            r37 = 0
            r32 = r3
            com.google.android.gms.measurement.internal.zzu r3 = r29.zza(r30, r32, r33, r34, r35, r36, r37)     // Catch:{ all -> 0x0df9 }
            long r10 = r3.zzahj     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzq r3 = r3.zzgv()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r13 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r13 = r13.zztt     // Catch:{ all -> 0x0df9 }
            r18 = r12
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzai.zzajh     // Catch:{ all -> 0x0df9 }
            int r3 = r3.zzb(r13, r12)     // Catch:{ all -> 0x0df9 }
            long r12 = (long) r3     // Catch:{ all -> 0x0df9 }
            int r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r3 <= 0) goto L_0x05cb
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r3 = r3.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjj()     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzfw r11 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = r11.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzas.zzbw(r11)     // Catch:{ all -> 0x0df9 }
            r3.zzg(r10, r11)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r3 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            int r10 = r3.length     // Catch:{ all -> 0x0df9 }
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0563:
            if (r11 >= r10) goto L_0x058d
            r19 = r10
            r10 = r3[r11]     // Catch:{ all -> 0x0df9 }
            r21 = r3
            java.lang.String r3 = "_c"
            r29 = r14
            java.lang.String r14 = r10.name     // Catch:{ all -> 0x0df9 }
            boolean r3 = r3.equals(r14)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x0579
            r13 = r10
            goto L_0x0584
        L_0x0579:
            java.lang.String r3 = "_err"
            java.lang.String r10 = r10.name     // Catch:{ all -> 0x0df9 }
            boolean r3 = r3.equals(r10)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x0584
            r12 = 1
        L_0x0584:
            int r11 = r11 + 1
            r10 = r19
            r3 = r21
            r14 = r29
            goto L_0x0563
        L_0x058d:
            r29 = r14
            if (r12 == 0) goto L_0x05a4
            if (r13 == 0) goto L_0x05a4
            com.google.android.gms.internal.measurement.zzfu[] r3 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            r10 = 1
            com.google.android.gms.internal.measurement.zzfu[] r11 = new com.google.android.gms.internal.measurement.zzfu[r10]     // Catch:{ all -> 0x0df9 }
            r10 = 0
            r11[r10] = r13     // Catch:{ all -> 0x0df9 }
            java.lang.Object[] r3 = com.google.android.gms.common.util.ArrayUtils.removeAll(r3, r11)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r3 = (com.google.android.gms.internal.measurement.zzfu[]) r3     // Catch:{ all -> 0x0df9 }
            r6.zzaxc = r3     // Catch:{ all -> 0x0df9 }
            goto L_0x05d2
        L_0x05a4:
            if (r13 == 0) goto L_0x05b3
            java.lang.String r3 = "_err"
            r13.name = r3     // Catch:{ all -> 0x0df9 }
            r10 = 10
            java.lang.Long r3 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0df9 }
            r13.zzaxg = r3     // Catch:{ all -> 0x0df9 }
            goto L_0x05d2
        L_0x05b3:
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r3 = r3.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzfw r11 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = r11.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzas.zzbw(r11)     // Catch:{ all -> 0x0df9 }
            r3.zzg(r10, r11)     // Catch:{ all -> 0x0df9 }
            goto L_0x05d2
        L_0x05cb:
            r29 = r14
            goto L_0x05d2
        L_0x05ce:
            r18 = r12
            r29 = r14
        L_0x05d2:
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzq r3 = r3.zzgv()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r10 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = r10.zztt     // Catch:{ all -> 0x0df9 }
            boolean r3 = r3.zzbd(r10)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x0697
            if (r7 == 0) goto L_0x0697
            com.google.android.gms.internal.measurement.zzfu[] r3 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            r7 = 0
            r10 = -1
            r11 = -1
        L_0x05e9:
            int r12 = r3.length     // Catch:{ all -> 0x0df9 }
            if (r7 >= r12) goto L_0x060b
            java.lang.String r12 = "value"
            r13 = r3[r7]     // Catch:{ all -> 0x0df9 }
            java.lang.String r13 = r13.name     // Catch:{ all -> 0x0df9 }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0df9 }
            if (r12 == 0) goto L_0x05fb
            r10 = r7
            goto L_0x0608
        L_0x05fb:
            java.lang.String r12 = "currency"
            r13 = r3[r7]     // Catch:{ all -> 0x0df9 }
            java.lang.String r13 = r13.name     // Catch:{ all -> 0x0df9 }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0df9 }
            if (r12 == 0) goto L_0x0608
            r11 = r7
        L_0x0608:
            int r7 = r7 + 1
            goto L_0x05e9
        L_0x060b:
            r7 = -1
            if (r10 == r7) goto L_0x0693
            r7 = r3[r10]     // Catch:{ all -> 0x0df9 }
            java.lang.Long r7 = r7.zzaxg     // Catch:{ all -> 0x0df9 }
            if (r7 != 0) goto L_0x063e
            r7 = r3[r10]     // Catch:{ all -> 0x0df9 }
            java.lang.Double r7 = r7.zzaup     // Catch:{ all -> 0x0df9 }
            if (r7 != 0) goto L_0x063e
            com.google.android.gms.measurement.internal.zzbw r7 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r7 = r7.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r7 = r7.zzjl()     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = "Value must be specified with a numeric type."
            r7.zzby(r11)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r3 = zza(r3, r10)     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = "_c"
            com.google.android.gms.internal.measurement.zzfu[] r3 = zza(r3, r7)     // Catch:{ all -> 0x0df9 }
            r7 = 18
            java.lang.String r10 = "value"
            com.google.android.gms.internal.measurement.zzfu[] r3 = zza(r3, r7, r10)     // Catch:{ all -> 0x0df9 }
            r12 = 3
            goto L_0x0694
        L_0x063e:
            r7 = -1
            if (r11 != r7) goto L_0x0644
            r7 = 1
            r12 = 3
            goto L_0x066f
        L_0x0644:
            r7 = r3[r11]     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = r7.zzamn     // Catch:{ all -> 0x0df9 }
            if (r7 == 0) goto L_0x066d
            int r11 = r7.length()     // Catch:{ all -> 0x0df9 }
            r12 = 3
            if (r11 == r12) goto L_0x0652
            goto L_0x066e
        L_0x0652:
            r11 = 0
        L_0x0653:
            int r13 = r7.length()     // Catch:{ all -> 0x0df9 }
            if (r11 >= r13) goto L_0x066b
            int r13 = r7.codePointAt(r11)     // Catch:{ all -> 0x0df9 }
            boolean r14 = java.lang.Character.isLetter(r13)     // Catch:{ all -> 0x0df9 }
            if (r14 != 0) goto L_0x0665
            r7 = 1
            goto L_0x066f
        L_0x0665:
            int r13 = java.lang.Character.charCount(r13)     // Catch:{ all -> 0x0df9 }
            int r11 = r11 + r13
            goto L_0x0653
        L_0x066b:
            r7 = 0
            goto L_0x066f
        L_0x066d:
            r12 = 3
        L_0x066e:
            r7 = 1
        L_0x066f:
            if (r7 == 0) goto L_0x0694
            com.google.android.gms.measurement.internal.zzbw r7 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r7 = r7.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r7 = r7.zzjl()     // Catch:{ all -> 0x0df9 }
            java.lang.String r11 = "Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter."
            r7.zzby(r11)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r3 = zza(r3, r10)     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = "_c"
            com.google.android.gms.internal.measurement.zzfu[] r3 = zza(r3, r7)     // Catch:{ all -> 0x0df9 }
            r7 = 19
            java.lang.String r10 = "currency"
            com.google.android.gms.internal.measurement.zzfu[] r3 = zza(r3, r7, r10)     // Catch:{ all -> 0x0df9 }
            goto L_0x0694
        L_0x0693:
            r12 = 3
        L_0x0694:
            r6.zzaxc = r3     // Catch:{ all -> 0x0df9 }
            goto L_0x0698
        L_0x0697:
            r12 = 3
        L_0x0698:
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzq r3 = r3.zzgv()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r7 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = r7.zztt     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzai$zza<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzai.zzalb     // Catch:{ all -> 0x0df9 }
            boolean r3 = r3.zze(r7, r10)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x071a
            java.lang.String r3 = "_e"
            java.lang.String r7 = r6.name     // Catch:{ all -> 0x0df9 }
            boolean r3 = r3.equals(r7)     // Catch:{ all -> 0x0df9 }
            r10 = 1000(0x3e8, double:4.94E-321)
            if (r3 == 0) goto L_0x06e3
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            java.lang.String r3 = "_fr"
            com.google.android.gms.internal.measurement.zzfu r3 = com.google.android.gms.measurement.internal.zzft.zza(r6, r3)     // Catch:{ all -> 0x0df9 }
            if (r3 != 0) goto L_0x071a
            if (r9 == 0) goto L_0x06e1
            java.lang.Long r3 = r9.zzaxd     // Catch:{ all -> 0x0df9 }
            long r7 = r3.longValue()     // Catch:{ all -> 0x0df9 }
            java.lang.Long r3 = r6.zzaxd     // Catch:{ all -> 0x0df9 }
            long r13 = r3.longValue()     // Catch:{ all -> 0x0df9 }
            long r7 = r7 - r13
            long r7 = java.lang.Math.abs(r7)     // Catch:{ all -> 0x0df9 }
            int r3 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r3 > 0) goto L_0x06e1
            boolean r3 = r1.zza(r6, r9)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x06e1
            r8 = 0
            r9 = 0
            goto L_0x071a
        L_0x06e1:
            r8 = r6
            goto L_0x071a
        L_0x06e3:
            java.lang.String r3 = "_vs"
            java.lang.String r7 = r6.name     // Catch:{ all -> 0x0df9 }
            boolean r3 = r3.equals(r7)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x071a
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            java.lang.String r3 = "_et"
            com.google.android.gms.internal.measurement.zzfu r3 = com.google.android.gms.measurement.internal.zzft.zza(r6, r3)     // Catch:{ all -> 0x0df9 }
            if (r3 != 0) goto L_0x071a
            if (r8 == 0) goto L_0x0719
            java.lang.Long r3 = r8.zzaxd     // Catch:{ all -> 0x0df9 }
            long r13 = r3.longValue()     // Catch:{ all -> 0x0df9 }
            java.lang.Long r3 = r6.zzaxd     // Catch:{ all -> 0x0df9 }
            long r27 = r3.longValue()     // Catch:{ all -> 0x0df9 }
            long r13 = r13 - r27
            long r13 = java.lang.Math.abs(r13)     // Catch:{ all -> 0x0df9 }
            int r3 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r3 > 0) goto L_0x0719
            boolean r3 = r1.zza(r8, r6)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x0719
            r8 = 0
            r9 = 0
            goto L_0x071a
        L_0x0719:
            r9 = r6
        L_0x071a:
            if (r4 == 0) goto L_0x0779
            if (r5 != 0) goto L_0x0779
            java.lang.String r3 = "_e"
            java.lang.String r7 = r6.name     // Catch:{ all -> 0x0df9 }
            boolean r3 = r3.equals(r7)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x0779
            com.google.android.gms.internal.measurement.zzfu[] r3 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x0762
            com.google.android.gms.internal.measurement.zzfu[] r3 = r6.zzaxc     // Catch:{ all -> 0x0df9 }
            int r3 = r3.length     // Catch:{ all -> 0x0df9 }
            if (r3 != 0) goto L_0x0732
            goto L_0x0762
        L_0x0732:
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            java.lang.String r3 = "_et"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzft.zzb(r6, r3)     // Catch:{ all -> 0x0df9 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x0df9 }
            if (r3 != 0) goto L_0x0757
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r3 = r3.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjj()     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = "Engagement event does not include duration. appId"
            com.google.android.gms.internal.measurement.zzfw r10 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = r10.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzas.zzbw(r10)     // Catch:{ all -> 0x0df9 }
            r3.zzg(r7, r10)     // Catch:{ all -> 0x0df9 }
            goto L_0x0779
        L_0x0757:
            long r10 = r3.longValue()     // Catch:{ all -> 0x0df9 }
            long r14 = r29 + r10
            r29 = r14
            r3 = r26
            goto L_0x077b
        L_0x0762:
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r3 = r3.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjj()     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = "Engagement event does not contain any parameters. appId"
            com.google.android.gms.internal.measurement.zzfw r10 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r10 = r10.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzas.zzbw(r10)     // Catch:{ all -> 0x0df9 }
            r3.zzg(r7, r10)     // Catch:{ all -> 0x0df9 }
        L_0x0779:
            r3 = r26
        L_0x077b:
            com.google.android.gms.internal.measurement.zzft[] r7 = r3.zzaxk     // Catch:{ all -> 0x0df9 }
            int r13 = r25 + 1
            r7[r25] = r6     // Catch:{ all -> 0x0df9 }
            r14 = r29
        L_0x0783:
            int r10 = r23 + 1
            r12 = r18
            r11 = 0
            goto L_0x02d4
        L_0x078a:
            r21 = r12
            r25 = r13
            r29 = r14
            if (r5 == 0) goto L_0x07ed
            r13 = r25
            r14 = r29
            r5 = 0
        L_0x0797:
            if (r5 >= r13) goto L_0x07ea
            com.google.android.gms.internal.measurement.zzft[] r6 = r3.zzaxk     // Catch:{ all -> 0x0df9 }
            r6 = r6[r5]     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = "_e"
            java.lang.String r8 = r6.name     // Catch:{ all -> 0x0df9 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0df9 }
            if (r7 == 0) goto L_0x07c5
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = "_fr"
            com.google.android.gms.internal.measurement.zzfu r7 = com.google.android.gms.measurement.internal.zzft.zza(r6, r7)     // Catch:{ all -> 0x0df9 }
            if (r7 == 0) goto L_0x07c5
            com.google.android.gms.internal.measurement.zzft[] r6 = r3.zzaxk     // Catch:{ all -> 0x0df9 }
            int r7 = r5 + 1
            com.google.android.gms.internal.measurement.zzft[] r8 = r3.zzaxk     // Catch:{ all -> 0x0df9 }
            int r9 = r13 - r5
            r10 = 1
            int r9 = r9 - r10
            java.lang.System.arraycopy(r6, r7, r8, r5, r9)     // Catch:{ all -> 0x0df9 }
            int r13 = r13 + -1
            int r5 = r5 + -1
            r6 = 1
            goto L_0x07e8
        L_0x07c5:
            if (r4 == 0) goto L_0x07e7
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = "_et"
            com.google.android.gms.internal.measurement.zzfu r6 = com.google.android.gms.measurement.internal.zzft.zza(r6, r7)     // Catch:{ all -> 0x0df9 }
            if (r6 == 0) goto L_0x07e7
            java.lang.Long r6 = r6.zzaxg     // Catch:{ all -> 0x0df9 }
            if (r6 == 0) goto L_0x07e7
            long r7 = r6.longValue()     // Catch:{ all -> 0x0df9 }
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x07e7
            long r6 = r6.longValue()     // Catch:{ all -> 0x0df9 }
            long r14 = r14 + r6
            r6 = 1
            goto L_0x07e8
        L_0x07e7:
            r6 = 1
        L_0x07e8:
            int r5 = r5 + r6
            goto L_0x0797
        L_0x07ea:
            r29 = r14
            goto L_0x07ef
        L_0x07ed:
            r13 = r25
        L_0x07ef:
            java.util.List<com.google.android.gms.internal.measurement.zzft> r5 = r2.zzauk     // Catch:{ all -> 0x0df9 }
            int r5 = r5.size()     // Catch:{ all -> 0x0df9 }
            if (r13 >= r5) goto L_0x0801
            com.google.android.gms.internal.measurement.zzft[] r5 = r3.zzaxk     // Catch:{ all -> 0x0df9 }
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r13)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzft[] r5 = (com.google.android.gms.internal.measurement.zzft[]) r5     // Catch:{ all -> 0x0df9 }
            r3.zzaxk = r5     // Catch:{ all -> 0x0df9 }
        L_0x0801:
            if (r4 == 0) goto L_0x08d3
            com.google.android.gms.measurement.internal.zzt r4 = r45.zzjt()     // Catch:{ all -> 0x0df9 }
            java.lang.String r5 = r3.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.String r6 = "_lte"
            com.google.android.gms.measurement.internal.zzfw r4 = r4.zzi(r5, r6)     // Catch:{ all -> 0x0df9 }
            if (r4 == 0) goto L_0x083c
            java.lang.Object r5 = r4.value     // Catch:{ all -> 0x0df9 }
            if (r5 != 0) goto L_0x0816
            goto L_0x083c
        L_0x0816:
            com.google.android.gms.measurement.internal.zzfw r5 = new com.google.android.gms.measurement.internal.zzfw     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = r3.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.String r8 = "auto"
            java.lang.String r9 = "_lte"
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.common.util.Clock r6 = r6.zzbx()     // Catch:{ all -> 0x0df9 }
            long r10 = r6.currentTimeMillis()     // Catch:{ all -> 0x0df9 }
            java.lang.Object r4 = r4.value     // Catch:{ all -> 0x0df9 }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ all -> 0x0df9 }
            long r12 = r4.longValue()     // Catch:{ all -> 0x0df9 }
            long r12 = r12 + r29
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0df9 }
            r6 = r5
            r6.<init>(r7, r8, r9, r10, r12)     // Catch:{ all -> 0x0df9 }
            r4 = r5
            goto L_0x0859
        L_0x083c:
            com.google.android.gms.measurement.internal.zzfw r4 = new com.google.android.gms.measurement.internal.zzfw     // Catch:{ all -> 0x0df9 }
            java.lang.String r5 = r3.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.String r33 = "auto"
            java.lang.String r34 = "_lte"
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.common.util.Clock r6 = r6.zzbx()     // Catch:{ all -> 0x0df9 }
            long r35 = r6.currentTimeMillis()     // Catch:{ all -> 0x0df9 }
            java.lang.Long r37 = java.lang.Long.valueOf(r29)     // Catch:{ all -> 0x0df9 }
            r31 = r4
            r32 = r5
            r31.<init>(r32, r33, r34, r35, r37)     // Catch:{ all -> 0x0df9 }
        L_0x0859:
            com.google.android.gms.internal.measurement.zzfz r5 = new com.google.android.gms.internal.measurement.zzfz     // Catch:{ all -> 0x0df9 }
            r5.<init>()     // Catch:{ all -> 0x0df9 }
            java.lang.String r6 = "_lte"
            r5.name = r6     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.common.util.Clock r6 = r6.zzbx()     // Catch:{ all -> 0x0df9 }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x0df9 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0df9 }
            r5.zzayw = r6     // Catch:{ all -> 0x0df9 }
            java.lang.Object r6 = r4.value     // Catch:{ all -> 0x0df9 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ all -> 0x0df9 }
            r5.zzaxg = r6     // Catch:{ all -> 0x0df9 }
            r6 = 0
        L_0x0879:
            com.google.android.gms.internal.measurement.zzfz[] r7 = r3.zzaxl     // Catch:{ all -> 0x0df9 }
            int r7 = r7.length     // Catch:{ all -> 0x0df9 }
            if (r6 >= r7) goto L_0x0895
            java.lang.String r7 = "_lte"
            com.google.android.gms.internal.measurement.zzfz[] r8 = r3.zzaxl     // Catch:{ all -> 0x0df9 }
            r8 = r8[r6]     // Catch:{ all -> 0x0df9 }
            java.lang.String r8 = r8.name     // Catch:{ all -> 0x0df9 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0df9 }
            if (r7 == 0) goto L_0x0892
            com.google.android.gms.internal.measurement.zzfz[] r7 = r3.zzaxl     // Catch:{ all -> 0x0df9 }
            r7[r6] = r5     // Catch:{ all -> 0x0df9 }
            r6 = 1
            goto L_0x0896
        L_0x0892:
            int r6 = r6 + 1
            goto L_0x0879
        L_0x0895:
            r6 = 0
        L_0x0896:
            if (r6 != 0) goto L_0x08b5
            com.google.android.gms.internal.measurement.zzfz[] r6 = r3.zzaxl     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfz[] r7 = r3.zzaxl     // Catch:{ all -> 0x0df9 }
            int r7 = r7.length     // Catch:{ all -> 0x0df9 }
            r8 = 1
            int r7 = r7 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfz[] r6 = (com.google.android.gms.internal.measurement.zzfz[]) r6     // Catch:{ all -> 0x0df9 }
            r3.zzaxl = r6     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfz[] r6 = r3.zzaxl     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r7 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfz[] r7 = r7.zzaxl     // Catch:{ all -> 0x0df9 }
            int r7 = r7.length     // Catch:{ all -> 0x0df9 }
            r8 = 1
            int r7 = r7 - r8
            r6[r7] = r5     // Catch:{ all -> 0x0df9 }
            r5 = 0
            goto L_0x08b7
        L_0x08b5:
            r5 = 0
        L_0x08b7:
            int r7 = (r29 > r5 ? 1 : (r29 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x08d3
            com.google.android.gms.measurement.internal.zzt r5 = r45.zzjt()     // Catch:{ all -> 0x0df9 }
            r5.zza(r4)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzbw r5 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r5 = r5.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r5 = r5.zzjn()     // Catch:{ all -> 0x0df9 }
            java.lang.String r6 = "Updated lifetime engagement user property with value. Value"
            java.lang.Object r4 = r4.value     // Catch:{ all -> 0x0df9 }
            r5.zzg(r6, r4)     // Catch:{ all -> 0x0df9 }
        L_0x08d3:
            java.lang.String r4 = r3.zztt     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfz[] r5 = r3.zzaxl     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzft[] r6 = r3.zzaxk     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzm r7 = r45.zzjs()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfr[] r4 = r7.zza(r4, r6, r5)     // Catch:{ all -> 0x0df9 }
            r3.zzayc = r4     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzbw r4 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzq r4 = r4.zzgv()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r5 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r5 = r5.zztt     // Catch:{ all -> 0x0df9 }
            boolean r4 = r4.zzat(r5)     // Catch:{ all -> 0x0df9 }
            if (r4 == 0) goto L_0x0c11
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ all -> 0x0c0b }
            r4.<init>()     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzft[] r5 = r3.zzaxk     // Catch:{ all -> 0x0c0b }
            int r5 = r5.length     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzft[] r5 = new com.google.android.gms.internal.measurement.zzft[r5]     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.measurement.internal.zzfx r6 = r6.zzgr()     // Catch:{ all -> 0x0c0b }
            java.security.SecureRandom r6 = r6.zzmk()     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzft[] r7 = r3.zzaxk     // Catch:{ all -> 0x0c0b }
            int r8 = r7.length     // Catch:{ all -> 0x0c0b }
            r9 = 0
            r10 = 0
        L_0x090f:
            if (r9 >= r8) goto L_0x0bda
            r11 = r7[r9]     // Catch:{ all -> 0x0c0b }
            java.lang.String r12 = r11.name     // Catch:{ all -> 0x0c0b }
            java.lang.String r13 = "_ep"
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0c0b }
            if (r12 == 0) goto L_0x0998
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            java.lang.String r12 = "_en"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzft.zzb(r11, r12)     // Catch:{ all -> 0x0df9 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0df9 }
            java.lang.Object r13 = r4.get(r12)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzac r13 = (com.google.android.gms.measurement.internal.zzac) r13     // Catch:{ all -> 0x0df9 }
            if (r13 != 0) goto L_0x093f
            com.google.android.gms.measurement.internal.zzt r13 = r45.zzjt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r14 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r14 = r14.zztt     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzac r13 = r13.zzg(r14, r12)     // Catch:{ all -> 0x0df9 }
            r4.put(r12, r13)     // Catch:{ all -> 0x0df9 }
        L_0x093f:
            java.lang.Long r12 = r13.zzaia     // Catch:{ all -> 0x0df9 }
            if (r12 != 0) goto L_0x098b
            java.lang.Long r12 = r13.zzaib     // Catch:{ all -> 0x0df9 }
            long r14 = r12.longValue()     // Catch:{ all -> 0x0df9 }
            int r12 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r12 <= 0) goto L_0x095c
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r12 = r11.zzaxc     // Catch:{ all -> 0x0df9 }
            java.lang.String r14 = "_sr"
            java.lang.Long r15 = r13.zzaib     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r12 = com.google.android.gms.measurement.internal.zzft.zza(r12, r14, r15)     // Catch:{ all -> 0x0df9 }
            r11.zzaxc = r12     // Catch:{ all -> 0x0df9 }
        L_0x095c:
            java.lang.Boolean r12 = r13.zzaic     // Catch:{ all -> 0x0df9 }
            if (r12 == 0) goto L_0x0979
            java.lang.Boolean r12 = r13.zzaic     // Catch:{ all -> 0x0df9 }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x0df9 }
            if (r12 == 0) goto L_0x0979
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r12 = r11.zzaxc     // Catch:{ all -> 0x0df9 }
            java.lang.String r13 = "_efs"
            java.lang.Long r14 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r12 = com.google.android.gms.measurement.internal.zzft.zza(r12, r13, r14)     // Catch:{ all -> 0x0df9 }
            r11.zzaxc = r12     // Catch:{ all -> 0x0df9 }
        L_0x0979:
            int r12 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0df9 }
            r26 = r3
            r46 = r6
            r23 = r7
            r25 = r8
            r27 = r9
            r10 = r12
            r9 = r2
            goto L_0x0bca
        L_0x098b:
            r26 = r3
            r46 = r6
            r23 = r7
            r25 = r8
            r27 = r9
            r9 = r2
            goto L_0x0bca
        L_0x0998:
            com.google.android.gms.measurement.internal.zzbq r12 = r45.zzls()     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzfw r13 = r2.zzaui     // Catch:{ all -> 0x0c0b }
            java.lang.String r13 = r13.zztt     // Catch:{ all -> 0x0c0b }
            long r12 = r12.zzck(r13)     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.measurement.internal.zzbw r14 = r1.zzada     // Catch:{ all -> 0x0c0b }
            r14.zzgr()     // Catch:{ all -> 0x0c0b }
            java.lang.Long r14 = r11.zzaxd     // Catch:{ all -> 0x0c0b }
            long r14 = r14.longValue()     // Catch:{ all -> 0x0c0b }
            long r14 = com.google.android.gms.measurement.internal.zzfx.zzc(r14, r12)     // Catch:{ all -> 0x0c0b }
            r23 = r7
            java.lang.String r7 = "_dbg"
            r25 = r8
            java.lang.Long r8 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x0c0b }
            boolean r26 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0c0b }
            if (r26 != 0) goto L_0x0a0d
            if (r8 != 0) goto L_0x09c6
            goto L_0x0a0d
        L_0x09c6:
            r26 = r3
            com.google.android.gms.internal.measurement.zzfu[] r3 = r11.zzaxc     // Catch:{ all -> 0x0df9 }
            r27 = r9
            int r9 = r3.length     // Catch:{ all -> 0x0df9 }
            r28 = r12
            r12 = 0
        L_0x09d0:
            if (r12 >= r9) goto L_0x0a0b
            r13 = r3[r12]     // Catch:{ all -> 0x0df9 }
            r30 = r3
            java.lang.String r3 = r13.name     // Catch:{ all -> 0x0df9 }
            boolean r3 = r7.equals(r3)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x0a06
            boolean r3 = r8 instanceof java.lang.Long     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x09ea
            java.lang.Long r3 = r13.zzaxg     // Catch:{ all -> 0x0df9 }
            boolean r3 = r8.equals(r3)     // Catch:{ all -> 0x0df9 }
            if (r3 != 0) goto L_0x0a02
        L_0x09ea:
            boolean r3 = r8 instanceof java.lang.String     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x09f6
            java.lang.String r3 = r13.zzamn     // Catch:{ all -> 0x0df9 }
            boolean r3 = r8.equals(r3)     // Catch:{ all -> 0x0df9 }
            if (r3 != 0) goto L_0x0a02
        L_0x09f6:
            boolean r3 = r8 instanceof java.lang.Double     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x0a04
            java.lang.Double r3 = r13.zzaup     // Catch:{ all -> 0x0df9 }
            boolean r3 = r8.equals(r3)     // Catch:{ all -> 0x0df9 }
            if (r3 == 0) goto L_0x0a04
        L_0x0a02:
            r3 = 1
            goto L_0x0a14
        L_0x0a04:
            r3 = 0
            goto L_0x0a14
        L_0x0a06:
            int r12 = r12 + 1
            r3 = r30
            goto L_0x09d0
        L_0x0a0b:
            r3 = 0
            goto L_0x0a14
        L_0x0a0d:
            r26 = r3
            r27 = r9
            r28 = r12
            r3 = 0
        L_0x0a14:
            if (r3 != 0) goto L_0x0a25
            com.google.android.gms.measurement.internal.zzbq r3 = r45.zzls()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r7 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = r7.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0df9 }
            int r12 = r3.zzq(r7, r8)     // Catch:{ all -> 0x0df9 }
            goto L_0x0a26
        L_0x0a25:
            r12 = 1
        L_0x0a26:
            if (r12 > 0) goto L_0x0a47
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r3 = r3.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjj()     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = "Sample rate must be positive. event, rate"
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0df9 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x0df9 }
            r3.zze(r7, r8, r9)     // Catch:{ all -> 0x0df9 }
            int r3 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0df9 }
            r9 = r2
            r10 = r3
            r46 = r6
            goto L_0x0bca
        L_0x0a47:
            java.lang.String r3 = r11.name     // Catch:{ all -> 0x0c0b }
            java.lang.Object r3 = r4.get(r3)     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.measurement.internal.zzac r3 = (com.google.android.gms.measurement.internal.zzac) r3     // Catch:{ all -> 0x0c0b }
            if (r3 != 0) goto L_0x0a9b
            com.google.android.gms.measurement.internal.zzt r3 = r45.zzjt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r7 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = r7.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzac r3 = r3.zzg(r7, r8)     // Catch:{ all -> 0x0df9 }
            if (r3 != 0) goto L_0x0a9b
            com.google.android.gms.measurement.internal.zzbw r3 = r1.zzada     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzas r3 = r3.zzgt()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjj()     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzfw r8 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r8 = r8.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.String r9 = r11.name     // Catch:{ all -> 0x0df9 }
            r3.zze(r7, r8, r9)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzac r3 = new com.google.android.gms.measurement.internal.zzac     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfw r7 = r2.zzaui     // Catch:{ all -> 0x0df9 }
            java.lang.String r7 = r7.zztt     // Catch:{ all -> 0x0df9 }
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0df9 }
            r33 = 1
            r35 = 1
            java.lang.Long r9 = r11.zzaxd     // Catch:{ all -> 0x0df9 }
            long r37 = r9.longValue()     // Catch:{ all -> 0x0df9 }
            r39 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r44 = 0
            r30 = r3
            r31 = r7
            r32 = r8
            r30.<init>(r31, r32, r33, r35, r37, r39, r41, r42, r43, r44)     // Catch:{ all -> 0x0df9 }
        L_0x0a9b:
            r45.zzjr()     // Catch:{ all -> 0x0c0b }
            java.lang.String r7 = "_eid"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzft.zzb(r11, r7)     // Catch:{ all -> 0x0c0b }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x0c0b }
            if (r7 == 0) goto L_0x0aaa
            r8 = 1
            goto L_0x0aab
        L_0x0aaa:
            r8 = 0
        L_0x0aab:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ all -> 0x0c0b }
            r9 = 1
            if (r12 != r9) goto L_0x0ad8
            int r7 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0df9 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0df9 }
            if (r8 == 0) goto L_0x0ad2
            java.lang.Long r8 = r3.zzaia     // Catch:{ all -> 0x0df9 }
            if (r8 != 0) goto L_0x0ac8
            java.lang.Long r8 = r3.zzaib     // Catch:{ all -> 0x0df9 }
            if (r8 != 0) goto L_0x0ac8
            java.lang.Boolean r8 = r3.zzaic     // Catch:{ all -> 0x0df9 }
            if (r8 == 0) goto L_0x0ad2
        L_0x0ac8:
            r8 = 0
            com.google.android.gms.measurement.internal.zzac r3 = r3.zza(r8, r8, r8)     // Catch:{ all -> 0x0df9 }
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0df9 }
            r4.put(r8, r3)     // Catch:{ all -> 0x0df9 }
        L_0x0ad2:
            r9 = r2
            r46 = r6
            r10 = r7
            goto L_0x0bca
        L_0x0ad8:
            int r9 = r6.nextInt(r12)     // Catch:{ all -> 0x0c0b }
            if (r9 != 0) goto L_0x0b18
            r45.zzjr()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r7 = r11.zzaxc     // Catch:{ all -> 0x0df9 }
            java.lang.String r9 = "_sr"
            long r12 = (long) r12     // Catch:{ all -> 0x0df9 }
            r46 = r6
            java.lang.Long r6 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.internal.measurement.zzfu[] r6 = com.google.android.gms.measurement.internal.zzft.zza(r7, r9, r6)     // Catch:{ all -> 0x0df9 }
            r11.zzaxc = r6     // Catch:{ all -> 0x0df9 }
            int r6 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0df9 }
            boolean r7 = r8.booleanValue()     // Catch:{ all -> 0x0df9 }
            if (r7 == 0) goto L_0x0b05
            java.lang.Long r7 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0df9 }
            r8 = 0
            com.google.android.gms.measurement.internal.zzac r3 = r3.zza(r8, r7, r8)     // Catch:{ all -> 0x0df9 }
        L_0x0b05:
            java.lang.String r7 = r11.name     // Catch:{ all -> 0x0df9 }
            java.lang.Long r8 = r11.zzaxd     // Catch:{ all -> 0x0df9 }
            long r8 = r8.longValue()     // Catch:{ all -> 0x0df9 }
            com.google.android.gms.measurement.internal.zzac r3 = r3.zza(r8, r14)     // Catch:{ all -> 0x0df9 }
            r4.put(r7, r3)     // Catch:{ all -> 0x0df9 }
            r9 = r2
            r10 = r6
            goto L_0x0bca
        L_0x0b18:
            r46 = r6
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.measurement.internal.zzq r6 = r6.zzgv()     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzfw r9 = r2.zzaui     // Catch:{ all -> 0x0c0b }
            java.lang.String r9 = r9.zztt     // Catch:{ all -> 0x0c0b }
            boolean r6 = r6.zzbf(r9)     // Catch:{ all -> 0x0c0b }
            if (r6 == 0) goto L_0x0b52
            java.lang.Long r6 = r3.zzahz     // Catch:{ all -> 0x0c0b }
            if (r6 == 0) goto L_0x0b37
            java.lang.Long r6 = r3.zzahz     // Catch:{ all -> 0x0df9 }
            long r28 = r6.longValue()     // Catch:{ all -> 0x0df9 }
            r9 = r2
            r13 = r7
            goto L_0x0b4a
        L_0x0b37:
            com.google.android.gms.measurement.internal.zzbw r6 = r1.zzada     // Catch:{ all -> 0x0c0b }
            r6.zzgr()     // Catch:{ all -> 0x0c0b }
            java.lang.Long r6 = r11.zzaxe     // Catch:{ all -> 0x0c0b }
            r9 = r2
            long r1 = r6.longValue()     // Catch:{ all -> 0x0c0b }
            r13 = r7
            r6 = r28
            long r28 = com.google.android.gms.measurement.internal.zzfx.zzc(r1, r6)     // Catch:{ all -> 0x0c0b }
        L_0x0b4a:
            int r1 = (r28 > r14 ? 1 : (r28 == r14 ? 0 : -1))
            if (r1 == 0) goto L_0x0b50
            r1 = 1
            goto L_0x0b6b
        L_0x0b50:
            r1 = 0
            goto L_0x0b6b
        L_0x0b52:
            r9 = r2
            r13 = r7
            long r1 = r3.zzahy     // Catch:{ all -> 0x0c0b }
            java.lang.Long r6 = r11.zzaxd     // Catch:{ all -> 0x0c0b }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0c0b }
            long r6 = r6 - r1
            long r1 = java.lang.Math.abs(r6)     // Catch:{ all -> 0x0c0b }
            r6 = 86400000(0x5265c00, double:4.2687272E-316)
            int r28 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r28 < 0) goto L_0x0b6a
            r1 = 1
            goto L_0x0b6b
        L_0x0b6a:
            r1 = 0
        L_0x0b6b:
            if (r1 == 0) goto L_0x0bb9
            r45.zzjr()     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzfu[] r1 = r11.zzaxc     // Catch:{ all -> 0x0c0b }
            java.lang.String r2 = "_efs"
            java.lang.Long r6 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzfu[] r1 = com.google.android.gms.measurement.internal.zzft.zza(r1, r2, r6)     // Catch:{ all -> 0x0c0b }
            r11.zzaxc = r1     // Catch:{ all -> 0x0c0b }
            r45.zzjr()     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzfu[] r1 = r11.zzaxc     // Catch:{ all -> 0x0c0b }
            java.lang.String r2 = "_sr"
            long r6 = (long) r12     // Catch:{ all -> 0x0c0b }
            java.lang.Long r12 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzfu[] r1 = com.google.android.gms.measurement.internal.zzft.zza(r1, r2, r12)     // Catch:{ all -> 0x0c0b }
            r11.zzaxc = r1     // Catch:{ all -> 0x0c0b }
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0c0b }
            boolean r2 = r8.booleanValue()     // Catch:{ all -> 0x0c0b }
            if (r2 == 0) goto L_0x0ba8
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0c0b }
            r6 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x0c0b }
            r6 = 0
            com.google.android.gms.measurement.internal.zzac r3 = r3.zza(r6, r2, r7)     // Catch:{ all -> 0x0c0b }
        L_0x0ba8:
            java.lang.String r2 = r11.name     // Catch:{ all -> 0x0c0b }
            java.lang.Long r6 = r11.zzaxd     // Catch:{ all -> 0x0c0b }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.measurement.internal.zzac r3 = r3.zza(r6, r14)     // Catch:{ all -> 0x0c0b }
            r4.put(r2, r3)     // Catch:{ all -> 0x0c0b }
            r10 = r1
            goto L_0x0bca
        L_0x0bb9:
            boolean r1 = r8.booleanValue()     // Catch:{ all -> 0x0c0b }
            if (r1 == 0) goto L_0x0bca
            java.lang.String r1 = r11.name     // Catch:{ all -> 0x0c0b }
            r7 = r13
            r2 = 0
            com.google.android.gms.measurement.internal.zzac r3 = r3.zza(r7, r2, r2)     // Catch:{ all -> 0x0c0b }
            r4.put(r1, r3)     // Catch:{ all -> 0x0c0b }
        L_0x0bca:
            int r1 = r27 + 1
            r6 = r46
            r2 = r9
            r7 = r23
            r8 = r25
            r3 = r26
            r9 = r1
            r1 = r45
            goto L_0x090f
        L_0x0bda:
            r9 = r2
            r1 = r3
            com.google.android.gms.internal.measurement.zzft[] r2 = r1.zzaxk     // Catch:{ all -> 0x0c0b }
            int r2 = r2.length     // Catch:{ all -> 0x0c0b }
            if (r10 >= r2) goto L_0x0be9
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r5, r10)     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.internal.measurement.zzft[] r2 = (com.google.android.gms.internal.measurement.zzft[]) r2     // Catch:{ all -> 0x0c0b }
            r1.zzaxk = r2     // Catch:{ all -> 0x0c0b }
        L_0x0be9:
            java.util.Set r2 = r4.entrySet()     // Catch:{ all -> 0x0c0b }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0c0b }
        L_0x0bf1:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0c0b }
            if (r3 == 0) goto L_0x0c13
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0c0b }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.measurement.internal.zzt r4 = r45.zzjt()     // Catch:{ all -> 0x0c0b }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x0c0b }
            com.google.android.gms.measurement.internal.zzac r3 = (com.google.android.gms.measurement.internal.zzac) r3     // Catch:{ all -> 0x0c0b }
            r4.zza(r3)     // Catch:{ all -> 0x0c0b }
            goto L_0x0bf1
        L_0x0c0b:
            r0 = move-exception
            r1 = r0
            r4 = r45
            goto L_0x0dfc
        L_0x0c11:
            r9 = r2
            r1 = r3
        L_0x0c13:
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0dd7 }
            r1.zzaxn = r2     // Catch:{ all -> 0x0dd7 }
            r2 = -9223372036854775808
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0dd7 }
            r1.zzaxo = r2     // Catch:{ all -> 0x0dd7 }
            r2 = 0
        L_0x0c27:
            com.google.android.gms.internal.measurement.zzft[] r3 = r1.zzaxk     // Catch:{ all -> 0x0dd7 }
            int r3 = r3.length     // Catch:{ all -> 0x0dd7 }
            if (r2 >= r3) goto L_0x0c5b
            com.google.android.gms.internal.measurement.zzft[] r3 = r1.zzaxk     // Catch:{ all -> 0x0c0b }
            r3 = r3[r2]     // Catch:{ all -> 0x0c0b }
            java.lang.Long r4 = r3.zzaxd     // Catch:{ all -> 0x0c0b }
            long r4 = r4.longValue()     // Catch:{ all -> 0x0c0b }
            java.lang.Long r6 = r1.zzaxn     // Catch:{ all -> 0x0c0b }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0c0b }
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0c44
            java.lang.Long r4 = r3.zzaxd     // Catch:{ all -> 0x0c0b }
            r1.zzaxn = r4     // Catch:{ all -> 0x0c0b }
        L_0x0c44:
            java.lang.Long r4 = r3.zzaxd     // Catch:{ all -> 0x0c0b }
            long r4 = r4.longValue()     // Catch:{ all -> 0x0c0b }
            java.lang.Long r6 = r1.zzaxo     // Catch:{ all -> 0x0c0b }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0c0b }
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0c58
            java.lang.Long r3 = r3.zzaxd     // Catch:{ all -> 0x0c0b }
            r1.zzaxo = r3     // Catch:{ all -> 0x0c0b }
        L_0x0c58:
            int r2 = r2 + 1
            goto L_0x0c27
        L_0x0c5b:
            com.google.android.gms.internal.measurement.zzfw r2 = r9.zzaui     // Catch:{ all -> 0x0dd7 }
            java.lang.String r2 = r2.zztt     // Catch:{ all -> 0x0dd7 }
            com.google.android.gms.measurement.internal.zzt r3 = r45.zzjt()     // Catch:{ all -> 0x0dd7 }
            com.google.android.gms.measurement.internal.zzg r3 = r3.zzbm(r2)     // Catch:{ all -> 0x0dd7 }
            if (r3 != 0) goto L_0x0c83
            r4 = r45
            com.google.android.gms.measurement.internal.zzbw r3 = r4.zzada     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzas r3 = r3.zzgt()     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ all -> 0x0df7 }
            java.lang.String r5 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzfw r6 = r9.zzaui     // Catch:{ all -> 0x0df7 }
            java.lang.String r6 = r6.zztt     // Catch:{ all -> 0x0df7 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzas.zzbw(r6)     // Catch:{ all -> 0x0df7 }
            r3.zzg(r5, r6)     // Catch:{ all -> 0x0df7 }
            goto L_0x0ce1
        L_0x0c83:
            r4 = r45
            com.google.android.gms.internal.measurement.zzft[] r5 = r1.zzaxk     // Catch:{ all -> 0x0df7 }
            int r5 = r5.length     // Catch:{ all -> 0x0df7 }
            if (r5 <= 0) goto L_0x0ce1
            long r5 = r3.zzhe()     // Catch:{ all -> 0x0df7 }
            r7 = 0
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x0c99
            java.lang.Long r7 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0df7 }
            goto L_0x0c9a
        L_0x0c99:
            r7 = 0
        L_0x0c9a:
            r1.zzaxq = r7     // Catch:{ all -> 0x0df7 }
            long r7 = r3.zzhd()     // Catch:{ all -> 0x0df7 }
            r10 = 0
            int r12 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x0ca7
            goto L_0x0ca8
        L_0x0ca7:
            r5 = r7
        L_0x0ca8:
            int r7 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r7 == 0) goto L_0x0cb1
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0df7 }
            goto L_0x0cb2
        L_0x0cb1:
            r5 = 0
        L_0x0cb2:
            r1.zzaxp = r5     // Catch:{ all -> 0x0df7 }
            r3.zzhm()     // Catch:{ all -> 0x0df7 }
            long r5 = r3.zzhj()     // Catch:{ all -> 0x0df7 }
            int r6 = (int) r5     // Catch:{ all -> 0x0df7 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0df7 }
            r1.zzaya = r5     // Catch:{ all -> 0x0df7 }
            java.lang.Long r5 = r1.zzaxn     // Catch:{ all -> 0x0df7 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0df7 }
            r3.zzo(r5)     // Catch:{ all -> 0x0df7 }
            java.lang.Long r5 = r1.zzaxo     // Catch:{ all -> 0x0df7 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0df7 }
            r3.zzp(r5)     // Catch:{ all -> 0x0df7 }
            java.lang.String r5 = r3.zzhu()     // Catch:{ all -> 0x0df7 }
            r1.zzagm = r5     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzt r5 = r45.zzjt()     // Catch:{ all -> 0x0df7 }
            r5.zza(r3)     // Catch:{ all -> 0x0df7 }
        L_0x0ce1:
            com.google.android.gms.internal.measurement.zzft[] r3 = r1.zzaxk     // Catch:{ all -> 0x0df7 }
            int r3 = r3.length     // Catch:{ all -> 0x0df7 }
            if (r3 <= 0) goto L_0x0d36
            com.google.android.gms.measurement.internal.zzbw r3 = r4.zzada     // Catch:{ all -> 0x0df7 }
            r3.zzgw()     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzbq r3 = r45.zzls()     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.internal.measurement.zzfw r5 = r9.zzaui     // Catch:{ all -> 0x0df7 }
            java.lang.String r5 = r5.zztt     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.internal.measurement.zzfp r3 = r3.zzcg(r5)     // Catch:{ all -> 0x0df7 }
            if (r3 == 0) goto L_0x0d03
            java.lang.Long r5 = r3.zzawm     // Catch:{ all -> 0x0df7 }
            if (r5 != 0) goto L_0x0cfe
            goto L_0x0d03
        L_0x0cfe:
            java.lang.Long r3 = r3.zzawm     // Catch:{ all -> 0x0df7 }
            r1.zzayh = r3     // Catch:{ all -> 0x0df7 }
            goto L_0x0d2d
        L_0x0d03:
            com.google.android.gms.internal.measurement.zzfw r3 = r9.zzaui     // Catch:{ all -> 0x0df7 }
            java.lang.String r3 = r3.zzafi     // Catch:{ all -> 0x0df7 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0df7 }
            if (r3 == 0) goto L_0x0d16
            r5 = -1
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0df7 }
            r1.zzayh = r3     // Catch:{ all -> 0x0df7 }
            goto L_0x0d2d
        L_0x0d16:
            com.google.android.gms.measurement.internal.zzbw r3 = r4.zzada     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzas r3 = r3.zzgt()     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjj()     // Catch:{ all -> 0x0df7 }
            java.lang.String r5 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzfw r6 = r9.zzaui     // Catch:{ all -> 0x0df7 }
            java.lang.String r6 = r6.zztt     // Catch:{ all -> 0x0df7 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzas.zzbw(r6)     // Catch:{ all -> 0x0df7 }
            r3.zzg(r5, r6)     // Catch:{ all -> 0x0df7 }
        L_0x0d2d:
            com.google.android.gms.measurement.internal.zzt r3 = r45.zzjt()     // Catch:{ all -> 0x0df7 }
            r11 = r21
            r3.zza(r1, r11)     // Catch:{ all -> 0x0df7 }
        L_0x0d36:
            com.google.android.gms.measurement.internal.zzt r1 = r45.zzjt()     // Catch:{ all -> 0x0df7 }
            java.util.List<java.lang.Long> r3 = r9.zzauj     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x0df7 }
            r1.zzaf()     // Catch:{ all -> 0x0df7 }
            r1.zzcl()     // Catch:{ all -> 0x0df7 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0df7 }
            java.lang.String r6 = "rowid in ("
            r5.<init>(r6)     // Catch:{ all -> 0x0df7 }
            r6 = 0
        L_0x0d4d:
            int r7 = r3.size()     // Catch:{ all -> 0x0df7 }
            if (r6 >= r7) goto L_0x0d6a
            if (r6 == 0) goto L_0x0d5a
            java.lang.String r7 = ","
            r5.append(r7)     // Catch:{ all -> 0x0df7 }
        L_0x0d5a:
            java.lang.Object r7 = r3.get(r6)     // Catch:{ all -> 0x0df7 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x0df7 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0df7 }
            r5.append(r7)     // Catch:{ all -> 0x0df7 }
            int r6 = r6 + 1
            goto L_0x0d4d
        L_0x0d6a:
            java.lang.String r6 = ")"
            r5.append(r6)     // Catch:{ all -> 0x0df7 }
            android.database.sqlite.SQLiteDatabase r6 = r1.getWritableDatabase()     // Catch:{ all -> 0x0df7 }
            java.lang.String r7 = "raw_events"
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0df7 }
            r8 = 0
            int r5 = r6.delete(r7, r5, r8)     // Catch:{ all -> 0x0df7 }
            int r6 = r3.size()     // Catch:{ all -> 0x0df7 }
            if (r5 == r6) goto L_0x0d9d
            com.google.android.gms.measurement.internal.zzas r1 = r1.zzgt()     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ all -> 0x0df7 }
            java.lang.String r6 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0df7 }
            int r3 = r3.size()     // Catch:{ all -> 0x0df7 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0df7 }
            r1.zze(r6, r5, r3)     // Catch:{ all -> 0x0df7 }
        L_0x0d9d:
            com.google.android.gms.measurement.internal.zzt r1 = r45.zzjt()     // Catch:{ all -> 0x0df7 }
            android.database.sqlite.SQLiteDatabase r3 = r1.getWritableDatabase()     // Catch:{ all -> 0x0df7 }
            java.lang.String r5 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0db4 }
            r7 = 0
            r6[r7] = r2     // Catch:{ SQLiteException -> 0x0db4 }
            r7 = 1
            r6[r7] = r2     // Catch:{ SQLiteException -> 0x0db4 }
            r3.execSQL(r5, r6)     // Catch:{ SQLiteException -> 0x0db4 }
            goto L_0x0dc7
        L_0x0db4:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.measurement.internal.zzas r1 = r1.zzgt()     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ all -> 0x0df7 }
            java.lang.String r5 = "Failed to remove unused event metadata. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzas.zzbw(r2)     // Catch:{ all -> 0x0df7 }
            r1.zze(r5, r2, r3)     // Catch:{ all -> 0x0df7 }
        L_0x0dc7:
            com.google.android.gms.measurement.internal.zzt r1 = r45.zzjt()     // Catch:{ all -> 0x0df7 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzt r1 = r45.zzjt()
            r1.endTransaction()
            r1 = 1
            return r1
        L_0x0dd7:
            r0 = move-exception
            r4 = r45
            goto L_0x0dfb
        L_0x0ddb:
            r4 = r1
            com.google.android.gms.measurement.internal.zzt r1 = r45.zzjt()     // Catch:{ all -> 0x0df7 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0df7 }
            com.google.android.gms.measurement.internal.zzt r1 = r45.zzjt()
            r1.endTransaction()
            r1 = 0
            return r1
        L_0x0dec:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r22 = r6
        L_0x0df1:
            if (r22 == 0) goto L_0x0df6
            r22.close()     // Catch:{ all -> 0x0df7 }
        L_0x0df6:
            throw r1     // Catch:{ all -> 0x0df7 }
        L_0x0df7:
            r0 = move-exception
            goto L_0x0dfb
        L_0x0df9:
            r0 = move-exception
            r4 = r1
        L_0x0dfb:
            r1 = r0
        L_0x0dfc:
            com.google.android.gms.measurement.internal.zzt r2 = r45.zzjt()
            r2.endTransaction()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfn.zzd(java.lang.String, long):boolean");
    }

    private final boolean zza(zzft zzft, zzft zzft2) {
        Object obj;
        Preconditions.checkArgument("_e".equals(zzft.name));
        zzjr();
        zzfu zza2 = zzft.zza(zzft, "_sc");
        String str = null;
        if (zza2 == null) {
            obj = null;
        } else {
            obj = zza2.zzamn;
        }
        zzjr();
        zzfu zza3 = zzft.zza(zzft2, "_pc");
        if (zza3 != null) {
            str = zza3.zzamn;
        }
        if (str == null || !str.equals(obj)) {
            return false;
        }
        zzjr();
        zzfu zza4 = zzft.zza(zzft, "_et");
        if (zza4.zzaxg == null || zza4.zzaxg.longValue() <= 0) {
            return true;
        }
        long longValue = zza4.zzaxg.longValue();
        zzjr();
        zzfu zza5 = zzft.zza(zzft2, "_et");
        if (!(zza5 == null || zza5.zzaxg == null || zza5.zzaxg.longValue() <= 0)) {
            longValue += zza5.zzaxg.longValue();
        }
        zzjr();
        zzft2.zzaxc = zzft.zza(zzft2.zzaxc, "_et", (Object) Long.valueOf(longValue));
        zzjr();
        zzft.zzaxc = zzft.zza(zzft.zzaxc, "_fr", (Object) Long.valueOf(1));
        return true;
    }

    @VisibleForTesting
    private static zzfu[] zza(zzfu[] zzfuArr, @NonNull String str) {
        int i = 0;
        while (true) {
            if (i >= zzfuArr.length) {
                i = -1;
                break;
            } else if (str.equals(zzfuArr[i].name)) {
                break;
            } else {
                i++;
            }
        }
        if (i < 0) {
            return zzfuArr;
        }
        return zza(zzfuArr, i);
    }

    @VisibleForTesting
    private static zzfu[] zza(zzfu[] zzfuArr, int i) {
        zzfu[] zzfuArr2 = new zzfu[(zzfuArr.length - 1)];
        if (i > 0) {
            System.arraycopy(zzfuArr, 0, zzfuArr2, 0, i);
        }
        if (i < zzfuArr2.length) {
            System.arraycopy(zzfuArr, i + 1, zzfuArr2, i, zzfuArr2.length - i);
        }
        return zzfuArr2;
    }

    @VisibleForTesting
    private static zzfu[] zza(zzfu[] zzfuArr, int i, String str) {
        for (zzfu zzfu : zzfuArr) {
            if ("_err".equals(zzfu.name)) {
                return zzfuArr;
            }
        }
        zzfu[] zzfuArr2 = new zzfu[(zzfuArr.length + 2)];
        System.arraycopy(zzfuArr, 0, zzfuArr2, 0, zzfuArr.length);
        zzfu zzfu2 = new zzfu();
        zzfu2.name = "_err";
        zzfu2.zzaxg = Long.valueOf((long) i);
        zzfu zzfu3 = new zzfu();
        zzfu3.name = "_ev";
        zzfu3.zzamn = str;
        zzfuArr2[zzfuArr2.length - 2] = zzfu2;
        zzfuArr2[zzfuArr2.length - 1] = zzfu3;
        return zzfuArr2;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzt zzjt;
        zzaf();
        zzlx();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzaty = false;
                zzmc();
                throw th2;
            }
        }
        List<Long> list = this.zzauc;
        this.zzauc = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzada.zzgu().zzanc.set(this.zzada.zzbx().currentTimeMillis());
                this.zzada.zzgu().zzand.set(0);
                zzmb();
                this.zzada.zzgt().zzjo().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzjt().beginTransaction();
                try {
                    for (Long l : list) {
                        try {
                            zzjt = zzjt();
                            long longValue = l.longValue();
                            zzjt.zzaf();
                            zzjt.zzcl();
                            if (zzjt.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zzjt.zzgt().zzjg().zzg("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.zzaud == null || !this.zzaud.contains(l)) {
                                throw e2;
                            }
                        }
                    }
                    zzjt().setTransactionSuccessful();
                    zzjt().endTransaction();
                    this.zzaud = null;
                    if (!zzlt().zzfb() || !zzma()) {
                        this.zzaue = -1;
                        zzmb();
                    } else {
                        zzlz();
                    }
                    this.zzatt = 0;
                } catch (Throwable th3) {
                    zzjt().endTransaction();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzada.zzgt().zzjg().zzg("Database error while trying to delete uploaded bundles", e3);
                this.zzatt = this.zzada.zzbx().elapsedRealtime();
                this.zzada.zzgt().zzjo().zzg("Disable upload, time", Long.valueOf(this.zzatt));
            }
        } else {
            this.zzada.zzgt().zzjo().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzada.zzgu().zzand.set(this.zzada.zzbx().currentTimeMillis());
            if (i != 503) {
                if (i != 429) {
                    z = false;
                }
            }
            if (z) {
                this.zzada.zzgu().zzane.set(this.zzada.zzbx().currentTimeMillis());
            }
            if (this.zzada.zzgv().zzaw(str)) {
                zzjt().zzc(list);
            }
            zzmb();
        }
        this.zzaty = false;
        zzmc();
    }

    private final boolean zzma() {
        zzaf();
        zzlx();
        return zzjt().zzim() || !TextUtils.isEmpty(zzjt().zzih());
    }

    @WorkerThread
    private final void zzb(zzg zzg) {
        Map map;
        zzaf();
        if (!TextUtils.isEmpty(zzg.getGmpAppId()) || (zzq.zzig() && !TextUtils.isEmpty(zzg.zzhb()))) {
            zzq zzgv = this.zzada.zzgv();
            Builder builder = new Builder();
            String gmpAppId = zzg.getGmpAppId();
            if (TextUtils.isEmpty(gmpAppId) && zzq.zzig()) {
                gmpAppId = zzg.zzhb();
            }
            Builder encodedAuthority = builder.scheme((String) zzai.zzaiy.get()).encodedAuthority((String) zzai.zzaiz.get());
            String str = "config/app/";
            String valueOf = String.valueOf(gmpAppId);
            encodedAuthority.path(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).appendQueryParameter("app_instance_id", zzg.getAppInstanceId()).appendQueryParameter(Http.PLATFORM, "android").appendQueryParameter("gmp_version", String.valueOf(zzgv.zzhh()));
            String uri = builder.build().toString();
            try {
                URL url = new URL(uri);
                this.zzada.zzgt().zzjo().zzg("Fetching remote configuration", zzg.zzal());
                zzfp zzcg = zzls().zzcg(zzg.zzal());
                String zzch = zzls().zzch(zzg.zzal());
                if (zzcg == null || TextUtils.isEmpty(zzch)) {
                    map = null;
                } else {
                    ArrayMap arrayMap = new ArrayMap();
                    arrayMap.put(HttpHeaders.IF_MODIFIED_SINCE, zzch);
                    map = arrayMap;
                }
                this.zzatx = true;
                zzaw zzlt = zzlt();
                String zzal = zzg.zzal();
                zzfq zzfq = new zzfq(this);
                zzlt.zzaf();
                zzlt.zzcl();
                Preconditions.checkNotNull(url);
                Preconditions.checkNotNull(zzfq);
                zzbr zzgs = zzlt.zzgs();
                zzba zzba = new zzba(zzlt, zzal, url, null, map, zzfq);
                zzgs.zzd((Runnable) zzba);
            } catch (MalformedURLException unused) {
                this.zzada.zzgt().zzjg().zze("Failed to parse config URL. Not fetching. appId", zzas.zzbw(zzg.zzal()), uri);
            }
        } else {
            zzb(zzg.zzal(), RequestCodes.VIEW_FOOD, null, null, null);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x013a A[Catch:{ all -> 0x018d, all -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x014a A[Catch:{ all -> 0x018d, all -> 0x0196 }] */
    @android.support.annotation.WorkerThread
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            r6.zzaf()
            r6.zzlx()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x000e
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x0196 }
        L_0x000e:
            com.google.android.gms.measurement.internal.zzbw r1 = r6.zzada     // Catch:{ all -> 0x0196 }
            com.google.android.gms.measurement.internal.zzas r1 = r1.zzgt()     // Catch:{ all -> 0x0196 }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjo()     // Catch:{ all -> 0x0196 }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x0196 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0196 }
            r1.zzg(r2, r3)     // Catch:{ all -> 0x0196 }
            com.google.android.gms.measurement.internal.zzt r1 = r6.zzjt()     // Catch:{ all -> 0x0196 }
            r1.beginTransaction()     // Catch:{ all -> 0x0196 }
            com.google.android.gms.measurement.internal.zzt r1 = r6.zzjt()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzg r1 = r1.zzbm(r7)     // Catch:{ all -> 0x018d }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 304(0x130, float:4.26E-43)
            r4 = 1
            if (r8 == r2) goto L_0x003e
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x003e
            if (r8 != r3) goto L_0x0042
        L_0x003e:
            if (r9 != 0) goto L_0x0042
            r2 = 1
            goto L_0x0043
        L_0x0042:
            r2 = 0
        L_0x0043:
            if (r1 != 0) goto L_0x005a
            com.google.android.gms.measurement.internal.zzbw r8 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzas r8 = r8.zzgt()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzau r8 = r8.zzjj()     // Catch:{ all -> 0x018d }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzas.zzbw(r7)     // Catch:{ all -> 0x018d }
            r8.zzg(r9, r7)     // Catch:{ all -> 0x018d }
            goto L_0x0179
        L_0x005a:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00ca
            if (r8 != r5) goto L_0x0061
            goto L_0x00ca
        L_0x0061:
            com.google.android.gms.measurement.internal.zzbw r10 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.common.util.Clock r10 = r10.zzbx()     // Catch:{ all -> 0x018d }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x018d }
            r1.zzv(r10)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzt r10 = r6.zzjt()     // Catch:{ all -> 0x018d }
            r10.zza(r1)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzbw r10 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzas r10 = r10.zzgt()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzau r10 = r10.zzjo()     // Catch:{ all -> 0x018d }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x018d }
            r10.zze(r11, r1, r9)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzbq r9 = r6.zzls()     // Catch:{ all -> 0x018d }
            r9.zzci(r7)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzbw r7 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzbd r7 = r7.zzgu()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzbg r7 = r7.zzand     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzbw r9 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.common.util.Clock r9 = r9.zzbx()     // Catch:{ all -> 0x018d }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x018d }
            r7.set(r9)     // Catch:{ all -> 0x018d }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00ae
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00ad
            goto L_0x00ae
        L_0x00ad:
            r4 = 0
        L_0x00ae:
            if (r4 == 0) goto L_0x00c5
            com.google.android.gms.measurement.internal.zzbw r7 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzbd r7 = r7.zzgu()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzbg r7 = r7.zzane     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzbw r8 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.common.util.Clock r8 = r8.zzbx()     // Catch:{ all -> 0x018d }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x018d }
            r7.set(r8)     // Catch:{ all -> 0x018d }
        L_0x00c5:
            r6.zzmb()     // Catch:{ all -> 0x018d }
            goto L_0x0179
        L_0x00ca:
            r9 = 0
            if (r11 == 0) goto L_0x00d6
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x018d }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x018d }
            goto L_0x00d7
        L_0x00d6:
            r11 = r9
        L_0x00d7:
            if (r11 == 0) goto L_0x00e6
            int r2 = r11.size()     // Catch:{ all -> 0x018d }
            if (r2 <= 0) goto L_0x00e6
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x018d }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x018d }
            goto L_0x00e7
        L_0x00e6:
            r11 = r9
        L_0x00e7:
            if (r8 == r5) goto L_0x0103
            if (r8 != r3) goto L_0x00ec
            goto L_0x0103
        L_0x00ec:
            com.google.android.gms.measurement.internal.zzbq r9 = r6.zzls()     // Catch:{ all -> 0x018d }
            boolean r9 = r9.zza(r7, r10, r11)     // Catch:{ all -> 0x018d }
            if (r9 != 0) goto L_0x0124
            com.google.android.gms.measurement.internal.zzt r7 = r6.zzjt()     // Catch:{ all -> 0x0196 }
            r7.endTransaction()     // Catch:{ all -> 0x0196 }
            r6.zzatx = r0
            r6.zzmc()
            return
        L_0x0103:
            com.google.android.gms.measurement.internal.zzbq r11 = r6.zzls()     // Catch:{ all -> 0x018d }
            com.google.android.gms.internal.measurement.zzfp r11 = r11.zzcg(r7)     // Catch:{ all -> 0x018d }
            if (r11 != 0) goto L_0x0124
            com.google.android.gms.measurement.internal.zzbq r11 = r6.zzls()     // Catch:{ all -> 0x018d }
            boolean r9 = r11.zza(r7, r9, r9)     // Catch:{ all -> 0x018d }
            if (r9 != 0) goto L_0x0124
            com.google.android.gms.measurement.internal.zzt r7 = r6.zzjt()     // Catch:{ all -> 0x0196 }
            r7.endTransaction()     // Catch:{ all -> 0x0196 }
            r6.zzatx = r0
            r6.zzmc()
            return
        L_0x0124:
            com.google.android.gms.measurement.internal.zzbw r9 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.common.util.Clock r9 = r9.zzbx()     // Catch:{ all -> 0x018d }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x018d }
            r1.zzu(r2)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzt r9 = r6.zzjt()     // Catch:{ all -> 0x018d }
            r9.zza(r1)     // Catch:{ all -> 0x018d }
            if (r8 != r5) goto L_0x014a
            com.google.android.gms.measurement.internal.zzbw r8 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzas r8 = r8.zzgt()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzau r8 = r8.zzjl()     // Catch:{ all -> 0x018d }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zzg(r9, r7)     // Catch:{ all -> 0x018d }
            goto L_0x0162
        L_0x014a:
            com.google.android.gms.measurement.internal.zzbw r7 = r6.zzada     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzas r7 = r7.zzgt()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzau r7 = r7.zzjo()     // Catch:{ all -> 0x018d }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x018d }
            int r10 = r10.length     // Catch:{ all -> 0x018d }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x018d }
            r7.zze(r9, r8, r10)     // Catch:{ all -> 0x018d }
        L_0x0162:
            com.google.android.gms.measurement.internal.zzaw r7 = r6.zzlt()     // Catch:{ all -> 0x018d }
            boolean r7 = r7.zzfb()     // Catch:{ all -> 0x018d }
            if (r7 == 0) goto L_0x0176
            boolean r7 = r6.zzma()     // Catch:{ all -> 0x018d }
            if (r7 == 0) goto L_0x0176
            r6.zzlz()     // Catch:{ all -> 0x018d }
            goto L_0x0179
        L_0x0176:
            r6.zzmb()     // Catch:{ all -> 0x018d }
        L_0x0179:
            com.google.android.gms.measurement.internal.zzt r7 = r6.zzjt()     // Catch:{ all -> 0x018d }
            r7.setTransactionSuccessful()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzt r7 = r6.zzjt()     // Catch:{ all -> 0x0196 }
            r7.endTransaction()     // Catch:{ all -> 0x0196 }
            r6.zzatx = r0
            r6.zzmc()
            return
        L_0x018d:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzt r8 = r6.zzjt()     // Catch:{ all -> 0x0196 }
            r8.endTransaction()     // Catch:{ all -> 0x0196 }
            throw r7     // Catch:{ all -> 0x0196 }
        L_0x0196:
            r7 = move-exception
            r6.zzatx = r0
            r6.zzmc()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfn.zzb(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    @WorkerThread
    private final void zzmb() {
        long j;
        long j2;
        zzaf();
        zzlx();
        if (zzmf() || this.zzada.zzgv().zza(zzai.zzalf)) {
            if (this.zzatt > 0) {
                long abs = 3600000 - Math.abs(this.zzada.zzbx().elapsedRealtime() - this.zzatt);
                if (abs > 0) {
                    this.zzada.zzgt().zzjo().zzg("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                    zzlu().unregister();
                    zzlv().cancel();
                    return;
                }
                this.zzatt = 0;
            }
            if (!this.zzada.zzkv() || !zzma()) {
                this.zzada.zzgt().zzjo().zzby("Nothing to upload or uploading impossible");
                zzlu().unregister();
                zzlv().cancel();
                return;
            }
            long currentTimeMillis = this.zzada.zzbx().currentTimeMillis();
            long max = Math.max(0, ((Long) zzai.zzaju.get()).longValue());
            boolean z = zzjt().zzin() || zzjt().zzii();
            if (z) {
                String zzid = this.zzada.zzgv().zzid();
                if (TextUtils.isEmpty(zzid) || ".none.".equals(zzid)) {
                    j = Math.max(0, ((Long) zzai.zzajo.get()).longValue());
                } else {
                    j = Math.max(0, ((Long) zzai.zzajp.get()).longValue());
                }
            } else {
                j = Math.max(0, ((Long) zzai.zzajn.get()).longValue());
            }
            long j3 = this.zzada.zzgu().zzanc.get();
            long j4 = this.zzada.zzgu().zzand.get();
            long j5 = j;
            long j6 = max;
            long max2 = Math.max(zzjt().zzik(), zzjt().zzil());
            if (max2 == 0) {
                j2 = 0;
            } else {
                long abs2 = currentTimeMillis - Math.abs(max2 - currentTimeMillis);
                long abs3 = currentTimeMillis - Math.abs(j3 - currentTimeMillis);
                long abs4 = currentTimeMillis - Math.abs(j4 - currentTimeMillis);
                long max3 = Math.max(abs3, abs4);
                long j7 = abs2 + j6;
                if (z && max3 > 0) {
                    j7 = Math.min(abs2, max3) + j5;
                }
                long j8 = j5;
                j2 = !zzjr().zzb(max3, j8) ? max3 + j8 : j7;
                if (abs4 != 0 && abs4 >= abs2) {
                    int i = 0;
                    while (true) {
                        if (i >= Math.min(20, Math.max(0, ((Integer) zzai.zzajw.get()).intValue()))) {
                            j2 = 0;
                            break;
                        }
                        j2 += Math.max(0, ((Long) zzai.zzajv.get()).longValue()) * (1 << i);
                        if (j2 > abs4) {
                            break;
                        }
                        i++;
                    }
                }
            }
            if (j2 == 0) {
                this.zzada.zzgt().zzjo().zzby("Next upload time is 0");
                zzlu().unregister();
                zzlv().cancel();
            } else if (!zzlt().zzfb()) {
                this.zzada.zzgt().zzjo().zzby("No network");
                zzlu().zzey();
                zzlv().cancel();
            } else {
                long j9 = this.zzada.zzgu().zzane.get();
                long max4 = Math.max(0, ((Long) zzai.zzajl.get()).longValue());
                if (!zzjr().zzb(j9, max4)) {
                    j2 = Math.max(j2, j9 + max4);
                }
                zzlu().unregister();
                long currentTimeMillis2 = j2 - this.zzada.zzbx().currentTimeMillis();
                if (currentTimeMillis2 <= 0) {
                    currentTimeMillis2 = Math.max(0, ((Long) zzai.zzajq.get()).longValue());
                    this.zzada.zzgu().zzanc.set(this.zzada.zzbx().currentTimeMillis());
                }
                this.zzada.zzgt().zzjo().zzg("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis2));
                zzlv().zzh(currentTimeMillis2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzg(Runnable runnable) {
        zzaf();
        if (this.zzatu == null) {
            this.zzatu = new ArrayList();
        }
        this.zzatu.add(runnable);
    }

    @WorkerThread
    private final void zzmc() {
        zzaf();
        if (this.zzatx || this.zzaty || this.zzatz) {
            this.zzada.zzgt().zzjo().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzatx), Boolean.valueOf(this.zzaty), Boolean.valueOf(this.zzatz));
            return;
        }
        this.zzada.zzgt().zzjo().zzby("Stopping uploading service(s)");
        List<Runnable> list = this.zzatu;
        if (list != null) {
            for (Runnable run : list) {
                run.run();
            }
            this.zzatu.clear();
        }
    }

    @WorkerThread
    private final Boolean zzc(zzg zzg) {
        try {
            if (zzg.zzhf() != -2147483648L) {
                if (zzg.zzhf() == ((long) Wrappers.packageManager(this.zzada.getContext()).getPackageInfo(zzg.zzal(), 0).versionCode)) {
                    return Boolean.valueOf(true);
                }
            } else {
                String str = Wrappers.packageManager(this.zzada.getContext()).getPackageInfo(zzg.zzal(), 0).versionName;
                if (zzg.zzak() != null && zzg.zzak().equals(str)) {
                    return Boolean.valueOf(true);
                }
            }
            return Boolean.valueOf(false);
        } catch (NameNotFoundException unused) {
            return null;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zzmd() {
        zzaf();
        try {
            this.zzaub = new RandomAccessFile(new File(this.zzada.getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzaua = this.zzaub.tryLock();
            if (this.zzaua != null) {
                this.zzada.zzgt().zzjo().zzby("Storage concurrent access okay");
                return true;
            }
            this.zzada.zzgt().zzjg().zzby("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            this.zzada.zzgt().zzjg().zzg("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            this.zzada.zzgt().zzjg().zzg("Failed to access storage lock file", e2);
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final int zza(FileChannel fileChannel) {
        zzaf();
        int i = 0;
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzada.zzgt().zzjg().zzby("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    this.zzada.zzgt().zzjj().zzg("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            i = allocate.getInt();
            return i;
        } catch (IOException e) {
            this.zzada.zzgt().zzjg().zzg("Failed to read from channel", e);
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zza(int i, FileChannel fileChannel) {
        zzaf();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzada.zzgt().zzjg().zzby("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzada.zzgt().zzjg().zzg("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzada.zzgt().zzjg().zzg("Failed to write to channel", e);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzme() {
        zzaf();
        zzlx();
        if (!this.zzats) {
            this.zzats = true;
            zzaf();
            zzlx();
            if ((this.zzada.zzgv().zza(zzai.zzalf) || zzmf()) && zzmd()) {
                int zza2 = zza(this.zzaub);
                int zzjd = this.zzada.zzgk().zzjd();
                zzaf();
                if (zza2 > zzjd) {
                    this.zzada.zzgt().zzjg().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzjd));
                } else if (zza2 < zzjd) {
                    if (zza(zzjd, this.zzaub)) {
                        this.zzada.zzgt().zzjo().zze("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzjd));
                    } else {
                        this.zzada.zzgt().zzjg().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzjd));
                    }
                }
            }
        }
        if (!this.zzatr && !this.zzada.zzgv().zza(zzai.zzalf)) {
            this.zzada.zzgt().zzjm().zzby("This instance being marked as an uploader");
            this.zzatr = true;
            zzmb();
        }
    }

    @WorkerThread
    private final boolean zzmf() {
        zzaf();
        zzlx();
        return this.zzatr;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final void zzd(zzk zzk) {
        if (this.zzauc != null) {
            this.zzaud = new ArrayList();
            this.zzaud.addAll(this.zzauc);
        }
        zzt zzjt = zzjt();
        String str = zzk.packageName;
        Preconditions.checkNotEmpty(str);
        zzjt.zzaf();
        zzjt.zzcl();
        try {
            SQLiteDatabase writableDatabase = zzjt.getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("apps", "app_id=?", strArr) + 0 + writableDatabase.delete(Constants.VIDEO_TRACKING_EVENTS_KEY, "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("queue", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzjt.zzgt().zzjo().zze("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzjt.zzgt().zzjg().zze("Error resetting analytics data. appId, error", zzas.zzbw(str), e);
        }
        zzk zza2 = zza(this.zzada.getContext(), zzk.packageName, zzk.zzafi, zzk.zzafr, zzk.zzaft, zzk.zzafu, zzk.zzago, zzk.zzafv);
        if (!this.zzada.zzgv().zzba(zzk.packageName) || zzk.zzafr) {
            zzf(zza2);
        }
    }

    private final zzk zza(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j, String str3) {
        String str4;
        int i;
        String str5 = str;
        String str6 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        String str7 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        String str8 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            this.zzada.zzgt().zzjg().zzby("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str6 = packageManager.getInstallerPackageName(str5);
        } catch (IllegalArgumentException unused) {
            this.zzada.zzgt().zzjg().zzg("Error retrieving installer package name. appId", zzas.zzbw(str));
        }
        String str9 = str6 == null ? "manual_install" : "com.android.vending".equals(str6) ? "" : str6;
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str5, 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = Wrappers.packageManager(context).getApplicationLabel(str5);
                if (!TextUtils.isEmpty(applicationLabel)) {
                    String charSequence = applicationLabel.toString();
                }
                str4 = packageInfo.versionName;
                i = packageInfo.versionCode;
            } else {
                str4 = str7;
                i = Integer.MIN_VALUE;
            }
            this.zzada.zzgw();
            zzk zzk = new zzk(str, str2, str4, (long) i, str9, this.zzada.zzgv().zzhh(), this.zzada.zzgr().zzd(context, str5), (String) null, z, false, "", 0, this.zzada.zzgv().zzbc(str5) ? j : 0, 0, z2, z3, false, str3);
            return zzk;
        } catch (NameNotFoundException unused2) {
            this.zzada.zzgt().zzjg().zze("Error retrieving newly installed package info. appId, appName", zzas.zzbw(str), str8);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzb(zzfu zzfu, zzk zzk) {
        zzaf();
        zzlx();
        if (TextUtils.isEmpty(zzk.zzafi) && TextUtils.isEmpty(zzk.zzafv)) {
            return;
        }
        if (!zzk.zzafr) {
            zzg(zzk);
            return;
        }
        int zzcv = this.zzada.zzgr().zzcv(zzfu.name);
        if (zzcv != 0) {
            this.zzada.zzgr();
            this.zzada.zzgr().zza(zzk.packageName, zzcv, "_ev", zzfx.zza(zzfu.name, 24, true), zzfu.name != null ? zzfu.name.length() : 0);
            return;
        }
        int zzi = this.zzada.zzgr().zzi(zzfu.name, zzfu.getValue());
        if (zzi != 0) {
            this.zzada.zzgr();
            String zza2 = zzfx.zza(zzfu.name, 24, true);
            Object value = zzfu.getValue();
            this.zzada.zzgr().zza(zzk.packageName, zzi, "_ev", zza2, (value == null || (!(value instanceof String) && !(value instanceof CharSequence))) ? 0 : String.valueOf(value).length());
            return;
        }
        Object zzj = this.zzada.zzgr().zzj(zzfu.name, zzfu.getValue());
        if (zzj != null) {
            if (this.zzada.zzgv().zzbh(zzk.packageName) && "_sno".equals(zzfu.name)) {
                long j = 0;
                zzfw zzi2 = zzjt().zzi(zzk.packageName, "_sno");
                if (zzi2 == null || !(zzi2.value instanceof Long)) {
                    zzac zzg = zzjt().zzg(zzk.packageName, "_s");
                    if (zzg != null) {
                        j = zzg.zzahv;
                        this.zzada.zzgt().zzjo().zzg("Backfill the session number. Last used session number", Long.valueOf(j));
                    }
                } else {
                    j = ((Long) zzi2.value).longValue();
                }
                zzj = Long.valueOf(j + 1);
            }
            zzfw zzfw = new zzfw(zzk.packageName, zzfu.origin, zzfu.name, zzfu.zzaum, zzj);
            this.zzada.zzgt().zzjn().zze("Setting user property", this.zzada.zzgq().zzbv(zzfw.name), zzj);
            zzjt().beginTransaction();
            try {
                zzg(zzk);
                boolean zza3 = zzjt().zza(zzfw);
                zzjt().setTransactionSuccessful();
                if (zza3) {
                    this.zzada.zzgt().zzjn().zze("User property set", this.zzada.zzgq().zzbv(zzfw.name), zzfw.value);
                } else {
                    this.zzada.zzgt().zzjg().zze("Too many unique user properties are set. Ignoring user property", this.zzada.zzgq().zzbv(zzfw.name), zzfw.value);
                    this.zzada.zzgr().zza(zzk.packageName, 9, (String) null, (String) null, 0);
                }
            } finally {
                zzjt().endTransaction();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzc(zzfu zzfu, zzk zzk) {
        zzaf();
        zzlx();
        if (TextUtils.isEmpty(zzk.zzafi) && TextUtils.isEmpty(zzk.zzafv)) {
            return;
        }
        if (!zzk.zzafr) {
            zzg(zzk);
            return;
        }
        this.zzada.zzgt().zzjn().zzg("Removing user property", this.zzada.zzgq().zzbv(zzfu.name));
        zzjt().beginTransaction();
        try {
            zzg(zzk);
            zzjt().zzh(zzk.packageName, zzfu.name);
            zzjt().setTransactionSuccessful();
            this.zzada.zzgt().zzjn().zzg("User property removed", this.zzada.zzgq().zzbv(zzfu.name));
        } finally {
            zzjt().endTransaction();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzfm zzfm) {
        this.zzatv++;
    }

    /* access modifiers changed from: 0000 */
    public final void zzmg() {
        this.zzatw++;
    }

    /* access modifiers changed from: 0000 */
    public final zzbw zzmh() {
        return this.zzada;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzf(zzk zzk) {
        int i;
        zzg zzbm;
        long j;
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        boolean z;
        zzt zzjt;
        String zzal;
        zzk zzk2 = zzk;
        zzaf();
        zzlx();
        Preconditions.checkNotNull(zzk);
        Preconditions.checkNotEmpty(zzk2.packageName);
        if (!TextUtils.isEmpty(zzk2.zzafi) || !TextUtils.isEmpty(zzk2.zzafv)) {
            zzg zzbm2 = zzjt().zzbm(zzk2.packageName);
            if (zzbm2 != null && TextUtils.isEmpty(zzbm2.getGmpAppId()) && !TextUtils.isEmpty(zzk2.zzafi)) {
                zzbm2.zzu(0);
                zzjt().zza(zzbm2);
                zzls().zzcj(zzk2.packageName);
            }
            if (!zzk2.zzafr) {
                zzg(zzk);
                return;
            }
            long j2 = zzk2.zzago;
            if (j2 == 0) {
                j2 = this.zzada.zzbx().currentTimeMillis();
            }
            int i2 = zzk2.zzagp;
            if (i2 == 0 || i2 == 1) {
                i = i2;
            } else {
                this.zzada.zzgt().zzjj().zze("Incorrect app type, assuming installed app. appId, appType", zzas.zzbw(zzk2.packageName), Integer.valueOf(i2));
                i = 0;
            }
            zzjt().beginTransaction();
            try {
                zzbm = zzjt().zzbm(zzk2.packageName);
                if (zzbm != null) {
                    this.zzada.zzgr();
                    if (zzfx.zza(zzk2.zzafi, zzbm.getGmpAppId(), zzk2.zzafv, zzbm.zzhb())) {
                        this.zzada.zzgt().zzjj().zzg("New GMP App Id passed in. Removing cached database data. appId", zzas.zzbw(zzbm.zzal()));
                        zzjt = zzjt();
                        zzal = zzbm.zzal();
                        zzjt.zzcl();
                        zzjt.zzaf();
                        Preconditions.checkNotEmpty(zzal);
                        SQLiteDatabase writableDatabase = zzjt.getWritableDatabase();
                        String[] strArr = {zzal};
                        int delete = writableDatabase.delete(Constants.VIDEO_TRACKING_EVENTS_KEY, "app_id=?", strArr) + 0 + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("event_filters", "app_id=?", strArr) + writableDatabase.delete("property_filters", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr);
                        if (delete > 0) {
                            zzjt.zzgt().zzjo().zze("Deleted application data. app, records", zzal, Integer.valueOf(delete));
                        }
                        zzbm = null;
                    }
                }
            } catch (SQLiteException e) {
                zzjt.zzgt().zzjg().zze("Error deleting application data. appId, error", zzas.zzbw(zzal), e);
            } catch (Throwable th) {
                zzjt().endTransaction();
                throw th;
            }
            if (zzbm != null) {
                if (zzbm.zzhf() != -2147483648L) {
                    if (zzbm.zzhf() != zzk2.zzafo) {
                        Bundle bundle = new Bundle();
                        bundle.putString("_pv", zzbm.zzak());
                        zzag zzag = new zzag("_au", new zzad(bundle), "auto", j2);
                        zzc(zzag, zzk2);
                    }
                } else if (zzbm.zzak() != null && !zzbm.zzak().equals(zzk2.zzts)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("_pv", zzbm.zzak());
                    zzag zzag2 = new zzag("_au", new zzad(bundle2), "auto", j2);
                    zzc(zzag2, zzk2);
                }
            }
            zzg(zzk);
            zzac zzac = i == 0 ? zzjt().zzg(zzk2.packageName, "_f") : i == 1 ? zzjt().zzg(zzk2.packageName, "_v") : null;
            if (zzac == null) {
                long j3 = ((j2 / 3600000) + 1) * 3600000;
                if (i == 0) {
                    j = 1;
                    zzfu zzfu = new zzfu("_fot", j2, Long.valueOf(j3), "auto");
                    zzb(zzfu, zzk2);
                    if (this.zzada.zzgv().zzbe(zzk2.zzafi)) {
                        zzaf();
                        this.zzada.zzkk().zzce(zzk2.packageName);
                    }
                    zzaf();
                    zzlx();
                    Bundle bundle3 = new Bundle();
                    bundle3.putLong("_c", 1);
                    bundle3.putLong("_r", 1);
                    bundle3.putLong("_uwa", 0);
                    bundle3.putLong("_pfo", 0);
                    bundle3.putLong("_sys", 0);
                    bundle3.putLong("_sysu", 0);
                    if (this.zzada.zzgv().zzbk(zzk2.packageName)) {
                        bundle3.putLong("_et", 1);
                    }
                    if (this.zzada.zzgv().zzba(zzk2.packageName) && zzk2.zzagq) {
                        bundle3.putLong("_dac", 1);
                    }
                    if (this.zzada.getContext().getPackageManager() == null) {
                        this.zzada.zzgt().zzjg().zzg("PackageManager is null, first open report might be inaccurate. appId", zzas.zzbw(zzk2.packageName));
                    } else {
                        try {
                            packageInfo = Wrappers.packageManager(this.zzada.getContext()).getPackageInfo(zzk2.packageName, 0);
                        } catch (NameNotFoundException e2) {
                            this.zzada.zzgt().zzjg().zze("Package info is null, first open report might be inaccurate. appId", zzas.zzbw(zzk2.packageName), e2);
                            packageInfo = null;
                        }
                        if (!(packageInfo == null || packageInfo.firstInstallTime == 0)) {
                            if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                bundle3.putLong("_uwa", 1);
                                z = false;
                            } else {
                                z = true;
                            }
                            zzfu zzfu2 = r7;
                            zzfu zzfu3 = new zzfu("_fi", j2, Long.valueOf(z ? 1 : 0), "auto");
                            zzb(zzfu2, zzk2);
                        }
                        try {
                            applicationInfo = Wrappers.packageManager(this.zzada.getContext()).getApplicationInfo(zzk2.packageName, 0);
                        } catch (NameNotFoundException e3) {
                            this.zzada.zzgt().zzjg().zze("Application info is null, first open report might be inaccurate. appId", zzas.zzbw(zzk2.packageName), e3);
                            applicationInfo = null;
                        }
                        if (applicationInfo != null) {
                            if ((applicationInfo.flags & 1) != 0) {
                                bundle3.putLong("_sys", 1);
                            }
                            if ((applicationInfo.flags & 128) != 0) {
                                bundle3.putLong("_sysu", 1);
                            }
                        }
                    }
                    zzt zzjt2 = zzjt();
                    String str = zzk2.packageName;
                    Preconditions.checkNotEmpty(str);
                    zzjt2.zzaf();
                    zzjt2.zzcl();
                    long zzn = zzjt2.zzn(str, "first_open_count");
                    if (zzn >= 0) {
                        bundle3.putLong("_pfo", zzn);
                    }
                    zzag zzag3 = new zzag("_f", new zzad(bundle3), "auto", j2);
                    zzc(zzag3, zzk2);
                } else {
                    j = 1;
                    if (i == 1) {
                        zzfu zzfu4 = new zzfu("_fvt", j2, Long.valueOf(j3), "auto");
                        zzb(zzfu4, zzk2);
                        zzaf();
                        zzlx();
                        Bundle bundle4 = new Bundle();
                        bundle4.putLong("_c", 1);
                        bundle4.putLong("_r", 1);
                        if (this.zzada.zzgv().zzbk(zzk2.packageName)) {
                            bundle4.putLong("_et", 1);
                        }
                        if (this.zzada.zzgv().zzba(zzk2.packageName) && zzk2.zzagq) {
                            bundle4.putLong("_dac", 1);
                        }
                        zzag zzag4 = new zzag("_v", new zzad(bundle4), "auto", j2);
                        zzc(zzag4, zzk2);
                    }
                }
                if (!this.zzada.zzgv().zze(zzk2.packageName, zzai.zzalc)) {
                    Bundle bundle5 = new Bundle();
                    bundle5.putLong("_et", j);
                    if (this.zzada.zzgv().zzbk(zzk2.packageName)) {
                        bundle5.putLong("_fr", j);
                    }
                    zzag zzag5 = new zzag("_e", new zzad(bundle5), "auto", j2);
                    zzc(zzag5, zzk2);
                }
            } else if (zzk2.zzagn) {
                zzag zzag6 = new zzag("_cd", new zzad(new Bundle()), "auto", j2);
                zzc(zzag6, zzk2);
            }
            zzjt().setTransactionSuccessful();
            zzjt().endTransaction();
        }
    }

    @WorkerThread
    private final zzk zzcr(String str) {
        String str2 = str;
        zzg zzbm = zzjt().zzbm(str2);
        if (zzbm == null || TextUtils.isEmpty(zzbm.zzak())) {
            this.zzada.zzgt().zzjn().zzg("No app data available; dropping", str2);
            return null;
        }
        Boolean zzc = zzc(zzbm);
        if (zzc == null || zzc.booleanValue()) {
            zzg zzg = zzbm;
            zzk zzk = new zzk(str, zzbm.getGmpAppId(), zzbm.zzak(), zzbm.zzhf(), zzbm.zzhg(), zzbm.zzhh(), zzbm.zzhi(), (String) null, zzbm.isMeasurementEnabled(), false, zzbm.getFirebaseInstanceId(), zzg.zzhv(), 0, 0, zzg.zzhw(), zzg.zzhx(), false, zzg.zzhb());
            return zzk;
        }
        this.zzada.zzgt().zzjg().zzg("App version does not match; dropping. appId", zzas.zzbw(str));
        return null;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zze(zzo zzo) {
        zzk zzcr = zzcr(zzo.packageName);
        if (zzcr != null) {
            zzb(zzo, zzcr);
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzb(zzo zzo, zzk zzk) {
        Preconditions.checkNotNull(zzo);
        Preconditions.checkNotEmpty(zzo.packageName);
        Preconditions.checkNotNull(zzo.origin);
        Preconditions.checkNotNull(zzo.zzags);
        Preconditions.checkNotEmpty(zzo.zzags.name);
        zzaf();
        zzlx();
        if (TextUtils.isEmpty(zzk.zzafi) && TextUtils.isEmpty(zzk.zzafv)) {
            return;
        }
        if (!zzk.zzafr) {
            zzg(zzk);
            return;
        }
        zzo zzo2 = new zzo(zzo);
        boolean z = false;
        zzo2.active = false;
        zzjt().beginTransaction();
        try {
            zzo zzj = zzjt().zzj(zzo2.packageName, zzo2.zzags.name);
            if (zzj != null && !zzj.origin.equals(zzo2.origin)) {
                this.zzada.zzgt().zzjj().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzada.zzgq().zzbv(zzo2.zzags.name), zzo2.origin, zzj.origin);
            }
            if (zzj != null && zzj.active) {
                zzo2.origin = zzj.origin;
                zzo2.creationTimestamp = zzj.creationTimestamp;
                zzo2.triggerTimeout = zzj.triggerTimeout;
                zzo2.triggerEventName = zzj.triggerEventName;
                zzo2.zzagu = zzj.zzagu;
                zzo2.active = zzj.active;
                zzfu zzfu = new zzfu(zzo2.zzags.name, zzj.zzags.zzaum, zzo2.zzags.getValue(), zzj.zzags.origin);
                zzo2.zzags = zzfu;
            } else if (TextUtils.isEmpty(zzo2.triggerEventName)) {
                zzfu zzfu2 = new zzfu(zzo2.zzags.name, zzo2.creationTimestamp, zzo2.zzags.getValue(), zzo2.zzags.origin);
                zzo2.zzags = zzfu2;
                zzo2.active = true;
                z = true;
            }
            if (zzo2.active) {
                zzfu zzfu3 = zzo2.zzags;
                zzfw zzfw = new zzfw(zzo2.packageName, zzo2.origin, zzfu3.name, zzfu3.zzaum, zzfu3.getValue());
                if (zzjt().zza(zzfw)) {
                    this.zzada.zzgt().zzjn().zzd("User property updated immediately", zzo2.packageName, this.zzada.zzgq().zzbv(zzfw.name), zzfw.value);
                } else {
                    this.zzada.zzgt().zzjg().zzd("(2)Too many active user properties, ignoring", zzas.zzbw(zzo2.packageName), this.zzada.zzgq().zzbv(zzfw.name), zzfw.value);
                }
                if (z && zzo2.zzagu != null) {
                    zzd(new zzag(zzo2.zzagu, zzo2.creationTimestamp), zzk);
                }
            }
            if (zzjt().zza(zzo2)) {
                this.zzada.zzgt().zzjn().zzd("Conditional property added", zzo2.packageName, this.zzada.zzgq().zzbv(zzo2.zzags.name), zzo2.zzags.getValue());
            } else {
                this.zzada.zzgt().zzjg().zzd("Too many conditional properties, ignoring", zzas.zzbw(zzo2.packageName), this.zzada.zzgq().zzbv(zzo2.zzags.name), zzo2.zzags.getValue());
            }
            zzjt().setTransactionSuccessful();
        } finally {
            zzjt().endTransaction();
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzf(zzo zzo) {
        zzk zzcr = zzcr(zzo.packageName);
        if (zzcr != null) {
            zzc(zzo, zzcr);
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzc(zzo zzo, zzk zzk) {
        Preconditions.checkNotNull(zzo);
        Preconditions.checkNotEmpty(zzo.packageName);
        Preconditions.checkNotNull(zzo.zzags);
        Preconditions.checkNotEmpty(zzo.zzags.name);
        zzaf();
        zzlx();
        if (TextUtils.isEmpty(zzk.zzafi) && TextUtils.isEmpty(zzk.zzafv)) {
            return;
        }
        if (!zzk.zzafr) {
            zzg(zzk);
            return;
        }
        zzjt().beginTransaction();
        try {
            zzg(zzk);
            zzo zzj = zzjt().zzj(zzo.packageName, zzo.zzags.name);
            if (zzj != null) {
                this.zzada.zzgt().zzjn().zze("Removing conditional user property", zzo.packageName, this.zzada.zzgq().zzbv(zzo.zzags.name));
                zzjt().zzk(zzo.packageName, zzo.zzags.name);
                if (zzj.active) {
                    zzjt().zzh(zzo.packageName, zzo.zzags.name);
                }
                if (zzo.zzagv != null) {
                    zzd(this.zzada.zzgr().zza(zzo.packageName, zzo.zzagv.name, zzo.zzagv.zzahu != null ? zzo.zzagv.zzahu.zziy() : null, zzj.origin, zzo.zzagv.zzaig, true, false), zzk);
                }
            } else {
                this.zzada.zzgt().zzjj().zze("Conditional user property doesn't exist", zzas.zzbw(zzo.packageName), this.zzada.zzgq().zzbv(zzo.zzags.name));
            }
            zzjt().setTransactionSuccessful();
        } finally {
            zzjt().endTransaction();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final zzg zzg(zzk zzk) {
        boolean z;
        zzaf();
        zzlx();
        Preconditions.checkNotNull(zzk);
        Preconditions.checkNotEmpty(zzk.packageName);
        zzg zzbm = zzjt().zzbm(zzk.packageName);
        String zzca = this.zzada.zzgu().zzca(zzk.packageName);
        if (zzbm == null) {
            zzbm = new zzg(this.zzada, zzk.packageName);
            zzbm.zzaj(this.zzada.zzgr().zzmm());
            zzbm.zzam(zzca);
            z = true;
        } else if (!zzca.equals(zzbm.zzhc())) {
            zzbm.zzam(zzca);
            zzbm.zzaj(this.zzada.zzgr().zzmm());
            z = true;
        } else {
            z = false;
        }
        if (!TextUtils.equals(zzk.zzafi, zzbm.getGmpAppId())) {
            zzbm.zzak(zzk.zzafi);
            z = true;
        }
        if (!TextUtils.equals(zzk.zzafv, zzbm.zzhb())) {
            zzbm.zzal(zzk.zzafv);
            z = true;
        }
        if (!TextUtils.isEmpty(zzk.zzafk) && !zzk.zzafk.equals(zzbm.getFirebaseInstanceId())) {
            zzbm.zzan(zzk.zzafk);
            z = true;
        }
        if (!(zzk.zzade == 0 || zzk.zzade == zzbm.zzhh())) {
            zzbm.zzr(zzk.zzade);
            z = true;
        }
        if (!TextUtils.isEmpty(zzk.zzts) && !zzk.zzts.equals(zzbm.zzak())) {
            zzbm.setAppVersion(zzk.zzts);
            z = true;
        }
        if (zzk.zzafo != zzbm.zzhf()) {
            zzbm.zzq(zzk.zzafo);
            z = true;
        }
        if (zzk.zzafp != null && !zzk.zzafp.equals(zzbm.zzhg())) {
            zzbm.zzao(zzk.zzafp);
            z = true;
        }
        if (zzk.zzafq != zzbm.zzhi()) {
            zzbm.zzs(zzk.zzafq);
            z = true;
        }
        if (zzk.zzafr != zzbm.isMeasurementEnabled()) {
            zzbm.setMeasurementEnabled(zzk.zzafr);
            z = true;
        }
        if (!TextUtils.isEmpty(zzk.zzagm) && !zzk.zzagm.equals(zzbm.zzht())) {
            zzbm.zzap(zzk.zzagm);
            z = true;
        }
        if (zzk.zzafs != zzbm.zzhv()) {
            zzbm.zzac(zzk.zzafs);
            z = true;
        }
        if (zzk.zzaft != zzbm.zzhw()) {
            zzbm.zze(zzk.zzaft);
            z = true;
        }
        if (zzk.zzafu != zzbm.zzhx()) {
            zzbm.zzf(zzk.zzafu);
            z = true;
        }
        if (z) {
            zzjt().zza(zzbm);
        }
        return zzbm;
    }

    /* access modifiers changed from: 0000 */
    public final String zzh(zzk zzk) {
        try {
            return (String) this.zzada.zzgs().zzb((Callable<V>) new zzfr<V>(this, zzk)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzada.zzgt().zzjg().zze("Failed to get app instance id. appId", zzas.zzbw(zzk.packageName), e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzm(boolean z) {
        zzmb();
    }
}
