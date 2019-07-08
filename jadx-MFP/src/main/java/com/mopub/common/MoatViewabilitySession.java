package com.mopub.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Reflection.MethodBuilder;
import com.mopub.mobileads.VastExtensionXmlManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MoatViewabilitySession implements ExternalViewabilitySession {
    private static final Map<String, String> QUERY_PARAM_MAPPING = new HashMap();
    private static boolean sIsVendorDisabled = false;
    private static Boolean sIsViewabilityEnabledViaReflection = null;
    private static boolean sWasInitialized = false;
    @NonNull
    private Map<String, String> mAdIds = new HashMap();
    @Nullable
    private Object mMoatVideoTracker;
    @Nullable
    private Object mMoatWebAdTracker;
    private boolean mWasVideoPrepared;

    @NonNull
    public String getName() {
        return VastExtensionXmlManager.MOAT;
    }

    MoatViewabilitySession() {
    }

    static {
        QUERY_PARAM_MAPPING.put("moatClientLevel1", "level1");
        QUERY_PARAM_MAPPING.put("moatClientLevel2", "level2");
        QUERY_PARAM_MAPPING.put("moatClientLevel3", "level3");
        QUERY_PARAM_MAPPING.put("moatClientLevel4", "level4");
        QUERY_PARAM_MAPPING.put("moatClientSlicer1", "slicer1");
        QUERY_PARAM_MAPPING.put("moatClientSlicer2", "slicer2");
    }

    static boolean isEnabled() {
        return !sIsVendorDisabled && isViewabilityEnabledViaReflection();
    }

    static void disable() {
        sIsVendorDisabled = true;
    }

    private static boolean isViewabilityEnabledViaReflection() {
        if (sIsViewabilityEnabledViaReflection == null) {
            sIsViewabilityEnabledViaReflection = Boolean.valueOf(Reflection.classFound("com.moat.analytics.mobile.mpub.MoatFactory"));
            StringBuilder sb = new StringBuilder();
            sb.append("Moat is ");
            sb.append(sIsViewabilityEnabledViaReflection.booleanValue() ? "" : "un");
            sb.append("available via reflection.");
            MoPubLog.d(sb.toString());
        }
        return sIsViewabilityEnabledViaReflection.booleanValue();
    }

    @Nullable
    public Boolean initialize(@NonNull Context context) {
        Application application;
        Preconditions.checkNotNull(context);
        if (!isEnabled()) {
            return null;
        }
        if (sWasInitialized) {
            return Boolean.valueOf(true);
        }
        if (context instanceof Activity) {
            application = ((Activity) context).getApplication();
        } else {
            try {
                application = (Application) context.getApplicationContext();
            } catch (ClassCastException unused) {
                MoPubLog.d("Unable to initialize Moat, error obtaining application context.");
                return Boolean.valueOf(false);
            }
        }
        try {
            Object instantiateClassWithEmptyConstructor = Reflection.instantiateClassWithEmptyConstructor("com.moat.analytics.mobile.mpub.MoatOptions", Object.class);
            instantiateClassWithEmptyConstructor.getClass().getField("disableAdIdCollection").setBoolean(instantiateClassWithEmptyConstructor, true);
            instantiateClassWithEmptyConstructor.getClass().getField("disableLocationServices").setBoolean(instantiateClassWithEmptyConstructor, true);
            new MethodBuilder(new MethodBuilder(null, "getInstance").setStatic("com.moat.analytics.mobile.mpub.MoatAnalytics").execute(), TtmlNode.START).addParam("com.moat.analytics.mobile.mpub.MoatOptions", instantiateClassWithEmptyConstructor).addParam(Application.class, application).execute();
            sWasInitialized = true;
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to initialize Moat: ");
            sb.append(e.getMessage());
            MoPubLog.d(sb.toString());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean invalidate() {
        if (!isEnabled()) {
            return null;
        }
        this.mMoatWebAdTracker = null;
        this.mMoatVideoTracker = null;
        this.mAdIds.clear();
        return Boolean.valueOf(true);
    }

    @Nullable
    public Boolean createDisplaySession(@NonNull Context context, @NonNull WebView webView, boolean z) {
        Preconditions.checkNotNull(context);
        if (!isEnabled()) {
            return null;
        }
        try {
            this.mMoatWebAdTracker = new MethodBuilder(new MethodBuilder(null, "create").setStatic("com.moat.analytics.mobile.mpub.MoatFactory").execute(), "createWebAdTracker").addParam(WebView.class, webView).execute();
            if (!z) {
                new MethodBuilder(this.mMoatWebAdTracker, "startTracking").execute();
            }
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to execute Moat start display session: ");
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
        Object obj = this.mMoatWebAdTracker;
        if (obj == null) {
            MoPubLog.d("MoatWebAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(obj, "startTracking").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to record deferred display session for Moat: ");
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
        Object obj = this.mMoatWebAdTracker;
        if (obj == null) {
            MoPubLog.d("Moat WebAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(obj, "stopTracking").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to execute Moat end session: ");
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
        updateAdIdsFromUrlStringAndBuyerResources((String) map.get("moat"), set);
        String str = (String) this.mAdIds.get("partnerCode");
        if (TextUtils.isEmpty(str)) {
            MoPubLog.d("partnerCode was empty when starting Moat video session");
            return Boolean.valueOf(false);
        }
        try {
            this.mMoatVideoTracker = new MethodBuilder(new MethodBuilder(null, "create").setStatic("com.moat.analytics.mobile.mpub.MoatFactory").execute(), "createCustomTracker").addParam("com.moat.analytics.mobile.mpub.MoatPlugin", Reflection.instantiateClassWithConstructor("com.moat.analytics.mobile.mpub.ReactiveVideoTrackerPlugin", Object.class, new Class[]{String.class}, new Object[]{str})).execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to execute Moat start video session: ");
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
        return Boolean.valueOf(true);
    }

    @Nullable
    public Boolean onVideoPrepared(@NonNull View view, int i) {
        Preconditions.checkNotNull(view);
        if (!isEnabled()) {
            return null;
        }
        Object obj = this.mMoatVideoTracker;
        if (obj == null) {
            MoPubLog.d("Moat VideoAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        } else if (this.mWasVideoPrepared) {
            return Boolean.valueOf(false);
        } else {
            try {
                new MethodBuilder(obj, "trackVideoAd").addParam(Map.class, this.mAdIds).addParam(Integer.class, Integer.valueOf(i)).addParam(View.class, view).execute();
                this.mWasVideoPrepared = true;
                return Boolean.valueOf(true);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to execute Moat onVideoPrepared: ");
                sb.append(e.getMessage());
                MoPubLog.d(sb.toString());
                return Boolean.valueOf(false);
            }
        }
    }

    @Nullable
    public Boolean recordVideoEvent(@NonNull VideoEvent videoEvent, int i) {
        Preconditions.checkNotNull(videoEvent);
        if (!isEnabled()) {
            return null;
        }
        if (this.mMoatVideoTracker == null) {
            MoPubLog.d("Moat VideoAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            switch (videoEvent) {
                case AD_STARTED:
                case AD_STOPPED:
                case AD_PAUSED:
                case AD_PLAYING:
                case AD_SKIPPED:
                case AD_VIDEO_FIRST_QUARTILE:
                case AD_VIDEO_MIDPOINT:
                case AD_VIDEO_THIRD_QUARTILE:
                case AD_COMPLETE:
                    handleVideoEventReflection(videoEvent, i);
                    return Boolean.valueOf(true);
                case AD_LOADED:
                case AD_IMPRESSED:
                case AD_CLICK_THRU:
                case RECORD_AD_ERROR:
                    return null;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected video event: ");
                    sb.append(videoEvent.getMoatEnumName());
                    MoPubLog.d(sb.toString());
                    return Boolean.valueOf(false);
            }
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Video event ");
            sb2.append(videoEvent.getMoatEnumName());
            sb2.append(" failed. ");
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
        Object obj = this.mMoatVideoTracker;
        if (obj == null) {
            MoPubLog.d("Moat VideoAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(obj, "stopTracking").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to execute Moat end video session: ");
            sb.append(e.getMessage());
            MoPubLog.d(sb.toString());
            return Boolean.valueOf(false);
        }
    }

    private void updateAdIdsFromUrlStringAndBuyerResources(@Nullable String str, @Nullable Set<String> set) {
        this.mAdIds.clear();
        this.mAdIds.put("partnerCode", "mopubinapphtmvideo468906546585");
        this.mAdIds.put("zMoatVASTIDs", TextUtils.join(";", set));
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            List pathSegments = parse.getPathSegments();
            if (pathSegments.size() > 0 && !TextUtils.isEmpty((CharSequence) pathSegments.get(0))) {
                this.mAdIds.put("partnerCode", pathSegments.get(0));
            }
            String fragment = parse.getFragment();
            if (!TextUtils.isEmpty(fragment)) {
                for (String split : fragment.split("&")) {
                    String[] split2 = split.split("=");
                    if (split2.length >= 2) {
                        String str2 = split2[0];
                        String str3 = split2[1];
                        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && QUERY_PARAM_MAPPING.containsKey(str2)) {
                            this.mAdIds.put(QUERY_PARAM_MAPPING.get(str2), str3);
                        }
                    }
                }
            }
        }
    }

    private boolean handleVideoEventReflection(@NonNull VideoEvent videoEvent, int i) throws Exception {
        if (videoEvent.getMoatEnumName() == null) {
            return false;
        }
        Class cls = Class.forName("com.moat.analytics.mobile.mpub.MoatAdEventType");
        new MethodBuilder(this.mMoatVideoTracker, "dispatchEvent").addParam("com.moat.analytics.mobile.mpub.MoatAdEvent", Reflection.instantiateClassWithConstructor("com.moat.analytics.mobile.mpub.MoatAdEvent", Object.class, new Class[]{cls, Integer.class}, new Object[]{Enum.valueOf(cls.asSubclass(Enum.class), videoEvent.getMoatEnumName()), Integer.valueOf(i)})).execute();
        return true;
    }
}
