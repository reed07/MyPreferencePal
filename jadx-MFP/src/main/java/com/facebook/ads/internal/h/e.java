package com.facebook.ads.internal.h;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.v.b.f;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class e {
    private static final String a = "e";
    private static e b;
    private final Future<f> c;

    private e(final Context context) {
        this.c = Executors.newSingleThreadExecutor().submit(new Callable<f>() {
            /* renamed from: a */
            public f call() {
                return new f(context);
            }
        });
    }

    public static e a(Context context) {
        if (b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (e.class) {
                if (b == null) {
                    b = new e(applicationContext);
                }
            }
        }
        return b;
    }

    @Nullable
    private f a() {
        try {
            return (f) this.c.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e(a, "Timed out waiting for cache server.", e);
            return null;
        }
    }

    public boolean a(String str) {
        f a2 = a();
        return a2 != null && a2.a(str);
    }

    @Nullable
    public String b(String str) {
        f a2 = a();
        if (a2 == null) {
            return null;
        }
        return a2.b(str);
    }
}
