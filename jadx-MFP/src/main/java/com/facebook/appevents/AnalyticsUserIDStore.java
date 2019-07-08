package com.facebook.appevents;

import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.AppEventUtility;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class AnalyticsUserIDStore {
    private static final String ANALYTICS_USER_ID_KEY = "com.facebook.appevents.AnalyticsUserIDStore.userID";
    private static final String TAG = "AnalyticsUserIDStore";
    private static volatile boolean initialized = false;
    /* access modifiers changed from: private */
    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    /* access modifiers changed from: private */
    public static String userID;

    AnalyticsUserIDStore() {
    }

    public static void initStore() {
        if (!initialized) {
            AppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    AnalyticsUserIDStore.initAndWait();
                }
            });
        }
    }

    public static void setUserID(final String str) {
        AppEventUtility.assertIsNotMainThread();
        if (!initialized) {
            Log.w(TAG, "initStore should have been called before calling setUserID");
            initAndWait();
        }
        AppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                AnalyticsUserIDStore.lock.writeLock().lock();
                try {
                    AnalyticsUserIDStore.userID = str;
                    Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
                    edit.putString(AnalyticsUserIDStore.ANALYTICS_USER_ID_KEY, AnalyticsUserIDStore.userID);
                    edit.apply();
                } finally {
                    AnalyticsUserIDStore.lock.writeLock().unlock();
                }
            }
        });
    }

    public static String getUserID() {
        if (!initialized) {
            Log.w(TAG, "initStore should have been called before calling setUserID");
            initAndWait();
        }
        lock.readLock().lock();
        try {
            return userID;
        } finally {
            lock.readLock().unlock();
        }
    }

    /* access modifiers changed from: private */
    public static void initAndWait() {
        if (!initialized) {
            lock.writeLock().lock();
            try {
                if (!initialized) {
                    userID = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).getString(ANALYTICS_USER_ID_KEY, null);
                    initialized = true;
                    lock.writeLock().unlock();
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
