package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.constants.Constants.Payments;
import com.uacf.core.util.Strings;

public class MfpBillingDetails implements Parcelable {
    public static final Creator<MfpBillingDetails> CREATOR = new Creator<MfpBillingDetails>() {
        public MfpBillingDetails createFromParcel(Parcel parcel) {
            return new MfpBillingDetails(parcel);
        }

        public MfpBillingDetails[] newArray(int i) {
            return new MfpBillingDetails[i];
        }
    };
    @Expose
    private String billingProfileId;
    @Expose
    private String countryCode;
    @Expose
    private String coupon;
    @Expose
    private String externalTransactionId;
    @Expose
    private String paymentProvider;
    @Expose
    private String paymentReceipt;

    public int describeContents() {
        return 0;
    }

    public MfpBillingDetails() {
    }

    public MfpBillingDetails(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getCoupon() {
        return this.coupon;
    }

    public void setCoupon(String str) {
        this.coupon = str;
    }

    public String getPaymentProvider() {
        return this.paymentProvider;
    }

    public void setPaymentProvider(String str) {
        this.paymentProvider = str;
    }

    public String getPaymentReceipt() {
        return this.paymentReceipt;
    }

    public void setPaymentReceipt(String str) {
        this.paymentReceipt = str;
    }

    public String getBillingProfileId() {
        return this.billingProfileId;
    }

    public void setBillingProfileId(String str) {
        this.billingProfileId = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getExternalTransactionId() {
        if (Strings.isEmpty(this.externalTransactionId) || Payments.MISSING_ORDER_ID.equals(this.externalTransactionId)) {
            return null;
        }
        return this.externalTransactionId;
    }

    public void setExternalTransactionId(String str) {
        this.externalTransactionId = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.billingProfileId);
        parcel.writeString(this.coupon);
        parcel.writeString(this.paymentProvider);
        parcel.writeString(this.paymentReceipt);
        parcel.writeString(this.countryCode);
        parcel.writeString(this.externalTransactionId);
    }

    private void readFromParcel(Parcel parcel) {
        this.billingProfileId = parcel.readString();
        this.coupon = parcel.readString();
        this.paymentProvider = parcel.readString();
        this.paymentReceipt = parcel.readString();
        this.countryCode = parcel.readString();
        this.externalTransactionId = parcel.readString();
    }
}
