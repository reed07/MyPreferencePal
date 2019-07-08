package com.myfitnesspal.feature.foodfeedback.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/UnitValue;", "", "unit", "", "value", "", "(Ljava/lang/String;F)V", "getUnit", "()Ljava/lang/String;", "getValue", "()F", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackItem.kt */
public final class UnitValue {
    @SerializedName("unit")
    @NotNull
    @Expose
    private final String unit;
    @SerializedName("value")
    @Expose
    private final float value;

    @NotNull
    public static /* synthetic */ UnitValue copy$default(UnitValue unitValue, String str, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            str = unitValue.unit;
        }
        if ((i & 2) != 0) {
            f = unitValue.value;
        }
        return unitValue.copy(str, f);
    }

    @NotNull
    public final String component1() {
        return this.unit;
    }

    public final float component2() {
        return this.value;
    }

    @NotNull
    public final UnitValue copy(@NotNull String str, float f) {
        Intrinsics.checkParameterIsNotNull(str, "unit");
        return new UnitValue(str, f);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (java.lang.Float.compare(r2.value, r3.value) == 0) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.myfitnesspal.feature.foodfeedback.model.UnitValue
            if (r0 == 0) goto L_0x001d
            com.myfitnesspal.feature.foodfeedback.model.UnitValue r3 = (com.myfitnesspal.feature.foodfeedback.model.UnitValue) r3
            java.lang.String r0 = r2.unit
            java.lang.String r1 = r3.unit
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            float r0 = r2.value
            float r3 = r3.value
            int r3 = java.lang.Float.compare(r0, r3)
            if (r3 != 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodfeedback.model.UnitValue.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.unit;
        return ((str != null ? str.hashCode() : 0) * 31) + Float.hashCode(this.value);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UnitValue(unit=");
        sb.append(this.unit);
        sb.append(", value=");
        sb.append(this.value);
        sb.append(")");
        return sb.toString();
    }

    public UnitValue(@NotNull String str, float f) {
        Intrinsics.checkParameterIsNotNull(str, "unit");
        this.unit = str;
        this.value = f;
    }

    @NotNull
    public final String getUnit() {
        return this.unit;
    }

    public final float getValue() {
        return this.value;
    }
}
