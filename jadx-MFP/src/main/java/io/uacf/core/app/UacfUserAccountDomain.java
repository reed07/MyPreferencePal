package io.uacf.core.app;

public enum UacfUserAccountDomain {
    MMF,
    MFP,
    RECORD,
    ENDO,
    UACF,
    ECOMM_NA,
    ECOMM_EU,
    ECOMM_SEA,
    ECOMM_MX,
    ECOMM_CL,
    ECOMM_AU,
    ECOMM_OC,
    ECOMM_KR,
    ECOMM_TR,
    ECOMM_BR;

    public static UacfUserAccountDomain tryGetValueOf(String str) {
        try {
            return valueOf(str);
        } catch (IllegalArgumentException | NullPointerException unused) {
            return null;
        }
    }
}
