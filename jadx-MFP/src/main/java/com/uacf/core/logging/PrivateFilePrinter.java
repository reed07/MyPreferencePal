package com.uacf.core.logging;

import android.util.Log;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.myfitnesspal.shared.constants.Constants.ABTest.SmartWaterPhase1;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PrivateFilePrinter extends PrinterBase {
    /* access modifiers changed from: private */
    public File currentLogFile;
    private final boolean debugOutput;
    /* access modifiers changed from: private */
    public final SimpleDateFormat formatter = Format.newIso8601DateTimeFormat();
    private final File logsDir;
    /* access modifiers changed from: private */
    public final BlockingQueue<QueueElement> messageQueue;
    /* access modifiers changed from: private */
    public PrintStream outputStream;
    private final Runnable threadRunnable = new Runnable() {
        public void run() {
            String str;
            PrivateFilePrinter.this.recreateOutputStream(false);
            if (PrivateFilePrinter.this.outputStream == null || PrivateFilePrinter.this.currentLogFile == null) {
                Ln.e("unable to open output stream for log file!", new Object[0]);
                PrivateFilePrinter.this.setEnabled(false);
                return;
            }
            while (true) {
                try {
                    PrivateFilePrinter.this.rotateLogsIfNecessary();
                    boolean z = false;
                    while (true) {
                        QueueElement queueElement = (QueueElement) PrivateFilePrinter.this.messageQueue.poll(30, TimeUnit.SECONDS);
                        if (queueElement != null) {
                            if (queueElement == QueueElement.CLEAR_LOGS_NOW) {
                                PrivateFilePrinter.this.logd("MFPLOGS: Clearing files", new Object[0]);
                                PrivateFilePrinter.this.cleanUpFiles(true);
                                PrivateFilePrinter.this.truncateOutputStream();
                            } else {
                                switch (queueElement.getPriority()) {
                                    case 2:
                                        str = "V";
                                        break;
                                    case 3:
                                        str = "D";
                                        break;
                                    case 4:
                                        str = "I";
                                        break;
                                    case 5:
                                        str = Attributes.WEDNESDAY;
                                        break;
                                    case 6:
                                        str = "E";
                                        break;
                                    case 7:
                                        str = SmartWaterPhase1.SMART_WATER_BRANDING_ON_VARIANT;
                                        break;
                                    default:
                                        str = "?";
                                        break;
                                }
                                String format = String.format("%s/%-25s %-40s %-40s %s", new Object[]{str, PrivateFilePrinter.this.formatter.format(queueElement.getTimestamp()), String.format("%s:%s", new Object[]{queueElement.getFileName(), Integer.valueOf(queueElement.getLineNumber())}), Strings.toString(queueElement.getThreadName()), Strings.toString(queueElement.getMesssage())});
                                PrivateFilePrinter.this.logd("MFPLOGS: writing %s", format);
                                PrivateFilePrinter.this.outputStream.println(format);
                                z = true;
                            }
                        } else if (z) {
                            PrivateFilePrinter.this.logd("MFPLOGS: Flush", new Object[0]);
                            PrivateFilePrinter.this.outputStream.flush();
                        }
                    }
                } catch (InterruptedException unused) {
                    PrivateFilePrinter.this.logd("MFPLOGS: INTERRUPTED!", new Object[0]);
                    PrivateFilePrinter.this.closeOutputStream();
                    PrivateFilePrinter.this.messageQueue.clear();
                    return;
                }
            }
        }
    };

    private static final class QueueElement {
        public static QueueElement CLEAR_LOGS_NOW;
        private final String fileName;
        private final int lineNumber;
        private final String messsage;
        private final int priority;
        private final String threadName;
        private final Date timestamp;

        static {
            QueueElement queueElement = new QueueElement(-1, null, null, 0, null, null);
            CLEAR_LOGS_NOW = queueElement;
        }

        public QueueElement(int i, Date date, String str, int i2, String str2, String str3) {
            this.priority = i;
            this.timestamp = date;
            this.fileName = str;
            this.lineNumber = i2;
            this.threadName = str2;
            this.messsage = str3;
        }

        public int getPriority() {
            return this.priority;
        }

        public Date getTimestamp() {
            return this.timestamp;
        }

        public String getFileName() {
            return this.fileName;
        }

        public int getLineNumber() {
            return this.lineNumber;
        }

        public String getThreadName() {
            return this.threadName;
        }

        public String getMesssage() {
            return this.messsage;
        }
    }

    public PrivateFilePrinter(File file, boolean z) {
        this.formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.logsDir = file;
        this.debugOutput = z;
        this.currentLogFile = getCurrentLogFile();
        this.messageQueue = new LinkedBlockingQueue();
        new Thread(this.threadRunnable).start();
    }

    public int println(int i, String str) {
        if (!this.enabled) {
            return 0;
        }
        Thread currentThread = Thread.currentThread();
        StackTraceElement stackTraceElement = currentThread.getStackTrace()[5];
        BlockingQueue<QueueElement> blockingQueue = this.messageQueue;
        QueueElement queueElement = new QueueElement(i, new Date(), stackTraceElement.getFileName(), stackTraceElement.getLineNumber(), currentThread.getName(), str);
        blockingQueue.offer(queueElement);
        return 0;
    }

    public void clear() {
        this.messageQueue.offer(QueueElement.CLEAR_LOGS_NOW);
    }

    /* access modifiers changed from: private */
    public void recreateOutputStream(boolean z) {
        closeOutputStream();
        try {
            this.currentLogFile.getParentFile().mkdirs();
            this.outputStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(this.currentLogFile, !z)));
        } catch (IOException e) {
            Ln.e(e);
            this.outputStream = null;
        }
    }

    /* access modifiers changed from: private */
    public void truncateOutputStream() {
        closeOutputStream();
        try {
            new FileWriter(this.currentLogFile, false).close();
        } catch (IOException e) {
            Log.e("MFPLOGS", "Error truncating file", e);
        }
        recreateOutputStream(false);
    }

    /* access modifiers changed from: private */
    public void closeOutputStream() {
        PrintStream printStream = this.outputStream;
        if (printStream != null) {
            printStream.flush();
            this.outputStream.close();
            this.outputStream = null;
        }
    }

    /* access modifiers changed from: private */
    public void cleanUpFiles(boolean z) {
        List allLogFiles = getAllLogFiles();
        String absolutePath = this.currentLogFile.getAbsolutePath();
        boolean z2 = false;
        for (int size = CollectionUtils.size((Collection<?>) allLogFiles) - 1; size >= 0; size--) {
            File file = (File) allLogFiles.get(size);
            if (Strings.equalsIgnoreCase(file.getAbsolutePath(), absolutePath)) {
                logd("MFPLOGS: found current file, keeping %s", file.getName());
            } else if (z || z2) {
                logd("MFPLOGS: deleting %s", file.getName());
                file.delete();
            } else {
                logd("MFPLOGS: keeping %s", file.getName());
                z2 = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void rotateLogsIfNecessary() {
        long length = this.currentLogFile.length();
        if (length > 1048576) {
            logd("MFPLOGS: current log %s has size %s > max size %s; create new file", this.currentLogFile.getName(), Long.valueOf(length), Integer.valueOf(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES));
            this.currentLogFile = createNewLogFile();
            cleanUpFiles(false);
            recreateOutputStream(false);
        }
    }

    private File getCurrentLogFile() {
        List allLogFiles = getAllLogFiles();
        if (CollectionUtils.notEmpty((Collection<?>) allLogFiles)) {
            return (File) allLogFiles.get(0);
        }
        return createNewLogFile();
    }

    private File createNewLogFile() {
        Calendar instance = Calendar.getInstance();
        String format = String.format("mfp-log-%4d%02d%02d-%02d%02d%02d.txt", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2)), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(10)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13))});
        logd("MFPLOGS: Created new log file %s", format);
        return new File(this.logsDir, format);
    }

    private List<File> getAllLogFiles() {
        return FileUtils.getFilesSortedByModifiedTime(this.logsDir);
    }

    /* access modifiers changed from: private */
    public void logd(String str, Object... objArr) {
        if (this.debugOutput) {
            Log.d("", String.format(Strings.toString(str), objArr));
        }
    }
}
