package com.myfitnesspal.shared.service.premium;

import android.content.Context;
import com.uacf.core.logging.PrivateFilePrinter;
import java.io.File;
import java.util.MissingFormatArgumentException;

public class PaymentsLogger {
    private static final String LOG_DIRECTORY_NAME = ".payment_logs";
    private static PrivateFilePrinter filePrinter;

    public static synchronized void init(Context context) {
        synchronized (PaymentsLogger.class) {
            if (filePrinter == null) {
                filePrinter = new PrivateFilePrinter(getLogDirectory(context), true);
                filePrinter.setEnabled(true);
            }
        }
    }

    public static File getLogDirectory(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().getAbsolutePath());
        sb.append(File.separator);
        sb.append(LOG_DIRECTORY_NAME);
        return new File(sb.toString());
    }

    public static void d(String str, Object... objArr) {
        printLogs(3, str, objArr);
    }

    public static void e(String str, Object... objArr) {
        printLogs(6, str, objArr);
    }

    private static void printLogs(int i, String str, Object... objArr) {
        String str2;
        if (filePrinter != null) {
            try {
                str2 = String.format(str, objArr);
            } catch (MissingFormatArgumentException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("\nThis log message has missing arguments.");
                str2 = sb.toString();
            }
            filePrinter.println(i, str2);
        }
    }
}
