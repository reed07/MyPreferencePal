package com.liulishuo.filedownloader.util;

public class FileDownloadProperties {
    public final boolean broadcastCompleted;
    public final int downloadMaxNetworkThreadCount;
    public final int downloadMinProgressStep;
    public final long downloadMinProgressTime;
    public final boolean fileNonPreAllocation;
    public final boolean httpLenient;
    public final boolean processNonSeparate;
    public final boolean trialConnectionHeadMethod;

    public static class HolderClass {
        /* access modifiers changed from: private */
        public static final FileDownloadProperties INSTANCE = new FileDownloadProperties();
    }

    public static FileDownloadProperties getImpl() {
        return HolderClass.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x01ad  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0225  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x022b  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x02bd A[SYNTHETIC, Splitter:B:135:0x02bd] */
    /* JADX WARNING: Removed duplicated region for block: B:143:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0098 A[Catch:{ all -> 0x02b9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00a6 A[Catch:{ all -> 0x02b9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00ab A[SYNTHETIC, Splitter:B:69:0x00ab] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x015b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private FileDownloadProperties() {
        /*
            r17 = this;
            r1 = r17
            r17.<init>()
            android.content.Context r0 = com.liulishuo.filedownloader.util.FileDownloadHelper.getAppContext()
            if (r0 == 0) goto L_0x02c7
            long r2 = java.lang.System.currentTimeMillis()
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            r4 = 0
            r5 = 0
            android.content.Context r6 = com.liulishuo.filedownloader.util.FileDownloadHelper.getAppContext()     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            java.lang.String r7 = "filedownloader.properties"
            java.io.InputStream r6 = r6.open(r7)     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            if (r6 == 0) goto L_0x0073
            r0.load(r6)     // Catch:{ IOException -> 0x0070 }
            java.lang.String r7 = "http.lenient"
            java.lang.String r7 = r0.getProperty(r7)     // Catch:{ IOException -> 0x0070 }
            java.lang.String r8 = "process.non-separate"
            java.lang.String r8 = r0.getProperty(r8)     // Catch:{ IOException -> 0x006d }
            java.lang.String r9 = "download.min-progress-step"
            java.lang.String r9 = r0.getProperty(r9)     // Catch:{ IOException -> 0x006a }
            java.lang.String r10 = "download.min-progress-time"
            java.lang.String r10 = r0.getProperty(r10)     // Catch:{ IOException -> 0x0067 }
            java.lang.String r11 = "download.max-network-thread-count"
            java.lang.String r11 = r0.getProperty(r11)     // Catch:{ IOException -> 0x0064 }
            java.lang.String r12 = "file.non-pre-allocation"
            java.lang.String r12 = r0.getProperty(r12)     // Catch:{ IOException -> 0x0061 }
            java.lang.String r13 = "broadcast.completed"
            java.lang.String r13 = r0.getProperty(r13)     // Catch:{ IOException -> 0x005e }
            java.lang.String r14 = "download.trial-connection-head-method"
            java.lang.String r0 = r0.getProperty(r14)     // Catch:{ IOException -> 0x005c }
            r4 = r7
            r7 = r0
            goto L_0x007a
        L_0x005c:
            r0 = move-exception
            goto L_0x0094
        L_0x005e:
            r0 = move-exception
            r13 = r4
            goto L_0x0094
        L_0x0061:
            r0 = move-exception
            r12 = r4
            goto L_0x0093
        L_0x0064:
            r0 = move-exception
            r11 = r4
            goto L_0x0092
        L_0x0067:
            r0 = move-exception
            r10 = r4
            goto L_0x0091
        L_0x006a:
            r0 = move-exception
            r9 = r4
            goto L_0x0090
        L_0x006d:
            r0 = move-exception
            r8 = r4
            goto L_0x008f
        L_0x0070:
            r0 = move-exception
            r7 = r4
            goto L_0x008e
        L_0x0073:
            r7 = r4
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
        L_0x007a:
            if (r6 == 0) goto L_0x00b9
            r6.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x00b9
        L_0x0080:
            r0 = move-exception
            r6 = r0
            r6.printStackTrace()
            goto L_0x00b9
        L_0x0086:
            r0 = move-exception
            r2 = r0
            r6 = r4
            goto L_0x02bb
        L_0x008b:
            r0 = move-exception
            r6 = r4
            r7 = r6
        L_0x008e:
            r8 = r7
        L_0x008f:
            r9 = r8
        L_0x0090:
            r10 = r9
        L_0x0091:
            r11 = r10
        L_0x0092:
            r12 = r11
        L_0x0093:
            r13 = r12
        L_0x0094:
            boolean r14 = r0 instanceof java.io.FileNotFoundException     // Catch:{ all -> 0x02b9 }
            if (r14 == 0) goto L_0x00a6
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG     // Catch:{ all -> 0x02b9 }
            if (r0 == 0) goto L_0x00a9
            java.lang.Class<com.liulishuo.filedownloader.util.FileDownloadProperties> r0 = com.liulishuo.filedownloader.util.FileDownloadProperties.class
            java.lang.String r14 = "not found filedownloader.properties"
            java.lang.Object[] r15 = new java.lang.Object[r5]     // Catch:{ all -> 0x02b9 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r0, r14, r15)     // Catch:{ all -> 0x02b9 }
            goto L_0x00a9
        L_0x00a6:
            r0.printStackTrace()     // Catch:{ all -> 0x02b9 }
        L_0x00a9:
            if (r6 == 0) goto L_0x00b4
            r6.close()     // Catch:{ IOException -> 0x00af }
            goto L_0x00b4
        L_0x00af:
            r0 = move-exception
            r6 = r0
            r6.printStackTrace()
        L_0x00b4:
            r16 = r7
            r7 = r4
            r4 = r16
        L_0x00b9:
            r0 = 2
            r6 = 1
            r14 = 3
            if (r4 == 0) goto L_0x00f5
            java.lang.String r15 = "true"
            boolean r15 = r4.equals(r15)
            if (r15 != 0) goto L_0x00eb
            java.lang.String r15 = "false"
            boolean r15 = r4.equals(r15)
            if (r15 == 0) goto L_0x00d0
            goto L_0x00eb
        L_0x00d0:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.Object[] r3 = new java.lang.Object[r14]
            java.lang.String r4 = "http.lenient"
            r3[r5] = r4
            java.lang.String r4 = "true"
            r3[r6] = r4
            java.lang.String r4 = "false"
            r3[r0] = r4
            java.lang.String r0 = "the value of '%s' must be '%s' or '%s'"
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r0, r3)
            r2.<init>(r0)
            throw r2
        L_0x00eb:
            java.lang.String r15 = "true"
            boolean r4 = r4.equals(r15)
            r1.httpLenient = r4
            goto L_0x00f7
        L_0x00f5:
            r1.httpLenient = r5
        L_0x00f7:
            if (r8 == 0) goto L_0x0130
            java.lang.String r4 = "true"
            boolean r4 = r8.equals(r4)
            if (r4 != 0) goto L_0x0126
            java.lang.String r4 = "false"
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L_0x010b
            goto L_0x0126
        L_0x010b:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.Object[] r3 = new java.lang.Object[r14]
            java.lang.String r4 = "process.non-separate"
            r3[r5] = r4
            java.lang.String r4 = "true"
            r3[r6] = r4
            java.lang.String r4 = "false"
            r3[r0] = r4
            java.lang.String r0 = "the value of '%s' must be '%s' or '%s'"
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r0, r3)
            r2.<init>(r0)
            throw r2
        L_0x0126:
            java.lang.String r4 = "true"
            boolean r4 = r8.equals(r4)
            r1.processNonSeparate = r4
            goto L_0x0132
        L_0x0130:
            r1.processNonSeparate = r5
        L_0x0132:
            if (r9 == 0) goto L_0x0143
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)
            int r4 = r4.intValue()
            int r4 = java.lang.Math.max(r5, r4)
            r1.downloadMinProgressStep = r4
            goto L_0x0147
        L_0x0143:
            r4 = 65536(0x10000, float:9.18355E-41)
            r1.downloadMinProgressStep = r4
        L_0x0147:
            if (r10 == 0) goto L_0x015b
            java.lang.Long r4 = java.lang.Long.valueOf(r10)
            long r8 = r4.longValue()
            r4 = r7
            r6 = 0
            long r6 = java.lang.Math.max(r6, r8)
            r1.downloadMinProgressTime = r6
            goto L_0x0160
        L_0x015b:
            r4 = r7
            r6 = 2000(0x7d0, double:9.88E-321)
            r1.downloadMinProgressTime = r6
        L_0x0160:
            if (r11 == 0) goto L_0x0171
            java.lang.Integer r6 = java.lang.Integer.valueOf(r11)
            int r6 = r6.intValue()
            int r6 = getValidNetworkThreadCount(r6)
            r1.downloadMaxNetworkThreadCount = r6
            goto L_0x0173
        L_0x0171:
            r1.downloadMaxNetworkThreadCount = r14
        L_0x0173:
            if (r12 == 0) goto L_0x01ad
            java.lang.String r6 = "true"
            boolean r6 = r12.equals(r6)
            if (r6 != 0) goto L_0x01a3
            java.lang.String r6 = "false"
            boolean r6 = r12.equals(r6)
            if (r6 == 0) goto L_0x0187
            goto L_0x01a3
        L_0x0187:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.Object[] r3 = new java.lang.Object[r14]
            java.lang.String r4 = "file.non-pre-allocation"
            r3[r5] = r4
            java.lang.String r4 = "true"
            r5 = 1
            r3[r5] = r4
            java.lang.String r4 = "false"
            r3[r0] = r4
            java.lang.String r0 = "the value of '%s' must be '%s' or '%s'"
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r0, r3)
            r2.<init>(r0)
            throw r2
        L_0x01a3:
            java.lang.String r6 = "true"
            boolean r6 = r12.equals(r6)
            r1.fileNonPreAllocation = r6
            goto L_0x01af
        L_0x01ad:
            r1.fileNonPreAllocation = r5
        L_0x01af:
            if (r13 == 0) goto L_0x01e9
            java.lang.String r6 = "true"
            boolean r6 = r13.equals(r6)
            if (r6 != 0) goto L_0x01df
            java.lang.String r6 = "false"
            boolean r6 = r13.equals(r6)
            if (r6 == 0) goto L_0x01c3
            goto L_0x01df
        L_0x01c3:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.Object[] r3 = new java.lang.Object[r14]
            java.lang.String r4 = "broadcast.completed"
            r3[r5] = r4
            java.lang.String r4 = "true"
            r5 = 1
            r3[r5] = r4
            java.lang.String r4 = "false"
            r3[r0] = r4
            java.lang.String r0 = "the value of '%s' must be '%s' or '%s'"
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r0, r3)
            r2.<init>(r0)
            throw r2
        L_0x01df:
            java.lang.String r6 = "true"
            boolean r6 = r13.equals(r6)
            r1.broadcastCompleted = r6
            goto L_0x01eb
        L_0x01e9:
            r1.broadcastCompleted = r5
        L_0x01eb:
            if (r4 == 0) goto L_0x0225
            java.lang.String r6 = "true"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x021b
            java.lang.String r6 = "false"
            boolean r6 = r4.equals(r6)
            if (r6 == 0) goto L_0x01ff
            goto L_0x021b
        L_0x01ff:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.Object[] r3 = new java.lang.Object[r14]
            java.lang.String r4 = "download.trial-connection-head-method"
            r3[r5] = r4
            java.lang.String r4 = "true"
            r5 = 1
            r3[r5] = r4
            java.lang.String r4 = "false"
            r3[r0] = r4
            java.lang.String r0 = "the value of '%s' must be '%s' or '%s'"
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r0, r3)
            r2.<init>(r0)
            throw r2
        L_0x021b:
            java.lang.String r6 = "true"
            boolean r4 = r4.equals(r6)
            r1.trialConnectionHeadMethod = r4
            goto L_0x0227
        L_0x0225:
            r1.trialConnectionHeadMethod = r5
        L_0x0227:
            boolean r4 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG
            if (r4 == 0) goto L_0x02b8
            java.lang.Class<com.liulishuo.filedownloader.util.FileDownloadProperties> r4 = com.liulishuo.filedownloader.util.FileDownloadProperties.class
            java.lang.String r6 = "init properties %d\n load properties: %s=%B; %s=%B; %s=%d; %s=%d; %s=%d; %s=%B; %s=%B; %s=%B"
            r7 = 17
            java.lang.Object[] r7 = new java.lang.Object[r7]
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = r8 - r2
            java.lang.Long r2 = java.lang.Long.valueOf(r8)
            r7[r5] = r2
            java.lang.String r2 = "http.lenient"
            r3 = 1
            r7[r3] = r2
            boolean r2 = r1.httpLenient
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r7[r0] = r2
            java.lang.String r0 = "process.non-separate"
            r7[r14] = r0
            r0 = 4
            boolean r2 = r1.processNonSeparate
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r7[r0] = r2
            r0 = 5
            java.lang.String r2 = "download.min-progress-step"
            r7[r0] = r2
            r0 = 6
            int r2 = r1.downloadMinProgressStep
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7[r0] = r2
            r0 = 7
            java.lang.String r2 = "download.min-progress-time"
            r7[r0] = r2
            r0 = 8
            long r2 = r1.downloadMinProgressTime
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r7[r0] = r2
            r0 = 9
            java.lang.String r2 = "download.max-network-thread-count"
            r7[r0] = r2
            r0 = 10
            int r2 = r1.downloadMaxNetworkThreadCount
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7[r0] = r2
            r0 = 11
            java.lang.String r2 = "file.non-pre-allocation"
            r7[r0] = r2
            r0 = 12
            boolean r2 = r1.fileNonPreAllocation
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r7[r0] = r2
            r0 = 13
            java.lang.String r2 = "broadcast.completed"
            r7[r0] = r2
            r0 = 14
            boolean r2 = r1.broadcastCompleted
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r7[r0] = r2
            r0 = 15
            java.lang.String r2 = "download.trial-connection-head-method"
            r7[r0] = r2
            r0 = 16
            boolean r2 = r1.trialConnectionHeadMethod
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r7[r0] = r2
            com.liulishuo.filedownloader.util.FileDownloadLog.i(r4, r6, r7)
        L_0x02b8:
            return
        L_0x02b9:
            r0 = move-exception
            r2 = r0
        L_0x02bb:
            if (r6 == 0) goto L_0x02c6
            r6.close()     // Catch:{ IOException -> 0x02c1 }
            goto L_0x02c6
        L_0x02c1:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x02c6:
            throw r2
        L_0x02c7:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "Please invoke the 'FileDownloader#setup' before using FileDownloader. If you want to register some components on FileDownloader please invoke the 'FileDownloader#setupOnApplicationOnCreate' on the 'Application#onCreate' first."
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.util.FileDownloadProperties.<init>():void");
    }

    public static int getValidNetworkThreadCount(int i) {
        if (i > 12) {
            FileDownloadLog.w(FileDownloadProperties.class, "require the count of network thread  is %d, what is more than the max valid count(%d), so adjust to %d auto", Integer.valueOf(i), Integer.valueOf(12), Integer.valueOf(12));
            return 12;
        } else if (i >= 1) {
            return i;
        } else {
            FileDownloadLog.w(FileDownloadProperties.class, "require the count of network thread  is %d, what is less than the min valid count(%d), so adjust to %d auto", Integer.valueOf(i), Integer.valueOf(1), Integer.valueOf(1));
            return 1;
        }
    }
}
