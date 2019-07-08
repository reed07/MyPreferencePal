package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCountAdapter;
import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCreator;
import com.liulishuo.filedownloader.util.FileDownloadHelper.IdGenerator;
import com.liulishuo.filedownloader.util.FileDownloadHelper.OutputStreamCreator;
import java.io.File;
import java.io.IOException;

public class CustomComponentHolder {
    private ConnectionCountAdapter connectionCountAdapter;
    private ConnectionCreator connectionCreator;
    private FileDownloadDatabase database;
    private IdGenerator idGenerator;
    private DownloadMgrInitialParams initialParams;
    private OutputStreamCreator outputStreamCreator;

    private static final class LazyLoader {
        /* access modifiers changed from: private */
        public static final CustomComponentHolder INSTANCE = new CustomComponentHolder();

        private LazyLoader() {
        }
    }

    public static CustomComponentHolder getImpl() {
        return LazyLoader.INSTANCE;
    }

    public FileDownloadConnection createConnection(String str) throws IOException {
        return getConnectionCreator().create(str);
    }

    public FileDownloadOutputStream createOutputStream(File file) throws IOException {
        return getOutputStreamCreator().create(file);
    }

    public IdGenerator getIdGeneratorInstance() {
        IdGenerator idGenerator2 = this.idGenerator;
        if (idGenerator2 != null) {
            return idGenerator2;
        }
        synchronized (this) {
            if (this.idGenerator == null) {
                this.idGenerator = getDownloadMgrInitialParams().createIdGenerator();
            }
        }
        return this.idGenerator;
    }

    public FileDownloadDatabase getDatabaseInstance() {
        FileDownloadDatabase fileDownloadDatabase = this.database;
        if (fileDownloadDatabase != null) {
            return fileDownloadDatabase;
        }
        synchronized (this) {
            if (this.database == null) {
                this.database = getDownloadMgrInitialParams().createDatabase();
                maintainDatabase(this.database.maintainer());
            }
        }
        return this.database;
    }

    public int getMaxNetworkThreadCount() {
        return getDownloadMgrInitialParams().getMaxNetworkThreadCount();
    }

    public boolean isSupportSeek() {
        return getOutputStreamCreator().supportSeek();
    }

    public int determineConnectionCount(int i, String str, String str2, long j) {
        return getConnectionCountAdapter().determineConnectionCount(i, str, str2, j);
    }

    private ConnectionCountAdapter getConnectionCountAdapter() {
        ConnectionCountAdapter connectionCountAdapter2 = this.connectionCountAdapter;
        if (connectionCountAdapter2 != null) {
            return connectionCountAdapter2;
        }
        synchronized (this) {
            if (this.connectionCountAdapter == null) {
                this.connectionCountAdapter = getDownloadMgrInitialParams().createConnectionCountAdapter();
            }
        }
        return this.connectionCountAdapter;
    }

    private ConnectionCreator getConnectionCreator() {
        ConnectionCreator connectionCreator2 = this.connectionCreator;
        if (connectionCreator2 != null) {
            return connectionCreator2;
        }
        synchronized (this) {
            if (this.connectionCreator == null) {
                this.connectionCreator = getDownloadMgrInitialParams().createConnectionCreator();
            }
        }
        return this.connectionCreator;
    }

    private OutputStreamCreator getOutputStreamCreator() {
        OutputStreamCreator outputStreamCreator2 = this.outputStreamCreator;
        if (outputStreamCreator2 != null) {
            return outputStreamCreator2;
        }
        synchronized (this) {
            if (this.outputStreamCreator == null) {
                this.outputStreamCreator = getDownloadMgrInitialParams().createOutputStreamCreator();
            }
        }
        return this.outputStreamCreator;
    }

    private DownloadMgrInitialParams getDownloadMgrInitialParams() {
        DownloadMgrInitialParams downloadMgrInitialParams = this.initialParams;
        if (downloadMgrInitialParams != null) {
            return downloadMgrInitialParams;
        }
        synchronized (this) {
            if (this.initialParams == null) {
                this.initialParams = new DownloadMgrInitialParams();
            }
        }
        return this.initialParams;
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x019e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void maintainDatabase(com.liulishuo.filedownloader.database.FileDownloadDatabase.Maintainer r25) {
        /*
            r1 = r25
            java.util.Iterator r0 = r25.iterator()
            com.liulishuo.filedownloader.download.CustomComponentHolder r2 = getImpl()
            com.liulishuo.filedownloader.util.FileDownloadHelper$IdGenerator r2 = r2.getIdGeneratorInstance()
            long r3 = java.lang.System.currentTimeMillis()
            r7 = 0
            r9 = 0
            r11 = 0
        L_0x0018:
            r14 = 3
            r15 = 2
            r16 = 0
            boolean r17 = r0.hasNext()     // Catch:{ all -> 0x018b }
            if (r17 == 0) goto L_0x014d
            java.lang.Object r17 = r0.next()     // Catch:{ all -> 0x018b }
            r5 = r17
            com.liulishuo.filedownloader.model.FileDownloadModel r5 = (com.liulishuo.filedownloader.model.FileDownloadModel) r5     // Catch:{ all -> 0x018b }
            byte r6 = r5.getStatus()     // Catch:{ all -> 0x018b }
            r13 = -2
            if (r6 == r14) goto L_0x004f
            byte r6 = r5.getStatus()     // Catch:{ all -> 0x018b }
            if (r6 == r15) goto L_0x004f
            byte r6 = r5.getStatus()     // Catch:{ all -> 0x018b }
            r15 = -1
            if (r6 == r15) goto L_0x004f
            byte r6 = r5.getStatus()     // Catch:{ all -> 0x018b }
            r15 = 1
            if (r6 != r15) goto L_0x0052
            long r20 = r5.getSoFar()     // Catch:{ all -> 0x018b }
            r18 = 0
            int r6 = (r20 > r18 ? 1 : (r20 == r18 ? 0 : -1))
            if (r6 <= 0) goto L_0x0052
        L_0x004f:
            r5.setStatus(r13)     // Catch:{ all -> 0x018b }
        L_0x0052:
            java.lang.String r6 = r5.getTargetFilePath()     // Catch:{ all -> 0x018b }
            if (r6 != 0) goto L_0x0061
            r21 = r3
            r23 = r7
            r3 = 1
            r6 = 0
            goto L_0x00fb
        L_0x0061:
            java.io.File r15 = new java.io.File     // Catch:{ all -> 0x018b }
            r15.<init>(r6)     // Catch:{ all -> 0x018b }
            byte r6 = r5.getStatus()     // Catch:{ all -> 0x018b }
            if (r6 != r13) goto L_0x00cd
            int r6 = r5.getId()     // Catch:{ all -> 0x018b }
            java.lang.String r13 = r5.getPath()     // Catch:{ all -> 0x018b }
            r14 = 0
            boolean r6 = com.liulishuo.filedownloader.util.FileDownloadUtils.isBreakpointAvailable(r6, r5, r13, r14)     // Catch:{ all -> 0x018b }
            if (r6 == 0) goto L_0x00c8
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x018b }
            java.lang.String r13 = r5.getTempFilePath()     // Catch:{ all -> 0x018b }
            r6.<init>(r13)     // Catch:{ all -> 0x018b }
            boolean r13 = r6.exists()     // Catch:{ all -> 0x018b }
            if (r13 != 0) goto L_0x00c3
            boolean r13 = r15.exists()     // Catch:{ all -> 0x018b }
            if (r13 == 0) goto L_0x00c3
            boolean r13 = r15.renameTo(r6)     // Catch:{ all -> 0x018b }
            boolean r14 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x018b }
            if (r14 == 0) goto L_0x00be
            java.lang.Class<com.liulishuo.filedownloader.database.FileDownloadDatabase> r14 = com.liulishuo.filedownloader.database.FileDownloadDatabase.class
            r21 = r3
            java.lang.String r3 = "resume from the old no-temp-file architecture [%B], [%s]->[%s]"
            r23 = r7
            r4 = 3
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ all -> 0x014b }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r13)     // Catch:{ all -> 0x014b }
            r7[r16] = r4     // Catch:{ all -> 0x014b }
            java.lang.String r4 = r15.getPath()     // Catch:{ all -> 0x014b }
            r8 = 1
            r7[r8] = r4     // Catch:{ all -> 0x014b }
            java.lang.String r4 = r6.getPath()     // Catch:{ all -> 0x014b }
            r6 = 2
            r7[r6] = r4     // Catch:{ all -> 0x014b }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r14, r3, r7)     // Catch:{ all -> 0x014b }
            goto L_0x00d1
        L_0x00bb:
            r0 = move-exception
            goto L_0x018e
        L_0x00be:
            r21 = r3
            r23 = r7
            goto L_0x00d1
        L_0x00c3:
            r21 = r3
            r23 = r7
            goto L_0x00d1
        L_0x00c8:
            r21 = r3
            r23 = r7
            goto L_0x00d1
        L_0x00cd:
            r21 = r3
            r23 = r7
        L_0x00d1:
            byte r3 = r5.getStatus()     // Catch:{ all -> 0x014b }
            r4 = 1
            if (r3 != r4) goto L_0x00e4
            long r3 = r5.getSoFar()     // Catch:{ all -> 0x014b }
            r6 = 0
            int r8 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x00e6
            r3 = 1
            goto L_0x00fb
        L_0x00e4:
            r6 = 0
        L_0x00e6:
            int r3 = r5.getId()     // Catch:{ all -> 0x014b }
            boolean r3 = com.liulishuo.filedownloader.util.FileDownloadUtils.isBreakpointAvailable(r3, r5)     // Catch:{ all -> 0x014b }
            if (r3 != 0) goto L_0x00f2
            r3 = 1
            goto L_0x00fb
        L_0x00f2:
            boolean r3 = r15.exists()     // Catch:{ all -> 0x014b }
            if (r3 == 0) goto L_0x00fa
            r3 = 1
            goto L_0x00fb
        L_0x00fa:
            r3 = 0
        L_0x00fb:
            r13 = 1
            if (r3 == 0) goto L_0x0109
            r0.remove()     // Catch:{ all -> 0x014b }
            r1.onRemovedInvalidData(r5)     // Catch:{ all -> 0x014b }
            long r9 = r9 + r13
            r7 = r23
            goto L_0x0147
        L_0x0109:
            int r3 = r5.getId()     // Catch:{ all -> 0x014b }
            java.lang.String r4 = r5.getUrl()     // Catch:{ all -> 0x014b }
            java.lang.String r8 = r5.getPath()     // Catch:{ all -> 0x014b }
            boolean r15 = r5.isPathAsDirectory()     // Catch:{ all -> 0x014b }
            int r4 = r2.transOldId(r3, r4, r8, r15)     // Catch:{ all -> 0x014b }
            if (r4 == r3) goto L_0x0142
            boolean r8 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x014b }
            if (r8 == 0) goto L_0x013b
            java.lang.Class<com.liulishuo.filedownloader.database.FileDownloadDatabase> r8 = com.liulishuo.filedownloader.database.FileDownloadDatabase.class
            java.lang.String r15 = "the id is changed on restoring from db: old[%d] -> new[%d]"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x014b }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x014b }
            r7[r16] = r6     // Catch:{ all -> 0x014b }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x014b }
            r17 = 1
            r7[r17] = r6     // Catch:{ all -> 0x014b }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r8, r15, r7)     // Catch:{ all -> 0x014b }
        L_0x013b:
            r5.setId(r4)     // Catch:{ all -> 0x014b }
            r1.changeFileDownloadModelId(r3, r5)     // Catch:{ all -> 0x014b }
            long r11 = r11 + r13
        L_0x0142:
            r1.onRefreshedValidData(r5)     // Catch:{ all -> 0x014b }
            long r7 = r23 + r13
        L_0x0147:
            r3 = r21
            goto L_0x0018
        L_0x014b:
            r0 = move-exception
            goto L_0x0190
        L_0x014d:
            r21 = r3
            r23 = r7
            android.content.Context r0 = com.liulishuo.filedownloader.util.FileDownloadHelper.getAppContext()
            com.liulishuo.filedownloader.util.FileDownloadUtils.markConverted(r0)
            r25.onFinishMaintain()
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG
            if (r0 == 0) goto L_0x018a
            java.lang.Class<com.liulishuo.filedownloader.database.FileDownloadDatabase> r0 = com.liulishuo.filedownloader.database.FileDownloadDatabase.class
            java.lang.String r1 = "refreshed data count: %d , delete data count: %d, reset id count: %d. consume %d"
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Long r3 = java.lang.Long.valueOf(r23)
            r2[r16] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r9)
            r4 = 1
            r2[r4] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r11)
            r4 = 2
            r2[r4] = r3
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r21
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r4 = 3
            r2[r4] = r3
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r0, r1, r2)
        L_0x018a:
            return
        L_0x018b:
            r0 = move-exception
            r21 = r3
        L_0x018e:
            r23 = r7
        L_0x0190:
            android.content.Context r2 = com.liulishuo.filedownloader.util.FileDownloadHelper.getAppContext()
            com.liulishuo.filedownloader.util.FileDownloadUtils.markConverted(r2)
            r25.onFinishMaintain()
            boolean r1 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG
            if (r1 == 0) goto L_0x01c9
            java.lang.Class<com.liulishuo.filedownloader.database.FileDownloadDatabase> r1 = com.liulishuo.filedownloader.database.FileDownloadDatabase.class
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Long r3 = java.lang.Long.valueOf(r23)
            r2[r16] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r9)
            r4 = 1
            r2[r4] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r11)
            r4 = 2
            r2[r4] = r3
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r21
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r4 = 3
            r2[r4] = r3
            java.lang.String r3 = "refreshed data count: %d , delete data count: %d, reset id count: %d. consume %d"
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r1, r3, r2)
        L_0x01c9:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.CustomComponentHolder.maintainDatabase(com.liulishuo.filedownloader.database.FileDownloadDatabase$Maintainer):void");
    }
}
