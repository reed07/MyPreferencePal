package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

/* compiled from: IMASDK */
final class vm extends HandlerThread implements Callback {
    private uc a;
    private Handler b;
    private Error c;
    private RuntimeException d;
    private vl e;

    public vm() {
        super("dummySurface");
    }

    public final vl a(int i) {
        boolean z;
        start();
        this.b = new Handler(getLooper(), this);
        this.a = new uc(this.b);
        synchronized (this) {
            z = false;
            this.b.obtainMessage(1, i, 0).sendToTarget();
            while (this.e == null && this.d == null && this.c == null) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                    z = true;
                }
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        RuntimeException runtimeException = this.d;
        if (runtimeException == null) {
            Error error = this.c;
            if (error == null) {
                return (vl) qi.a(this.e);
            }
            throw error;
        }
        throw runtimeException;
    }

    public final void a() {
        qi.a(this.b);
        this.b.sendEmptyMessage(2);
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                try {
                    int i = message.arg1;
                    qi.a(this.a);
                    this.a.a(i);
                    this.e = new vl(this, this.a.b(), i != 0, 0);
                    synchronized (this) {
                        notify();
                    }
                } catch (RuntimeException e2) {
                    uk.b("DummySurface", "Failed to initialize dummy surface", e2);
                    this.d = e2;
                    synchronized (this) {
                        notify();
                    }
                } catch (Error e3) {
                    try {
                        uk.b("DummySurface", "Failed to initialize dummy surface", e3);
                        this.c = e3;
                        synchronized (this) {
                            notify();
                        }
                    } catch (Throwable th) {
                        synchronized (this) {
                            notify();
                            throw th;
                        }
                    }
                }
                return true;
            case 2:
                try {
                    qi.a(this.a);
                    this.a.a();
                } catch (Throwable th2) {
                    uk.b("DummySurface", "Failed to release dummy surface", th2);
                } finally {
                    quit();
                }
                return true;
            default:
                return true;
        }
    }
}
