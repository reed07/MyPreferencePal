package com.myfitnesspal.feature.foodfeedback.model;

import com.brightcove.player.C;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bB\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BÕ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u001aJ\t\u00103\u001a\u00020\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0002\u0010H\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010L\u001a\u00020MHÖ\u0001J\t\u0010N\u001a\u00020OHÖ\u0001R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001cR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001cR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001cR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001cR\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001cR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001cR\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001c¨\u0006P"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/NutritionalContentsFeedback;", "", "servingSize", "Lcom/myfitnesspal/feature/foodfeedback/model/ServingSize;", "beforeAfterCalories", "Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterCalories;", "fatBeforeAfter", "Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;", "saturatedBeforeAfter", "polyUnsaturatedBeforeAfter", "monoUnsaturatedBeforeAfter", "transBeforeAfter", "cholesterolBeforeAfter", "sodiumBeforeAfter", "potassiumBeforeAfter", "carbsBeforeAfter", "fiberBeforeAfter", "sugarBeforeAfter", "proteinBeforeAfter", "vitaminABeforeAfter", "vitaminCBeforeAfter", "calciumBeforeAfter", "ironBeforeAfter", "addedSugarsBeforeAfter", "vitaminDBeforeAfter", "sugarAlcohols", "(Lcom/myfitnesspal/feature/foodfeedback/model/ServingSize;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterCalories;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;)V", "getAddedSugarsBeforeAfter", "()Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;", "getBeforeAfterCalories", "()Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterCalories;", "getCalciumBeforeAfter", "getCarbsBeforeAfter", "getCholesterolBeforeAfter", "getFatBeforeAfter", "getFiberBeforeAfter", "getIronBeforeAfter", "getMonoUnsaturatedBeforeAfter", "getPolyUnsaturatedBeforeAfter", "getPotassiumBeforeAfter", "getProteinBeforeAfter", "getSaturatedBeforeAfter", "getServingSize", "()Lcom/myfitnesspal/feature/foodfeedback/model/ServingSize;", "getSodiumBeforeAfter", "getSugarAlcohols", "getSugarBeforeAfter", "getTransBeforeAfter", "getVitaminABeforeAfter", "getVitaminCBeforeAfter", "getVitaminDBeforeAfter", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackItem.kt */
public final class NutritionalContentsFeedback {
    @SerializedName("added_sugars")
    @Nullable
    @Expose
    private final BeforeAfterFloat addedSugarsBeforeAfter;
    @SerializedName("calories")
    @Nullable
    @Expose
    private final BeforeAfterCalories beforeAfterCalories;
    @SerializedName("calcium")
    @Nullable
    @Expose
    private final BeforeAfterFloat calciumBeforeAfter;
    @SerializedName("carbs")
    @Nullable
    @Expose
    private final BeforeAfterFloat carbsBeforeAfter;
    @SerializedName("cholesterol")
    @Nullable
    @Expose
    private final BeforeAfterFloat cholesterolBeforeAfter;
    @SerializedName("fat")
    @Nullable
    @Expose
    private final BeforeAfterFloat fatBeforeAfter;
    @SerializedName("fiber")
    @Nullable
    @Expose
    private final BeforeAfterFloat fiberBeforeAfter;
    @SerializedName("iron")
    @Nullable
    @Expose
    private final BeforeAfterFloat ironBeforeAfter;
    @SerializedName("monounsaturated_fat")
    @Nullable
    @Expose
    private final BeforeAfterFloat monoUnsaturatedBeforeAfter;
    @SerializedName("polyunsaturated_fat")
    @Nullable
    @Expose
    private final BeforeAfterFloat polyUnsaturatedBeforeAfter;
    @SerializedName("potassium")
    @Nullable
    @Expose
    private final BeforeAfterFloat potassiumBeforeAfter;
    @SerializedName("protein")
    @Nullable
    @Expose
    private final BeforeAfterFloat proteinBeforeAfter;
    @SerializedName("saturated_fat")
    @Nullable
    @Expose
    private final BeforeAfterFloat saturatedBeforeAfter;
    @SerializedName("serving_size")
    @NotNull
    @Expose
    private final ServingSize servingSize;
    @SerializedName("sodium")
    @Nullable
    @Expose
    private final BeforeAfterFloat sodiumBeforeAfter;
    @SerializedName("sugar_alcohols")
    @Nullable
    @Expose
    private final BeforeAfterFloat sugarAlcohols;
    @SerializedName("sugar")
    @Nullable
    @Expose
    private final BeforeAfterFloat sugarBeforeAfter;
    @SerializedName("trans_fat")
    @Nullable
    @Expose
    private final BeforeAfterFloat transBeforeAfter;
    @SerializedName("vitamin_a")
    @Nullable
    @Expose
    private final BeforeAfterFloat vitaminABeforeAfter;
    @SerializedName("vitamin_c")
    @Nullable
    @Expose
    private final BeforeAfterFloat vitaminCBeforeAfter;
    @SerializedName("vitamin_d")
    @Nullable
    @Expose
    private final BeforeAfterFloat vitaminDBeforeAfter;

    @NotNull
    public static /* synthetic */ NutritionalContentsFeedback copy$default(NutritionalContentsFeedback nutritionalContentsFeedback, ServingSize servingSize2, BeforeAfterCalories beforeAfterCalories2, BeforeAfterFloat beforeAfterFloat, BeforeAfterFloat beforeAfterFloat2, BeforeAfterFloat beforeAfterFloat3, BeforeAfterFloat beforeAfterFloat4, BeforeAfterFloat beforeAfterFloat5, BeforeAfterFloat beforeAfterFloat6, BeforeAfterFloat beforeAfterFloat7, BeforeAfterFloat beforeAfterFloat8, BeforeAfterFloat beforeAfterFloat9, BeforeAfterFloat beforeAfterFloat10, BeforeAfterFloat beforeAfterFloat11, BeforeAfterFloat beforeAfterFloat12, BeforeAfterFloat beforeAfterFloat13, BeforeAfterFloat beforeAfterFloat14, BeforeAfterFloat beforeAfterFloat15, BeforeAfterFloat beforeAfterFloat16, BeforeAfterFloat beforeAfterFloat17, BeforeAfterFloat beforeAfterFloat18, BeforeAfterFloat beforeAfterFloat19, int i, Object obj) {
        BeforeAfterFloat beforeAfterFloat20;
        BeforeAfterFloat beforeAfterFloat21;
        BeforeAfterFloat beforeAfterFloat22;
        BeforeAfterFloat beforeAfterFloat23;
        BeforeAfterFloat beforeAfterFloat24;
        BeforeAfterFloat beforeAfterFloat25;
        BeforeAfterFloat beforeAfterFloat26;
        BeforeAfterFloat beforeAfterFloat27;
        BeforeAfterFloat beforeAfterFloat28;
        BeforeAfterFloat beforeAfterFloat29;
        NutritionalContentsFeedback nutritionalContentsFeedback2 = nutritionalContentsFeedback;
        int i2 = i;
        ServingSize servingSize3 = (i2 & 1) != 0 ? nutritionalContentsFeedback2.servingSize : servingSize2;
        BeforeAfterCalories beforeAfterCalories3 = (i2 & 2) != 0 ? nutritionalContentsFeedback2.beforeAfterCalories : beforeAfterCalories2;
        BeforeAfterFloat beforeAfterFloat30 = (i2 & 4) != 0 ? nutritionalContentsFeedback2.fatBeforeAfter : beforeAfterFloat;
        BeforeAfterFloat beforeAfterFloat31 = (i2 & 8) != 0 ? nutritionalContentsFeedback2.saturatedBeforeAfter : beforeAfterFloat2;
        BeforeAfterFloat beforeAfterFloat32 = (i2 & 16) != 0 ? nutritionalContentsFeedback2.polyUnsaturatedBeforeAfter : beforeAfterFloat3;
        BeforeAfterFloat beforeAfterFloat33 = (i2 & 32) != 0 ? nutritionalContentsFeedback2.monoUnsaturatedBeforeAfter : beforeAfterFloat4;
        BeforeAfterFloat beforeAfterFloat34 = (i2 & 64) != 0 ? nutritionalContentsFeedback2.transBeforeAfter : beforeAfterFloat5;
        BeforeAfterFloat beforeAfterFloat35 = (i2 & 128) != 0 ? nutritionalContentsFeedback2.cholesterolBeforeAfter : beforeAfterFloat6;
        BeforeAfterFloat beforeAfterFloat36 = (i2 & 256) != 0 ? nutritionalContentsFeedback2.sodiumBeforeAfter : beforeAfterFloat7;
        BeforeAfterFloat beforeAfterFloat37 = (i2 & 512) != 0 ? nutritionalContentsFeedback2.potassiumBeforeAfter : beforeAfterFloat8;
        BeforeAfterFloat beforeAfterFloat38 = (i2 & 1024) != 0 ? nutritionalContentsFeedback2.carbsBeforeAfter : beforeAfterFloat9;
        BeforeAfterFloat beforeAfterFloat39 = (i2 & 2048) != 0 ? nutritionalContentsFeedback2.fiberBeforeAfter : beforeAfterFloat10;
        BeforeAfterFloat beforeAfterFloat40 = (i2 & 4096) != 0 ? nutritionalContentsFeedback2.sugarBeforeAfter : beforeAfterFloat11;
        BeforeAfterFloat beforeAfterFloat41 = (i2 & 8192) != 0 ? nutritionalContentsFeedback2.proteinBeforeAfter : beforeAfterFloat12;
        BeforeAfterFloat beforeAfterFloat42 = (i2 & C.DASH_ROLE_CAPTION_FLAG) != 0 ? nutritionalContentsFeedback2.vitaminABeforeAfter : beforeAfterFloat13;
        if ((i2 & 32768) != 0) {
            beforeAfterFloat20 = beforeAfterFloat42;
            beforeAfterFloat21 = nutritionalContentsFeedback2.vitaminCBeforeAfter;
        } else {
            beforeAfterFloat20 = beforeAfterFloat42;
            beforeAfterFloat21 = beforeAfterFloat14;
        }
        if ((i2 & 65536) != 0) {
            beforeAfterFloat22 = beforeAfterFloat21;
            beforeAfterFloat23 = nutritionalContentsFeedback2.calciumBeforeAfter;
        } else {
            beforeAfterFloat22 = beforeAfterFloat21;
            beforeAfterFloat23 = beforeAfterFloat15;
        }
        if ((i2 & 131072) != 0) {
            beforeAfterFloat24 = beforeAfterFloat23;
            beforeAfterFloat25 = nutritionalContentsFeedback2.ironBeforeAfter;
        } else {
            beforeAfterFloat24 = beforeAfterFloat23;
            beforeAfterFloat25 = beforeAfterFloat16;
        }
        if ((i2 & C.DASH_ROLE_SUB_FLAG) != 0) {
            beforeAfterFloat26 = beforeAfterFloat25;
            beforeAfterFloat27 = nutritionalContentsFeedback2.addedSugarsBeforeAfter;
        } else {
            beforeAfterFloat26 = beforeAfterFloat25;
            beforeAfterFloat27 = beforeAfterFloat17;
        }
        if ((i2 & 524288) != 0) {
            beforeAfterFloat28 = beforeAfterFloat27;
            beforeAfterFloat29 = nutritionalContentsFeedback2.vitaminDBeforeAfter;
        } else {
            beforeAfterFloat28 = beforeAfterFloat27;
            beforeAfterFloat29 = beforeAfterFloat18;
        }
        return nutritionalContentsFeedback.copy(servingSize3, beforeAfterCalories3, beforeAfterFloat30, beforeAfterFloat31, beforeAfterFloat32, beforeAfterFloat33, beforeAfterFloat34, beforeAfterFloat35, beforeAfterFloat36, beforeAfterFloat37, beforeAfterFloat38, beforeAfterFloat39, beforeAfterFloat40, beforeAfterFloat41, beforeAfterFloat20, beforeAfterFloat22, beforeAfterFloat24, beforeAfterFloat26, beforeAfterFloat28, beforeAfterFloat29, (i2 & ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? nutritionalContentsFeedback2.sugarAlcohols : beforeAfterFloat19);
    }

    @NotNull
    public final ServingSize component1() {
        return this.servingSize;
    }

    @Nullable
    public final BeforeAfterFloat component10() {
        return this.potassiumBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component11() {
        return this.carbsBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component12() {
        return this.fiberBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component13() {
        return this.sugarBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component14() {
        return this.proteinBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component15() {
        return this.vitaminABeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component16() {
        return this.vitaminCBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component17() {
        return this.calciumBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component18() {
        return this.ironBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component19() {
        return this.addedSugarsBeforeAfter;
    }

    @Nullable
    public final BeforeAfterCalories component2() {
        return this.beforeAfterCalories;
    }

    @Nullable
    public final BeforeAfterFloat component20() {
        return this.vitaminDBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component21() {
        return this.sugarAlcohols;
    }

    @Nullable
    public final BeforeAfterFloat component3() {
        return this.fatBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component4() {
        return this.saturatedBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component5() {
        return this.polyUnsaturatedBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component6() {
        return this.monoUnsaturatedBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component7() {
        return this.transBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component8() {
        return this.cholesterolBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat component9() {
        return this.sodiumBeforeAfter;
    }

    @NotNull
    public final NutritionalContentsFeedback copy(@NotNull ServingSize servingSize2, @Nullable BeforeAfterCalories beforeAfterCalories2, @Nullable BeforeAfterFloat beforeAfterFloat, @Nullable BeforeAfterFloat beforeAfterFloat2, @Nullable BeforeAfterFloat beforeAfterFloat3, @Nullable BeforeAfterFloat beforeAfterFloat4, @Nullable BeforeAfterFloat beforeAfterFloat5, @Nullable BeforeAfterFloat beforeAfterFloat6, @Nullable BeforeAfterFloat beforeAfterFloat7, @Nullable BeforeAfterFloat beforeAfterFloat8, @Nullable BeforeAfterFloat beforeAfterFloat9, @Nullable BeforeAfterFloat beforeAfterFloat10, @Nullable BeforeAfterFloat beforeAfterFloat11, @Nullable BeforeAfterFloat beforeAfterFloat12, @Nullable BeforeAfterFloat beforeAfterFloat13, @Nullable BeforeAfterFloat beforeAfterFloat14, @Nullable BeforeAfterFloat beforeAfterFloat15, @Nullable BeforeAfterFloat beforeAfterFloat16, @Nullable BeforeAfterFloat beforeAfterFloat17, @Nullable BeforeAfterFloat beforeAfterFloat18, @Nullable BeforeAfterFloat beforeAfterFloat19) {
        ServingSize servingSize3 = servingSize2;
        BeforeAfterCalories beforeAfterCalories3 = beforeAfterCalories2;
        BeforeAfterFloat beforeAfterFloat20 = beforeAfterFloat;
        BeforeAfterFloat beforeAfterFloat21 = beforeAfterFloat2;
        BeforeAfterFloat beforeAfterFloat22 = beforeAfterFloat3;
        BeforeAfterFloat beforeAfterFloat23 = beforeAfterFloat4;
        BeforeAfterFloat beforeAfterFloat24 = beforeAfterFloat5;
        BeforeAfterFloat beforeAfterFloat25 = beforeAfterFloat6;
        BeforeAfterFloat beforeAfterFloat26 = beforeAfterFloat7;
        BeforeAfterFloat beforeAfterFloat27 = beforeAfterFloat8;
        BeforeAfterFloat beforeAfterFloat28 = beforeAfterFloat9;
        BeforeAfterFloat beforeAfterFloat29 = beforeAfterFloat10;
        BeforeAfterFloat beforeAfterFloat30 = beforeAfterFloat11;
        BeforeAfterFloat beforeAfterFloat31 = beforeAfterFloat12;
        BeforeAfterFloat beforeAfterFloat32 = beforeAfterFloat13;
        BeforeAfterFloat beforeAfterFloat33 = beforeAfterFloat14;
        BeforeAfterFloat beforeAfterFloat34 = beforeAfterFloat15;
        BeforeAfterFloat beforeAfterFloat35 = beforeAfterFloat16;
        BeforeAfterFloat beforeAfterFloat36 = beforeAfterFloat17;
        BeforeAfterFloat beforeAfterFloat37 = beforeAfterFloat18;
        BeforeAfterFloat beforeAfterFloat38 = beforeAfterFloat19;
        ServingSize servingSize4 = servingSize3;
        Intrinsics.checkParameterIsNotNull(servingSize3, "servingSize");
        NutritionalContentsFeedback nutritionalContentsFeedback = new NutritionalContentsFeedback(servingSize3, beforeAfterCalories3, beforeAfterFloat20, beforeAfterFloat21, beforeAfterFloat22, beforeAfterFloat23, beforeAfterFloat24, beforeAfterFloat25, beforeAfterFloat26, beforeAfterFloat27, beforeAfterFloat28, beforeAfterFloat29, beforeAfterFloat30, beforeAfterFloat31, beforeAfterFloat32, beforeAfterFloat33, beforeAfterFloat34, beforeAfterFloat35, beforeAfterFloat36, beforeAfterFloat37, beforeAfterFloat38);
        return nutritionalContentsFeedback;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d8, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.sugarAlcohols, (java.lang.Object) r3.sugarAlcohols) != false) goto L_0x00dd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x00dd
            boolean r0 = r3 instanceof com.myfitnesspal.feature.foodfeedback.model.NutritionalContentsFeedback
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.NutritionalContentsFeedback r3 = (com.myfitnesspal.feature.foodfeedback.model.NutritionalContentsFeedback) r3
            com.myfitnesspal.feature.foodfeedback.model.ServingSize r0 = r2.servingSize
            com.myfitnesspal.feature.foodfeedback.model.ServingSize r1 = r3.servingSize
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterCalories r0 = r2.beforeAfterCalories
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterCalories r1 = r3.beforeAfterCalories
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.fatBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.fatBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.saturatedBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.saturatedBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.polyUnsaturatedBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.polyUnsaturatedBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.monoUnsaturatedBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.monoUnsaturatedBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.transBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.transBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.cholesterolBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.cholesterolBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.sodiumBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.sodiumBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.potassiumBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.potassiumBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.carbsBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.carbsBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.fiberBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.fiberBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.sugarBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.sugarBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.proteinBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.proteinBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.vitaminABeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.vitaminABeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.vitaminCBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.vitaminCBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.calciumBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.calciumBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.ironBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.ironBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.addedSugarsBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.addedSugarsBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.vitaminDBeforeAfter
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r1 = r3.vitaminDBeforeAfter
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x00db
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r0 = r2.sugarAlcohols
            com.myfitnesspal.feature.foodfeedback.model.BeforeAfterFloat r3 = r3.sugarAlcohols
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x00db
            goto L_0x00dd
        L_0x00db:
            r3 = 0
            return r3
        L_0x00dd:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodfeedback.model.NutritionalContentsFeedback.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        ServingSize servingSize2 = this.servingSize;
        int i = 0;
        int hashCode = (servingSize2 != null ? servingSize2.hashCode() : 0) * 31;
        BeforeAfterCalories beforeAfterCalories2 = this.beforeAfterCalories;
        int hashCode2 = (hashCode + (beforeAfterCalories2 != null ? beforeAfterCalories2.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat = this.fatBeforeAfter;
        int hashCode3 = (hashCode2 + (beforeAfterFloat != null ? beforeAfterFloat.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat2 = this.saturatedBeforeAfter;
        int hashCode4 = (hashCode3 + (beforeAfterFloat2 != null ? beforeAfterFloat2.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat3 = this.polyUnsaturatedBeforeAfter;
        int hashCode5 = (hashCode4 + (beforeAfterFloat3 != null ? beforeAfterFloat3.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat4 = this.monoUnsaturatedBeforeAfter;
        int hashCode6 = (hashCode5 + (beforeAfterFloat4 != null ? beforeAfterFloat4.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat5 = this.transBeforeAfter;
        int hashCode7 = (hashCode6 + (beforeAfterFloat5 != null ? beforeAfterFloat5.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat6 = this.cholesterolBeforeAfter;
        int hashCode8 = (hashCode7 + (beforeAfterFloat6 != null ? beforeAfterFloat6.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat7 = this.sodiumBeforeAfter;
        int hashCode9 = (hashCode8 + (beforeAfterFloat7 != null ? beforeAfterFloat7.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat8 = this.potassiumBeforeAfter;
        int hashCode10 = (hashCode9 + (beforeAfterFloat8 != null ? beforeAfterFloat8.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat9 = this.carbsBeforeAfter;
        int hashCode11 = (hashCode10 + (beforeAfterFloat9 != null ? beforeAfterFloat9.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat10 = this.fiberBeforeAfter;
        int hashCode12 = (hashCode11 + (beforeAfterFloat10 != null ? beforeAfterFloat10.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat11 = this.sugarBeforeAfter;
        int hashCode13 = (hashCode12 + (beforeAfterFloat11 != null ? beforeAfterFloat11.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat12 = this.proteinBeforeAfter;
        int hashCode14 = (hashCode13 + (beforeAfterFloat12 != null ? beforeAfterFloat12.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat13 = this.vitaminABeforeAfter;
        int hashCode15 = (hashCode14 + (beforeAfterFloat13 != null ? beforeAfterFloat13.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat14 = this.vitaminCBeforeAfter;
        int hashCode16 = (hashCode15 + (beforeAfterFloat14 != null ? beforeAfterFloat14.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat15 = this.calciumBeforeAfter;
        int hashCode17 = (hashCode16 + (beforeAfterFloat15 != null ? beforeAfterFloat15.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat16 = this.ironBeforeAfter;
        int hashCode18 = (hashCode17 + (beforeAfterFloat16 != null ? beforeAfterFloat16.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat17 = this.addedSugarsBeforeAfter;
        int hashCode19 = (hashCode18 + (beforeAfterFloat17 != null ? beforeAfterFloat17.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat18 = this.vitaminDBeforeAfter;
        int hashCode20 = (hashCode19 + (beforeAfterFloat18 != null ? beforeAfterFloat18.hashCode() : 0)) * 31;
        BeforeAfterFloat beforeAfterFloat19 = this.sugarAlcohols;
        if (beforeAfterFloat19 != null) {
            i = beforeAfterFloat19.hashCode();
        }
        return hashCode20 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NutritionalContentsFeedback(servingSize=");
        sb.append(this.servingSize);
        sb.append(", beforeAfterCalories=");
        sb.append(this.beforeAfterCalories);
        sb.append(", fatBeforeAfter=");
        sb.append(this.fatBeforeAfter);
        sb.append(", saturatedBeforeAfter=");
        sb.append(this.saturatedBeforeAfter);
        sb.append(", polyUnsaturatedBeforeAfter=");
        sb.append(this.polyUnsaturatedBeforeAfter);
        sb.append(", monoUnsaturatedBeforeAfter=");
        sb.append(this.monoUnsaturatedBeforeAfter);
        sb.append(", transBeforeAfter=");
        sb.append(this.transBeforeAfter);
        sb.append(", cholesterolBeforeAfter=");
        sb.append(this.cholesterolBeforeAfter);
        sb.append(", sodiumBeforeAfter=");
        sb.append(this.sodiumBeforeAfter);
        sb.append(", potassiumBeforeAfter=");
        sb.append(this.potassiumBeforeAfter);
        sb.append(", carbsBeforeAfter=");
        sb.append(this.carbsBeforeAfter);
        sb.append(", fiberBeforeAfter=");
        sb.append(this.fiberBeforeAfter);
        sb.append(", sugarBeforeAfter=");
        sb.append(this.sugarBeforeAfter);
        sb.append(", proteinBeforeAfter=");
        sb.append(this.proteinBeforeAfter);
        sb.append(", vitaminABeforeAfter=");
        sb.append(this.vitaminABeforeAfter);
        sb.append(", vitaminCBeforeAfter=");
        sb.append(this.vitaminCBeforeAfter);
        sb.append(", calciumBeforeAfter=");
        sb.append(this.calciumBeforeAfter);
        sb.append(", ironBeforeAfter=");
        sb.append(this.ironBeforeAfter);
        sb.append(", addedSugarsBeforeAfter=");
        sb.append(this.addedSugarsBeforeAfter);
        sb.append(", vitaminDBeforeAfter=");
        sb.append(this.vitaminDBeforeAfter);
        sb.append(", sugarAlcohols=");
        sb.append(this.sugarAlcohols);
        sb.append(")");
        return sb.toString();
    }

    public NutritionalContentsFeedback(@NotNull ServingSize servingSize2, @Nullable BeforeAfterCalories beforeAfterCalories2, @Nullable BeforeAfterFloat beforeAfterFloat, @Nullable BeforeAfterFloat beforeAfterFloat2, @Nullable BeforeAfterFloat beforeAfterFloat3, @Nullable BeforeAfterFloat beforeAfterFloat4, @Nullable BeforeAfterFloat beforeAfterFloat5, @Nullable BeforeAfterFloat beforeAfterFloat6, @Nullable BeforeAfterFloat beforeAfterFloat7, @Nullable BeforeAfterFloat beforeAfterFloat8, @Nullable BeforeAfterFloat beforeAfterFloat9, @Nullable BeforeAfterFloat beforeAfterFloat10, @Nullable BeforeAfterFloat beforeAfterFloat11, @Nullable BeforeAfterFloat beforeAfterFloat12, @Nullable BeforeAfterFloat beforeAfterFloat13, @Nullable BeforeAfterFloat beforeAfterFloat14, @Nullable BeforeAfterFloat beforeAfterFloat15, @Nullable BeforeAfterFloat beforeAfterFloat16, @Nullable BeforeAfterFloat beforeAfterFloat17, @Nullable BeforeAfterFloat beforeAfterFloat18, @Nullable BeforeAfterFloat beforeAfterFloat19) {
        ServingSize servingSize3 = servingSize2;
        Intrinsics.checkParameterIsNotNull(servingSize2, "servingSize");
        this.servingSize = servingSize3;
        this.beforeAfterCalories = beforeAfterCalories2;
        this.fatBeforeAfter = beforeAfterFloat;
        this.saturatedBeforeAfter = beforeAfterFloat2;
        this.polyUnsaturatedBeforeAfter = beforeAfterFloat3;
        this.monoUnsaturatedBeforeAfter = beforeAfterFloat4;
        this.transBeforeAfter = beforeAfterFloat5;
        this.cholesterolBeforeAfter = beforeAfterFloat6;
        this.sodiumBeforeAfter = beforeAfterFloat7;
        this.potassiumBeforeAfter = beforeAfterFloat8;
        this.carbsBeforeAfter = beforeAfterFloat9;
        this.fiberBeforeAfter = beforeAfterFloat10;
        this.sugarBeforeAfter = beforeAfterFloat11;
        this.proteinBeforeAfter = beforeAfterFloat12;
        this.vitaminABeforeAfter = beforeAfterFloat13;
        this.vitaminCBeforeAfter = beforeAfterFloat14;
        this.calciumBeforeAfter = beforeAfterFloat15;
        this.ironBeforeAfter = beforeAfterFloat16;
        this.addedSugarsBeforeAfter = beforeAfterFloat17;
        this.vitaminDBeforeAfter = beforeAfterFloat18;
        this.sugarAlcohols = beforeAfterFloat19;
    }

    @NotNull
    public final ServingSize getServingSize() {
        return this.servingSize;
    }

    @Nullable
    public final BeforeAfterCalories getBeforeAfterCalories() {
        return this.beforeAfterCalories;
    }

    @Nullable
    public final BeforeAfterFloat getFatBeforeAfter() {
        return this.fatBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getSaturatedBeforeAfter() {
        return this.saturatedBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getPolyUnsaturatedBeforeAfter() {
        return this.polyUnsaturatedBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getMonoUnsaturatedBeforeAfter() {
        return this.monoUnsaturatedBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getTransBeforeAfter() {
        return this.transBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getCholesterolBeforeAfter() {
        return this.cholesterolBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getSodiumBeforeAfter() {
        return this.sodiumBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getPotassiumBeforeAfter() {
        return this.potassiumBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getCarbsBeforeAfter() {
        return this.carbsBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getFiberBeforeAfter() {
        return this.fiberBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getSugarBeforeAfter() {
        return this.sugarBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getProteinBeforeAfter() {
        return this.proteinBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getVitaminABeforeAfter() {
        return this.vitaminABeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getVitaminCBeforeAfter() {
        return this.vitaminCBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getCalciumBeforeAfter() {
        return this.calciumBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getIronBeforeAfter() {
        return this.ironBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getAddedSugarsBeforeAfter() {
        return this.addedSugarsBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getVitaminDBeforeAfter() {
        return this.vitaminDBeforeAfter;
    }

    @Nullable
    public final BeforeAfterFloat getSugarAlcohols() {
        return this.sugarAlcohols;
    }
}
