package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

final class h {
    /* access modifiers changed from: private */
    public Context a;
    private a b;
    private FutureTask<Void> c;
    /* access modifiers changed from: private */
    public boolean d;

    class a implements Callable<Void> {
        private a() {
        }

        /* synthetic */ a(h hVar, byte b) {
            this();
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public Void call() throws Exception {
            SAAdapter a2 = SAAdapter.a(h.this.a);
            try {
                a2.a();
                try {
                    a2.a(l.a(h.this.a).a());
                    Log.i("[SA_SDK]SARegistrationTask", "Services Registered successfully!");
                    synchronized (h.this) {
                        h.this.d = false;
                    }
                    return null;
                } catch (c e) {
                    Log.e("[SA_SDK]SARegistrationTask", "Registration failed!", e);
                    throw e;
                } catch (Throwable th) {
                    synchronized (h.this) {
                        h.this.d = false;
                        throw th;
                    }
                }
            } catch (c e2) {
                Log.e("[SA_SDK]SARegistrationTask", "Registration failed.Unable to connect to Accessory framework!", e2);
                throw e2;
            }
        }
    }

    h(Context context) {
        if (context != null) {
            this.a = context;
            return;
        }
        StringBuilder sb = new StringBuilder("Invalid context:");
        sb.append(null);
        throw new IllegalArgumentException(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public final synchronized Future<Void> a() {
        if (this.b == null && this.c == null) {
            this.b = new a(this, 0);
            this.c = new FutureTask<>(this.b);
        } else {
            throw new IllegalStateException("SARegistrationTask instance cannot be reused");
        }
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void b() {
        if (this.b == null || this.c == null) {
            throw new IllegalStateException("Prepare not called");
        } else if (!this.d) {
            new Thread(this.c, "RegistreationThread").start();
            this.d = true;
        } else {
            Log.e("[SA_SDK]SARegistrationTask", "Registration task has already started");
            throw new IllegalStateException("Registration task is already running!");
        }
    }
}
