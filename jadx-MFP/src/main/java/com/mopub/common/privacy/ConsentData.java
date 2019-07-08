package com.mopub.common.privacy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface ConsentData {
    @Nullable
    String getConsentedPrivacyPolicyVersion();

    @Nullable
    String getConsentedVendorListIabFormat();

    @Nullable
    String getConsentedVendorListVersion();

    @NonNull
    String getCurrentPrivacyPolicyLink();

    @NonNull
    String getCurrentPrivacyPolicyLink(@Nullable String str);

    @Nullable
    String getCurrentPrivacyPolicyVersion();

    @Nullable
    String getCurrentVendorListIabFormat();

    @NonNull
    String getCurrentVendorListLink();

    @NonNull
    String getCurrentVendorListLink(@Nullable String str);

    @Nullable
    String getCurrentVendorListVersion();

    boolean isForceGdprApplies();
}
