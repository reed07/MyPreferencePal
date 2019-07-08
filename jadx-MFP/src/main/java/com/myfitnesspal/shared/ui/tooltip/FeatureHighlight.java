package com.myfitnesspal.shared.ui.tooltip;

import android.support.annotation.ColorInt;
import android.view.View;
import com.brightcove.player.C;
import com.google.android.gms.analytics.ecommerce.Promotion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b0\b\b\u0018\u0000 D2\u00020\u0001:\u0003DEFB«\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0003\u0010\n\u001a\u00020\b\u0012\b\b\u0003\u0010\u000b\u001a\u00020\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0003\u0010\r\u001a\u00020\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0003\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u0018J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00101\u001a\u00020\bHÆ\u0003J\t\u00102\u001a\u00020\bHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\t\u00104\u001a\u00020\u0012HÆ\u0003J\t\u00105\u001a\u00020\u0015HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00109\u001a\u00020\bHÆ\u0003J\t\u0010:\u001a\u00020\bHÆ\u0003J\t\u0010;\u001a\u00020\bHÆ\u0003J\t\u0010<\u001a\u00020\bHÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010>\u001a\u00020\bHÆ\u0003J³\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0003\u0010\n\u001a\u00020\b2\b\b\u0003\u0010\u000b\u001a\u00020\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\r\u001a\u00020\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÆ\u0001J\u0013\u0010@\u001a\u00020\u00152\b\u0010A\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010B\u001a\u00020\bHÖ\u0001J\t\u0010C\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0010\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001aR\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001aR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u0006G"}, d2 = {"Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight;", "", "view", "Landroid/view/View;", "description", "", "title", "descriptionTextSize", "", "titleTextSize", "dimColor", "textColor", "positiveButtonText", "positiveButtonTextColor", "negativeButtonText", "negativeButtonTextColor", "buttonTextSize", "pointerGravity", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$Gravity;", "contentGravity", "shouldHighlightFeatureView", "", "highlightShape", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$HighlightShape;", "(Landroid/view/View;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;ILjava/lang/String;IILcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$Gravity;Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$Gravity;ZLcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$HighlightShape;)V", "getButtonTextSize", "()I", "getContentGravity", "()Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$Gravity;", "getDescription", "()Ljava/lang/String;", "getDescriptionTextSize", "getDimColor", "getHighlightShape", "()Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$HighlightShape;", "getNegativeButtonText", "getNegativeButtonTextColor", "getPointerGravity", "getPositiveButtonText", "getPositiveButtonTextColor", "getShouldHighlightFeatureView", "()Z", "getTextColor", "getTitle", "getTitleTextSize", "getView", "()Landroid/view/View;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "Companion", "Gravity", "HighlightShape", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FeatureHighlight.kt */
public final class FeatureHighlight {
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_BUTTON_COLOR = -16748353;
    public static final int DEFAULT_BUTTON_TEXT_SIZE_SP = 14;
    public static final int DEFAULT_DESC_TEXT_SIZE_SP = 14;
    public static final int DEFAULT_DIM_COLOR = -1996488704;
    public static final int DEFAULT_TEXT_COLOR = -1996488704;
    public static final int DEFAULT_TITLE_TEXT_SIZE_SP = 14;
    private final int buttonTextSize;
    @NotNull
    private final Gravity contentGravity;
    @NotNull
    private final String description;
    private final int descriptionTextSize;
    private final int dimColor;
    @Nullable
    private final HighlightShape highlightShape;
    @Nullable
    private final String negativeButtonText;
    private final int negativeButtonTextColor;
    @Nullable
    private final Gravity pointerGravity;
    @Nullable
    private final String positiveButtonText;
    private final int positiveButtonTextColor;
    private final boolean shouldHighlightFeatureView;
    private final int textColor;
    @Nullable
    private final String title;
    private final int titleTextSize;
    @NotNull
    private final View view;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$Companion;", "", "()V", "DEFAULT_BUTTON_COLOR", "", "DEFAULT_BUTTON_TEXT_SIZE_SP", "DEFAULT_DESC_TEXT_SIZE_SP", "DEFAULT_DIM_COLOR", "DEFAULT_TEXT_COLOR", "DEFAULT_TITLE_TEXT_SIZE_SP", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FeatureHighlight.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$Gravity;", "", "(Ljava/lang/String;I)V", "LEFT", "RIGHT", "CENTER", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FeatureHighlight.kt */
    public enum Gravity {
        LEFT,
        RIGHT,
        CENTER
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight$HighlightShape;", "", "(Ljava/lang/String;I)V", "RECT", "CIRCLE", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FeatureHighlight.kt */
    public enum HighlightShape {
        RECT,
        CIRCLE
    }

    @NotNull
    public static /* synthetic */ FeatureHighlight copy$default(FeatureHighlight featureHighlight, View view2, String str, String str2, int i, int i2, int i3, int i4, String str3, int i5, String str4, int i6, int i7, Gravity gravity, Gravity gravity2, boolean z, HighlightShape highlightShape2, int i8, Object obj) {
        FeatureHighlight featureHighlight2 = featureHighlight;
        int i9 = i8;
        return featureHighlight.copy((i9 & 1) != 0 ? featureHighlight2.view : view2, (i9 & 2) != 0 ? featureHighlight2.description : str, (i9 & 4) != 0 ? featureHighlight2.title : str2, (i9 & 8) != 0 ? featureHighlight2.descriptionTextSize : i, (i9 & 16) != 0 ? featureHighlight2.titleTextSize : i2, (i9 & 32) != 0 ? featureHighlight2.dimColor : i3, (i9 & 64) != 0 ? featureHighlight2.textColor : i4, (i9 & 128) != 0 ? featureHighlight2.positiveButtonText : str3, (i9 & 256) != 0 ? featureHighlight2.positiveButtonTextColor : i5, (i9 & 512) != 0 ? featureHighlight2.negativeButtonText : str4, (i9 & 1024) != 0 ? featureHighlight2.negativeButtonTextColor : i6, (i9 & 2048) != 0 ? featureHighlight2.buttonTextSize : i7, (i9 & 4096) != 0 ? featureHighlight2.pointerGravity : gravity, (i9 & 8192) != 0 ? featureHighlight2.contentGravity : gravity2, (i9 & C.DASH_ROLE_CAPTION_FLAG) != 0 ? featureHighlight2.shouldHighlightFeatureView : z, (i9 & 32768) != 0 ? featureHighlight2.highlightShape : highlightShape2);
    }

    @NotNull
    public final View component1() {
        return this.view;
    }

    @Nullable
    public final String component10() {
        return this.negativeButtonText;
    }

    public final int component11() {
        return this.negativeButtonTextColor;
    }

    public final int component12() {
        return this.buttonTextSize;
    }

    @Nullable
    public final Gravity component13() {
        return this.pointerGravity;
    }

    @NotNull
    public final Gravity component14() {
        return this.contentGravity;
    }

    public final boolean component15() {
        return this.shouldHighlightFeatureView;
    }

    @Nullable
    public final HighlightShape component16() {
        return this.highlightShape;
    }

    @NotNull
    public final String component2() {
        return this.description;
    }

    @Nullable
    public final String component3() {
        return this.title;
    }

    public final int component4() {
        return this.descriptionTextSize;
    }

    public final int component5() {
        return this.titleTextSize;
    }

    public final int component6() {
        return this.dimColor;
    }

    public final int component7() {
        return this.textColor;
    }

    @Nullable
    public final String component8() {
        return this.positiveButtonText;
    }

    public final int component9() {
        return this.positiveButtonTextColor;
    }

    @NotNull
    public final FeatureHighlight copy(@NotNull View view2, @NotNull String str, @Nullable String str2, int i, int i2, @ColorInt int i3, @ColorInt int i4, @Nullable String str3, @ColorInt int i5, @Nullable String str4, @ColorInt int i6, int i7, @Nullable Gravity gravity, @NotNull Gravity gravity2, boolean z, @Nullable HighlightShape highlightShape2) {
        View view3 = view2;
        String str5 = str;
        String str6 = str2;
        int i8 = i;
        int i9 = i2;
        int i10 = i3;
        int i11 = i4;
        String str7 = str3;
        int i12 = i5;
        String str8 = str4;
        int i13 = i6;
        int i14 = i7;
        Gravity gravity3 = gravity;
        Gravity gravity4 = gravity2;
        boolean z2 = z;
        HighlightShape highlightShape3 = highlightShape2;
        View view4 = view3;
        Intrinsics.checkParameterIsNotNull(view3, Promotion.ACTION_VIEW);
        Intrinsics.checkParameterIsNotNull(str, "description");
        Intrinsics.checkParameterIsNotNull(gravity2, "contentGravity");
        FeatureHighlight featureHighlight = new FeatureHighlight(view4, str5, str6, i8, i9, i10, i11, str7, i12, str8, i13, i14, gravity3, gravity4, z2, highlightShape3);
        return featureHighlight;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof FeatureHighlight) {
                FeatureHighlight featureHighlight = (FeatureHighlight) obj;
                if (Intrinsics.areEqual((Object) this.view, (Object) featureHighlight.view) && Intrinsics.areEqual((Object) this.description, (Object) featureHighlight.description) && Intrinsics.areEqual((Object) this.title, (Object) featureHighlight.title)) {
                    if (this.descriptionTextSize == featureHighlight.descriptionTextSize) {
                        if (this.titleTextSize == featureHighlight.titleTextSize) {
                            if (this.dimColor == featureHighlight.dimColor) {
                                if ((this.textColor == featureHighlight.textColor) && Intrinsics.areEqual((Object) this.positiveButtonText, (Object) featureHighlight.positiveButtonText)) {
                                    if ((this.positiveButtonTextColor == featureHighlight.positiveButtonTextColor) && Intrinsics.areEqual((Object) this.negativeButtonText, (Object) featureHighlight.negativeButtonText)) {
                                        if (this.negativeButtonTextColor == featureHighlight.negativeButtonTextColor) {
                                            if ((this.buttonTextSize == featureHighlight.buttonTextSize) && Intrinsics.areEqual((Object) this.pointerGravity, (Object) featureHighlight.pointerGravity) && Intrinsics.areEqual((Object) this.contentGravity, (Object) featureHighlight.contentGravity)) {
                                                if (!(this.shouldHighlightFeatureView == featureHighlight.shouldHighlightFeatureView) || !Intrinsics.areEqual((Object) this.highlightShape, (Object) featureHighlight.highlightShape)) {
                                                    return false;
                                                }
                                            }
                                        }
                                    }
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
        View view2 = this.view;
        int i = 0;
        int hashCode = (view2 != null ? view2.hashCode() : 0) * 31;
        String str = this.description;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.title;
        int hashCode3 = (((((((((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.descriptionTextSize)) * 31) + Integer.hashCode(this.titleTextSize)) * 31) + Integer.hashCode(this.dimColor)) * 31) + Integer.hashCode(this.textColor)) * 31;
        String str3 = this.positiveButtonText;
        int hashCode4 = (((hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + Integer.hashCode(this.positiveButtonTextColor)) * 31;
        String str4 = this.negativeButtonText;
        int hashCode5 = (((((hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31) + Integer.hashCode(this.negativeButtonTextColor)) * 31) + Integer.hashCode(this.buttonTextSize)) * 31;
        Gravity gravity = this.pointerGravity;
        int hashCode6 = (hashCode5 + (gravity != null ? gravity.hashCode() : 0)) * 31;
        Gravity gravity2 = this.contentGravity;
        int hashCode7 = (hashCode6 + (gravity2 != null ? gravity2.hashCode() : 0)) * 31;
        boolean z = this.shouldHighlightFeatureView;
        if (z) {
            z = true;
        }
        int i2 = (hashCode7 + (z ? 1 : 0)) * 31;
        HighlightShape highlightShape2 = this.highlightShape;
        if (highlightShape2 != null) {
            i = highlightShape2.hashCode();
        }
        return i2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FeatureHighlight(view=");
        sb.append(this.view);
        sb.append(", description=");
        sb.append(this.description);
        sb.append(", title=");
        sb.append(this.title);
        sb.append(", descriptionTextSize=");
        sb.append(this.descriptionTextSize);
        sb.append(", titleTextSize=");
        sb.append(this.titleTextSize);
        sb.append(", dimColor=");
        sb.append(this.dimColor);
        sb.append(", textColor=");
        sb.append(this.textColor);
        sb.append(", positiveButtonText=");
        sb.append(this.positiveButtonText);
        sb.append(", positiveButtonTextColor=");
        sb.append(this.positiveButtonTextColor);
        sb.append(", negativeButtonText=");
        sb.append(this.negativeButtonText);
        sb.append(", negativeButtonTextColor=");
        sb.append(this.negativeButtonTextColor);
        sb.append(", buttonTextSize=");
        sb.append(this.buttonTextSize);
        sb.append(", pointerGravity=");
        sb.append(this.pointerGravity);
        sb.append(", contentGravity=");
        sb.append(this.contentGravity);
        sb.append(", shouldHighlightFeatureView=");
        sb.append(this.shouldHighlightFeatureView);
        sb.append(", highlightShape=");
        sb.append(this.highlightShape);
        sb.append(")");
        return sb.toString();
    }

    public FeatureHighlight(@NotNull View view2, @NotNull String str, @Nullable String str2, int i, int i2, @ColorInt int i3, @ColorInt int i4, @Nullable String str3, @ColorInt int i5, @Nullable String str4, @ColorInt int i6, int i7, @Nullable Gravity gravity, @NotNull Gravity gravity2, boolean z, @Nullable HighlightShape highlightShape2) {
        View view3 = view2;
        String str5 = str;
        Gravity gravity3 = gravity2;
        Intrinsics.checkParameterIsNotNull(view2, Promotion.ACTION_VIEW);
        Intrinsics.checkParameterIsNotNull(str, "description");
        Intrinsics.checkParameterIsNotNull(gravity3, "contentGravity");
        this.view = view3;
        this.description = str5;
        this.title = str2;
        this.descriptionTextSize = i;
        this.titleTextSize = i2;
        this.dimColor = i3;
        this.textColor = i4;
        this.positiveButtonText = str3;
        this.positiveButtonTextColor = i5;
        this.negativeButtonText = str4;
        this.negativeButtonTextColor = i6;
        this.buttonTextSize = i7;
        this.pointerGravity = gravity;
        this.contentGravity = gravity3;
        this.shouldHighlightFeatureView = z;
        this.highlightShape = highlightShape2;
    }

    @NotNull
    public final View getView() {
        return this.view;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    public /* synthetic */ FeatureHighlight(View view2, String str, String str2, int i, int i2, int i3, int i4, String str3, int i5, String str4, int i6, int i7, Gravity gravity, Gravity gravity2, boolean z, HighlightShape highlightShape2, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        int i9 = i8;
        this(view2, str, (i9 & 4) != 0 ? null : str2, (i9 & 8) != 0 ? 14 : i, (i9 & 16) != 0 ? 14 : i2, (i9 & 32) != 0 ? -1996488704 : i3, (i9 & 64) != 0 ? -1996488704 : i4, (i9 & 128) != 0 ? null : str3, (i9 & 256) != 0 ? DEFAULT_BUTTON_COLOR : i5, (i9 & 512) != 0 ? null : str4, (i9 & 1024) != 0 ? -1996488704 : i6, (i9 & 2048) != 0 ? 14 : i7, (i9 & 4096) != 0 ? null : gravity, (i9 & 8192) != 0 ? Gravity.CENTER : gravity2, (i9 & C.DASH_ROLE_CAPTION_FLAG) != 0 ? true : z, (i9 & 32768) != 0 ? HighlightShape.RECT : highlightShape2);
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final int getDescriptionTextSize() {
        return this.descriptionTextSize;
    }

    public final int getTitleTextSize() {
        return this.titleTextSize;
    }

    public final int getDimColor() {
        return this.dimColor;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    @Nullable
    public final String getPositiveButtonText() {
        return this.positiveButtonText;
    }

    public final int getPositiveButtonTextColor() {
        return this.positiveButtonTextColor;
    }

    @Nullable
    public final String getNegativeButtonText() {
        return this.negativeButtonText;
    }

    public final int getNegativeButtonTextColor() {
        return this.negativeButtonTextColor;
    }

    public final int getButtonTextSize() {
        return this.buttonTextSize;
    }

    @Nullable
    public final Gravity getPointerGravity() {
        return this.pointerGravity;
    }

    @NotNull
    public final Gravity getContentGravity() {
        return this.contentGravity;
    }

    public final boolean getShouldHighlightFeatureView() {
        return this.shouldHighlightFeatureView;
    }

    @Nullable
    public final HighlightShape getHighlightShape() {
        return this.highlightShape;
    }
}
