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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J+\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0017HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\u0019\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0017HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006#"}, d2 = {"Lcom/myfitnesspal/feature/payments/model/MfpProductPrice;", "Landroid/os/Parcelable;", "amount", "", "currency", "", "displayPrice", "(DLjava/lang/String;Ljava/lang/String;)V", "getAmount", "()D", "setAmount", "(D)V", "getCurrency", "()Ljava/lang/String;", "setCurrency", "(Ljava/lang/String;)V", "getDisplayPrice", "setDisplayPrice", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: MfpProductPrice.kt */
public final class MfpProductPrice implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    @Expose
    private double amount;
    @Nullable
    @Expose
    private String currency;
    @Nullable
    @Expose
    private String displayPrice;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new MfpProductPrice(parcel.readDouble(), parcel.readString(), parcel.readString());
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new MfpProductPrice[i];
        }
    }

    public MfpProductPrice() {
        this(0.0d, null, null, 7, null);
    }

    @NotNull
    public static /* synthetic */ MfpProductPrice copy$default(MfpProductPrice mfpProductPrice, double d, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = mfpProductPrice.amount;
        }
        if ((i & 2) != 0) {
            str = mfpProductPrice.currency;
        }
        if ((i & 4) != 0) {
            str2 = mfpProductPrice.displayPrice;
        }
        return mfpProductPrice.copy(d, str, str2);
    }

    public final double component1() {
        return this.amount;
    }

    @Nullable
    public final String component2() {
        return this.currency;
    }

    @Nullable
    public final String component3() {
        return this.displayPrice;
    }

    @NotNull
    public final MfpProductPrice copy(double d, @Nullable String str, @Nullable String str2) {
        return new MfpProductPrice(d, str, str2);
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4.displayPrice, (java.lang.Object) r5.displayPrice) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r5) {
        /*
            r4 = this;
            if (r4 == r5) goto L_0x0029
            boolean r0 = r5 instanceof com.myfitnesspal.feature.payments.model.MfpProductPrice
            if (r0 == 0) goto L_0x0027
            com.myfitnesspal.feature.payments.model.MfpProductPrice r5 = (com.myfitnesspal.feature.payments.model.MfpProductPrice) r5
            double r0 = r4.amount
            double r2 = r5.amount
            int r0 = java.lang.Double.compare(r0, r2)
            if (r0 != 0) goto L_0x0027
            java.lang.String r0 = r4.currency
            java.lang.String r1 = r5.currency
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r4.displayPrice
            java.lang.String r5 = r5.displayPrice
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r5)
            if (r5 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r5 = 0
            return r5
        L_0x0029:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.payments.model.MfpProductPrice.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode = Double.hashCode(this.amount) * 31;
        String str = this.currency;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.displayPrice;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MfpProductPrice(amount=");
        sb.append(this.amount);
        sb.append(", currency=");
        sb.append(this.currency);
        sb.append(", displayPrice=");
        sb.append(this.displayPrice);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        parcel.writeDouble(this.amount);
        parcel.writeString(this.currency);
        parcel.writeString(this.displayPrice);
    }

    public MfpProductPrice(double d, @Nullable String str, @Nullable String str2) {
        this.amount = d;
        this.currency = str;
        this.displayPrice = str2;
    }

    public final double getAmount() {
        return this.amount;
    }

    public final void setAmount(double d) {
        this.amount = d;
    }

    public /* synthetic */ MfpProductPrice(double d, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            d = 0.0d;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        this(d, str, str2);
    }

    @Nullable
    public final String getCurrency() {
        return this.currency;
    }

    public final void setCurrency(@Nullable String str) {
        this.currency = str;
    }

    @Nullable
    public final String getDisplayPrice() {
        return this.displayPrice;
    }

    public final void setDisplayPrice(@Nullable String str) {
        this.displayPrice = str;
    }
}
