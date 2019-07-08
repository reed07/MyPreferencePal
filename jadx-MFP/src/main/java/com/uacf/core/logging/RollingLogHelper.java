package com.uacf.core.logging;

import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class RollingLogHelper {
    private String appName;
    private String currentFilePath;
    private SimpleDateFormat dateFormat;
    private String dirPath;
    private boolean isFSLib;
    private long maxFileSize;
    private int maxNumOfLogs;
    private ExecutorService writeToLogExecutor;

    public interface RollingLogCallback {
    }

    public synchronized void writeToLog(final String str) {
        this.writeToLogExecutor.execute(new Runnable() {
            public void run() {
                RollingLogHelper.this.appendToLogFile(str);
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void appendToLogFile(String str) {
        if (Strings.isEmpty(this.currentFilePath)) {
            createNewLogFile();
        } else {
            determineIfFileShouldRoll();
        }
        FileUtils.appendToFile(this.currentFilePath, str);
    }

    private void determineIfFileShouldRoll() {
        if (new File(this.currentFilePath).length() >= this.maxFileSize) {
            createNewLogFile();
        }
        List filesSortedByModifiedTime = FileUtils.getFilesSortedByModifiedTime(new File(this.dirPath));
        if (!CollectionUtils.isEmpty((Collection<?>) filesSortedByModifiedTime) && filesSortedByModifiedTime.size() > this.maxNumOfLogs) {
            try {
                FileUtils.forceDelete((File) filesSortedByModifiedTime.get(0));
            } catch (IOException e) {
                Ln.e(e);
            }
        }
    }

    private void createNewLogFile() {
        String str;
        String format = this.dateFormat.format(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        if (this.isFSLib) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.appName);
            sb.append("_");
            str = sb.toString();
        } else {
            str = "";
        }
        File file = new File(this.dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("log_");
        sb2.append(str);
        sb2.append(format);
        sb2.append(".txt");
        File file2 = new File(file, sb2.toString());
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                Ln.e(e);
            }
        }
        this.currentFilePath = file2.getAbsolutePath();
    }
}
