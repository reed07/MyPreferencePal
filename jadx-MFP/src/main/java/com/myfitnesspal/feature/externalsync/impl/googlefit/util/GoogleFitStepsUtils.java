package com.myfitnesspal.feature.externalsync.impl.googlefit.util;

import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpAppImage;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Arrays;

public final class GoogleFitStepsUtils {
    private static final String GOOGLE_FIT_CLIENT_ID = "mfp-mobile-android-google";
    private static final int STEP_GOAL_DEFAULT = 10000;
    private static MfpStepSource googleFitStepSource;

    public static boolean isGoogleFitStepsActive(StepService stepService) {
        return Enumerable.firstOrDefault(stepService.getStepSources(), new ReturningFunction1<Boolean, MfpStepSource>() {
            public Boolean execute(MfpStepSource mfpStepSource) {
                return Boolean.valueOf(mfpStepSource.isPrimary() && GoogleFitStepsUtils.isGoogleFitStepSource(mfpStepSource));
            }
        }) != null;
    }

    public static boolean isGoogleFitStepSource(MfpStepSource mfpStepSource) {
        return mfpStepSource != null && isGoogleFitStepSource(mfpStepSource.getClientId(), mfpStepSource.getAppId());
    }

    public static boolean isGoogleFitStepSource(ExerciseEntry exerciseEntry) {
        if (exerciseEntry.getStepsData() == null) {
            return false;
        }
        return isGoogleFitStepSource(exerciseEntry.getStepsData().getClientId(), exerciseEntry.extraPropertyNamed("app_id"));
    }

    public static boolean isGoogleFitStepSource(String str, String str2) {
        boolean z = false;
        if (!Strings.isEmpty(str2)) {
            return false;
        }
        if (Strings.equals(str, "google_fit") || Strings.equals(str, "mfp-mobile-android-google")) {
            z = true;
        }
        return z;
    }

    public static MfpStepSource createGoogleFitStepSource(Session session) {
        MfpStepSource mfpStepSource = googleFitStepSource;
        if (mfpStepSource != null) {
            return mfpStepSource;
        }
        MfpStepSource mfpStepSource2 = new MfpStepSource();
        mfpStepSource2.setClientId("mfp-mobile-android-google");
        mfpStepSource2.setAppId(null);
        mfpStepSource2.setDeviceType(null);
        MfpGoalPreferences goalPreferences = session.getUser().getGoalPreferences();
        mfpStepSource2.setStepGoal(goalPreferences != null ? goalPreferences.getDailyStepGoal() : 10000);
        mfpStepSource2.setPrimary(false);
        mfpStepSource2.setStepGoalEditable(true);
        googleFitStepSource = mfpStepSource2;
        return mfpStepSource2;
    }

    public static MfpPlatformApp createMockPlatformApp() {
        MfpPlatformApp mfpPlatformApp = new MfpPlatformApp();
        mfpPlatformApp.setAppId("7965e92e6fed");
        mfpPlatformApp.setClientId("google_fit");
        mfpPlatformApp.setClientName("Google");
        mfpPlatformApp.setName("GoogleFit");
        mfpPlatformApp.setId(337);
        mfpPlatformApp.setAppCategories(new ArrayList(Arrays.asList(new String[]{"Featured", "Activity Trackers"})));
        mfpPlatformApp.setThumbnailImage(new MfpAppImage());
        mfpPlatformApp.getThumbnailImage().setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_337_4_20_0_47405.png");
        mfpPlatformApp.setIconImage(new MfpAppImage());
        mfpPlatformApp.getIconImage().setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_337_1_20_0_61931.png");
        mfpPlatformApp.setAppShortDescription("Connect Google Fit to MyFitnessPal to see your nutrition data in Google Fit, and to see your Google Fit activity data in MyFitnessPal. Net out your caloric intake in MyFitnessPal by seamlessly tracking the calories you burn throughout the day with Google Fit.");
        mfpPlatformApp.setAppLongDescription("Connect Google Fit to MyFitnessPal to see your nutrition data in Google Fit, and to see your Google Fit activity data in MyFitnessPal. Net out your caloric intake in MyFitnessPal by seamlessly tracking the calories you burn throughout the day with Google Fit.");
        mfpPlatformApp.setScreenshotImages(new ArrayList(Arrays.asList(new MfpAppImage[]{new MfpAppImage(), new MfpAppImage()})));
        ((MfpAppImage) mfpPlatformApp.getScreenshotImages().get(0)).setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_337_1_20_0_61931.png");
        ((MfpAppImage) mfpPlatformApp.getScreenshotImages().get(1)).setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_337_4_20_0_47405.png");
        return mfpPlatformApp;
    }
}
