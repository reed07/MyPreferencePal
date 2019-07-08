package com.myfitnesspal.feature.payments.exception;

import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.shared.api.ApiException;

public class PaymentException extends Exception {
    private final ApiException apiException;
    private final MfpProduct product;
    private final String reason;

    public PaymentException(MfpProduct mfpProduct, ApiException apiException2) {
        this.product = mfpProduct;
        this.apiException = apiException2;
        this.reason = null;
    }

    public PaymentException(MfpProduct mfpProduct, String str) {
        this.product = mfpProduct;
        this.apiException = null;
        this.reason = str;
    }

    public MfpProduct getProduct() {
        return this.product;
    }

    public ApiException getApiException() {
        return this.apiException;
    }

    public String getReason() {
        return this.reason;
    }
}
