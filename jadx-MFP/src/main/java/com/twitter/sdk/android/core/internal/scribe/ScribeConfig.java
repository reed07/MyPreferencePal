package com.twitter.sdk.android.core.internal.scribe;

public class ScribeConfig {
    public final String baseUrl;
    public final boolean isEnabled;
    public final int maxFilesToKeep;
    public final String pathType;
    public final String pathVersion;
    public final int sendIntervalSeconds;
    public final String sequence;
    public final String userAgent;

    public ScribeConfig(boolean z, String str, String str2, String str3, String str4, String str5, int i, int i2) {
        this.isEnabled = z;
        this.baseUrl = str;
        this.pathVersion = str2;
        this.pathType = str3;
        this.sequence = str4;
        this.userAgent = str5;
        this.maxFilesToKeep = i;
        this.sendIntervalSeconds = i2;
    }
}
