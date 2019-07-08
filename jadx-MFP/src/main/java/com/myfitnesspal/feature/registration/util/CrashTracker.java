package com.myfitnesspal.feature.registration.util;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

public class CrashTracker {
    private static final String CRASH_FILENAME = ".mfpcrashfile";
    private static Context context;
    private static boolean installed;
    private static boolean lastSessionCrashed;
    /* access modifiers changed from: private */
    public static UncaughtExceptionHandler originalHandler;

    private static class ExceptionHandler implements UncaughtExceptionHandler {
        public void uncaughtException(Thread thread, Throwable th) {
            CrashTracker.recordCrash();
            if (CrashTracker.originalHandler != null) {
                CrashTracker.originalHandler.uncaughtException(thread, th);
            }
        }
    }

    public static synchronized void install(Context context2) {
        synchronized (CrashTracker.class) {
            if (!installed) {
                installed = true;
                originalHandler = Thread.getDefaultUncaughtExceptionHandler();
                context = context2.getApplicationContext();
                Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
                readCrash();
            }
        }
    }

    public static synchronized boolean lastSessionCrashed() {
        boolean z;
        synchronized (CrashTracker.class) {
            z = lastSessionCrashed;
        }
        return z;
    }

    private static File getCrashFile() {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().getAbsolutePath());
        sb.append(File.separator);
        sb.append(CRASH_FILENAME);
        return new File(sb.toString());
    }

    /* access modifiers changed from: private */
    public static void recordCrash() {
        try {
            File crashFile = getCrashFile();
            crashFile.getParentFile().mkdirs();
            crashFile.createNewFile();
        } catch (IOException unused) {
        }
    }

    private static void readCrash() {
        File crashFile = getCrashFile();
        lastSessionCrashed = crashFile.exists();
        crashFile.delete();
    }
}
