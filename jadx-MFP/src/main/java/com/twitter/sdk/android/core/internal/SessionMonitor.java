package com.twitter.sdk.android.core.internal;

import android.app.Activity;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.internal.ActivityLifecycleManager.Callbacks;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;

public class SessionMonitor<T extends Session> {
    private final ExecutorService executorService;
    protected final MonitorState monitorState;
    private final SessionManager<T> sessionManager;
    private final SessionVerifier sessionVerifier;
    private final SystemCurrentTimeProvider time;

    protected static class MonitorState {
        public long lastVerification;
        private final Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        public boolean verifying;

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean beginVerification(long r8) {
            /*
                r7 = this;
                monitor-enter(r7)
                long r0 = r7.lastVerification     // Catch:{ all -> 0x002a }
                long r0 = r8 - r0
                r2 = 21600000(0x1499700, double:1.0671818E-316)
                r4 = 1
                r5 = 0
                int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r6 <= 0) goto L_0x0010
                r0 = 1
                goto L_0x0011
            L_0x0010:
                r0 = 0
            L_0x0011:
                long r1 = r7.lastVerification     // Catch:{ all -> 0x002a }
                boolean r8 = r7.isOnSameDate(r8, r1)     // Catch:{ all -> 0x002a }
                if (r8 != 0) goto L_0x001b
                r8 = 1
                goto L_0x001c
            L_0x001b:
                r8 = 0
            L_0x001c:
                boolean r9 = r7.verifying     // Catch:{ all -> 0x002a }
                if (r9 != 0) goto L_0x0028
                if (r0 != 0) goto L_0x0024
                if (r8 == 0) goto L_0x0028
            L_0x0024:
                r7.verifying = r4     // Catch:{ all -> 0x002a }
                monitor-exit(r7)
                return r4
            L_0x0028:
                monitor-exit(r7)
                return r5
            L_0x002a:
                r8 = move-exception
                monitor-exit(r7)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.twitter.sdk.android.core.internal.SessionMonitor.MonitorState.beginVerification(long):boolean");
        }

        public synchronized void endVerification(long j) {
            this.verifying = false;
            this.lastVerification = j;
        }

        private boolean isOnSameDate(long j, long j2) {
            this.utcCalendar.setTimeInMillis(j);
            int i = this.utcCalendar.get(6);
            int i2 = this.utcCalendar.get(1);
            this.utcCalendar.setTimeInMillis(j2);
            int i3 = this.utcCalendar.get(6);
            int i4 = this.utcCalendar.get(1);
            if (i == i3 && i2 == i4) {
                return true;
            }
            return false;
        }
    }

    public SessionMonitor(SessionManager<T> sessionManager2, ExecutorService executorService2, SessionVerifier<T> sessionVerifier2) {
        this(sessionManager2, new SystemCurrentTimeProvider(), executorService2, new MonitorState(), sessionVerifier2);
    }

    SessionMonitor(SessionManager<T> sessionManager2, SystemCurrentTimeProvider systemCurrentTimeProvider, ExecutorService executorService2, MonitorState monitorState2, SessionVerifier sessionVerifier2) {
        this.time = systemCurrentTimeProvider;
        this.sessionManager = sessionManager2;
        this.executorService = executorService2;
        this.monitorState = monitorState2;
        this.sessionVerifier = sessionVerifier2;
    }

    public void monitorActivityLifecycle(ActivityLifecycleManager activityLifecycleManager) {
        activityLifecycleManager.registerCallbacks(new Callbacks() {
            public void onActivityStarted(Activity activity) {
                SessionMonitor.this.triggerVerificationIfNecessary();
            }
        });
    }

    public void triggerVerificationIfNecessary() {
        if (this.sessionManager.getActiveSession() != null && this.monitorState.beginVerification(this.time.getCurrentTimeMillis())) {
            this.executorService.submit(new Runnable() {
                public void run() {
                    SessionMonitor.this.verifyAll();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void verifyAll() {
        for (Session verifySession : this.sessionManager.getSessionMap().values()) {
            this.sessionVerifier.verifySession(verifySession);
        }
        this.monitorState.endVerification(this.time.getCurrentTimeMillis());
    }
}
