package com.myfitnesspal.feature.foodfeedback.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterCalories;", "", "caloriesBeforeUnitValue", "Lcom/myfitnesspal/feature/foodfeedback/model/UnitValue;", "caloriesAfterUnitValue", "(Lcom/myfitnesspal/feature/foodfeedback/model/UnitValue;Lcom/myfitnesspal/feature/foodfeedback/model/UnitValue;)V", "getCaloriesAfterUnitValue", "()Lcom/myfitnesspal/feature/foodfeedback/model/UnitValue;", "getCaloriesBeforeUnitValue", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackItem.kt */
public final class BeforeAfterCalories {
    @SerializedName("after")
    @Nullable
    @Expose
    private final UnitValue caloriesAfterUnitValue;
    @SerializedName("before")
    @Nullable
    @Expose
    private final UnitValue caloriesBeforeUnitValue;

    @NotNull
    public static /* synthetic */ BeforeAfterCalories copy$default(BeforeAfterCalories beforeAfterCalories, UnitValue unitValue, UnitValue unitValue2, int i, Object obj) {
        if ((i & 1) != 0) {
            unitValue = beforeAfterCalories.caloriesBeforeUnitValue;
        }
        if ((i & 2) != 0) {
            unitValue2 = beforeAfterCalories.caloriesAfterUnitValue;
        }
        return beforeAfterCalories.copy(unitValue, unitValue2);
    }

    @Nullable
    public final UnitValue component1() {
        return this.caloriesBeforeUnitValue;
    }

    @Nullable
    public final UnitValue component2() {
        return this.caloriesAfterUnitValue;
    }

    @NotNull
    public final BeforeAfterCalories copy(@Nullable UnitValue unitValue, @Nullable UnitValue unitValue2) {
        return new BeforeAfterCalories(unitValue, unitValue2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.caloriesAfterUnitValue, (java.lang.Object) r3.caloriesAfterUnitValue) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.myfitnesspal.feature.foodfeedback.model.BeforeAfterCalories
            if (r0 == 0) goto L_0x001d
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterCalories r3 = (com.myfitnesspal.feature.foodfeedback.model.BeforeAfterCalories) r3
            com.myfitnesspal.feature.foodfeedback.model.UnitValue r0 = r2.caloriesBeforeUnitValue
            com.myfitnesspal.feature.foodfeedback.model.UnitValue r1 = r3.caloriesBeforeUnitValue
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            com.myfitnesspal.feature.foodfeedback.model.UnitValue r0 = r2.caloriesAfterUnitValue
            com.myfitnesspal.feature.foodfeedback.model.UnitValue r3 = r3.caloriesAfterUnitValue
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodfeedback.model.BeforeAfterCalories.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        UnitValue unitValue = this.caloriesBeforeUnitValue;
        int i = 0;
        int hashCode = (unitValue != null ? unitValue.hashCode() : 0) * 31;
        UnitValue unitValue2 = this.caloriesAfterUnitValue;
        if (unitValue2 != null) {
            i = unitValue2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BeforeAfterCalories(caloriesBeforeUnitValue=");
        sb.append(this.caloriesBeforeUnitValue);
        sb.append(", caloriesAfterUnitValue=");
        sb.append(this.caloriesAfterUnitValue);
        sb.append(")");
        return sb.toString();
    }

    public BeforeAfterCalories(@Nullable UnitValue unitValue, @Nullable UnitValue unitValue2) {
        this.caloriesBeforeUnitValue = unitValue;
        this.caloriesAfterUnitValue = unitValue2;
    }

    @Nullable
    public final UnitValue getCaloriesBeforeUnitValue() {
        return this.caloriesBeforeUnitValue;
    }

    @Nullable
    public final UnitValue getCaloriesAfterUnitValue() {
        return this.caloriesAfterUnitValue;
    }
}
