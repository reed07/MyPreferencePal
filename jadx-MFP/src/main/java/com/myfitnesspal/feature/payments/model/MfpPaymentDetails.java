package com.myfitnesspal.feature.payments.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020\"HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\u0019\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\"HÖ\u0001R \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006."}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpPaymentDetails;", "Landroid/os/Parcelable;", "productId", "", "paymentDate", "platformDetails", "Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails;", "pricePoint", "Lcom/myfitnesspal/feature/payments/model/MfpProductPrice;", "countryCode", "(Ljava/lang/String;Ljava/lang/String;Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails;Lcom/myfitnesspal/feature/payments/model/MfpProductPrice;Ljava/lang/String;)V", "getCountryCode", "()Ljava/lang/String;", "setCountryCode", "(Ljava/lang/String;)V", "getPaymentDate", "setPaymentDate", "getPlatformDetails", "()Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails;", "setPlatformDetails", "(Lcom/myfitnesspal/feature/payments/model/MfpPlatformDetails;)V", "getPricePoint", "()Lcom/myfitnesspal/feature/payments/model/MfpProductPrice;", "setPricePoint", "(Lcom/myfitnesspal/feature/payments/model/MfpProductPrice;)V", "getProductId", "setProductId", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: MfpPaymentDetails.kt */
public final class MfpPaymentDetails implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    @Nullable
    @Expose
    private String countryCode;
    @Nullable
    @Expose
    private String paymentDate;
    @Nullable
    @Expose
    private MfpPlatformDetails platformDetails;
    @Nullable
    @Expose
    private MfpProductPrice pricePoint;
    @Nullable
    @Expose
    private String productId;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            MfpPaymentDetails mfpPaymentDetails = new MfpPaymentDetails(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (MfpPlatformDetails) MfpPlatformDetails.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (MfpProductPrice) MfpProductPrice.CREATOR.createFromParcel(parcel) : null, parcel.readString());
            return mfpPaymentDetails;
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new MfpPaymentDetails[i];
        }
    }

    public MfpPaymentDetails() {
        this(null, null, null, null, null, 31, null);
    }

    @NotNull
    public static /* synthetic */ MfpPaymentDetails copy$default(MfpPaymentDetails mfpPaymentDetails, String str, String str2, MfpPlatformDetails mfpPlatformDetails, MfpProductPrice mfpProductPrice, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mfpPaymentDetails.productId;
        }
        if ((i & 2) != 0) {
            str2 = mfpPaymentDetails.paymentDate;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            mfpPlatformDetails = mfpPaymentDetails.platformDetails;
        }
        MfpPlatformDetails mfpPlatformDetails2 = mfpPlatformDetails;
        if ((i & 8) != 0) {
            mfpProductPrice = mfpPaymentDetails.pricePoint;
        }
        MfpProductPrice mfpProductPrice2 = mfpProductPrice;
        if ((i & 16) != 0) {
            str3 = mfpPaymentDetails.countryCode;
        }
        return mfpPaymentDetails.copy(str, str4, mfpPlatformDetails2, mfpProductPrice2, str3);
    }

    @Nullable
    public final String component1() {
        return this.productId;
    }

    @Nullable
    public final String component2() {
        return this.paymentDate;
    }

    @Nullable
    public final MfpPlatformDetails component3() {
        return this.platformDetails;
    }

    @Nullable
    public final MfpProductPrice component4() {
        return this.pricePoint;
    }

    @Nullable
    public final String component5() {
        return this.countryCode;
    }

    @NotNull
    public final MfpPaymentDetails copy(@Nullable String str, @Nullable String str2, @Nullable MfpPlatformDetails mfpPlatformDetails, @Nullable MfpProductPrice mfpProductPrice, @Nullable String str3) {
        MfpPaymentDetails mfpPaymentDetails = new MfpPaymentDetails(str, str2, mfpPlatformDetails, mfpProductPrice, str3);
        return mfpPaymentDetails;
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.countryCode, (java.lang.Object) r3.countryCode) != false) goto L_0x003d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x003d
            boolean r0 = r3 instanceof com.myfitnesspal.feature.payments.model.MfpPaymentDetails
            if (r0 == 0) goto L_0x003b
            com.myfitnesspal.feature.payments.model.MfpPaymentDetails r3 = (com.myfitnesspal.feature.payments.model.MfpPaymentDetails) r3
            java.lang.String r0 = r2.productId
            java.lang.String r1 = r3.productId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x003b
            java.lang.String r0 = r2.paymentDate
            java.lang.String r1 = r3.paymentDate
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x003b
            com.myfitnesspal.feature.payments.model.MfpPlatformDetails r0 = r2.platformDetails
            com.myfitnesspal.feature.payments.model.MfpPlatformDetails r1 = r3.platformDetails
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x003b
            com.myfitnesspal.feature.payments.model.MfpProductPrice r0 = r2.pricePoint
            com.myfitnesspal.feature.payments.model.MfpProductPrice r1 = r3.pricePoint
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x003b
            java.lang.String r0 = r2.countryCode
            java.lang.String r3 = r3.countryCode
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x003b
            goto L_0x003d
        L_0x003b:
            r3 = 0
            return r3
        L_0x003d:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.model.MfpPaymentDetails.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.productId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.paymentDate;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        MfpPlatformDetails mfpPlatformDetails = this.platformDetails;
        int hashCode3 = (hashCode2 + (mfpPlatformDetails != null ? mfpPlatformDetails.hashCode() : 0)) * 31;
        MfpProductPrice mfpProductPrice = this.pricePoint;
        int hashCode4 = (hashCode3 + (mfpProductPrice != null ? mfpProductPrice.hashCode() : 0)) * 31;
        String str3 = this.countryCode;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MfpPaymentDetails(productId=");
        sb.append(this.productId);
        sb.append(", paymentDate=");
        sb.append(this.paymentDate);
        sb.append(", platformDetails=");
        sb.append(this.platformDetails);
        sb.append(", pricePoint=");
        sb.append(this.pricePoint);
        sb.append(", countryCode=");
        sb.append(this.countryCode);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        parcel.writeString(this.productId);
        parcel.writeString(this.paymentDate);
        MfpPlatformDetails mfpPlatformDetails = this.platformDetails;
        if (mfpPlatformDetails != null) {
            parcel.writeInt(1);
            mfpPlatformDetails.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        MfpProductPrice mfpProductPrice = this.pricePoint;
        if (mfpProductPrice != null) {
            parcel.writeInt(1);
            mfpProductPrice.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.countryCode);
    }

    public MfpPaymentDetails(@Nullable String str, @Nullable String str2, @Nullable MfpPlatformDetails mfpPlatformDetails, @Nullable MfpProductPrice mfpProductPrice, @Nullable String str3) {
        this.productId = str;
        this.paymentDate = str2;
        this.platformDetails = mfpPlatformDetails;
        this.pricePoint = mfpProductPrice;
        this.countryCode = str3;
    }

    @Nullable
    public final String getProductId() {
        return this.productId;
    }

    public final void setProductId(@Nullable String str) {
        this.productId = str;
    }

    @Nullable
    public final String getPaymentDate() {
        return this.paymentDate;
    }

    public final void setPaymentDate(@Nullable String str) {
        this.paymentDate = str;
    }

    public /* synthetic */ MfpPaymentDetails(String str, String str2, MfpPlatformDetails mfpPlatformDetails, MfpProductPrice mfpProductPrice, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            str = "";
        }
        String str4 = (i & 2) != 0 ? "" : str2;
        this(str, str4, (i & 4) != 0 ? null : mfpPlatformDetails, (i & 8) != 0 ? null : mfpProductPrice, (i & 16) != 0 ? "" : str3);
    }

    @Nullable
    public final MfpPlatformDetails getPlatformDetails() {
        return this.platformDetails;
    }

    public final void setPlatformDetails(@Nullable MfpPlatformDetails mfpPlatformDetails) {
        this.platformDetails = mfpPlatformDetails;
    }

    @Nullable
    public final MfpProductPrice getPricePoint() {
        return this.pricePoint;
    }

    public final void setPricePoint(@Nullable MfpProductPrice mfpProductPrice) {
        this.pricePoint = mfpProductPrice;
    }

    @Nullable
    public final String getCountryCode() {
        return this.countryCode;
    }

    public final void setCountryCode(@Nullable String str) {
        this.countryCode = str;
    }
}
