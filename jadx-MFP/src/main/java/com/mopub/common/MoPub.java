package com.mopub.common;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Reflection.MethodBuilder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MoPub {
    public static final String SDK_VERSION = "5.4.1";
    private static boolean sAdvancedBiddingEnabled = true;
    private static AdvancedBiddingTokens sAdvancedBiddingTokens;
    @NonNull
    private static volatile BrowserAgent sBrowserAgent = BrowserAgent.IN_APP;
    private static volatile boolean sIsBrowserAgentOverriddenByClient = false;
    @NonNull
    private static volatile LocationAwareness sLocationAwareness = LocationAwareness.NORMAL;
    private static volatile int sLocationPrecision = 6;
    private static volatile long sMinimumLocationRefreshTimeMillis = DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS;
    private static PersonalInfoManager sPersonalInfoManager;
    private static boolean sSdkInitialized = false;
    private static boolean sSdkInitializing = false;
    private static boolean sSearchedForUpdateActivityMethod = false;
    @Nullable
    private static Method sUpdateActivityMethod;

    public enum BrowserAgent {
        IN_APP,
        NATIVE;

        @NonNull
        public static BrowserAgent fromHeader(@Nullable Integer num) {
            if (num == null) {
                return IN_APP;
            }
            return num.intValue() == 1 ? NATIVE : IN_APP;
        }
    }

    private static class InternalSdkInitializationListener implements SdkInitializationListener {
        @Nullable
        private SdkInitializationListener mSdkInitializationListener;

        InternalSdkInitializationListener(@Nullable SdkInitializationListener sdkInitializationListener) {
            this.mSdkInitializationListener = sdkInitializationListener;
        }

        public void onInitializationFinished() {
            MoPub.initializationFinished(this.mSdkInitializationListener);
            this.mSdkInitializationListener = null;
        }
    }

    public enum LocationAwareness {
        NORMAL,
        TRUNCATED,
        DISABLED
    }

    @NonNull
    public static LocationAwareness getLocationAwareness() {
        Preconditions.checkNotNull(sLocationAwareness);
        return sLocationAwareness;
    }

    public static void setLocationAwareness(@NonNull LocationAwareness locationAwareness) {
        Preconditions.checkNotNull(locationAwareness);
        sLocationAwareness = locationAwareness;
    }

    public static int getLocationPrecision() {
        return sLocationPrecision;
    }

    public static void setLocationPrecision(int i) {
        sLocationPrecision = Math.min(Math.max(0, i), 6);
    }

    public static void setMinimumLocationRefreshTimeMillis(long j) {
        sMinimumLocationRefreshTimeMillis = j;
    }

    public static long getMinimumLocationRefreshTimeMillis() {
        return sMinimumLocationRefreshTimeMillis;
    }

    public static void setBrowserAgent(@NonNull BrowserAgent browserAgent) {
        Preconditions.checkNotNull(browserAgent);
        sBrowserAgent = browserAgent;
        sIsBrowserAgentOverriddenByClient = true;
    }

    public static void setBrowserAgentFromAdServer(@NonNull BrowserAgent browserAgent) {
        Preconditions.checkNotNull(browserAgent);
        if (sIsBrowserAgentOverriddenByClient) {
            StringBuilder sb = new StringBuilder();
            sb.append("Browser agent already overridden by client with value ");
            sb.append(sBrowserAgent);
            MoPubLog.w(sb.toString());
            return;
        }
        sBrowserAgent = browserAgent;
    }

    @NonNull
    public static BrowserAgent getBrowserAgent() {
        Preconditions.checkNotNull(sBrowserAgent);
        return sBrowserAgent;
    }

    public static void setAdvancedBiddingEnabled(boolean z) {
        sAdvancedBiddingEnabled = z;
    }

    public static boolean isAdvancedBiddingEnabled() {
        return sAdvancedBiddingEnabled;
    }

    public static void initializeSdk(@NonNull Context context, @NonNull SdkConfiguration sdkConfiguration, @Nullable SdkInitializationListener sdkInitializationListener) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(sdkConfiguration);
        StringBuilder sb = new StringBuilder();
        sb.append("Initializing MoPub with ad unit: ");
        sb.append(sdkConfiguration.getAdUnitId());
        MoPubLog.d(sb.toString());
        if (context instanceof Activity) {
            initializeRewardedVideo((Activity) context, sdkConfiguration);
        }
        if (sSdkInitialized) {
            MoPubLog.d("MoPub SDK is already initialized");
            initializationFinished(sdkInitializationListener);
        } else if (sSdkInitializing) {
            MoPubLog.d("MoPub SDK is currently initializing.");
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            MoPubLog.e("MoPub can only be initialized on the main thread.");
        } else {
            sSdkInitializing = true;
            CompositeSdkInitializationListener compositeSdkInitializationListener = new CompositeSdkInitializationListener(new InternalSdkInitializationListener(sdkInitializationListener), 2);
            sPersonalInfoManager = new PersonalInfoManager(context, sdkConfiguration.getAdUnitId(), compositeSdkInitializationListener);
            ClientMetadata.getInstance(context);
            sAdvancedBiddingTokens = new AdvancedBiddingTokens(compositeSdkInitializationListener);
            sAdvancedBiddingTokens.addAdvancedBidders(sdkConfiguration.getAdvancedBidders());
        }
    }

    public static boolean isSdkInitialized() {
        return sSdkInitialized;
    }

    public static boolean canCollectPersonalInformation() {
        PersonalInfoManager personalInfoManager = sPersonalInfoManager;
        return personalInfoManager != null && personalInfoManager.canCollectPersonalInformation();
    }

    @Nullable
    static String getAdvancedBiddingTokensJson(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        if (isAdvancedBiddingEnabled()) {
            AdvancedBiddingTokens advancedBiddingTokens = sAdvancedBiddingTokens;
            if (advancedBiddingTokens != null) {
                return advancedBiddingTokens.getTokensAsJsonString(context);
            }
        }
        return null;
    }

    @Nullable
    public static PersonalInfoManager getPersonalInformationManager() {
        return sPersonalInfoManager;
    }

    @Deprecated
    @VisibleForTesting
    public static void resetBrowserAgent() {
        sBrowserAgent = BrowserAgent.IN_APP;
        sIsBrowserAgentOverriddenByClient = false;
    }

    public static void onCreate(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onCreate(activity);
        updateActivity(activity);
    }

    public static void onStart(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onStart(activity);
        updateActivity(activity);
    }

    public static void onPause(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onPause(activity);
    }

    public static void onResume(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onResume(activity);
        updateActivity(activity);
    }

    public static void onRestart(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onRestart(activity);
        updateActivity(activity);
    }

    public static void onStop(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onStop(activity);
    }

    public static void onDestroy(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onDestroy(activity);
    }

    public static void onBackPressed(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onBackPressed(activity);
    }

    public static void disableViewability(@NonNull ViewabilityVendor viewabilityVendor) {
        Preconditions.checkNotNull(viewabilityVendor);
        viewabilityVendor.disable();
    }

    private static void initializeRewardedVideo(@NonNull Activity activity, @NonNull SdkConfiguration sdkConfiguration) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(sdkConfiguration);
        try {
            new MethodBuilder(null, "initializeRewardedVideo").setStatic(Class.forName("com.mopub.mobileads.MoPubRewardedVideos")).setAccessible().addParam(Activity.class, activity).addParam(SdkConfiguration.class, sdkConfiguration).execute();
        } catch (ClassNotFoundException unused) {
            MoPubLog.w("initializeRewardedVideo was called without the rewarded video module");
        } catch (NoSuchMethodException unused2) {
            MoPubLog.w("initializeRewardedVideo was called without the rewarded video module");
        } catch (Exception e) {
            MoPubLog.e("Error while initializing rewarded video", e);
        }
    }

    /* access modifiers changed from: private */
    public static void initializationFinished(@Nullable final SdkInitializationListener sdkInitializationListener) {
        sSdkInitializing = false;
        sSdkInitialized = true;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                SdkInitializationListener sdkInitializationListener = sdkInitializationListener;
                if (sdkInitializationListener != null) {
                    sdkInitializationListener.onInitializationFinished();
                }
            }
        });
    }

    @VisibleForTesting
    static void updateActivity(@NonNull Activity activity) {
        if (!sSearchedForUpdateActivityMethod) {
            sSearchedForUpdateActivityMethod = true;
            try {
                sUpdateActivityMethod = Reflection.getDeclaredMethodWithTraversal(Class.forName("com.mopub.mobileads.MoPubRewardedVideoManager"), "updateActivity", Activity.class);
            } catch (ClassNotFoundException unused) {
            } catch (NoSuchMethodException unused2) {
            }
        }
        Method method = sUpdateActivityMethod;
        if (method != null) {
            try {
                method.invoke(null, new Object[]{activity});
            } catch (IllegalAccessException e) {
                MoPubLog.e("Error while attempting to access the update activity method - this should not have happened", e);
            } catch (InvocationTargetException e2) {
                MoPubLog.e("Error while attempting to access the update activity method - this should not have happened", e2);
            }
        }
    }
}
