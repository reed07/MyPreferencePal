package com.uacf.identity.internal.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import io.uacf.core.app.UacfAppId;

public class IdmVerifyEmailRequest {
    @Expose
    private String appName;

    public IdmVerifyEmailRequest(@NonNull UacfAppId uacfAppId) {
        this.appName = uacfAppId.name();
    }
}
