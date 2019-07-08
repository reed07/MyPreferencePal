package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbi extends zzau {
    private boolean started;
    private final zzbf zzxl;
    private final zzcr zzxm;
    private final zzcq zzxn;
    private final zzba zzxo;
    private long zzxp = Long.MIN_VALUE;
    private final zzbz zzxq;
    private final zzbz zzxr;
    private final zzdc zzxs;
    private long zzxt;
    private boolean zzxu;

    protected zzbi(zzaw zzaw, zzay zzay) {
        super(zzaw);
        Preconditions.checkNotNull(zzay);
        this.zzxn = new zzcq(zzaw);
        this.zzxl = new zzbf(zzaw);
        this.zzxm = new zzcr(zzaw);
        this.zzxo = new zzba(zzaw);
        this.zzxs = new zzdc(zzbx());
        this.zzxq = new zzbj(this, zzaw);
        this.zzxr = new zzbk(this, zzaw);
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
        this.zzxl.zzq();
        this.zzxm.zzq();
        this.zzxo.zzq();
    }

    /* access modifiers changed from: 0000 */
    public final void start() {
        zzcl();
        Preconditions.checkState(!this.started, "Analytics backend already started");
        this.started = true;
        zzca().zza((Runnable) new zzbl(this));
    }

    private final boolean zzx(String str) {
        return Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    /* access modifiers changed from: protected */
    public final void zzdg() {
        zzcl();
        zzk.zzaf();
        Context context = zzbw().getContext();
        if (!zzcw.zza(context)) {
            zzt("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzcx.zze(context)) {
            zzu("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zza(context)) {
            zzt("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzcf().zzff();
        if (!zzx("android.permission.ACCESS_NETWORK_STATE")) {
            zzu("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzdq();
        }
        if (!zzx("android.permission.INTERNET")) {
            zzu("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzdq();
        }
        if (zzcx.zze(getContext())) {
            zzq("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzt("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzxu && !this.zzxl.isEmpty()) {
            zzdj();
        }
        zzdm();
    }

    /* access modifiers changed from: private */
    public final void zzdh() {
        zzb((zzcd) new zzbm(this));
    }

    /* access modifiers changed from: 0000 */
    public final void zzbv() {
        zzk.zzaf();
        this.zzxt = zzbx().currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044 A[LOOP:1: B:15:0x0044->B:23:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0040 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected() {
        /*
            r5 = this;
            com.google.android.gms.analytics.zzk.zzaf()
            com.google.android.gms.analytics.zzk.zzaf()
            r5.zzcl()
            boolean r0 = com.google.android.gms.internal.measurement.zzbx.zzdx()
            if (r0 != 0) goto L_0x0014
            java.lang.String r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService"
            r5.zzt(r0)
        L_0x0014:
            com.google.android.gms.internal.measurement.zzba r0 = r5.zzxo
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "Service not connected"
            r5.zzq(r0)
            return
        L_0x0022:
            com.google.android.gms.internal.measurement.zzbf r0 = r5.zzxl
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x007e
            java.lang.String r0 = "Dispatching local hits to device AnalyticsService"
            r5.zzq(r0)
        L_0x002f:
            com.google.android.gms.internal.measurement.zzbf r0 = r5.zzxl     // Catch:{ SQLiteException -> 0x0074 }
            int r1 = com.google.android.gms.internal.measurement.zzbx.zzeb()     // Catch:{ SQLiteException -> 0x0074 }
            long r1 = (long) r1     // Catch:{ SQLiteException -> 0x0074 }
            java.util.List r0 = r0.zzd(r1)     // Catch:{ SQLiteException -> 0x0074 }
            boolean r1 = r0.isEmpty()     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x0044
            r5.zzdm()     // Catch:{ SQLiteException -> 0x0074 }
            return
        L_0x0044:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x002f
            r1 = 0
            java.lang.Object r1 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzck r1 = (com.google.android.gms.internal.measurement.zzck) r1
            com.google.android.gms.internal.measurement.zzba r2 = r5.zzxo
            boolean r2 = r2.zzb(r1)
            if (r2 != 0) goto L_0x005d
            r5.zzdm()
            return
        L_0x005d:
            r0.remove(r1)
            com.google.android.gms.internal.measurement.zzbf r2 = r5.zzxl     // Catch:{ SQLiteException -> 0x006a }
            long r3 = r1.zzeq()     // Catch:{ SQLiteException -> 0x006a }
            r2.zze(r3)     // Catch:{ SQLiteException -> 0x006a }
            goto L_0x0044
        L_0x006a:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r5.zze(r1, r0)
            r5.zzdo()
            return
        L_0x0074:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from store"
            r5.zze(r1, r0)
            r5.zzdo()
            return
        L_0x007e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbi.onServiceConnected():void");
    }

    /* access modifiers changed from: private */
    public final void zzdi() {
        try {
            this.zzxl.zzdb();
            zzdm();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzxr.zzh(86400000);
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzaz zzaz) {
        zzk.zzaf();
        zzb("Sending first hit to property", zzaz.zzct());
        if (!zzcf().zzfg().zzj(zzbx.zzeh())) {
            String zzfj = zzcf().zzfj();
            if (!TextUtils.isEmpty(zzfj)) {
                zzy zza = zzdg.zza(zzby(), zzfj);
                zzb("Found relevant installation campaign", zza);
                zza(zzaz, zza);
            }
        }
    }

    public final void zzg(long j) {
        zzk.zzaf();
        zzcl();
        if (j < 0) {
            j = 0;
        }
        this.zzxp = j;
        zzdm();
    }

    private final void zzdj() {
        if (!this.zzxu && zzbx.zzdx() && !this.zzxo.isConnected()) {
            if (this.zzxs.zzj(((Long) zzcf.zzaaj.get()).longValue())) {
                this.zzxs.start();
                zzq("Connecting to service");
                if (this.zzxo.connect()) {
                    zzq("Connected to service");
                    this.zzxs.clear();
                    onServiceConnected();
                }
            }
        }
    }

    public final long zza(zzaz zzaz, boolean z) {
        Preconditions.checkNotNull(zzaz);
        zzcl();
        zzk.zzaf();
        try {
            this.zzxl.beginTransaction();
            zzbf zzbf = this.zzxl;
            long zzcs = zzaz.zzcs();
            String zzbd = zzaz.zzbd();
            Preconditions.checkNotEmpty(zzbd);
            zzbf.zzcl();
            zzk.zzaf();
            int i = 1;
            int delete = zzbf.getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(zzcs), zzbd});
            if (delete > 0) {
                zzbf.zza("Deleted property records", Integer.valueOf(delete));
            }
            long zza = this.zzxl.zza(zzaz.zzcs(), zzaz.zzbd(), zzaz.zzct());
            zzaz.zzb(1 + zza);
            zzbf zzbf2 = this.zzxl;
            Preconditions.checkNotNull(zzaz);
            zzbf2.zzcl();
            zzk.zzaf();
            SQLiteDatabase writableDatabase = zzbf2.getWritableDatabase();
            Map zzcw = zzaz.zzcw();
            Preconditions.checkNotNull(zzcw);
            Builder builder = new Builder();
            for (Entry entry : zzcw.entrySet()) {
                builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            String encodedQuery = builder.build().getEncodedQuery();
            if (encodedQuery == null) {
                encodedQuery = "";
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_uid", Long.valueOf(zzaz.zzcs()));
            contentValues.put("cid", zzaz.zzbd());
            contentValues.put("tid", zzaz.zzct());
            String str = "adid";
            if (!zzaz.zzcu()) {
                i = 0;
            }
            contentValues.put(str, Integer.valueOf(i));
            contentValues.put("hits_count", Long.valueOf(zzaz.zzcv()));
            contentValues.put(NativeProtocol.WEB_DIALOG_PARAMS, encodedQuery);
            try {
                if (writableDatabase.insertWithOnConflict("properties", null, contentValues, 5) == -1) {
                    zzbf2.zzu("Failed to insert/update a property (got -1)");
                }
            } catch (SQLiteException e) {
                zzbf2.zze("Error storing a property", e);
            }
            this.zzxl.setTransactionSuccessful();
            try {
            } catch (SQLiteException e2) {
                zze("Failed to end transaction", e2);
            }
            return zza;
        } catch (SQLiteException e3) {
            zze("Failed to update Analytics property", e3);
            try {
            } catch (SQLiteException e4) {
                zze("Failed to end transaction", e4);
            }
            return -1;
        } finally {
            try {
                this.zzxl.endTransaction();
            } catch (SQLiteException e5) {
                zze("Failed to end transaction", e5);
            }
        }
    }

    public final void zza(zzck zzck) {
        Preconditions.checkNotNull(zzck);
        zzk.zzaf();
        zzcl();
        if (this.zzxu) {
            zzr("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzck);
        }
        if (TextUtils.isEmpty(zzck.zzev())) {
            Pair zzfm = zzcf().zzfk().zzfm();
            if (zzfm != null) {
                Long l = (Long) zzfm.second;
                String str = (String) zzfm.first;
                String valueOf = String.valueOf(l);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length());
                sb.append(valueOf);
                sb.append(":");
                sb.append(str);
                String sb2 = sb.toString();
                HashMap hashMap = new HashMap(zzck.zzcw());
                hashMap.put("_m", sb2);
                zzck zzck2 = new zzck(this, hashMap, zzck.zzer(), zzck.zzet(), zzck.zzeq(), zzck.zzep(), zzck.zzes());
                zzck = zzck2;
            }
        }
        zzdj();
        if (this.zzxo.zzb(zzck)) {
            zzr("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzxl.zzc(zzck);
            zzdm();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zzby().zza(zzck, "deliver: failed to insert hit to database");
        }
    }

    public final void zzbr() {
        zzk.zzaf();
        zzcl();
        zzq("Delete all hits from local store");
        try {
            zzbf zzbf = this.zzxl;
            zzk.zzaf();
            zzbf.zzcl();
            zzbf.getWritableDatabase().delete("hits2", null, null);
            zzbf zzbf2 = this.zzxl;
            zzk.zzaf();
            zzbf2.zzcl();
            zzbf2.getWritableDatabase().delete("properties", null, null);
            zzdm();
        } catch (SQLiteException e) {
            zzd("Failed to delete hits from store", e);
        }
        zzdj();
        if (this.zzxo.zzcx()) {
            zzq("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    private final boolean zzdk() {
        zzk.zzaf();
        zzcl();
        zzq("Dispatching a batch of local hits");
        boolean z = !this.zzxm.zzfb();
        if (!(!this.zzxo.isConnected()) || !z) {
            long max = (long) Math.max(zzbx.zzeb(), zzbx.zzec());
            ArrayList arrayList = new ArrayList();
            long j = 0;
            while (true) {
                try {
                    this.zzxl.beginTransaction();
                    arrayList.clear();
                    try {
                        List<zzck> zzd = this.zzxl.zzd(max);
                        if (zzd.isEmpty()) {
                            zzq("Store is empty, nothing to dispatch");
                            zzdo();
                            try {
                                this.zzxl.setTransactionSuccessful();
                                this.zzxl.endTransaction();
                                return false;
                            } catch (SQLiteException e) {
                                zze("Failed to commit local dispatch transaction", e);
                                zzdo();
                                return false;
                            }
                        } else {
                            zza("Hits loaded from store. count", Integer.valueOf(zzd.size()));
                            for (zzck zzeq : zzd) {
                                if (zzeq.zzeq() == j) {
                                    zzd("Database contains successfully uploaded hit", Long.valueOf(j), Integer.valueOf(zzd.size()));
                                    zzdo();
                                    try {
                                        return false;
                                    } catch (SQLiteException e2) {
                                        zze("Failed to commit local dispatch transaction", e2);
                                        zzdo();
                                        return false;
                                    }
                                }
                            }
                            if (this.zzxo.isConnected()) {
                                zzq("Service connected, sending hits to the service");
                                while (!zzd.isEmpty()) {
                                    zzck zzck = (zzck) zzd.get(0);
                                    if (this.zzxo.zzb(zzck)) {
                                        j = Math.max(j, zzck.zzeq());
                                        zzd.remove(zzck);
                                        zzb("Hit sent do device AnalyticsService for delivery", zzck);
                                        this.zzxl.zze(zzck.zzeq());
                                        arrayList.add(Long.valueOf(zzck.zzeq()));
                                    }
                                }
                            }
                            if (this.zzxm.zzfb()) {
                                List<Long> zzb = this.zzxm.zzb(zzd);
                                for (Long longValue : zzb) {
                                    j = Math.max(j, longValue.longValue());
                                }
                                try {
                                    this.zzxl.zza(zzb);
                                    arrayList.addAll(zzb);
                                } catch (SQLiteException e3) {
                                    zze("Failed to remove successfully uploaded hits", e3);
                                    zzdo();
                                    try {
                                        this.zzxl.setTransactionSuccessful();
                                        this.zzxl.endTransaction();
                                        return false;
                                    } catch (SQLiteException e4) {
                                        zze("Failed to commit local dispatch transaction", e4);
                                        zzdo();
                                        return false;
                                    }
                                }
                            }
                            if (arrayList.isEmpty()) {
                                try {
                                    this.zzxl.setTransactionSuccessful();
                                    this.zzxl.endTransaction();
                                    return false;
                                } catch (SQLiteException e5) {
                                    zze("Failed to commit local dispatch transaction", e5);
                                    zzdo();
                                    return false;
                                }
                            } else {
                                try {
                                    this.zzxl.setTransactionSuccessful();
                                    this.zzxl.endTransaction();
                                } catch (SQLiteException e6) {
                                    zze("Failed to commit local dispatch transaction", e6);
                                    zzdo();
                                    return false;
                                }
                            }
                        }
                    } catch (SQLiteException e7) {
                        zzd("Failed to read hits from persisted store", e7);
                        zzdo();
                        try {
                            this.zzxl.setTransactionSuccessful();
                            this.zzxl.endTransaction();
                            return false;
                        } catch (SQLiteException e8) {
                            zze("Failed to commit local dispatch transaction", e8);
                            zzdo();
                            return false;
                        }
                    }
                } catch (SQLiteException e9) {
                    zze("Failed to remove hit that was send for delivery", e9);
                    zzdo();
                    try {
                        return false;
                    } catch (SQLiteException e10) {
                        zze("Failed to commit local dispatch transaction", e10);
                        zzdo();
                        return false;
                    }
                } finally {
                    try {
                        this.zzxl.setTransactionSuccessful();
                        this.zzxl.endTransaction();
                    } catch (SQLiteException e11) {
                        zze("Failed to commit local dispatch transaction", e11);
                        zzdo();
                        return false;
                    }
                }
            }
        } else {
            zzq("No network or service available. Will retry later");
            return false;
        }
    }

    public final void zzb(zzcd zzcd) {
        long j = this.zzxt;
        zzk.zzaf();
        zzcl();
        long zzfh = zzcf().zzfh();
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(zzfh != 0 ? Math.abs(zzbx().currentTimeMillis() - zzfh) : -1));
        zzdj();
        try {
            zzdk();
            zzcf().zzfi();
            zzdm();
            if (zzcd != null) {
                zzcd.zza(null);
            }
            if (this.zzxt != j) {
                this.zzxn.zzfa();
            }
        } catch (Exception e) {
            zze("Local dispatch failed", e);
            zzcf().zzfi();
            zzdm();
            if (zzcd != null) {
                zzcd.zza(e);
            }
        }
    }

    public final void zzdl() {
        zzk.zzaf();
        zzcl();
        zzr("Sync dispatching local hits");
        long j = this.zzxt;
        zzdj();
        try {
            zzdk();
            zzcf().zzfi();
            zzdm();
            if (this.zzxt != j) {
                this.zzxn.zzfa();
            }
        } catch (Exception e) {
            zze("Sync local dispatch failed", e);
            zzdm();
        }
    }

    private final long zzdc() {
        zzk.zzaf();
        zzcl();
        try {
            return this.zzxl.zzdc();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    public final void zzdm() {
        long j;
        zzk.zzaf();
        zzcl();
        boolean z = true;
        if (!(!this.zzxu && zzdp() > 0)) {
            this.zzxn.unregister();
            zzdo();
        } else if (this.zzxl.isEmpty()) {
            this.zzxn.unregister();
            zzdo();
        } else {
            if (!((Boolean) zzcf.zzaae.get()).booleanValue()) {
                this.zzxn.zzey();
                z = this.zzxn.isConnected();
            }
            if (z) {
                zzdn();
                long zzdp = zzdp();
                long zzfh = zzcf().zzfh();
                if (zzfh != 0) {
                    j = zzdp - Math.abs(zzbx().currentTimeMillis() - zzfh);
                    if (j <= 0) {
                        j = Math.min(zzbx.zzdz(), zzdp);
                    }
                } else {
                    j = Math.min(zzbx.zzdz(), zzdp);
                }
                zza("Dispatch scheduled (ms)", Long.valueOf(j));
                if (this.zzxq.zzej()) {
                    this.zzxq.zzi(Math.max(1, j + this.zzxq.zzei()));
                } else {
                    this.zzxq.zzh(j);
                }
            } else {
                zzdo();
                zzdn();
            }
        }
    }

    private final void zzdn() {
        zzcc zzcd = zzcd();
        if (zzcd.zzem() && !zzcd.zzej()) {
            long zzdc = zzdc();
            if (zzdc != 0 && Math.abs(zzbx().currentTimeMillis() - zzdc) <= ((Long) zzcf.zzzi.get()).longValue()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzbx.zzea()));
                zzcd.zzen();
            }
        }
    }

    private final void zzdo() {
        if (this.zzxq.zzej()) {
            zzq("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzxq.cancel();
        zzcc zzcd = zzcd();
        if (zzcd.zzej()) {
            zzcd.cancel();
        }
    }

    private final long zzdp() {
        long j = this.zzxp;
        if (j != Long.MIN_VALUE) {
            return j;
        }
        long longValue = ((Long) zzcf.zzzd.get()).longValue();
        zzdh zzce = zzce();
        zzce.zzcl();
        if (zzce.zzacr) {
            zzdh zzce2 = zzce();
            zzce2.zzcl();
            longValue = ((long) zzce2.zzaat) * 1000;
        }
        return longValue;
    }

    public final void zzy(String str) {
        Preconditions.checkNotEmpty(str);
        zzk.zzaf();
        zzy zza = zzdg.zza(zzby(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzfj = zzcf().zzfj();
        if (str.equals(zzfj)) {
            zzt("Ignoring duplicate install campaign");
        } else if (!TextUtils.isEmpty(zzfj)) {
            zzd("Ignoring multiple install campaigns. original, new", zzfj, str);
        } else {
            zzcf().zzac(str);
            if (zzcf().zzfg().zzj(zzbx.zzeh())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzaz zza2 : this.zzxl.zzf(0)) {
                zza(zza2, zza);
            }
        }
    }

    private final void zza(zzaz zzaz, zzy zzy) {
        Preconditions.checkNotNull(zzaz);
        Preconditions.checkNotNull(zzy);
        zza zza = new zza(zzbw());
        zza.zza(zzaz.zzct());
        zza.enableAdvertisingIdCollection(zzaz.zzcu());
        zzg zzm = zza.zzm();
        zzag zzag = (zzag) zzm.zzb(zzag.class);
        zzag.zzl("data");
        zzag.zzb(true);
        zzm.zza((zzi) zzy);
        zzab zzab = (zzab) zzm.zzb(zzab.class);
        zzx zzx = (zzx) zzm.zzb(zzx.class);
        for (Entry entry : zzaz.zzcw().entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if ("an".equals(str)) {
                zzx.setAppName(str2);
            } else if ("av".equals(str)) {
                zzx.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                zzx.setAppId(str2);
            } else if ("aiid".equals(str)) {
                zzx.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                zzag.setUserId(str2);
            } else {
                zzab.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", zzaz.zzct(), zzy);
        zzm.zza(zzcf().zzff());
        zzm.zzw();
    }

    private final void zzdq() {
        zzcl();
        zzk.zzaf();
        this.zzxu = true;
        this.zzxo.disconnect();
        zzdm();
    }
}
