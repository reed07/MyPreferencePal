package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.internal.ads.zzpi;
import java.io.IOException;

@SuppressLint({"HandlerLeak"})
final class zzph<T extends zzpi> extends Handler implements Runnable {
    private final T zzbgx;
    private final zzpg<T> zzbgy;
    public final int zzbgz;
    private final long zzbha;
    private IOException zzbhb;
    private int zzbhc;
    private volatile Thread zzbhd;
    private final /* synthetic */ zzpf zzbhe;
    private volatile boolean zzyb;

    public zzph(zzpf zzpf, Looper looper, T t, zzpg<T> zzpg, int i, long j) {
        this.zzbhe = zzpf;
        super(looper);
        this.zzbgx = t;
        this.zzbgy = zzpg;
        this.zzbgz = i;
        this.zzbha = j;
    }

    public final void zzbi(int i) throws IOException {
        IOException iOException = this.zzbhb;
        if (iOException != null && this.zzbhc > i) {
            throw iOException;
        }
    }

    public final void zzal(long j) {
        zzpo.checkState(this.zzbhe.zzbgw == null);
        this.zzbhe.zzbgw = this;
        if (j > 0) {
            sendEmptyMessageDelayed(0, j);
        } else {
            execute();
        }
    }

    public final void zzj(boolean z) {
        this.zzyb = z;
        this.zzbhb = null;
        if (hasMessages(0)) {
            removeMessages(0);
            if (!z) {
                sendEmptyMessage(1);
            }
        } else {
            this.zzbgx.cancelLoad();
            if (this.zzbhd != null) {
                this.zzbhd.interrupt();
            }
        }
        if (z) {
            finish();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zzbgy.zza(this.zzbgx, elapsedRealtime, elapsedRealtime - this.zzbha, true);
        }
    }

    public final void run() {
        try {
            this.zzbhd = Thread.currentThread();
            if (!this.zzbgx.zzfe()) {
                String str = "load:";
                String valueOf = String.valueOf(this.zzbgx.getClass().getSimpleName());
                zzqc.beginSection(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                this.zzbgx.zzff();
                zzqc.endSection();
            }
            if (!this.zzyb) {
                sendEmptyMessage(2);
            }
        } catch (IOException e) {
            if (!this.zzyb) {
                obtainMessage(3, e).sendToTarget();
            }
        } catch (InterruptedException unused) {
            zzpo.checkState(this.zzbgx.zzfe());
            if (!this.zzyb) {
                sendEmptyMessage(2);
            }
        } catch (Exception e2) {
            Log.e("LoadTask", "Unexpected exception loading stream", e2);
            if (!this.zzyb) {
                obtainMessage(3, new zzpj(e2)).sendToTarget();
            }
        } catch (OutOfMemoryError e3) {
            Log.e("LoadTask", "OutOfMemory error loading stream", e3);
            if (!this.zzyb) {
                obtainMessage(3, new zzpj(e3)).sendToTarget();
            }
        } catch (Error e4) {
            Log.e("LoadTask", "Unexpected error loading stream", e4);
            if (!this.zzyb) {
                obtainMessage(4, e4).sendToTarget();
            }
            throw e4;
        } catch (Throwable th) {
            zzqc.endSection();
            throw th;
        }
    }

    public final void handleMessage(Message message) {
        int i;
        if (!this.zzyb) {
            if (message.what == 0) {
                execute();
            } else if (message.what != 4) {
                finish();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                long j = elapsedRealtime - this.zzbha;
                if (this.zzbgx.zzfe()) {
                    this.zzbgy.zza(this.zzbgx, elapsedRealtime, j, false);
                    return;
                }
                switch (message.what) {
                    case 1:
                        this.zzbgy.zza(this.zzbgx, elapsedRealtime, j, false);
                        return;
                    case 2:
                        this.zzbgy.zza(this.zzbgx, elapsedRealtime, j);
                        return;
                    case 3:
                        this.zzbhb = (IOException) message.obj;
                        int zza = this.zzbgy.zza(this.zzbgx, elapsedRealtime, j, this.zzbhb);
                        if (zza != 3) {
                            if (zza != 2) {
                                if (zza == 1) {
                                    i = 1;
                                } else {
                                    i = this.zzbhc + 1;
                                }
                                this.zzbhc = i;
                                zzal((long) Math.min((this.zzbhc - 1) * 1000, 5000));
                                break;
                            }
                        } else {
                            this.zzbhe.zzbci = this.zzbhb;
                            return;
                        }
                        break;
                }
            } else {
                throw ((Error) message.obj);
            }
        }
    }

    private final void execute() {
        this.zzbhb = null;
        this.zzbhe.zzbgv.execute(this.zzbhe.zzbgw);
    }

    private final void finish() {
        this.zzbhe.zzbgw = null;
    }
}
