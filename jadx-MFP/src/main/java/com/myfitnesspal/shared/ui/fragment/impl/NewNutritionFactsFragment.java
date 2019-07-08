package com.myfitnesspal.shared.ui.fragment.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.task.FetchNutrientGoalForDateTask;
import com.myfitnesspal.shared.task.FetchNutrientGoalForDateTask.CompletedEvent;
import com.myfitnesspal.shared.ui.fragment.NutritionFactsFragmentBase;
import com.myfitnesspal.shared.ui.view.CustomMacroProgressBar;
import com.myfitnesspal.shared.ui.view.MacroDetails;
import com.myfitnesspal.shared.ui.view.MacroWheelWithText;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Date;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class NewNutritionFactsFragment extends NutritionFactsFragmentBase {
    private static final String PREMIUM_FEATURE_DAILY_VALUE = "premium-daily-value";
    private static final String UNIT_GRAMS = "g";
    private static final String UNIT_MILLIGRAMS = "mg";
    @Nullable
    @BindView(2131363324)
    CustomMacroProgressBar caloriesProgress;
    @Nullable
    @BindView(2131363325)
    CustomMacroProgressBar carbsProgress;
    @Nullable
    @BindView(2131362308)
    MacroDetails detailsCarbs;
    @Nullable
    @BindView(2131362309)
    MacroDetails detailsFat;
    @Nullable
    @BindView(2131362310)
    MacroDetails detailsProtein;
    @Nullable
    @BindView(2131363329)
    CustomMacroProgressBar fatProgress;
    @Nullable
    @BindView(2131362709)
    TextView goPremiumCta;
    @Nullable
    @BindView(2131362980)
    MacroWheelWithText macroWheelWithText;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    private OnNutritionFactsExpandedOrCollapsedListener nutritionFactsExpandedOrCollapsedListener;
    @Nullable
    @BindView(2131363197)
    public View nutritionFactsPrem;
    @Nullable
    @BindView(2131363256)
    View percentDailyGoalsContainer;
    @Inject
    Lazy<PremiumService> premiumService;
    private OnClickListener premiumUpsellCtaClickListener = new OnClickListener() {
        public void onClick(View view) {
            NewNutritionFactsFragment.this.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) NewNutritionFactsFragment.this.getActivity(), NewNutritionFactsFragment.PREMIUM_FEATURE_DAILY_VALUE)).startActivity();
        }
    };
    @Nullable
    @BindView(2131363333)
    CustomMacroProgressBar proteinProgress;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public interface OnNutritionFactsExpandedOrCollapsedListener {
        void onNutritionFactsExpandedOrCollapsed(boolean z);
    }

    /* access modifiers changed from: protected */
    public String getUnitGmDetailed() {
        return UNIT_GRAMS;
    }

    /* access modifiers changed from: protected */
    public String getUnitGmSimple() {
        return "";
    }

    /* access modifiers changed from: protected */
    public String getUnitMg() {
        return UNIT_MILLIGRAMS;
    }

    public static NewNutritionFactsFragment newInstance(MfpNutritionalContents mfpNutritionalContents, int i, int i2, double d) {
        NewNutritionFactsFragment newNutritionFactsFragment = new NewNutritionFactsFragment();
        setArgumentsToFragment(newNutritionFactsFragment, mfpNutritionalContents, i, i2, d, false);
        return newNutritionFactsFragment;
    }

    public static NewNutritionFactsFragment newInstanceForMealFood(MfpNutritionalContents mfpNutritionalContents, int i, int i2, double d) {
        NewNutritionFactsFragment newNutritionFactsFragment = new NewNutritionFactsFragment();
        setArgumentsToFragment(newNutritionFactsFragment, mfpNutritionalContents, i, i2, d, true);
        return newNutritionFactsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return ConfigUtils.isNewNutrientsForUsEnabled(getConfigService()) ? R.layout.new_nutrition_facts_us : R.layout.new_nutrition_facts;
    }

    /* access modifiers changed from: protected */
    public void onShowMoreClicked(int i) {
        boolean z = false;
        int i2 = i == 0 ? 1 : 0;
        setDisplayMode(i2);
        getAnalyticsService().reportEvent(i2 == 0 ? Events.FOOD_DETAILS_HIDE : Events.FOOD_DETAILS_EXPAND);
        OnNutritionFactsExpandedOrCollapsedListener onNutritionFactsExpandedOrCollapsedListener = this.nutritionFactsExpandedOrCollapsedListener;
        if (onNutritionFactsExpandedOrCollapsedListener != null) {
            if (i2 == 1) {
                z = true;
            }
            onNutritionFactsExpandedOrCollapsedListener.onNutritionFactsExpandedOrCollapsed(z);
        }
    }

    /* access modifiers changed from: protected */
    public void onDisplayModeChanged(int i) {
        toggleNutritionFactsWithAnimation(i);
        setCaretProperties(i);
    }

    public void setNutritionFactsExpandedOrCollapsedListener(OnNutritionFactsExpandedOrCollapsedListener onNutritionFactsExpandedOrCollapsedListener) {
        this.nutritionFactsExpandedOrCollapsedListener = onNutritionFactsExpandedOrCollapsedListener;
    }

    private void setCaretProperties(int i) {
        TextView textView = (TextView) ViewUtils.findById(this.showMore, R.id.show_more_text);
        ImageView imageView = (ImageView) ViewUtils.findById(this.showMore, R.id.show_more_icon);
        boolean z = i == 0;
        imageView.setImageResource(z ? R.drawable.ic_chevron_down_black_24dp : R.drawable.ic_chevron_up_black_24dp);
        textView.setText(z ? R.string.view_nutrition_facts : R.string.close_nutrition_facts);
        boolean z2 = !ConfigUtils.showFoodInfoExpandCaret(getConfigService());
        ViewUtils.setVisible(!z2, imageView);
        ViewUtils.setVisible(z2, textView);
    }

    /* access modifiers changed from: protected */
    public void redrawSimpleValues(View view, MfpNutritionalContents mfpNutritionalContents) {
        MfpNutritionalContents nutritionalContents = getNutritionalContents();
        if (shouldShowFoodDesignUpdate()) {
            ViewUtils.setVisible(this.nutritionFactsPrem);
            ViewUtils.setGone(this.simpleView);
            boolean isPremiumSubscribed = ((PremiumService) this.premiumService.get()).isPremiumSubscribed();
            Date activeDate = getSession().getUser().getActiveDate();
            float caloriesValue = nutritionalContents.getEnergy().getCaloriesValue();
            double doubleValue = nutritionalContents.getCarbohydrates().doubleValue() > 0.0d ? nutritionalContents.getCarbohydrates().doubleValue() * getScale() : 0.0d;
            double doubleValue2 = nutritionalContents.getFat().doubleValue() > 0.0d ? nutritionalContents.getFat().doubleValue() * getScale() : 0.0d;
            double doubleValue3 = nutritionalContents.getProtein().doubleValue() > 0.0d ? nutritionalContents.getProtein().doubleValue() * getScale() : 0.0d;
            if (!isPremiumSubscribed) {
                this.percentDailyGoalsContainer.setOnClickListener(this.premiumUpsellCtaClickListener);
            }
            setMacros(caloriesValue, doubleValue, doubleValue2, doubleValue3);
            FetchNutrientGoalForDateTask fetchNutrientGoalForDateTask = new FetchNutrientGoalForDateTask(this.nutrientGoalService, isPremiumSubscribed, activeDate, doubleValue, doubleValue2, doubleValue3, ((double) caloriesValue) * getScale());
            fetchNutrientGoalForDateTask.run(getRunner());
            return;
        }
        ViewUtils.setGone(this.nutritionFactsPrem);
        super.redrawSimpleValues(this.simpleView, nutritionalContents);
    }

    @Subscribe
    public void onNutrientGoalFetched(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner()) && completedEvent.successful()) {
            setProgressValues((MfpDailyGoal) completedEvent.getResult(), completedEvent.getActiveDate(), completedEvent.isPremiumSubscribed(), completedEvent.getCarbs(), completedEvent.getFat(), completedEvent.getProtein(), completedEvent.getCaloriesEnergy());
        }
    }

    private boolean shouldShowFoodDesignUpdate() {
        return isForMeals() || ConfigUtils.isFoodDetailsScreenUpdateOn(getConfigService());
    }

    private void setMacros(float f, double d, double d2, double d3) {
        this.macroWheelWithText.setEnergyUnit(getString(((UserEnergyService) this.userEnergyService.get()).isCalories() ? R.string.macrowheel_cal : R.string.kj));
        this.macroWheelWithText.setEnergyValue(getEnergyValue(f));
        double d4 = d;
        float f2 = (float) d4;
        float f3 = (float) d3;
        float f4 = (float) d2;
        int round = Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMacroCarbohydratesPercentage(f2, f3, f4));
        int round2 = Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMacroFatPercentage(f2, f3, f4));
        int i = (100 - round) - round2;
        this.macroWheelWithText.setMacroWheelValues(round, round2, i);
        setMacro(this.detailsCarbs, getString(R.string.carbs), R.color.mfp_blue, d4, round);
        setMacro(this.detailsFat, getString(R.string.fat), R.color.red_light, d2, round2);
        setMacro(this.detailsProtein, getString(R.string.protein), R.color.android_green, d3, i);
    }

    private void setProgressValues(MfpDailyGoal mfpDailyGoal, Date date, boolean z, double d, double d2, double d3, double d4) {
        MfpDailyGoal mfpDailyGoal2 = mfpDailyGoal;
        boolean z2 = z;
        DiaryDay diaryDay = new DiaryDay();
        Date date2 = date;
        diaryDay.initFromDatabaseForDate(date);
        float round = (float) Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 9));
        float round2 = (float) Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 1));
        float round3 = (float) Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 12));
        double calories = (double) (((UserEnergyService) this.userEnergyService.get()).getCalories((float) (mfpDailyGoal2 == null || mfpDailyGoal.isAssignExerciseEnergyOn() ? (double) diaryDay.caloriesBurnedByExercise(true) : 0.0d)) + mfpDailyGoal.getEnergy().getValue());
        this.caloriesProgress.setMacroName(getEnergyType(), getResources().getColor(R.color.orange), z2);
        this.caloriesProgress.setMacroNameColor(getResources().getColor(R.color.orange));
        this.caloriesProgress.setGoalValue(NumberUtils.localeStringFromInt((int) LocalizedEnergy.fromCalories(calories).getValue(((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit())));
        String localeStringFromDoubleNoDecimal = NumberUtils.localeStringFromDoubleNoDecimal((100.0d * d4) / calories);
        this.caloriesProgress.setGoalPercent(getString(R.string.percent_format, localeStringFromDoubleNoDecimal));
        this.caloriesProgress.setProgress(NumberUtils.tryParseInt(localeStringFromDoubleNoDecimal), R.color.orange);
        ViewUtils.setVisible(!z2, this.goPremiumCta);
        boolean z3 = z;
        setProgress(this.carbsProgress, R.string.carbs, d, R.color.macro_wheel_carbs, round, z3);
        setProgress(this.fatProgress, R.string.fat, d2, R.color.macro_wheel_fat, round2, z3);
        setProgress(this.proteinProgress, R.string.protein, d3, R.color.macro_wheel_protein, round3, z3);
    }

    private void setProgress(CustomMacroProgressBar customMacroProgressBar, int i, double d, int i2, float f, boolean z) {
        customMacroProgressBar.setMacroName(getString(i), i2, z);
        if (z) {
            customMacroProgressBar.hideLock();
            customMacroProgressBar.setGoalValue(NumberUtils.localeStringFromDouble(d));
            double d2 = (double) f;
            String localeStringFromDoubleNoDecimal = NumberUtils.localeStringFromDoubleNoDecimal((d * 100.0d) / d2);
            customMacroProgressBar.setGoalPercent(getString(R.string.percent_format, localeStringFromDoubleNoDecimal));
            customMacroProgressBar.setProgress(NumberUtils.tryParseInt(localeStringFromDoubleNoDecimal), i2);
            customMacroProgressBar.setGoalValue(getString(R.string.macro_prem_value, NumberUtils.localeStringFromDouble(d2)));
            return;
        }
        customMacroProgressBar.showLock();
    }

    private void setMacro(MacroDetails macroDetails, String str, int i, double d, int i2) {
        macroDetails.setMacroName(str, getResources().getColor(i));
        macroDetails.setMacroValue(getString(R.string.macro_prem_value, NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(d)));
        macroDetails.setMacroPercent(getString(R.string.percent_format, NumberUtils.localeStringFromInt(i2)));
    }

    private void toggleNutritionFactsWithAnimation(int i) {
        if (i != 0) {
            ViewUtils.setVisible(true, this.detailedView);
            this.detailedView.clearAnimation();
            this.detailedView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_down_200));
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up_200);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                ViewUtils.setVisible(false, NewNutritionFactsFragment.this.detailedView);
            }
        });
        this.detailedView.clearAnimation();
        this.detailedView.startAnimation(loadAnimation);
    }
}
