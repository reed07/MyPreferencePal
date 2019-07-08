package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class DownloadRunnable implements Runnable {
    private final ProcessCallback callback;
    private final ConnectTask connectTask;
    final int connectionIndex;
    private final int downloadId;
    private FetchDataTask fetchDataTask;
    private final boolean isWifiRequired;
    private final String path;
    private volatile boolean paused;

    public static class Builder {
        private ProcessCallback callback;
        private final Builder connectTaskBuilder = new Builder();
        private Integer connectionIndex;
        private Boolean isWifiRequired;
        private String path;

        public Builder setCallback(ProcessCallback processCallback) {
            this.callback = processCallback;
            return this;
        }

        public Builder setId(int i) {
            this.connectTaskBuilder.setDownloadId(i);
            return this;
        }

        public Builder setUrl(String str) {
            this.connectTaskBuilder.setUrl(str);
            return this;
        }

        public Builder setEtag(String str) {
            this.connectTaskBuilder.setEtag(str);
            return this;
        }

        public Builder setHeader(FileDownloadHeader fileDownloadHeader) {
            this.connectTaskBuilder.setHeader(fileDownloadHeader);
            return this;
        }

        public Builder setConnectionModel(ConnectionProfile connectionProfile) {
            this.connectTaskBuilder.setConnectionProfile(connectionProfile);
            return this;
        }

        public Builder setPath(String str) {
            this.path = str;
            return this;
        }

        public Builder setWifiRequired(boolean z) {
            this.isWifiRequired = Boolean.valueOf(z);
            return this;
        }

        public Builder setConnectionIndex(Integer num) {
            this.connectionIndex = num;
            return this;
        }

        public DownloadRunnable build() {
            if (this.callback == null || this.path == null || this.isWifiRequired == null || this.connectionIndex == null) {
                throw new IllegalArgumentException(FileDownloadUtils.formatString("%s %s %B", this.callback, this.path, this.isWifiRequired));
            }
            ConnectTask build = this.connectTaskBuilder.build();
            DownloadRunnable downloadRunnable = new DownloadRunnable(build.downloadId, this.connectionIndex.intValue(), build, this.callback, this.isWifiRequired.booleanValue(), this.path);
            return downloadRunnable;
        }
    }

    private DownloadRunnable(int i, int i2, ConnectTask connectTask2, ProcessCallback processCallback, boolean z, String str) {
        this.downloadId = i;
        this.connectionIndex = i2;
        this.paused = false;
        this.callback = processCallback;
        this.path = str;
        this.connectTask = connectTask2;
        this.isWifiRequired = z;
    }

    public void pause() {
        this.paused = true;
        FetchDataTask fetchDataTask2 = this.fetchDataTask;
        if (fetchDataTask2 != null) {
            fetchDataTask2.pause();
        }
    }

    public void discard() {
        pause();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00e7, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ee, code lost:
        r4 = r1;
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00fb, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fd, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0104, code lost:
        r11 = r4;
        r4 = r1;
        r1 = r2;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0110, code lost:
        if (r1 == false) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0116, code lost:
        com.liulishuo.filedownloader.util.FileDownloadLog.w(r12, "it is valid to retry and connection is valid but create fetch-data-task failed, so give up directly with %s", r2);
        r12.callback.onError(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0124, code lost:
        if (r4 != null) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0129, code lost:
        if (r12.fetchDataTask != null) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x012b, code lost:
        r5 = getDownloadedOffset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0133, code lost:
        if (r5 > 0) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0135, code lost:
        r12.connectTask.updateConnectionProfile(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x013a, code lost:
        r12.callback.onRetry(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x013f, code lost:
        if (r4 != null) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0141, code lost:
        r4.ending();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0144, code lost:
        r2 = r1;
        r1 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r12.callback.onError(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x014d, code lost:
        if (r4 != null) goto L_0x014f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0157, code lost:
        r1.ending();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00fb A[ExcHandler: all (th java.lang.Throwable), PHI: r1 
  PHI: (r1v8 com.liulishuo.filedownloader.connection.FileDownloadConnection) = (r1v1 com.liulishuo.filedownloader.connection.FileDownloadConnection), (r1v1 com.liulishuo.filedownloader.connection.FileDownloadConnection), (r1v1 com.liulishuo.filedownloader.connection.FileDownloadConnection), (r1v10 com.liulishuo.filedownloader.connection.FileDownloadConnection), (r1v10 com.liulishuo.filedownloader.connection.FileDownloadConnection), (r1v10 com.liulishuo.filedownloader.connection.FileDownloadConnection) binds: [B:2:0x0011, B:3:?, B:8:0x001b, B:19:0x008d, B:20:?, B:25:0x009c] A[DONT_GENERATE, DONT_INLINE], Splitter:B:2:0x0011] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0110 A[Catch:{ all -> 0x0153 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0148 A[SYNTHETIC, Splitter:B:56:0x0148] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0157  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r12 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
            com.liulishuo.filedownloader.download.ConnectTask r0 = r12.connectTask
            com.liulishuo.filedownloader.download.ConnectionProfile r0 = r0.getProfile()
            long r0 = r0.currentOffset
            r0 = 0
            r1 = 0
            r2 = 0
        L_0x0010:
            r3 = 1
            boolean r2 = r12.paused     // Catch:{ IllegalAccessException -> 0x0103, IOException -> 0x0101, FileDownloadGiveUpRetryException -> 0x00ff, IllegalArgumentException -> 0x00fd, all -> 0x00fb }
            if (r2 == 0) goto L_0x001b
            if (r1 == 0) goto L_0x001a
            r1.ending()
        L_0x001a:
            return
        L_0x001b:
            com.liulishuo.filedownloader.download.ConnectTask r2 = r12.connectTask     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            com.liulishuo.filedownloader.connection.FileDownloadConnection r1 = r2.connect()     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            int r2 = r1.getResponseCode()     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            boolean r4 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r5 = 3
            r6 = 2
            r7 = 4
            if (r4 == 0) goto L_0x0051
            java.lang.String r4 = "the connection[%d] for %d, is connected %s with code[%d]"
            java.lang.Object[] r8 = new java.lang.Object[r7]     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            int r9 = r12.connectionIndex     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r8[r0] = r9     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            int r9 = r12.downloadId     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r8[r3] = r9     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            com.liulishuo.filedownloader.download.ConnectTask r9 = r12.connectTask     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            com.liulishuo.filedownloader.download.ConnectionProfile r9 = r9.getProfile()     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r8[r6] = r9     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r8[r5] = r9     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r12, r4, r8)     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
        L_0x0051:
            r4 = 206(0xce, float:2.89E-43)
            if (r2 == r4) goto L_0x008d
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 != r4) goto L_0x005a
            goto L_0x008d
        L_0x005a:
            java.net.SocketException r4 = new java.net.SocketException     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.lang.String r8 = "Connection failed with request[%s] response[%s] http-state[%d] on task[%d-%d], which is changed after verify connection, so please try again."
            r9 = 5
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            com.liulishuo.filedownloader.download.ConnectTask r10 = r12.connectTask     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.util.Map r10 = r10.getRequestHeader()     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r9[r0] = r10     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.util.Map r10 = r1.getResponseHeaderFields()     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r9[r3] = r10     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r9[r6] = r2     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            int r2 = r12.downloadId     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r9[r5] = r2     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            int r2 = r12.connectionIndex     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r9[r7] = r2     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            java.lang.String r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r8, r9)     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            r4.<init>(r2)     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
            throw r4     // Catch:{ IllegalAccessException -> 0x00f7, IOException -> 0x00f5, FileDownloadGiveUpRetryException -> 0x00f3, IllegalArgumentException -> 0x00f1, all -> 0x00fb }
        L_0x008d:
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r2 = new com.liulishuo.filedownloader.download.FetchDataTask$Builder     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            r2.<init>()     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            boolean r4 = r12.paused     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            if (r4 == 0) goto L_0x009c
            if (r1 == 0) goto L_0x009b
            r1.ending()
        L_0x009b:
            return
        L_0x009c:
            int r4 = r12.downloadId     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r2 = r2.setDownloadId(r4)     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            int r4 = r12.connectionIndex     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r2 = r2.setConnectionIndex(r4)     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.ProcessCallback r4 = r12.callback     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r2 = r2.setCallback(r4)     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r2 = r2.setHost(r12)     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            boolean r4 = r12.isWifiRequired     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r2 = r2.setWifiRequired(r4)     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r2 = r2.setConnection(r1)     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.ConnectTask r4 = r12.connectTask     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.ConnectionProfile r4 = r4.getProfile()     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r2 = r2.setConnectionProfile(r4)     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            java.lang.String r4 = r12.path     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r2 = r2.setPath(r4)     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask r2 = r2.build()     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            r12.fetchDataTask = r2     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            com.liulishuo.filedownloader.download.FetchDataTask r2 = r12.fetchDataTask     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            r2.run()     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            boolean r2 = r12.paused     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            if (r2 == 0) goto L_0x00e0
            com.liulishuo.filedownloader.download.FetchDataTask r2 = r12.fetchDataTask     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
            r2.pause()     // Catch:{ IllegalAccessException -> 0x00ed, IOException -> 0x00eb, FileDownloadGiveUpRetryException -> 0x00e9, IllegalArgumentException -> 0x00e7, all -> 0x00fb }
        L_0x00e0:
            if (r1 == 0) goto L_0x0152
            r1.ending()
            goto L_0x0152
        L_0x00e7:
            r2 = move-exception
            goto L_0x00ee
        L_0x00e9:
            r2 = move-exception
            goto L_0x00ee
        L_0x00eb:
            r2 = move-exception
            goto L_0x00ee
        L_0x00ed:
            r2 = move-exception
        L_0x00ee:
            r4 = r1
            r1 = 1
            goto L_0x0108
        L_0x00f1:
            r2 = move-exception
            goto L_0x00f8
        L_0x00f3:
            r2 = move-exception
            goto L_0x00f8
        L_0x00f5:
            r2 = move-exception
            goto L_0x00f8
        L_0x00f7:
            r2 = move-exception
        L_0x00f8:
            r4 = r1
            r1 = 0
            goto L_0x0108
        L_0x00fb:
            r0 = move-exception
            goto L_0x0155
        L_0x00fd:
            r4 = move-exception
            goto L_0x0104
        L_0x00ff:
            r4 = move-exception
            goto L_0x0104
        L_0x0101:
            r4 = move-exception
            goto L_0x0104
        L_0x0103:
            r4 = move-exception
        L_0x0104:
            r11 = r4
            r4 = r1
            r1 = r2
            r2 = r11
        L_0x0108:
            com.liulishuo.filedownloader.download.ProcessCallback r5 = r12.callback     // Catch:{ all -> 0x0153 }
            boolean r5 = r5.isRetry(r2)     // Catch:{ all -> 0x0153 }
            if (r5 == 0) goto L_0x0148
            if (r1 == 0) goto L_0x0127
            com.liulishuo.filedownloader.download.FetchDataTask r5 = r12.fetchDataTask     // Catch:{ all -> 0x0153 }
            if (r5 != 0) goto L_0x0127
            java.lang.String r1 = "it is valid to retry and connection is valid but create fetch-data-task failed, so give up directly with %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0153 }
            r3[r0] = r2     // Catch:{ all -> 0x0153 }
            com.liulishuo.filedownloader.util.FileDownloadLog.w(r12, r1, r3)     // Catch:{ all -> 0x0153 }
            com.liulishuo.filedownloader.download.ProcessCallback r0 = r12.callback     // Catch:{ all -> 0x0153 }
            r0.onError(r2)     // Catch:{ all -> 0x0153 }
            if (r4 == 0) goto L_0x0152
            goto L_0x014f
        L_0x0127:
            com.liulishuo.filedownloader.download.FetchDataTask r3 = r12.fetchDataTask     // Catch:{ all -> 0x0153 }
            if (r3 == 0) goto L_0x013a
            long r5 = r12.getDownloadedOffset()     // Catch:{ all -> 0x0153 }
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x013a
            com.liulishuo.filedownloader.download.ConnectTask r3 = r12.connectTask     // Catch:{ all -> 0x0153 }
            r3.updateConnectionProfile(r5)     // Catch:{ all -> 0x0153 }
        L_0x013a:
            com.liulishuo.filedownloader.download.ProcessCallback r3 = r12.callback     // Catch:{ all -> 0x0153 }
            r3.onRetry(r2)     // Catch:{ all -> 0x0153 }
            if (r4 == 0) goto L_0x0144
            r4.ending()
        L_0x0144:
            r2 = r1
            r1 = r4
            goto L_0x0010
        L_0x0148:
            com.liulishuo.filedownloader.download.ProcessCallback r0 = r12.callback     // Catch:{ all -> 0x0153 }
            r0.onError(r2)     // Catch:{ all -> 0x0153 }
            if (r4 == 0) goto L_0x0152
        L_0x014f:
            r4.ending()
        L_0x0152:
            return
        L_0x0153:
            r0 = move-exception
            r1 = r4
        L_0x0155:
            if (r1 == 0) goto L_0x015a
            r1.ending()
        L_0x015a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadRunnable.run():void");
    }

    private long getDownloadedOffset() {
        FileDownloadDatabase databaseInstance = CustomComponentHolder.getImpl().getDatabaseInstance();
        if (this.connectionIndex < 0) {
            return databaseInstance.find(this.downloadId).getSoFar();
        }
        for (ConnectionModel connectionModel : databaseInstance.findConnectionModel(this.downloadId)) {
            if (connectionModel.getIndex() == this.connectionIndex) {
                return connectionModel.getCurrentOffset();
            }
        }
        return 0;
    }
}
