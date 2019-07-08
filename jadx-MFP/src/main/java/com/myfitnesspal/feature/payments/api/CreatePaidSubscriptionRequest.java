package com.myfitnesspal.feature.payments.api;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.payments.model.MfpBillingDetails;

public class CreatePaidSubscriptionRequest {
    @Expose
    private MfpBillingDetails billingDetails;
    @Expose
    private String productId;
    @Expose
    private String startDate;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public MfpBillingDetails getBillingDetails() {
        return this.billingDetails;
    }

    public void setBillingDetails(MfpBillingDetails mfpBillingDetails) {
        this.billingDetails = mfpBillingDetails;
    }
}
