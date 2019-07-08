package com.myfitnesspal.feature.profile.util;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;

public final class ProfileViewUtil {
    public static void addTopPaddingIfAdVisible(PremiumService premiumService, View view, int i) {
        if (premiumService.getFeatureAvailability(PremiumFeature.AdFree) != Availability.Available) {
            removeTopPadding(view);
        } else {
            addTopPadding(view, i);
        }
    }

    public static void addTopPadding(View view, int i) {
        setTopPadding(view, view.getResources().getDimensionPixelSize(i));
    }

    public static void removeTopPadding(View view) {
        setTopPadding(view, 0);
    }

    private static void setTopPadding(View view, int i) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams.topMargin != i) {
            marginLayoutParams.topMargin = i;
            view.requestLayout();
        }
    }
}
