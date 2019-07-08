package com.crashlytics.android.core;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.BackgroundPriorityRunnable;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ReportUploader {
    static final Map<String, String> HEADER_INVALID_CLS_FILE = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    /* access modifiers changed from: private */
    public static final short[] RETRY_INTERVALS = {10, 20, 30, 60, 120, 300};
    private final String apiKey;
    private final CreateReportSpiCall createReportCall;
    private final Object fileAccessLock = new Object();
    /* access modifiers changed from: private */
    public final HandlingExceptionCheck handlingExceptionCheck;
    private final ReportFilesProvider reportFilesProvider;
    /* access modifiers changed from: private */
    public Thread uploadThread;

    static final class AlwaysSendCheck implements SendCheck {
        public boolean canSendReports() {
            return true;
        }

        AlwaysSendCheck() {
        }
    }

    interface HandlingExceptionCheck {
        boolean isHandlingException();
    }

    interface ReportFilesProvider {
        File[] getCompleteSessionFiles();

        File[] getInvalidSessionFiles();

        File[] getNativeReportFiles();
    }

    interface SendCheck {
        boolean canSendReports();
    }

    private class Worker extends BackgroundPriorityRunnable {
        private final float delay;
        private final SendCheck sendCheck;

        Worker(float f, SendCheck sendCheck2) {
            this.delay = f;
            this.sendCheck = sendCheck2;
        }

        public void onRun() {
            try {
                attemptUploadWithRetry();
            } catch (Exception e) {
                Fabric.getLogger().e("CrashlyticsCore", "An unexpected error occurred while attempting to upload crash reports.", e);
            }
            ReportUploader.this.uploadThread = null;
        }

        private void attemptUploadWithRetry() {
            StringBuilder sb = new StringBuilder();
            sb.append("Starting report processing in ");
            sb.append(this.delay);
            sb.append(" second(s)...");
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            float f = this.delay;
            if (f > BitmapDescriptorFactory.HUE_RED) {
                try {
                    Thread.sleep((long) (f * 1000.0f));
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            List<Report> findReports = ReportUploader.this.findReports();
            if (!ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                if (findReports.isEmpty() || this.sendCheck.canSendReports()) {
                    int i = 0;
                    while (!findReports.isEmpty() && !ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Attempting to send ");
                        sb2.append(findReports.size());
                        sb2.append(" report(s)");
                        Fabric.getLogger().d("CrashlyticsCore", sb2.toString());
                        for (Report forceUpload : findReports) {
                            ReportUploader.this.forceUpload(forceUpload);
                        }
                        findReports = ReportUploader.this.findReports();
                        if (!findReports.isEmpty()) {
                            int i2 = i + 1;
                            long j = (long) ReportUploader.RETRY_INTERVALS[Math.min(i, ReportUploader.RETRY_INTERVALS.length - 1)];
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("Report submisson: scheduling delayed retry in ");
                            sb3.append(j);
                            sb3.append(" seconds");
                            Fabric.getLogger().d("CrashlyticsCore", sb3.toString());
                            try {
                                Thread.sleep(j * 1000);
                                i = i2;
                            } catch (InterruptedException unused2) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                    }
                    return;
                }
                StringBuilder sb4 = new StringBuilder();
                sb4.append("User declined to send. Removing ");
                sb4.append(findReports.size());
                sb4.append(" Report(s).");
                Fabric.getLogger().d("CrashlyticsCore", sb4.toString());
                for (Report remove : findReports) {
                    remove.remove();
                }
            }
        }
    }

    public ReportUploader(String str, CreateReportSpiCall createReportSpiCall, ReportFilesProvider reportFilesProvider2, HandlingExceptionCheck handlingExceptionCheck2) {
        if (createReportSpiCall != null) {
            this.createReportCall = createReportSpiCall;
            this.apiKey = str;
            this.reportFilesProvider = reportFilesProvider2;
            this.handlingExceptionCheck = handlingExceptionCheck2;
            return;
        }
        throw new IllegalArgumentException("createReportCall must not be null.");
    }

    public synchronized void uploadReports(float f, SendCheck sendCheck) {
        if (this.uploadThread != null) {
            Fabric.getLogger().d("CrashlyticsCore", "Report upload has already been started.");
            return;
        }
        this.uploadThread = new Thread(new Worker(f, sendCheck), "Crashlytics Report Uploader");
        this.uploadThread.start();
    }

    /* access modifiers changed from: 0000 */
    public boolean forceUpload(Report report) {
        boolean z;
        synchronized (this.fileAccessLock) {
            z = false;
            try {
                boolean invoke = this.createReportCall.invoke(new CreateReportRequest(this.apiKey, report));
                Logger logger = Fabric.getLogger();
                String str = "CrashlyticsCore";
                StringBuilder sb = new StringBuilder();
                sb.append("Crashlytics report upload ");
                sb.append(invoke ? "complete: " : "FAILED: ");
                sb.append(report.getIdentifier());
                logger.i(str, sb.toString());
                if (invoke) {
                    report.remove();
                    z = true;
                }
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Error occurred sending report ");
                sb2.append(report);
                Fabric.getLogger().e("CrashlyticsCore", sb2.toString(), e);
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public List<Report> findReports() {
        File[] completeSessionFiles;
        File[] invalidSessionFiles;
        File[] nativeReportFiles;
        Fabric.getLogger().d("CrashlyticsCore", "Checking for crash reports...");
        synchronized (this.fileAccessLock) {
            completeSessionFiles = this.reportFilesProvider.getCompleteSessionFiles();
            invalidSessionFiles = this.reportFilesProvider.getInvalidSessionFiles();
            nativeReportFiles = this.reportFilesProvider.getNativeReportFiles();
        }
        LinkedList linkedList = new LinkedList();
        if (completeSessionFiles != null) {
            for (File file : completeSessionFiles) {
                StringBuilder sb = new StringBuilder();
                sb.append("Found crash report ");
                sb.append(file.getPath());
                Fabric.getLogger().d("CrashlyticsCore", sb.toString());
                linkedList.add(new SessionReport(file));
            }
        }
        HashMap hashMap = new HashMap();
        if (invalidSessionFiles != null) {
            for (File file2 : invalidSessionFiles) {
                String sessionIdFromSessionFile = CrashlyticsController.getSessionIdFromSessionFile(file2);
                if (!hashMap.containsKey(sessionIdFromSessionFile)) {
                    hashMap.put(sessionIdFromSessionFile, new LinkedList());
                }
                ((List) hashMap.get(sessionIdFromSessionFile)).add(file2);
            }
        }
        for (String str : hashMap.keySet()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Found invalid session: ");
            sb2.append(str);
            Fabric.getLogger().d("CrashlyticsCore", sb2.toString());
            List list = (List) hashMap.get(str);
            linkedList.add(new InvalidSessionReport(str, (File[]) list.toArray(new File[list.size()])));
        }
        if (nativeReportFiles != null) {
            for (File nativeSessionReport : nativeReportFiles) {
                linkedList.add(new NativeSessionReport(nativeSessionReport));
            }
        }
        if (linkedList.isEmpty()) {
            Fabric.getLogger().d("CrashlyticsCore", "No reports found.");
        }
        return linkedList;
    }
}
