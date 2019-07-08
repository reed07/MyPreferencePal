package com.myfitnesspal.feature.appgallery.service;

import com.myfitnesspal.feature.appgallery.api.ExerciseTrackingAppRecommendation;
import com.myfitnesspal.feature.appgallery.util.AppStateUtil;
import com.myfitnesspal.feature.externalsync.impl.shealth.util.SHealthUtil;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.MfpApiUtil;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.constants.Constants.ABTest.GoogleFit201511;
import com.myfitnesspal.shared.constants.Constants.Partner;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.model.v2.MfpPlatformAppOptions.AppType;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.caching.Cache;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;

public class AppGalleryServiceImpl implements AppGalleryService {
    /* access modifiers changed from: private */
    public static List<MfpPlatformApp> allAppsLocalCache;
    private static AtomicBoolean localCacheRequestRunning = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final Lazy<ConfigService> configService;
    private Set<String> connectedAppClientIds = new HashSet();
    private final DeviceInfo deviceInfo;
    private final Provider<MfpV2Api> platformAppsApi;
    private final Cache<ApiResponse<MfpPlatformApp>> responseCache;
    private final Lazy<Session> session;

    private enum OptionsType {
        Default,
        Recommended
    }

    public AppGalleryServiceImpl(Provider<MfpV2Api> provider, Cache<ApiResponse<MfpPlatformApp>> cache, DeviceInfo deviceInfo2, Lazy<Session> lazy, Lazy<ConfigService> lazy2) {
        this.platformAppsApi = provider;
        this.responseCache = cache;
        this.deviceInfo = deviceInfo2;
        this.session = lazy;
        this.configService = lazy2;
    }

    public List<MfpPlatformApp> getAllAppsFromCache() {
        List<MfpPlatformApp> list = allAppsLocalCache;
        return list != null ? new ArrayList(list) : list;
    }

    public void getAppListAsync(final String str, final Function1<List<MfpPlatformApp>> function1, final MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        try {
            if (Strings.equals(str, "all") || Strings.equals(str, AppType.GRANTED) || Strings.equals(str, AppType.FEATURED)) {
                List platformAppOptions = getPlatformAppOptions(OptionsType.Default);
                platformAppOptions.add(Http.PLATFORM_APPS_TYPE);
                platformAppOptions.add(Strings.toString(str));
                ((MfpV2Api) ((MfpV2Api) this.platformAppsApi.get()).withOutputType(API_RESPONSE_MAPPER.class)).getAsync(Uri.APP_LIST, (Function1<T>) new Function1<ApiResponse<MfpPlatformApp>>() {
                    public void execute(ApiResponse<MfpPlatformApp> apiResponse) {
                        FunctionUtils.invokeIfValid(function1, apiResponse.getItems());
                        AppGalleryServiceImpl.checkInjectSHealthApp(AppGalleryServiceImpl.this.configService, str, apiResponse.getItems());
                        if ("all".equals(str)) {
                            AppGalleryServiceImpl.allAppsLocalCache = apiResponse.getItems();
                        }
                    }
                }, (ApiErrorCallback) new ApiErrorCallback() {
                    public void execute(ApiException apiException) {
                        FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(apiException));
                    }
                }, platformAppOptions.toArray(new String[platformAppOptions.size()]));
                localCacheRequestRunning.set(false);
                return;
            }
            ApiResponseBase apiResponseBase = new ApiResponseBase();
            apiResponseBase.setError("Invalid type param");
            FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, apiResponseBase);
        } finally {
            localCacheRequestRunning.set(false);
        }
    }

    public MfpPlatformApp findByAppId(String str) {
        if (!allAppsCacheExists()) {
            return null;
        }
        for (MfpPlatformApp mfpPlatformApp : allAppsLocalCache) {
            if (Strings.equals(mfpPlatformApp.getAppId(), str)) {
                return mfpPlatformApp;
            }
        }
        return null;
    }

    public MfpPlatformApp findByClientId(String str) {
        if (!allAppsCacheExists()) {
            return null;
        }
        for (MfpPlatformApp mfpPlatformApp : allAppsLocalCache) {
            if (Strings.equals(mfpPlatformApp.getClientId(), str)) {
                return mfpPlatformApp;
            }
        }
        return null;
    }

    public MfpPlatformApp findByStepSource(MfpStepSource mfpStepSource) {
        if (mfpStepSource != null) {
            String appId = mfpStepSource.getAppId();
            final String clientId = mfpStepSource.getClientId();
            if (Strings.notEmpty(appId)) {
                findByAppId(appId);
            } else if (allAppsLocalCache != null) {
                return Strings.equalsIgnoreCase(clientId, Partner.MAPMYFITNESS_CLIENT_ID) ? (MfpPlatformApp) Enumerable.firstOrDefault(allAppsLocalCache, new ReturningFunction1<Boolean, MfpPlatformApp>() {
                    public Boolean execute(MfpPlatformApp mfpPlatformApp) {
                        return Boolean.valueOf(Strings.equalsIgnoreCase(mfpPlatformApp.getClientId(), Partner.MAPMYFITNESS_CLIENT_ID) && Strings.equalsIgnoreCase(mfpPlatformApp.getName(), Partner.UNDER_ARMOUR_RECORD_NAME));
                    }
                }) : (MfpPlatformApp) Enumerable.firstOrDefault(allAppsLocalCache, new ReturningFunction1<Boolean, MfpPlatformApp>() {
                    public Boolean execute(MfpPlatformApp mfpPlatformApp) {
                        return Boolean.valueOf(Strings.equalsIgnoreCase(mfpPlatformApp.getClientId(), clientId));
                    }
                });
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static void checkInjectSHealthApp(Lazy<ConfigService> lazy, String str, List<MfpPlatformApp> list) {
        if (ConfigUtils.isSHealthAppGalleryHackEnabled((ConfigService) lazy.get()) && "all".equals(str)) {
            list.add(0, SHealthUtil.createMockPlatformApp());
        }
    }

    public List<MfpPlatformApp> getAppList(String str) throws ApiException {
        List platformAppOptions = getPlatformAppOptions(OptionsType.Default);
        if (AppType.UACF.equals(str)) {
            platformAppOptions.add(Constants.Http.UACF_PROMOTED);
            platformAppOptions.add(Strings.toString(Boolean.valueOf(true)));
        } else {
            platformAppOptions.add(Http.PLATFORM_APPS_TYPE);
            platformAppOptions.add(Strings.toString(str));
        }
        List<MfpPlatformApp> items = ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.platformAppsApi.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.APP_LIST, platformAppOptions.toArray(new String[platformAppOptions.size()]))).getItems();
        checkInjectSHealthApp(this.configService, str, items);
        if ("all".equals(str)) {
            cacheConnectedAppClientIds(items);
        }
        return items;
    }

    public void getAppDetailsAsync(MfpStepSource mfpStepSource, final Function1<MfpPlatformApp> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        if (mfpStepSource != null) {
            String appId = mfpStepSource.getAppId();
            String clientId = mfpStepSource.getClientId();
            if (Strings.notEmpty(appId)) {
                getAppDetailsAsync(appId, null, function1, mfpV2ApiErrorCallback);
            } else if (Strings.equalsIgnoreCase(clientId, Partner.MAPMYFITNESS_CLIENT_ID)) {
                getAppDetailsForClientIdAndNameAsync(Partner.MAPMYFITNESS_CLIENT_ID, Partner.UNDER_ARMOUR_RECORD_NAME, new Function1<MfpPlatformApp>() {
                    public void execute(MfpPlatformApp mfpPlatformApp) {
                        FunctionUtils.invokeIfValid(function1, mfpPlatformApp);
                    }
                }, mfpV2ApiErrorCallback);
            } else {
                getAppDetailsAsync(null, clientId, function1, mfpV2ApiErrorCallback);
            }
        } else {
            FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, new ApiResponseBase().setError("Invalid type param"));
        }
    }

    public ApiResponseBase disconnectFromApp(String str) throws ApiException {
        String format = String.format(Uri.APP_DISCONNECT, new Object[]{str});
        removeCachedConnectedAppClientId(str);
        return (ApiResponseBase) ((MfpV2Api) ((MfpV2Api) this.platformAppsApi.get()).withOutputType(ApiResponseBase.class)).delete(format);
    }

    public List<ExerciseTrackingAppRecommendation> getExerciseRecommendationApp(String str) throws ApiException {
        List platformAppOptions = getPlatformAppOptions(OptionsType.Recommended);
        platformAppOptions.add("exercise_id");
        platformAppOptions.add(Strings.toString(str));
        return ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.platformAppsApi.get()).withOutputType(ExerciseTrackingAppRecommendation.API_RESPONSE_MAPPER.class)).get(Uri.EXERCISE_TRACKING_APP_RECOMMENDATION, platformAppOptions.toArray(new String[platformAppOptions.size()]))).getItems();
    }

    public void checkIfUserHasConnectedAnyAppsAsync(final Function1<Boolean> function1) {
        if (!isLoggedIn()) {
            FunctionUtils.invokeIfValid(function1, Boolean.valueOf(false));
        } else {
            getAppListAsync(AppType.GRANTED, new Function1<List<MfpPlatformApp>>() {
                public void execute(List<MfpPlatformApp> list) {
                    FunctionUtils.invokeIfValid(function1, Boolean.valueOf(CollectionUtils.size((Collection<?>) list) > 0));
                }
            }, new MfpV2ApiErrorCallback() {
                public void execute(ApiResponseBase apiResponseBase) {
                    FunctionUtils.invokeIfValid(function1, Boolean.valueOf(false));
                }
            });
        }
    }

    public synchronized boolean isAppConnected(String str) {
        return this.connectedAppClientIds.contains(Strings.toString(str).toLowerCase());
    }

    private boolean allAppsCacheExists() {
        if (allAppsLocalCache != null) {
            return true;
        }
        if (!localCacheRequestRunning.get()) {
            localCacheRequestRunning.set(true);
            getAppListAsync("all", null, null);
        }
        return false;
    }

    private List<String> getPlatformAppOptions(OptionsType optionsType) {
        String str = "google";
        String bool = Boolean.toString(((ConfigService) this.configService.get()).isVariantEnabled(GoogleFit201511.NAME));
        ArrayList arrayList = new ArrayList();
        if (optionsType == OptionsType.Default) {
            arrayList.add(Http.PIXEL_RATIO);
            arrayList.add(Strings.toString(Float.valueOf(this.deviceInfo.getDensity())));
            String userId = getUserId();
            if (Strings.notEmpty(userId)) {
                arrayList.add("user_id");
                arrayList.add(userId);
            }
        }
        arrayList.add(Http.PLATFORM_TYPE);
        arrayList.add("android");
        arrayList.add(Http.PLATFORM_SUBTYPE);
        arrayList.add(str);
        if (optionsType != OptionsType.Recommended) {
            arrayList.add(Http.GOOGLE_FIT_ENABLED);
            arrayList.add(bool);
        }
        return arrayList;
    }

    private boolean isLoggedIn() {
        return ((Session) this.session.get()).getUser().isLoggedIn();
    }

    private String getUserId() {
        return ((Session) this.session.get()).getUser().getUserId();
    }

    private synchronized void cacheConnectedAppClientIds(List<MfpPlatformApp> list) {
        HashSet hashSet = new HashSet();
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            for (MfpPlatformApp mfpPlatformApp : list) {
                if (AppStateUtil.isConnected(mfpPlatformApp) && Strings.notEmpty(mfpPlatformApp.getClientId())) {
                    hashSet.add(mfpPlatformApp.getClientId().toLowerCase());
                }
            }
        }
        this.connectedAppClientIds = Collections.unmodifiableSet(hashSet);
    }

    private synchronized void removeCachedConnectedAppClientId(String str) {
        if (Strings.notEmpty(str)) {
            String lowerCase = str.toLowerCase();
            if (this.connectedAppClientIds.contains(lowerCase)) {
                HashSet hashSet = new HashSet(this.connectedAppClientIds);
                hashSet.remove(lowerCase);
                this.connectedAppClientIds = Collections.unmodifiableSet(hashSet);
            }
        }
    }

    private void getAppDetailsAsync(String str, final String str2, final Function1<MfpPlatformApp> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        if (Strings.notEmpty(str)) {
            str2 = str;
        } else if (!Strings.notEmpty(str2)) {
            str2 = null;
        }
        if (str2 != null) {
            final boolean notEmpty = Strings.notEmpty(str);
            getAppListAsync("all", new Function1<List<MfpPlatformApp>>() {
                public void execute(List<MfpPlatformApp> list) {
                    FunctionUtils.invokeIfValid(function1, Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, MfpPlatformApp>() {
                        public Boolean execute(MfpPlatformApp mfpPlatformApp) {
                            return Boolean.valueOf(Strings.equalsIgnoreCase(notEmpty ? mfpPlatformApp.getAppId() : mfpPlatformApp.getClientId(), str2));
                        }
                    }));
                }
            }, mfpV2ApiErrorCallback);
            return;
        }
        FunctionUtils.invokeIfValid(function1, null);
    }

    private void getAppDetailsForClientIdAndNameAsync(final String str, final String str2, final Function1<MfpPlatformApp> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        if (Strings.isEmpty(str) || Strings.isEmpty(str2)) {
            FunctionUtils.invokeIfValid(function1, null);
        }
        getAppListAsync("all", new Function1<List<MfpPlatformApp>>() {
            public void execute(List<MfpPlatformApp> list) {
                FunctionUtils.invokeIfValid(function1, Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, MfpPlatformApp>() {
                    public Boolean execute(MfpPlatformApp mfpPlatformApp) {
                        return Boolean.valueOf(Strings.equalsIgnoreCase(mfpPlatformApp.getClientId(), str) && Strings.equalsIgnoreCase(mfpPlatformApp.getName(), str2));
                    }
                }));
            }
        }, mfpV2ApiErrorCallback);
    }
}
