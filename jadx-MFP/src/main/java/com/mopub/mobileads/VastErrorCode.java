package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.myfitnesspal.feature.goals.ui.fragment.MealGoalsFragment;

public enum VastErrorCode {
    XML_PARSING_ERROR(MealGoalsFragment.TOTAL_HUNDRED_PERCENT),
    WRAPPER_TIMEOUT("301"),
    NO_ADS_VAST_RESPONSE("303"),
    GENERAL_LINEAR_AD_ERROR("400"),
    GENERAL_COMPANION_AD_ERROR("600"),
    UNDEFINED_ERROR("900");
    
    @NonNull
    private final String mErrorCode;

    private VastErrorCode(String str) {
        Preconditions.checkNotNull(str, "errorCode cannot be null");
        this.mErrorCode = str;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public String getErrorCode() {
        return this.mErrorCode;
    }
}
