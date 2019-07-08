package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.common.util.PlatformVersion;
import java.io.InputStream;

final class zzaqc implements zzazy<zzabr> {
    private final /* synthetic */ String zzdpv;
    private final /* synthetic */ zzapw zzdtp;
    private final /* synthetic */ boolean zzdtz;
    private final /* synthetic */ double zzdua;
    private final /* synthetic */ boolean zzdub;

    zzaqc(zzapw zzapw, boolean z, double d, boolean z2, String str) {
        this.zzdtp = zzapw;
        this.zzdtz = z;
        this.zzdua = d;
        this.zzdub = z2;
        this.zzdpv = str;
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    /* renamed from: zzd */
    public final zzabr zze(InputStream inputStream) {
        Bitmap bitmap;
        Options options = new Options();
        options.inDensity = (int) (this.zzdua * 160.0d);
        if (!this.zzdub) {
            options.inPreferredConfig = Config.RGB_565;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            bitmap = BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Exception e) {
            zzaxz.zzb("Error grabbing image.", e);
            bitmap = null;
        }
        if (bitmap == null) {
            this.zzdtp.zzh(2, this.zzdtz);
            return null;
        }
        long uptimeMillis2 = SystemClock.uptimeMillis();
        if (PlatformVersion.isAtLeastKitKat() && zzaxz.zzza()) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int allocationByteCount = bitmap.getAllocationByteCount();
            long j = uptimeMillis2 - uptimeMillis;
            boolean z = Looper.getMainLooper().getThread() == Thread.currentThread();
            StringBuilder sb = new StringBuilder(108);
            sb.append("Decoded image w: ");
            sb.append(width);
            sb.append(" h:");
            sb.append(height);
            sb.append(" bytes: ");
            sb.append(allocationByteCount);
            sb.append(" time: ");
            sb.append(j);
            sb.append(" on ui thread: ");
            sb.append(z);
            zzaxz.v(sb.toString());
        }
        return new zzabr(new BitmapDrawable(Resources.getSystem(), bitmap), Uri.parse(this.zzdpv), this.zzdua);
    }

    public final /* synthetic */ Object zzwf() {
        this.zzdtp.zzh(2, this.zzdtz);
        return null;
    }
}
