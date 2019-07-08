package com.mopub.common.privacy;

import android.support.annotation.NonNull;

public interface ConsentStatusChangeListener {
    void onConsentStateChange(@NonNull ConsentStatus consentStatus, @NonNull ConsentStatus consentStatus2, boolean z);
}
