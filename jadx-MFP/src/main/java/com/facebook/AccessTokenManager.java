package com.facebook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.AccessToken.AccessTokenRefreshCallback;
import com.facebook.GraphRequest.Callback;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.model.v2.MfpPlatformAppOptions.AppType;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public final class AccessTokenManager {
    public static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    public static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    public static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    private static final String ME_PERMISSIONS_GRAPH_PATH = "me/permissions";
    public static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    public static final String TAG = "AccessTokenManager";
    private static final String TOKEN_EXTEND_GRAPH_PATH = "oauth/access_token";
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    private static volatile AccessTokenManager instance;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private Date lastAttemptedTokenExtendDate = new Date(0);
    private final LocalBroadcastManager localBroadcastManager;
    /* access modifiers changed from: private */
    public AtomicBoolean tokenRefreshInProgress = new AtomicBoolean(false);

    private static class RefreshResult {
        public String accessToken;
        public Long dataAccessExpirationTime;
        public int expiresAt;

        private RefreshResult() {
        }
    }

    AccessTokenManager(LocalBroadcastManager localBroadcastManager2, AccessTokenCache accessTokenCache2) {
        Validate.notNull(localBroadcastManager2, "localBroadcastManager");
        Validate.notNull(accessTokenCache2, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager2;
        this.accessTokenCache = accessTokenCache2;
    }

    static AccessTokenManager getInstance() {
        if (instance == null) {
            synchronized (AccessTokenManager.class) {
                if (instance == null) {
                    instance = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new AccessTokenCache());
                }
            }
        }
        return instance;
    }

    /* access modifiers changed from: 0000 */
    public AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }

    /* access modifiers changed from: 0000 */
    public boolean loadCurrentAccessToken() {
        AccessToken load = this.accessTokenCache.load();
        if (load == null) {
            return false;
        }
        setCurrentAccessToken(load, false);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentAccessToken(AccessToken accessToken) {
        setCurrentAccessToken(accessToken, true);
    }

    private void setCurrentAccessToken(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.currentAccessToken;
        this.currentAccessToken = accessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.accessTokenCache.save(accessToken);
            } else {
                this.accessTokenCache.clear();
                Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
            }
        }
        if (!Utility.areObjectsEqual(accessToken2, accessToken)) {
            sendCurrentAccessTokenChangedBroadcastIntent(accessToken2, accessToken);
            setTokenExpirationBroadcastAlarm();
        }
    }

    /* access modifiers changed from: 0000 */
    public void currentAccessTokenChanged() {
        AccessToken accessToken = this.currentAccessToken;
        sendCurrentAccessTokenChangedBroadcastIntent(accessToken, accessToken);
    }

    private void sendCurrentAccessTokenChangedBroadcastIntent(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(FacebookSdk.getApplicationContext(), CurrentAccessTokenExpirationBroadcastReceiver.class);
        intent.setAction(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
        intent.putExtra(EXTRA_OLD_ACCESS_TOKEN, accessToken);
        intent.putExtra(EXTRA_NEW_ACCESS_TOKEN, accessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    private void setTokenExpirationBroadcastAlarm() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        AccessToken currentAccessToken2 = AccessToken.getCurrentAccessToken();
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (AccessToken.isCurrentAccessTokenActive() && currentAccessToken2.getExpires() != null && alarmManager != null) {
            Intent intent = new Intent(applicationContext, CurrentAccessTokenExpirationBroadcastReceiver.class);
            intent.setAction(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
            alarmManager.set(1, currentAccessToken2.getExpires().getTime(), PendingIntent.getBroadcast(applicationContext, 0, intent, 0));
        }
    }

    /* access modifiers changed from: 0000 */
    public void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            refreshCurrentAccessToken(null);
        }
    }

    private boolean shouldExtendAccessToken() {
        boolean z = false;
        if (this.currentAccessToken == null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (this.currentAccessToken.getSource().canExtendToken() && valueOf.longValue() - this.lastAttemptedTokenExtendDate.getTime() > 3600000 && valueOf.longValue() - this.currentAccessToken.getLastRefresh().getTime() > 86400000) {
            z = true;
        }
        return z;
    }

    private static GraphRequest createGrantedPermissionsRequest(AccessToken accessToken, Callback callback) {
        AccessToken accessToken2 = accessToken;
        GraphRequest graphRequest = new GraphRequest(accessToken2, ME_PERMISSIONS_GRAPH_PATH, new Bundle(), HttpMethod.GET, callback);
        return graphRequest;
    }

    private static GraphRequest createExtendAccessTokenRequest(AccessToken accessToken, Callback callback) {
        Bundle bundle = new Bundle();
        bundle.putString(Http.GRANT_TYPE, "fb_extend_sso_token");
        GraphRequest graphRequest = new GraphRequest(accessToken, TOKEN_EXTEND_GRAPH_PATH, bundle, HttpMethod.GET, callback);
        return graphRequest;
    }

    /* access modifiers changed from: 0000 */
    public void refreshCurrentAccessToken(final AccessTokenRefreshCallback accessTokenRefreshCallback) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    AccessTokenManager.this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void refreshCurrentAccessTokenImpl(AccessTokenRefreshCallback accessTokenRefreshCallback) {
        final AccessToken accessToken = this.currentAccessToken;
        if (accessToken == null) {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
        } else if (!this.tokenRefreshInProgress.compareAndSet(false, true)) {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
            }
        } else {
            this.lastAttemptedTokenExtendDate = new Date();
            final HashSet hashSet = new HashSet();
            final HashSet hashSet2 = new HashSet();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final RefreshResult refreshResult = new RefreshResult();
            GraphRequestBatch graphRequestBatch = new GraphRequestBatch(createGrantedPermissionsRequest(accessToken, new Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("data");
                        if (optJSONArray != null) {
                            atomicBoolean.set(true);
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("permission");
                                    String optString2 = optJSONObject.optString("status");
                                    if (!Utility.isNullOrEmpty(optString) && !Utility.isNullOrEmpty(optString2)) {
                                        String lowerCase = optString2.toLowerCase(Locale.US);
                                        if (lowerCase.equals(AppType.GRANTED)) {
                                            hashSet.add(optString);
                                        } else if (lowerCase.equals("declined")) {
                                            hashSet2.add(optString);
                                        } else {
                                            String str = AccessTokenManager.TAG;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("Unexpected status: ");
                                            sb.append(lowerCase);
                                            Log.w(str, sb.toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }), createExtendAccessTokenRequest(accessToken, new Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null) {
                        refreshResult.accessToken = jSONObject.optString("access_token");
                        refreshResult.expiresAt = jSONObject.optInt("expires_at");
                        refreshResult.dataAccessExpirationTime = Long.valueOf(jSONObject.optLong(AccessToken.DATA_ACCESS_EXPIRATION_TIME));
                    }
                }
            }));
            final AccessTokenRefreshCallback accessTokenRefreshCallback2 = accessTokenRefreshCallback;
            AnonymousClass4 r0 = new GraphRequestBatch.Callback() {
                /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0053=Splitter:B:18:0x0053, B:48:0x00ff=Splitter:B:48:0x00ff} */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onBatchCompleted(com.facebook.GraphRequestBatch r18) {
                    /*
                        r17 = this;
                        r1 = r17
                        r2 = 0
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.getInstance()     // Catch:{ all -> 0x011b }
                        com.facebook.AccessToken r0 = r0.getCurrentAccessToken()     // Catch:{ all -> 0x011b }
                        if (r0 == 0) goto L_0x00ff
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.getInstance()     // Catch:{ all -> 0x011b }
                        com.facebook.AccessToken r0 = r0.getCurrentAccessToken()     // Catch:{ all -> 0x011b }
                        java.lang.String r0 = r0.getUserId()     // Catch:{ all -> 0x011b }
                        com.facebook.AccessToken r4 = r2     // Catch:{ all -> 0x011b }
                        java.lang.String r4 = r4.getUserId()     // Catch:{ all -> 0x011b }
                        if (r0 == r4) goto L_0x0023
                        goto L_0x00ff
                    L_0x0023:
                        java.util.concurrent.atomic.AtomicBoolean r0 = r4     // Catch:{ all -> 0x011b }
                        boolean r0 = r0.get()     // Catch:{ all -> 0x011b }
                        if (r0 != 0) goto L_0x0053
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x011b }
                        java.lang.String r0 = r0.accessToken     // Catch:{ all -> 0x011b }
                        if (r0 != 0) goto L_0x0053
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x011b }
                        int r0 = r0.expiresAt     // Catch:{ all -> 0x011b }
                        if (r0 != 0) goto L_0x0053
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3     // Catch:{ all -> 0x011b }
                        if (r0 == 0) goto L_0x0047
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3     // Catch:{ all -> 0x011b }
                        com.facebook.FacebookException r4 = new com.facebook.FacebookException     // Catch:{ all -> 0x011b }
                        java.lang.String r5 = "Failed to refresh access token"
                        r4.<init>(r5)     // Catch:{ all -> 0x011b }
                        r0.OnTokenRefreshFailed(r4)     // Catch:{ all -> 0x011b }
                    L_0x0047:
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.tokenRefreshInProgress
                        r0.set(r2)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3
                        return
                    L_0x0053:
                        com.facebook.AccessToken r14 = new com.facebook.AccessToken     // Catch:{ all -> 0x011b }
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x011b }
                        java.lang.String r0 = r0.accessToken     // Catch:{ all -> 0x011b }
                        if (r0 == 0) goto L_0x0061
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x011b }
                        java.lang.String r0 = r0.accessToken     // Catch:{ all -> 0x011b }
                        r5 = r0
                        goto L_0x0068
                    L_0x0061:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x011b }
                        java.lang.String r0 = r0.getToken()     // Catch:{ all -> 0x011b }
                        r5 = r0
                    L_0x0068:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x011b }
                        java.lang.String r6 = r0.getApplicationId()     // Catch:{ all -> 0x011b }
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x011b }
                        java.lang.String r7 = r0.getUserId()     // Catch:{ all -> 0x011b }
                        java.util.concurrent.atomic.AtomicBoolean r0 = r4     // Catch:{ all -> 0x011b }
                        boolean r0 = r0.get()     // Catch:{ all -> 0x011b }
                        if (r0 == 0) goto L_0x0080
                        java.util.Set r0 = r6     // Catch:{ all -> 0x011b }
                        r8 = r0
                        goto L_0x0087
                    L_0x0080:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x011b }
                        java.util.Set r0 = r0.getPermissions()     // Catch:{ all -> 0x011b }
                        r8 = r0
                    L_0x0087:
                        java.util.concurrent.atomic.AtomicBoolean r0 = r4     // Catch:{ all -> 0x011b }
                        boolean r0 = r0.get()     // Catch:{ all -> 0x011b }
                        if (r0 == 0) goto L_0x0093
                        java.util.Set r0 = r7     // Catch:{ all -> 0x011b }
                        r9 = r0
                        goto L_0x009a
                    L_0x0093:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x011b }
                        java.util.Set r0 = r0.getDeclinedPermissions()     // Catch:{ all -> 0x011b }
                        r9 = r0
                    L_0x009a:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x011b }
                        com.facebook.AccessTokenSource r10 = r0.getSource()     // Catch:{ all -> 0x011b }
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x011b }
                        int r0 = r0.expiresAt     // Catch:{ all -> 0x011b }
                        r11 = 1000(0x3e8, double:4.94E-321)
                        if (r0 == 0) goto L_0x00b5
                        java.util.Date r0 = new java.util.Date     // Catch:{ all -> 0x011b }
                        com.facebook.AccessTokenManager$RefreshResult r4 = r5     // Catch:{ all -> 0x011b }
                        int r4 = r4.expiresAt     // Catch:{ all -> 0x011b }
                        long r3 = (long) r4     // Catch:{ all -> 0x011b }
                        long r3 = r3 * r11
                        r0.<init>(r3)     // Catch:{ all -> 0x011b }
                        goto L_0x00bb
                    L_0x00b5:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x011b }
                        java.util.Date r0 = r0.getExpires()     // Catch:{ all -> 0x011b }
                    L_0x00bb:
                        java.util.Date r3 = new java.util.Date     // Catch:{ all -> 0x011b }
                        r3.<init>()     // Catch:{ all -> 0x011b }
                        com.facebook.AccessTokenManager$RefreshResult r4 = r5     // Catch:{ all -> 0x011b }
                        java.lang.Long r4 = r4.dataAccessExpirationTime     // Catch:{ all -> 0x011b }
                        if (r4 == 0) goto L_0x00d7
                        java.util.Date r4 = new java.util.Date     // Catch:{ all -> 0x011b }
                        com.facebook.AccessTokenManager$RefreshResult r13 = r5     // Catch:{ all -> 0x011b }
                        java.lang.Long r13 = r13.dataAccessExpirationTime     // Catch:{ all -> 0x011b }
                        long r15 = r13.longValue()     // Catch:{ all -> 0x011b }
                        long r11 = r11 * r15
                        r4.<init>(r11)     // Catch:{ all -> 0x011b }
                        r13 = r4
                        goto L_0x00de
                    L_0x00d7:
                        com.facebook.AccessToken r4 = r2     // Catch:{ all -> 0x011b }
                        java.util.Date r4 = r4.getDataAccessExpirationTime()     // Catch:{ all -> 0x011b }
                        r13 = r4
                    L_0x00de:
                        r4 = r14
                        r11 = r0
                        r12 = r3
                        r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x011b }
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.getInstance()     // Catch:{ all -> 0x00fc }
                        r0.setCurrentAccessToken(r14)     // Catch:{ all -> 0x00fc }
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.tokenRefreshInProgress
                        r0.set(r2)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3
                        if (r0 == 0) goto L_0x00fb
                        r0.OnTokenRefreshed(r14)
                    L_0x00fb:
                        return
                    L_0x00fc:
                        r0 = move-exception
                        r3 = r14
                        goto L_0x011d
                    L_0x00ff:
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3     // Catch:{ all -> 0x011b }
                        if (r0 == 0) goto L_0x010f
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3     // Catch:{ all -> 0x011b }
                        com.facebook.FacebookException r3 = new com.facebook.FacebookException     // Catch:{ all -> 0x011b }
                        java.lang.String r4 = "No current access token to refresh"
                        r3.<init>(r4)     // Catch:{ all -> 0x011b }
                        r0.OnTokenRefreshFailed(r3)     // Catch:{ all -> 0x011b }
                    L_0x010f:
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.tokenRefreshInProgress
                        r0.set(r2)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3
                        return
                    L_0x011b:
                        r0 = move-exception
                        r3 = 0
                    L_0x011d:
                        com.facebook.AccessTokenManager r4 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r4 = r4.tokenRefreshInProgress
                        r4.set(r2)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r2 = r3
                        if (r2 == 0) goto L_0x012f
                        if (r3 == 0) goto L_0x012f
                        r2.OnTokenRefreshed(r3)
                    L_0x012f:
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessTokenManager.AnonymousClass4.onBatchCompleted(com.facebook.GraphRequestBatch):void");
                }
            };
            graphRequestBatch.addCallback(r0);
            graphRequestBatch.executeAsync();
        }
    }
}
