package com.facebook.appevents.codeless;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewIndexer {
    private static final String APP_VERSION_PARAM = "app_version";
    private static final String PLATFORM_PARAM = "platform";
    private static final String REQUEST_TYPE = "request_type";
    private static final String SUCCESS = "success";
    /* access modifiers changed from: private */
    public static final String TAG = ViewIndexer.class.getCanonicalName();
    private static final String TREE_PARAM = "tree";
    private static ViewIndexer instance;
    private WeakReference<Activity> activityReference;
    /* access modifiers changed from: private */
    public Timer indexingTimer;
    /* access modifiers changed from: private */
    public String previousDigest = null;
    /* access modifiers changed from: private */
    public final Handler uiThreadHandler = new Handler(Looper.getMainLooper());

    private static class ScreenshotTaker implements Callable<String> {
        private WeakReference<View> rootView;

        public ScreenshotTaker(View view) {
            this.rootView = new WeakReference<>(view);
        }

        public String call() throws Exception {
            View view = (View) this.rootView.get();
            if (view == null || view.getWidth() == 0 || view.getHeight() == 0) {
                return "";
            }
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
            view.draw(new Canvas(createBitmap));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(CompressFormat.JPEG, 10, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        }
    }

    public ViewIndexer(Activity activity) {
        this.activityReference = new WeakReference<>(activity);
        instance = this;
    }

    public void schedule() {
        final Activity activity = (Activity) this.activityReference.get();
        if (activity != null) {
            final String simpleName = activity.getClass().getSimpleName();
            FacebookSdk.getApplicationId();
            final AnonymousClass1 r2 = new TimerTask() {
                /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
                /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006d */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r6 = this;
                        android.app.Activity r0 = r0     // Catch:{ Exception -> 0x0082 }
                        android.view.Window r0 = r0.getWindow()     // Catch:{ Exception -> 0x0082 }
                        android.view.View r0 = r0.getDecorView()     // Catch:{ Exception -> 0x0082 }
                        android.view.View r0 = r0.getRootView()     // Catch:{ Exception -> 0x0082 }
                        boolean r1 = com.facebook.appevents.internal.ActivityLifecycleTracker.getIsAppIndexingEnabled()     // Catch:{ Exception -> 0x0082 }
                        if (r1 != 0) goto L_0x0015
                        return
                    L_0x0015:
                        boolean r1 = com.facebook.internal.InternalSettings.isUnityApp()     // Catch:{ Exception -> 0x0082 }
                        if (r1 == 0) goto L_0x001f
                        com.facebook.appevents.codeless.internal.UnityReflection.captureViewHierarchy()     // Catch:{ Exception -> 0x0082 }
                        return
                    L_0x001f:
                        java.util.concurrent.FutureTask r1 = new java.util.concurrent.FutureTask     // Catch:{ Exception -> 0x0082 }
                        com.facebook.appevents.codeless.ViewIndexer$ScreenshotTaker r2 = new com.facebook.appevents.codeless.ViewIndexer$ScreenshotTaker     // Catch:{ Exception -> 0x0082 }
                        r2.<init>(r0)     // Catch:{ Exception -> 0x0082 }
                        r1.<init>(r2)     // Catch:{ Exception -> 0x0082 }
                        com.facebook.appevents.codeless.ViewIndexer r2 = com.facebook.appevents.codeless.ViewIndexer.this     // Catch:{ Exception -> 0x0082 }
                        android.os.Handler r2 = r2.uiThreadHandler     // Catch:{ Exception -> 0x0082 }
                        r2.post(r1)     // Catch:{ Exception -> 0x0082 }
                        java.lang.String r2 = ""
                        r3 = 1
                        java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x003f }
                        java.lang.Object r1 = r1.get(r3, r5)     // Catch:{ Exception -> 0x003f }
                        java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x003f }
                        goto L_0x004a
                    L_0x003f:
                        r1 = move-exception
                        java.lang.String r3 = com.facebook.appevents.codeless.ViewIndexer.TAG     // Catch:{ Exception -> 0x0082 }
                        java.lang.String r4 = "Failed to take screenshot."
                        android.util.Log.e(r3, r4, r1)     // Catch:{ Exception -> 0x0082 }
                        r1 = r2
                    L_0x004a:
                        org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0082 }
                        r2.<init>()     // Catch:{ Exception -> 0x0082 }
                        java.lang.String r3 = "screenname"
                        java.lang.String r4 = r1     // Catch:{ JSONException -> 0x006d }
                        r2.put(r3, r4)     // Catch:{ JSONException -> 0x006d }
                        java.lang.String r3 = "screenshot"
                        r2.put(r3, r1)     // Catch:{ JSONException -> 0x006d }
                        org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x006d }
                        r1.<init>()     // Catch:{ JSONException -> 0x006d }
                        org.json.JSONObject r0 = com.facebook.appevents.codeless.internal.ViewHierarchy.getDictionaryOfView(r0)     // Catch:{ JSONException -> 0x006d }
                        r1.put(r0)     // Catch:{ JSONException -> 0x006d }
                        java.lang.String r0 = "view"
                        r2.put(r0, r1)     // Catch:{ JSONException -> 0x006d }
                        goto L_0x0076
                    L_0x006d:
                        java.lang.String r0 = com.facebook.appevents.codeless.ViewIndexer.TAG     // Catch:{ Exception -> 0x0082 }
                        java.lang.String r1 = "Failed to create JSONObject"
                        android.util.Log.e(r0, r1)     // Catch:{ Exception -> 0x0082 }
                    L_0x0076:
                        java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0082 }
                        com.facebook.appevents.codeless.ViewIndexer r1 = com.facebook.appevents.codeless.ViewIndexer.this     // Catch:{ Exception -> 0x0082 }
                        java.lang.String r2 = r1     // Catch:{ Exception -> 0x0082 }
                        r1.sendToServer(r0, r2)     // Catch:{ Exception -> 0x0082 }
                        goto L_0x008c
                    L_0x0082:
                        r0 = move-exception
                        java.lang.String r1 = com.facebook.appevents.codeless.ViewIndexer.TAG
                        java.lang.String r2 = "UI Component tree indexing failure!"
                        android.util.Log.e(r1, r2, r0)
                    L_0x008c:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.ViewIndexer.AnonymousClass1.run():void");
                }
            };
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    try {
                        if (ViewIndexer.this.indexingTimer != null) {
                            ViewIndexer.this.indexingTimer.cancel();
                        }
                        ViewIndexer.this.previousDigest = null;
                        ViewIndexer.this.indexingTimer = new Timer();
                        ViewIndexer.this.indexingTimer.scheduleAtFixedRate(r2, 0, 1000);
                    } catch (Exception e) {
                        Log.e(ViewIndexer.TAG, "Error scheduling indexing job", e);
                    }
                }
            });
        }
    }

    public void unschedule() {
        if (((Activity) this.activityReference.get()) != null) {
            Timer timer = this.indexingTimer;
            if (timer != null) {
                try {
                    timer.cancel();
                    this.indexingTimer = null;
                } catch (Exception e) {
                    Log.e(TAG, "Error unscheduling indexing job", e);
                }
            }
        }
    }

    public static void sendToServerUnityInstance(String str) {
        ViewIndexer viewIndexer = instance;
        if (viewIndexer != null) {
            viewIndexer.sendToServerUnity(str);
        }
    }

    @Deprecated
    public void sendToServerUnity(String str) {
        Activity activity = (Activity) this.activityReference.get();
        String str2 = "";
        if (activity != null) {
            str2 = activity.getClass().getSimpleName();
        }
        instance.sendToServer(str, str2);
    }

    /* access modifiers changed from: private */
    public void sendToServer(final String str, String str2) {
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                String applicationId = FacebookSdk.getApplicationId();
                String md5hash = Utility.md5hash(str);
                AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                if (md5hash == null || !md5hash.equals(ViewIndexer.this.previousDigest)) {
                    GraphRequest buildAppIndexingRequest = ViewIndexer.buildAppIndexingRequest(str, currentAccessToken, applicationId, Constants.APP_INDEXING);
                    if (buildAppIndexingRequest != null) {
                        GraphResponse executeAndWait = buildAppIndexingRequest.executeAndWait();
                        try {
                            JSONObject jSONObject = executeAndWait.getJSONObject();
                            if (jSONObject != null) {
                                if (jSONObject.has("success") && jSONObject.getString("success") == "true") {
                                    Logger.log(LoggingBehavior.APP_EVENTS, ViewIndexer.TAG, "Successfully send UI component tree to server");
                                    ViewIndexer.this.previousDigest = md5hash;
                                }
                                if (jSONObject.has(Constants.APP_INDEXING_ENABLED)) {
                                    ActivityLifecycleTracker.updateAppIndexing(Boolean.valueOf(jSONObject.getBoolean(Constants.APP_INDEXING_ENABLED)));
                                }
                            } else {
                                String access$100 = ViewIndexer.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("Error sending UI component tree to Facebook: ");
                                sb.append(executeAndWait.getError());
                                Log.e(access$100, sb.toString());
                            }
                        } catch (JSONException e) {
                            Log.e(ViewIndexer.TAG, "Error decoding server response.", e);
                        }
                    }
                }
            }
        });
    }

    @Nullable
    public static GraphRequest buildAppIndexingRequest(String str, AccessToken accessToken, String str2, String str3) {
        if (str == null) {
            return null;
        }
        GraphRequest newPostRequest = GraphRequest.newPostRequest(accessToken, String.format(Locale.US, "%s/app_indexing", new Object[]{str2}), null, null);
        Bundle parameters = newPostRequest.getParameters();
        if (parameters == null) {
            parameters = new Bundle();
        }
        parameters.putString(TREE_PARAM, str);
        parameters.putString("app_version", AppEventUtility.getAppVersion());
        parameters.putString("platform", "android");
        parameters.putString(REQUEST_TYPE, str3);
        if (str3.equals(Constants.APP_INDEXING)) {
            parameters.putString(Constants.DEVICE_SESSION_ID, ActivityLifecycleTracker.getCurrentDeviceSessionID());
        }
        newPostRequest.setParameters(parameters);
        newPostRequest.setCallback(new Callback() {
            public void onCompleted(GraphResponse graphResponse) {
                Logger.log(LoggingBehavior.APP_EVENTS, ViewIndexer.TAG, "App index sent to FB!");
            }
        });
        return newPostRequest;
    }
}
