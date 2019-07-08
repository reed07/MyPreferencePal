package com.uacf.identity.internal.util;

import com.uacf.core.util.Tuple2;
import io.uacf.core.app.UacfAppId;

public final class AppIdMigrationUtil {
    public static Tuple2<UacfAppId, UacfAppId> getAppIdMigrationTuple(UacfAppId uacfAppId) {
        UacfAppId uacfAppId2;
        if (uacfAppId != null) {
            switch (uacfAppId) {
                case MFP:
                    uacfAppId2 = UacfAppId.MYFITNESSPAL;
                    break;
                case ENDO:
                    uacfAppId2 = UacfAppId.ENDOMONDO;
                    break;
                case ECOMM:
                    uacfAppId2 = UacfAppId.ECOMMERCE;
                    break;
                case NBA:
                    uacfAppId2 = UacfAppId.NBA_FIT;
                    break;
                default:
                    uacfAppId2 = uacfAppId;
                    break;
            }
            if (uacfAppId2 == uacfAppId) {
                switch (uacfAppId2) {
                    case MYFITNESSPAL:
                        uacfAppId = UacfAppId.MFP;
                        break;
                    case ENDOMONDO:
                        uacfAppId = UacfAppId.ENDO;
                        break;
                    case ECOMMERCE:
                        uacfAppId = UacfAppId.ECOMM;
                        break;
                    case NBA_FIT:
                        uacfAppId = UacfAppId.NBA;
                        break;
                }
            }
        } else {
            uacfAppId2 = uacfAppId;
        }
        return Tuple2.create(uacfAppId, uacfAppId2);
    }
}
