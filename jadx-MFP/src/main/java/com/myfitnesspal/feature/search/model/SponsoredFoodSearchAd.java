package com.myfitnesspal.feature.search.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.myfitnesspal.shared.model.v2.SearchResultItem;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 ,2\u00020\u00012\u00020\u0002:\u0001,BS\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003Jg\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020 HÖ\u0001J\t\u0010&\u001a\u00020\u0004HÖ\u0001J\u0019\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020 HÖ\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006-"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;", "Lcom/myfitnesspal/shared/model/v2/SearchResultItem;", "Landroid/os/Parcelable;", "productName", "", "foodDescription", "productTagline", "productThumbnailImagePath", "sponsoredCallout", "foodId", "measurement", "calories", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCalories", "()Ljava/lang/String;", "getFoodDescription", "getFoodId", "getMeasurement", "getProductName", "getProductTagline", "getProductThumbnailImagePath", "getSponsoredCallout", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
@Parcelize
/* compiled from: SponsoredFoodSearchAd.kt */
public final class SponsoredFoodSearchAd implements Parcelable, SearchResultItem {
    private static final String ATTR_CALORIES = "Calories";
    private static final String ATTR_FOOD_DESCRIPTION = "FoodDescription";
    private static final String ATTR_FOOD_ID = "FoodID";
    private static final String ATTR_MEASUREMENT = "Measurement";
    private static final String ATTR_PRODUCT_NAME = "ProductName";
    private static final String ATTR_PRODUCT_TAGLINE = "ProductTagline";
    private static final String ATTR_PRODUCT_THUMBNAIL_IMAGE_PATH = "ProductThumbnailImagePath";
    private static final String ATTR_SPONSORED_CALLOUT = "SponsoredCallout";
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final String calories;
    @Nullable
    private final String foodDescription;
    @Nullable
    private final String foodId;
    @Nullable
    private final String measurement;
    @NotNull
    private final String productName;
    @Nullable
    private final String productTagline;
    @Nullable
    private final String productThumbnailImagePath;
    @Nullable
    private final String sponsoredCallout;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd$Companion;", "", "()V", "ATTR_CALORIES", "", "ATTR_FOOD_DESCRIPTION", "ATTR_FOOD_ID", "ATTR_MEASUREMENT", "ATTR_PRODUCT_NAME", "ATTR_PRODUCT_TAGLINE", "ATTR_PRODUCT_THUMBNAIL_IMAGE_PATH", "ATTR_SPONSORED_CALLOUT", "fromCustomDfpAd", "Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;", "customAd", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: SponsoredFoodSearchAd.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SponsoredFoodSearchAd fromCustomDfpAd(@NotNull NativeCustomTemplateAd nativeCustomTemplateAd) {
            Intrinsics.checkParameterIsNotNull(nativeCustomTemplateAd, "customAd");
            CharSequence text = nativeCustomTemplateAd.getText(SponsoredFoodSearchAd.ATTR_PRODUCT_NAME);
            if (text != null) {
                SponsoredFoodSearchAd sponsoredFoodSearchAd = new SponsoredFoodSearchAd((String) text, (String) nativeCustomTemplateAd.getText(SponsoredFoodSearchAd.ATTR_FOOD_DESCRIPTION), (String) nativeCustomTemplateAd.getText(SponsoredFoodSearchAd.ATTR_PRODUCT_TAGLINE), (String) nativeCustomTemplateAd.getText(SponsoredFoodSearchAd.ATTR_PRODUCT_THUMBNAIL_IMAGE_PATH), (String) nativeCustomTemplateAd.getText(SponsoredFoodSearchAd.ATTR_SPONSORED_CALLOUT), (String) nativeCustomTemplateAd.getText(SponsoredFoodSearchAd.ATTR_FOOD_ID), (String) nativeCustomTemplateAd.getText("Measurement"), (String) nativeCustomTemplateAd.getText("Calories"));
                return sponsoredFoodSearchAd;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static class Creator implements android.os.Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            SponsoredFoodSearchAd sponsoredFoodSearchAd = new SponsoredFoodSearchAd(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
            return sponsoredFoodSearchAd;
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new SponsoredFoodSearchAd[i];
        }
    }

    @NotNull
    public static /* synthetic */ SponsoredFoodSearchAd copy$default(SponsoredFoodSearchAd sponsoredFoodSearchAd, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, Object obj) {
        SponsoredFoodSearchAd sponsoredFoodSearchAd2 = sponsoredFoodSearchAd;
        int i2 = i;
        return sponsoredFoodSearchAd.copy((i2 & 1) != 0 ? sponsoredFoodSearchAd2.productName : str, (i2 & 2) != 0 ? sponsoredFoodSearchAd2.foodDescription : str2, (i2 & 4) != 0 ? sponsoredFoodSearchAd2.productTagline : str3, (i2 & 8) != 0 ? sponsoredFoodSearchAd2.productThumbnailImagePath : str4, (i2 & 16) != 0 ? sponsoredFoodSearchAd2.sponsoredCallout : str5, (i2 & 32) != 0 ? sponsoredFoodSearchAd2.foodId : str6, (i2 & 64) != 0 ? sponsoredFoodSearchAd2.measurement : str7, (i2 & 128) != 0 ? sponsoredFoodSearchAd2.calories : str8);
    }

    @NotNull
    public final String component1() {
        return this.productName;
    }

    @Nullable
    public final String component2() {
        return this.foodDescription;
    }

    @Nullable
    public final String component3() {
        return this.productTagline;
    }

    @Nullable
    public final String component4() {
        return this.productThumbnailImagePath;
    }

    @Nullable
    public final String component5() {
        return this.sponsoredCallout;
    }

    @Nullable
    public final String component6() {
        return this.foodId;
    }

    @Nullable
    public final String component7() {
        return this.measurement;
    }

    @Nullable
    public final String component8() {
        return this.calories;
    }

    @NotNull
    public final SponsoredFoodSearchAd copy(@NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
        String str9 = str;
        Intrinsics.checkParameterIsNotNull(str, "productName");
        SponsoredFoodSearchAd sponsoredFoodSearchAd = new SponsoredFoodSearchAd(str9, str2, str3, str4, str5, str6, str7, str8);
        return sponsoredFoodSearchAd;
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.calories, (java.lang.Object) r3.calories) != false) goto L_0x005b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x005b
            boolean r0 = r3 instanceof com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd
            if (r0 == 0) goto L_0x0059
            com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd r3 = (com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd) r3
            java.lang.String r0 = r2.productName
            java.lang.String r1 = r3.productName
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.foodDescription
            java.lang.String r1 = r3.foodDescription
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.productTagline
            java.lang.String r1 = r3.productTagline
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.productThumbnailImagePath
            java.lang.String r1 = r3.productThumbnailImagePath
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.sponsoredCallout
            java.lang.String r1 = r3.sponsoredCallout
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.foodId
            java.lang.String r1 = r3.foodId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.measurement
            java.lang.String r1 = r3.measurement
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.calories
            java.lang.String r3 = r3.calories
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r3 = 0
            return r3
        L_0x005b:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.productName;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.foodDescription;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.productTagline;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.productThumbnailImagePath;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.sponsoredCallout;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.foodId;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.measurement;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.calories;
        if (str8 != null) {
            i = str8.hashCode();
        }
        return hashCode7 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SponsoredFoodSearchAd(productName=");
        sb.append(this.productName);
        sb.append(", foodDescription=");
        sb.append(this.foodDescription);
        sb.append(", productTagline=");
        sb.append(this.productTagline);
        sb.append(", productThumbnailImagePath=");
        sb.append(this.productThumbnailImagePath);
        sb.append(", sponsoredCallout=");
        sb.append(this.sponsoredCallout);
        sb.append(", foodId=");
        sb.append(this.foodId);
        sb.append(", measurement=");
        sb.append(this.measurement);
        sb.append(", calories=");
        sb.append(this.calories);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
        parcel.writeString(this.productName);
        parcel.writeString(this.foodDescription);
        parcel.writeString(this.productTagline);
        parcel.writeString(this.productThumbnailImagePath);
        parcel.writeString(this.sponsoredCallout);
        parcel.writeString(this.foodId);
        parcel.writeString(this.measurement);
        parcel.writeString(this.calories);
    }

    public SponsoredFoodSearchAd(@NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
        Intrinsics.checkParameterIsNotNull(str, "productName");
        this.productName = str;
        this.foodDescription = str2;
        this.productTagline = str3;
        this.productThumbnailImagePath = str4;
        this.sponsoredCallout = str5;
        this.foodId = str6;
        this.measurement = str7;
        this.calories = str8;
    }

    @NotNull
    public final String getProductName() {
        return this.productName;
    }

    @Nullable
    public final String getFoodDescription() {
        return this.foodDescription;
    }

    @Nullable
    public final String getProductTagline() {
        return this.productTagline;
    }

    @Nullable
    public final String getProductThumbnailImagePath() {
        return this.productThumbnailImagePath;
    }

    @Nullable
    public final String getSponsoredCallout() {
        return this.sponsoredCallout;
    }

    @Nullable
    public final String getFoodId() {
        return this.foodId;
    }

    @Nullable
    public final String getMeasurement() {
        return this.measurement;
    }

    @Nullable
    public final String getCalories() {
        return this.calories;
    }
}
