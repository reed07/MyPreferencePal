package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.google.ads.interactivemedia.v3.internal.to;
import java.io.IOException;

@SuppressLint({"HandlerLeak"})
/* compiled from: IMASDK */
final class tn<T extends to> extends Handler implements Runnable {
    public final int a;
    private final T b;
    private final long c;
    private tl<T> d;
    private IOException e;
    private int f;
    private volatile Thread g;
    private volatile boolean h;
    private volatile boolean i;
    private final /* synthetic */ tj j;

    public tn(tj tjVar, Looper looper, T t, tl<T> tlVar, int i2, long j2) {
        this.j = tjVar;
        super(looper);
        this.b = t;
        this.d = tlVar;
        this.a = i2;
        this.c = j2;
    }

    public final void a(int i2) throws IOException {
        IOException iOException = this.e;
        if (iOException != null && this.f > i2) {
            throw iOException;
        }
    }

    public final void a(long j2) {
        qi.c(this.j.e == null);
        this.j.e = this;
        if (j2 > 0) {
            sendEmptyMessageDelayed(0, j2);
        } else {
            a();
        }
    }

    public final void a(boolean z) {
        this.i = z;
        this.e = null;
        if (hasMessages(0)) {
            removeMessages(0);
            if (!z) {
                sendEmptyMessage(1);
            }
        } else {
            this.h = true;
            this.b.a();
            if (this.g != null) {
                this.g.interrupt();
            }
        }
        if (z) {
            b();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.d.a(this.b, elapsedRealtime, elapsedRealtime - this.c, true);
            this.d = null;
        }
    }

    public final void run() {
        try {
            this.g = Thread.currentThread();
            if (!this.h) {
                String str = "load:";
                String valueOf = String.valueOf(this.b.getClass().getSimpleName());
                qi.b(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                this.b.b();
                qi.e();
            }
            if (!this.i) {
                sendEmptyMessage(2);
            }
        } catch (IOException e2) {
            if (!this.i) {
                obtainMessage(3, e2).sendToTarget();
            }
        } catch (InterruptedException unused) {
            qi.c(this.h);
            if (!this.i) {
                sendEmptyMessage(2);
            }
        } catch (Exception e3) {
            uk.b("LoadTask", "Unexpected exception loading stream", e3);
            if (!this.i) {
                obtainMessage(3, new tr(e3)).sendToTarget();
            }
        } catch (OutOfMemoryError e4) {
            uk.b("LoadTask", "OutOfMemory error loading stream", e4);
            if (!this.i) {
                obtainMessage(3, new tr(e4)).sendToTarget();
            }
        } catch (Error e5) {
            uk.b("LoadTask", "Unexpected error loading stream", e5);
            if (!this.i) {
                obtainMessage(4, e5).sendToTarget();
            }
            throw e5;
        } catch (Throwable th) {
            qi.e();
            throw th;
        }
    }

    public final void handleMessage(Message message) {
        long j2;
        if (!this.i) {
            if (message.what == 0) {
                a();
            } else if (message.what != 4) {
                b();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                long j3 = elapsedRealtime - this.c;
                if (this.h) {
                    this.d.a(this.b, elapsedRealtime, j3, false);
                    return;
                }
                switch (message.what) {
                    case 1:
                        this.d.a(this.b, elapsedRealtime, j3, false);
                        return;
                    case 2:
                        try {
                            this.d.a(this.b, elapsedRealtime, j3);
                            return;
                        } catch (RuntimeException e2) {
                            uk.b("LoadTask", "Unexpected exception handling load completed", e2);
                            this.j.f = new tr(e2);
                            return;
                        }
                    case 3:
                        this.e = (IOException) message.obj;
                        this.f++;
                        tm a2 = this.d.a(this.b, elapsedRealtime, j3, this.e, this.f);
                        if (a2.a != 3) {
                            if (a2.a != 2) {
                                if (a2.a == 1) {
                                    this.f = 1;
                                }
                                if (a2.b != -9223372036854775807L) {
                                    j2 = a2.b;
                                } else {
                                    j2 = (long) Math.min((this.f - 1) * 1000, 5000);
                                }
                                a(j2);
                                break;
                            }
                        } else {
                            this.j.f = this.e;
                            return;
                        }
                        break;
                }
            } else {
                throw ((Error) message.obj);
            }
        }
    }

    private final void a() {
        this.e = null;
        this.j.d.execute(this.j.e);
    }

    private final void b() {
        this.j.e = null;
    }
}
