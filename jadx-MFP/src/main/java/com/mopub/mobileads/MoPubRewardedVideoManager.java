package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdReport;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.SharedPreferencesHelper;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Json;
import com.mopub.common.util.MoPubCollections;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.ReflectionTarget;
import com.mopub.common.util.Utils;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.volley.NoConnectionError;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class MoPubRewardedVideoManager {
    public static final int API_VERSION = 1;
    @NonNull
    private static SharedPreferences sCustomEventSharedPrefs;
    /* access modifiers changed from: private */
    public static MoPubRewardedVideoManager sInstance;
    @NonNull
    private final Handler mCallbackHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    @NonNull
    public final Context mContext;
    @NonNull
    private final Handler mCustomEventTimeoutHandler;
    @NonNull
    private final Set<MediationSettings> mGlobalMediationSettings = new HashSet();
    @NonNull
    private final Map<String, Set<MediationSettings>> mInstanceMediationSettings;
    @NonNull
    private WeakReference<Activity> mMainActivity;
    /* access modifiers changed from: private */
    @NonNull
    public final RewardedAdData mRewardedAdData = new RewardedAdData();
    @NonNull
    private final Map<String, Runnable> mTimeoutMap;
    /* access modifiers changed from: private */
    @Nullable
    public MoPubRewardedVideoListener mVideoListener;
    /* access modifiers changed from: private */
    @NonNull
    public final RewardedAdsLoaders rewardedAdsLoaders;

    private static abstract class ForEachMoPubIdRunnable implements Runnable {
        @NonNull
        private final Class<? extends CustomEventRewardedAd> mCustomEventClass;
        @NonNull
        private final String mThirdPartyId;

        /* access modifiers changed from: protected */
        public abstract void forEach(@NonNull String str);

        ForEachMoPubIdRunnable(@NonNull Class<? extends CustomEventRewardedAd> cls, @NonNull String str) {
            Preconditions.checkNotNull(cls);
            Preconditions.checkNotNull(str);
            this.mCustomEventClass = cls;
            this.mThirdPartyId = str;
        }

        public void run() {
            for (String forEach : MoPubRewardedVideoManager.sInstance.mRewardedAdData.getMoPubIdsForAdNetwork(this.mCustomEventClass, this.mThirdPartyId)) {
                forEach(forEach);
            }
        }
    }

    public static final class RequestParameters {
        @Nullable
        public final String mCustomerId;
        @Nullable
        public final String mKeywords;
        @Nullable
        public final Location mLocation;
        @Nullable
        public final String mUserDataKeywords;

        public RequestParameters(@Nullable String str) {
            this(str, null);
        }

        public RequestParameters(@Nullable String str, @Nullable String str2) {
            this(str, str2, null);
        }

        public RequestParameters(@Nullable String str, @Nullable String str2, @Nullable Location location) {
            this(str, str2, location, null);
        }

        public RequestParameters(@Nullable String str, @Nullable String str2, @Nullable Location location, @Nullable String str3) {
            this.mKeywords = str;
            this.mCustomerId = str3;
            boolean canCollectPersonalInformation = MoPub.canCollectPersonalInformation();
            if (!canCollectPersonalInformation) {
                str2 = null;
            }
            this.mUserDataKeywords = str2;
            if (!canCollectPersonalInformation) {
                location = null;
            }
            this.mLocation = location;
        }
    }

    private MoPubRewardedVideoManager(@NonNull Activity activity, MediationSettings... mediationSettingsArr) {
        this.mMainActivity = new WeakReference<>(activity);
        this.mContext = activity.getApplicationContext();
        MoPubCollections.addAllNonNull((Collection<? super T>) this.mGlobalMediationSettings, (T[]) mediationSettingsArr);
        this.mInstanceMediationSettings = new HashMap();
        this.mCustomEventTimeoutHandler = new Handler();
        this.mTimeoutMap = new HashMap();
        this.rewardedAdsLoaders = new RewardedAdsLoaders(this);
        sCustomEventSharedPrefs = SharedPreferencesHelper.getSharedPreferences(this.mContext, "mopubCustomEventSettings");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:14|15|16|17|27) */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004a, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x008e */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.util.List<com.mopub.mobileads.CustomEventRewardedVideo> initNetworks(@android.support.annotation.NonNull android.app.Activity r12, @android.support.annotation.NonNull java.util.List<java.lang.Class<? extends com.mopub.mobileads.CustomEventRewardedVideo>> r13) {
        /*
            java.lang.Class<com.mopub.mobileads.MoPubRewardedVideoManager> r0 = com.mopub.mobileads.MoPubRewardedVideoManager.class
            monitor-enter(r0)
            com.mopub.common.Preconditions.checkNotNull(r12)     // Catch:{ all -> 0x00ba }
            com.mopub.common.Preconditions.checkNotNull(r13)     // Catch:{ all -> 0x00ba }
            com.mopub.mobileads.MoPubRewardedVideoManager r1 = sInstance     // Catch:{ all -> 0x00ba }
            if (r1 != 0) goto L_0x0016
            logErrorNotInitialized()     // Catch:{ all -> 0x00ba }
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00ba }
            monitor-exit(r0)
            return r12
        L_0x0016:
            java.util.LinkedList r1 = new java.util.LinkedList     // Catch:{ all -> 0x00ba }
            r1.<init>()     // Catch:{ all -> 0x00ba }
            android.content.SharedPreferences r2 = sCustomEventSharedPrefs     // Catch:{ all -> 0x00ba }
            java.util.Map r2 = r2.getAll()     // Catch:{ all -> 0x00ba }
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = "fetched init settings for %s networks: %s"
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x00ba }
            int r7 = r2.size()     // Catch:{ all -> 0x00ba }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x00ba }
            r8 = 0
            r6[r8] = r7     // Catch:{ all -> 0x00ba }
            java.util.Set r7 = r2.keySet()     // Catch:{ all -> 0x00ba }
            r9 = 1
            r6[r9] = r7     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = java.lang.String.format(r3, r4, r6)     // Catch:{ all -> 0x00ba }
            com.mopub.common.logging.MoPubLog.d(r3)     // Catch:{ all -> 0x00ba }
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet     // Catch:{ all -> 0x00ba }
            r3.<init>(r13)     // Catch:{ all -> 0x00ba }
            java.util.Iterator r13 = r3.iterator()     // Catch:{ all -> 0x00ba }
        L_0x004a:
            boolean r3 = r13.hasNext()     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x00b8
            java.lang.Object r3 = r13.next()     // Catch:{ all -> 0x00ba }
            java.lang.Class r3 = (java.lang.Class) r3     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x00ba }
            boolean r4 = r2.containsKey(r3)     // Catch:{ all -> 0x00ba }
            if (r4 == 0) goto L_0x00a3
            java.lang.Object r4 = r2.get(r3)     // Catch:{ Exception -> 0x008e }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x008e }
            java.util.Map r4 = com.mopub.common.util.Json.jsonStringToMap(r4)     // Catch:{ Exception -> 0x008e }
            java.lang.Class<com.mopub.mobileads.CustomEventRewardedVideo> r6 = com.mopub.mobileads.CustomEventRewardedVideo.class
            java.lang.Object r6 = com.mopub.common.util.Reflection.instantiateClassWithEmptyConstructor(r3, r6)     // Catch:{ Exception -> 0x008e }
            com.mopub.mobileads.CustomEventRewardedVideo r6 = (com.mopub.mobileads.CustomEventRewardedVideo) r6     // Catch:{ Exception -> 0x008e }
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ Exception -> 0x008e }
            java.lang.String r10 = "Initializing %s with params %s"
            java.lang.Object[] r11 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x008e }
            r11[r8] = r3     // Catch:{ Exception -> 0x008e }
            r11[r9] = r4     // Catch:{ Exception -> 0x008e }
            java.lang.String r7 = java.lang.String.format(r7, r10, r11)     // Catch:{ Exception -> 0x008e }
            com.mopub.common.logging.MoPubLog.d(r7)     // Catch:{ Exception -> 0x008e }
            java.util.Map r7 = java.util.Collections.emptyMap()     // Catch:{ Exception -> 0x008e }
            r6.checkAndInitializeSdk(r12, r7, r4)     // Catch:{ Exception -> 0x008e }
            r1.add(r6)     // Catch:{ Exception -> 0x008e }
            goto L_0x004a
        L_0x008e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r4.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r6 = "Error fetching init settings for network "
            r4.append(r6)     // Catch:{ all -> 0x00ba }
            r4.append(r3)     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x00ba }
            com.mopub.common.logging.MoPubLog.e(r3)     // Catch:{ all -> 0x00ba }
            goto L_0x004a
        L_0x00a3:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r4.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r6 = "Init settings not found for "
            r4.append(r6)     // Catch:{ all -> 0x00ba }
            r4.append(r3)     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x00ba }
            com.mopub.common.logging.MoPubLog.d(r3)     // Catch:{ all -> 0x00ba }
            goto L_0x004a
        L_0x00b8:
            monitor-exit(r0)
            return r1
        L_0x00ba:
            r12 = move-exception
            monitor-exit(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.MoPubRewardedVideoManager.initNetworks(android.app.Activity, java.util.List):java.util.List");
    }

    public static synchronized void init(@NonNull Activity activity, MediationSettings... mediationSettingsArr) {
        synchronized (MoPubRewardedVideoManager.class) {
            if (sInstance == null) {
                sInstance = new MoPubRewardedVideoManager(activity, mediationSettingsArr);
            } else {
                MoPubLog.e("Tried to call initializeRewardedVideo more than once. Only the first initialization call has any effect.");
            }
        }
    }

    @ReflectionTarget
    public static void updateActivity(@Nullable Activity activity) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            moPubRewardedVideoManager.mMainActivity = new WeakReference<>(activity);
        } else {
            logErrorNotInitialized();
        }
    }

    @Nullable
    public static <T extends MediationSettings> T getGlobalMediationSettings(@NonNull Class<T> cls) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager == null) {
            logErrorNotInitialized();
            return null;
        }
        for (MediationSettings mediationSettings : moPubRewardedVideoManager.mGlobalMediationSettings) {
            if (cls.equals(mediationSettings.getClass())) {
                return (MediationSettings) cls.cast(mediationSettings);
            }
        }
        return null;
    }

    @Nullable
    public static <T extends MediationSettings> T getInstanceMediationSettings(@NonNull Class<T> cls, @NonNull String str) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager == null) {
            logErrorNotInitialized();
            return null;
        }
        Set<MediationSettings> set = (Set) moPubRewardedVideoManager.mInstanceMediationSettings.get(str);
        if (set == null) {
            return null;
        }
        for (MediationSettings mediationSettings : set) {
            if (cls.equals(mediationSettings.getClass())) {
                return (MediationSettings) cls.cast(mediationSettings);
            }
        }
        return null;
    }

    public static void setVideoListener(@Nullable MoPubRewardedVideoListener moPubRewardedVideoListener) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            moPubRewardedVideoManager.mVideoListener = moPubRewardedVideoListener;
        } else {
            logErrorNotInitialized();
        }
    }

    public static void loadVideo(@NonNull final String str, @Nullable RequestParameters requestParameters, @Nullable MediationSettings... mediationSettingsArr) {
        String str2;
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager == null) {
            logErrorNotInitialized();
        } else if (str.equals(moPubRewardedVideoManager.mRewardedAdData.getCurrentlyShowingAdUnitId())) {
            MoPubLog.d(String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. The ad is already showing.", new Object[]{str}));
        } else if (sInstance.rewardedAdsLoaders.canPlay(str)) {
            MoPubLog.d(String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. This ad unit already finished loading and is ready to show.", new Object[]{str}));
            postToInstance(new Runnable() {
                public void run() {
                    if (MoPubRewardedVideoManager.sInstance.mVideoListener != null) {
                        MoPubRewardedVideoManager.sInstance.mVideoListener.onRewardedVideoLoadSuccess(str);
                    }
                }
            });
        } else {
            HashSet hashSet = new HashSet();
            MoPubCollections.addAllNonNull((Collection<? super T>) hashSet, (T[]) mediationSettingsArr);
            sInstance.mInstanceMediationSettings.put(str, hashSet);
            if (requestParameters == null) {
                str2 = null;
            } else {
                str2 = requestParameters.mCustomerId;
            }
            if (!TextUtils.isEmpty(str2)) {
                sInstance.mRewardedAdData.setCustomerId(str2);
            }
            loadVideo(str, new WebViewAdUrlGenerator(sInstance.mContext, false).withAdUnitId(str).withKeywords(requestParameters == null ? null : requestParameters.mKeywords).withUserDataKeywords((requestParameters == null || !MoPub.canCollectPersonalInformation()) ? null : requestParameters.mUserDataKeywords).withLocation(requestParameters == null ? null : requestParameters.mLocation).generateUrlString(Constants.HOST), (MoPubErrorCode) null);
        }
    }

    private static void loadVideo(@NonNull String str, @NonNull String str2, @Nullable MoPubErrorCode moPubErrorCode) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager == null) {
            logErrorNotInitialized();
        } else {
            moPubRewardedVideoManager.fetchAd(str, str2, moPubErrorCode);
        }
    }

    private void fetchAd(@NonNull String str, @NonNull String str2, @Nullable MoPubErrorCode moPubErrorCode) {
        if (this.rewardedAdsLoaders.isLoading(str)) {
            MoPubLog.d(String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. A request is already pending.", new Object[]{str}));
        } else if (this.rewardedAdsLoaders.isLoading(str)) {
            MoPubLog.d(String.format(Locale.US, "Did not queue rewarded ad request for ad unit %s. A request is already pending.", new Object[]{str}));
        } else {
            MoPubLog.d(String.format(Locale.US, "Loading rewarded ad request for ad unit %s with URL %s", new Object[]{str, str2}));
            this.rewardedAdsLoaders.loadNextAd(this.mContext, str, str2, moPubErrorCode);
        }
    }

    public static boolean hasVideo(@NonNull String str) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            return isPlayable(str, moPubRewardedVideoManager.mRewardedAdData.getCustomEvent(str));
        }
        logErrorNotInitialized();
        return false;
    }

    public static void showVideo(@NonNull String str) {
        showVideo(str, null);
    }

    public static void showVideo(@NonNull String str, @Nullable String str2) {
        if (sInstance == null) {
            logErrorNotInitialized();
            return;
        }
        if (str2 != null && str2.length() > 8192) {
            MoPubLog.w(String.format(Locale.US, "Provided rewarded ad custom data parameter longer than supported(%d bytes, %d maximum)", new Object[]{Integer.valueOf(str2.length()), Integer.valueOf(8192)}));
        }
        CustomEventRewardedAd customEvent = sInstance.mRewardedAdData.getCustomEvent(str);
        if (!isPlayable(str, customEvent)) {
            if (sInstance.rewardedAdsLoaders.isLoading(str)) {
                MoPubLog.d("Rewarded ad is not ready to be shown yet.");
            } else {
                MoPubLog.d("No rewarded ad loading or loaded.");
            }
            sInstance.failover(str, MoPubErrorCode.VIDEO_NOT_AVAILABLE);
        } else if (sInstance.mRewardedAdData.getAvailableRewards(str).isEmpty() || sInstance.mRewardedAdData.getMoPubReward(str) != null) {
            sInstance.mRewardedAdData.updateCustomEventLastShownRewardMapping(customEvent.getClass(), sInstance.mRewardedAdData.getMoPubReward(str));
            sInstance.mRewardedAdData.updateAdUnitToCustomDataMapping(str, str2);
            sInstance.mRewardedAdData.setCurrentlyShowingAdUnitId(str);
            customEvent.show();
        } else {
            sInstance.failover(str, MoPubErrorCode.REWARD_NOT_SELECTED);
        }
    }

    private static boolean isPlayable(String str, @Nullable CustomEventRewardedAd customEventRewardedAd) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        return moPubRewardedVideoManager != null && moPubRewardedVideoManager.rewardedAdsLoaders.canPlay(str) && customEventRewardedAd != null && customEventRewardedAd.isReady();
    }

    @NonNull
    public static Set<MoPubReward> getAvailableRewards(@NonNull String str) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            return moPubRewardedVideoManager.mRewardedAdData.getAvailableRewards(str);
        }
        logErrorNotInitialized();
        return Collections.emptySet();
    }

    public static void selectReward(@NonNull String str, @NonNull MoPubReward moPubReward) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            moPubRewardedVideoManager.mRewardedAdData.selectReward(str, moPubReward);
        } else {
            logErrorNotInitialized();
        }
    }

    /* access modifiers changed from: 0000 */
    public void onAdSuccess(AdResponse adResponse) {
        String adUnitId = adResponse.getAdUnitId();
        Integer adTimeoutMillis = adResponse.getAdTimeoutMillis(30000);
        String customEventClassName = adResponse.getCustomEventClassName();
        if (customEventClassName == null) {
            MoPubLog.e("Couldn't create custom event, class name was null.");
            failover(adUnitId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        CustomEventRewardedAd customEvent = this.mRewardedAdData.getCustomEvent(adUnitId);
        if (customEvent != null) {
            customEvent.onInvalidate();
        }
        try {
            final CustomEventRewardedAd customEventRewardedAd = (CustomEventRewardedAd) Reflection.instantiateClassWithEmptyConstructor(customEventClassName, CustomEventRewardedAd.class);
            TreeMap treeMap = new TreeMap();
            treeMap.put(DataKeys.AD_UNIT_ID_KEY, adUnitId);
            treeMap.put(DataKeys.REWARDED_AD_CURRENCY_NAME_KEY, adResponse.getRewardedVideoCurrencyName());
            treeMap.put(DataKeys.REWARDED_AD_CURRENCY_AMOUNT_STRING_KEY, adResponse.getRewardedVideoCurrencyAmount());
            treeMap.put(DataKeys.REWARDED_AD_DURATION_KEY, adResponse.getRewardedDuration());
            treeMap.put(DataKeys.SHOULD_REWARD_ON_CLICK_KEY, Boolean.valueOf(adResponse.shouldRewardOnClick()));
            treeMap.put(DataKeys.AD_REPORT_KEY, new AdReport(adUnitId, ClientMetadata.getInstance(this.mContext), adResponse));
            treeMap.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(Utils.generateUniqueId()));
            treeMap.put("rewarded-ad-customer-id", this.mRewardedAdData.getCustomerId());
            String rewardedCurrencies = adResponse.getRewardedCurrencies();
            this.mRewardedAdData.resetAvailableRewards(adUnitId);
            this.mRewardedAdData.resetSelectedReward(adUnitId);
            if (TextUtils.isEmpty(rewardedCurrencies)) {
                this.mRewardedAdData.updateAdUnitRewardMapping(adUnitId, adResponse.getRewardedVideoCurrencyName(), adResponse.getRewardedVideoCurrencyAmount());
            } else {
                try {
                    parseMultiCurrencyJson(adUnitId, rewardedCurrencies);
                } catch (Exception unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error parsing rewarded currencies JSON header: ");
                    sb.append(rewardedCurrencies);
                    MoPubLog.e(sb.toString());
                    failover(adUnitId, MoPubErrorCode.REWARDED_CURRENCIES_PARSING_ERROR);
                    return;
                }
            }
            this.mRewardedAdData.updateAdUnitToServerCompletionUrlMapping(adUnitId, adResponse.getRewardedVideoCompletionUrl());
            Activity activity = (Activity) this.mMainActivity.get();
            if (activity == null) {
                MoPubLog.d("Could not load custom event because Activity reference was null. Call MoPub#updateActivity before requesting more rewarded ads.");
                this.rewardedAdsLoaders.markFail(adUnitId);
                return;
            }
            AnonymousClass2 r8 = new Runnable() {
                public void run() {
                    MoPubLog.d("Custom Event failed to load rewarded ad in a timely fashion.");
                    MoPubRewardedVideoManager.onRewardedVideoLoadFailure(customEventRewardedAd.getClass(), customEventRewardedAd.getAdNetworkId(), MoPubErrorCode.NETWORK_TIMEOUT);
                    customEventRewardedAd.onInvalidate();
                }
            };
            this.mCustomEventTimeoutHandler.postDelayed(r8, (long) adTimeoutMillis.intValue());
            this.mTimeoutMap.put(adUnitId, r8);
            Map serverExtras = adResponse.getServerExtras();
            if (customEventRewardedAd instanceof CustomEventRewardedVideo) {
                String jSONObject = new JSONObject(serverExtras).toString();
                MoPubLog.d(String.format(Locale.US, "Updating init settings for custom event %s with params %s", new Object[]{customEventClassName, jSONObject}));
                sCustomEventSharedPrefs.edit().putString(customEventClassName, jSONObject).commit();
            }
            MoPubLog.d(String.format(Locale.US, "Loading custom event with class name %s", new Object[]{customEventClassName}));
            customEventRewardedAd.loadCustomEvent(activity, treeMap, serverExtras);
            this.mRewardedAdData.updateAdUnitCustomEventMapping(adUnitId, customEventRewardedAd, customEventRewardedAd.getAdNetworkId());
        } catch (Exception unused2) {
            MoPubLog.e(String.format(Locale.US, "Couldn't create custom event with class name %s", new Object[]{customEventClassName}));
            failover(adUnitId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    /* access modifiers changed from: 0000 */
    public void onAdError(@NonNull VolleyError volleyError, @NonNull String str) {
        MoPubErrorCode moPubErrorCode = MoPubErrorCode.INTERNAL_ERROR;
        if (volleyError instanceof MoPubNetworkError) {
            MoPubNetworkError moPubNetworkError = (MoPubNetworkError) volleyError;
            switch (moPubNetworkError.getReason()) {
                case NO_FILL:
                case WARMING_UP:
                    moPubErrorCode = MoPubErrorCode.NO_FILL;
                    break;
                default:
                    moPubErrorCode = MoPubErrorCode.INTERNAL_ERROR;
                    break;
            }
        }
        if (volleyError instanceof NoConnectionError) {
            moPubErrorCode = MoPubErrorCode.NO_CONNECTION;
        }
        failover(str, moPubErrorCode);
    }

    private void parseMultiCurrencyJson(@NonNull String str, @NonNull String str2) throws JSONException {
        String[] jsonArrayToStringArray = Json.jsonArrayToStringArray((String) Json.jsonStringToMap(str2).get("rewards"));
        if (jsonArrayToStringArray.length == 1) {
            Map jsonStringToMap = Json.jsonStringToMap(jsonArrayToStringArray[0]);
            this.mRewardedAdData.updateAdUnitRewardMapping(str, (String) jsonStringToMap.get("name"), (String) jsonStringToMap.get("amount"));
        }
        for (String jsonStringToMap2 : jsonArrayToStringArray) {
            Map jsonStringToMap3 = Json.jsonStringToMap(jsonStringToMap2);
            this.mRewardedAdData.addAvailableReward(str, (String) jsonStringToMap3.get("name"), (String) jsonStringToMap3.get("amount"));
        }
    }

    /* access modifiers changed from: private */
    public void failover(@NonNull String str, @NonNull MoPubErrorCode moPubErrorCode) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(moPubErrorCode);
        if (!this.rewardedAdsLoaders.hasMoreAds(str) || moPubErrorCode.equals(MoPubErrorCode.EXPIRED)) {
            MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
            if (moPubRewardedVideoListener != null) {
                moPubRewardedVideoListener.onRewardedVideoLoadFailure(str, moPubErrorCode);
                return;
            }
            return;
        }
        loadVideo(str, "", moPubErrorCode);
    }

    /* access modifiers changed from: private */
    public void cancelTimeouts(@NonNull String str) {
        Runnable runnable = (Runnable) this.mTimeoutMap.remove(str);
        if (runnable != null) {
            this.mCustomEventTimeoutHandler.removeCallbacks(runnable);
        }
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoLoadSuccess(@NonNull Class<T> cls, @NonNull String str) {
        postToInstance(new ForEachMoPubIdRunnable(cls, str) {
            /* access modifiers changed from: protected */
            public void forEach(@NonNull String str) {
                MoPubRewardedVideoManager.sInstance.cancelTimeouts(str);
                MoPubRewardedVideoManager.sInstance.rewardedAdsLoaders.creativeDownloadSuccess(str);
                if (MoPubRewardedVideoManager.sInstance.mVideoListener != null) {
                    MoPubRewardedVideoManager.sInstance.mVideoListener.onRewardedVideoLoadSuccess(str);
                }
            }
        });
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoLoadFailure(@NonNull Class<T> cls, String str, final MoPubErrorCode moPubErrorCode) {
        postToInstance(new ForEachMoPubIdRunnable(cls, str) {
            /* access modifiers changed from: protected */
            public void forEach(@NonNull String str) {
                MoPubRewardedVideoManager.sInstance.cancelTimeouts(str);
                MoPubRewardedVideoManager.sInstance.failover(str, moPubErrorCode);
            }
        });
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoStarted(@NonNull Class<T> cls, String str) {
        final String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new ForEachMoPubIdRunnable(cls, str) {
                /* access modifiers changed from: protected */
                public void forEach(@NonNull String str) {
                    MoPubRewardedVideoManager.onRewardedVideoStartedAction(str);
                }
            });
        } else {
            postToInstance(new Runnable() {
                public void run() {
                    MoPubRewardedVideoManager.onRewardedVideoStartedAction(currentlyShowingAdUnitId);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void onRewardedVideoStartedAction(@NonNull String str) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
        if (moPubRewardedVideoListener != null) {
            moPubRewardedVideoListener.onRewardedVideoStarted(str);
        }
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        moPubRewardedVideoManager.rewardedAdsLoaders.onRewardedVideoStarted(str, moPubRewardedVideoManager.mContext);
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoPlaybackError(@NonNull Class<T> cls, String str, final MoPubErrorCode moPubErrorCode) {
        final String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new ForEachMoPubIdRunnable(cls, str) {
                /* access modifiers changed from: protected */
                public void forEach(@NonNull String str) {
                    MoPubRewardedVideoManager.onRewardedVideoPlaybackErrorAction(str, moPubErrorCode);
                }
            });
        } else {
            postToInstance(new Runnable() {
                public void run() {
                    MoPubRewardedVideoManager.onRewardedVideoPlaybackErrorAction(currentlyShowingAdUnitId, moPubErrorCode);
                }
            });
        }
        sInstance.mRewardedAdData.setCurrentlyShowingAdUnitId(null);
    }

    /* access modifiers changed from: private */
    public static void onRewardedVideoPlaybackErrorAction(@NonNull String str, @NonNull MoPubErrorCode moPubErrorCode) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(moPubErrorCode);
        sInstance.rewardedAdsLoaders.markFail(str);
        MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
        if (moPubRewardedVideoListener != null) {
            moPubRewardedVideoListener.onRewardedVideoPlaybackError(str, moPubErrorCode);
        }
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoClicked(@NonNull Class<T> cls, String str) {
        final String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new ForEachMoPubIdRunnable(cls, str) {
                /* access modifiers changed from: protected */
                public void forEach(@NonNull String str) {
                    MoPubRewardedVideoManager.onRewardedVideoClickedAction(str);
                }
            });
        } else {
            postToInstance(new Runnable() {
                public void run() {
                    MoPubRewardedVideoManager.onRewardedVideoClickedAction(currentlyShowingAdUnitId);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void onRewardedVideoClickedAction(@NonNull String str) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
        if (moPubRewardedVideoListener != null) {
            moPubRewardedVideoListener.onRewardedVideoClicked(str);
        }
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        moPubRewardedVideoManager.rewardedAdsLoaders.onRewardedVideoClicked(str, moPubRewardedVideoManager.mContext);
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoClosed(@NonNull Class<T> cls, String str) {
        final String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        if (TextUtils.isEmpty(currentlyShowingAdUnitId)) {
            postToInstance(new ForEachMoPubIdRunnable(cls, str) {
                /* access modifiers changed from: protected */
                public void forEach(@NonNull String str) {
                    MoPubRewardedVideoManager.onRewardedVideoClosedAction(str);
                }
            });
        } else {
            postToInstance(new Runnable() {
                public void run() {
                    MoPubRewardedVideoManager.onRewardedVideoClosedAction(currentlyShowingAdUnitId);
                }
            });
        }
        sInstance.mRewardedAdData.setCurrentlyShowingAdUnitId(null);
    }

    /* access modifiers changed from: private */
    public static void onRewardedVideoClosedAction(@NonNull String str) {
        Preconditions.checkNotNull(str);
        sInstance.rewardedAdsLoaders.markPlayed(str);
        MoPubRewardedVideoListener moPubRewardedVideoListener = sInstance.mVideoListener;
        if (moPubRewardedVideoListener != null) {
            moPubRewardedVideoListener.onRewardedVideoClosed(str);
        }
    }

    public static <T extends CustomEventRewardedAd> void onRewardedVideoCompleted(@NonNull Class<T> cls, String str, @NonNull MoPubReward moPubReward) {
        String currentlyShowingAdUnitId = sInstance.mRewardedAdData.getCurrentlyShowingAdUnitId();
        rewardOnClient(cls, str, moPubReward, currentlyShowingAdUnitId);
        rewardOnServer(currentlyShowingAdUnitId);
    }

    private static void rewardOnServer(@Nullable final String str) {
        final String serverCompletionUrl = sInstance.mRewardedAdData.getServerCompletionUrl(str);
        if (!TextUtils.isEmpty(serverCompletionUrl)) {
            postToInstance(new Runnable() {
                public void run() {
                    String str;
                    String str2;
                    MoPubReward moPubReward = MoPubRewardedVideoManager.sInstance.mRewardedAdData.getMoPubReward(str);
                    if (moPubReward == null) {
                        str = "";
                    } else {
                        str = moPubReward.getLabel();
                    }
                    if (moPubReward == null) {
                        str2 = Integer.toString(0);
                    } else {
                        str2 = Integer.toString(moPubReward.getAmount());
                    }
                    CustomEventRewardedAd customEvent = MoPubRewardedVideoManager.sInstance.mRewardedAdData.getCustomEvent(str);
                    RewardedVideoCompletionRequestHandler.makeRewardedVideoCompletionRequest(MoPubRewardedVideoManager.sInstance.mContext, serverCompletionUrl, MoPubRewardedVideoManager.sInstance.mRewardedAdData.getCustomerId(), str, str2, (customEvent == null || customEvent.getClass() == null) ? null : customEvent.getClass().getName(), MoPubRewardedVideoManager.sInstance.mRewardedAdData.getCustomData(str));
                }
            });
        }
    }

    private static <T extends CustomEventRewardedAd> void rewardOnClient(@NonNull final Class<T> cls, @Nullable final String str, @NonNull final MoPubReward moPubReward, @Nullable final String str2) {
        postToInstance(new Runnable() {
            public void run() {
                MoPubReward chooseReward = MoPubRewardedVideoManager.chooseReward(MoPubRewardedVideoManager.sInstance.mRewardedAdData.getLastShownMoPubReward(cls), moPubReward);
                HashSet hashSet = new HashSet();
                if (TextUtils.isEmpty(str2)) {
                    hashSet.addAll(MoPubRewardedVideoManager.sInstance.mRewardedAdData.getMoPubIdsForAdNetwork(cls, str));
                } else {
                    hashSet.add(str2);
                }
                if (MoPubRewardedVideoManager.sInstance.mVideoListener != null) {
                    MoPubRewardedVideoManager.sInstance.mVideoListener.onRewardedVideoCompleted(hashSet, chooseReward);
                }
            }
        });
    }

    @VisibleForTesting
    static MoPubReward chooseReward(@Nullable MoPubReward moPubReward, @NonNull MoPubReward moPubReward2) {
        if (!moPubReward2.isSuccessful()) {
            return moPubReward2;
        }
        if (moPubReward == null) {
            moPubReward = moPubReward2;
        }
        return moPubReward;
    }

    private static void postToInstance(@NonNull Runnable runnable) {
        MoPubRewardedVideoManager moPubRewardedVideoManager = sInstance;
        if (moPubRewardedVideoManager != null) {
            moPubRewardedVideoManager.mCallbackHandler.post(runnable);
        }
    }

    private static void logErrorNotInitialized() {
        MoPubLog.e("MoPub rewarded ads must be initialized with an Activity Context before calling rewarded ads methods.");
    }
}
