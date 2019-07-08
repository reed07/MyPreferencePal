package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.ByteArrayOutputStream;

final class zzawj implements Runnable {
    private final /* synthetic */ Bitmap val$bitmap;
    private final /* synthetic */ zzawg zzege;

    zzawj(zzawg zzawg, Bitmap bitmap) {
        this.zzege = zzawg;
        this.val$bitmap = bitmap;
    }

    public final void run() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.val$bitmap.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
        synchronized (this.zzege.mLock) {
            this.zzege.zzefr.zzgay = new zzbvs();
            this.zzege.zzefr.zzgay.zzgbs = byteArrayOutputStream.toByteArray();
            this.zzege.zzefr.zzgay.mimeType = "image/png";
            this.zzege.zzefr.zzgay.zzgar = Integer.valueOf(1);
        }
    }
}
