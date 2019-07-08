package com.myfitnesspal.feature.externalsync.impl.shealth.util;

import com.myfitnesspal.feature.externalsync.impl.shealth.constants.SHealthConstants;
import com.myfitnesspal.shared.model.v2.MfpAppImage;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Arrays;

public class SHealthUtil {
    private static final int STEP_GOAL_DEFAULT = 10000;

    public static MfpPlatformApp createMockPlatformApp() {
        MfpPlatformApp mfpPlatformApp = new MfpPlatformApp();
        mfpPlatformApp.setAppId(SHealthConstants.getAppId());
        mfpPlatformApp.setClientId(SHealthConstants.getClientId());
        mfpPlatformApp.setClientName("Samsung");
        mfpPlatformApp.setName("Samsung Health");
        mfpPlatformApp.setId(1230875);
        mfpPlatformApp.setAppCategories(new ArrayList(Arrays.asList(new String[]{"Featured", "Activity Trackers"})));
        mfpPlatformApp.setThumbnailImage(new MfpAppImage());
        mfpPlatformApp.getThumbnailImage().setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_362_1_20_0_60420.png");
        mfpPlatformApp.setIconImage(new MfpAppImage());
        mfpPlatformApp.getIconImage().setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_362_1_20_0_60420.png");
        mfpPlatformApp.setAppShortDescription("Lifestyle companion to track your fitness, weight, diet, food & sleep.");
        mfpPlatformApp.setAppLongDescription("S Health provides core features to keep up your body fit and healthy. It will record and analyze your daily activities and habits to help maintain successful diet and lead healthy lifestyle.");
        mfpPlatformApp.setScreenshotImages(new ArrayList(Arrays.asList(new MfpAppImage[]{new MfpAppImage(), new MfpAppImage(), new MfpAppImage(), new MfpAppImage()})));
        ((MfpAppImage) mfpPlatformApp.getScreenshotImages().get(0)).setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_362_3_20_0_65171.png");
        ((MfpAppImage) mfpPlatformApp.getScreenshotImages().get(1)).setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_362_3_20_0_65242.png");
        ((MfpAppImage) mfpPlatformApp.getScreenshotImages().get(2)).setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_362_3_20_0_70485.png");
        ((MfpAppImage) mfpPlatformApp.getScreenshotImages().get(3)).setFilename("http://dakd0cjsv8wfa.cloudfront.net/images/api_clients/0/api_clients_362_3_20_0_19294.png");
        return mfpPlatformApp;
    }

    public static boolean isSHealthStepsSource(MfpStepSource mfpStepSource) {
        return mfpStepSource != null && Strings.equals(mfpStepSource.getAppId(), SHealthConstants.getAppId()) && Strings.equals(mfpStepSource.getClientId(), SHealthConstants.getClientId());
    }

    public static MfpStepSource createStepSource(Session session) {
        MfpStepSource mfpStepSource = new MfpStepSource();
        mfpStepSource.setClientId(SHealthConstants.getClientId());
        mfpStepSource.setAppId(SHealthConstants.getAppId());
        mfpStepSource.setDeviceType(null);
        MfpGoalPreferences goalPreferences = session.getUser().getGoalPreferences();
        mfpStepSource.setStepGoal(goalPreferences != null ? goalPreferences.getDailyStepGoal() : 10000);
        mfpStepSource.setPrimary(false);
        mfpStepSource.setStepGoalEditable(true);
        return mfpStepSource;
    }
}
