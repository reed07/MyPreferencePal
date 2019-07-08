package com.myfitnesspal.feature.onboarding.model;

import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rj\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lcom/myfitnesspal/feature/onboarding/model/OnboardingScreen;", "", "animation", "", "title", "", "description", "btnFeatureTitle", "id", "(Ljava/lang/String;ILjava/lang/String;IIILjava/lang/String;)V", "getAnimation", "()Ljava/lang/String;", "getBtnFeatureTitle", "()I", "getDescription", "getId", "getTitle", "FoodAnalysis", "CustomDashboard", "MacroByGram", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: OnboardingScreen.kt */
public enum OnboardingScreen {
    FoodAnalysis("animation/2_FoodAnalysis.json", R.string.onboarding_screen_title_food_analysis, R.string.onboarding_screen_description_food_analysis, R.string.onboarding_screen_btn_feature_title_food_analysis, Screens.PREMIUM_ONBOARDING_FOOD_ANALYSIS),
    CustomDashboard("animation/3_customSummary.json", R.string.onboarding_screen_title_custom_dashboard, R.string.onboarding_screen_description_custom_dashboard, R.string.onboarding_screen_btn_feature_title_custom_dashboard, Screens.PREMIUM_ONBOARDING_CUSTOM_DASHBOARD),
    MacroByGram("animation/4_Macros.json", R.string.onboarding_screen_title_macro_goals, R.string.onboarding_screen_description_macro_goals, R.string.onboarding_screen_btn_feature_title_macro_goals, Screens.PREMIUM_ONBOARDING_MACRO_GOALS);
    
    @NotNull
    private final String animation;
    private final int btnFeatureTitle;
    private final int description;
    @NotNull
    private final String id;
    private final int title;

    protected OnboardingScreen(String str, int i, @NotNull int i2, int i3, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "animation");
        Intrinsics.checkParameterIsNotNull(str2, "id");
        this.animation = str;
        this.title = i;
        this.description = i2;
        this.btnFeatureTitle = i3;
        this.id = str2;
    }

    @NotNull
    public final String getAnimation() {
        return this.animation;
    }

    public final int getBtnFeatureTitle() {
        return this.btnFeatureTitle;
    }

    public final int getDescription() {
        return this.description;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final int getTitle() {
        return this.title;
    }
}
