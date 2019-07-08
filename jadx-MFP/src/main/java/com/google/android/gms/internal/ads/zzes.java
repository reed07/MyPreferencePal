package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzes {
    private static final String TAG = "zzes";
    private final String className;
    private final zzdl zzqo;
    private final String zzuu;
    private final int zzuv = 2;
    private volatile Method zzuw = null;
    private final Class<?>[] zzux;
    private CountDownLatch zzuy = new CountDownLatch(1);

    public zzes(zzdl zzdl, String str, String str2, Class<?>... clsArr) {
        this.zzqo = zzdl;
        this.className = str;
        this.zzuu = str2;
        this.zzux = clsArr;
        this.zzqo.zzac().submit(new zzet(this));
    }

    /* access modifiers changed from: private */
    public final void zzaw() {
        try {
            Class loadClass = this.zzqo.zzad().loadClass(zzb(this.zzqo.zzaf(), this.className));
            if (loadClass != null) {
                this.zzuw = loadClass.getMethod(zzb(this.zzqo.zzaf(), this.zzuu), this.zzux);
                if (this.zzuw == null) {
                    this.zzuy.countDown();
                } else {
                    this.zzuy.countDown();
                }
            }
        } catch (zzcx unused) {
        } catch (UnsupportedEncodingException unused2) {
        } catch (ClassNotFoundException unused3) {
        } catch (NoSuchMethodException unused4) {
        } catch (NullPointerException unused5) {
        } finally {
            this.zzuy.countDown();
        }
    }

    private final String zzb(byte[] bArr, String str) throws zzcx, UnsupportedEncodingException {
        return new String(this.zzqo.zzae().zza(bArr, str), "UTF-8");
    }

    public final Method zzax() {
        if (this.zzuw != null) {
            return this.zzuw;
        }
        try {
            if (!this.zzuy.await(2, TimeUnit.SECONDS)) {
                return null;
            }
            return this.zzuw;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
