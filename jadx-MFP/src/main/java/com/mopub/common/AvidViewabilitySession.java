package com.mopub.common;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.session.internal.jsbridge.AvidJavascriptInterface;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Reflection.MethodBuilder;
import com.mopub.mobileads.VastExtensionXmlManager;
import java.util.Map;
import java.util.Set;

class AvidViewabilitySession implements ExternalViewabilitySession {
    private static Object sAvidAdSessionContextDeferred;
    private static Object sAvidAdSessionContextNonDeferred;
    private static boolean sIsVendorDisabled;
    private static Boolean sIsViewabilityEnabledViaReflection;
    @Nullable
    private Object mAvidDisplayAdSession;
    @Nullable
    private Object mAvidVideoAdSession;

    @NonNull
    public String getName() {
        return VastExtensionXmlManager.AVID;
    }

    AvidViewabilitySession() {
    }

    static boolean isEnabled() {
        return !sIsVendorDisabled && isViewabilityEnabledViaReflection();
    }

    static void disable() {
        sIsVendorDisabled = true;
    }

    private static boolean isViewabilityEnabledViaReflection() {
        if (sIsViewabilityEnabledViaReflection == null) {
            sIsViewabilityEnabledViaReflection = Boolean.valueOf(Reflection.classFound("com.integralads.avid.library.mopub.session.AvidAdSessionManager"));
            StringBuilder sb = new StringBuilder();
            sb.append("Avid is ");
            sb.append(sIsViewabilityEnabledViaReflection.booleanValue() ? "" : "un");
            sb.append("available via reflection.");
            MoPubLog.d(sb.toString());
        }
        return sIsViewabilityEnabledViaReflection.booleanValue();
    }

    @Nullable
    private static Object getAvidAdSessionContextDeferred() {
        if (sAvidAdSessionContextDeferred == null) {
            try {
                sAvidAdSessionContextDeferred = Reflection.instantiateClassWithConstructor("com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext", Object.class, new Class[]{String.class, Boolean.TYPE}, new Object[]{"5.4.1", Boolean.valueOf(true)});
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to generate Avid deferred ad session context: ");
                sb.append(e.getMessage());
                MoPubLog.d(sb.toString());
            }
        }
        return sAvidAdSessionContextDeferred;
    }

    @Nullable
    private static Object getAvidAdSessionContextNonDeferred() {
        if (sAvidAdSessionContextNonDeferred == null) {
            try {
                sAvidAdSessionContextNonDeferred = Reflection.instantiateClassWithConstructor("com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext", Object.class, new Class[]{String.class}, new Object[]{"5.4.1"});
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to generate Avid ad session context: ");
                sb.append(e.getMessage());
                MoPubLog.d(sb.toString());
            }
        }
        return sAvidAdSessionContextNonDeferred;
    }

    @Nullable
    public Boolean initialize(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        if (!isEnabled()) {
            return null;
        }
        return Boolean.valueOf(true);
    }

    @Nullable
    public Boolean invalidate() {
        if (!isEnabled()) {
            return null;
        }
        this.mAvidDisplayAdSession = null;
        this.mAvidVideoAdSession = null;
        return Boolean.valueOf(true);
    }

    @Nullable
    public Boolean createDisplaySession(@NonNull Context context, @NonNull WebView webView, boolean z) {
        Object obj;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(webView);
        if (!isEnabled()) {
            return null;
        }
        if (z) {
            obj = getAvidAdSessionContextDeferred();
        } else {
            obj = getAvidAdSessionContextNonDeferred();
        }
        Object obj2 = context instanceof Activity ? (Activity) context : null;
        try {
            this.mAvidDisplayAdSession = new MethodBuilder(null, "startAvidDisplayAdSession").setStatic("com.integralads.avid.library.mopub.session.AvidAdSessionManager").addParam(Context.class, context).addParam("com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext", obj).execute();
            new MethodBuilder(this.mAvidDisplayAdSession, "registerAdView").addParam(View.class, webView).addParam(Activity.class, obj2).execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to execute Avid start display session: ");
            sb.append(e.getMessage());
            MoPubLog.d(sb.toString());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean startDeferredDisplaySession(@NonNull Activity activity) {
        if (!isEnabled()) {
            return null;
        }
        if (this.mAvidDisplayAdSession == null) {
            MoPubLog.d("Avid DisplayAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(new MethodBuilder(null, "getInstance").setStatic("com.integralads.avid.library.mopub.AvidManager").execute(), "registerActivity").addParam(Activity.class, activity).execute();
            Object execute = new MethodBuilder(this.mAvidDisplayAdSession, "getAvidDeferredAdSessionListener").execute();
            if (execute == null) {
                MoPubLog.d("Avid AdSessionListener unexpectedly null.");
                return Boolean.valueOf(false);
            }
            new MethodBuilder(execute, "recordReadyEvent").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to execute Avid record deferred session: ");
            sb.append(e.getMessage());
            MoPubLog.d(sb.toString());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean endDisplaySession() {
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mAvidDisplayAdSession;
        if (obj == null) {
            MoPubLog.d("Avid DisplayAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(obj, "endSession").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to execute Avid end session: ");
            sb.append(e.getMessage());
            MoPubLog.d(sb.toString());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean createVideoSession(@NonNull Activity activity, @NonNull View view, @NonNull Set<String> set, @NonNull Map<String, String> map) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(set);
        Preconditions.checkNotNull(map);
        if (!isEnabled()) {
            return null;
        }
        try {
            this.mAvidVideoAdSession = new MethodBuilder(null, "startAvidManagedVideoAdSession").setStatic("com.integralads.avid.library.mopub.session.AvidAdSessionManager").addParam(Context.class, activity).addParam("com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext", getAvidAdSessionContextNonDeferred()).execute();
            new MethodBuilder(this.mAvidVideoAdSession, "registerAdView").addParam(View.class, view).addParam(Activity.class, activity).execute();
            if (!TextUtils.isEmpty((CharSequence) map.get(AvidJavascriptInterface.AVID_OBJECT))) {
                new MethodBuilder(this.mAvidVideoAdSession, "injectJavaScriptResource").addParam(String.class, map.get(AvidJavascriptInterface.AVID_OBJECT)).execute();
            }
            for (String str : set) {
                if (!TextUtils.isEmpty(str)) {
                    new MethodBuilder(this.mAvidVideoAdSession, "injectJavaScriptResource").addParam(String.class, str).execute();
                }
            }
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to execute Avid start video session: ");
            sb.append(e.getMessage());
            MoPubLog.d(sb.toString());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean registerVideoObstruction(@NonNull View view) {
        Preconditions.checkNotNull(view);
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mAvidVideoAdSession;
        if (obj == null) {
            MoPubLog.d("Avid VideoAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(obj, "registerFriendlyObstruction").addParam(View.class, view).execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to register Avid video obstructions: ");
            sb.append(e.getMessage());
            MoPubLog.d(sb.toString());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean onVideoPrepared(@NonNull View view, int i) {
        Preconditions.checkNotNull(view);
        if (!isEnabled()) {
            return null;
        }
        return Boolean.valueOf(true);
    }

    @Nullable
    public Boolean recordVideoEvent(@NonNull VideoEvent videoEvent, int i) {
        Preconditions.checkNotNull(videoEvent);
        if (!isEnabled()) {
            return null;
        }
        if (this.mAvidVideoAdSession == null) {
            MoPubLog.d("Avid VideoAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            switch (videoEvent) {
                case AD_LOADED:
                case AD_STARTED:
                case AD_STOPPED:
                case AD_PAUSED:
                case AD_PLAYING:
                case AD_SKIPPED:
                case AD_IMPRESSED:
                case AD_CLICK_THRU:
                case AD_VIDEO_FIRST_QUARTILE:
                case AD_VIDEO_MIDPOINT:
                case AD_VIDEO_THIRD_QUARTILE:
                case AD_COMPLETE:
                    handleVideoEventReflection(videoEvent);
                    return Boolean.valueOf(true);
                case RECORD_AD_ERROR:
                    handleVideoEventReflection(videoEvent, "error");
                    return Boolean.valueOf(true);
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected video event type: ");
                    sb.append(videoEvent);
                    MoPubLog.d(sb.toString());
                    return Boolean.valueOf(false);
            }
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to execute Avid video event for ");
            sb2.append(videoEvent.getAvidMethodName());
            sb2.append(": ");
            sb2.append(e.getMessage());
            MoPubLog.d(sb2.toString());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean endVideoSession() {
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mAvidVideoAdSession;
        if (obj == null) {
            MoPubLog.d("Avid VideoAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(obj, "endSession").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to execute Avid end video session: ");
            sb.append(e.getMessage());
            MoPubLog.d(sb.toString());
            return Boolean.valueOf(false);
        }
    }

    private void handleVideoEventReflection(@NonNull VideoEvent videoEvent) throws Exception {
        handleVideoEventReflection(videoEvent, null);
    }

    private void handleVideoEventReflection(@NonNull VideoEvent videoEvent, @Nullable String str) throws Exception {
        MethodBuilder methodBuilder = new MethodBuilder(new MethodBuilder(this.mAvidVideoAdSession, "getAvidVideoPlaybackListener").execute(), videoEvent.getAvidMethodName());
        if (!TextUtils.isEmpty(str)) {
            methodBuilder.addParam(String.class, str);
        }
        methodBuilder.execute();
    }
}
