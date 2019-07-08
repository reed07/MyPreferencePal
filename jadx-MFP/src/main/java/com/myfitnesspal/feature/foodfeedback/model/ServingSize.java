package com.myfitnesspal.feature.foodfeedback.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/ServingSize;", "", "servingSizeIndex", "", "nutritionMultiplier", "", "unit", "", "value", "(IDLjava/lang/String;D)V", "getNutritionMultiplier", "()D", "getServingSizeIndex", "()I", "getUnit", "()Ljava/lang/String;", "getValue", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackItem.kt */
public final class ServingSize {
    @SerializedName("nutrition_multiplier")
    @Expose
    private final double nutritionMultiplier;
    @SerializedName("index")
    @Expose
    private final int servingSizeIndex;
    @SerializedName("unit")
    @NotNull
    @Expose
    private final String unit;
    @SerializedName("value")
    @Expose
    private final double value;

    @NotNull
    public static /* synthetic */ ServingSize copy$default(ServingSize servingSize, int i, double d, String str, double d2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = servingSize.servingSizeIndex;
        }
        if ((i2 & 2) != 0) {
            d = servingSize.nutritionMultiplier;
        }
        double d3 = d;
        if ((i2 & 4) != 0) {
            str = servingSize.unit;
        }
        String str2 = str;
        if ((i2 & 8) != 0) {
            d2 = servingSize.value;
        }
        return servingSize.copy(i, d3, str2, d2);
    }

    public final int component1() {
        return this.servingSizeIndex;
    }

    public final double component2() {
        return this.nutritionMultiplier;
    }

    @NotNull
    public final String component3() {
        return this.unit;
    }

    public final double component4() {
        return this.value;
    }

    @NotNull
    public final ServingSize copy(int i, double d, @NotNull String str, double d2) {
        Intrinsics.checkParameterIsNotNull(str, "unit");
        ServingSize servingSize = new ServingSize(i, d, str, d2);
        return servingSize;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ServingSize) {
                ServingSize servingSize = (ServingSize) obj;
                if (!(this.servingSizeIndex == servingSize.servingSizeIndex) || Double.compare(this.nutritionMultiplier, servingSize.nutritionMultiplier) != 0 || !Intrinsics.areEqual((Object) this.unit, (Object) servingSize.unit) || Double.compare(this.value, servingSize.value) != 0) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.servingSizeIndex) * 31) + Double.hashCode(this.nutritionMultiplier)) * 31;
        String str = this.unit;
        return ((hashCode + (str != null ? str.hashCode() : 0)) * 31) + Double.hashCode(this.value);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ServingSize(servingSizeIndex=");
        sb.append(this.servingSizeIndex);
        sb.append(", nutritionMultiplier=");
        sb.append(this.nutritionMultiplier);
        sb.append(", unit=");
        sb.append(this.unit);
        sb.append(", value=");
        sb.append(this.value);
        sb.append(")");
        return sb.toString();
    }

    public ServingSize(int i, double d, @NotNull String str, double d2) {
        Intrinsics.checkParameterIsNotNull(str, "unit");
        this.servingSizeIndex = i;
        this.nutritionMultiplier = d;
        this.unit = str;
        this.value = d2;
    }

    public final int getServingSizeIndex() {
        return this.servingSizeIndex;
    }

    public final double getNutritionMultiplier() {
        return this.nutritionMultiplier;
    }

    @NotNull
    public final String getUnit() {
        return this.unit;
    }

    public final double getValue() {
        return this.value;
    }
}
