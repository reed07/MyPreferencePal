package io.uacf.rollouts.internal.service;

import android.os.Build.VERSION;
import com.google.gson.annotations.Expose;
import io.uacf.core.app.UacfUserAccountDomain;

public class VariantsRequest {
    @Expose
    private String application;
    @Expose
    private String customId;
    @Expose
    private String deviceId;
    @Expose
    private UacfUserAccountDomain domain;
    @Expose
    private final String os = "Android";
    @Expose
    private final String osVersion = VERSION.RELEASE;
    @Expose
    private final String platform = "Android";
    @Expose
    private String userId;

    public VariantsRequest(UacfUserAccountDomain uacfUserAccountDomain, String str, String str2, String str3, String str4) {
        this.domain = uacfUserAccountDomain;
        this.userId = str;
        this.deviceId = str2;
        this.customId = str3;
        this.application = str4;
    }
}
