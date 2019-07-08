package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpLocalizedText;
import java.util.ArrayList;
import java.util.List;

public class MfpProduct implements Parcelable {
    public static final Creator<MfpProduct> CREATOR = new Creator<MfpProduct>() {
        public MfpProduct createFromParcel(Parcel parcel) {
            return new MfpProduct(parcel);
        }

        public MfpProduct[] newArray(int i) {
            return new MfpProduct[i];
        }
    };
    private static int VERSION = 1;
    @Expose
    private List<MfpAvailabilityDetails> availabilityDetails = new ArrayList();
    @Expose
    private String productCategory = "";
    @Expose
    private List<MfpLocalizedText> productDescriptions = new ArrayList();
    @Expose
    private List<MfpProductFeature> productFeatures = new ArrayList();
    @Expose
    private String productId = "";
    @Expose
    private String productStatus = "";
    @Expose
    private String productType = "";
    @Expose
    private MfpSubscriptionDetails subscriptionDetails = null;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpProduct> {
    }

    public static class LIST_MAPPER extends ArrayList<MfpProduct> {
    }

    public static final class ProductCategory {
        public static final String COACHING = "coaching";
        public static final String PREMIUM = "premium";
    }

    public static final class ProductType {
        public static final String PHYSICAL_GOOD = "physical_good";
        public static final String SUBSCRIPTION = "subscription";
    }

    public static final class Status {
        public static final String ACTIVE = "active";
        public static final String INACTIVE = "inactive";
    }

    public int describeContents() {
        return 0;
    }

    public static int getVersion() {
        return VERSION;
    }

    public MfpProduct() {
    }

    public MfpProduct(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String str) {
        this.productType = str;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public void setProductCategory(String str) {
        this.productCategory = str;
    }

    public List<MfpAvailabilityDetails> getAvailabilityDetails() {
        return this.availabilityDetails;
    }

    public void setAvailabilityDetails(List<MfpAvailabilityDetails> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.availabilityDetails = list;
    }

    public List<MfpLocalizedText> getProductDescriptions() {
        return this.productDescriptions;
    }

    public void setProductDescriptions(List<MfpLocalizedText> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.productDescriptions = list;
    }

    public String getProductStatus() {
        return this.productStatus;
    }

    public void setProductStatus(String str) {
        this.productStatus = str;
    }

    public List<MfpProductFeature> getProductFeatures() {
        return this.productFeatures;
    }

    public void setProductFeatures(List<MfpProductFeature> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.productFeatures = list;
    }

    public MfpSubscriptionDetails getSubscriptionDetails() {
        return this.subscriptionDetails;
    }

    public void setSubscriptionDetails(MfpSubscriptionDetails mfpSubscriptionDetails) {
        this.subscriptionDetails = mfpSubscriptionDetails;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.productId);
        parcel.writeString(this.productType);
        parcel.writeString(this.productCategory);
        parcel.writeString(this.productStatus);
        parcel.writeList(this.productDescriptions);
        parcel.writeList(this.productFeatures);
        parcel.writeList(this.availabilityDetails);
        if (this.subscriptionDetails != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.subscriptionDetails, 0);
            return;
        }
        parcel.writeByte(0);
    }

    private void readFromParcel(Parcel parcel) {
        this.availabilityDetails.clear();
        this.productId = parcel.readString();
        this.productType = parcel.readString();
        this.productCategory = parcel.readString();
        this.productStatus = parcel.readString();
        parcel.readList(this.productDescriptions, MfpLocalizedText.class.getClassLoader());
        parcel.readList(this.productFeatures, MfpProductFeature.class.getClassLoader());
        parcel.readList(this.availabilityDetails, MfpAvailabilityDetails.class.getClassLoader());
        this.subscriptionDetails = null;
        if (parcel.readByte() == 1) {
            this.subscriptionDetails = (MfpSubscriptionDetails) parcel.readParcelable(MfpSubscriptionDetails.class.getClassLoader());
        }
    }
}
