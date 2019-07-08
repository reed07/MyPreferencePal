package com.facebook.ads.internal.h;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class b {
    /* access modifiers changed from: private */
    public static final String a = "b";
    private static final ExecutorService b = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public static final ExecutorService c = Executors.newFixedThreadPool(5);
    /* access modifiers changed from: private */
    public final Handler d = new Handler();
    /* access modifiers changed from: private */
    public final d e;
    /* access modifiers changed from: private */
    public final e f;
    /* access modifiers changed from: private */
    public final c g;
    private final List<Callable<Boolean>> h;

    private class a implements Callable<Boolean> {
        private final String b;

        public a(String str) {
            this.b = str;
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(b.this.g.a(this.b));
        }
    }

    /* renamed from: com.facebook.ads.internal.h.b$b reason: collision with other inner class name */
    private class C0006b implements Callable<Boolean> {
        private final String b;
        private final int c;
        private final int d;

        public C0006b(String str, int i, int i2) {
            this.b = str;
            this.c = i;
            this.d = i2;
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(b.this.e.a(this.b, this.c, this.d) != null);
        }
    }

    private class c implements Callable<Boolean> {
        private final String b;

        public c(String str) {
            this.b = str;
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(b.this.f.a(this.b));
        }
    }

    public b(Context context) {
        this.e = d.a(context);
        this.f = e.a(context);
        this.g = c.a(context);
        this.h = new ArrayList();
    }

    public void a(@Nullable final a aVar) {
        final ArrayList arrayList = new ArrayList(this.h);
        b.execute(new Runnable() {
            public void run() {
                ArrayList<Future> arrayList = new ArrayList<>(arrayList.size());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList.add(b.c.submit((Callable) it.next()));
                }
                final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                try {
                    for (Future future : arrayList) {
                        atomicBoolean.set(((Boolean) future.get()).booleanValue() & atomicBoolean.get());
                    }
                } catch (InterruptedException | ExecutionException e) {
                    Log.e(b.a, "Exception while executing cache downloads.", e);
                    atomicBoolean.set(false);
                }
                b.this.d.post(new Runnable() {
                    public void run() {
                        if (aVar == null) {
                            return;
                        }
                        if (atomicBoolean.get()) {
                            aVar.a();
                        } else {
                            aVar.b();
                        }
                    }
                });
            }
        });
        this.h.clear();
    }

    public void a(String str) {
        this.h.add(new c(str));
    }

    public void a(String str, int i, int i2) {
        this.h.add(new C0006b(str, i, i2));
    }

    public void b(String str) {
        this.h.add(new a(str));
    }

    public String c(String str) {
        return this.f.b(str);
    }

    public String d(String str) {
        return this.g.b(str);
    }
}
