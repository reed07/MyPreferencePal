package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.brightcove.player.event.EventType;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfi;
import com.google.android.gms.internal.measurement.zzfj;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzxz;
import com.google.android.gms.internal.measurement.zzya;
import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import com.mopub.common.Constants;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzt extends zzfm {
    /* access modifiers changed from: private */
    public static final String[] zzagz = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzaha = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzahb = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzahc = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzahd = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzahe = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzw zzahf = new zzw(this, getContext(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zzfi zzahg = new zzfi(zzbx());

    zzt(zzfn zzfn) {
        super(zzfn);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    @WorkerThread
    public final void beginTransaction() {
        zzcl();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public final void setTransactionSuccessful() {
        zzcl();
        getWritableDatabase().setTransactionSuccessful();
    }

    @WorkerThread
    public final void endTransaction() {
        zzcl();
        getWritableDatabase().endTransaction();
    }

    @WorkerThread
    private final long zza(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzgt().zzjg().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j2 = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j2;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzgt().zzjg().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final SQLiteDatabase getWritableDatabase() {
        zzaf();
        try {
            return this.zzahf.getWritableDatabase();
        } catch (SQLiteException e) {
            zzgt().zzjj().zzg("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x011f  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzac zzg(java.lang.String r23, java.lang.String r24) {
        /*
            r22 = this;
            r15 = r24
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            r22.zzaf()
            r22.zzcl()
            r16 = 0
            android.database.sqlite.SQLiteDatabase r1 = r22.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f4 }
            java.lang.String r2 = "events"
            java.lang.String r3 = "lifetime_count"
            java.lang.String r4 = "current_bundle_count"
            java.lang.String r5 = "last_fire_timestamp"
            java.lang.String r6 = "last_bundled_timestamp"
            java.lang.String r7 = "last_bundled_day"
            java.lang.String r8 = "last_sampled_complex_event_id"
            java.lang.String r9 = "last_sampling_rate"
            java.lang.String r10 = "last_exempt_from_sampling"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5, r6, r7, r8, r9, r10}     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f4 }
            java.lang.String r4 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f4 }
            r9 = 0
            r5[r9] = r23     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f4 }
            r10 = 1
            r5[r10] = r15     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f4 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f4 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r1 != 0) goto L_0x0048
            if (r14 == 0) goto L_0x0047
            r14.close()
        L_0x0047:
            return r16
        L_0x0048:
            long r4 = r14.getLong(r9)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            long r6 = r14.getLong(r10)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            long r11 = r14.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            r0 = 3
            boolean r1 = r14.isNull(r0)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r1 == 0) goto L_0x005e
            r0 = 0
            goto L_0x0062
        L_0x005e:
            long r0 = r14.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
        L_0x0062:
            r17 = r0
            r0 = 4
            boolean r1 = r14.isNull(r0)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r1 == 0) goto L_0x006e
            r0 = r16
            goto L_0x0076
        L_0x006e:
            long r0 = r14.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
        L_0x0076:
            r1 = 5
            boolean r2 = r14.isNull(r1)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r2 == 0) goto L_0x0080
            r13 = r16
            goto L_0x0089
        L_0x0080:
            long r1 = r14.getLong(r1)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            r13 = r1
        L_0x0089:
            r1 = 6
            boolean r2 = r14.isNull(r1)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r2 == 0) goto L_0x0093
            r19 = r16
            goto L_0x009d
        L_0x0093:
            long r1 = r14.getLong(r1)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            r19 = r1
        L_0x009d:
            r1 = 7
            boolean r2 = r14.isNull(r1)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r2 != 0) goto L_0x00b6
            long r1 = r14.getLong(r1)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            r20 = 1
            int r3 = (r1 > r20 ? 1 : (r1 == r20 ? 0 : -1))
            if (r3 != 0) goto L_0x00af
            r9 = 1
        L_0x00af:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r9)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            r20 = r1
            goto L_0x00b8
        L_0x00b6:
            r20 = r16
        L_0x00b8:
            com.google.android.gms.measurement.internal.zzac r21 = new com.google.android.gms.measurement.internal.zzac     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            r1 = r21
            r2 = r23
            r3 = r24
            r8 = r11
            r10 = r17
            r12 = r0
            r17 = r14
            r14 = r19
            r15 = r20
            r1.<init>(r2, r3, r4, r6, r8, r10, r12, r13, r14, r15)     // Catch:{ SQLiteException -> 0x00ea }
            boolean r0 = r17.moveToNext()     // Catch:{ SQLiteException -> 0x00ea }
            if (r0 == 0) goto L_0x00e4
            com.google.android.gms.measurement.internal.zzas r0 = r22.zzgt()     // Catch:{ SQLiteException -> 0x00ea }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ SQLiteException -> 0x00ea }
            java.lang.String r1 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzas.zzbw(r23)     // Catch:{ SQLiteException -> 0x00ea }
            r0.zzg(r1, r2)     // Catch:{ SQLiteException -> 0x00ea }
        L_0x00e4:
            if (r17 == 0) goto L_0x00e9
            r17.close()
        L_0x00e9:
            return r21
        L_0x00ea:
            r0 = move-exception
            goto L_0x00fb
        L_0x00ec:
            r0 = move-exception
            r17 = r14
            goto L_0x011d
        L_0x00f0:
            r0 = move-exception
            r17 = r14
            goto L_0x00fb
        L_0x00f4:
            r0 = move-exception
            r17 = r16
            goto L_0x011d
        L_0x00f8:
            r0 = move-exception
            r17 = r16
        L_0x00fb:
            com.google.android.gms.measurement.internal.zzas r1 = r22.zzgt()     // Catch:{ all -> 0x011c }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ all -> 0x011c }
            java.lang.String r2 = "Error querying events. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzas.zzbw(r23)     // Catch:{ all -> 0x011c }
            com.google.android.gms.measurement.internal.zzaq r4 = r22.zzgq()     // Catch:{ all -> 0x011c }
            r5 = r24
            java.lang.String r4 = r4.zzbt(r5)     // Catch:{ all -> 0x011c }
            r1.zzd(r2, r3, r4, r0)     // Catch:{ all -> 0x011c }
            if (r17 == 0) goto L_0x011b
            r17.close()
        L_0x011b:
            return r16
        L_0x011c:
            r0 = move-exception
        L_0x011d:
            if (r17 == 0) goto L_0x0122
            r17.close()
        L_0x0122:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzg(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzac");
    }

    @WorkerThread
    public final void zza(zzac zzac) {
        Preconditions.checkNotNull(zzac);
        zzaf();
        zzcl();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzac.zztt);
        contentValues.put("name", zzac.name);
        contentValues.put("lifetime_count", Long.valueOf(zzac.zzahv));
        contentValues.put("current_bundle_count", Long.valueOf(zzac.zzahw));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzac.zzahx));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzac.zzahy));
        contentValues.put("last_bundled_day", zzac.zzahz);
        contentValues.put("last_sampled_complex_event_id", zzac.zzaia);
        contentValues.put("last_sampling_rate", zzac.zzaib);
        contentValues.put("last_exempt_from_sampling", (zzac.zzaic == null || !zzac.zzaic.booleanValue()) ? null : Long.valueOf(1));
        try {
            if (getWritableDatabase().insertWithOnConflict(Constants.VIDEO_TRACKING_EVENTS_KEY, null, contentValues, 5) == -1) {
                zzgt().zzjg().zzg("Failed to insert/update event aggregates (got -1). appId", zzas.zzbw(zzac.zztt));
            }
        } catch (SQLiteException e) {
            zzgt().zzjg().zze("Error storing event aggregates. appId", zzas.zzbw(zzac.zztt), e);
        }
    }

    @WorkerThread
    public final void zzh(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        try {
            zzgt().zzjo().zzg("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzgt().zzjg().zzd("Error deleting user attribute. appId", zzas.zzbw(str), zzgq().zzbv(str2), e);
        }
    }

    @WorkerThread
    public final boolean zza(zzfw zzfw) {
        Preconditions.checkNotNull(zzfw);
        zzaf();
        zzcl();
        if (zzi(zzfw.zztt, zzfw.name) == null) {
            if (zzfx.zzct(zzfw.name)) {
                if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzfw.zztt}) >= 25) {
                    return false;
                }
            } else {
                if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzfw.zztt, zzfw.origin}) >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzfw.zztt);
        contentValues.put("origin", zzfw.origin);
        contentValues.put("name", zzfw.name);
        contentValues.put("set_timestamp", Long.valueOf(zzfw.zzaum));
        zza(contentValues, "value", zzfw.value);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzgt().zzjg().zzg("Failed to insert/update user property (got -1). appId", zzas.zzbw(zzfw.zztt));
            }
        } catch (SQLiteException e) {
            zzgt().zzjg().zze("Error storing user property. appId", zzas.zzbw(zzfw.zztt), e);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a9  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzfw zzi(java.lang.String r19, java.lang.String r20) {
        /*
            r18 = this;
            r8 = r20
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r19)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            r18.zzaf()
            r18.zzcl()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r18.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            java.lang.String r11 = "user_attributes"
            java.lang.String r0 = "set_timestamp"
            java.lang.String r1 = "value"
            java.lang.String r2 = "origin"
            java.lang.String[] r12 = new java.lang.String[]{r0, r1, r2}     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            java.lang.String r13 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r14 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            r1 = 0
            r14[r1] = r19     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            r2 = 1
            r14[r2] = r8     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x0083, all -> 0x007e }
            boolean r3 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x007a, all -> 0x0076 }
            if (r3 != 0) goto L_0x003f
            if (r10 == 0) goto L_0x003e
            r10.close()
        L_0x003e:
            return r9
        L_0x003f:
            long r5 = r10.getLong(r1)     // Catch:{ SQLiteException -> 0x007a, all -> 0x0076 }
            r11 = r18
            java.lang.Object r7 = r11.zza(r10, r2)     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.String r3 = r10.getString(r0)     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.measurement.internal.zzfw r0 = new com.google.android.gms.measurement.internal.zzfw     // Catch:{ SQLiteException -> 0x0074 }
            r1 = r0
            r2 = r19
            r4 = r20
            r1.<init>(r2, r3, r4, r5, r7)     // Catch:{ SQLiteException -> 0x0074 }
            boolean r1 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x006e
            com.google.android.gms.measurement.internal.zzas r1 = r18.zzgt()     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzas.zzbw(r19)     // Catch:{ SQLiteException -> 0x0074 }
            r1.zzg(r2, r3)     // Catch:{ SQLiteException -> 0x0074 }
        L_0x006e:
            if (r10 == 0) goto L_0x0073
            r10.close()
        L_0x0073:
            return r0
        L_0x0074:
            r0 = move-exception
            goto L_0x0087
        L_0x0076:
            r0 = move-exception
            r11 = r18
            goto L_0x00a7
        L_0x007a:
            r0 = move-exception
            r11 = r18
            goto L_0x0087
        L_0x007e:
            r0 = move-exception
            r11 = r18
            r10 = r9
            goto L_0x00a7
        L_0x0083:
            r0 = move-exception
            r11 = r18
            r10 = r9
        L_0x0087:
            com.google.android.gms.measurement.internal.zzas r1 = r18.zzgt()     // Catch:{ all -> 0x00a6 }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ all -> 0x00a6 }
            java.lang.String r2 = "Error querying user property. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzas.zzbw(r19)     // Catch:{ all -> 0x00a6 }
            com.google.android.gms.measurement.internal.zzaq r4 = r18.zzgq()     // Catch:{ all -> 0x00a6 }
            java.lang.String r4 = r4.zzbv(r8)     // Catch:{ all -> 0x00a6 }
            r1.zzd(r2, r3, r4, r0)     // Catch:{ all -> 0x00a6 }
            if (r10 == 0) goto L_0x00a5
            r10.close()
        L_0x00a5:
            return r9
        L_0x00a6:
            r0 = move-exception
        L_0x00a7:
            if (r10 == 0) goto L_0x00ac
            r10.close()
        L_0x00ac:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzi(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzfw");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a3  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzfw> zzbl(java.lang.String r14) {
        /*
            r13 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            r13.zzaf()
            r13.zzcl()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r13.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0087, all -> 0x0084 }
            java.lang.String r3 = "user_attributes"
            java.lang.String r4 = "name"
            java.lang.String r5 = "origin"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6, r7}     // Catch:{ SQLiteException -> 0x0087, all -> 0x0084 }
            java.lang.String r5 = "app_id=?"
            r11 = 1
            java.lang.String[] r6 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x0087, all -> 0x0084 }
            r12 = 0
            r6[r12] = r14     // Catch:{ SQLiteException -> 0x0087, all -> 0x0084 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "1000"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0087, all -> 0x0084 }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0082 }
            if (r3 != 0) goto L_0x003f
            if (r2 == 0) goto L_0x003e
            r2.close()
        L_0x003e:
            return r0
        L_0x003f:
            java.lang.String r7 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x0082 }
            java.lang.String r3 = r2.getString(r11)     // Catch:{ SQLiteException -> 0x0082 }
            if (r3 != 0) goto L_0x004d
            java.lang.String r3 = ""
            r6 = r3
            goto L_0x004e
        L_0x004d:
            r6 = r3
        L_0x004e:
            r3 = 2
            long r8 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0082 }
            r3 = 3
            java.lang.Object r10 = r13.zza(r2, r3)     // Catch:{ SQLiteException -> 0x0082 }
            if (r10 != 0) goto L_0x006c
            com.google.android.gms.measurement.internal.zzas r3 = r13.zzgt()     // Catch:{ SQLiteException -> 0x0082 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ SQLiteException -> 0x0082 }
            java.lang.String r4 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzas.zzbw(r14)     // Catch:{ SQLiteException -> 0x0082 }
            r3.zzg(r4, r5)     // Catch:{ SQLiteException -> 0x0082 }
            goto L_0x0076
        L_0x006c:
            com.google.android.gms.measurement.internal.zzfw r3 = new com.google.android.gms.measurement.internal.zzfw     // Catch:{ SQLiteException -> 0x0082 }
            r4 = r3
            r5 = r14
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x0082 }
            r0.add(r3)     // Catch:{ SQLiteException -> 0x0082 }
        L_0x0076:
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0082 }
            if (r3 != 0) goto L_0x003f
            if (r2 == 0) goto L_0x0081
            r2.close()
        L_0x0081:
            return r0
        L_0x0082:
            r0 = move-exception
            goto L_0x0089
        L_0x0084:
            r14 = move-exception
            r2 = r1
            goto L_0x00a1
        L_0x0087:
            r0 = move-exception
            r2 = r1
        L_0x0089:
            com.google.android.gms.measurement.internal.zzas r3 = r13.zzgt()     // Catch:{ all -> 0x00a0 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ all -> 0x00a0 }
            java.lang.String r4 = "Error querying user properties. appId"
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzas.zzbw(r14)     // Catch:{ all -> 0x00a0 }
            r3.zze(r4, r14, r0)     // Catch:{ all -> 0x00a0 }
            if (r2 == 0) goto L_0x009f
            r2.close()
        L_0x009f:
            return r1
        L_0x00a0:
            r14 = move-exception
        L_0x00a1:
            if (r2 == 0) goto L_0x00a6
            r2.close()
        L_0x00a6:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzbl(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f9, code lost:
        r12 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0100, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0101, code lost:
        r12 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0104, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0105, code lost:
        r12 = r21;
        r11 = r22;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0100 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0127  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzfw> zzb(java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r22)
            r21.zzaf()
            r21.zzcl()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0104, all -> 0x0100 }
            r3 = 3
            r2.<init>(r3)     // Catch:{ SQLiteException -> 0x0104, all -> 0x0100 }
            r11 = r22
            r2.add(r11)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            java.lang.String r5 = "app_id=?"
            r4.<init>(r5)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            boolean r5 = android.text.TextUtils.isEmpty(r23)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            if (r5 != 0) goto L_0x0032
            r5 = r23
            r2.add(r5)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r6 = " and origin=?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            goto L_0x0034
        L_0x0032:
            r5 = r23
        L_0x0034:
            boolean r6 = android.text.TextUtils.isEmpty(r24)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            if (r6 != 0) goto L_0x004c
            java.lang.String r6 = java.lang.String.valueOf(r24)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r7 = "*"
            java.lang.String r6 = r6.concat(r7)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            r2.add(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r6 = " and name glob ?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
        L_0x004c:
            int r6 = r2.size()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.Object[] r2 = r2.toArray(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            r16 = r2
            java.lang.String[] r16 = (java.lang.String[]) r16     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            android.database.sqlite.SQLiteDatabase r12 = r21.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r13 = "user_attributes"
            java.lang.String r2 = "name"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String r8 = "origin"
            java.lang.String[] r14 = new java.lang.String[]{r2, r6, r7, r8}     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r15 = r4.toString()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            java.lang.String r20 = "1001"
            android.database.Cursor r2 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            boolean r4 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            if (r4 != 0) goto L_0x0088
            if (r2 == 0) goto L_0x0087
            r2.close()
        L_0x0087:
            return r0
        L_0x0088:
            int r4 = r0.size()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r6 = 1000(0x3e8, float:1.401E-42)
            if (r4 < r6) goto L_0x00a4
            com.google.android.gms.measurement.internal.zzas r3 = r21.zzgt()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            java.lang.String r4 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r3.zzg(r4, r6)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r12 = r21
            goto L_0x00e3
        L_0x00a4:
            r4 = 0
            java.lang.String r7 = r2.getString(r4)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r4 = 1
            long r8 = r2.getLong(r4)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r4 = 2
            r12 = r21
            java.lang.Object r10 = r12.zza(r2, r4)     // Catch:{ SQLiteException -> 0x00ee }
            java.lang.String r13 = r2.getString(r3)     // Catch:{ SQLiteException -> 0x00ee }
            if (r10 != 0) goto L_0x00cf
            com.google.android.gms.measurement.internal.zzas r4 = r21.zzgt()     // Catch:{ SQLiteException -> 0x00eb }
            com.google.android.gms.measurement.internal.zzau r4 = r4.zzjg()     // Catch:{ SQLiteException -> 0x00eb }
            java.lang.String r5 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzas.zzbw(r22)     // Catch:{ SQLiteException -> 0x00eb }
            r14 = r24
            r4.zzd(r5, r6, r13, r14)     // Catch:{ SQLiteException -> 0x00eb }
            goto L_0x00dd
        L_0x00cf:
            r14 = r24
            com.google.android.gms.measurement.internal.zzfw r15 = new com.google.android.gms.measurement.internal.zzfw     // Catch:{ SQLiteException -> 0x00eb }
            r4 = r15
            r5 = r22
            r6 = r13
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x00eb }
            r0.add(r15)     // Catch:{ SQLiteException -> 0x00eb }
        L_0x00dd:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00eb }
            if (r4 != 0) goto L_0x00e9
        L_0x00e3:
            if (r2 == 0) goto L_0x00e8
            r2.close()
        L_0x00e8:
            return r0
        L_0x00e9:
            r5 = r13
            goto L_0x0088
        L_0x00eb:
            r0 = move-exception
            r5 = r13
            goto L_0x010c
        L_0x00ee:
            r0 = move-exception
            goto L_0x010c
        L_0x00f0:
            r0 = move-exception
            r12 = r21
            goto L_0x0124
        L_0x00f4:
            r0 = move-exception
            r12 = r21
            goto L_0x010c
        L_0x00f8:
            r0 = move-exception
            r12 = r21
            goto L_0x010b
        L_0x00fc:
            r0 = move-exception
            r12 = r21
            goto L_0x0109
        L_0x0100:
            r0 = move-exception
            r12 = r21
            goto L_0x0125
        L_0x0104:
            r0 = move-exception
            r12 = r21
            r11 = r22
        L_0x0109:
            r5 = r23
        L_0x010b:
            r2 = r1
        L_0x010c:
            com.google.android.gms.measurement.internal.zzas r3 = r21.zzgt()     // Catch:{ all -> 0x0123 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ all -> 0x0123 }
            java.lang.String r4 = "(2)Error querying user properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzas.zzbw(r22)     // Catch:{ all -> 0x0123 }
            r3.zzd(r4, r6, r5, r0)     // Catch:{ all -> 0x0123 }
            if (r2 == 0) goto L_0x0122
            r2.close()
        L_0x0122:
            return r1
        L_0x0123:
            r0 = move-exception
        L_0x0124:
            r1 = r2
        L_0x0125:
            if (r1 == 0) goto L_0x012a
            r1.close()
        L_0x012a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public final boolean zza(zzo zzo) {
        Preconditions.checkNotNull(zzo);
        zzaf();
        zzcl();
        if (zzi(zzo.packageName, zzo.zzags.name) == null) {
            if (zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzo.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzo.packageName);
        contentValues.put("origin", zzo.origin);
        contentValues.put("name", zzo.zzags.name);
        zza(contentValues, "value", zzo.zzags.getValue());
        contentValues.put("active", Boolean.valueOf(zzo.active));
        contentValues.put(ConditionalUserProperty.TRIGGER_EVENT_NAME, zzo.triggerEventName);
        contentValues.put(ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzo.triggerTimeout));
        zzgr();
        contentValues.put("timed_out_event", zzfx.zza((Parcelable) zzo.zzagt));
        contentValues.put(ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzo.creationTimestamp));
        zzgr();
        contentValues.put("triggered_event", zzfx.zza((Parcelable) zzo.zzagu));
        contentValues.put(ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzo.zzags.zzaum));
        contentValues.put(ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzo.timeToLive));
        zzgr();
        contentValues.put("expired_event", zzfx.zza((Parcelable) zzo.zzagv));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzgt().zzjg().zzg("Failed to insert/update conditional user property (got -1)", zzas.zzbw(zzo.packageName));
            }
        } catch (SQLiteException e) {
            zzgt().zzjg().zze("Error storing conditional user property", zzas.zzbw(zzo.packageName), e);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0125  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzo zzj(java.lang.String r30, java.lang.String r31) {
        /*
            r29 = this;
            r7 = r31
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r30)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r31)
            r29.zzaf()
            r29.zzcl()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r29.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            java.lang.String r10 = "conditional_properties"
            java.lang.String r11 = "origin"
            java.lang.String r12 = "value"
            java.lang.String r13 = "active"
            java.lang.String r14 = "trigger_event_name"
            java.lang.String r15 = "trigger_timeout"
            java.lang.String r16 = "timed_out_event"
            java.lang.String r17 = "creation_timestamp"
            java.lang.String r18 = "triggered_event"
            java.lang.String r19 = "triggered_timestamp"
            java.lang.String r20 = "time_to_live"
            java.lang.String r21 = "expired_event"
            java.lang.String[] r11 = new java.lang.String[]{r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21}     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            java.lang.String r12 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r13 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            r1 = 0
            r13[r1] = r30     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            r2 = 1
            r13[r2] = r7     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            r14 = 0
            r15 = 0
            r16 = 0
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x00fa }
            boolean r3 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            if (r3 != 0) goto L_0x004e
            if (r9 == 0) goto L_0x004d
            r9.close()
        L_0x004d:
            return r8
        L_0x004e:
            java.lang.String r16 = r9.getString(r1)     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            r10 = r29
            java.lang.Object r5 = r10.zza(r9, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            int r0 = r9.getInt(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            if (r0 == 0) goto L_0x0061
            r20 = 1
            goto L_0x0063
        L_0x0061:
            r20 = 0
        L_0x0063:
            r0 = 3
            java.lang.String r21 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 4
            long r23 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzft r0 = r29.zzjr()     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = 5
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzag> r2 = com.google.android.gms.measurement.internal.zzag.CREATOR     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable r0 = r0.zza(r1, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            r22 = r0
            com.google.android.gms.measurement.internal.zzag r22 = (com.google.android.gms.measurement.internal.zzag) r22     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 6
            long r18 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzft r0 = r29.zzjr()     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = 7
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzag> r2 = com.google.android.gms.measurement.internal.zzag.CREATOR     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable r0 = r0.zza(r1, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            r25 = r0
            com.google.android.gms.measurement.internal.zzag r25 = (com.google.android.gms.measurement.internal.zzag) r25     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 8
            long r3 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 9
            long r26 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzft r0 = r29.zzjr()     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = 10
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzag> r2 = com.google.android.gms.measurement.internal.zzag.CREATOR     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable r0 = r0.zza(r1, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            r28 = r0
            com.google.android.gms.measurement.internal.zzag r28 = (com.google.android.gms.measurement.internal.zzag) r28     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzfu r17 = new com.google.android.gms.measurement.internal.zzfu     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = r17
            r2 = r31
            r6 = r16
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzo r0 = new com.google.android.gms.measurement.internal.zzo     // Catch:{ SQLiteException -> 0x00f0 }
            r14 = r0
            r15 = r30
            r14.<init>(r15, r16, r17, r18, r20, r21, r22, r23, r25, r26, r28)     // Catch:{ SQLiteException -> 0x00f0 }
            boolean r1 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x00f0 }
            if (r1 == 0) goto L_0x00ea
            com.google.android.gms.measurement.internal.zzas r1 = r29.zzgt()     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r2 = "Got multiple records for conditional property, expected one"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzas.zzbw(r30)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzaq r4 = r29.zzgq()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r4 = r4.zzbv(r7)     // Catch:{ SQLiteException -> 0x00f0 }
            r1.zze(r2, r3, r4)     // Catch:{ SQLiteException -> 0x00f0 }
        L_0x00ea:
            if (r9 == 0) goto L_0x00ef
            r9.close()
        L_0x00ef:
            return r0
        L_0x00f0:
            r0 = move-exception
            goto L_0x0103
        L_0x00f2:
            r0 = move-exception
            r10 = r29
            goto L_0x0123
        L_0x00f6:
            r0 = move-exception
            r10 = r29
            goto L_0x0103
        L_0x00fa:
            r0 = move-exception
            r10 = r29
            r9 = r8
            goto L_0x0123
        L_0x00ff:
            r0 = move-exception
            r10 = r29
            r9 = r8
        L_0x0103:
            com.google.android.gms.measurement.internal.zzas r1 = r29.zzgt()     // Catch:{ all -> 0x0122 }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ all -> 0x0122 }
            java.lang.String r2 = "Error querying conditional property"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzas.zzbw(r30)     // Catch:{ all -> 0x0122 }
            com.google.android.gms.measurement.internal.zzaq r4 = r29.zzgq()     // Catch:{ all -> 0x0122 }
            java.lang.String r4 = r4.zzbv(r7)     // Catch:{ all -> 0x0122 }
            r1.zzd(r2, r3, r4, r0)     // Catch:{ all -> 0x0122 }
            if (r9 == 0) goto L_0x0121
            r9.close()
        L_0x0121:
            return r8
        L_0x0122:
            r0 = move-exception
        L_0x0123:
            if (r9 == 0) goto L_0x0128
            r9.close()
        L_0x0128:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzj(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzo");
    }

    @WorkerThread
    public final int zzk(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzgt().zzjg().zzd("Error deleting conditional property", zzas.zzbw(str), zzgq().zzbv(str2), e);
            return 0;
        }
    }

    @WorkerThread
    public final List<zzo> zzc(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzaf();
        zzcl();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat(EventType.ANY));
            sb.append(" and name glob ?");
        }
        return zzb(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final List<zzo> zzb(String str, String[] strArr) {
        zzaf();
        zzcl();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().query("conditional_properties", new String[]{"app_id", "origin", "name", "value", "active", ConditionalUserProperty.TRIGGER_EVENT_NAME, ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", ConditionalUserProperty.TRIGGERED_TIMESTAMP, ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, str, strArr, null, null, "rowid", NativeContentAd.ASSET_HEADLINE);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            while (true) {
                if (arrayList.size() < 1000) {
                    boolean z = false;
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Object zza = zza(cursor, 3);
                    if (cursor.getInt(4) != 0) {
                        z = true;
                    }
                    String string4 = cursor.getString(5);
                    long j = cursor.getLong(6);
                    zzag zzag = (zzag) zzjr().zza(cursor.getBlob(7), zzag.CREATOR);
                    long j2 = cursor.getLong(8);
                    zzag zzag2 = (zzag) zzjr().zza(cursor.getBlob(9), zzag.CREATOR);
                    long j3 = cursor.getLong(10);
                    long j4 = cursor.getLong(11);
                    zzag zzag3 = (zzag) zzjr().zza(cursor.getBlob(12), zzag.CREATOR);
                    zzfu zzfu = new zzfu(string3, j3, zza, string2);
                    boolean z2 = z;
                    zzo zzo = r3;
                    zzo zzo2 = new zzo(string, string2, zzfu, j2, z2, string4, zzag, j, zzag2, j4, zzag3);
                    arrayList.add(zzo);
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } else {
                    zzgt().zzjg().zzg("Read more than the max allowed conditional properties, ignoring extra", Integer.valueOf(1000));
                    break;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzgt().zzjg().zzg("Error querying conditional user property value", e);
            List<zzo> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0115 A[Catch:{ SQLiteException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0119 A[Catch:{ SQLiteException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x014d A[Catch:{ SQLiteException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0150 A[Catch:{ SQLiteException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x015f A[Catch:{ SQLiteException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0174 A[Catch:{ SQLiteException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0191 A[Catch:{ SQLiteException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01d5  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzg zzbm(java.lang.String r32) {
        /*
            r31 = this;
            r1 = r32
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r32)
            r31.zzaf()
            r31.zzcl()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r31.getWritableDatabase()     // Catch:{ SQLiteException -> 0x01b7, all -> 0x01b2 }
            java.lang.String r4 = "apps"
            java.lang.String r5 = "app_instance_id"
            java.lang.String r6 = "gmp_app_id"
            java.lang.String r7 = "resettable_device_id_hash"
            java.lang.String r8 = "last_bundle_index"
            java.lang.String r9 = "last_bundle_start_timestamp"
            java.lang.String r10 = "last_bundle_end_timestamp"
            java.lang.String r11 = "app_version"
            java.lang.String r12 = "app_store"
            java.lang.String r13 = "gmp_version"
            java.lang.String r14 = "dev_cert_hash"
            java.lang.String r15 = "measurement_enabled"
            java.lang.String r16 = "day"
            java.lang.String r17 = "daily_public_events_count"
            java.lang.String r18 = "daily_events_count"
            java.lang.String r19 = "daily_conversions_count"
            java.lang.String r20 = "config_fetched_time"
            java.lang.String r21 = "failed_config_fetch_time"
            java.lang.String r22 = "app_version_int"
            java.lang.String r23 = "firebase_instance_id"
            java.lang.String r24 = "daily_error_events_count"
            java.lang.String r25 = "daily_realtime_events_count"
            java.lang.String r26 = "health_monitor_sample"
            java.lang.String r27 = "android_id"
            java.lang.String r28 = "adid_reporting_enabled"
            java.lang.String r29 = "ssaid_reporting_enabled"
            java.lang.String r30 = "admob_app_id"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30}     // Catch:{ SQLiteException -> 0x01b7, all -> 0x01b2 }
            java.lang.String r6 = "app_id=?"
            r0 = 1
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x01b7, all -> 0x01b2 }
            r11 = 0
            r7[r11] = r1     // Catch:{ SQLiteException -> 0x01b7, all -> 0x01b2 }
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x01b7, all -> 0x01b2 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x01ae, all -> 0x01aa }
            if (r4 != 0) goto L_0x0065
            if (r3 == 0) goto L_0x0064
            r3.close()
        L_0x0064:
            return r2
        L_0x0065:
            com.google.android.gms.measurement.internal.zzg r4 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ SQLiteException -> 0x01ae, all -> 0x01aa }
            r5 = r31
            com.google.android.gms.measurement.internal.zzfn r6 = r5.zzamx     // Catch:{ SQLiteException -> 0x01a8 }
            com.google.android.gms.measurement.internal.zzbw r6 = r6.zzmh()     // Catch:{ SQLiteException -> 0x01a8 }
            r4.<init>(r6, r1)     // Catch:{ SQLiteException -> 0x01a8 }
            java.lang.String r6 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzaj(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            java.lang.String r6 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzak(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 2
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzam(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 3
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzt(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 4
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzo(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 5
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzp(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 6
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.setAppVersion(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 7
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzao(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 8
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzr(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 9
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzs(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 10
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            if (r7 != 0) goto L_0x00d3
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            if (r6 == 0) goto L_0x00d1
            goto L_0x00d3
        L_0x00d1:
            r6 = 0
            goto L_0x00d4
        L_0x00d3:
            r6 = 1
        L_0x00d4:
            r4.setMeasurementEnabled(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 11
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzw(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 12
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzx(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 13
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzy(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 14
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzz(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 15
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzu(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 16
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzv(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 17
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            if (r7 == 0) goto L_0x0119
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x011e
        L_0x0119:
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x01a8 }
        L_0x011e:
            r4.zzq(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 18
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzan(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 19
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzab(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 20
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzaa(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 21
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzap(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 22
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            if (r7 == 0) goto L_0x0150
            r6 = 0
            goto L_0x0154
        L_0x0150:
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01a8 }
        L_0x0154:
            r4.zzac(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 23
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            if (r7 != 0) goto L_0x0168
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            if (r6 == 0) goto L_0x0166
            goto L_0x0168
        L_0x0166:
            r6 = 0
            goto L_0x0169
        L_0x0168:
            r6 = 1
        L_0x0169:
            r4.zze(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            r6 = 24
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            if (r7 != 0) goto L_0x017c
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01a8 }
            if (r6 == 0) goto L_0x017b
            goto L_0x017c
        L_0x017b:
            r0 = 0
        L_0x017c:
            r4.zzf(r0)     // Catch:{ SQLiteException -> 0x01a8 }
            r0 = 25
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzal(r0)     // Catch:{ SQLiteException -> 0x01a8 }
            r4.zzha()     // Catch:{ SQLiteException -> 0x01a8 }
            boolean r0 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x01a8 }
            if (r0 == 0) goto L_0x01a2
            com.google.android.gms.measurement.internal.zzas r0 = r31.zzgt()     // Catch:{ SQLiteException -> 0x01a8 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ SQLiteException -> 0x01a8 }
            java.lang.String r6 = "Got multiple records for app, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzas.zzbw(r32)     // Catch:{ SQLiteException -> 0x01a8 }
            r0.zzg(r6, r7)     // Catch:{ SQLiteException -> 0x01a8 }
        L_0x01a2:
            if (r3 == 0) goto L_0x01a7
            r3.close()
        L_0x01a7:
            return r4
        L_0x01a8:
            r0 = move-exception
            goto L_0x01bb
        L_0x01aa:
            r0 = move-exception
            r5 = r31
            goto L_0x01d3
        L_0x01ae:
            r0 = move-exception
            r5 = r31
            goto L_0x01bb
        L_0x01b2:
            r0 = move-exception
            r5 = r31
            r3 = r2
            goto L_0x01d3
        L_0x01b7:
            r0 = move-exception
            r5 = r31
            r3 = r2
        L_0x01bb:
            com.google.android.gms.measurement.internal.zzas r4 = r31.zzgt()     // Catch:{ all -> 0x01d2 }
            com.google.android.gms.measurement.internal.zzau r4 = r4.zzjg()     // Catch:{ all -> 0x01d2 }
            java.lang.String r6 = "Error querying app. appId"
            java.lang.Object r1 = com.google.android.gms.measurement.internal.zzas.zzbw(r32)     // Catch:{ all -> 0x01d2 }
            r4.zze(r6, r1, r0)     // Catch:{ all -> 0x01d2 }
            if (r3 == 0) goto L_0x01d1
            r3.close()
        L_0x01d1:
            return r2
        L_0x01d2:
            r0 = move-exception
        L_0x01d3:
            if (r3 == 0) goto L_0x01d8
            r3.close()
        L_0x01d8:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzbm(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    @WorkerThread
    public final void zza(zzg zzg) {
        Preconditions.checkNotNull(zzg);
        zzaf();
        zzcl();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzg.zzal());
        contentValues.put("app_instance_id", zzg.getAppInstanceId());
        contentValues.put("gmp_app_id", zzg.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzg.zzhc());
        contentValues.put("last_bundle_index", Long.valueOf(zzg.zzhj()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzg.zzhd()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzg.zzhe()));
        contentValues.put("app_version", zzg.zzak());
        contentValues.put("app_store", zzg.zzhg());
        contentValues.put("gmp_version", Long.valueOf(zzg.zzhh()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzg.zzhi()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzg.isMeasurementEnabled()));
        contentValues.put("day", Long.valueOf(zzg.zzhn()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzg.zzho()));
        contentValues.put("daily_events_count", Long.valueOf(zzg.zzhp()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzg.zzhq()));
        contentValues.put("config_fetched_time", Long.valueOf(zzg.zzhk()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzg.zzhl()));
        contentValues.put("app_version_int", Long.valueOf(zzg.zzhf()));
        contentValues.put("firebase_instance_id", zzg.getFirebaseInstanceId());
        contentValues.put("daily_error_events_count", Long.valueOf(zzg.zzhs()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzg.zzhr()));
        contentValues.put("health_monitor_sample", zzg.zzht());
        contentValues.put("android_id", Long.valueOf(zzg.zzhv()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzg.zzhw()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzg.zzhx()));
        contentValues.put("admob_app_id", zzg.zzhb());
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzg.zzal()})) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzgt().zzjg().zzg("Failed to insert/update app (got -1). appId", zzas.zzbw(zzg.zzal()));
            }
        } catch (SQLiteException e) {
            zzgt().zzjg().zze("Error storing app. appId", zzas.zzbw(zzg.zzal()), e);
        }
    }

    public final long zzbn(String str) {
        Preconditions.checkNotEmpty(str);
        zzaf();
        zzcl();
        try {
            return (long) getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzgv().zzb(str, zzai.zzajj))))});
        } catch (SQLiteException e) {
            zzgt().zzjg().zze("Error deleting over the limit events. appId", zzas.zzbw(str), e);
            return 0;
        }
    }

    @WorkerThread
    public final zzu zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzaf();
        zzcl();
        String[] strArr = {str};
        zzu zzu = new zzu();
        Cursor cursor = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            SQLiteDatabase sQLiteDatabase = writableDatabase;
            cursor = sQLiteDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            if (!cursor.moveToFirst()) {
                zzgt().zzjj().zzg("Not updating daily counts, app is not known. appId", zzas.zzbw(str));
                if (cursor != null) {
                    cursor.close();
                }
                return zzu;
            }
            if (cursor.getLong(0) == j) {
                zzu.zzahi = cursor.getLong(1);
                zzu.zzahh = cursor.getLong(2);
                zzu.zzahj = cursor.getLong(3);
                zzu.zzahk = cursor.getLong(4);
                zzu.zzahl = cursor.getLong(5);
            }
            if (z) {
                zzu.zzahi++;
            }
            if (z2) {
                zzu.zzahh++;
            }
            if (z3) {
                zzu.zzahj++;
            }
            if (z4) {
                zzu.zzahk++;
            }
            if (z5) {
                zzu.zzahl++;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j));
            contentValues.put("daily_public_events_count", Long.valueOf(zzu.zzahh));
            contentValues.put("daily_events_count", Long.valueOf(zzu.zzahi));
            contentValues.put("daily_conversions_count", Long.valueOf(zzu.zzahj));
            contentValues.put("daily_error_events_count", Long.valueOf(zzu.zzahk));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzu.zzahl));
            writableDatabase.update("apps", contentValues, "app_id=?", strArr);
            if (cursor != null) {
                cursor.close();
            }
            return zzu;
        } catch (SQLiteException e) {
            zzgt().zzjg().zze("Error updating daily counts. appId", zzas.zzbw(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return zzu;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0073  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzbo(java.lang.String r11) {
        /*
            r10 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            r10.zzaf()
            r10.zzcl()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            java.lang.String r2 = "apps"
            java.lang.String r3 = "remote_config"
            java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            java.lang.String r4 = "app_id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            r9 = 0
            r5[r9] = r11     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0057, all -> 0x0054 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0052 }
            if (r2 != 0) goto L_0x0031
            if (r1 == 0) goto L_0x0030
            r1.close()
        L_0x0030:
            return r0
        L_0x0031:
            byte[] r2 = r1.getBlob(r9)     // Catch:{ SQLiteException -> 0x0052 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0052 }
            if (r3 == 0) goto L_0x004c
            com.google.android.gms.measurement.internal.zzas r3 = r10.zzgt()     // Catch:{ SQLiteException -> 0x0052 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ SQLiteException -> 0x0052 }
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzas.zzbw(r11)     // Catch:{ SQLiteException -> 0x0052 }
            r3.zzg(r4, r5)     // Catch:{ SQLiteException -> 0x0052 }
        L_0x004c:
            if (r1 == 0) goto L_0x0051
            r1.close()
        L_0x0051:
            return r2
        L_0x0052:
            r2 = move-exception
            goto L_0x0059
        L_0x0054:
            r11 = move-exception
            r1 = r0
            goto L_0x0071
        L_0x0057:
            r2 = move-exception
            r1 = r0
        L_0x0059:
            com.google.android.gms.measurement.internal.zzas r3 = r10.zzgt()     // Catch:{ all -> 0x0070 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ all -> 0x0070 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzas.zzbw(r11)     // Catch:{ all -> 0x0070 }
            r3.zze(r4, r11, r2)     // Catch:{ all -> 0x0070 }
            if (r1 == 0) goto L_0x006f
            r1.close()
        L_0x006f:
            return r0
        L_0x0070:
            r11 = move-exception
        L_0x0071:
            if (r1 == 0) goto L_0x0076
            r1.close()
        L_0x0076:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzbo(java.lang.String):byte[]");
    }

    @WorkerThread
    public final boolean zza(zzfw zzfw, boolean z) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzfw);
        Preconditions.checkNotEmpty(zzfw.zztt);
        Preconditions.checkNotNull(zzfw.zzaxo);
        zzij();
        long currentTimeMillis = zzbx().currentTimeMillis();
        if (zzfw.zzaxo.longValue() < currentTimeMillis - zzq.zzib() || zzfw.zzaxo.longValue() > zzq.zzib() + currentTimeMillis) {
            zzgt().zzjj().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzas.zzbw(zzfw.zztt), Long.valueOf(currentTimeMillis), zzfw.zzaxo);
        }
        try {
            byte[] bArr = new byte[zzfw.zzvx()];
            zzya zzk = zzya.zzk(bArr, 0, bArr.length);
            zzfw.zza(zzk);
            zzk.zzza();
            byte[] zzb = zzjr().zzb(bArr);
            zzgt().zzjo().zzg("Saving bundle, size", Integer.valueOf(zzb.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzfw.zztt);
            contentValues.put("bundle_end_timestamp", zzfw.zzaxo);
            contentValues.put("data", zzb);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzfw.zzayl != null) {
                contentValues.put("retry_count", zzfw.zzayl);
            }
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzgt().zzjg().zzg("Failed to insert bundle (got -1). appId", zzas.zzbw(zzfw.zztt));
                return false;
            } catch (SQLiteException e) {
                zzgt().zzjg().zze("Error storing bundle. appId", zzas.zzbw(zzfw.zztt), e);
                return false;
            }
        } catch (IOException e2) {
            zzgt().zzjg().zze("Data loss. Failed to serialize bundle. appId", zzas.zzbw(zzfw.zztt), e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzih() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r1
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x002b
        L_0x0024:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003f
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            com.google.android.gms.measurement.internal.zzas r3 = r6.zzgt()     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzg(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzih():java.lang.String");
    }

    public final boolean zzii() {
        return zza("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    @WorkerThread
    public final List<Pair<zzfw, Long>> zzb(String str, int i, int i2) {
        zzaf();
        zzcl();
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
            if (!cursor.moveToFirst()) {
                List<Pair<zzfw, Long>> emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            do {
                long j = cursor.getLong(0);
                try {
                    byte[] zza = zzjr().zza(cursor.getBlob(1));
                    if (!arrayList.isEmpty() && zza.length + i3 > i2) {
                        break;
                    }
                    zzxz zzj = zzxz.zzj(zza, 0, zza.length);
                    zzfw zzfw = new zzfw();
                    try {
                        zzfw.zza(zzj);
                        if (!cursor.isNull(2)) {
                            zzfw.zzayl = Integer.valueOf(cursor.getInt(2));
                        }
                        i3 += zza.length;
                        arrayList.add(Pair.create(zzfw, Long.valueOf(j)));
                    } catch (IOException e) {
                        zzgt().zzjg().zze("Failed to merge queued bundle. appId", zzas.zzbw(str), e);
                    }
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } catch (IOException e2) {
                    zzgt().zzjg().zze("Failed to unzip queued bundle. appId", zzas.zzbw(str), e2);
                }
            } while (i3 <= i2);
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e3) {
            zzgt().zzjg().zze("Error querying bundles. appId", zzas.zzbw(str), e3);
            List<Pair<zzfw, Long>> emptyList2 = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzij() {
        zzaf();
        zzcl();
        if (zzip()) {
            long j = zzgu().zzanf.get();
            long elapsedRealtime = zzbx().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > ((Long) zzai.zzajs.get()).longValue()) {
                zzgu().zzanf.set(elapsedRealtime);
                zzaf();
                zzcl();
                if (zzip()) {
                    int delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzbx().currentTimeMillis()), String.valueOf(zzq.zzib())});
                    if (delete > 0) {
                        zzgt().zzjo().zzg("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final void zzc(List<Long> list) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzip()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zza(sb3.toString(), (String[]) null) > 0) {
                zzgt().zzjj().zzby("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + Statements.GetOwnedFoodIdsDateDescending);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                writableDatabase.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                zzgt().zzjg().zzg("Error incrementing retry count. error", e);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zza(String str, zzfi[] zzfiArr) {
        boolean z;
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfiArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzcl();
            zzaf();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzfi zzfi : zzfiArr) {
                zzcl();
                zzaf();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzfi);
                Preconditions.checkNotNull(zzfi.zzavi);
                Preconditions.checkNotNull(zzfi.zzavh);
                if (zzfi.zzavg == null) {
                    zzgt().zzjj().zzg("Audience with no ID. appId", zzas.zzbw(str));
                } else {
                    int intValue = zzfi.zzavg.intValue();
                    zzfj[] zzfjArr = zzfi.zzavi;
                    int length = zzfjArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            zzfm[] zzfmArr = zzfi.zzavh;
                            int length2 = zzfmArr.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= length2) {
                                    zzfj[] zzfjArr2 = zzfi.zzavi;
                                    int length3 = zzfjArr2.length;
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 >= length3) {
                                            z = true;
                                            break;
                                        } else if (!zza(str, intValue, zzfjArr2[i3])) {
                                            z = false;
                                            break;
                                        } else {
                                            i3++;
                                        }
                                    }
                                    if (z) {
                                        zzfm[] zzfmArr2 = zzfi.zzavh;
                                        int length4 = zzfmArr2.length;
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 >= length4) {
                                                break;
                                            } else if (!zza(str, intValue, zzfmArr2[i4])) {
                                                z = false;
                                                break;
                                            } else {
                                                i4++;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzcl();
                                        zzaf();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase writableDatabase3 = getWritableDatabase();
                                        writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                        writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                    }
                                } else if (zzfmArr[i2].zzavm == null) {
                                    zzgt().zzjj().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzas.zzbw(str), zzfi.zzavg);
                                    break;
                                } else {
                                    i2++;
                                }
                            }
                        } else if (zzfjArr[i].zzavm == null) {
                            zzgt().zzjj().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzas.zzbw(str), zzfi.zzavg);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzfi zzfi2 : zzfiArr) {
                arrayList.add(zzfi2.zzavg);
            }
            zza(str, (List<Integer>) arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzfj zzfj) {
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfj);
        if (TextUtils.isEmpty(zzfj.zzavn)) {
            zzgt().zzjj().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzas.zzbw(str), Integer.valueOf(i), String.valueOf(zzfj.zzavm));
            return false;
        }
        try {
            byte[] bArr = new byte[zzfj.zzvx()];
            zzya zzk = zzya.zzk(bArr, 0, bArr.length);
            zzfj.zza(zzk);
            zzk.zzza();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzfj.zzavm);
            contentValues.put("event_name", zzfj.zzavn);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzgt().zzjg().zzg("Failed to insert event filter (got -1). appId", zzas.zzbw(str));
                }
                return true;
            } catch (SQLiteException e) {
                zzgt().zzjg().zze("Error storing event filter. appId", zzas.zzbw(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgt().zzjg().zze("Configuration loss. Failed to serialize event filter. appId", zzas.zzbw(str), e2);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzfm zzfm) {
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfm);
        if (TextUtils.isEmpty(zzfm.zzawc)) {
            zzgt().zzjj().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzas.zzbw(str), Integer.valueOf(i), String.valueOf(zzfm.zzavm));
            return false;
        }
        try {
            byte[] bArr = new byte[zzfm.zzvx()];
            zzya zzk = zzya.zzk(bArr, 0, bArr.length);
            zzfm.zza(zzk);
            zzk.zzza();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzfm.zzavm);
            contentValues.put("property_name", zzfm.zzawc);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzgt().zzjg().zzg("Failed to insert property filter (got -1). appId", zzas.zzbw(str));
                return false;
            } catch (SQLiteException e) {
                zzgt().zzjg().zze("Error storing property filter. appId", zzas.zzbw(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgt().zzjg().zze("Configuration loss. Failed to serialize property filter. appId", zzas.zzbw(str), e2);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzfj>> zzl(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzcl()
            r12.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "event_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            java.lang.String r4 = "app_id=? AND event_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            r10 = 0
            r5[r10] = r13     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            r11 = 1
            r5[r11] = r14     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0091 }
            if (r1 != 0) goto L_0x0042
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0091 }
            if (r14 == 0) goto L_0x0041
            r14.close()
        L_0x0041:
            return r13
        L_0x0042:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0091 }
            int r2 = r1.length     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.measurement.zzxz r1 = com.google.android.gms.internal.measurement.zzxz.zzj(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.measurement.zzfj r2 = new com.google.android.gms.internal.measurement.zzfj     // Catch:{ SQLiteException -> 0x0091 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0091 }
            r2.zza(r1)     // Catch:{ IOException -> 0x0073 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0091 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0091 }
            if (r3 != 0) goto L_0x006f
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0091 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0091 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0091 }
        L_0x006f:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0091 }
            goto L_0x0085
        L_0x0073:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzas r2 = r12.zzgt()     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjg()     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzas.zzbw(r13)     // Catch:{ SQLiteException -> 0x0091 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0091 }
        L_0x0085:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0091 }
            if (r1 != 0) goto L_0x0042
            if (r14 == 0) goto L_0x0090
            r14.close()
        L_0x0090:
            return r0
        L_0x0091:
            r0 = move-exception
            goto L_0x0098
        L_0x0093:
            r13 = move-exception
            r14 = r9
            goto L_0x00b0
        L_0x0096:
            r0 = move-exception
            r14 = r9
        L_0x0098:
            com.google.android.gms.measurement.internal.zzas r1 = r12.zzgt()     // Catch:{ all -> 0x00af }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ all -> 0x00af }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzas.zzbw(r13)     // Catch:{ all -> 0x00af }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00af }
            if (r14 == 0) goto L_0x00ae
            r14.close()
        L_0x00ae:
            return r9
        L_0x00af:
            r13 = move-exception
        L_0x00b0:
            if (r14 == 0) goto L_0x00b5
            r14.close()
        L_0x00b5:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzl(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzfm>> zzm(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzcl()
            r12.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "property_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            java.lang.String r4 = "app_id=? AND property_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            r10 = 0
            r5[r10] = r13     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            r11 = 1
            r5[r11] = r14     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0091 }
            if (r1 != 0) goto L_0x0042
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0091 }
            if (r14 == 0) goto L_0x0041
            r14.close()
        L_0x0041:
            return r13
        L_0x0042:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0091 }
            int r2 = r1.length     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.measurement.zzxz r1 = com.google.android.gms.internal.measurement.zzxz.zzj(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.measurement.zzfm r2 = new com.google.android.gms.internal.measurement.zzfm     // Catch:{ SQLiteException -> 0x0091 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0091 }
            r2.zza(r1)     // Catch:{ IOException -> 0x0073 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0091 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0091 }
            if (r3 != 0) goto L_0x006f
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0091 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0091 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0091 }
        L_0x006f:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0091 }
            goto L_0x0085
        L_0x0073:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzas r2 = r12.zzgt()     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjg()     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzas.zzbw(r13)     // Catch:{ SQLiteException -> 0x0091 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0091 }
        L_0x0085:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0091 }
            if (r1 != 0) goto L_0x0042
            if (r14 == 0) goto L_0x0090
            r14.close()
        L_0x0090:
            return r0
        L_0x0091:
            r0 = move-exception
            goto L_0x0098
        L_0x0093:
            r13 = move-exception
            r14 = r9
            goto L_0x00b0
        L_0x0096:
            r0 = move-exception
            r14 = r9
        L_0x0098:
            com.google.android.gms.measurement.internal.zzas r1 = r12.zzgt()     // Catch:{ all -> 0x00af }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ all -> 0x00af }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzas.zzbw(r13)     // Catch:{ all -> 0x00af }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00af }
            if (r14 == 0) goto L_0x00ae
            r14.close()
        L_0x00ae:
            return r9
        L_0x00af:
            r13 = move-exception
        L_0x00b0:
            if (r14 == 0) goto L_0x00b5
            r14.close()
        L_0x00b5:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzm(java.lang.String, java.lang.String):java.util.Map");
    }

    private final boolean zza(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzcl();
        zzaf();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zza = zza("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zzgv().zzb(str, zzai.zzajz)));
            if (zza <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = (Integer) list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 140);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzgt().zzjg().zze("Database error querying filters. appId", zzas.zzbw(str), e);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzfx> zzbp(java.lang.String r12) {
        /*
            r11 = this;
            r11.zzcl()
            r11.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            java.lang.String r2 = "audience_id"
            java.lang.String r3 = "current_results"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch:{ SQLiteException -> 0x007c, all -> 0x0079 }
            java.lang.String r3 = "app_id=?"
            r9 = 1
            java.lang.String[] r4 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x007c, all -> 0x0079 }
            r10 = 0
            r4[r10] = r12     // Catch:{ SQLiteException -> 0x007c, all -> 0x0079 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x007c, all -> 0x0079 }
            boolean r1 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0077 }
            if (r1 != 0) goto L_0x0033
            if (r0 == 0) goto L_0x0032
            r0.close()
        L_0x0032:
            return r8
        L_0x0033:
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap     // Catch:{ SQLiteException -> 0x0077 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x0077 }
        L_0x0038:
            int r2 = r0.getInt(r10)     // Catch:{ SQLiteException -> 0x0077 }
            byte[] r3 = r0.getBlob(r9)     // Catch:{ SQLiteException -> 0x0077 }
            int r4 = r3.length     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.internal.measurement.zzxz r3 = com.google.android.gms.internal.measurement.zzxz.zzj(r3, r10, r4)     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.internal.measurement.zzfx r4 = new com.google.android.gms.internal.measurement.zzfx     // Catch:{ SQLiteException -> 0x0077 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0077 }
            r4.zza(r3)     // Catch:{ IOException -> 0x0055 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0077 }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x0077 }
            goto L_0x006b
        L_0x0055:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzas r4 = r11.zzgt()     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.measurement.internal.zzau r4 = r4.zzjg()     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzas.zzbw(r12)     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0077 }
            r4.zzd(r5, r6, r2, r3)     // Catch:{ SQLiteException -> 0x0077 }
        L_0x006b:
            boolean r2 = r0.moveToNext()     // Catch:{ SQLiteException -> 0x0077 }
            if (r2 != 0) goto L_0x0038
            if (r0 == 0) goto L_0x0076
            r0.close()
        L_0x0076:
            return r1
        L_0x0077:
            r1 = move-exception
            goto L_0x007e
        L_0x0079:
            r12 = move-exception
            r0 = r8
            goto L_0x0096
        L_0x007c:
            r1 = move-exception
            r0 = r8
        L_0x007e:
            com.google.android.gms.measurement.internal.zzas r2 = r11.zzgt()     // Catch:{ all -> 0x0095 }
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjg()     // Catch:{ all -> 0x0095 }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzas.zzbw(r12)     // Catch:{ all -> 0x0095 }
            r2.zze(r3, r12, r1)     // Catch:{ all -> 0x0095 }
            if (r0 == 0) goto L_0x0094
            r0.close()
        L_0x0094:
            return r8
        L_0x0095:
            r12 = move-exception
        L_0x0096:
            if (r0 == 0) goto L_0x009b
            r0.close()
        L_0x009b:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzbp(java.lang.String):java.util.Map");
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzgt().zzjg().zzby("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzgt().zzjg().zzby("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzgt().zzjg().zzg("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    public final long zzik() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final long zzn(String str, String str2) {
        long j;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
            sb.append("select ");
            sb.append(str2);
            sb.append(" from app2 where app_id=?");
            j = zza(sb.toString(), new String[]{str}, -1);
            if (j == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", Integer.valueOf(0));
                contentValues.put("previous_install_count", Integer.valueOf(0));
                if (writableDatabase.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                    zzgt().zzjg().zze("Failed to insert column (got -1). appId", zzas.zzbw(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                j = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + j));
                if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzgt().zzjg().zze("Failed to update column (got 0). appId", zzas.zzbw(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return j;
            } catch (SQLiteException e) {
                e = e;
                try {
                    zzgt().zzjg().zzd("Error inserting column. appId", zzas.zzbw(str), str2, e);
                    return j;
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e2) {
            e = e2;
            j = 0;
            zzgt().zzjg().zzd("Error inserting column. appId", zzas.zzbw(str), str2, e);
            return j;
        }
    }

    @WorkerThread
    public final long zzil() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final long zza(zzfw zzfw) throws IOException {
        long j;
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzfw);
        Preconditions.checkNotEmpty(zzfw.zztt);
        try {
            byte[] bArr = new byte[zzfw.zzvx()];
            zzya zzk = zzya.zzk(bArr, 0, bArr.length);
            zzfw.zza(zzk);
            zzk.zzza();
            zzft zzjr = zzjr();
            Preconditions.checkNotNull(bArr);
            zzjr.zzgr().zzaf();
            MessageDigest messageDigest = zzfx.getMessageDigest();
            if (messageDigest == null) {
                zzjr.zzgt().zzjg().zzby("Failed to get MD5");
                j = 0;
            } else {
                j = zzfx.zzc(messageDigest.digest(bArr));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzfw.zztt);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put(TtmlNode.TAG_METADATA, bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return j;
            } catch (SQLiteException e) {
                zzgt().zzjg().zze("Error storing raw event metadata. appId", zzas.zzbw(zzfw.zztt), e);
                throw e;
            }
        } catch (IOException e2) {
            zzgt().zzjg().zze("Data loss. Failed to serialize event metadata. appId", zzas.zzbw(zzfw.zztt), e2);
            throw e2;
        }
    }

    public final boolean zzim() {
        return zza("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzin() {
        return zza("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzbq(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzad(long r5) {
        /*
            r4 = this;
            r4.zzaf()
            r4.zzcl()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003e }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.measurement.internal.zzas r6 = r4.zzgt()     // Catch:{ SQLiteException -> 0x003e }
            com.google.android.gms.measurement.internal.zzau r6 = r6.zzjo()     // Catch:{ SQLiteException -> 0x003e }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.zzby(r1)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x0033
            r5.close()
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.String r6 = r5.getString(r6)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x003d
            r5.close()
        L_0x003d:
            return r6
        L_0x003e:
            r6 = move-exception
            goto L_0x0045
        L_0x0040:
            r6 = move-exception
            r5 = r0
            goto L_0x0059
        L_0x0043:
            r6 = move-exception
            r5 = r0
        L_0x0045:
            com.google.android.gms.measurement.internal.zzas r1 = r4.zzgt()     // Catch:{ all -> 0x0058 }
            com.google.android.gms.measurement.internal.zzau r1 = r1.zzjg()     // Catch:{ all -> 0x0058 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zzg(r2, r6)     // Catch:{ all -> 0x0058 }
            if (r5 == 0) goto L_0x0057
            r5.close()
        L_0x0057:
            return r0
        L_0x0058:
            r6 = move-exception
        L_0x0059:
            if (r5 == 0) goto L_0x005e
            r5.close()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zzad(long):java.lang.String");
    }

    public final long zzio() {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return -1;
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzgt().zzjg().zzg("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzft, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzaf()
            r7.zzcl()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            r4 = 0
            r3[r4] = r8     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            r6 = 1
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0072 }
            if (r2 != 0) goto L_0x0037
            com.google.android.gms.measurement.internal.zzas r8 = r7.zzgt()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.measurement.internal.zzau r8 = r8.zzjo()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r9 = "Main event not found"
            r8.zzby(r9)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r4)     // Catch:{ SQLiteException -> 0x0072 }
            long r5 = r1.getLong(r6)     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x0072 }
            int r5 = r2.length     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzxz r2 = com.google.android.gms.internal.measurement.zzxz.zzj(r2, r4, r5)     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzft r4 = new com.google.android.gms.internal.measurement.zzft     // Catch:{ SQLiteException -> 0x0072 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0072 }
            r4.zza(r2)     // Catch:{ IOException -> 0x005a }
            android.util.Pair r8 = android.util.Pair.create(r4, r3)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0059
            r1.close()
        L_0x0059:
            return r8
        L_0x005a:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzas r3 = r7.zzgt()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.measurement.internal.zzau r3 = r3.zzjg()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzas.zzbw(r8)     // Catch:{ SQLiteException -> 0x0072 }
            r3.zzd(r4, r8, r9, r2)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0071
            r1.close()
        L_0x0071:
            return r0
        L_0x0072:
            r8 = move-exception
            goto L_0x0079
        L_0x0074:
            r8 = move-exception
            r1 = r0
            goto L_0x008d
        L_0x0077:
            r8 = move-exception
            r1 = r0
        L_0x0079:
            com.google.android.gms.measurement.internal.zzas r9 = r7.zzgt()     // Catch:{ all -> 0x008c }
            com.google.android.gms.measurement.internal.zzau r9 = r9.zzjg()     // Catch:{ all -> 0x008c }
            java.lang.String r2 = "Error selecting main event"
            r9.zzg(r2, r8)     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x008b
            r1.close()
        L_0x008b:
            return r0
        L_0x008c:
            r8 = move-exception
        L_0x008d:
            if (r1 == 0) goto L_0x0092
            r1.close()
        L_0x0092:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    public final boolean zza(String str, Long l, long j, zzft zzft) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzft);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        try {
            byte[] bArr = new byte[zzft.zzvx()];
            zzya zzk = zzya.zzk(bArr, 0, bArr.length);
            zzft.zza(zzk);
            zzk.zzza();
            zzgt().zzjo().zze("Saving complex main event, appId, data size", zzgq().zzbt(str), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put(Extras.EVENT_ID, l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                    return true;
                }
                zzgt().zzjg().zzg("Failed to insert complex main event (got -1). appId", zzas.zzbw(str));
                return false;
            } catch (SQLiteException e) {
                zzgt().zzjg().zze("Error storing complex main event. appId", zzas.zzbw(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgt().zzjg().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzas.zzbw(str), l, e2);
            return false;
        }
    }

    public final boolean zza(zzab zzab, long j, boolean z) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzab);
        Preconditions.checkNotEmpty(zzab.zztt);
        zzft zzft = new zzft();
        zzft.zzaxe = Long.valueOf(zzab.zzaht);
        zzft.zzaxc = new zzfu[zzab.zzahu.size()];
        Iterator it = zzab.zzahu.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            zzfu zzfu = new zzfu();
            int i2 = i + 1;
            zzft.zzaxc[i] = zzfu;
            zzfu.name = str;
            zzjr().zza(zzfu, zzab.zzahu.get(str));
            i = i2;
        }
        try {
            byte[] bArr = new byte[zzft.zzvx()];
            zzya zzk = zzya.zzk(bArr, 0, bArr.length);
            zzft.zza(zzk);
            zzk.zzza();
            zzgt().zzjo().zze("Saving event, name, data size", zzgq().zzbt(zzab.name), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzab.zztt);
            contentValues.put("name", zzab.name);
            contentValues.put("timestamp", Long.valueOf(zzab.timestamp));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                zzgt().zzjg().zzg("Failed to insert raw event (got -1). appId", zzas.zzbw(zzab.zztt));
                return false;
            } catch (SQLiteException e) {
                zzgt().zzjg().zze("Error storing raw event. appId", zzas.zzbw(zzab.zztt), e);
                return false;
            }
        } catch (IOException e2) {
            zzgt().zzjg().zze("Data loss. Failed to serialize event params/data. appId", zzas.zzbw(zzab.zztt), e2);
            return false;
        }
    }

    private final boolean zzip() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }
}
