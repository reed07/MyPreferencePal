package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import javax.inject.Inject;

public class ViewFoodActivity extends MfpActivity {
    private Food customFood;
    @BindView(2131362058)
    TextView energyTxt;
    private NutritionalValues nutritionValues;
    private Resources resources;
    private String servingSize = "";
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    private ArrayList<String> valueStrings;
    @BindView(2131364159)
    TextView viewTxtBrand;
    @BindView(2131364160)
    TextView viewTxtCalcium;
    @BindView(2131364161)
    TextView viewTxtCalories;
    @BindView(2131364162)
    TextView viewTxtCholesterol;
    @BindView(2131364163)
    TextView viewTxtDescription;
    @BindView(2131364164)
    TextView viewTxtDietaryFibers;
    @BindView(2131364165)
    TextView viewTxtIron;
    @BindView(2131364166)
    TextView viewTxtMonounsaturatedFat;
    @BindView(2131364167)
    TextView viewTxtPolyunsaturatedFat;
    @BindView(2131364168)
    TextView viewTxtPotassium;
    @BindView(2131364169)
    TextView viewTxtProtein;
    @BindView(2131364170)
    TextView viewTxtSaturatedFat;
    @BindView(2131364171)
    TextView viewTxtServingSize;
    @BindView(2131364172)
    TextView viewTxtServingsPerContainer;
    @BindView(2131364173)
    TextView viewTxtSodium;
    @BindView(2131364174)
    TextView viewTxtSugars;
    @BindView(2131364175)
    TextView viewTxtTotalCarbohydrates;
    @BindView(2131364176)
    TextView viewTxtTotalFat;
    @BindView(2131364177)
    TextView viewTxtTransFat;
    @BindView(2131364178)
    TextView viewTxtVitaminA;
    @BindView(2131364179)
    TextView viewTxtVitaminC;

    public static Intent newStartIntent(Context context, Food food) {
        return new Intent(context, ViewFoodActivity.class).putExtra("food", food);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.view_profile_food);
        this.customFood = (Food) ExtrasUtils.getParcelable(getIntent(), "food", Food.class.getClassLoader());
        this.resources = getResources();
        this.nutritionValues = this.customFood.getNutritionalValues();
        if (this.nutritionValues == null) {
            this.nutritionValues = new NutritionalValues();
            this.nutritionValues.initAsBlank();
        }
        this.viewTxtBrand.setHint(R.string.optionalField);
        setupValueStrings();
        loadInfo();
    }

    private void setupValueStrings() {
        ArrayList<String> arrayList = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            if (this.nutritionValues.valueIsNullForNutrientIndex(i)) {
                arrayList.add("");
            } else {
                arrayList.add(Strings.initStringWithFormattedFloat(this.nutritionValues.valueForNutrientIndex(i), 1));
            }
        }
        this.valueStrings = arrayList;
    }

    private void loadInfo() {
        this.servingSize = getServingSize(this.customFood.defaultPortion());
        this.viewTxtBrand.setText(Strings.toString(this.customFood.getBrand()));
        this.viewTxtDescription.setText(this.customFood.getDescription());
        this.viewTxtServingSize.setText(this.servingSize);
        this.viewTxtServingsPerContainer.setText(NumberUtils.localeStringFromFloat(this.customFood.servingsPerContainer()));
        this.viewTxtTotalFat.setText((CharSequence) this.valueStrings.get(1));
        this.viewTxtSaturatedFat.setText((CharSequence) this.valueStrings.get(2));
        this.viewTxtPolyunsaturatedFat.setText((CharSequence) this.valueStrings.get(3));
        this.viewTxtMonounsaturatedFat.setText((CharSequence) this.valueStrings.get(4));
        this.viewTxtTransFat.setText((CharSequence) this.valueStrings.get(5));
        this.viewTxtCholesterol.setText((CharSequence) this.valueStrings.get(6));
        this.viewTxtSodium.setText((CharSequence) this.valueStrings.get(7));
        this.viewTxtPotassium.setText((CharSequence) this.valueStrings.get(8));
        this.viewTxtTotalCarbohydrates.setText((CharSequence) this.valueStrings.get(9));
        this.viewTxtDietaryFibers.setText((CharSequence) this.valueStrings.get(10));
        this.viewTxtSugars.setText((CharSequence) this.valueStrings.get(11));
        this.viewTxtProtein.setText((CharSequence) this.valueStrings.get(12));
        this.viewTxtVitaminA.setText((CharSequence) this.valueStrings.get(13));
        this.viewTxtVitaminC.setText((CharSequence) this.valueStrings.get(14));
        this.viewTxtCalcium.setText((CharSequence) this.valueStrings.get(15));
        this.viewTxtIron.setText((CharSequence) this.valueStrings.get(16));
        this.energyTxt.setText(Strings.toString(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnitString()));
        this.viewTxtCalories.setText(Strings.toString(((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(Strings.toString(this.valueStrings.get(0)))));
    }

    private String getServingSize(FoodPortion foodPortion) {
        String str;
        if (foodPortion == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (foodPortion.getIsFraction()) {
            str = FoodPortion.GetFraction(foodPortion.getAmount());
        } else {
            str = NumberUtils.localeStringFromFloat(foodPortion.getAmount());
        }
        sb.append(str);
        sb.append(" ");
        sb.append(foodPortion.getDescription());
        return sb.toString();
    }
}
