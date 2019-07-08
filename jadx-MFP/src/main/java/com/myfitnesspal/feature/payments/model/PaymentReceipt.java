package com.myfitnesspal.feature.payments.model;

public abstract class PaymentReceipt {
    protected String countryCode;
    protected String coupon;
    protected String orderId;
    protected String payload;
    protected String paymentProvider;
    protected String paymentSessionId;
    protected MfpProduct product;
    protected String productId;

    public abstract long getId();

    public String getPaymentSessionId() {
        return this.paymentSessionId;
    }

    public String getProductId() {
        return this.productId;
    }

    public MfpProduct getProduct() {
        return this.product;
    }

    public String getPaymentProvider() {
        return this.paymentProvider;
    }

    public String getPayload() {
        return this.payload;
    }

    public String getCoupon() {
        return this.coupon;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getOrderId() {
        return this.orderId;
    }
}
