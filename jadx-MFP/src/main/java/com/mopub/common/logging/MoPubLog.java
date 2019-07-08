package com.mopub.common.logging;

import android.support.annotation.NonNull;
import android.util.Log;
import com.mopub.common.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MoPubLog {
    private static final Logger LOGGER = Logger.getLogger(LOGGER_NAMESPACE);
    public static final String LOGGER_NAMESPACE = "com.mopub";
    private static final MoPubLogHandler LOG_HANDLER = new MoPubLogHandler();

    private static final class MoPubLogHandler extends Handler {
        private static final Map<Level, Integer> LEVEL_TO_LOG = new HashMap(7);

        public void close() {
        }

        public void flush() {
        }

        private MoPubLogHandler() {
        }

        static {
            LEVEL_TO_LOG.put(Level.FINEST, Integer.valueOf(2));
            LEVEL_TO_LOG.put(Level.FINER, Integer.valueOf(2));
            LEVEL_TO_LOG.put(Level.FINE, Integer.valueOf(2));
            LEVEL_TO_LOG.put(Level.CONFIG, Integer.valueOf(3));
            LEVEL_TO_LOG.put(Level.INFO, Integer.valueOf(4));
            LEVEL_TO_LOG.put(Level.WARNING, Integer.valueOf(5));
            LEVEL_TO_LOG.put(Level.SEVERE, Integer.valueOf(6));
        }

        public void publish(LogRecord logRecord) {
            if (isLoggable(logRecord)) {
                int intValue = LEVEL_TO_LOG.containsKey(logRecord.getLevel()) ? ((Integer) LEVEL_TO_LOG.get(logRecord.getLevel())).intValue() : 2;
                StringBuilder sb = new StringBuilder();
                sb.append(logRecord.getMessage());
                sb.append("\n");
                String sb2 = sb.toString();
                Throwable thrown = logRecord.getThrown();
                if (thrown != null) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(sb2);
                    sb3.append(Log.getStackTraceString(thrown));
                    sb2 = sb3.toString();
                }
                Log.println(intValue, "MoPub", sb2);
            }
        }
    }

    static {
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.ALL);
        LOG_HANDLER.setLevel(Level.FINE);
        LogManager.getLogManager().addLogger(LOGGER);
        addHandler(LOGGER, LOG_HANDLER);
    }

    private MoPubLog() {
    }

    public static void c(String str) {
        c(str, null);
    }

    public static void v(String str) {
        v(str, null);
    }

    public static void d(String str) {
        d(str, null);
    }

    public static void i(String str) {
        i(str, null);
    }

    public static void w(String str) {
        w(str, null);
    }

    public static void e(String str) {
        e(str, null);
    }

    public static void c(String str, Throwable th) {
        LOGGER.log(Level.FINEST, str, th);
    }

    public static void v(String str, Throwable th) {
        LOGGER.log(Level.FINE, str, th);
    }

    public static void d(String str, Throwable th) {
        LOGGER.log(Level.CONFIG, str, th);
    }

    public static void i(String str, Throwable th) {
        LOGGER.log(Level.INFO, str, th);
    }

    public static void w(String str, Throwable th) {
        LOGGER.log(Level.WARNING, str, th);
    }

    public static void e(String str, Throwable th) {
        LOGGER.log(Level.SEVERE, str, th);
    }

    @VisibleForTesting
    public static void setSdkHandlerLevel(@NonNull Level level) {
        LOG_HANDLER.setLevel(level);
    }

    private static void addHandler(@NonNull Logger logger, @NonNull Handler handler) {
        Handler[] handlers = logger.getHandlers();
        int length = handlers.length;
        int i = 0;
        while (i < length) {
            if (!handlers[i].equals(handler)) {
                i++;
            } else {
                return;
            }
        }
        logger.addHandler(handler);
    }
}
