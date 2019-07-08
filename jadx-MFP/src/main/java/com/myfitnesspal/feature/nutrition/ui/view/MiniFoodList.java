package com.myfitnesspal.feature.nutrition.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.nutrition.event.NavigateToFoodListEvent;
import com.myfitnesspal.feature.nutrition.event.NavigateToPremiumUpsellEvent;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.service.NutritionAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs;
import com.myfitnesspal.feature.nutrition.service.renderer.MiniFoodListsChartRendererImpl;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Date;
import javax.inject.Inject;

public class MiniFoodList {
    private static final int EXAMPLE_RICE_BOWL_CALORIES = 485;
    private static final int EXAMPLE_RICE_BOWL_CARBS = 43;
    private static final int EXAMPLE_TOMATO_SOUP_CALORIES = 185;
    private static final int EXAMPLE_TOMATO_SOUP_CARBS = 30;
    @Inject
    Lazy<Bus> bus;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<CoreChartRendererBaseConstructorArgs> coreChartRendererBaseConstructorArgs;
    private boolean isPrimary;
    private boolean isWeekly;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringUtil;
    /* access modifiers changed from: private */
    public NutrientEntry nutrientEntry;
    @Inject
    Lazy<NutritionAnalyticsHelper> nutritionAnalyticsHelper;
    private OnClickListener onUpgradeClickListener = new OnClickListener() {
        public void onClick(View view) {
            MiniFoodList.this.getMessageBus().post(new NavigateToPremiumUpsellEvent(PremiumFeature.FoodLists));
        }
    };
    @Inject
    Lazy<PremiumService> premiumService;
    private Date selectedDate;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public MiniFoodList(NutrientEntry nutrientEntry2, Date date, boolean z, boolean z2) {
        MyFitnessPalApp.getInstance().component().inject(this);
        this.nutrientEntry = nutrientEntry2;
        this.selectedDate = date;
        this.isPrimary = z;
        this.isWeekly = z2;
    }

    public void addView(Context context, int i, ViewGroup viewGroup) {
        ViewGroup viewGroup2;
        Context context2 = context;
        ViewGroup viewGroup3 = viewGroup;
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup viewGroup4 = (ViewGroup) from.inflate(R.layout.mini_food_list, viewGroup3, false);
        boolean isFeatureSubscribed = ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.FoodLists);
        ViewGroup viewGroup5 = (ViewGroup) ViewUtils.findById(viewGroup4, R.id.content_container);
        TextView textView = (TextView) ViewUtils.findById(viewGroup4, R.id.header);
        TextView textView2 = (TextView) ViewUtils.findById(viewGroup4, R.id.unit);
        TextView textView3 = (TextView) ViewUtils.findById(viewGroup4, R.id.clickable_text);
        View findById = ViewUtils.findById(viewGroup4, R.id.bottom_divider);
        View findById2 = ViewUtils.findById(viewGroup4, R.id.premium_lock);
        View findById3 = ViewUtils.findById(viewGroup4, R.id.header_container);
        int nutrientIndex = this.nutrientEntry.getNutrientIndex();
        final String fieldLabel = this.nutrientEntry.getFieldLabel();
        View view = findById3;
        textView.setText(String.format(((LocalizedStringsUtil) this.localizedStringUtil.get()).getNutrientString(isFeatureSubscribed ? LocalizedStrings.HIGHEST_IN : LocalizedStrings.FOODS_HIGHEST_IN, nutrientIndex, this.userEnergyService), new Object[]{fieldLabel}));
        viewGroup5.removeAllViews();
        ViewUtils.setVisible(isFeatureSubscribed, textView2);
        ViewUtils.setVisible(!isFeatureSubscribed, findById2);
        if (!isFeatureSubscribed) {
            from.inflate(R.layout.food_list_upgrade_now, viewGroup5);
            TextView textView4 = (TextView) ViewUtils.findById(viewGroup5, R.id.upgrade_text);
            View findById4 = ViewUtils.findById(viewGroup5, R.id.upgrade_btn);
            View findById5 = ViewUtils.findById(viewGroup5, R.id.iv_upgrade_crown);
            TextView textView5 = (TextView) ViewUtils.findById(viewGroup5, R.id.tv_upgrade_crown);
            ViewUtils.setVisible(this.isPrimary, findById4);
            ViewUtils.setVisible(!this.isPrimary, findById);
            ViewUtils.setVisible(!this.isPrimary, textView3);
            setupExampleFoodListIfNecessary(context2, viewGroup4, nutrientIndex);
            boolean showExamplesInPremiumUpsellFoodList = ConfigUtils.showExamplesInPremiumUpsellFoodList((ConfigService) this.configService.get());
            textView4.setText(String.format(((LocalizedStringsUtil) this.localizedStringUtil.get()).getNutrientString(showExamplesInPremiumUpsellFoodList ? LocalizedStrings.UPGRADE_FOR_HIGHEST_IN_V2 : LocalizedStrings.UPGRADE_FOR_HIGHEST_IN, nutrientIndex, this.userEnergyService), new Object[]{fieldLabel}));
            textView3.setText(context2.getString(showExamplesInPremiumUpsellFoodList ? R.string.go_premium : R.string.upgrade_now));
            ViewUtils.setVisible(showExamplesInPremiumUpsellFoodList, findById5);
            textView5.setText(context2.getString(showExamplesInPremiumUpsellFoodList ? R.string.analyze_my_foods : R.string.upgrade_to_premium));
            textView3.setOnClickListener(this.onUpgradeClickListener);
            findById4.setOnClickListener(this.onUpgradeClickListener);
            view.setOnClickListener(this.onUpgradeClickListener);
            viewGroup2 = viewGroup;
        } else {
            from.inflate(R.layout.mini_food_list_spinner_container, viewGroup5);
            MiniFoodListsChartRendererImpl miniFoodListsChartRendererImpl = new MiniFoodListsChartRendererImpl(context2, this.coreChartRendererBaseConstructorArgs, this.nutritionAnalyticsHelper);
            ViewUtils.setVisible(false, findById);
            ViewUtils.setVisible(false, textView3);
            textView3.setText(context2.getString(R.string.view_more));
            final int i2 = i;
            textView3.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((NutritionAnalyticsHelper) MiniFoodList.this.nutritionAnalyticsHelper.get()).reportViewMore(fieldLabel);
                    MiniFoodList.this.getMessageBus().post(new NavigateToFoodListEvent(MiniFoodList.this.nutrientEntry, i2));
                }
            });
            if (this.isWeekly) {
                miniFoodListsChartRendererImpl.addWeeklyChart(viewGroup4, this.selectedDate, fieldLabel, nutrientIndex, i);
            } else {
                miniFoodListsChartRendererImpl.addDailyChart(viewGroup4, this.selectedDate, fieldLabel, nutrientIndex, i);
            }
            String unitForNutrientIndex = NutritionalValues.unitForNutrientIndex(context2, nutrientIndex);
            textView2.setText(String.format("(%s)", new Object[]{unitForNutrientIndex}));
            ViewUtils.setVisible(Strings.notEmpty(unitForNutrientIndex), textView2);
            viewGroup2 = viewGroup;
        }
        viewGroup2.addView(viewGroup4);
    }

    /* access modifiers changed from: private */
    public Bus getMessageBus() {
        return (Bus) this.bus.get();
    }

    private void setupExampleFoodListIfNecessary(Context context, View view, int i) {
        if (!ConfigUtils.showExamplesInPremiumUpsellFoodList((ConfigService) this.configService.get())) {
            return;
        }
        if (i == 0 || i == 9) {
            View findById = ViewUtils.findById(view, R.id.example_food_list_upsell_container);
            TextView textView = (TextView) ViewUtils.findById(view, R.id.upsell_example_rice_bowl_value);
            TextView textView2 = (TextView) ViewUtils.findById(view, R.id.upsell_example_tomato_soup_value);
            ViewUtils.setVisible(findById);
            if (i == 0) {
                textView.setText(Strings.toString(Integer.valueOf(EXAMPLE_RICE_BOWL_CALORIES)));
                textView2.setText(Strings.toString(Integer.valueOf(185)));
            } else if (i == 9) {
                textView.setText(formatGrams(context, 43));
                textView2.setText(formatGrams(context, 30));
            }
        }
    }

    private String formatGrams(Context context, int i) {
        return String.format("%s %s", new Object[]{Integer.valueOf(i), context.getString(R.string.gram_abbreviation)});
    }
}
