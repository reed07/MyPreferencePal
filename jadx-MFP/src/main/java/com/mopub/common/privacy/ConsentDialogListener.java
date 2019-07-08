package com.mopub.common.privacy;

import android.support.annotation.NonNull;
import com.mopub.mobileads.MoPubErrorCode;

public interface ConsentDialogListener {
    void onConsentDialogLoadFailed(@NonNull MoPubErrorCode moPubErrorCode);

    void onConsentDialogLoaded();
}
