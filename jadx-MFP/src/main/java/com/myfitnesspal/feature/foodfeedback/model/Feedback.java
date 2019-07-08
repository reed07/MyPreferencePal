package com.myfitnesspal.feature.foodfeedback.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003JK\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006\""}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/Feedback;", "", "duplicateEntry", "", "inAppropriateListing", "servingNotAvailable", "brandName", "Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterString;", "description", "nutritionalContents", "Lcom/myfitnesspal/feature/foodfeedback/model/NutritionalContentsFeedback;", "(ZZZLcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterString;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterString;Lcom/myfitnesspal/feature/foodfeedback/model/NutritionalContentsFeedback;)V", "getBrandName", "()Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterString;", "getDescription", "getDuplicateEntry", "()Z", "getInAppropriateListing", "getNutritionalContents", "()Lcom/myfitnesspal/feature/foodfeedback/model/NutritionalContentsFeedback;", "getServingNotAvailable", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackItem.kt */
public final class Feedback {
    @SerializedName("brand_name")
    @Nullable
    @Expose
    private final BeforeAfterString brandName;
    @SerializedName("description")
    @Nullable
    @Expose
    private final BeforeAfterString description;
    @SerializedName("duplicate_entry")
    @Expose
    private final boolean duplicateEntry;
    @SerializedName("inappropriate_listing")
    @Expose
    private final boolean inAppropriateListing;
    @SerializedName("nutritional_contents")
    @Nullable
    @Expose
    private final NutritionalContentsFeedback nutritionalContents;
    @SerializedName("serving_not_available")
    @Expose
    private final boolean servingNotAvailable;

    @NotNull
    public static /* synthetic */ Feedback copy$default(Feedback feedback, boolean z, boolean z2, boolean z3, BeforeAfterString beforeAfterString, BeforeAfterString beforeAfterString2, NutritionalContentsFeedback nutritionalContentsFeedback, int i, Object obj) {
        if ((i & 1) != 0) {
            z = feedback.duplicateEntry;
        }
        if ((i & 2) != 0) {
            z2 = feedback.inAppropriateListing;
        }
        boolean z4 = z2;
        if ((i & 4) != 0) {
            z3 = feedback.servingNotAvailable;
        }
        boolean z5 = z3;
        if ((i & 8) != 0) {
            beforeAfterString = feedback.brandName;
        }
        BeforeAfterString beforeAfterString3 = beforeAfterString;
        if ((i & 16) != 0) {
            beforeAfterString2 = feedback.description;
        }
        BeforeAfterString beforeAfterString4 = beforeAfterString2;
        if ((i & 32) != 0) {
            nutritionalContentsFeedback = feedback.nutritionalContents;
        }
        return feedback.copy(z, z4, z5, beforeAfterString3, beforeAfterString4, nutritionalContentsFeedback);
    }

    public final boolean component1() {
        return this.duplicateEntry;
    }

    public final boolean component2() {
        return this.inAppropriateListing;
    }

    public final boolean component3() {
        return this.servingNotAvailable;
    }

    @Nullable
    public final BeforeAfterString component4() {
        return this.brandName;
    }

    @Nullable
    public final BeforeAfterString component5() {
        return this.description;
    }

    @Nullable
    public final NutritionalContentsFeedback component6() {
        return this.nutritionalContents;
    }

    @NotNull
    public final Feedback copy(boolean z, boolean z2, boolean z3, @Nullable BeforeAfterString beforeAfterString, @Nullable BeforeAfterString beforeAfterString2, @Nullable NutritionalContentsFeedback nutritionalContentsFeedback) {
        Feedback feedback = new Feedback(z, z2, z3, beforeAfterString, beforeAfterString2, nutritionalContentsFeedback);
        return feedback;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Feedback) {
                Feedback feedback = (Feedback) obj;
                if (this.duplicateEntry == feedback.duplicateEntry) {
                    if (this.inAppropriateListing == feedback.inAppropriateListing) {
                        if (!(this.servingNotAvailable == feedback.servingNotAvailable) || !Intrinsics.areEqual((Object) this.brandName, (Object) feedback.brandName) || !Intrinsics.areEqual((Object) this.description, (Object) feedback.description) || !Intrinsics.areEqual((Object) this.nutritionalContents, (Object) feedback.nutritionalContents)) {
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.duplicateEntry;
        int i = 1;
        if (z) {
            z = true;
        }
        int i2 = (z ? 1 : 0) * true;
        boolean z2 = this.inAppropriateListing;
        if (z2) {
            z2 = true;
        }
        int i3 = (i2 + (z2 ? 1 : 0)) * 31;
        boolean z3 = this.servingNotAvailable;
        if (!z3) {
            i = z3;
        }
        int i4 = (i3 + i) * 31;
        BeforeAfterString beforeAfterString = this.brandName;
        int i5 = 0;
        int hashCode = (i4 + (beforeAfterString != null ? beforeAfterString.hashCode() : 0)) * 31;
        BeforeAfterString beforeAfterString2 = this.description;
        int hashCode2 = (hashCode + (beforeAfterString2 != null ? beforeAfterString2.hashCode() : 0)) * 31;
        NutritionalContentsFeedback nutritionalContentsFeedback = this.nutritionalContents;
        if (nutritionalContentsFeedback != null) {
            i5 = nutritionalContentsFeedback.hashCode();
        }
        return hashCode2 + i5;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Feedback(duplicateEntry=");
        sb.append(this.duplicateEntry);
        sb.append(", inAppropriateListing=");
        sb.append(this.inAppropriateListing);
        sb.append(", servingNotAvailable=");
        sb.append(this.servingNotAvailable);
        sb.append(", brandName=");
        sb.append(this.brandName);
        sb.append(", description=");
        sb.append(this.description);
        sb.append(", nutritionalContents=");
        sb.append(this.nutritionalContents);
        sb.append(")");
        return sb.toString();
    }

    public Feedback(boolean z, boolean z2, boolean z3, @Nullable BeforeAfterString beforeAfterString, @Nullable BeforeAfterString beforeAfterString2, @Nullable NutritionalContentsFeedback nutritionalContentsFeedback) {
        this.duplicateEntry = z;
        this.inAppropriateListing = z2;
        this.servingNotAvailable = z3;
        this.brandName = beforeAfterString;
        this.description = beforeAfterString2;
        this.nutritionalContents = nutritionalContentsFeedback;
    }

    public final boolean getDuplicateEntry() {
        return this.duplicateEntry;
    }

    public final boolean getInAppropriateListing() {
        return this.inAppropriateListing;
    }

    public final boolean getServingNotAvailable() {
        return this.servingNotAvailable;
    }

    @Nullable
    public final BeforeAfterString getBrandName() {
        return this.brandName;
    }

    @Nullable
    public final BeforeAfterString getDescription() {
        return this.description;
    }

    public /* synthetic */ Feedback(boolean z, boolean z2, boolean z3, BeforeAfterString beforeAfterString, BeforeAfterString beforeAfterString2, NutritionalContentsFeedback nutritionalContentsFeedback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, z3, beforeAfterString, beforeAfterString2, (i & 32) != 0 ? null : nutritionalContentsFeedback);
    }

    @Nullable
    public final NutritionalContentsFeedback getNutritionalContents() {
        return this.nutritionalContents;
    }
}
