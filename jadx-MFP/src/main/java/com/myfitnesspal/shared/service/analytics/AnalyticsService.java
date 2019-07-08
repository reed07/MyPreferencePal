package com.myfitnesspal.shared.service.analytics;

import android.app.Activity;
import android.support.annotation.NonNull;
import java.util.Map;

public interface AnalyticsService {
    void initialize(Activity activity);

    boolean isEnabled();

    void reportEvent(String str);

    void reportEvent(String str, int i);

    void reportEvent(String str, Map<String, String> map);

    void reportEvent(String str, Map<String, String> map, String str2);

    void reportEvent(String str, Map<String, String> map, String str2, int i);

    void reportExerciseLogged(String str, int i, String str2, int i2, String str3, int i3);

    void reportExperimentStart(String str, String str2);

    void reportFoodLookup(Map<String, String> map);

    void reportInstall();

    void reportLogin(String str);

    void reportLogout(String str);

    void reportRegistration();

    void reportRequiredConsents(String str, int i, String[] strArr);

    void reportScreenView(String str);

    void reportScreenView(String str, Map<String, String> map);

    void reportSessionStart();

    void reportUpgrade();

    void reportUserId(String str);

    void restartSession();

    void updateUserPremiumStatus(@NonNull String str);
}
