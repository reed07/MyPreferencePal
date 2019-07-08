package com.myfitnesspal.feature.appgallery.service;

import com.myfitnesspal.feature.appgallery.api.ExerciseTrackingAppRecommendation;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.uacf.core.util.Function1;
import java.util.List;

public interface AppGalleryService {
    void checkIfUserHasConnectedAnyAppsAsync(Function1<Boolean> function1);

    ApiResponseBase disconnectFromApp(String str) throws ApiException;

    MfpPlatformApp findByAppId(String str);

    MfpPlatformApp findByClientId(String str);

    MfpPlatformApp findByStepSource(MfpStepSource mfpStepSource);

    List<MfpPlatformApp> getAllAppsFromCache();

    void getAppDetailsAsync(MfpStepSource mfpStepSource, Function1<MfpPlatformApp> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);

    List<MfpPlatformApp> getAppList(String str) throws ApiException;

    void getAppListAsync(String str, Function1<List<MfpPlatformApp>> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);

    List<ExerciseTrackingAppRecommendation> getExerciseRecommendationApp(String str) throws ApiException;

    boolean isAppConnected(String str);
}
