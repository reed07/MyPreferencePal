package com.facebook.ads.internal.w.c;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.support.annotation.WorkerThread;
import android.util.Base64;
import com.facebook.ads.internal.w.b.x;
import java.io.InputStream;

public class c {
    private static int a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static Bitmap a(b bVar) {
        byte[] decode = Base64.decode(bVar.a(x.b), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    @WorkerThread
    public static Bitmap a(InputStream inputStream, int i, int i2) {
        if (VERSION.SDK_INT < 19) {
            return BitmapFactory.decodeStream(inputStream);
        }
        e eVar = new e(inputStream);
        eVar.mark(8192);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(eVar, null, options);
        eVar.reset();
        if (eVar.a()) {
            return BitmapFactory.decodeStream(eVar);
        }
        options.inSampleSize = a(options, i2, i);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(eVar, null, options);
    }

    @WorkerThread
    public static Bitmap a(String str, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = a(options, i2, i);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }
}
