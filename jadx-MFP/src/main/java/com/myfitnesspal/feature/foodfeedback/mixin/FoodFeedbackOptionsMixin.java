package com.myfitnesspal.feature.foodfeedback.mixin;

import android.os.Bundle;
import android.view.View;
import com.brightcove.player.event.AbstractEvent;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 &2\u00020\u0001:\u0001&B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010!H\u0016J\u000e\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0017X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/mixin/FoodFeedbackOptionsMixin;", "Lcom/myfitnesspal/framework/mixin/RunnerLifecycleMixin;", "activity", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "rootView", "Landroid/view/View;", "foodEditorType", "Lcom/myfitnesspal/feature/foodeditor/ui/activity/FoodEditorType;", "(Lcom/myfitnesspal/shared/ui/activity/MfpActivity;Landroid/view/View;Lcom/myfitnesspal/feature/foodeditor/ui/activity/FoodEditorType;)V", "foodFeedbackAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/foodfeedback/service/FoodFeedbackAnalyticsHelper;", "getFoodFeedbackAnalyticsHelper", "()Ldagger/Lazy;", "setFoodFeedbackAnalyticsHelper", "(Ldagger/Lazy;)V", "foodFromFeedback", "", "getFoodFromFeedback", "()Z", "setFoodFromFeedback", "(Z)V", "hideForFoodEditorType", "", "mfpFood", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "getMfpFood", "()Lcom/myfitnesspal/shared/model/v2/MfpFood;", "setMfpFood", "(Lcom/myfitnesspal/shared/model/v2/MfpFood;)V", "onPostCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "showReportFood", "shouldShowFoodFeedback", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackOptionsMixin.kt */
public final class FoodFeedbackOptionsMixin extends RunnerLifecycleMixin {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_FROM_FEEDBACK = "extra_from_feedback";
    private static final String EXTRA_MIXIN_FOOD = "extra_mixin_food";
    /* access modifiers changed from: private */
    public final MfpActivity activity;
    private final FoodEditorType foodEditorType;
    @Inject
    @NotNull
    public Lazy<FoodFeedbackAnalyticsHelper> foodFeedbackAnalyticsHelper;
    private boolean foodFromFeedback;
    private final List<FoodEditorType> hideForFoodEditorType;
    @Nullable
    private MfpFood mfpFood;
    /* access modifiers changed from: private */
    public final View rootView;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/mixin/FoodFeedbackOptionsMixin$Companion;", "", "()V", "EXTRA_FROM_FEEDBACK", "", "EXTRA_MIXIN_FOOD", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodFeedbackOptionsMixin.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmOverloads
    public FoodFeedbackOptionsMixin(@NotNull MfpActivity mfpActivity, @NotNull View view) {
        this(mfpActivity, view, null, 4, null);
    }

    @JvmOverloads
    public /* synthetic */ FoodFeedbackOptionsMixin(MfpActivity mfpActivity, View view, FoodEditorType foodEditorType2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            foodEditorType2 = FoodEditorType.DiaryFood;
        }
        this(mfpActivity, view, foodEditorType2);
    }

    @JvmOverloads
    public FoodFeedbackOptionsMixin(@NotNull MfpActivity mfpActivity, @NotNull View view, @NotNull FoodEditorType foodEditorType2) {
        Intrinsics.checkParameterIsNotNull(mfpActivity, AbstractEvent.ACTIVITY);
        Intrinsics.checkParameterIsNotNull(view, AvidJSONUtil.KEY_ROOT_VIEW);
        Intrinsics.checkParameterIsNotNull(foodEditorType2, "foodEditorType");
        super(mfpActivity);
        this.activity = mfpActivity;
        this.rootView = view;
        this.foodEditorType = foodEditorType2;
        this.hideForFoodEditorType = CollectionsKt.listOf(FoodEditorType.SponsoredFood, FoodEditorType.RestaurantMenuItem, FoodEditorType.Meal, FoodEditorType.ViewSharedMeal);
        component().inject(this);
    }

    public final boolean getFoodFromFeedback() {
        return this.foodFromFeedback;
    }

    public final void setFoodFromFeedback(boolean z) {
        this.foodFromFeedback = z;
    }

    @Nullable
    public final MfpFood getMfpFood() {
        return this.mfpFood;
    }

    public final void setMfpFood(@Nullable MfpFood mfpFood2) {
        this.mfpFood = mfpFood2;
    }

    @NotNull
    public final Lazy<FoodFeedbackAnalyticsHelper> getFoodFeedbackAnalyticsHelper() {
        Lazy<FoodFeedbackAnalyticsHelper> lazy = this.foodFeedbackAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("foodFeedbackAnalyticsHelper");
        }
        return lazy;
    }

    public final void setFoodFeedbackAnalyticsHelper(@NotNull Lazy<FoodFeedbackAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.foodFeedbackAnalyticsHelper = lazy;
    }

    public void onPostCreate(@Nullable Bundle bundle) {
        if (bundle != null) {
            this.mfpFood = (MfpFood) bundle.getParcelable(EXTRA_MIXIN_FOOD);
            this.foodFromFeedback = bundle.getBoolean(EXTRA_FROM_FEEDBACK);
        }
    }

    public void onSaveInstanceState(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelable(EXTRA_MIXIN_FOOD, this.mfpFood);
            bundle.putBoolean(EXTRA_FROM_FEEDBACK, this.foodFromFeedback);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        if (r0.isQuickAddOfAnySort() == false) goto L_0x0049;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void showReportFood(boolean r6) {
        /*
            r5 = this;
            com.myfitnesspal.shared.model.v2.MfpFood r0 = r5.mfpFood
            if (r0 == 0) goto L_0x0078
            com.myfitnesspal.shared.ui.activity.MfpActivity r1 = r5.activity
            com.myfitnesspal.shared.service.config.ConfigService r1 = r1.getConfigService()
            boolean r1 = com.myfitnesspal.shared.util.ConfigUtils.isFoodFeedbackEnabled(r1)
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0048
            if (r6 == 0) goto L_0x0048
            java.lang.Boolean r1 = r0.isPublic()
            java.lang.String r4 = "food.isPublic"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0048
            java.lang.String r1 = r0.getId()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x0034
            int r1 = r1.length()
            if (r1 != 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r1 = 0
            goto L_0x0035
        L_0x0034:
            r1 = 1
        L_0x0035:
            if (r1 != 0) goto L_0x0048
            java.util.List<com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType> r1 = r5.hideForFoodEditorType
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType r4 = r5.foodEditorType
            boolean r1 = r1.contains(r4)
            if (r1 != 0) goto L_0x0048
            boolean r1 = r0.isQuickAddOfAnySort()
            if (r1 != 0) goto L_0x0048
            goto L_0x0049
        L_0x0048:
            r2 = 0
        L_0x0049:
            android.view.View r1 = r5.rootView
            int r4 = com.myfitnesspal.android.R.id.report_food
            android.view.View r1 = r1.findViewById(r4)
            android.widget.TextView r1 = (android.widget.TextView) r1
            java.lang.String r4 = "rootView.report_food"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            android.view.View r1 = (android.view.View) r1
            if (r2 == 0) goto L_0x005d
            goto L_0x005f
        L_0x005d:
            r3 = 8
        L_0x005f:
            r1.setVisibility(r3)
            if (r2 == 0) goto L_0x0078
            android.view.View r1 = r5.rootView
            int r2 = com.myfitnesspal.android.R.id.report_food
            android.view.View r1 = r1.findViewById(r2)
            android.widget.TextView r1 = (android.widget.TextView) r1
            com.myfitnesspal.feature.foodfeedback.mixin.FoodFeedbackOptionsMixin$showReportFood$$inlined$let$lambda$1 r2 = new com.myfitnesspal.feature.foodfeedback.mixin.FoodFeedbackOptionsMixin$showReportFood$$inlined$let$lambda$1
            r2.<init>(r0, r5, r6)
            android.view.View$OnClickListener r2 = (android.view.View.OnClickListener) r2
            r1.setOnClickListener(r2)
        L_0x0078:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodfeedback.mixin.FoodFeedbackOptionsMixin.showReportFood(boolean):void");
    }
}
