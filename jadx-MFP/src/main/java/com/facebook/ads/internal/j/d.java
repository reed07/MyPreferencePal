package com.facebook.ads.internal.j;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.w.b.p;
import com.facebook.ads.internal.w.h.b;
import com.mopub.common.Constants;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class d {
    private static final String a;
    private static final ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    private static final Lock c = b.readLock();
    /* access modifiers changed from: private */
    public static final Lock d = b.writeLock();
    /* access modifiers changed from: private */
    public final Context e;
    /* access modifiers changed from: private */
    public final h f = new h(this);
    /* access modifiers changed from: private */
    public final c g = new c(this);
    private SQLiteOpenHelper h;

    private static class a<T> extends AsyncTask<Void, Void, T> {
        private final f<T> a;
        private final a<T> b;
        private final Context c;
        private com.facebook.ads.internal.j.f.a d;

        a(Context context, f<T> fVar, a<T> aVar) {
            this.a = fVar;
            this.b = aVar;
            this.c = context;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public T doInBackground(Void... voidArr) {
            T t = null;
            try {
                t = this.a.b();
                this.d = this.a.c();
                return t;
            } catch (Exception e) {
                com.facebook.ads.internal.w.h.a.b(this.c, "database", b.x, e);
                this.d = com.facebook.ads.internal.j.f.a.UNKNOWN;
                return t;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(T t) {
            com.facebook.ads.internal.j.f.a aVar = this.d;
            if (aVar == null) {
                this.b.a(t);
            } else {
                this.b.a(aVar.a(), this.d.b());
            }
            this.b.a();
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT tokens.");
        sb.append(h.a.b);
        sb.append(", ");
        sb.append("tokens");
        sb.append(".");
        sb.append(h.b.b);
        sb.append(", ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.a.b);
        sb.append(", ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.c.b);
        sb.append(", ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.d.b);
        sb.append(", ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.e.b);
        sb.append(", ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.f.b);
        sb.append(", ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.g.b);
        sb.append(", ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.h.b);
        sb.append(", ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.i.b);
        sb.append(" FROM ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(" JOIN ");
        sb.append("tokens");
        sb.append(" ON ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.b.b);
        sb.append(" = ");
        sb.append("tokens");
        sb.append(".");
        sb.append(h.a.b);
        sb.append(" ORDER BY ");
        sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
        sb.append(".");
        sb.append(c.e.b);
        sb.append(" ASC");
        a = sb.toString();
    }

    public d(Context context) {
        this.e = context;
    }

    private synchronized SQLiteDatabase j() {
        if (this.h == null) {
            this.h = new e(this.e, this);
        }
        return this.h.getWritableDatabase();
    }

    @WorkerThread
    public Cursor a(int i) {
        c.lock();
        try {
            SQLiteDatabase a2 = a();
            StringBuilder sb = new StringBuilder();
            sb.append(a);
            sb.append(" LIMIT ");
            sb.append(String.valueOf(i));
            Cursor rawQuery = a2.rawQuery(sb.toString(), null);
            return rawQuery;
        } finally {
            c.unlock();
        }
    }

    public SQLiteDatabase a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return j();
        }
        throw new IllegalStateException("Cannot call getDatabase from the UI thread!");
    }

    public <T> AsyncTask a(f<T> fVar, a<T> aVar) {
        Executor executor = p.b;
        a aVar2 = new a(this.e.getApplicationContext(), fVar, aVar);
        Void[] voidArr = new Void[0];
        if (VERSION.SDK_INT >= 11) {
            aVar2.executeOnExecutor(executor, voidArr);
        } else {
            aVar2.execute(voidArr);
        }
        return aVar2;
    }

    public AsyncTask a(String str, int i, String str2, double d2, double d3, String str3, Map<String, String> map, a<String> aVar) {
        final String str4 = str;
        final int i2 = i;
        final String str5 = str2;
        final double d4 = d2;
        final double d5 = d3;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        AnonymousClass1 r0 = new i<String>() {
            /* JADX WARNING: Removed duplicated region for block: B:32:0x0090 A[Catch:{ Exception -> 0x0094 }] */
            /* JADX WARNING: Removed duplicated region for block: B:44:0x00b9 A[Catch:{ Exception -> 0x00bd }] */
            @android.support.annotation.Nullable
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.String b() {
                /*
                    r14 = this;
                    java.lang.String r0 = r2
                    boolean r0 = android.text.TextUtils.isEmpty(r0)
                    r1 = 0
                    if (r0 == 0) goto L_0x000a
                    return r1
                L_0x000a:
                    java.util.concurrent.locks.Lock r0 = com.facebook.ads.internal.j.d.d
                    r0.lock()
                    com.facebook.ads.internal.j.d r0 = com.facebook.ads.internal.j.d.this     // Catch:{ Exception -> 0x006e, all -> 0x0069 }
                    android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch:{ Exception -> 0x006e, all -> 0x0069 }
                    r0.beginTransaction()     // Catch:{ Exception -> 0x0067 }
                    com.facebook.ads.internal.j.d r2 = com.facebook.ads.internal.j.d.this     // Catch:{ Exception -> 0x0067 }
                    com.facebook.ads.internal.j.c r3 = r2.g     // Catch:{ Exception -> 0x0067 }
                    com.facebook.ads.internal.j.d r2 = com.facebook.ads.internal.j.d.this     // Catch:{ Exception -> 0x0067 }
                    com.facebook.ads.internal.j.h r2 = r2.f     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r4 = r2     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r4 = r2.a(r4)     // Catch:{ Exception -> 0x0067 }
                    int r5 = r3     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r6 = r4     // Catch:{ Exception -> 0x0067 }
                    double r7 = r5     // Catch:{ Exception -> 0x0067 }
                    double r9 = r7     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r11 = r9     // Catch:{ Exception -> 0x0067 }
                    java.util.Map r12 = r10     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r2 = r3.a(r4, r5, r6, r7, r9, r11, r12)     // Catch:{ Exception -> 0x0067 }
                    r0.setTransactionSuccessful()     // Catch:{ Exception -> 0x0067 }
                    if (r0 == 0) goto L_0x005f
                    boolean r1 = r0.isOpen()
                    if (r1 == 0) goto L_0x005f
                    boolean r1 = r0.inTransaction()     // Catch:{ Exception -> 0x0051 }
                    if (r1 == 0) goto L_0x005f
                    r0.endTransaction()     // Catch:{ Exception -> 0x0051 }
                    goto L_0x005f
                L_0x0051:
                    r0 = move-exception
                    com.facebook.ads.internal.j.d r1 = com.facebook.ads.internal.j.d.this
                    android.content.Context r1 = r1.e
                    java.lang.String r3 = "database"
                    int r4 = com.facebook.ads.internal.w.h.b.w
                    com.facebook.ads.internal.w.h.a.b(r1, r3, r4, r0)
                L_0x005f:
                    java.util.concurrent.locks.Lock r0 = com.facebook.ads.internal.j.d.d
                    r0.unlock()
                    return r2
                L_0x0067:
                    r2 = move-exception
                    goto L_0x0070
                L_0x0069:
                    r0 = move-exception
                    r13 = r1
                    r1 = r0
                    r0 = r13
                    goto L_0x00ab
                L_0x006e:
                    r2 = move-exception
                    r0 = r1
                L_0x0070:
                    com.facebook.ads.internal.j.f$a r3 = com.facebook.ads.internal.j.f.a.DATABASE_INSERT     // Catch:{ all -> 0x00aa }
                    r14.a(r3)     // Catch:{ all -> 0x00aa }
                    com.facebook.ads.internal.j.d r3 = com.facebook.ads.internal.j.d.this     // Catch:{ all -> 0x00aa }
                    android.content.Context r3 = r3.e     // Catch:{ all -> 0x00aa }
                    java.lang.String r4 = "database"
                    int r5 = com.facebook.ads.internal.w.h.b.u     // Catch:{ all -> 0x00aa }
                    com.facebook.ads.internal.w.h.a.b(r3, r4, r5, r2)     // Catch:{ all -> 0x00aa }
                    if (r0 == 0) goto L_0x00a2
                    boolean r2 = r0.isOpen()
                    if (r2 == 0) goto L_0x00a2
                    boolean r2 = r0.inTransaction()     // Catch:{ Exception -> 0x0094 }
                    if (r2 == 0) goto L_0x00a2
                    r0.endTransaction()     // Catch:{ Exception -> 0x0094 }
                    goto L_0x00a2
                L_0x0094:
                    r0 = move-exception
                    com.facebook.ads.internal.j.d r2 = com.facebook.ads.internal.j.d.this
                    android.content.Context r2 = r2.e
                    java.lang.String r3 = "database"
                    int r4 = com.facebook.ads.internal.w.h.b.w
                    com.facebook.ads.internal.w.h.a.b(r2, r3, r4, r0)
                L_0x00a2:
                    java.util.concurrent.locks.Lock r0 = com.facebook.ads.internal.j.d.d
                    r0.unlock()
                    return r1
                L_0x00aa:
                    r1 = move-exception
                L_0x00ab:
                    if (r0 == 0) goto L_0x00cb
                    boolean r2 = r0.isOpen()
                    if (r2 == 0) goto L_0x00cb
                    boolean r2 = r0.inTransaction()     // Catch:{ Exception -> 0x00bd }
                    if (r2 == 0) goto L_0x00cb
                    r0.endTransaction()     // Catch:{ Exception -> 0x00bd }
                    goto L_0x00cb
                L_0x00bd:
                    r0 = move-exception
                    com.facebook.ads.internal.j.d r2 = com.facebook.ads.internal.j.d.this
                    android.content.Context r2 = r2.e
                    int r3 = com.facebook.ads.internal.w.h.b.w
                    java.lang.String r4 = "database"
                    com.facebook.ads.internal.w.h.a.b(r2, r4, r3, r0)
                L_0x00cb:
                    java.util.concurrent.locks.Lock r0 = com.facebook.ads.internal.j.d.d
                    r0.unlock()
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.j.d.AnonymousClass1.b():java.lang.String");
            }
        };
        return a(r0, aVar);
    }

    @WorkerThread
    public boolean a(String str) {
        d.lock();
        boolean z = true;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ");
            sb.append(Constants.VIDEO_TRACKING_EVENTS_KEY);
            sb.append(" SET ");
            sb.append(c.i.b);
            sb.append("=");
            sb.append(c.i.b);
            sb.append("+1");
            sb.append(" WHERE ");
            sb.append(c.a.b);
            sb.append("=?");
            a().execSQL(sb.toString(), new String[]{str});
        } catch (SQLiteException unused) {
            z = false;
        }
        d.unlock();
        return z;
    }

    public synchronized void b() {
        for (g e2 : c()) {
            e2.e();
        }
        if (this.h != null) {
            this.h.close();
            this.h = null;
        }
    }

    @WorkerThread
    public boolean b(String str) {
        d.lock();
        try {
            return this.g.a(str);
        } finally {
            d.unlock();
        }
    }

    public g[] c() {
        return new g[]{this.f, this.g};
    }

    public Cursor d() {
        c.lock();
        try {
            return this.g.c();
        } finally {
            c.unlock();
        }
    }

    @WorkerThread
    public Cursor e() {
        c.lock();
        try {
            return this.g.d();
        } finally {
            c.unlock();
        }
    }

    @WorkerThread
    public Cursor f() {
        c.lock();
        try {
            return this.f.c();
        } finally {
            c.unlock();
        }
    }

    @WorkerThread
    public void g() {
        d.lock();
        try {
            this.f.d();
        } finally {
            d.unlock();
        }
    }

    @WorkerThread
    public void h() {
        d.lock();
        try {
            this.g.g();
            this.f.g();
        } finally {
            d.unlock();
        }
    }
}
