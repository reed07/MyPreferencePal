package com.mopub.common;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.DiskLruCache.Editor;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Streams;
import com.mopub.common.util.Utils;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CacheService {
    private static DiskLruCache sDiskLruCache;

    public interface DiskLruCacheGetListener {
        void onComplete(String str, byte[] bArr);
    }

    private static class DiskLruCacheGetTask extends AsyncTask<Void, Void, byte[]> {
        private final DiskLruCacheGetListener mDiskLruCacheGetListener;
        private final String mKey;

        DiskLruCacheGetTask(String str, DiskLruCacheGetListener diskLruCacheGetListener) {
            this.mDiskLruCacheGetListener = diskLruCacheGetListener;
            this.mKey = str;
        }

        /* access modifiers changed from: protected */
        public byte[] doInBackground(Void... voidArr) {
            return CacheService.getFromDiskCache(this.mKey);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(byte[] bArr) {
            if (isCancelled()) {
                onCancelled();
                return;
            }
            DiskLruCacheGetListener diskLruCacheGetListener = this.mDiskLruCacheGetListener;
            if (diskLruCacheGetListener != null) {
                diskLruCacheGetListener.onComplete(this.mKey, bArr);
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled() {
            DiskLruCacheGetListener diskLruCacheGetListener = this.mDiskLruCacheGetListener;
            if (diskLruCacheGetListener != null) {
                diskLruCacheGetListener.onComplete(this.mKey, null);
            }
        }
    }

    private static class DiskLruCachePutTask extends AsyncTask<Void, Void, Void> {
        private final byte[] mContent;
        private final String mKey;

        DiskLruCachePutTask(String str, byte[] bArr) {
            this.mKey = str;
            this.mContent = bArr;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            CacheService.putToDiskCache(this.mKey, this.mContent);
            return null;
        }
    }

    public static boolean initializeDiskCache(Context context) {
        if (context == null) {
            return false;
        }
        if (sDiskLruCache == null) {
            File diskCacheDirectory = getDiskCacheDirectory(context);
            if (diskCacheDirectory == null) {
                return false;
            }
            try {
                sDiskLruCache = DiskLruCache.open(diskCacheDirectory, 1, 1, DeviceUtils.diskCacheSizeBytes(diskCacheDirectory));
            } catch (IOException e) {
                MoPubLog.d("Unable to create DiskLruCache", e);
                return false;
            }
        }
        return true;
    }

    public static void initialize(Context context) {
        initializeDiskCache(context);
    }

    public static String createValidDiskCacheKey(String str) {
        return Utils.sha1(str);
    }

    @Nullable
    public static File getDiskCacheDirectory(@NonNull Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        String path = cacheDir.getPath();
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        sb.append(File.separator);
        sb.append("mopub-cache");
        return new File(sb.toString());
    }

    public static boolean containsKeyDiskCache(String str) {
        DiskLruCache diskLruCache = sDiskLruCache;
        boolean z = false;
        if (diskLruCache == null) {
            return false;
        }
        try {
            if (diskLruCache.get(createValidDiskCacheKey(str)) != null) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getFilePathDiskCache(String str) {
        if (sDiskLruCache == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(sDiskLruCache.getDirectory());
        sb.append(File.separator);
        sb.append(createValidDiskCacheKey(str));
        sb.append(".");
        sb.append(0);
        return sb.toString();
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0055  */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] getFromDiskCache(java.lang.String r6) {
        /*
            com.mopub.common.DiskLruCache r0 = sDiskLruCache
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r6 = createValidDiskCacheKey(r6)     // Catch:{ Exception -> 0x0046 }
            com.mopub.common.DiskLruCache$Snapshot r6 = r0.get(r6)     // Catch:{ Exception -> 0x0046 }
            if (r6 != 0) goto L_0x0016
            if (r6 == 0) goto L_0x0015
            r6.close()
        L_0x0015:
            return r1
        L_0x0016:
            r0 = 0
            java.io.InputStream r2 = r6.getInputStream(r0)     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            if (r2 == 0) goto L_0x0035
            long r3 = r6.getLength(r0)     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            int r0 = (int) r3     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            byte[] r1 = new byte[r0]     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            r0.<init>(r2)     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            com.mopub.common.util.Streams.readStream(r0, r1)     // Catch:{ all -> 0x0030 }
            com.mopub.common.util.Streams.closeStream(r0)     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            goto L_0x0035
        L_0x0030:
            r2 = move-exception
            com.mopub.common.util.Streams.closeStream(r0)     // Catch:{ Exception -> 0x003e, all -> 0x003c }
            throw r2     // Catch:{ Exception -> 0x003e, all -> 0x003c }
        L_0x0035:
            if (r6 == 0) goto L_0x003a
            r6.close()
        L_0x003a:
            r6 = r1
            goto L_0x0052
        L_0x003c:
            r0 = move-exception
            goto L_0x0053
        L_0x003e:
            r0 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0048
        L_0x0043:
            r0 = move-exception
            r6 = r1
            goto L_0x0053
        L_0x0046:
            r0 = move-exception
            r6 = r1
        L_0x0048:
            java.lang.String r2 = "Unable to get from DiskLruCache"
            com.mopub.common.logging.MoPubLog.d(r2, r0)     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x0052
            r1.close()
        L_0x0052:
            return r6
        L_0x0053:
            if (r6 == 0) goto L_0x0058
            r6.close()
        L_0x0058:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.CacheService.getFromDiskCache(java.lang.String):byte[]");
    }

    public static void getFromDiskCacheAsync(String str, DiskLruCacheGetListener diskLruCacheGetListener) {
        new DiskLruCacheGetTask(str, diskLruCacheGetListener).execute(new Void[0]);
    }

    public static boolean putToDiskCache(String str, byte[] bArr) {
        return putToDiskCache(str, (InputStream) new ByteArrayInputStream(bArr));
    }

    public static boolean putToDiskCache(String str, InputStream inputStream) {
        DiskLruCache diskLruCache = sDiskLruCache;
        if (diskLruCache == null) {
            return false;
        }
        Editor editor = null;
        try {
            Editor edit = diskLruCache.edit(createValidDiskCacheKey(str));
            if (edit == null) {
                return false;
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(edit.newOutputStream(0));
            Streams.copyContent(inputStream, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            sDiskLruCache.flush();
            edit.commit();
            return true;
        } catch (Exception e) {
            MoPubLog.d("Unable to put to DiskLruCache", e);
            if (editor != null) {
                try {
                    editor.abort();
                } catch (IOException unused) {
                }
            }
            return false;
        }
    }

    public static void putToDiskCacheAsync(String str, byte[] bArr) {
        new DiskLruCachePutTask(str, bArr).execute(new Void[0]);
    }

    @Deprecated
    @VisibleForTesting
    public static void clearAndNullCaches() {
        DiskLruCache diskLruCache = sDiskLruCache;
        if (diskLruCache != null) {
            try {
                diskLruCache.delete();
                sDiskLruCache = null;
            } catch (IOException unused) {
                sDiskLruCache = null;
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    public static DiskLruCache getDiskLruCache() {
        return sDiskLruCache;
    }
}
