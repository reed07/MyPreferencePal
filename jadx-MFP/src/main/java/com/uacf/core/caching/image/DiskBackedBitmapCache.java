package com.uacf.core.caching.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.uacf.core.caching.Cache;
import com.uacf.core.caching.disk.DiskLruCache;
import com.uacf.core.caching.disk.DiskLruCache.Snapshot;
import java.io.File;
import java.io.IOException;

public class DiskBackedBitmapCache {
    private static final CompressFormat DEFAULT_COMPRESS_FORMAT = CompressFormat.WEBP;
    private String cacheName;
    private CompressFormat compressFormat;
    private int compressQuality;
    private Context context;
    private final Object diskCacheLock;
    private DiskLruCache diskLruCache;
    private final Cache<Bitmap> memoryCache;

    public DiskBackedBitmapCache(Context context2, String str, Cache<Bitmap> cache) {
        this(context2, str, cache, DEFAULT_COMPRESS_FORMAT, 70);
    }

    public DiskBackedBitmapCache(Context context2, String str, Cache<Bitmap> cache, CompressFormat compressFormat2, int i) {
        this.compressQuality = 70;
        this.diskCacheLock = new Object();
        this.memoryCache = cache;
        this.context = context2;
        this.compressFormat = compressFormat2;
        this.compressQuality = i;
        this.cacheName = str;
        initDiskCache();
    }

    private void initDiskCache() {
        synchronized (this.diskCacheLock) {
            try {
                this.diskLruCache = DiskLruCache.open(getDiskCacheDir(this.context, this.cacheName), 1, 1, 10485760);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.diskCacheLock.notifyAll();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:9|10|(1:12)|13|(3:14|15|(2:19|(3:21|22|23)(3:24|25|(1:27)(1:28))))|36|37|38) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x006e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void put(java.lang.String r6, android.graphics.Bitmap r7) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L_0x000e
            java.lang.String r6 = "DiskBackedBitmapCache"
            java.lang.String r7 = "Key value must not be empty or null"
            android.util.Log.d(r6, r7)
            return
        L_0x000e:
            if (r7 != 0) goto L_0x0018
            java.lang.String r6 = "DiskBackedBitmapCache"
            java.lang.String r7 = "Image Bitmap must not be null"
            android.util.Log.d(r6, r7)
            return
        L_0x0018:
            java.lang.Object r0 = r5.diskCacheLock
            monitor-enter(r0)
            com.uacf.core.caching.Cache<android.graphics.Bitmap> r1 = r5.memoryCache     // Catch:{ all -> 0x0070 }
            boolean r1 = r1.contains(r6)     // Catch:{ all -> 0x0070 }
            if (r1 != 0) goto L_0x0028
            com.uacf.core.caching.Cache<android.graphics.Bitmap> r1 = r5.memoryCache     // Catch:{ all -> 0x0070 }
            r1.put(r6, r7)     // Catch:{ all -> 0x0070 }
        L_0x0028:
            r1 = 0
            com.uacf.core.caching.disk.DiskLruCache r2 = r5.diskLruCache     // Catch:{ IOException -> 0x0052 }
            if (r2 == 0) goto L_0x006e
            com.uacf.core.caching.disk.DiskLruCache r2 = r5.diskLruCache     // Catch:{ IOException -> 0x0052 }
            com.uacf.core.caching.disk.DiskLruCache$Snapshot r2 = r2.get(r6)     // Catch:{ IOException -> 0x0052 }
            if (r2 != 0) goto L_0x006e
            com.uacf.core.caching.disk.DiskLruCache r2 = r5.diskLruCache     // Catch:{ IOException -> 0x0052 }
            com.uacf.core.caching.disk.DiskLruCache$Editor r1 = r2.edit(r6)     // Catch:{ IOException -> 0x0052 }
            if (r1 != 0) goto L_0x003f
            monitor-exit(r0)     // Catch:{ all -> 0x0070 }
            return
        L_0x003f:
            boolean r7 = r5.writeBitmapToFile(r7, r1)     // Catch:{ IOException -> 0x0052 }
            if (r7 == 0) goto L_0x004e
            com.uacf.core.caching.disk.DiskLruCache r7 = r5.diskLruCache     // Catch:{ IOException -> 0x0052 }
            r7.flush()     // Catch:{ IOException -> 0x0052 }
            r1.commit()     // Catch:{ IOException -> 0x0052 }
            goto L_0x006e
        L_0x004e:
            r1.abort()     // Catch:{ IOException -> 0x0052 }
            goto L_0x006e
        L_0x0052:
            r7 = move-exception
            java.lang.String r2 = "DiskBackedBitmapCache"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0070 }
            r3.<init>()     // Catch:{ all -> 0x0070 }
            java.lang.String r4 = "Failed to save Bitmap for key: "
            r3.append(r4)     // Catch:{ all -> 0x0070 }
            r3.append(r6)     // Catch:{ all -> 0x0070 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0070 }
            android.util.Log.e(r2, r6, r7)     // Catch:{ all -> 0x0070 }
            if (r1 == 0) goto L_0x006e
            r1.abort()     // Catch:{ IOException -> 0x006e }
        L_0x006e:
            monitor-exit(r0)     // Catch:{ all -> 0x0070 }
            return
        L_0x0070:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0070 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.caching.image.DiskBackedBitmapCache.put(java.lang.String, android.graphics.Bitmap):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003e, code lost:
        if (r7 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0050, code lost:
        if (r7 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0054, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0058 A[Catch:{ all -> 0x0055 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap get(java.lang.String r7) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.Object r0 = r6.diskCacheLock
            monitor-enter(r0)
            com.uacf.core.caching.Cache<android.graphics.Bitmap> r2 = r6.memoryCache     // Catch:{ all -> 0x005c }
            boolean r2 = r2.contains(r7)     // Catch:{ all -> 0x005c }
            if (r2 == 0) goto L_0x001d
            com.uacf.core.caching.Cache<android.graphics.Bitmap> r1 = r6.memoryCache     // Catch:{ all -> 0x005c }
            java.lang.Object r7 = r1.get(r7)     // Catch:{ all -> 0x005c }
            android.graphics.Bitmap r7 = (android.graphics.Bitmap) r7     // Catch:{ all -> 0x005c }
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            return r7
        L_0x001d:
            com.uacf.core.caching.disk.DiskLruCache r2 = r6.diskLruCache     // Catch:{ IOException -> 0x004b, all -> 0x0046 }
            com.uacf.core.caching.disk.DiskLruCache$Snapshot r7 = r2.get(r7)     // Catch:{ IOException -> 0x004b, all -> 0x0046 }
            if (r7 != 0) goto L_0x002c
            if (r7 == 0) goto L_0x002a
            r7.close()     // Catch:{ all -> 0x005c }
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            return r1
        L_0x002c:
            r2 = 0
            java.io.InputStream r2 = r7.getInputStream(r2)     // Catch:{ IOException -> 0x0044 }
            if (r2 == 0) goto L_0x003e
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0044 }
            r4 = 8192(0x2000, float:1.14794E-41)
            r3.<init>(r2, r4)     // Catch:{ IOException -> 0x0044 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r3)     // Catch:{ IOException -> 0x0044 }
        L_0x003e:
            if (r7 == 0) goto L_0x0053
        L_0x0040:
            r7.close()     // Catch:{ all -> 0x005c }
            goto L_0x0053
        L_0x0044:
            r2 = move-exception
            goto L_0x004d
        L_0x0046:
            r7 = move-exception
            r5 = r1
            r1 = r7
            r7 = r5
            goto L_0x0056
        L_0x004b:
            r2 = move-exception
            r7 = r1
        L_0x004d:
            r2.printStackTrace()     // Catch:{ all -> 0x0055 }
            if (r7 == 0) goto L_0x0053
            goto L_0x0040
        L_0x0053:
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            return r1
        L_0x0055:
            r1 = move-exception
        L_0x0056:
            if (r7 == 0) goto L_0x005b
            r7.close()     // Catch:{ all -> 0x005c }
        L_0x005b:
            throw r1     // Catch:{ all -> 0x005c }
        L_0x005c:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.caching.image.DiskBackedBitmapCache.get(java.lang.String):android.graphics.Bitmap");
    }

    public boolean contains(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        synchronized (this.diskCacheLock) {
            if (this.memoryCache.contains(str)) {
                return true;
            }
            try {
                Snapshot snapshot = this.diskLruCache.get(str);
                if (snapshot != null) {
                    z = true;
                }
                if (snapshot != null) {
                    snapshot.close();
                }
            } catch (IOException e) {
                String str2 = "DiskBackedBitmapCache";
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to get Bitmap for key: ");
                sb.append(str);
                Log.e(str2, sb.toString(), e);
            }
            return z;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean writeBitmapToFile(android.graphics.Bitmap r4, com.uacf.core.caching.disk.DiskLruCache.Editor r5) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 0
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x001c }
            r2 = 0
            java.io.OutputStream r5 = r5.newOutputStream(r2)     // Catch:{ all -> 0x001c }
            r2 = 8192(0x2000, float:1.14794E-41)
            r1.<init>(r5, r2)     // Catch:{ all -> 0x001c }
            android.graphics.Bitmap$CompressFormat r5 = r3.compressFormat     // Catch:{ all -> 0x0019 }
            int r0 = r3.compressQuality     // Catch:{ all -> 0x0019 }
            boolean r4 = r4.compress(r5, r0, r1)     // Catch:{ all -> 0x0019 }
            r1.close()
            return r4
        L_0x0019:
            r4 = move-exception
            r0 = r1
            goto L_0x001d
        L_0x001c:
            r4 = move-exception
        L_0x001d:
            if (r0 == 0) goto L_0x0022
            r0.close()
        L_0x0022:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.caching.image.DiskBackedBitmapCache.writeBitmapToFile(android.graphics.Bitmap, com.uacf.core.caching.disk.DiskLruCache$Editor):boolean");
    }

    private static File getDiskCacheDir(Context context2, String str) {
        String str2;
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            str2 = context2.getExternalCacheDir().getPath();
        } else {
            str2 = context2.getCacheDir().getPath();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(File.separator);
        sb.append(str);
        return new File(sb.toString());
    }
}
