package com.uacf.identity.internal.sdk;

import io.uacf.core.app.UacfUserAccountDomain;

public class Utils {
    public static int getDomainValue(UacfUserAccountDomain uacfUserAccountDomain) {
        if (uacfUserAccountDomain != null) {
            switch (uacfUserAccountDomain) {
                case UACF:
                    return 5;
                case ECOMM_NA:
                case ECOMM_EU:
                case ECOMM_SEA:
                case ECOMM_MX:
                case ECOMM_CL:
                case ECOMM_AU:
                case ECOMM_OC:
                case ECOMM_KR:
                case ECOMM_TR:
                case ECOMM_BR:
                    return 3;
                case MMF:
                    return 4;
                case MFP:
                    return 2;
                case ENDO:
                    return 1;
            }
        }
        return 0;
    }
}
