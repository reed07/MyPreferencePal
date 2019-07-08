package com.crashlytics.android.answers;

final class SessionEventMetadata {
    public final String advertisingId;
    public final String androidId;
    public final String appBundleId;
    public final String appVersionCode;
    public final String appVersionName;
    public final String betaDeviceToken;
    public final String buildId;
    public final String deviceModel;
    public final String executionId;
    public final String installationId;
    public final Boolean limitAdTrackingEnabled;
    public final String osVersion;
    private String stringRepresentation;

    public SessionEventMetadata(String str, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.appBundleId = str;
        this.executionId = str2;
        this.installationId = str3;
        this.androidId = str4;
        this.advertisingId = str5;
        this.limitAdTrackingEnabled = bool;
        this.betaDeviceToken = str6;
        this.buildId = str7;
        this.osVersion = str8;
        this.deviceModel = str9;
        this.appVersionCode = str10;
        this.appVersionName = str11;
    }

    public String toString() {
        if (this.stringRepresentation == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("appBundleId=");
            sb.append(this.appBundleId);
            sb.append(", executionId=");
            sb.append(this.executionId);
            sb.append(", installationId=");
            sb.append(this.installationId);
            sb.append(", androidId=");
            sb.append(this.androidId);
            sb.append(", advertisingId=");
            sb.append(this.advertisingId);
            sb.append(", limitAdTrackingEnabled=");
            sb.append(this.limitAdTrackingEnabled);
            sb.append(", betaDeviceToken=");
            sb.append(this.betaDeviceToken);
            sb.append(", buildId=");
            sb.append(this.buildId);
            sb.append(", osVersion=");
            sb.append(this.osVersion);
            sb.append(", deviceModel=");
            sb.append(this.deviceModel);
            sb.append(", appVersionCode=");
            sb.append(this.appVersionCode);
            sb.append(", appVersionName=");
            sb.append(this.appVersionName);
            this.stringRepresentation = sb.toString();
        }
        return this.stringRepresentation;
    }
}
