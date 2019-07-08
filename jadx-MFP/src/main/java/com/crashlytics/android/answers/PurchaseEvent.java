package com.crashlytics.android.answers;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.math.BigDecimal;

public class PurchaseEvent extends PredefinedEvent<PurchaseEvent> {
    static final BigDecimal MICRO_CONSTANT = BigDecimal.valueOf(1000000);

    /* access modifiers changed from: 0000 */
    public String getPredefinedType() {
        return ProductAction.ACTION_PURCHASE;
    }
}
