package com.bumptech.glide.load.model;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.InputStream;

public class StreamEncoder implements Encoder<InputStream> {
    private final ArrayPool byteArrayPool;

    public StreamEncoder(ArrayPool arrayPool) {
        this.byteArrayPool = arrayPool;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0038 A[Catch:{ all -> 0x002c }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0041 A[SYNTHETIC, Splitter:B:23:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004c A[SYNTHETIC, Splitter:B:29:0x004c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean encode(@android.support.annotation.NonNull java.io.InputStream r4, @android.support.annotation.NonNull java.io.File r5, @android.support.annotation.NonNull com.bumptech.glide.load.Options r6) {
        /*
            r3 = this;
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r6 = r3.byteArrayPool
            java.lang.Class<byte[]> r0 = byte[].class
            r1 = 65536(0x10000, float:9.18355E-41)
            java.lang.Object r6 = r6.get(r1, r0)
            byte[] r6 = (byte[]) r6
            r0 = 0
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x002e }
            r2.<init>(r5)     // Catch:{ IOException -> 0x002e }
        L_0x0013:
            int r5 = r4.read(r6)     // Catch:{ IOException -> 0x0029, all -> 0x0026 }
            r1 = -1
            if (r5 == r1) goto L_0x001e
            r2.write(r6, r0, r5)     // Catch:{ IOException -> 0x0029, all -> 0x0026 }
            goto L_0x0013
        L_0x001e:
            r2.close()     // Catch:{ IOException -> 0x0029, all -> 0x0026 }
            r0 = 1
            r2.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0044
        L_0x0026:
            r4 = move-exception
            r1 = r2
            goto L_0x004a
        L_0x0029:
            r4 = move-exception
            r1 = r2
            goto L_0x002f
        L_0x002c:
            r4 = move-exception
            goto L_0x004a
        L_0x002e:
            r4 = move-exception
        L_0x002f:
            java.lang.String r5 = "StreamEncoder"
            r2 = 3
            boolean r5 = android.util.Log.isLoggable(r5, r2)     // Catch:{ all -> 0x002c }
            if (r5 == 0) goto L_0x003f
            java.lang.String r5 = "StreamEncoder"
            java.lang.String r2 = "Failed to encode data onto the OutputStream"
            android.util.Log.d(r5, r2, r4)     // Catch:{ all -> 0x002c }
        L_0x003f:
            if (r1 == 0) goto L_0x0044
            r1.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0044:
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r4 = r3.byteArrayPool
            r4.put(r6)
            return r0
        L_0x004a:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ IOException -> 0x004f }
        L_0x004f:
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r5 = r3.byteArrayPool
            r5.put(r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.model.StreamEncoder.encode(java.io.InputStream, java.io.File, com.bumptech.glide.load.Options):boolean");
    }
}
