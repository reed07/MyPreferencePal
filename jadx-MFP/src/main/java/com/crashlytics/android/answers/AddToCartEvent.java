package com.crashlytics.android.answers;

import java.math.BigDecimal;

public class AddToCartEvent extends PredefinedEvent<AddToCartEvent> {
    static final BigDecimal MICRO_CONSTANT = BigDecimal.valueOf(1000000);

    /* access modifiers changed from: 0000 */
    public String getPredefinedType() {
        return "addToCart";
    }
}
