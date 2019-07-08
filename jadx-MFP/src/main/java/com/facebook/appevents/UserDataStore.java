package com.facebook.appevents;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.AppEventUtility;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDataStore {
    public static final String CITY = "ct";
    public static final String COUNTRY = "country";
    public static final String DATE_OF_BIRTH = "db";
    public static final String EMAIL = "em";
    public static final String FIRST_NAME = "fn";
    public static final String GENDER = "ge";
    public static final String LAST_NAME = "ln";
    public static final String PHONE = "ph";
    public static final String STATE = "st";
    private static final String TAG = "UserDataStore";
    private static final String USER_DATA_KEY = "com.facebook.appevents.UserDataStore.userData";
    public static final String ZIP = "zp";
    /* access modifiers changed from: private */
    public static String hashedUserData;
    private static volatile boolean initialized = false;
    /* access modifiers changed from: private */
    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void initStore() {
        if (!initialized) {
            AppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    UserDataStore.initAndWait();
                }
            });
        }
    }

    public static void setUserDataAndHash(final Bundle bundle) {
        if (!initialized) {
            Log.w(TAG, "initStore should have been called before calling setUserData");
            initAndWait();
        }
        AppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                UserDataStore.lock.writeLock().lock();
                try {
                    UserDataStore.hashedUserData = UserDataStore.hashUserData(bundle);
                    Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
                    edit.putString(UserDataStore.USER_DATA_KEY, UserDataStore.hashedUserData);
                    edit.apply();
                } finally {
                    UserDataStore.lock.writeLock().unlock();
                }
            }
        });
    }

    public static void setUserDataAndHash(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10) {
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString(EMAIL, str);
        }
        if (str2 != null) {
            bundle.putString(FIRST_NAME, str2);
        }
        if (str3 != null) {
            bundle.putString(LAST_NAME, str3);
        }
        if (str4 != null) {
            bundle.putString(PHONE, str4);
        }
        if (str5 != null) {
            bundle.putString(DATE_OF_BIRTH, str5);
        }
        if (str6 != null) {
            bundle.putString(GENDER, str6);
        }
        if (str7 != null) {
            bundle.putString(CITY, str7);
        }
        if (str8 != null) {
            bundle.putString(STATE, str8);
        }
        if (str9 != null) {
            bundle.putString(ZIP, str9);
        }
        if (str10 != null) {
            bundle.putString("country", str10);
        }
        setUserDataAndHash(bundle);
    }

    public static String getHashedUserData() {
        if (!initialized) {
            Log.w(TAG, "initStore should have been called before calling setUserID");
            initAndWait();
        }
        lock.readLock().lock();
        try {
            return hashedUserData;
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
                    hashedUserData = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).getString(USER_DATA_KEY, null);
                    initialized = true;
                    lock.writeLock().unlock();
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    /* access modifiers changed from: private */
    public static String hashUserData(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                String obj = bundle.get(str).toString();
                if (maybeSHA256Hashed(obj)) {
                    jSONObject.put(str, obj.toLowerCase());
                } else {
                    String encryptData = encryptData(normalizeData(str, bundle.get(str).toString()));
                    if (encryptData != null) {
                        jSONObject.put(str, encryptData);
                    }
                }
            } catch (JSONException unused) {
            }
        }
        return jSONObject.toString();
    }

    private static String encryptData(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes());
            return AppEventUtility.bytesToHex(instance.digest());
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String normalizeData(java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.String r0 = ""
            int r1 = r5.hashCode()
            r2 = 3185(0xc71, float:4.463E-42)
            r3 = 1
            r4 = 0
            if (r1 == r2) goto L_0x0070
            r2 = 3240(0xca8, float:4.54E-42)
            if (r1 == r2) goto L_0x0066
            r2 = 3272(0xcc8, float:4.585E-42)
            if (r1 == r2) goto L_0x005c
            r2 = 3294(0xcde, float:4.616E-42)
            if (r1 == r2) goto L_0x0052
            r2 = 3458(0xd82, float:4.846E-42)
            if (r1 == r2) goto L_0x0048
            r2 = 3576(0xdf8, float:5.011E-42)
            if (r1 == r2) goto L_0x003e
            r2 = 3681(0xe61, float:5.158E-42)
            if (r1 == r2) goto L_0x0034
            r2 = 957831062(0x39175796, float:1.443311E-4)
            if (r1 == r2) goto L_0x002a
            goto L_0x007a
        L_0x002a:
            java.lang.String r1 = "country"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x007a
            r5 = 5
            goto L_0x007b
        L_0x0034:
            java.lang.String r1 = "st"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x007a
            r5 = 4
            goto L_0x007b
        L_0x003e:
            java.lang.String r1 = "ph"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x007a
            r5 = 6
            goto L_0x007b
        L_0x0048:
            java.lang.String r1 = "ln"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x007a
            r5 = 2
            goto L_0x007b
        L_0x0052:
            java.lang.String r1 = "ge"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x007a
            r5 = 7
            goto L_0x007b
        L_0x005c:
            java.lang.String r1 = "fn"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x007a
            r5 = 1
            goto L_0x007b
        L_0x0066:
            java.lang.String r1 = "em"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x007a
            r5 = 0
            goto L_0x007b
        L_0x0070:
            java.lang.String r1 = "ct"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x007a
            r5 = 3
            goto L_0x007b
        L_0x007a:
            r5 = -1
        L_0x007b:
            switch(r5) {
                case 0: goto L_0x00a3;
                case 1: goto L_0x00a3;
                case 2: goto L_0x00a3;
                case 3: goto L_0x00a3;
                case 4: goto L_0x00a3;
                case 5: goto L_0x00a3;
                case 6: goto L_0x0096;
                case 7: goto L_0x007f;
                default: goto L_0x007e;
            }
        L_0x007e:
            goto L_0x00ab
        L_0x007f:
            java.lang.String r5 = r6.trim()
            java.lang.String r5 = r5.toLowerCase()
            int r6 = r5.length()
            if (r6 <= 0) goto L_0x0092
            java.lang.String r5 = r5.substring(r4, r3)
            goto L_0x0094
        L_0x0092:
            java.lang.String r5 = ""
        L_0x0094:
            r0 = r5
            goto L_0x00ab
        L_0x0096:
            java.lang.String r5 = r6.trim()
            java.lang.String r6 = "[^0-9]"
            java.lang.String r0 = ""
            java.lang.String r0 = r5.replaceAll(r6, r0)
            goto L_0x00ab
        L_0x00a3:
            java.lang.String r5 = r6.trim()
            java.lang.String r0 = r5.toLowerCase()
        L_0x00ab:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.UserDataStore.normalizeData(java.lang.String, java.lang.String):java.lang.String");
    }

    private static boolean maybeSHA256Hashed(String str) {
        return str.matches("[A-Fa-f0-9]{64}");
    }
}
