package com.myfitnesspal.feature.premium.util;

import com.myfitnesspal.feature.payments.model.MfpProduct;
import java.util.Comparator;

/* renamed from: com.myfitnesspal.feature.premium.util.-$$Lambda$ProductUtils$BIvjAMmufo0AFQSyHLbUuRFK-Fk reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ProductUtils$BIvjAMmufo0AFQSyHLbUuRFKFk implements Comparator {
    public static final /* synthetic */ $$Lambda$ProductUtils$BIvjAMmufo0AFQSyHLbUuRFKFk INSTANCE = new $$Lambda$ProductUtils$BIvjAMmufo0AFQSyHLbUuRFKFk();

    private /* synthetic */ $$Lambda$ProductUtils$BIvjAMmufo0AFQSyHLbUuRFKFk() {
    }

    public final int compare(Object obj, Object obj2) {
        return Integer.compare(ProductUtils.getDurationCompareKey((MfpProduct) obj), ProductUtils.getDurationCompareKey((MfpProduct) obj2));
    }
}
