package com.uacf.core.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.uacf.core.util.Ln;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LoggingSQLiteDatabaseWrapper extends SQLiteDatabaseWrapper {
    /* access modifiers changed from: private */
    public AtomicInteger cursorAccessMainThreadCount = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public AtomicLong cursorAccessMainThreadDuration = new AtomicLong(0);
    private Runnable dumpComponents = new Runnable() {
        public void run() {
            synchronized (LoggingSQLiteDatabaseWrapper.this) {
                LoggingSQLiteDatabaseWrapper.debug("-- QUERY DUMP ---------------------------------------------------", new Object[0]);
                LoggingSQLiteDatabaseWrapper.debug("file: %s", LoggingSQLiteDatabaseWrapper.this.getDb().getPath());
                if (LoggingSQLiteDatabaseWrapper.this.queriesByComponent.size() > 0) {
                    LoggingSQLiteDatabaseWrapper.debug("", new Object[0]);
                    for (String str : LoggingSQLiteDatabaseWrapper.this.queriesByComponent.keySet()) {
                        LoggingSQLiteDatabaseWrapper.debug("%s => %s", LoggingSQLiteDatabaseWrapper.this.queriesByComponent.get(str), str);
                    }
                }
                LoggingSQLiteDatabaseWrapper.debug("", new Object[0]);
                LoggingSQLiteDatabaseWrapper.debug("total queries on main thread (all components) = %s", Integer.valueOf(LoggingSQLiteDatabaseWrapper.this.mainThreadQueries.get()));
                LoggingSQLiteDatabaseWrapper.debug("total query duration (ms) on main thread (all components) = %s", Long.valueOf(LoggingSQLiteDatabaseWrapper.this.mainThreadDuration.get()));
                LoggingSQLiteDatabaseWrapper.debug("", new Object[0]);
                LoggingSQLiteDatabaseWrapper.debug("total query count (sum of all components) = %s", Integer.valueOf(LoggingSQLiteDatabaseWrapper.this.totalQueries.get()));
                LoggingSQLiteDatabaseWrapper.debug("total query duration (sum of all query times) = %s", Long.valueOf(LoggingSQLiteDatabaseWrapper.this.totalDuration.get()));
                LoggingSQLiteDatabaseWrapper.debug("", new Object[0]);
                LoggingSQLiteDatabaseWrapper.debug("total Cursor access ON MAIN THREAD = %s", Integer.valueOf(LoggingSQLiteDatabaseWrapper.this.cursorAccessMainThreadCount.get()));
                LoggingSQLiteDatabaseWrapper.debug("total Cursor access time ON MAIN THREAD = %s", Long.valueOf(LoggingSQLiteDatabaseWrapper.this.cursorAccessMainThreadDuration.get()));
                LoggingSQLiteDatabaseWrapper.debug("total Cursor access time = %s", Long.valueOf(LoggingSQLiteDatabaseWrapper.this.totalCursorAccessDuration.get()));
                LoggingSQLiteDatabaseWrapper.debug("-----------------------------------------------------------------", new Object[0]);
            }
        }
    };
    private Handler handler = null;
    /* access modifiers changed from: private */
    public AtomicLong mainThreadDuration = new AtomicLong(0);
    /* access modifiers changed from: private */
    public AtomicInteger mainThreadQueries = new AtomicInteger(0);
    private AtomicInteger nextId = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public Map<String, Integer> queriesByComponent = new ConcurrentHashMap();
    private Map<Integer, Long> timers = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public AtomicLong totalCursorAccessDuration = new AtomicLong(0);
    /* access modifiers changed from: private */
    public AtomicLong totalDuration = new AtomicLong(0);
    /* access modifiers changed from: private */
    public AtomicInteger totalQueries = new AtomicInteger(0);

    private class LoggingSQLiteCursor extends CursorWrapper {
        final long start = SystemClock.uptimeMillis();

        public LoggingSQLiteCursor(Cursor cursor) {
            super(cursor);
        }

        public void close() {
            super.close();
            long max = Math.max(1, SystemClock.uptimeMillis() - this.start);
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                LoggingSQLiteDatabaseWrapper.this.cursorAccessMainThreadDuration.addAndGet(max);
                LoggingSQLiteDatabaseWrapper.this.cursorAccessMainThreadCount.incrementAndGet();
            }
            LoggingSQLiteDatabaseWrapper.this.totalCursorAccessDuration.addAndGet(max);
        }
    }

    public LoggingSQLiteDatabaseWrapper(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public Cursor query(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        int startTimer = startTimer();
        Cursor wrap = wrap(super.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6));
        endTimerAndLog(startTimer);
        return wrap;
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        int startTimer = startTimer();
        Cursor wrap = wrap(super.query(str, strArr, str2, strArr2, str3, str4, str5));
        endTimerAndLog(startTimer);
        return wrap;
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        int startTimer = startTimer();
        Cursor wrap = wrap(super.query(str, strArr, str2, strArr2, str3, str4, str5, str6));
        endTimerAndLog(startTimer);
        return wrap;
    }

    public Cursor rawQuery(String str, String[] strArr) {
        int startTimer = startTimer();
        Cursor wrap = wrap(super.rawQuery(str, strArr));
        endTimerAndLog(startTimer);
        return wrap;
    }

    public long insert(String str, String str2, ContentValues contentValues) {
        int startTimer = startTimer();
        long insert = super.insert(str, str2, contentValues);
        endTimerAndLog(startTimer);
        return insert;
    }

    public long insertOrThrow(String str, String str2, ContentValues contentValues) throws SQLException {
        int startTimer = startTimer();
        long insertOrThrow = super.insertOrThrow(str, str2, contentValues);
        endTimerAndLog(startTimer);
        return insertOrThrow;
    }

    public long insertWithOnConflict(String str, String str2, ContentValues contentValues, int i) {
        int startTimer = startTimer();
        long insertWithOnConflict = super.insertWithOnConflict(str, str2, contentValues, i);
        endTimerAndLog(startTimer);
        return insertWithOnConflict;
    }

    public int delete(String str, String str2, String[] strArr) {
        int startTimer = startTimer();
        int delete = super.delete(str, str2, strArr);
        endTimerAndLog(startTimer);
        return delete;
    }

    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        int startTimer = startTimer();
        int update = super.update(str, contentValues, str2, strArr);
        endTimerAndLog(startTimer);
        return update;
    }

    public void execSQL(String str) throws SQLException {
        int startTimer = startTimer();
        super.execSQL(str);
        endTimerAndLog(startTimer);
    }

    public void execSQL(String str, Object[] objArr) throws SQLException {
        int startTimer = startTimer();
        super.execSQL(str, objArr);
        endTimerAndLog(startTimer);
    }

    private int startTimer() {
        int incrementAndGet = this.nextId.incrementAndGet();
        this.timers.put(Integer.valueOf(incrementAndGet), Long.valueOf(SystemClock.uptimeMillis()));
        return incrementAndGet;
    }

    private Cursor wrap(Cursor cursor) {
        return new LoggingSQLiteCursor(cursor);
    }

    private static String matchComponent(String str, String str2, String str3) {
        if (str != null) {
            return str;
        }
        if (str2 == null || str3 == null || !str2.contains(str3)) {
            return null;
        }
        return str2;
    }

    private String incrementComponentQueryCount(String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                if (this.queriesByComponent.containsKey(str)) {
                    Map<String, Integer> map = this.queriesByComponent;
                    map.put(str, Integer.valueOf(((Integer) map.get(str)).intValue() + 1));
                } else {
                    this.queriesByComponent.put(str, Integer.valueOf(1));
                }
                return str;
            }
        }
        return null;
    }

    private synchronized void endTimerAndLog(int i) {
        boolean z = Thread.currentThread() == Looper.getMainLooper().getThread();
        scheduleComponentDump();
        this.totalQueries.incrementAndGet();
        long max = Math.max(1, SystemClock.uptimeMillis() - ((Long) this.timers.get(Integer.valueOf(i))).longValue());
        this.totalDuration.addAndGet(max);
        if (z) {
            debug("-- MAIN THREAD QUERY --------------------------------------------", new Object[0]);
            debug("file = %s", getDb().getPath());
            this.mainThreadQueries.incrementAndGet();
            this.mainThreadDuration.addAndGet(max);
            String str = null;
            String str2 = null;
            String str3 = null;
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                String stackTraceElement2 = stackTraceElement.toString();
                str = matchComponent(str, stackTraceElement2, ".ui.");
                str2 = matchComponent(str2, stackTraceElement2, ".service.");
                str3 = matchComponent(str3, stackTraceElement2, ".adapter.");
            }
            String incrementComponentQueryCount = incrementComponentQueryCount(str, str2, str3);
            if (incrementComponentQueryCount != null) {
                debug("component = %s", incrementComponentQueryCount);
                debug("query count for this component = %s", this.queriesByComponent.get(incrementComponentQueryCount));
            }
            debug("this query's duration (ms) = %s", Long.valueOf(max));
            debug("-----------------------------------------------------------------", new Object[0]);
        }
        this.timers.remove(Integer.valueOf(i));
    }

    private void scheduleComponentDump() {
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper());
        }
        this.handler.removeCallbacks(this.dumpComponents);
        this.handler.postDelayed(this.dumpComponents, 2500);
    }

    /* access modifiers changed from: private */
    public static void debug(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("DB_QUERY_MONITOR: ");
        sb.append(str);
        Ln.d(sb.toString(), objArr);
    }
}
