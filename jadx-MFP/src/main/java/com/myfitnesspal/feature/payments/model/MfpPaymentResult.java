package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MfpPaymentResult implements Parcelable {
    public static final Creator<MfpPaymentResult> CREATOR = new Creator<MfpPaymentResult>() {
        public MfpPaymentResult createFromParcel(Parcel parcel) {
            return new MfpPaymentResult(parcel);
        }

        public MfpPaymentResult[] newArray(int i) {
            return new MfpPaymentResult[i];
        }
    };
    private double amountPaid;
    private String countryCode;
    private String orderId;
    private String paymentSessionId;
    private MfpProduct product;
    private String provider;
    private String receipt;

    public int describeContents() {
        return 0;
    }

    public MfpPaymentResult(MfpProduct mfpProduct, String str, double d, String str2, String str3, String str4, String str5) {
        this.product = mfpProduct;
        this.paymentSessionId = str;
        this.amountPaid = d;
        this.provider = str2;
        this.receipt = str4;
        this.countryCode = str3;
        this.orderId = str5;
    }

    public MfpPaymentResult(Parcel parcel) {
        readFromParcel(parcel);
    }

    public MfpProduct getProduct() {
        return this.product;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getReceipt() {
        return this.receipt;
    }

    public double getAmountPaid() {
        return this.amountPaid;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getPaymentSessionId() {
        return this.paymentSessionId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public boolean isTrialEligible() {
        MfpTrialDetails trialDetails = this.product.getSubscriptionDetails().getTrialDetails();
        boolean z = true;
        boolean z2 = trialDetails != null;
        if (trialDetails.getDurationInterval() <= 0) {
            z = false;
        }
        return z2 & z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.product, 0);
        parcel.writeString(this.paymentSessionId);
        parcel.writeDouble(this.amountPaid);
        parcel.writeString(this.provider);
        parcel.writeString(this.receipt);
        parcel.writeString(this.countryCode);
        parcel.writeString(this.orderId);
    }

    private void readFromParcel(Parcel parcel) {
        this.product = (MfpProduct) parcel.readParcelable(MfpProduct.class.getClassLoader());
        this.paymentSessionId = parcel.readString();
        this.amountPaid = parcel.readDouble();
        this.provider = parcel.readString();
        this.receipt = parcel.readString();
        this.countryCode = parcel.readString();
        this.orderId = parcel.readString();
    }
}
