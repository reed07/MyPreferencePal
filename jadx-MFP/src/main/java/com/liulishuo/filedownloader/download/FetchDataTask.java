package com.liulishuo.filedownloader.download;

import android.os.SystemClock;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.IOException;

public class FetchDataTask {
    private final ProcessCallback callback;
    private final FileDownloadConnection connection;
    private final int connectionIndex;
    private final long contentLength;
    long currentOffset;
    private final FileDownloadDatabase database;
    private final int downloadId;
    private final long endOffset;
    private final DownloadRunnable hostRunnable;
    private final boolean isWifiRequired;
    private volatile long lastSyncBytes;
    private volatile long lastSyncTimestamp;
    private FileDownloadOutputStream outputStream;
    private final String path;
    private volatile boolean paused;
    private final long startOffset;

    public static class Builder {
        ProcessCallback callback;
        FileDownloadConnection connection;
        Integer connectionIndex;
        ConnectionProfile connectionProfile;
        Integer downloadId;
        DownloadRunnable downloadRunnable;
        Boolean isWifiRequired;
        String path;

        public Builder setConnection(FileDownloadConnection fileDownloadConnection) {
            this.connection = fileDownloadConnection;
            return this;
        }

        public Builder setConnectionProfile(ConnectionProfile connectionProfile2) {
            this.connectionProfile = connectionProfile2;
            return this;
        }

        public Builder setCallback(ProcessCallback processCallback) {
            this.callback = processCallback;
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

        public Builder setHost(DownloadRunnable downloadRunnable2) {
            this.downloadRunnable = downloadRunnable2;
            return this;
        }

        public Builder setConnectionIndex(int i) {
            this.connectionIndex = Integer.valueOf(i);
            return this;
        }

        public Builder setDownloadId(int i) {
            this.downloadId = Integer.valueOf(i);
            return this;
        }

        public FetchDataTask build() throws IllegalArgumentException {
            if (this.isWifiRequired != null) {
                FileDownloadConnection fileDownloadConnection = this.connection;
                if (fileDownloadConnection != null) {
                    ConnectionProfile connectionProfile2 = this.connectionProfile;
                    if (!(connectionProfile2 == null || this.callback == null || this.path == null)) {
                        Integer num = this.downloadId;
                        if (!(num == null || this.connectionIndex == null)) {
                            FetchDataTask fetchDataTask = new FetchDataTask(fileDownloadConnection, connectionProfile2, this.downloadRunnable, num.intValue(), this.connectionIndex.intValue(), this.isWifiRequired.booleanValue(), this.callback, this.path);
                            return fetchDataTask;
                        }
                    }
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public void pause() {
        this.paused = true;
    }

    private FetchDataTask(FileDownloadConnection fileDownloadConnection, ConnectionProfile connectionProfile, DownloadRunnable downloadRunnable, int i, int i2, boolean z, ProcessCallback processCallback, String str) {
        this.lastSyncBytes = 0;
        this.lastSyncTimestamp = 0;
        this.callback = processCallback;
        this.path = str;
        this.connection = fileDownloadConnection;
        this.isWifiRequired = z;
        this.hostRunnable = downloadRunnable;
        this.connectionIndex = i2;
        this.downloadId = i;
        this.database = CustomComponentHolder.getImpl().getDatabaseInstance();
        this.startOffset = connectionProfile.startOffset;
        this.endOffset = connectionProfile.endOffset;
        this.currentOffset = connectionProfile.currentOffset;
        this.contentLength = connectionProfile.contentLength;
    }

    /* JADX WARNING: Removed duplicated region for block: B:131:0x0206 A[SYNTHETIC, Splitter:B:131:0x0206] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0211 A[SYNTHETIC, Splitter:B:136:0x0211] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0225 A[SYNTHETIC, Splitter:B:147:0x0225] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() throws java.io.IOException, java.lang.IllegalAccessException, java.lang.IllegalArgumentException, com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException {
        /*
            r19 = this;
            r1 = r19
            boolean r0 = r1.paused
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            int r0 = r1.connectionIndex
            com.liulishuo.filedownloader.connection.FileDownloadConnection r2 = r1.connection
            long r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.findContentLength(r0, r2)
            r4 = -1
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x001b
            com.liulishuo.filedownloader.connection.FileDownloadConnection r0 = r1.connection
            long r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.findContentLengthFromContentRange(r0)
        L_0x001b:
            r6 = 0
            r8 = 2
            r9 = 1
            r10 = 0
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x022f
            long r11 = r1.contentLength
            r13 = 5
            r14 = 4
            r15 = 3
            int r0 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x008e
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x008e
            long r6 = r1.endOffset
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0048
            java.lang.Object[] r0 = new java.lang.Object[r9]
            long r4 = r1.currentOffset
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r0[r10] = r4
            java.lang.String r4 = "range[%d-)"
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r4, r0)
            goto L_0x0060
        L_0x0048:
            java.lang.Object[] r0 = new java.lang.Object[r8]
            long r4 = r1.currentOffset
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r0[r10] = r4
            long r4 = r1.endOffset
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r0[r9] = r4
            java.lang.String r4 = "range[%d-%d)"
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r4, r0)
        L_0x0060:
            com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException r4 = new com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException
            java.lang.Object[] r5 = new java.lang.Object[r13]
            r5[r10] = r0
            long r6 = r1.contentLength
            java.lang.Long r0 = java.lang.Long.valueOf(r6)
            r5[r9] = r0
            java.lang.Long r0 = java.lang.Long.valueOf(r2)
            r5[r8] = r0
            int r0 = r1.downloadId
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r15] = r0
            int r0 = r1.connectionIndex
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r14] = r0
            java.lang.String r0 = "require %s with contentLength(%d), but the backend response contentLength is %d on downloadId[%d]-connectionIndex[%d], please ask your backend dev to fix such problem."
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r0, r5)
            r4.<init>(r0)
            throw r4
        L_0x008e:
            long r6 = r1.currentOffset
            r11 = 0
            com.liulishuo.filedownloader.download.CustomComponentHolder r0 = com.liulishuo.filedownloader.download.CustomComponentHolder.getImpl()     // Catch:{ all -> 0x0201 }
            boolean r0 = r0.isSupportSeek()     // Catch:{ all -> 0x0201 }
            com.liulishuo.filedownloader.download.DownloadRunnable r12 = r1.hostRunnable     // Catch:{ all -> 0x0201 }
            if (r12 == 0) goto L_0x00a8
            if (r0 == 0) goto L_0x00a0
            goto L_0x00a8
        L_0x00a0:
            java.lang.IllegalAccessException r0 = new java.lang.IllegalAccessException     // Catch:{ all -> 0x0201 }
            java.lang.String r2 = "can't using multi-download when the output stream can't support seek"
            r0.<init>(r2)     // Catch:{ all -> 0x0201 }
            throw r0     // Catch:{ all -> 0x0201 }
        L_0x00a8:
            java.lang.String r12 = r1.path     // Catch:{ all -> 0x0201 }
            com.liulishuo.filedownloader.stream.FileDownloadOutputStream r12 = com.liulishuo.filedownloader.util.FileDownloadUtils.createOutputStream(r12)     // Catch:{ all -> 0x0201 }
            r1.outputStream = r12     // Catch:{ all -> 0x01fe }
            if (r0 == 0) goto L_0x00b7
            long r4 = r1.currentOffset     // Catch:{ all -> 0x01fe }
            r12.seek(r4)     // Catch:{ all -> 0x01fe }
        L_0x00b7:
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x01fe }
            if (r0 == 0) goto L_0x00e2
            java.lang.String r0 = "start fetch(%d): range [%d, %d), seek to[%d]"
            java.lang.Object[] r4 = new java.lang.Object[r14]     // Catch:{ all -> 0x01fe }
            int r5 = r1.connectionIndex     // Catch:{ all -> 0x01fe }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x01fe }
            r4[r10] = r5     // Catch:{ all -> 0x01fe }
            long r13 = r1.startOffset     // Catch:{ all -> 0x01fe }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x01fe }
            r4[r9] = r13     // Catch:{ all -> 0x01fe }
            long r13 = r1.endOffset     // Catch:{ all -> 0x01fe }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x01fe }
            r4[r8] = r13     // Catch:{ all -> 0x01fe }
            long r13 = r1.currentOffset     // Catch:{ all -> 0x01fe }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x01fe }
            r4[r15] = r13     // Catch:{ all -> 0x01fe }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r1, r0, r4)     // Catch:{ all -> 0x01fe }
        L_0x00e2:
            com.liulishuo.filedownloader.connection.FileDownloadConnection r0 = r1.connection     // Catch:{ all -> 0x01fe }
            java.io.InputStream r11 = r0.getInputStream()     // Catch:{ all -> 0x01fe }
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x01fe }
            boolean r4 = r1.paused     // Catch:{ all -> 0x01fe }
            if (r4 == 0) goto L_0x011b
            if (r11 == 0) goto L_0x00fb
            r11.close()     // Catch:{ IOException -> 0x00f6 }
            goto L_0x00fb
        L_0x00f6:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x00fb:
            if (r12 == 0) goto L_0x010f
            r19.sync()     // Catch:{ all -> 0x0101 }
            goto L_0x010f
        L_0x0101:
            r0 = move-exception
            r2 = r0
            if (r12 == 0) goto L_0x010e
            r12.close()     // Catch:{ IOException -> 0x0109 }
            goto L_0x010e
        L_0x0109:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x010e:
            throw r2
        L_0x010f:
            if (r12 == 0) goto L_0x011a
            r12.close()     // Catch:{ IOException -> 0x0115 }
            goto L_0x011a
        L_0x0115:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x011a:
            return
        L_0x011b:
            int r4 = r11.read(r0)     // Catch:{ all -> 0x01fe }
            r13 = -1
            if (r4 != r13) goto L_0x01a2
            if (r11 == 0) goto L_0x012d
            r11.close()     // Catch:{ IOException -> 0x0128 }
            goto L_0x012d
        L_0x0128:
            r0 = move-exception
            r4 = r0
            r4.printStackTrace()
        L_0x012d:
            if (r12 == 0) goto L_0x0141
            r19.sync()     // Catch:{ all -> 0x0133 }
            goto L_0x0141
        L_0x0133:
            r0 = move-exception
            r2 = r0
            if (r12 == 0) goto L_0x0140
            r12.close()     // Catch:{ IOException -> 0x013b }
            goto L_0x0140
        L_0x013b:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x0140:
            throw r2
        L_0x0141:
            if (r12 == 0) goto L_0x014c
            r12.close()     // Catch:{ IOException -> 0x0147 }
            goto L_0x014c
        L_0x0147:
            r0 = move-exception
            r4 = r0
            r4.printStackTrace()
        L_0x014c:
            long r11 = r1.currentOffset
            long r11 = r11 - r6
            r13 = -1
            int r0 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0196
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x015a
            goto L_0x0196
        L_0x015a:
            com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException r0 = new com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException
            r4 = 6
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            r4[r10] = r11
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r4[r9] = r2
            long r2 = r1.startOffset
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r4[r8] = r2
            long r2 = r1.endOffset
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r4[r15] = r2
            long r2 = r1.currentOffset
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r16 = 4
            r4[r16] = r2
            java.lang.Long r2 = java.lang.Long.valueOf(r6)
            r5 = 5
            r4[r5] = r2
            java.lang.String r2 = "fetched length[%d] != content length[%d], range[%d, %d) offset[%d] fetch begin offset[%d]"
            java.lang.String r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r2, r4)
            r0.<init>(r2)
            throw r0
        L_0x0196:
            com.liulishuo.filedownloader.download.ProcessCallback r3 = r1.callback
            com.liulishuo.filedownloader.download.DownloadRunnable r4 = r1.hostRunnable
            long r5 = r1.startOffset
            long r7 = r1.endOffset
            r3.onCompleted(r4, r5, r7)
            return
        L_0x01a2:
            r5 = 5
            r13 = -1
            r16 = 4
            r12.write(r0, r10, r4)     // Catch:{ all -> 0x01fe }
            r17 = r6
            long r5 = r1.currentOffset     // Catch:{ all -> 0x01fe }
            long r13 = (long) r4     // Catch:{ all -> 0x01fe }
            long r5 = r5 + r13
            r1.currentOffset = r5     // Catch:{ all -> 0x01fe }
            com.liulishuo.filedownloader.download.ProcessCallback r4 = r1.callback     // Catch:{ all -> 0x01fe }
            r4.onProgress(r13)     // Catch:{ all -> 0x01fe }
            r19.checkAndSync()     // Catch:{ all -> 0x01fe }
            boolean r4 = r1.paused     // Catch:{ all -> 0x01fe }
            if (r4 == 0) goto L_0x01e9
            if (r11 == 0) goto L_0x01c9
            r11.close()     // Catch:{ IOException -> 0x01c4 }
            goto L_0x01c9
        L_0x01c4:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x01c9:
            if (r12 == 0) goto L_0x01dd
            r19.sync()     // Catch:{ all -> 0x01cf }
            goto L_0x01dd
        L_0x01cf:
            r0 = move-exception
            r2 = r0
            if (r12 == 0) goto L_0x01dc
            r12.close()     // Catch:{ IOException -> 0x01d7 }
            goto L_0x01dc
        L_0x01d7:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x01dc:
            throw r2
        L_0x01dd:
            if (r12 == 0) goto L_0x01e8
            r12.close()     // Catch:{ IOException -> 0x01e3 }
            goto L_0x01e8
        L_0x01e3:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x01e8:
            return
        L_0x01e9:
            boolean r4 = r1.isWifiRequired     // Catch:{ all -> 0x01fe }
            if (r4 == 0) goto L_0x01fa
            boolean r4 = com.liulishuo.filedownloader.util.FileDownloadUtils.isNetworkNotOnWifiType()     // Catch:{ all -> 0x01fe }
            if (r4 != 0) goto L_0x01f4
            goto L_0x01fa
        L_0x01f4:
            com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException r0 = new com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException     // Catch:{ all -> 0x01fe }
            r0.<init>()     // Catch:{ all -> 0x01fe }
            throw r0     // Catch:{ all -> 0x01fe }
        L_0x01fa:
            r6 = r17
            goto L_0x011b
        L_0x01fe:
            r0 = move-exception
            r2 = r0
            goto L_0x0204
        L_0x0201:
            r0 = move-exception
            r2 = r0
            r12 = r11
        L_0x0204:
            if (r11 == 0) goto L_0x020f
            r11.close()     // Catch:{ IOException -> 0x020a }
            goto L_0x020f
        L_0x020a:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x020f:
            if (r12 == 0) goto L_0x0223
            r19.sync()     // Catch:{ all -> 0x0215 }
            goto L_0x0223
        L_0x0215:
            r0 = move-exception
            r2 = r0
            if (r12 == 0) goto L_0x0222
            r12.close()     // Catch:{ IOException -> 0x021d }
            goto L_0x0222
        L_0x021d:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x0222:
            throw r2
        L_0x0223:
            if (r12 == 0) goto L_0x022e
            r12.close()     // Catch:{ IOException -> 0x0229 }
            goto L_0x022e
        L_0x0229:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x022e:
            throw r2
        L_0x022f:
            com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException r0 = new com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException
            java.lang.Object[] r2 = new java.lang.Object[r8]
            int r3 = r1.downloadId
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r10] = r3
            int r3 = r1.connectionIndex
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r9] = r3
            java.lang.String r3 = "there isn't any content need to download on %d-%d with the content-length is 0"
            java.lang.String r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r3, r2)
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.FetchDataTask.run():void");
    }

    private void checkAndSync() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (FileDownloadUtils.isNeedSync(this.currentOffset - this.lastSyncBytes, elapsedRealtime - this.lastSyncTimestamp)) {
            sync();
            this.lastSyncBytes = this.currentOffset;
            this.lastSyncTimestamp = elapsedRealtime;
        }
    }

    private void sync() {
        boolean z;
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            this.outputStream.flushAndSync();
            z = true;
        } catch (IOException e) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "Because of the system cannot guarantee that all the buffers have been synchronized with physical media, or write to filefailed, we just not flushAndSync process to database too %s", e);
            }
            z = false;
        }
        if (z) {
            if (this.connectionIndex >= 0) {
                this.database.updateConnectionModel(this.downloadId, this.connectionIndex, this.currentOffset);
            } else {
                this.callback.syncProgressFromCache();
            }
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "require flushAndSync id[%d] index[%d] offset[%d], consume[%d]", Integer.valueOf(this.downloadId), Integer.valueOf(this.connectionIndex), Long.valueOf(this.currentOffset), Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
            }
        }
    }
}
