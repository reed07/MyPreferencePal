package com.myfitnesspal.feature.foodfeedback.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterString;", "", "before", "", "after", "(Ljava/lang/String;Ljava/lang/String;)V", "getAfter", "()Ljava/lang/String;", "getBefore", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackItem.kt */
public final class BeforeAfterString {
    @SerializedName("after")
    @NotNull
    @Expose
    private final String after;
    @SerializedName("before")
    @NotNull
    @Expose
    private final String before;

    @NotNull
    public static /* synthetic */ BeforeAfterString copy$default(BeforeAfterString beforeAfterString, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = beforeAfterString.before;
        }
        if ((i & 2) != 0) {
            str2 = beforeAfterString.after;
        }
        return beforeAfterString.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.before;
    }

    @NotNull
    public final String component2() {
        return this.after;
    }

    @NotNull
    public final BeforeAfterString copy(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "before");
        Intrinsics.checkParameterIsNotNull(str2, "after");
        return new BeforeAfterString(str, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.after, (java.lang.Object) r3.after) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.myfitnesspal.feature.foodfeedback.model.BeforeAfterString
            if (r0 == 0) goto L_0x001d
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterString r3 = (com.myfitnesspal.feature.foodfeedback.model.BeforeAfterString) r3
            java.lang.String r0 = r2.before
            java.lang.String r1 = r3.before
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = r2.after
            java.lang.String r3 = r3.after
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
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodfeedback.model.BeforeAfterString.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.before;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.after;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BeforeAfterString(before=");
        sb.append(this.before);
        sb.append(", after=");
        sb.append(this.after);
        sb.append(")");
        return sb.toString();
    }

    public BeforeAfterString(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "before");
        Intrinsics.checkParameterIsNotNull(str2, "after");
        this.before = str;
        this.after = str2;
    }

    @NotNull
    public final String getBefore() {
        return this.before;
    }

    @NotNull
    public final String getAfter() {
        return this.after;
    }
}
