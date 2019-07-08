package com.mopub.common.privacy;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;

class ConsentDialogResponse {
    @NonNull
    private final String mHtml;

    ConsentDialogResponse(@NonNull String str) {
        Preconditions.checkNotNull(str);
        this.mHtml = str;
    }

    @NonNull
    public String getHtml() {
        return this.mHtml;
    }
}
