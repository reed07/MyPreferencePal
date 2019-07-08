package com.mopub.mobileads;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;

public class VideoDownloader {
    /* access modifiers changed from: private */
    public static final Deque<WeakReference<VideoDownloaderTask>> sDownloaderTasks = new ArrayDeque();

    interface VideoDownloaderListener {
        void onComplete(boolean z);
    }

    @VisibleForTesting
    static class VideoDownloaderTask extends AsyncTask<String, Void, Boolean> {
        @NonNull
        private final VideoDownloaderListener mListener;
        @NonNull
        private final WeakReference<VideoDownloaderTask> mWeakSelf = new WeakReference<>(this);

        @VisibleForTesting
        VideoDownloaderTask(@NonNull VideoDownloaderListener videoDownloaderListener) {
            this.mListener = videoDownloaderListener;
            VideoDownloader.sDownloaderTasks.add(this.mWeakSelf);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x00a7  */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x00b0  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Boolean doInBackground(java.lang.String... r7) {
            /*
                r6 = this;
                r0 = 0
                if (r7 == 0) goto L_0x00b4
                int r1 = r7.length
                if (r1 == 0) goto L_0x00b4
                r1 = r7[r0]
                if (r1 != 0) goto L_0x000c
                goto L_0x00b4
            L_0x000c:
                r7 = r7[r0]
                r1 = 0
                java.net.HttpURLConnection r2 = com.mopub.common.MoPubHttpUrlConnection.getHttpUrlConnection(r7)     // Catch:{ Exception -> 0x0097, all -> 0x0093 }
                java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0091 }
                java.io.InputStream r4 = r2.getInputStream()     // Catch:{ Exception -> 0x0091 }
                r3.<init>(r4)     // Catch:{ Exception -> 0x0091 }
                int r1 = r2.getResponseCode()     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                r4 = 200(0xc8, float:2.8E-43)
                if (r1 < r4) goto L_0x0068
                r4 = 300(0x12c, float:4.2E-43)
                if (r1 < r4) goto L_0x0029
                goto L_0x0068
            L_0x0029:
                int r1 = r2.getContentLength()     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                r4 = 26214400(0x1900000, float:5.2897246E-38)
                if (r1 <= r4) goto L_0x0057
                java.lang.String r7 = "VideoDownloader encountered video larger than disk cap. (%d bytes / %d maximum)."
                r5 = 2
                java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                r5[r0] = r1     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                r1 = 1
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                r5[r1] = r4     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                java.lang.String r7 = java.lang.String.format(r7, r5)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                com.mopub.common.logging.MoPubLog.d(r7)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r0)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                com.mopub.common.util.Streams.closeStream(r3)
                if (r2 == 0) goto L_0x0056
                r2.disconnect()
            L_0x0056:
                return r7
            L_0x0057:
                boolean r7 = com.mopub.common.CacheService.putToDiskCache(r7, r3)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                com.mopub.common.util.Streams.closeStream(r3)
                if (r2 == 0) goto L_0x0067
                r2.disconnect()
            L_0x0067:
                return r7
            L_0x0068:
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                r7.<init>()     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                java.lang.String r4 = "VideoDownloader encountered unexpected statusCode: "
                r7.append(r4)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                r7.append(r1)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                com.mopub.common.logging.MoPubLog.d(r7)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r0)     // Catch:{ Exception -> 0x008b, all -> 0x0089 }
                com.mopub.common.util.Streams.closeStream(r3)
                if (r2 == 0) goto L_0x0088
                r2.disconnect()
            L_0x0088:
                return r7
            L_0x0089:
                r7 = move-exception
                goto L_0x00ab
            L_0x008b:
                r7 = move-exception
                r1 = r3
                goto L_0x0099
            L_0x008e:
                r7 = move-exception
                r3 = r1
                goto L_0x00ab
            L_0x0091:
                r7 = move-exception
                goto L_0x0099
            L_0x0093:
                r7 = move-exception
                r2 = r1
                r3 = r2
                goto L_0x00ab
            L_0x0097:
                r7 = move-exception
                r2 = r1
            L_0x0099:
                java.lang.String r3 = "VideoDownloader task threw an internal exception."
                com.mopub.common.logging.MoPubLog.d(r3, r7)     // Catch:{ all -> 0x008e }
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x008e }
                com.mopub.common.util.Streams.closeStream(r1)
                if (r2 == 0) goto L_0x00aa
                r2.disconnect()
            L_0x00aa:
                return r7
            L_0x00ab:
                com.mopub.common.util.Streams.closeStream(r3)
                if (r2 == 0) goto L_0x00b3
                r2.disconnect()
            L_0x00b3:
                throw r7
            L_0x00b4:
                java.lang.String r7 = "VideoDownloader task tried to execute null or empty url."
                com.mopub.common.logging.MoPubLog.d(r7)
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r0)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VideoDownloader.VideoDownloaderTask.doInBackground(java.lang.String[]):java.lang.Boolean");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            if (isCancelled()) {
                onCancelled();
                return;
            }
            VideoDownloader.sDownloaderTasks.remove(this.mWeakSelf);
            if (bool == null) {
                this.mListener.onComplete(false);
            } else {
                this.mListener.onComplete(bool.booleanValue());
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled() {
            MoPubLog.d("VideoDownloader task was cancelled.");
            VideoDownloader.sDownloaderTasks.remove(this.mWeakSelf);
            this.mListener.onComplete(false);
        }
    }

    private VideoDownloader() {
    }

    public static void cache(@Nullable String str, @NonNull VideoDownloaderListener videoDownloaderListener) {
        Preconditions.checkNotNull(videoDownloaderListener);
        if (str == null) {
            MoPubLog.d("VideoDownloader attempted to cache video with null url.");
            videoDownloaderListener.onComplete(false);
            return;
        }
        try {
            AsyncTasks.safeExecuteOnExecutor(new VideoDownloaderTask(videoDownloaderListener), str);
        } catch (Exception unused) {
            videoDownloaderListener.onComplete(false);
        }
    }

    public static void cancelAllDownloaderTasks() {
        for (WeakReference cancelOneTask : sDownloaderTasks) {
            cancelOneTask(cancelOneTask);
        }
        sDownloaderTasks.clear();
    }

    public static void cancelLastDownloadTask() {
        if (!sDownloaderTasks.isEmpty()) {
            cancelOneTask((WeakReference) sDownloaderTasks.peekLast());
            sDownloaderTasks.removeLast();
        }
    }

    private static boolean cancelOneTask(@Nullable WeakReference<VideoDownloaderTask> weakReference) {
        if (weakReference == null) {
            return false;
        }
        VideoDownloaderTask videoDownloaderTask = (VideoDownloaderTask) weakReference.get();
        if (videoDownloaderTask == null) {
            return false;
        }
        return videoDownloaderTask.cancel(true);
    }

    @Deprecated
    @VisibleForTesting
    public static Deque<WeakReference<VideoDownloaderTask>> getDownloaderTasks() {
        return sDownloaderTasks;
    }

    @Deprecated
    @VisibleForTesting
    public static void clearDownloaderTasks() {
        sDownloaderTasks.clear();
    }
}
