package com.myfitnesspal.feature.foodfeedback.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;", "", "before", "", "after", "(FF)V", "getAfter", "()F", "getBefore", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackItem.kt */
public final class BeforeAfterFloat {
    @SerializedName("after")
    @Expose
    private final float after;
    @SerializedName("before")
    @Expose
    private final float before;

    @NotNull
    public static /* synthetic */ BeforeAfterFloat copy$default(BeforeAfterFloat beforeAfterFloat, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = beforeAfterFloat.before;
        }
        if ((i & 2) != 0) {
            f2 = beforeAfterFloat.after;
        }
        return beforeAfterFloat.copy(f, f2);
    }

    public final float component1() {
        return this.before;
    }

    public final float component2() {
        return this.after;
    }

    @NotNull
    public final BeforeAfterFloat copy(float f, float f2) {
        return new BeforeAfterFloat(f, f2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (java.lang.Float.compare(r2.after, r3.after) == 0) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat
            if (r0 == 0) goto L_0x001d
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r3 = (com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat) r3
            float r0 = r2.before
            float r1 = r3.before
            int r0 = java.lang.Float.compare(r0, r1)
            if (r0 != 0) goto L_0x001d
            float r0 = r2.after
            float r3 = r3.after
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
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (Float.hashCode(this.before) * 31) + Float.hashCode(this.after);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BeforeAfterFloat(before=");
        sb.append(this.before);
        sb.append(", after=");
        sb.append(this.after);
        sb.append(")");
        return sb.toString();
    }

    public BeforeAfterFloat(float f, float f2) {
        this.before = f;
        this.after = f2;
    }

    public final float getBefore() {
        return this.before;
    }

    public final float getAfter() {
        return this.after;
    }
}
