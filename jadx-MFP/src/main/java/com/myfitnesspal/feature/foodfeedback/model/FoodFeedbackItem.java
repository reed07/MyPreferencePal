package com.myfitnesspal.feature.foodfeedback.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackItem;", "", "foodId", "", "foodVersion", "feedback", "Lcom/myfitnesspal/feature/foodfeedback/model/Feedback;", "(Ljava/lang/String;Ljava/lang/String;Lcom/myfitnesspal/feature/foodfeedback/model/Feedback;)V", "getFeedback", "()Lcom/myfitnesspal/feature/foodfeedback/model/Feedback;", "getFoodId", "()Ljava/lang/String;", "getFoodVersion", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackItem.kt */
public final class FoodFeedbackItem {
    @SerializedName("feedback")
    @NotNull
    @Expose
    private final Feedback feedback;
    @SerializedName("food_id")
    @NotNull
    @Expose
    private final String foodId;
    @SerializedName("food_version")
    @NotNull
    @Expose
    private final String foodVersion;

    @NotNull
    public static /* synthetic */ FoodFeedbackItem copy$default(FoodFeedbackItem foodFeedbackItem, String str, String str2, Feedback feedback2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = foodFeedbackItem.foodId;
        }
        if ((i & 2) != 0) {
            str2 = foodFeedbackItem.foodVersion;
        }
        if ((i & 4) != 0) {
            feedback2 = foodFeedbackItem.feedback;
        }
        return foodFeedbackItem.copy(str, str2, feedback2);
    }

    @NotNull
    public final String component1() {
        return this.foodId;
    }

    @NotNull
    public final String component2() {
        return this.foodVersion;
    }

    @NotNull
    public final Feedback component3() {
        return this.feedback;
    }

    @NotNull
    public final FoodFeedbackItem copy(@NotNull String str, @NotNull String str2, @NotNull Feedback feedback2) {
        Intrinsics.checkParameterIsNotNull(str, "foodId");
        Intrinsics.checkParameterIsNotNull(str2, "foodVersion");
        Intrinsics.checkParameterIsNotNull(feedback2, "feedback");
        return new FoodFeedbackItem(str, str2, feedback2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.feedback, (java.lang.Object) r3.feedback) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0029
            boolean r0 = r3 instanceof com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackItem
            if (r0 == 0) goto L_0x0027
            com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackItem r3 = (com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackItem) r3
            java.lang.String r0 = r2.foodId
            java.lang.String r1 = r3.foodId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r2.foodVersion
            java.lang.String r1 = r3.foodVersion
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            com.myfitnesspal.feature.foodfeedback.model.Feedback r0 = r2.feedback
            com.myfitnesspal.feature.foodfeedback.model.Feedback r3 = r3.feedback
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r3 = 0
            return r3
        L_0x0029:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackItem.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.foodId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.foodVersion;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Feedback feedback2 = this.feedback;
        if (feedback2 != null) {
            i = feedback2.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FoodFeedbackItem(foodId=");
        sb.append(this.foodId);
        sb.append(", foodVersion=");
        sb.append(this.foodVersion);
        sb.append(", feedback=");
        sb.append(this.feedback);
        sb.append(")");
        return sb.toString();
    }

    public FoodFeedbackItem(@NotNull String str, @NotNull String str2, @NotNull Feedback feedback2) {
        Intrinsics.checkParameterIsNotNull(str, "foodId");
        Intrinsics.checkParameterIsNotNull(str2, "foodVersion");
        Intrinsics.checkParameterIsNotNull(feedback2, "feedback");
        this.foodId = str;
        this.foodVersion = str2;
        this.feedback = feedback2;
    }

    @NotNull
    public final String getFoodId() {
        return this.foodId;
    }

    @NotNull
    public final String getFoodVersion() {
        return this.foodVersion;
    }

    @NotNull
    public final Feedback getFeedback() {
        return this.feedback;
    }
}
