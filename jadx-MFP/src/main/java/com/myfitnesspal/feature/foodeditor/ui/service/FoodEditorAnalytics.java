package com.myfitnesspal.feature.foodeditor.ui.service;

import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.model.v2.MfpFood;
import java.util.Date;

public interface FoodEditorAnalytics {
    void reportFoodAddedToDiary(String str, MfpFood mfpFood, String str2, String str3, String str4, Date date, TimeValue timeValue, String str5, int i);

    void reportFoodAddedToDiary(String str, MfpFood mfpFood, String str2, String str3, String str4, Date date, String str5, int i, int i2, String str6, TimeValue timeValue, String str7, int i3);

    void reportFoodDeletion(String str, int i);

    void reportPairedFoodsLogged(int i);

    void reportRecipeAddedToDiary(String str, Date date, TimeValue timeValue);

    void reportSponsoredFoodBannerClicked(String str, String str2, String str3, String str4);

    void reportSponsoredFoodBannerDisplayed(String str, String str2, String str3, String str4);

    void reportSponsoredFoodLogged(MfpFood mfpFood, String str, String str2, String str3, Date date, int i, FoodEditorAnalyticsExtras foodEditorAnalyticsExtras, int i2);
}
