package com.myfitnesspal.feature.payments.service;

import android.content.Context;
import android.content.Intent;
import com.myfitnesspal.feature.payments.model.MfpProduct;

public interface PaymentService {
    Intent getStartIntent(Context context, MfpProduct mfpProduct);

    boolean isPaymentProviderAvailable(String str);
}
