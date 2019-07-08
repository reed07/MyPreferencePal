package com.myfitnesspal.shared.model.v2;

public class MfpPlatformAppOptions {
    private String appCategoryName;
    private float pixelRatio;
    private String platformAppsType;
    private String platformSubtype;
    private String platformType;

    public static final class AppType {
        public static final String ALL = "all";
        public static final String FEATURED = "featured";
        public static final String GRANTED = "granted";
        public static final String UACF = "uacf";
    }

    public static final class PlatformSubtypes {
        public static final String AMAZON = "amazon";
        public static final String GOOGLE = "google";
    }

    public static final class Platforms {
        public static final String ANDROID = "android";
        public static final String IOS = "iosclient";
        public static final String WEB = "web";
    }

    public String getAppCategoryName() {
        return this.appCategoryName;
    }

    public MfpPlatformAppOptions setAppCategoryName(String str) {
        this.appCategoryName = str;
        return this;
    }

    public float getPixelRatio() {
        return this.pixelRatio;
    }

    public MfpPlatformAppOptions setPixelRatio(float f) {
        this.pixelRatio = f;
        return this;
    }

    public String getPlatformAppsType() {
        return this.platformAppsType;
    }

    public MfpPlatformAppOptions setPlatformAppsType(String str) {
        this.platformAppsType = str;
        return this;
    }

    public String getPlatformSubtype() {
        return this.platformSubtype;
    }

    public MfpPlatformAppOptions setPlatformSubtype(String str) {
        this.platformSubtype = str;
        return this;
    }

    public String getPlatformType() {
        return this.platformType;
    }

    public MfpPlatformAppOptions setPlatformType(String str) {
        this.platformType = str;
        return this;
    }
}
