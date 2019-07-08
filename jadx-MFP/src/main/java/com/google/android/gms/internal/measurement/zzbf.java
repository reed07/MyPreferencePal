package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbf extends zzau implements Closeable {
    /* access modifiers changed from: private */
    public static final String zzxf = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    private static final String zzxg = String.format("SELECT MAX(%s) FROM %s WHERE 1;", new Object[]{"hit_time", "hits2"});
    private final zzbg zzxh;
    private final zzdc zzxi = new zzdc(zzbx());
    /* access modifiers changed from: private */
    public final zzdc zzxj = new zzdc(zzbx());

    zzbf(zzaw zzaw) {
        super(zzaw);
        this.zzxh = new zzbg(this, zzaw.getContext(), "google_analytics_v4.db");
    }

    /* access modifiers changed from: private */
    public static String zzdd() {
        return "google_analytics_v4.db";
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
    }

    public final void beginTransaction() {
        zzcl();
        getWritableDatabase().beginTransaction();
    }

    public final void setTransactionSuccessful() {
        zzcl();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final void endTransaction() {
        zzcl();
        getWritableDatabase().endTransaction();
    }

    public final void zzc(zzck zzck) {
        String str;
        Preconditions.checkNotNull(zzck);
        zzk.zzaf();
        zzcl();
        Preconditions.checkNotNull(zzck);
        Builder builder = new Builder();
        for (Entry entry : zzck.zzcw().entrySet()) {
            String str2 = (String) entry.getKey();
            if (!"ht".equals(str2) && !"qt".equals(str2) && !"AppUID".equals(str2)) {
                builder.appendQueryParameter(str2, (String) entry.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        if (encodedQuery == null) {
            encodedQuery = "";
        }
        if (encodedQuery.length() > 8192) {
            zzby().zza(zzck, "Hit length exceeds the maximum allowed size");
            return;
        }
        int intValue = ((Integer) zzcf.zzza.get()).intValue();
        long zzcv = zzcv();
        if (zzcv > ((long) (intValue - 1))) {
            List zzc = zzc((zzcv - ((long) intValue)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzc.size()));
            zza(zzc);
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", encodedQuery);
        contentValues.put("hit_time", Long.valueOf(zzck.zzer()));
        contentValues.put("hit_app_id", Integer.valueOf(zzck.zzep()));
        String str3 = "hit_url";
        if (zzck.zzet()) {
            str = zzbx.zzed();
        } else {
            str = zzbx.zzee();
        }
        contentValues.put(str3, str);
        try {
            long insert = writableDatabase.insert("hits2", null, contentValues);
            if (insert == -1) {
                zzu("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), zzck);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    private final long zzcv() {
        zzk.zzaf();
        zzcl();
        return zza("SELECT COUNT(*) FROM hits2", null);
    }

    /* access modifiers changed from: 0000 */
    public final boolean isEmpty() {
        return zzcv() == 0;
    }

    private final List<Long> zzc(long j) {
        zzk.zzaf();
        zzcl();
        if (j <= 0) {
            return Collections.emptyList();
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor query = writableDatabase.query("hits2", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", new Object[]{"hit_id"}), Long.toString(j));
            if (query.moveToFirst()) {
                do {
                    arrayList.add(Long.valueOf(query.getLong(0)));
                } while (query.moveToNext());
            }
            if (query != null) {
                query.close();
            }
        } catch (SQLiteException e) {
            zzd("Error selecting hit ids", e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }

    public final List<zzck> zzd(long j) {
        Preconditions.checkArgument(j >= 0);
        zzk.zzaf();
        zzcl();
        Cursor cursor = null;
        try {
            Cursor query = getWritableDatabase().query("hits2", new String[]{"hit_id", "hit_time", "hit_string", "hit_url", "hit_app_id"}, null, null, null, null, String.format("%s ASC", new Object[]{"hit_id"}), Long.toString(j));
            ArrayList arrayList = new ArrayList();
            if (query.moveToFirst()) {
                do {
                    long j2 = query.getLong(0);
                    zzck zzck = new zzck(this, zzv(query.getString(2)), query.getLong(1), zzdg.zzai(query.getString(3)), j2, query.getInt(4));
                    arrayList.add(zzck);
                } while (query.moveToNext());
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zze("Error loading hits from the database", e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void zza(List<Long> list) {
        Preconditions.checkNotNull(list);
        zzk.zzaf();
        zzcl();
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder("hit_id");
            sb.append(" in (");
            for (int i = 0; i < list.size(); i++) {
                Long l = (Long) list.get(i);
                if (l == null || l.longValue() == 0) {
                    throw new SQLiteException("Invalid hit id");
                }
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(l);
            }
            sb.append(")");
            String sb2 = sb.toString();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                int delete = writableDatabase.delete("hits2", sb2, null);
                if (delete != list.size()) {
                    zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(delete), sb2);
                }
            } catch (SQLiteException e) {
                zze("Error deleting hits", e);
                throw e;
            }
        }
    }

    public final void zze(long j) {
        zzk.zzaf();
        zzcl();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zza((List<Long>) arrayList);
    }

    public final int zzdb() {
        zzk.zzaf();
        zzcl();
        if (!this.zzxi.zzj(86400000)) {
            return 0;
        }
        this.zzxi.start();
        zzq("Deleting stale hits (if any)");
        int delete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zzbx().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public final long zzdc() {
        zzk.zzaf();
        zzcl();
        return zza(zzxg, (String[]) null, 0);
    }

    public final long zza(long j, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzcl();
        zzk.zzaf();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    public final List<zzaz> zzf(long j) {
        zzcl();
        zzk.zzaf();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = null;
        try {
            String[] strArr = {"cid", "tid", "adid", "hits_count", NativeProtocol.WEB_DIALOG_PARAMS};
            int intValue = ((Integer) zzcf.zzzc.get()).intValue();
            Cursor query = writableDatabase.query("properties", strArr, "app_uid=?", new String[]{"0"}, null, null, null, String.valueOf(intValue));
            ArrayList arrayList = new ArrayList();
            if (query.moveToFirst()) {
                do {
                    String string = query.getString(0);
                    String string2 = query.getString(1);
                    boolean z = query.getInt(2) != 0;
                    long j2 = (long) query.getInt(3);
                    Map zzw = zzw(query.getString(4));
                    if (!TextUtils.isEmpty(string)) {
                        if (!TextUtils.isEmpty(string2)) {
                            zzaz zzaz = new zzaz(0, string, string2, z, j2, zzw);
                            arrayList.add(zzaz);
                        }
                    }
                    zzc("Read property with empty client id or tracker id", string, string2);
                } while (query.moveToNext());
            }
            if (arrayList.size() >= intValue) {
                zzt("Sending hits to too many properties. Campaign report might be incorrect");
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zze("Error loading hits from the database", e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void close() {
        try {
            this.zzxh.close();
        } catch (SQLiteException e) {
            zze("Sql error closing database", e);
        } catch (IllegalStateException e2) {
            zze("Error closing database", e2);
        }
    }

    private final long zza(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, null);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

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
            return 0;
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @VisibleForTesting
    private final Map<String, String> zzv(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String str2 = "?";
                String valueOf = String.valueOf(str);
                str = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
            }
            return HttpUtils.parse(new URI(str), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    @VisibleForTesting
    private final Map<String, String> zzw(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        String str2 = "?";
        try {
            String valueOf = String.valueOf(str);
            return HttpUtils.parse(new URI(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2)), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzxh.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }
}
