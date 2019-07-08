package com.myfitnesspal.feature.foodfeedback.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0017\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\u000bHÆ\u0003JE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\tHÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/FoodExistsCheckItem;", "", "foodName", "", "brand", "checkBrand", "", "checkPublicFood", "itemType", "", "userLocalId", "", "(Ljava/lang/String;Ljava/lang/String;ZZIJ)V", "getBrand", "()Ljava/lang/String;", "getCheckBrand", "()Z", "getCheckPublicFood", "getFoodName", "getItemType", "()I", "getUserLocalId", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodExistsCheckItem.kt */
public final class FoodExistsCheckItem {
    @NotNull
    private final String brand;
    private final boolean checkBrand;
    private final boolean checkPublicFood;
    @NotNull
    private final String foodName;
    private final int itemType;
    private final long userLocalId;

    @NotNull
    public static /* synthetic */ FoodExistsCheckItem copy$default(FoodExistsCheckItem foodExistsCheckItem, String str, String str2, boolean z, boolean z2, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = foodExistsCheckItem.foodName;
        }
        if ((i2 & 2) != 0) {
            str2 = foodExistsCheckItem.brand;
        }
        String str3 = str2;
        if ((i2 & 4) != 0) {
            z = foodExistsCheckItem.checkBrand;
        }
        boolean z3 = z;
        if ((i2 & 8) != 0) {
            z2 = foodExistsCheckItem.checkPublicFood;
        }
        boolean z4 = z2;
        if ((i2 & 16) != 0) {
            i = foodExistsCheckItem.itemType;
        }
        int i3 = i;
        if ((i2 & 32) != 0) {
            j = foodExistsCheckItem.userLocalId;
        }
        return foodExistsCheckItem.copy(str, str3, z3, z4, i3, j);
    }

    @NotNull
    public final String component1() {
        return this.foodName;
    }

    @NotNull
    public final String component2() {
        return this.brand;
    }

    public final boolean component3() {
        return this.checkBrand;
    }

    public final boolean component4() {
        return this.checkPublicFood;
    }

    public final int component5() {
        return this.itemType;
    }

    public final long component6() {
        return this.userLocalId;
    }

    @NotNull
    public final FoodExistsCheckItem copy(@NotNull String str, @NotNull String str2, boolean z, boolean z2, int i, long j) {
        String str3 = str;
        Intrinsics.checkParameterIsNotNull(str, "foodName");
        String str4 = str2;
        Intrinsics.checkParameterIsNotNull(str2, "brand");
        FoodExistsCheckItem foodExistsCheckItem = new FoodExistsCheckItem(str3, str4, z, z2, i, j);
        return foodExistsCheckItem;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof FoodExistsCheckItem) {
                FoodExistsCheckItem foodExistsCheckItem = (FoodExistsCheckItem) obj;
                if (Intrinsics.areEqual((Object) this.foodName, (Object) foodExistsCheckItem.foodName) && Intrinsics.areEqual((Object) this.brand, (Object) foodExistsCheckItem.brand)) {
                    if (this.checkBrand == foodExistsCheckItem.checkBrand) {
                        if (this.checkPublicFood == foodExistsCheckItem.checkPublicFood) {
                            if (this.itemType == foodExistsCheckItem.itemType) {
                                if (this.userLocalId == foodExistsCheckItem.userLocalId) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.foodName;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.brand;
        if (str2 != null) {
            i = str2.hashCode();
        }
        int i2 = (hashCode + i) * 31;
        boolean z = this.checkBrand;
        if (z) {
            z = true;
        }
        int i3 = (i2 + (z ? 1 : 0)) * 31;
        boolean z2 = this.checkPublicFood;
        if (z2) {
            z2 = true;
        }
        return ((((i3 + (z2 ? 1 : 0)) * 31) + Integer.hashCode(this.itemType)) * 31) + Long.hashCode(this.userLocalId);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FoodExistsCheckItem(foodName=");
        sb.append(this.foodName);
        sb.append(", brand=");
        sb.append(this.brand);
        sb.append(", checkBrand=");
        sb.append(this.checkBrand);
        sb.append(", checkPublicFood=");
        sb.append(this.checkPublicFood);
        sb.append(", itemType=");
        sb.append(this.itemType);
        sb.append(", userLocalId=");
        sb.append(this.userLocalId);
        sb.append(")");
        return sb.toString();
    }

    public FoodExistsCheckItem(@NotNull String str, @NotNull String str2, boolean z, boolean z2, int i, long j) {
        Intrinsics.checkParameterIsNotNull(str, "foodName");
        Intrinsics.checkParameterIsNotNull(str2, "brand");
        this.foodName = str;
        this.brand = str2;
        this.checkBrand = z;
        this.checkPublicFood = z2;
        this.itemType = i;
        this.userLocalId = j;
    }

    @NotNull
    public final String getFoodName() {
        return this.foodName;
    }

    @NotNull
    public final String getBrand() {
        return this.brand;
    }

    public final boolean getCheckBrand() {
        return this.checkBrand;
    }

    public final boolean getCheckPublicFood() {
        return this.checkPublicFood;
    }

    public final int getItemType() {
        return this.itemType;
    }

    public final long getUserLocalId() {
        return this.userLocalId;
    }
}
