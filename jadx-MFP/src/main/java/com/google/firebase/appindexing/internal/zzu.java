package com.google.firebase.appindexing.internal;

import android.util.Log;
import com.google.firebase.appindexing.FirebaseAppIndex;

public final class zzu {
    public static int zzr(String str) {
        if (isLoggable(3)) {
            return Log.d(FirebaseAppIndex.APP_INDEXING_API_TAG, str);
        }
        return 0;
    }

    public static boolean isLoggable(int i) {
        if (Log.isLoggable(FirebaseAppIndex.APP_INDEXING_API_TAG, i)) {
            return true;
        }
        return Log.isLoggable(FirebaseAppIndex.APP_INDEXING_API_TAG, i);
    }
}
