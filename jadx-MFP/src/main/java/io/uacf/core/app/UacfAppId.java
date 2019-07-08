package io.uacf.core.app;

public enum UacfAppId {
    MAPMYFITNESS,
    MAPMYHIKE,
    MAPMYRIDE,
    MAPMYRUN,
    MAPMYWALK,
    MAPMYDOGWALK,
    MYFITNESSPAL,
    ENDOMONDO,
    ECOMMERCE,
    ECOMMERCE_INTERNATIONAL,
    UA_SHOP,
    UA_SHOP_SAMSUNG,
    RECORD,
    NBA_FIT,
    UA_TRAINING,
    UARUN,
    MFP,
    ENDO,
    ECOMM,
    NBA,
    MAPMYFITNESS_PLUS,
    MAPMYHIKE_PLUS,
    MAPMYRIDE_PLUS,
    MAPMYRUN_PLUS,
    MAPMYWALK_PLUS;
    
    private AppType appType;

    public enum AppType {
        private static final /* synthetic */ AppType[] $VALUES = null;
        public static final AppType DEBUG = null;
        public static final AppType PRODUCTION = null;
        public static final AppType SAMPLE = null;

        private AppType(String str, int i) {
        }

        public static AppType valueOf(String str) {
            return (AppType) Enum.valueOf(AppType.class, str);
        }

        public static AppType[] values() {
            return (AppType[]) $VALUES.clone();
        }

        static {
            PRODUCTION = new AppType("PRODUCTION", 0);
            DEBUG = new AppType("DEBUG", 1);
            SAMPLE = new AppType("SAMPLE", 2);
            $VALUES = new AppType[]{PRODUCTION, DEBUG, SAMPLE};
        }
    }

    public AppType getAppType() {
        return this.appType;
    }

    public UacfAppId getBaseAppId() {
        switch (this) {
            case MAPMYFITNESS_PLUS:
                return MAPMYFITNESS;
            case MAPMYHIKE_PLUS:
                return MAPMYHIKE;
            case MAPMYRIDE_PLUS:
                return MAPMYRIDE;
            case MAPMYRUN_PLUS:
                return MAPMYRUN;
            case MAPMYWALK_PLUS:
                return MAPMYWALK;
            default:
                return this;
        }
    }

    public String getBaseAppName() {
        return getBaseAppId().toString();
    }

    public static UacfAppId convertFromDeprecatedValue(UacfAppId uacfAppId) {
        switch (uacfAppId) {
            case ENDO:
                return ENDOMONDO;
            case MFP:
                return MYFITNESSPAL;
            case ECOMM:
                return UA_SHOP;
            case NBA:
                return NBA_FIT;
            default:
                return uacfAppId;
        }
    }
}
