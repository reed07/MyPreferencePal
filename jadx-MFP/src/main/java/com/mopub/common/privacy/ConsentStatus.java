package com.mopub.common.privacy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public enum ConsentStatus {
    EXPLICIT_YES("explicit_yes"),
    EXPLICIT_NO("explicit_no"),
    UNKNOWN("unknown"),
    POTENTIAL_WHITELIST("potential_whitelist"),
    DNT("dnt");
    
    @NonNull
    private final String mValue;

    private ConsentStatus(String str) {
        this.mValue = str;
    }

    @NonNull
    public String getValue() {
        return this.mValue;
    }

    @NonNull
    public static ConsentStatus fromString(@Nullable String str) {
        ConsentStatus[] values;
        if (str == null) {
            return UNKNOWN;
        }
        for (ConsentStatus consentStatus : values()) {
            if (str.equals(consentStatus.name())) {
                return consentStatus;
            }
        }
        return UNKNOWN;
    }
}
